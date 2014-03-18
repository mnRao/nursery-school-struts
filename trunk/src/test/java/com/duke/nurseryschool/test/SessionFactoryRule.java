package com.duke.nurseryschool.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

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

@SuppressWarnings("deprecation")
public class SessionFactoryRule implements MethodRule {
	private SessionFactory	sessionFactory;
	private Transaction		transaction;
	private Session			session;

	@Override
	public Statement apply(final Statement statement, FrameworkMethod method,
			Object test) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				SessionFactoryRule.this.sessionFactory = SessionFactoryRule.this
						.createSessionFactory();
				SessionFactoryRule.this.createSession();
				SessionFactoryRule.this.beginTransaction();
				try {
					statement.evaluate();

				}
				finally {
					SessionFactoryRule.this.shutdown();
				}
			}
		};
	}

	private void shutdown() {
		try {
			try {
				try {
					this.transaction.rollback();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				this.session.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			this.sessionFactory.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
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
		configuration.setProperty("hibernate.dialect",
				"org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.driver_class",
				"org.h2.Driver");
		// configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem");
		configuration.setProperty("hibernate.connection.url",
				"jdbc:h2:mem:demoDB");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("show_sql", "true");

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}

	public Session createSession() {
		this.session = this.sessionFactory.openSession();
		return this.session;
	}

	public void commit() {
		this.transaction.commit();
	}

	public void beginTransaction() {
		this.transaction = this.session.beginTransaction();
	}

	public Session getSession() {
		return this.session;
	}
}
