package com.pengpj.code.config.druid;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * 更完整的配置说明连接：https://github.com/alibaba/druid/wiki/FAQ
 *
 * @author pengpj
 * @date 2019-04-15
 */
public class DruidMysqlConnectionConfig {

    /**
     * 获取数据库连接池
     *
     * @return
     */
    public DataSource getDruldDataSource() {
        DruidDataSource dataSource = new DruidDataSource();



        return dataSource;
    }

}
