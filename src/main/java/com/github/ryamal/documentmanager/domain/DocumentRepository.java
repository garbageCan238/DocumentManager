package com.github.ryamal.documentmanager.domain;

import com.github.ryamal.documentmanager.entity.AbstractDocumentEntity;

import java.util.List;

public interface DocumentRepository {
    List<AbstractDocumentEntity> getAllSorted();

    void save(AbstractDocumentEntity entity);

    void deleteMultiple(List<AbstractDocumentEntity> entities);

    AbstractDocumentEntity get(AbstractDocumentEntity entity);
}
