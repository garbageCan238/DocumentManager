package com.github.ryamal.documentmanager.dao.impl;

import com.github.ryamal.documentmanager.dao.DocumentDAO;
import com.github.ryamal.documentmanager.entity.BaseDocument;
import org.hibernate.SessionFactory;

import java.util.List;

public class DocumentDaoImpl<T extends BaseDocument> extends GenericDAOImpl<T, String> implements DocumentDAO<T> {
    public DocumentDaoImpl(Class<T> persistentClass, SessionFactory factory) {
        super(persistentClass, factory);
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<T> getAllSortedByDate() {
        return sessionFactory.fromTransaction(session ->
                session.createQuery("from " + persistentClass.getName() + " order by date asc" , persistentClass).getResultList()
        );
    }

    @Override
    public boolean isExists(String number) {
        return sessionFactory.fromTransaction(session -> {
                    return session.get(persistentClass, number) != null;
                }
        );
    }

    @Override
    public void deleteMultiple(List<String> numbers) {
        sessionFactory.inTransaction(session -> {
            session.createQuery("delete from " + persistentClass.getName() + " where number in (:numbers)", persistentClass);
        });
    }
}
