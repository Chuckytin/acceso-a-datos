package org.example.ejercicio2.dao;

import org.example.ejercicio2.entities.Alumno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

//NO UTILIZADO
public class AlumnoDao {

    /*
    private SessionFactory sessionFactory;

    public AlumnoDao() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void crearAlumno(Alumno alumno) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(alumno);
            transaction.commit();
        }
    }

    public List<Alumno> verAlumnos() {
        try (Session session = sessionFactory.openSession()) {
            Query<Alumno> query = session.createQuery("FROM Alumno", Alumno.class);
            return query.list();
        }
    }

    public Alumno buscarAlumnoPorNombre(String nombre) {
        try (Session session = sessionFactory.openSession()) {
            Query<Alumno> query = session.createQuery("FROM Alumno WHERE nombre = :nombre", Alumno.class);
            query.setParameter("nombre", nombre);
            return query.uniqueResult();
        }
    }

    public void modificarAlumno(Alumno alumno) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(alumno);
            transaction.commit();
        }
    }

    public void borrarAlumno(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Alumno alumno = session.find(Alumno.class, id);
            if (alumno != null) {
                session.remove(alumno);
            }
            transaction.commit();
        }
    }

     */
}