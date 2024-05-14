package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitStatusResponse;
import com.group.libraryapp.service.Fruit.FruitService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FruitController {

    private final FruitService service;

    public FruitController(FruitService service) {
        this.service = service;
    }
    @PostMapping("api/v1/fruit")
    public void saveFruit(@RequestBody FruitCreateRequest request){
        service.saveFruit(request);
    }

    @PutMapping("api/v1/fruit")
    public void updateFruit(@RequestBody FruitCreateRequest request){
        service.updateFruit(request);
    }

    @GetMapping("api/v1/fruit/stat")
    public FruitStatusResponse getFruitStatus(@RequestParam String name){
        long salesAmount = service.getFruitStatus(name);
        long notSalesAmount = service.getNotFruitStause(name);
        return new FruitStatusResponse(salesAmount,notSalesAmount);
    }
}
