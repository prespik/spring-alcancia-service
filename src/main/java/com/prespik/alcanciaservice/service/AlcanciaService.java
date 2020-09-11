package com.prespik.alcanciaservice.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;


@Component
public class AlcanciaService {


	// HashMap para almacenar las monedas 
	static HashMap<Integer, Integer> monedas = new HashMap<Integer, Integer>();

	/**
	 * Inicializa los Datos
	 */
	static {

		// Se definen las denominaciones de monedas permitidas en la alcancia 
		monedas.put(50, 0); 
		monedas.put(100, 0); 
		monedas.put(200, 0);
		monedas.put(500, 0);
		monedas.put(1000, 0);
	} 


	/**
	 * Metodo usado para ingresar dinero a la alcancia.
	 * Requerimiento: La alcancía solo puede recibir monedas de 50, 100, 200, 500 y 1000 pesos colombianos
	 * @param denominacion Representa la denominacion de la moneda a ingresar
	 * @param cantidad Representa la cantidad de monedas a ingresar
	 * @return boolean Retorna falso si la denominacion no es valida.
	 */
	public boolean recibirDinero(int denominacion, int cantidad) {

		//Se valida la existencia de la denominacion en el hashmap 
		boolean value = monedas.containsKey(denominacion);

		if (value) {
			//Actualizacion de la cantidad correspondiente a la denominacion especificada
			monedas.put(denominacion, monedas.getOrDefault(denominacion, 0) + cantidad);
			return true;
		} else {
			return false;
		}

	}


	/**
	 * Metodo usado para obtener la Cantidad de Monedas en la Alcancia.
	 * Requerimiento: En cualquier momento yo puedo saber la cantidad de monedas dentro de la alcancía
	 * @return int Retorna la Cantidad de monedas en la Alcancia.
	 */
	public int getCantidadMonedas() {

		int cantidadMonedas = 0;

		Set set = monedas.entrySet();
		Iterator iterator = set.iterator();

		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			cantidadMonedas += (int)mentry.getValue();
		}

		return cantidadMonedas;
	}


	/**
	 * Metodo usado para obtener la Cantidad de Dinero en la Alcancia.
	 * Requerimiento: En cualquier momento yo pudo saber la cantidad de dinero dentro de la alcancía
	 * @return 
	 * @return int Retorna la Cantidad de monedas en la Alcancia.
	 */
	public int getCantidadDinero() {

		int cantidadDinero = 0;

		Set set = monedas.entrySet();
		Iterator iterator = set.iterator();

		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			cantidadDinero += (int)mentry.getKey() * (int)mentry.getValue();
		}

		return cantidadDinero;

	}


	/**
	 * Metodo usado para obtener la Cantidad de Monedas en la Alcancia.
	 * Requerimiento: 
	 * En cualquier momento yo puedo saber la cantidad de monedas por denominación (50, 100, 200, 500 y 100) dentro de la alcancía. 
	 * Es decir, yo puedo preguntar cuántas monedas de 100 tengo y la aplicación debe darme el resultado
	 * @param denominacion Representa la denominacion de la moneda a ingresar
	 * @return int Retorna la Cantidad de monedas por denominacion especifica.
	 */
	public int getCantidadMonedasDenominacion(int denominacion) {

		int cantidadMonedas = 0;

		//Se valida la existencia de la denominacion en el hashmap 
		boolean value = monedas.containsKey(denominacion);

		if (value) {
			//Obtiene la cantidad correspondiente a la denominacion especificada
			cantidadMonedas = monedas.get(denominacion);
		}

		return cantidadMonedas;
	}


	/**
	 * Metodo usado para obtener la Cantidad de Dinero en la Alcancia.
	 * Requerimiento: 
	 * En cualquier momento yo puedo saber la cantidad de dinero por denominación dentro de la alcancía. 
	 * Es decir, yo puedo preguntar cuanto dinero tengo en monedas de 100 y la aplicación debe darme el resultado
	 * @param denominacion Representa la denominacion de la moneda a ingresar
	 * @return int Retorna la Cantidad de monedas en la Alcancia.
	 */
	public int getCantidadDineroDenominacion(int denominacion) {

		int cantidadDinero = 0;

		//Se valida la existencia de la denominacion en el hashmap 
		boolean value = monedas.containsKey(denominacion);

		if (value) {
			//Obtiene la cantidad correspondiente a la denominacion especificada
			cantidadDinero = monedas.get(denominacion) * denominacion;
		}

		return cantidadDinero;

	}




	/*

	private static List<Student> students = new ArrayList<>();

	static {
		//Initialize Data
		Course course1 = new Course("Course1", "Spring", "10 Steps", Arrays
				.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course3 = new Course("Course3", "Spring Boot", "6K Students",
				Arrays.asList("Learn Maven", "Learn Spring",
						"Learn Spring MVC", "First Example", "Second Example"));
		Course course4 = new Course("Course4", "Maven",
				"Most popular maven course on internet!", Arrays.asList(
						"Pom.xml", "Build Life Cycle", "Parent POM",
						"Importing into Eclipse"));

		Student ranga = new Student("Student1", "Ranga Karanam",
				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));

		Student satish = new Student("Student2", "Satish T",
				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));

		students.add(ranga);
		students.add(satish);
	}

	public List<Student> retrieveAllStudents() {
		return students;
	}

	public Student retrieveStudent(String studentId) {
		for (Student student : students) {
			if (student.getId().equals(studentId)) {
				return student;
			}
		}
		return null;
	}

	public List<Course> retrieveCourses(String studentId) {
		Student student = retrieveStudent(studentId);

		if(studentId.equalsIgnoreCase("Student1")){
			throw new RuntimeException("Something went wrong");
		}

		if (student == null) {
			return null;
		}

		return student.getCourses();
	}

	public Course retrieveCourse(String studentId, String courseId) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		for (Course course : student.getCourses()) {
			if (course.getId().equals(courseId)) {
				return course;
			}
		}

		return null;
	}

	private SecureRandom random = new SecureRandom();

	public Course addCourse(String studentId, Course course) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		course.setId(randomId);

		student.getCourses().add(course);

		return course;
	}
	 */
}