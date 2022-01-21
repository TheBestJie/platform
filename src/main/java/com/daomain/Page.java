package com.daomain;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
    private List<?> rows;
    private Long total;

    public Page() {
    }

    public Page(List<?> rows, Long total) {
        this.rows = rows;
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
