package com.github.ryamal.documentmanager.util;

import com.github.ryamal.documentmanager.dao.impl.DocumentDaoImpl;
import com.github.ryamal.documentmanager.domain.DocumentRepository;
import com.github.ryamal.documentmanager.domain.DocumentRepositoryImpl;
import com.github.ryamal.documentmanager.entity.DocumentMapper;
import com.github.ryamal.documentmanager.entity.InvoiceEntity;
import com.github.ryamal.documentmanager.entity.PaymentRequestEntity;
import com.github.ryamal.documentmanager.entity.PaymentSlipEntity;
import com.github.ryamal.documentmanager.presentation.DocumentsViewModel;
import com.github.ryamal.documentmanager.presentation.DocumentsViewModelInterface;
import com.github.ryamal.documentmanager.presentation.SimpleDocumentsMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DocumentMapper.class).to(SimpleDocumentsMapper.class);
        bind(DocumentsViewModelInterface.class).to(DocumentsViewModel.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public DocumentRepository provideDocumentRepository() {
        return new DocumentRepositoryImpl(
                new DocumentDaoImpl<>(InvoiceEntity.class, HibernateUtil.getSessionFactory()),
                new DocumentDaoImpl<>(PaymentRequestEntity.class, HibernateUtil.getSessionFactory()),
                new DocumentDaoImpl<>(PaymentSlipEntity.class, HibernateUtil.getSessionFactory())
        );
    }
}
