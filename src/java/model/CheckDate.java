package model;

public class CheckDate {

    public boolean isValidDate(int day, int month, int year) {

        if (year <= 0) {
            return false;
        }

        if (month < 1 || month > 12) {
            return false;
        }

        if (day < 1) {
            return false;
        }

        int maxDay;

        switch (month) {

            case 4:
            case 6:
            case 9:
            case 11:
                maxDay = 30;
                break;

            case 2:
                if (isLeapYear(year)) {
                    maxDay = 29;
                } else {
                    maxDay = 28;
                }
                break;

            default:
                maxDay = 31;
        }

        return day <= maxDay;
    }

    private boolean isLeapYear(int year) {
        return (year % 400 == 0)
                || (year % 4 == 0 && year % 100 != 0);
    }
}