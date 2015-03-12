package com.aalexandrakis.mycrmliferay.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.aalexandrakis.mycrmliferay.commons.Constants;
 
public class HibernateUtil {
 
    //Annotation based configuration
    private static SessionFactory sessionAnnotationFactory;
    
    private  static SessionFactory buildSessionAnnotationFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate-annotation.cfg.xml");
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//            props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + System.getenv("MYCRM_DB") + "?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8")
            props.put("hibernate.connection.url", Constants.MYSQL_CONNECTION_STRING);
            props.put("hibernate.connection.username", System.getenv("MYCRM_DB_USERNAME"));
            props.put("hibernate.connection.password", System.getenv("MYCRM_DB_PASSWORD"));
            props.put("hibernate.current_session_context_class", "thread");
            props.put("hibernate.connection.CharSet", "utf-8");
            props.put("hibernate.connection.characterEncoding", "utf-8");
            props.put("hibernate.connection.useUnicode", "true");
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
            props.put("hibernate.connection.autoReconnect", "true");
            configuration.setProperties(props);
             
            System.out.println("Hibernate Annotation Configuration loaded");
             
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");
             
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
             
            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public  static SessionFactory getSessionAnnotationFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }
     
     
}