package demo;

import javax.activation.DataSource;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"demo"})
@Configuration
public class Config {

  @Bean
  public BasicDataSource dataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      //DriverManagerDataSource dataSource = new DriverManagerDataSource();

      dataSource.setDriverClassName("org.postgresql.Driver");
      dataSource.setUrl("jdbc:postgresql://localhost:5433/banque");
      dataSource.setUsername("postgres");
      dataSource.setPassword("root");
      return dataSource;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
      HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
      hibernateJpaVendorAdapter.setShowSql(false);
      hibernateJpaVendorAdapter.setGenerateDdl(false);
      hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
      return hibernateJpaVendorAdapter;
  }

  // EntityManagerFactory
  @Bean
  public EntityManagerFactory entityManagerFactory(JpaVendorAdapter jpaVendorAdapter, javax.sql.DataSource dataSource) {
      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setJpaVendorAdapter(jpaVendorAdapter);
      factory.setPackagesToScan("demo");
      factory.setDataSource(dataSource);
      factory.afterPropertiesSet();
      return factory.getObject();
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
      JpaTransactionManager txManager = new JpaTransactionManager();
      txManager.setEntityManagerFactory(entityManagerFactory);
      return txManager;
  }
}
