package com.github.axinger.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
public class QuartzDataSourceConfig {

    @Autowired
    private org.springframework.boot.autoconfigure.jdbc.DataSourceProperties dataSourceProperties;

//    @Order(1)
//    @Bean
//    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
//        DataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().build();
//        return schedulerFactoryBean -> {
//            schedulerFactoryBean.setDataSource(dataSource);
//            schedulerFactoryBean.setTransactionManager(new DataSourceTransactionManager(dataSource));
//        };
//    }

    //如果需要使用动态数据源里的某个数据源则打开以下配置，关闭上面配置。
    @Order(1)
    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer(DataSource dataSource) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource source = ds.getDataSource("slave_quartz");
        return schedulerFactoryBean -> {
            schedulerFactoryBean.setDataSource(source);
            schedulerFactoryBean.setTransactionManager(new DataSourceTransactionManager(source));
        };
    }

    // 使用@QuartzDataSource来指明quartz数据源。
    //如果需要使用动态数据源里的某个数据源则打开以下配置，关闭上面配置。
    // 会循环引用
//    @Bean
//    @QuartzDataSource
//    @Lazy
//    @Order(1)
//    public DataSource quartzDataSource(DataSource dataSource) {
//        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
//        return ds.getDataSource("slave_quartz");
//    }


}
