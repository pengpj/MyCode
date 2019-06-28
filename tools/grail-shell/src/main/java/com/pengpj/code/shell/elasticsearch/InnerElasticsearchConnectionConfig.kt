package com.pengpj.code.shell.elasticsearch

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "spring.database.elasticsearch.inner")
class InnerElasticsearchConnectionConfig {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
}