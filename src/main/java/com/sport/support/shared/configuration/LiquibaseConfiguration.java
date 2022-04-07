package com.sport.support.shared.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

   @Bean
   public SpringLiquibase liquibase(DataSource dataSource) {
      SpringLiquibase liquibase = new SpringLiquibase();
      liquibase.setChangeLog("classpath:db/changelog/database-changelog.xml");
      liquibase.setDataSource(dataSource);
      liquibase.setShouldRun(false);
      return liquibase;
   }

}
