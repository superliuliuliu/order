package com.lgy.order.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@Data
@ConfigurationProperties(prefix = "spring.datasource.druid.db2")
@MapperScan(basePackages = SlaveDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {

    static final String PACKAGE = "com.lgy.order.mapper.slave";

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName("slave");
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(0);
        dataSource.setMinIdle(0);
        dataSource.setMaxActive(10);
        // 配置获取连接等待超时的时间
        dataSource.setMaxWait(15000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，一个小时
        dataSource.setMinEvictableIdleTimeMillis(3600000);
        dataSource.setTestWhileIdle(true);
        // 这里建议配置为TRUE，防止取到的连接不可用
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        // 这里配置提交方式，默认就是TRUE，可以不用配置
        dataSource.setDefaultAutoCommit(true);

        return dataSource;
    }

    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager slaveTransactionManager() {
        return new DataSourceTransactionManager(slaveDataSource());
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource slaveDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(slaveDataSource);
        return sessionFactory.getObject();
    }
}
