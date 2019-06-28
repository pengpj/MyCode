package com.pengpj.code.shell.mysql

import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import java.util.*

@ShellComponent
class DataIdCommands(
        var innerMysqlConnectionConfig: InnerMysqlConnectionConfig,
        var proMysqlConnectionConfig: ProMysqlConnectionConfig) {

    private val log = LoggerFactory.getLogger(DataIdCommands::class.java)

    @ShellMethod("内网环境创建 dataId")
    fun create(@ShellOption(help = "项目名，用于生成 dataId") name: String): String {

        log.info("项目名：{}", name)

        /**
         * datasource
         */
        val datasource = DriverManagerDataSource()
        datasource.setDriverClassName("com.mysql.jdbc.Driver")
        datasource.url = innerMysqlConnectionConfig.url
        datasource.username = innerMysqlConnectionConfig.username
        datasource.password = innerMysqlConnectionConfig.password

        val jdbcTemplate = JdbcTemplate(datasource)

        val dataId: String = name + "_" + UUID.randomUUID().toString().subSequence(0, 8)

        val createTlp: String = "insert into t_grail_config(c_data_id,c_enable) value('%s',1);"

        val insertSql = String.format(createTlp, dataId)

        val affectRows: Int = jdbcTemplate.update(insertSql)


        if (affectRows > 0) {
            log.info("SUCCESS ! 内网环境更新完成！ \n dataId = {}", dataId)
        } else {
            log.info("FAILED ! ")
        }
        return ""
    }

    @ShellMethod("线上环境增加 dataId")
    fun insert(@ShellOption(help = "dataId，用于插入线上数据库") name: String): String {

        log.info("dataId ：{}", name)

        /**
         * datasource
         */
        val datasource = DriverManagerDataSource()
        datasource.setDriverClassName("com.mysql.jdbc.Driver")
        datasource.url = proMysqlConnectionConfig.url
        datasource.username = proMysqlConnectionConfig.username
        datasource.password = proMysqlConnectionConfig.password

        val jdbcTemplate = JdbcTemplate(datasource)

        val createTlp: String = "insert into t_grail_config(c_data_id,c_enable) value('%s',1);"

        val insertSql = String.format(createTlp, name)

        val affectRows: Int = jdbcTemplate.update(insertSql)


        if (affectRows > 0) {
            log.info("SUCCESS ! 线上环境更新完成 ！\n dataId = {}", name)
        } else {
            log.info("FAILED ! ")
        }
        return ""
    }


}

