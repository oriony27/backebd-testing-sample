package com.backend.testing.dto;

public class PageParamsDto {
    private int limit;
    private int offset;

    public PageParamsDto(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
