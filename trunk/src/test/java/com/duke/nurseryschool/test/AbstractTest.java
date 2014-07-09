package com.duke.nurseryschool.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;

import com.duke.nurseryschool.hibernate.bean.AlternativeFeeMap;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeGroup;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Parent;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;

public abstract class AbstractTest {

	private SessionFactory sessionFactory;
	protected Session session;

	@Before
	public void setUp() throws Exception {
		this.sessionFactory = this.createSessionFactory();
		this.session = this.sessionFactory.openSession();
		Transaction transaction = this.session.beginTransaction();

		this.doSetup();
	}

	protected abstract void doSetup();

	@After
	public void tearDown() throws Exception {
		if (this.session.getTransaction().isActive()) {
			this.session.getTransaction().commit();
		}

		this.session.close();
	}

	private SessionFactory createSessionFactory() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		// Includes all beans
		configuration.addAnnotatedClass(Classes.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Fee.class)
				.addAnnotatedClass(FeeGroup.class)
				.addAnnotatedClass(FeeMap.class)
				.addAnnotatedClass(FeePolicy.class)
				.addAnnotatedClass(Month.class).addAnnotatedClass(Parent.class)
				.addAnnotatedClass(Payment.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(AlternativeFeeMap.class);
		// H2 memory database for testing
		// Note: Need to specify a trivial database name (not to test on actual
		// database & rollback)
		configuration.setProperty("hibernate.dialect",
				"org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.driver_class",
				"org.h2.Driver");
		configuration.setProperty("hibernate.connection.url",
				"jdbc:h2:mem:testDB");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create");
		configuration.setProperty("hibernate.hbm2ddl.import_files",
				"/initial-data-test.sql");
		configuration.setProperty("show_sql", "true");

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}

}
