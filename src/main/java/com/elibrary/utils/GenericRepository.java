package com.elibrary.utils;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Page;



public interface GenericRepository<T> extends PanacheMongoRepository<T> {
    default PaginationResponse<T> findAllWithPagination(int pageNum, int resultCount) {

        Page page = new Page(pageNum - 1 , resultCount);

        return new PaginationResponse<>(findAll().page(page));

    }
}
