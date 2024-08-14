package com.github.ryamal.documentmanager.entity;

import com.github.ryamal.documentmanager.presentation.DocumentModel;

public interface DocumentMapper {
    DocumentModel mapDomainToPresentation(AbstractDocumentEntity entity);

    AbstractDocumentEntity mapPresentationToDomain(DocumentModel item);
}
