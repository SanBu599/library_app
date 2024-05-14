package com.group.libraryapp.domain.fruit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FruitRepository extends JpaRepository<Fruit, String> {

    Fruit findById(long id);

    @Query("SELECT SUM(f.price) FROM Fruit f WHERE f.status = 'SOLD'")
    Long sumPriceByStatusSold(String name);

    @Query("SELECT SUM(f.price) FROM Fruit f WHERE f.status = 'Not_sold'")
    Long notSalesAmount(String name);
}
