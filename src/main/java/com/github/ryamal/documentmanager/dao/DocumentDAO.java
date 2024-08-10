package com.github.ryamal.documentmanager.dao;

import com.github.ryamal.documentmanager.entity.BaseDocument;

import java.util.List;

public interface DocumentDAO<T extends BaseDocument> extends GenericDAO<T, String> {
    List<T> getAllSortedByDate();

    boolean isExists(String number);

    void deleteMultiple(List<String> numbers);
}
