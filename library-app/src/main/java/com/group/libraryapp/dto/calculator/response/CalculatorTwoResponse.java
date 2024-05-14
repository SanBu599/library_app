package com.group.libraryapp.dto.calculator.response;

import com.group.libraryapp.dto.calculator.request.CalculatorTwoRequest;

public class CalculatorTwoResponse {

    private Integer add;
    private Integer minus;
    private Integer multiply;

    public CalculatorTwoResponse(CalculatorTwoRequest request) {
        this.add = request.getNum1() + request.getNum2();
        this.minus = request.getNum1() - request.getNum2();
        this.multiply = request.getNum1() * request.getNum2();
    }

    public Integer getAdd() {
        return add;
    }

    public Integer getMinus() {
        return minus;
    }

    public Integer getMultiply() {
        return multiply;
    }
}
