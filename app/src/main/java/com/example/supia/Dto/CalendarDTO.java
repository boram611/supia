package com.example.supia.Dto;

public class CalendarDTO {
int calendarNo;
String calendarStartDate;
String calendarFinishDate;
String calendarDeliveryDate;
String calendarBirthDate;
String userId;


    public CalendarDTO(int calendarNo, String calendarStartDate, String calendarFinishDate, String calendarDeliveryDate, String calendarBirthDate, String userId) {
        this.calendarNo = calendarNo;
        this.calendarStartDate = calendarStartDate;
        this.calendarFinishDate = calendarFinishDate;
        this.calendarDeliveryDate = calendarDeliveryDate;
        this.calendarBirthDate = calendarBirthDate;
        this.userId = userId;
    }

    public CalendarDTO(String userId, String calendarStartDate, String calendarFinishDate) {
        this.userId = userId;
        this.calendarStartDate = calendarStartDate;
        this.calendarFinishDate = calendarFinishDate;
    }

    public CalendarDTO(String calendarDeliveryDate, String userId) {
        this.calendarDeliveryDate = calendarDeliveryDate;
        this.userId = userId;
    }

    public int getCalendarNo() {
        return calendarNo;
    }

    public void setCalendarNo(int calendarNo) {
        this.calendarNo = calendarNo;
    }

    public String getCalendarStartDate() {
        return calendarStartDate;
    }

    public void setCalendarStartDate(String calendarStartDate) {
        this.calendarStartDate = calendarStartDate;
    }

    public String getCalendarFinishDate() {
        return calendarFinishDate;
    }

    public void setCalendarFinishDate(String calendarFinishDate) {
        this.calendarFinishDate = calendarFinishDate;
    }

    public String getCalendarDeliveryDate() {
        return calendarDeliveryDate;
    }

    public void setCalendarDeliveryDate(String calendarDeliveryDate) {
        this.calendarDeliveryDate = calendarDeliveryDate;
    }

    public String getCalendarBirthDate() {
        return calendarBirthDate;
    }

    public void setCalendarBirthDate(String calendarBirthDate) {
        this.calendarBirthDate = calendarBirthDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}