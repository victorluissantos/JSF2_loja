package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	
	private static final SessionFactory sf;
	
	static{
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure();
		
//        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/sicc_teste");
//        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        cfg.setProperty("hibernate.connection.username", "root");
//        cfg.setProperty("hibernate.connection.password", "12345");
//		
//		cfg.setProperty("hibernate.current_session_context_class", "thread");
//
//        cfg.setProperty("hibernate.show_sql", "true");
//        cfg.setProperty("hibernate.format_sql", "true");
//        
//        cfg.setProperty("hibernate.hbm2ddl.auto", "update");
        
		sf = cfg.buildSessionFactory();
	}

	public static SessionFactory getSf() {
		return sf;
	}
	
	public static Session getSession(){
		return sf.openSession();
	}

}