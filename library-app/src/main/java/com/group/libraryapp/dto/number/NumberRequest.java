package com.group.libraryapp.dto.number;

import java.util.List;

public class NumberRequest {

    List<Integer> numbers;

    public NumberRequest() {
    }

    public NumberRequest(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
