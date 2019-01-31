package com.senpure.chat.data.configuration;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.senpure.base.util.DatabaseUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * DatabaseConfigurtion
 *
 * @author senpure
 * @time 2019-01-04 10:58:29
 */
@Configuration
@MapperScan(basePackages = "com.senpure.chat.data", sqlSessionFactoryRef = "sqlSessionFactory",annotationClass = Mapper.class)
@EnableTransactionManagement
@EnableJpaRepositories
public class DatabaseConfiguration {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean(name = "dataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {
        DataSourceProperties prop = dataSourceProperties();
        DatabaseUtil.checkAndCreateDatabase(prop);
        return DruidDataSourceBuilder.create().build();
    }


   // @Bean(name = "sqlSessionFactory")
   // @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //配置mapper文件位置
        String mapperLocations = "classpath*:com/senpure/test/mapper/*.xml";
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));

        logger.info("sqlsessionFactory {}", mapperLocations);
        return sqlSessionFactoryBean.getObject();
    }

   // @Bean(name = "sqlSessionTemplate")
   // @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    //@Bean(name = "transactionManager")
  //  @Primary
    public DataSourceTransactionManager transactionManager(
            @Qualifier("dataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }


   // @Bean(name = "entityManager")
  //  @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")
    @Primary
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

   // @Bean(name = "entityManagerFactory")
   // @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(dataSource())
                .properties(jpaProperties().getProperties())
                .packages("com.senpure.chat.data.entity") //设置实体类所在位置
                .persistenceUnit("persistenceUnit")
                .build();
    }
}
