package com.group.libraryapp.dto.fruit.request;

public class FruitStatusResponse {

    private long salesAmount;
    private long notSalesAmount;

    public FruitStatusResponse(long salesAmount, long notSalesAmount) {
        this.salesAmount = salesAmount;
        this.notSalesAmount = notSalesAmount;
    }

    public long getSalesAmount() {
        return salesAmount;
    }

    public long getNotSalesAmount() {
        return notSalesAmount;
    }
}
