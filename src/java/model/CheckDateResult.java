package model;

public class CheckDateResult {

    private int day;
    private int month;
    private int year;
    private boolean valid;
    private String message;

    public CheckDateResult(
            int day,
            int month,
            int year,
            boolean valid,
            String message) {

        this.day = day;
        this.month = month;
        this.year = year;
        this.valid = valid;
        this.message = message;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}