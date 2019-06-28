package com.pengpj.code.shell.mail

import org.slf4j.LoggerFactory
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
class MailCommands {
    val log = LoggerFactory.getLogger(MailCommands::class.java)

    @ShellMethod("生成邮件内容")
    fun mail(dataId: String, username: String, password: String, orgId: String) {

        log.info(
                String.format(mailTpl,
                        dataId,
                        dataId,
                        username,
                        password,
                        dataId,
                        dataId,
                        dataId,
                        dataId,
                        username,
                        password,
                        orgId)
        )

    }

    val mailTpl = """

您好：
	%s  Grail 相关信息如下 ：
	dataId : %s

	Grail 相关账号信息如下：
	1. kibana
		host : 221.4.55.86    kibana.cvtapi.com
		登陆URL : http://kibana.cvtapi.com/login
		账号：%s
		初始密码：%s
		template :
			内网：template_log_grail_inner_%s
			外网：template_log_grail_pro_%s
		index :
			内网：log_grail_inner_%s_*
			外网：log_grail_pro_%s_*
	2. grafana
		host : 116.62.84.143 monitor.cvte.com
		登陆URL : http://monitor.cvte.com/grafana/?orgId=1
		账号：%s
		初始密码：%s
		org: %s

	注：
	1. ES 中 index 生成的两种方式：1：每天晚上12点自动生成；2：手动删除当前 index 后再上报数据。
	2. ES 中 template 生效，需要创建新的 index， index 创建参考1。
	3. Grafana 配置 DataSource 时，用到的账号信息，需要与 Kibana 上的账号信息保持一致。


	相关问题，cvtalk 或 微信 沟通。


    """.trimIndent()

}