package com.group.libraryapp.service.Fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.domain.fruit.FruitRepository;
import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Transactional
    public void saveFruit(FruitCreateRequest request){
        request.setStatus("Not_sold");
        Fruit fruit = fruitRepository.save(new Fruit(request.getName(),request.getWarehousingDate(), request.getPrice(),request.getStatus()));
    }

    @Transactional
    public void updateFruit(FruitCreateRequest request){
        Fruit fruit = fruitRepository.findById(request.getId());

        if (fruit == null) {
            throw new IllegalArgumentException("해당 ID에 해당하는 id를 찾을 수 없습니다: " + request.getId());
        }
        request.setStatus("Sold");
        fruit.updateStatus(request.getStatus());
        fruitRepository.save(fruit);
    }

    @Transactional
    public long getFruitStatus(@RequestParam String name){
        Long saleAmount = fruitRepository.sumPriceByStatusSold(name);
        return saleAmount;
    }

    @Transactional
    public long getNotFruitStause(@RequestParam String name){
        Long notSalesAmount = fruitRepository.notSalesAmount(name);
        return notSalesAmount;
    }


}
