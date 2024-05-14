package com.group.libraryapp.dto.date;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateResponse {

    private String dayOfTheWeek;

    public DateResponse(String dayOfTheWeek) {
        this.dayOfTheWeek = LocalDate.parse(dayOfTheWeek).getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
