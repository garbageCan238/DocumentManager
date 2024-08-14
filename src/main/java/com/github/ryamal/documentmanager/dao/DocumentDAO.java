package com.github.ryamal.documentmanager.dao;

import com.github.ryamal.documentmanager.entity.AbstractDocumentEntity;

import java.util.List;

public interface DocumentDAO<T extends AbstractDocumentEntity> extends GenericDAO<T, String> {
    void deleteMultiple(List<String> numbers);
}
