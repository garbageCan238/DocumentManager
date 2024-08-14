package com.github.ryamal.documentmanager.presentation;

import com.github.ryamal.documentmanager.entity.*;
import com.github.ryamal.documentmanager.util.DateFormatter;
import com.github.ryamal.documentmanager.util.DateParser;

import java.math.BigDecimal;
import java.util.Objects;

public class SimpleDocumentsMapper implements DocumentMapper {
    public SimpleDocumentsMapper() {}

    @Override
    public DocumentModel mapDomainToPresentation(AbstractDocumentEntity entity) {
        if (Objects.isNull(entity)) throw new NullPointerException("entity is null");
        switch (entity) {
            case InvoiceEntity invoiceEntity -> {
                DocumentModel document = DocumentFactory.createEmptyInvoice();
                document.attributes.get(0).value.set(invoiceEntity.getNumber());
                document.attributes.get(1).value.set(DateFormatter.formatDate(invoiceEntity.getDate()));
                document.attributes.get(2).value.set(invoiceEntity.getUser());
                document.attributes.get(3).value.set(invoiceEntity.getAmount().toString());
                document.attributes.get(4).value.set(invoiceEntity.getCurrency());
                document.attributes.get(5).value.set(invoiceEntity.getExchangeRate().toString());
                document.attributes.get(6).value.set(invoiceEntity.getProduct());
                document.attributes.get(7).value.set(invoiceEntity.getQuantity().toString());
                return document;
            }
            case PaymentRequestEntity paymentRequestEntity -> {
                DocumentModel document = DocumentFactory.createEmptyPaymentRequest();
                document.attributes.get(0).value.set(paymentRequestEntity.getNumber());
                document.attributes.get(1).value.set(DateFormatter.formatDate(paymentRequestEntity.getDate()));
                document.attributes.get(2).value.set(paymentRequestEntity.getUser());
                document.attributes.get(3).value.set(paymentRequestEntity.getAmount().toString());
                document.attributes.get(4).value.set(paymentRequestEntity.getEmployee());
                return document;
            }
            case PaymentSlipEntity paymentSlipEntity -> {
                DocumentModel document = DocumentFactory.createEmptyPaymentSlip();
                document.attributes.get(0).value.set(paymentSlipEntity.getNumber());
                document.attributes.get(1).value.set(DateFormatter.formatDate(paymentSlipEntity.getDate()));
                document.attributes.get(2).value.set(paymentSlipEntity.getUser());
                document.attributes.get(3).value.set(paymentSlipEntity.getCounterparty());
                document.attributes.get(4).value.set(paymentSlipEntity.getAmount().toString());
                document.attributes.get(5).value.set(paymentSlipEntity.getCurrency());
                document.attributes.get(6).value.set(paymentSlipEntity.getExchangeRate().toString());
                document.attributes.get(7).value.set(paymentSlipEntity.getCommission().toString());
                return document;
            }
            default -> {
            }
        }
        throw new RuntimeException();
    }

    @Override
    public AbstractDocumentEntity mapPresentationToDomain(DocumentModel item) {
        for (DocumentAttributeModel itemAttribute : item.getAttributes()) {
            if (itemAttribute.value.get().isEmpty()){
                throw new RuntimeException(itemAttribute.key.get() + " is empty");
            }
        }
        switch (item.type.get()) {
            case "Накладная" -> {
                InvoiceEntity invoice = new InvoiceEntity();
                invoice.setNumber(item.attributes.get(0).value.getValue());
                invoice.setDate(DateParser.parseDate(item.attributes.get(1).value.getValue()));
                invoice.setUser(item.attributes.get(2).value.getValue());
                invoice.setAmount(new BigDecimal(item.attributes.get(3).value.getValue()));
                invoice.setCurrency(item.attributes.get(4).value.getValue());
                invoice.setExchangeRate(new BigDecimal(item.attributes.get(5).value.getValue()));
                invoice.setProduct(item.attributes.get(6).value.getValue());
                invoice.setQuantity(new BigDecimal(item.attributes.get(7).value.getValue()));
                return invoice;
            }
            case "Платежка" -> {
                PaymentRequestEntity paymentRequest = new PaymentRequestEntity();
                paymentRequest.setNumber(item.attributes.get(0).value.getValue());
                paymentRequest.setDate(DateParser.parseDate(item.attributes.get(1).value.getValue()));
                paymentRequest.setUser(item.attributes.get(2).value.getValue());
                paymentRequest.setAmount(new BigDecimal(item.attributes.get(3).value.getValue()));
                paymentRequest.setEmployee(item.attributes.get(4).value.getValue());
                return paymentRequest;
            }
            case "Заявка на оплату" -> {
                PaymentSlipEntity paymentSlip = new PaymentSlipEntity();
                paymentSlip.setNumber(item.attributes.get(0).value.getValue());
                paymentSlip.setDate(DateParser.parseDate(item.attributes.get(1).value.getValue()));
                paymentSlip.setUser(item.attributes.get(2).value.getValue());
                paymentSlip.setCounterparty(item.attributes.get(3).value.getValue());
                paymentSlip.setAmount(new BigDecimal(item.attributes.get(4).value.getValue()));
                paymentSlip.setCurrency(item.attributes.get(5).value.getValue());
                paymentSlip.setExchangeRate(new BigDecimal(item.attributes.get(6).value.getValue()));
                paymentSlip.setCommission(new BigDecimal(item.attributes.get(7).value.getValue()));
                return paymentSlip;
            }
            case null, default -> {
            }
        }
        throw new RuntimeException();
    }
}
