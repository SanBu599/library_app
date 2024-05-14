package com.group.libraryapp.domain.user;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false, length = 20) //, name = "name") -> name과 이름이 같기에 생략 가능 아니면 적어줘야함// name Varchar(20)
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistoryList = new ArrayList<>();

    protected User(){}

    public User(String name, Integer age) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다",name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void loanBook(String bookName){
        this.userLoanHistoryList.add(new UserLoanHistory(this,bookName));
    }

    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistoryList
                .stream() // 함수형 프로그래밍을 할 수 있게, stream을 시작한다.
                .filter // 들어오는 객체들 중에 다음 조건을 충족하는 것만 필터링 한다.
                        (history -> history.getBookName().equals(bookName)) // UserLoanHistory 중 책 이름이 bookName과 같은 것!
                .findFirst() // 첫 번째로 해당하는 UserLoanHistory를 찾는다.
                .orElseThrow(IllegalArgumentException::new); // Optional을 제거하기 위해 없으면 예외를 던진다.
        targetHistory.doReturn(); // 그렇게 찾은 UserLoanHistory를 반납처리 한다.
    }
}
