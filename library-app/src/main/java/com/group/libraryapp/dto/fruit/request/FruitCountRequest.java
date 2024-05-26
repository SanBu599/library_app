package com.group.libraryapp.dto.fruit.request;

public class FruitCountRequest {

    private long count;

    public FruitCountRequest(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
}
