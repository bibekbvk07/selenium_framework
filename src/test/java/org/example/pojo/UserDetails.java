package org.example.pojo;

import java.util.List;

public class UserDetails {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Data> data;
    private Support support;

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Data> getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                ", support=" + support +
                '}';
    }
}
