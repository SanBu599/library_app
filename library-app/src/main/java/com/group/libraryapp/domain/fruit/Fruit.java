package com.group.libraryapp.domain.fruit;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String name;
    private LocalDate warehousing_date;
    private long price;

    @Column(columnDefinition = "varchar(10) CHECK (status IN ('SOLD', 'NOT_SOLD'))")
    private String status;

    protected Fruit(){}

    public Fruit(String name, LocalDate warehousing_date, long price, String status) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.",name));
        }
        this.name = name;
        this.price = price;
        this.warehousing_date = warehousing_date;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    public LocalDate getWarehousing_date() {
        return warehousing_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void update(long id){
        this.id = id;
    }

    public void updateStatus(String status){
        this.status = status;
    }
}
