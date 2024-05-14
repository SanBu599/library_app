package com.group.libraryapp.controller.date;

import com.group.libraryapp.dto.date.DateResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;

@RestController
public class DateController {

    @GetMapping("api/v1/day-of-the-week")
    public DateResponse date(@RequestParam String date){

        return new DateResponse(date);
    }
}