package com.jdbc.main;

import java.util.List;

import java.util.Scanner;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jdbc.opration.PatientDAO;
import com.patient.model.Userfields;

@Configuration
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		System.out.println("Simple change made for GitHub repor check-in");
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring.xml");
		PatientDAO patientDAO = ctx.getBean("PatientDAOJDBCTemplate",
				PatientDAO.class);
		Userfields user = new Userfields();
		System.out
				.println("************************************************************");
		System.out.println("                Spring Using JDBC CRUD operations");
		System.out
				.println("************************************************************");
		System.out.println("");
		System.out.println("------------------option:-------------------");
		System.out.println("");
		System.out.println("1.Insert------");
		System.out.println("2.Show Table data--------");
		System.out.println("3.Update------");
		System.out.println("4.Delete------");
		System.out.println("5.Patient Information ");
		System.out.println("6.Exit ");
		System.out.println("");
		System.out.println("Choose Your options");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int op = sc.nextInt();
		switch (op) {
		case 1:
			System.out.println("Enter the Patient Name:");
			String name = sc.next();
			System.out.println("Enter the Patient Age:");
			int age = sc.nextInt();
			System.out.println("Enter the patient Address:");
			String address = sc.next();
			System.out.println("Enter the patient Sex:");
			String sex = sc.next();
			System.out.println("Enter the patient Date Of Birth:");
			String dob = sc.next();
			System.out.println("Enter the patient Height:");
			int height = sc.nextInt();
			System.out.println("Enter the patient Weight:");
			int weight = sc.nextInt();
			System.out.println("Enter the patient Mobile Number:");
			String mobile = sc.next();
			System.out.println("");
			//Userfields user = new Userfields();
			user.setName(name);
			user.setAge(age);
			user.setAddress(address);
			user.setSex(sex);
			user.setDob(dob);
			user.setHeight(height);
			user.setWeight(weight);
			user.setMobno(mobile);
			patientDAO.save(user);
			break;
		case 2:
			// Get All table Data
			List<Userfields> patient = patientDAO.getAll();
			System.out.println(patient);
			break;

		case 3:
			System.out.println("Enter the update Patient id:");
			int uid = sc.nextInt();
			
			System.out.println("Enter the Patient Name:");
			String uname = sc.next();
			System.out.println("Enter the Patient Age:");
			int uage = sc.nextInt();
			System.out.println("Enter the patient Address:");
			String uaddress = sc.next();
			System.out.println("Enter the patient Sex:");
			String usex = sc.next();
			System.out.println("Enter the patient Date Of Birth:");
			String udob = sc.next();
			System.out.println("Enter the patient Height:");
			int uheight = sc.nextInt();
			System.out.println("Enter the patient Weight:");
			int uweight = sc.nextInt();
			System.out.println("Enter the patient Mobile Number:");
			String umobile = sc.next();
			user.setName(uname);
			user.setAge(uage);
			user.setAddress(uaddress);
			user.setSex(usex);
			user.setDob(udob);
			user.setHeight(uheight);
			user.setWeight(uweight);
			user.setMobno(umobile);
			patientDAO.update(user);
			break;
		case 4:
			System.out.println("Enter the Deleted Patient id:");
			int did = sc.nextInt();
			patientDAO.deleteById(did);
			break;

		case 5:
			System.out.println("Enter the Patient id");
			int rid = sc.nextInt();
			Userfields users = patientDAO.getById(rid);
			System.out.println("Patient Details:" + users);
			break;
		case 6:
			System.out.println("Exit In Programme");
			break;
		case 7:
			System.out.println("Invalid Number!! Please Try Again");
			break;
		default:
			break;
		}
		ctx.close();

	}

}
