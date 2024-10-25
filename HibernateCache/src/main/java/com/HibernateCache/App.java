package com.HibernateCache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args )
    {
        Student student=null;
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  

        Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();  
          
        SessionFactory sf=meta.getSessionFactoryBuilder().build();  
        
        Session session1=sf.openSession();
        session1.beginTransaction();
        student = session1.get(Student.class, 100);
        System.out.println(student);
        student = session1.get(Student.class, 100);//first level cache 
        System.out.println(student);
        session1.getTransaction().commit();
        session1.close();
        
        
        Session session2=sf.openSession();
        session2.beginTransaction();
        student = session2.get(Student.class, 100);//second level cache
        System.out.println(student);
        session2.getTransaction().commit();
        session2.close();
    }
}
