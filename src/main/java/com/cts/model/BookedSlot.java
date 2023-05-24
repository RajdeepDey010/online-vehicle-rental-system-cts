package com.cts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookedSlot {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date endTime;
    Date d=new Date();  
    private int year=d.getYear();

    private static final int COMPANY_ESTABLISHMENT_YEAR = 2011;

    public void setEndTime(Date endTime) {
        // Validate that endTime is greater than startTime
        if (endTime.before(startTime)) {
            System.out.println("endTime must be greater than startTime");
        } else {
            this.endTime = endTime;
        }
    }

    public void setStartTime(Date startTime) {
        // Validate that MM is less than 12
        if (startTime.getMonth() >= 12) {
            System.out.println("MM must be less than 12");
        } else {
            this.startTime = startTime;
        }
    }

    public void setYear(int year) {
        // Validate that year is more than the year of establishment of the company
        if (year <= COMPANY_ESTABLISHMENT_YEAR) {
            System.out.println("Year must be more than " + COMPANY_ESTABLISHMENT_YEAR);
        } else {
            this.year = year;
        }
    }
}