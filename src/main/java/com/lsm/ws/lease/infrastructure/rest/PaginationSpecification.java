package com.lsm.ws.lease.infrastructure.rest;

import com.lsm.ws.lease.domain.Pagination;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.beans.ConstructorProperties;

public class PaginationSpecification implements Pagination {

    @Parameter(example = "1")
    @Min(1)
    private final int page;

    @Min(1)
    @Max(100)
    @Parameter(example = "10")
    private final int size;

    @ConstructorProperties({"page", "size"})
    public PaginationSpecification(Integer page, Integer size) {
        this.page = page != null ? page : 1;
        this.size = size != null ? size : 10;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }
}
