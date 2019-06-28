package com.pengpj.code.shell

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(
        scanBasePackages = ["com.pengpj.code.shell"]
)
class ShellApplication {
}

fun main(args: Array<String>) {
    SpringApplication.run(ShellApplication::class.java, *args)
}