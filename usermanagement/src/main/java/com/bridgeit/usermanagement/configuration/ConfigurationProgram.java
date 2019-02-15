package com.bridgeit.usermanagement.configuration;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bridgeit.usermanagement.dto.UserDto;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bridgeit.usermanagement")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ConfigurationProgram {
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

	   private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";

	   private static final String PROPERTY_NAME_DATABASE_URL = "db.url";

	   private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	   private static final String PROPERTY_NAME_UPDATE="hibernate.hbm2ddl.auto";

	   private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";

	   private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	   private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";



	   @Resource
	   private Environment env;




	   @Bean

	   public DataSource dataSource() {
		   System.out.println("data source");
		
	       DriverManagerDataSource dataSource = new DriverManagerDataSource();



	       dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
	       
	       dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
	
	       dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));

	       dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

	  
	       System.out.println(dataSource);

	       return dataSource;

	   }



	  @Bean

	   public LocalSessionFactoryBean sessionFactory() {
		   System.out.println("session factory");

	       LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

	       sessionFactoryBean.setDataSource(dataSource());

	       
	       sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(

	PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

	       sessionFactoryBean.setHibernateProperties(hibProperties());

	       return sessionFactoryBean;

	   }



	  private Properties hibProperties() {
		   System.out.println("property");

	       Properties properties = new Properties();

	       	properties.put(PROPERTY_NAME_UPDATE,env.getRequiredProperty("hibernate.hbm2ddl.auto"));
	       	
	       properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));

	       properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
	       System.out.println(properties);
	       return properties; 

	   }



	   @Bean

	   public HibernateTransactionManager transactionManager() {
		   System.out.println("transaction");

	       HibernateTransactionManager transactionManager = new HibernateTransactionManager();

	       transactionManager.setSessionFactory(sessionFactory().getObject());

	       return transactionManager;

	   }
	   @Bean
	  public UserDto getUSerDto()
	  {
		   UserDto dto=new UserDto();
		   dto.setEmail("admin@gmail.com");
		   dto.setPassword("admin");
		   return dto;
	  }
	 
	   
//	   @Bean
//	   public String getPassword()
//	   {
//		   return "admin";
//	   }
}