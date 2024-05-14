package com.group.libraryapp.controller.number;

import com.group.libraryapp.dto.number.NumberRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Number {

    @PostMapping("/number")
    public int numbers(@RequestBody NumberRequest request){
        int answer = 0;
        for (int a : request.getNumbers()){
            answer += a;
        }
        return answer;
    }
}
