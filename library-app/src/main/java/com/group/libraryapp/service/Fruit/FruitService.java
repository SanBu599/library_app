package com.group.libraryapp.service.Fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.domain.fruit.FruitRepository;
import com.group.libraryapp.dto.fruit.request.FruitCountRequest;
import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitListRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Transactional
    public void saveFruit(FruitCreateRequest request){
        request.setStatus("Not_sold");
        Fruit fruit = fruitRepository.save(new Fruit(
                request.getName(),
                request.getWarehousingDate(),
                request.getPrice(),
                request.getStatus()));
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
    public long getFruitStatus(String name){
        Long saleAmount = fruitRepository.sumPriceByStatusSold(name);
        return saleAmount;
    }

    @Transactional
    public long getNotFruitStause(String name){
        Long notSalesAmount = fruitRepository.notSalesAmount(name);
        return notSalesAmount;
    }

    @Transactional
    public FruitCountRequest count(String name){
        long count = fruitRepository.countByName(name);
        return new FruitCountRequest(count);
    }

    public List<FruitListRequest> getFruitList(String option, long price) {
        List<Fruit> fruits = list(option, price);
        return FruitInfoList(fruits);
    }
    @Transactional
    public List<Fruit> list(String option, long price){
        if(option.equals("GTE")){
            return fruitRepository.findByPriceGreaterThanEqual(price);
        } else if (option.equals("LTE")) {
            return fruitRepository.findByPriceLessThanEqual(price);
        }else {
            throw new IllegalArgumentException("GTE와 LTE 중 하나를 입력하세요");
        }
    }

    private FruitListRequest FruitInfo(Fruit fruit) {
        return new FruitListRequest(fruit.getName(), fruit.getPrice(), fruit.getWarehousing_date());
    }

    private List<FruitListRequest> FruitInfoList(List<Fruit> fruits) {
        return fruits.stream()
                .map(this::FruitInfo)
                .collect(Collectors.toList());
    }
}
