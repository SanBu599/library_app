package com.group.libraryapp.dto.fruit.request;

import java.time.LocalDate;

public class FruitListRequest {
    private String name;
    private long price;
    private LocalDate warehousingDate;

    public FruitListRequest(String name, long price, LocalDate warehousingDate) {
        this.name = name;
        this.price = price;
        this.warehousingDate = warehousingDate;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }
}
