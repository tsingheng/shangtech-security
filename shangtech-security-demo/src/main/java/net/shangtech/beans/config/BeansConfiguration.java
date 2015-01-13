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
	private static final String 	JDBC_DBNAME 					= 		"JDBC_DBNAME";
	private static final String 	JDBC_DBHOST 					= 		"JDBC_DBHOST";
	
	@Bean
	public DataSource dataSource(){
		BasicDataSource ds = new BasicDataSource();
		
		// load proerties from environment variable
		ds.setUsername(System.getenv(JDBC_USERNAME));
		ds.setPassword(System.getenv(JDBC_PASSWORD));
		ds.setUrl("jdbc:mysql://" + System.getenv(JDBC_DBHOST) + ":3306/" + System.getenv(JDBC_DBNAME) + "?useUnicode=true&characterEncoding=utf-8&autoReconnect=true");
		
		// load properties from properties file
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
			for(Entry<Object, Object> entry : properties.entrySet()){
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
				for(Method method : ds.getClass().getDeclaredMethods()){
					if(method.getName().equals(methodName)){
						Class<?>[] paramTypes = method.getParameterTypes();
						if(paramTypes != null && paramTypes.length == 1){
							Class<?> paramType = paramTypes[0];
							if(String.class.equals(paramType)){
								method.invoke(ds, value);
							}
							else if(Integer.class.equals(paramType)){
								method.invoke(ds, Integer.parseInt(value));
							}
							else if(Long.class.equals(paramType)){
								method.invoke(ds, Long.parseLong(value));
							}
							else if(Float.class.equals(paramType)){
								method.invoke(ds, Float.parseFloat(value));
							}
							else if(Double.class.equals(paramType)){
								method.invoke(ds, Double.parseDouble(value));
							}
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ds;
	}
	
}
