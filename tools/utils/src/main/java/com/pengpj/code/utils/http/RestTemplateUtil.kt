package com.pengpj.code.utils.http

import com.alibaba.fastjson.JSONObject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate

class RestTemplateUtil {

    fun post(url: String, obj: Any): ResponseEntity<String> {
        val restTemplate = RestTemplate()
        // 设置默认字符为UTF-8
        restTemplate.messageConverters.forEach { t ->
            run {
                if (t is StringHttpMessageConverter) {
                    t.defaultCharset = Charsets.UTF_8
                }
            }
        }

        val headers = HttpHeaders()
        headers.set("Content-Type", "application/json")
        val httpEntity = HttpEntity(
                JSONObject.toJSONString(obj),
                headers)
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String::class.java)

    }

}