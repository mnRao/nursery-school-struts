package com.duke.nurseryschool.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.duke.nurseryschool.hibernate.bean.ExtraFeeMap;
import com.duke.nurseryschool.hibernate.bean.ExtraFeeType;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Parent;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.bean.Subject;
import com.duke.nurseryschool.hibernate.bean.SubjectFeeMap;
import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;

public class TestHibernate {

	private SessionFactory sessionFactory;
	private Session session;

	@Before
	public void setUp() throws Exception {
		this.sessionFactory = this.createSessionFactory();
		this.session = this.sessionFactory.openSession();
		Transaction transaction = this.session.beginTransaction();
	}

	@Test
	public void test() throws ParseException {
		// Create objects
		Course course1 = new Course(1991, 1994);
		this.session.save(course1);

		Classes class1 = new Classes("Code 1");
		class1.setCourse(course1);
		this.session.save(class1);

		Parent parent1 = new Parent(1, "Stallone", "Accountant", "0123456789");
		this.session.save(parent1);

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-M-yyyy");
		String dateOfBirthString = "07-09-1991";
		Date dateOfBirth = dateFormatter.parse(dateOfBirthString);
		Student student1 = new Student("Jackie Chan", dateOfBirth, 1,
				"23 Thuy Khue", "0431234323", true, "Height: 173cm");
		student1.getParents().add(parent1);
		student1.setAssociatedClass(class1);
		this.session.save(student1);

		// Embedded
		Month month1 = new Month(1, 1991);
		this.session.save(month1);

		ClassMonth classMonthEmbedded = new ClassMonth(class1, month1);

		FeePolicy feePolicy1 = new FeePolicy(10, 5, 22);
		feePolicy1.setClassMonth(classMonthEmbedded);
		this.session.save(feePolicy1);

		// Subject fee map
		Subject subject1 = new Subject("First Subject");
		this.session.save(subject1);

		FeeDetails feeDetails1 = new FeeDetails();
		feeDetails1.setAssociatedClass(class1);
		feeDetails1.setMonth(month1);
		feeDetails1.setBasicStudyFee(100);
		this.session.save(feeDetails1);

		this.session.flush();

		SubjectFeeMap subjectFeeMap1 = new SubjectFeeMap(200, feeDetails1,
				subject1);
		this.session.save(subjectFeeMap1);

		BusinessLogicSolver.recalculateExtraStudyFee(
				feeDetails1.getFeeDetailsId(), this.session);

		// Commit all
		this.session.getTransaction().commit();

		// Assert
		List<Student> students = this.session.createQuery(
				Constant.DATABASE_QUERY.ALL_STUDENTS).list();
		assertEquals(1, students.size());
		assertEquals("Jackie Chan", students.get(0).getName());
		assertEquals(1,
				this.session.createQuery(Constant.DATABASE_QUERY.ALL_COURSES)
						.list().size());
		assertEquals(1,
				this.session.createQuery(Constant.DATABASE_QUERY.ALL_CLASSES)
						.list().size());

		// Assert embedded
		List<FeePolicy> feePolicies = this.session.createQuery(
				Constant.DATABASE_QUERY.ALL_FEE_POLICIES).list();
		assertEquals(1, feePolicies.size());
		assertEquals("Code 1", feePolicies.get(0).getClassMonth()
				.getAssociatedClass().getCode());
		assertEquals(1991, feePolicies.get(0).getClassMonth().getMonth()
				.getYear());

		// Assert subject fee map
		List<SubjectFeeMap> subjectFeeMaps = this.session.createQuery(
				Constant.DATABASE_QUERY.ALL_SUBJECT_FEE_MAPS).list();
		assertEquals(1, subjectFeeMaps.size());
		assertEquals("First Subject", subjectFeeMaps.get(0).getSubjectFee()
				.getSubject().getName());
		assertEquals(100, subjectFeeMaps.get(0).getSubjectFee().getFeeDetails()
				.getBasicStudyFee(), 0.1);
		assertEquals(200, subjectFeeMaps.get(0).getSubjectFee().getFeeDetails()
				.getTotalExtraStudyFee(), 0.1);
	}

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
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(ExtraFeeMap.class)
				.addAnnotatedClass(ExtraFeeType.class)
				.addAnnotatedClass(FeeDetails.class)
				.addAnnotatedClass(FeePolicy.class)
				.addAnnotatedClass(Month.class).addAnnotatedClass(Parent.class)
				.addAnnotatedClass(Payment.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Subject.class)
				.addAnnotatedClass(SubjectFeeMap.class);
		// H2 memory database for testing
		// Note: Need to specify a trivial database name (not to test on actual
		// database & rollback)
		configuration.setProperty("hibernate.dialect",
				"org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.driver_class",
				"org.h2.Driver");
		configuration.setProperty("hibernate.connection.url",
				"jdbc:h2:mem:testDB");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("show_sql", "true");

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}
}
