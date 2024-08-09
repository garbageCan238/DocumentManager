package com.github.ryamal.documentmanager.util;

import com.github.ryamal.documentmanager.entity.Invoice;
import com.github.ryamal.documentmanager.entity.PaymentRequest;
import com.github.ryamal.documentmanager.entity.PaymentSlip;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.load(HibernateUtil.class.getResourceAsStream("/hibernate.properties"));

            return new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(Invoice.class)
                    .addAnnotatedClass(PaymentRequest.class)
                    .addAnnotatedClass(PaymentSlip.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
