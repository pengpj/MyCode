package com.pengpj.code.shell.alarm.user

import org.slf4j.LoggerFactory
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
class UserCommands(var userAddConfig: AlarmUserAddConfig) {

    val log = LoggerFactory.getLogger(UserCommands::class.java)

    @ShellMethod(value = "add")
    fun addUser(userName: String) {

        log.info("name is $userName")
    }
}

data class AlarmUser(val userName: String, val loginName: String)
