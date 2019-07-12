package com.pengpj.code.shell.alarm.user

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "url")
class AlarmUserAddConfig {
    lateinit var inner: String
}