package com.cursos_online;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cursos_online.entidades.Curso;


import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transaction;

import com.cursos_online.entidades.Estudiantes;

public class Main {

	static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
	
	static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
	static Session session = sessionFactory.openSession();
	Transaction tx = (Transaction) session.beginTransaction();
	public static void main(String[] args) {
		//ingresar curso
		
		Curso cur1 = new Curso("Fundamentos de Java");
		Curso cur2 = new Curso("Hibernate para principiantes");
		Curso cur3 = new Curso("Hibernate para profesionales");
		
		
		ingresarCurso(cur1);
		ingresarCurso(cur2);
		ingresarCurso(cur3);
		
		//ingresar curso
		
				Estudiantes est1 = new Estudiantes ("Angelo", "silva");
				Estudiantes est2 = new Estudiantes ("carlos","andrade");
				Estudiantes est3 = new Estudiantes ("jose","jimenez");
				
				
				ingresarEstudiante(est1);
				ingresarEstudiante(est2);
				ingresarEstudiante(est3);
				
		
		//modificar curso
         
		
		modificaCurso(3,"finanza");
		//eliminar curso
        
		
		
				eliminarCurso(2);
		
		//modificar estudiantes
        
		
				modificaEstudiante(5,"juan","paredes");
		
		
		//eliminar estudiantes
        
		
		
				eliminarEstudiante(4);
		
		
		
		List<Curso> cursos= getCursos();
		
		for(Curso temp:cursos) {
			System.out.println(temp);
		}
		
       
	}
	
	static void eliminarCurso(int id) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		Curso curso = 
                (Curso)session.get(Curso.class,id);
		session.delete(curso);
		
		session.getTransaction().commit();
		session.close();
	}
		
	
	static void modificaCurso (int id, String nombre) {
		Session session = sessionFactory.openSession();
	
		   
		    	  session.beginTransaction();
		         Curso curso = 
		                    (Curso)session.get(Curso.class,id); 
		         curso.setDescripcion(nombre);
		         session.update(curso); 
		     	session.getTransaction().commit();
		     
		         session.close(); 
		      
		   }
	
	
	
	static void modificaEstudiante (int id, String nombre, String apellido) {
		Session session = sessionFactory.openSession();
		
		   
  	  session.beginTransaction();
       Estudiantes estudiantes = 
                  (Estudiantes)session.get(Estudiantes.class,id); 
       estudiantes.setNombre(nombre);
       estudiantes.setApellido(apellido);
       session.update(estudiantes); 
   	session.getTransaction().commit();
   
       session.close(); 
    
 }
	static void eliminarEstudiante (int id ) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		 Estudiantes estudiantes = 
                 (Estudiantes)session.get(Estudiantes.class,id); 
		session.delete(estudiantes);
		
		session.getTransaction().commit();
		session.close();
	}
	
	static List<Curso> getCursos(){
		Session session = sessionFactory.openSession();
		List<Curso> cursos = session.createQuery("from Curso", Curso.class).list();
		return cursos;
	}
	
	static List<Estudiantes> getEstudiantes(){
		Session session = sessionFactory.openSession();
		List<Estudiantes> estudiantes = session.createQuery("from Estudiante", Estudiantes.class).list();
		return estudiantes;
	}
	static List<Estudiantes> getEstudiantesPorNombre(String nombre){
		Session session = sessionFactory.openSession();
	    Query query =  session.createQuery("from Estudiante where nombre=:nombre");
		query.setParameter("nombre", nombre);
		List<Estudiantes> estudiantes=(List<Estudiantes>) query.getResultList();
		return estudiantes;
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

		
