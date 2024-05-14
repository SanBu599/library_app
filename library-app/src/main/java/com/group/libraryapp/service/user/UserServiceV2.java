package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 아래 있는 함수가 시작될 때 start transaction; 을 해준다 ( 즉 트랜잭션을 시작!)
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시라도 문제가 있다면 rollback
    // IOException과 같은 Checked Exception은 롤백이 일어나지 않는다.
    // 스프링에서는 트랜잭션을 사용하면 영속성 컨텍스트가 생겨나고, 트랜잭션이 종료되면 영속성 컨텍스트가 종료된다.

    // 영속성 컨텍스트의 특수 능력 4가지
    // 1 . 변경감지 (Dirty Check)
    // 영속성 컨텍스트 안에서 불러와진 Entity는 명시적으로 save하지 않더라도, 변경을 감지해 자동으로 저장된다.
    // 2. 쓰기 지연
    // DB의 INSERT / UPDATE / DELETE SQL을 바로 날리는 것이 아니라, 트랜잭션이 commit될 때 모아서 한 번만 날린다.
    // 3. 1차 캐싱
    // ID를 기준으로 Entity를 기억한다!
    // 이렇게 캐싱된 객체는 완전이 동일하다!
    // 4. 지연로딩(Lazy Loading)
    // @OneToMany의 fetch 옵션
    // 지연로딩을 사용하게 되면, 연결되어 있는 객체를 꼭 필요한 순간에만 가져온다


    // 1. 연관관계를 사용하면 무엇이 좋을까??
    // 1) 각자의 역할에 집중하게 된다! (= 응집성)
    // 2) 새로운 개발자가 코드를 읽을 때 이해하기 쉬워진다.
    // 3) 테스트 코드 작성이 쉬워진다.

    // 2. 연관관계를 사용하는 것이 항상 좋을까?
    // 지나치게 사용하면, 성능상의 문제가 생길 수도 있고
    // 도메인 간의 복잡한 연결로 인해 시스템을 파악하기 어려워질 수 있다.
    // 또한 너무 얽혀 있으면, A를 수정했을 때 B C D 까지 영향이 퍼지게 된다.
    // 비즈니스 요구사항, 기술적인 요구사항, 도메인 아키텍처 등 여러 부분을 고민해서 연관관계 사용을 선택해야 한다.
    @Transactional
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(),request.getAge()));
        throw new IllegalArgumentException();
    }

    @Transactional(readOnly = true) // SELECT 쿼리만 사용한다면, readOnly 옵션을 쓸 수 있다.
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request){
        // SELECT * FROM user WHERE id =?;
        // Optional<User>
        User user = userRepository.findById(request.getId()) // findById를 사용하면 id를 기준으로 1개의 데이터를 가져온다.
                .orElseThrow(IllegalArgumentException::new); // Optional의 orElseThrow를 사용해 User가 없다면 예외를 던진다.

        user.updateName(request.getName()); // 객체를 업데이트 해주고, save 메소드를 호출
//        userRepository.save(user);  // 그러면 자동으로 UPDATE SQL이 날라가게 된다. => 영속성 컨텍스트의 특수 능력중 변경감지를 통해 작성하지 않아도 변경을 감지해 자동으로 저장됨
    }
    // save : 주어지는 객체를 저장하거나 업데이트 시켜준다.
    // findAll : 주어지는 객체가 매핑된 테이블의 모든 데이터를 가져온다.
    // findById : id를 기준으로 특정한 1개의 데이터를 가져온다.

    // Spring Data JPA

    // 복잡한 JPA 코드를 스프링과 함께 쉽게 사용할 수 있도록 도와주는 라이브러리

    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }
}
