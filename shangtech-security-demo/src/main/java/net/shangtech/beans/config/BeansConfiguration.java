package net.shangtech.beans.config;

import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
	
	private static final String 	JDBC_USERNAME 					= 		"JDBC_USERNAME";
	private static final String 	JDBC_PASSWORD 					= 		"JDBC_PASSWORD";
	
	@Bean
	public DataSource dataSource(){
		BasicDataSource ds = new BasicDataSource();
		
		// load proerties from environment variable
		ds.setUsername(System.getenv(JDBC_USERNAME));
		ds.setPassword(System.getenv(JDBC_PASSWORD));
		
		// load properties from properties file
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
			for(Entry<Object, Object> entry : properties.entrySet()){
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				Method method = getClass().getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), String.class);
				method.invoke(this, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ds;
	}
	
}
