package com.github.ryamal.documentmanager.util;

import com.github.ryamal.documentmanager.entity.InvoiceEntity;
import com.github.ryamal.documentmanager.entity.PaymentRequestEntity;
import com.github.ryamal.documentmanager.entity.PaymentSlipEntity;
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
                    .addAnnotatedClass(InvoiceEntity.class)
                    .addAnnotatedClass(PaymentRequestEntity.class)
                    .addAnnotatedClass(PaymentSlipEntity.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
