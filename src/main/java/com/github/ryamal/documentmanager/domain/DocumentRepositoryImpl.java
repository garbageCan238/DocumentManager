package com.github.ryamal.documentmanager.domain;

import com.github.ryamal.documentmanager.dao.DocumentDAO;
import com.github.ryamal.documentmanager.entity.AbstractDocumentEntity;
import com.github.ryamal.documentmanager.entity.InvoiceEntity;
import com.github.ryamal.documentmanager.entity.PaymentRequestEntity;
import com.github.ryamal.documentmanager.entity.PaymentSlipEntity;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {
    private final DocumentDAO<InvoiceEntity> invoiceDao;
    private final DocumentDAO<PaymentRequestEntity> paymentRequestDao;
    private final DocumentDAO<PaymentSlipEntity> paymentSlipDAO;

    @Inject
    public DocumentRepositoryImpl(DocumentDAO<InvoiceEntity> invoiceDao,
                                  DocumentDAO<PaymentRequestEntity> paymentRequestDao,
                                  DocumentDAO<PaymentSlipEntity> paymentSlipDAO) {
        this.invoiceDao = invoiceDao;
        this.paymentRequestDao = paymentRequestDao;
        this.paymentSlipDAO = paymentSlipDAO;
    }

    @Override
    public List<AbstractDocumentEntity> getAllSorted() {
        List<AbstractDocumentEntity> documents = new ArrayList<>();
        documents.addAll(invoiceDao.getAll());
        documents.addAll(paymentRequestDao.getAll());
        documents.addAll(paymentSlipDAO.getAll());
        documents.sort(Comparator.comparing(AbstractDocumentEntity::getDate));
        return documents;
    }

    @Override
    public void save(AbstractDocumentEntity entity) {
        if (entity instanceof InvoiceEntity) {
            invoiceDao.save((InvoiceEntity) entity);
        }
        if (entity instanceof PaymentRequestEntity) {
            paymentRequestDao.save((PaymentRequestEntity) entity);
        }
        if (entity instanceof PaymentSlipEntity) {
            paymentSlipDAO.save((PaymentSlipEntity) entity);
        }
    }

    @Override
    public void deleteMultiple(List<AbstractDocumentEntity> entities) {
        List<String> invoiceNumbers = new ArrayList<>();
        List<String> paymentRequestNumbers = new ArrayList<>();
        List<String> paymentSlipNumbers = new ArrayList<>();
        for (AbstractDocumentEntity entity : entities) {
            if (entity instanceof InvoiceEntity) invoiceNumbers.add(entity.getNumber());
            if (entity instanceof PaymentRequestEntity) paymentRequestNumbers.add(entity.getNumber());
            if (entity instanceof PaymentSlipEntity) paymentSlipNumbers.add(entity.getNumber());
        }
        invoiceDao.deleteMultiple(invoiceNumbers);
        paymentRequestDao.deleteMultiple(paymentRequestNumbers);
        paymentSlipDAO.deleteMultiple(paymentSlipNumbers);
    }

    @Override
    public AbstractDocumentEntity get(AbstractDocumentEntity entity) {
        if (entity instanceof InvoiceEntity) return invoiceDao.get(entity.getNumber());
        if (entity instanceof PaymentRequestEntity) return paymentRequestDao.get(entity.getNumber());
        if (entity instanceof PaymentSlipEntity) return paymentSlipDAO.get(entity.getNumber());
        else throw new RuntimeException("Entity not found");
    }
}
