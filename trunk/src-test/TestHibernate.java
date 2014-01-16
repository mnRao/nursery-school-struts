import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.duke.nurseryschool.hibernate.HibernateUtil;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.duke.nurseryschool.hibernate.bean.Parent;
import com.duke.nurseryschool.hibernate.bean.Student;

public class TestHibernate {

	public static void main(String[] args) throws ParseException {
		// Start transaction
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Create objects
		Course course1 = new Course(1991, 1994);
		session.save(course1);

		Classes class1 = new Classes("Code 1");
		class1.setCourse(course1);
		session.save(class1);

		Parent parent1 = new Parent(1, "Stallone", "Accountant", "0123456789");
		session.save(parent1);

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-M-yyyy");
		String dateOfBirthString = "07-09-1991";
		Date dateOfBirth = dateFormatter.parse(dateOfBirthString);
		Student student1 = new Student("Jackie Chan", dateOfBirth, 1,
				"23 Thuy Khue", "0431234323", true, "Height: 173cm");
		student1.getParents().add(parent1);
		student1.setAssociatedClass(class1);
		session.save(student1);

		// End transaction
		session.getTransaction().commit();
		session.close();
	}
}
