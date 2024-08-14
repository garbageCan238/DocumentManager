package com.github.ryamal.documentmanager.dao.impl;

import com.github.ryamal.documentmanager.dao.DocumentDAO;
import com.github.ryamal.documentmanager.entity.AbstractDocumentEntity;
import org.hibernate.SessionFactory;

import java.util.List;

public class DocumentDaoImpl<T extends AbstractDocumentEntity> extends GenericDAOImpl<T, String> implements DocumentDAO<T> {
    public DocumentDaoImpl(Class<T> persistentClass, SessionFactory factory) {
        super(persistentClass, factory);
    }

    @Override
    public void deleteMultiple(List<String> numbers) {
        if (numbers.isEmpty()) return;
        sessionFactory.inTransaction(session ->
                session.createMutationQuery("delete from " + persistentClass.getName() + " where number in (:numbers)")
                        .setParameter("numbers", numbers)
                        .executeUpdate());
    }
}
