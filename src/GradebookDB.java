import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Assignment;
import model.Student;

import java.math.BigDecimal;
import java.util.List;
import customTools.DBUtil;

public class GradebookDB {

	public static Student findStudent(String first, String last, long studentID) {
		Student found = new Student();
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT s from Student s WHERE s.firstname = '" + first 
				+ "' AND s.lastname = '" + last 
				+ "' AND s.sId = " + studentID;
		TypedQuery<Student> q = em.createQuery(qString, Student.class);
		try {
			found = q.getSingleResult();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			em.close();
		}
		return found;
	}

	public static boolean studentExists(String first, String last, long studentID) {
		boolean exists = false;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT s from Student s WHERE s.firstname = '" + first 
				+ "' AND s.lastname = '" + last 
				+ "' AND s.sId = " + studentID;
		TypedQuery<Student> q = em.createQuery(qString, Student.class);
		List<Student> assignments;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				exists = false;
			} else {
				exists = true;
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			em.close();
		}

		return exists;
	}

	public static void addGrade(Student student, long assignmentID, String assignmentType, BigDecimal grade) {
		Assignment add = new Assignment();
		add.setAId(assignmentID);
		add.setAType(assignmentType);
		add.setGrade(grade);
		add.setStudent(student);

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(add);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void updateGrade(Student student, long assignmentID, String assignmentType, BigDecimal grade) {
		Assignment upd = new Assignment();
		upd.setAId(assignmentID);
		upd.setAType(assignmentType);
		upd.setGrade(grade);
		upd.setStudent(student);

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(upd);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static List<Assignment> getGrades(Student student) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT a from Assignment a WHERE a.student.sId = " + student.getSId();
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments = null;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			em.close();
		}
		return assignments;
	}
	
	public static List<Assignment> getAllGrades() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT a from Assignment a";
		TypedQuery<Assignment> q = em.createQuery(qString, Assignment.class);
		List<Assignment> assignments = null;
		try {
			assignments = q.getResultList();
			if(assignments == null || assignments.isEmpty()) {
				assignments = null;
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			em.close();
		}
		return assignments;
	}
	
	/*
	create table students (
	s_id int primary key,
	firstname varchar(50),
	lastname varchar(50)
	);

	create table assignments (
	a_id int primary key,
	a_type varchar(50),
	grade int,
	s_id int
	);
	alter table assignments add foreign key (s_id) references students(s_id);

	insert into students (s_id, firstname, lastname) values (1, 'Eric', 'Cheng');
	insert into students (s_id, firstname, lastname) values (2, 'Daniel', 'Pak');
	insert into students (s_id, firstname, lastname) values (3, 'David', 'Shiao');

	insert into assignments (a_id, a_type, grade, s_id) values (1, 'quiz', 95, 1);
	insert into assignments (a_id, a_type, grade, s_id) values (2, 'quiz', 89, 1);
	insert into assignments (a_id, a_type, grade, s_id) values (3, 'project', 90, 1);
	insert into assignments (a_id, a_type, grade, s_id) values (4, 'project', 86, 1);
	insert into assignments (a_id, a_type, grade, s_id) values (5, 'quiz', 92, 2);
	insert into assignments (a_id, a_type, grade, s_id) values (6, 'quiz', 90, 2);
	insert into assignments (a_id, a_type, grade, s_id) values (7, 'project', 96, 2);
	insert into assignments (a_id, a_type, grade, s_id) values (8, 'project', 88, 2);
	insert into assignments (a_id, a_type, grade, s_id) values (9, 'quiz', 98, 3);
	insert into assignments (a_id, a_type, grade, s_id) values (10, 'quiz', 94, 3);
	insert into assignments (a_id, a_type, grade, s_id) values (11, 'project', 96, 3);
	insert into assignments (a_id, a_type, grade, s_id) values (12, 'project', 92, 3);




	 */
}
