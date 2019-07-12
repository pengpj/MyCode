package com.pengpj.code.shell.alarm

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(
        scanBasePackages = ["com.pengpj.code.shell.alarm"]
)
class AlarmApplication {
}

fun main(args: Array<String>) {
    SpringApplication.run(AlarmApplication::class.java, *args)
}