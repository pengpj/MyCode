package com.pengpj.code.shell.elasticsearch

import org.apache.commons.codec.binary.Base64
import org.apache.http.HttpHost
import org.apache.http.HttpStatus
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.entity.ContentType
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.nio.entity.NStringEntity
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import org.springframework.web.client.RestTemplate

@ShellComponent
class TemplateCommands(
        var innerElasticsearchConnectionConfig: InnerElasticsearchConnectionConfig,
        var proElasticsearchConnectionConfig: ProElasticsearchConnectionConfig) {

    val log = LoggerFactory.getLogger(TemplateCommands::class.java)

    val templateTpl: String = """
    {
    "order": 1,
    "index_patterns": [
      "%s"
    ],
    "settings": {
      "index": {
        "number_of_shards": "10",
        "number_of_replicas": "1",
        "refresh_interval": "25s"
      }
    },
    "mappings": {
      "_default_": {
        "dynamic": false,
        "properties": {
          "@timestamp": {
            "type": "date"
          },
          "dataId": {
            "type": "keyword"
          }
        }
      }
    },
    "aliases": {}
    }
    """.trimIndent()

    val innerTemplateNameTpl: String = "template_log_grail_inner_%s"
    val innerIndexTpl: String = "log_grail_inner_%s_*"
    val proTemplateNameTpl: String = "template_log_grail_pro_%s"
    val proIndexTpl: String = "log_grail_pro_%s_*"

    @ShellMethod("创建内外网 template")
    fun template(@ShellOption(help = "dataId，用于创建 template ") dataId: String) {

        val params = emptyMap<String, String>()

        /**
         * 内网操作
         */
        val innerClient = RestHighLevelClient(
                RestClient.builder(HttpHost.create(innerElasticsearchConnectionConfig.url)).setHttpClientConfigCallback { builder ->
                    val provider = BasicCredentialsProvider()
                    provider.setCredentials(AuthScope.ANY, UsernamePasswordCredentials(innerElasticsearchConnectionConfig.username, innerElasticsearchConnectionConfig.password))
                    builder.setDefaultCredentialsProvider(provider)
                }
        )
        try {
            val innerIndex = String.format(innerIndexTpl, dataId)
            val innerTemplateName = String.format(innerTemplateNameTpl, dataId)
            val innerEntity = NStringEntity(
                    String.format(templateTpl, innerIndex),
                    ContentType.APPLICATION_JSON)
            val innerUri: String = "/_template/" + innerTemplateName
            val innerResponse = innerClient.lowLevelClient.performRequest(
                    "PUT",
                    innerUri,
                    params,
                    innerEntity
            )
            log.info("inner create template :  {},\n index = {},\n template = {}", innerResponse.statusLine.statusCode == HttpStatus.SC_OK,
                    innerIndex,
                    innerTemplateName)
        } catch (e: Exception) {
            log.error(e.message, e)
        } finally {
            innerClient.close()
        }

        /**
         * 外网操作
         */
        val proClient = RestHighLevelClient(
                RestClient.builder(HttpHost.create(proElasticsearchConnectionConfig.url)).setHttpClientConfigCallback { builder ->
                    val provider = BasicCredentialsProvider()
                    provider.setCredentials(AuthScope.ANY, UsernamePasswordCredentials(proElasticsearchConnectionConfig.username, proElasticsearchConnectionConfig.password))
                    builder.setDefaultCredentialsProvider(provider)
                }
        )
        try {
            val proIndex = String.format(proIndexTpl, dataId)
            val proTemplateName = String.format(proTemplateNameTpl, dataId)
            val proEntity = NStringEntity(
                    String.format(templateTpl, proIndex),
                    ContentType.APPLICATION_JSON)
            val proUri: String = "/_template/" + proTemplateName
            val proResponse = proClient.lowLevelClient.performRequest(
                    "PUT",
                    proUri,
                    params,
                    proEntity
            )
            log.info("pro create template : {},\n index = {},\n template = {}", proResponse.statusLine.statusCode == HttpStatus.SC_OK,
                    proIndex,
                    proTemplateName)
        } catch (e: Exception) {
            log.error(e.message, e)
        } finally {
            proClient.close()
        }
    }

    val indexPatternTpl: String = """
        {
            "attributes": {
                "title": "%s",
                "timeFieldName": "@timestamp"
            }
        }
    """.trimIndent()

    @ShellMethod("创建内外网 index")
    fun index(@ShellOption(help = "dataId，用于创建 index-pattern") dataId: String) {
        val restTemplate = RestTemplate()
        /**
         * 内网操作
         */
        try {
            val innerIndex = String.format(innerIndexTpl, dataId)
            val innerUri: String = "http://kibana.cvtapi.com/api/saved_objects/index-pattern"

            val httpHeaders = HttpHeaders()
            httpHeaders.set("Content-Type", "application/json")
            httpHeaders.set("kbn-version", "6.1.1")
            httpHeaders.set("Authorization", "Basic " + Base64.encodeBase64String((innerElasticsearchConnectionConfig.username + ":" + innerElasticsearchConnectionConfig.password).toByteArray(Charsets.UTF_8)))
            val entity = HttpEntity<String>(String.format(indexPatternTpl, innerIndex), httpHeaders)
            val innerResponse = restTemplate.postForEntity(innerUri, entity, String::class.java)
            if (innerResponse.statusCode == org.springframework.http.HttpStatus.OK) {
                log.info("inner index create success. index = {}", innerIndex)
            }
        } catch (e: Exception) {
            log.error(e.message, e)
        } finally {
            // nothing to do
        }

        /**
         * 外网操作
         */
        try {
            val proIndex = String.format(proIndexTpl, dataId)
            val proUri = "http://kibana.cvtapi.com/api/saved_objects/index-pattern"

            val httpHeaders = HttpHeaders()
            httpHeaders.set("Content-Type", "application/json")
            httpHeaders.set("kbn-version", "6.1.1")
            httpHeaders.set("Authorization", "Basic " + Base64.encodeBase64String((proElasticsearchConnectionConfig.username + ":" + proElasticsearchConnectionConfig.password).toByteArray(Charsets.UTF_8)))
            val entity = HttpEntity<String>(String.format(indexPatternTpl, proIndex), httpHeaders)
            val innerResponse = restTemplate.postForEntity(proUri, entity, String::class.java)
            if (innerResponse.statusCode == org.springframework.http.HttpStatus.OK) {
                log.info("inner index create success. index = {}", proIndex)
            }
        } catch (e: Exception) {
            log.error(e.message, e)
        } finally {
            // nothing to do
        }
    }

}