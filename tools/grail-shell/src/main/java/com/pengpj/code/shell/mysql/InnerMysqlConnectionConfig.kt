package com.pengpj.code.shell.mysql

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "spring.database.mysql.inner")
class InnerMysqlConnectionConfig {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
}