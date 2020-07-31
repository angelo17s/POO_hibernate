package com.cursos_online;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cursos_online.entidades.Curso;
import com.cursos_online.entidades.Estudiantes;

public class Main {


	static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		
		static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		
		public static void main(String[] args) {
			
			Curso cur1 = new Curso("Fundamentos de Java");
			Curso cur2 = new Curso("Hibernate para principiantes");
			
			
			ingresarCurso(cur1);
			ingresarCurso(cur2);
			
			Estudiantes est1 = new Estudiantes("Angelo ", "Silva");
			Estudiantes est2 = new Estudiantes("Bryan", "Andrade");
			
			
			ingresarEstudiante(est1);
			ingresarEstudiante(est2);
			
			
		
		}
		
		static void ingresarCurso(Curso curso) {
			
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(curso);
		
		session.getTransaction().commit();
		session.close();

	}
		static void ingresarEstudiante(Estudiantes estudiante) {
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(estudiante);
			
			session.getTransaction().commit();
			session.close();
			
			System.out.println(estudiante.getId());
}
}