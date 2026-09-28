package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CheckDate;
import model.CheckDateResult;

@WebServlet("/check-date")
public class CheckDateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String dayParam = request.getParameter("day");
        String monthParam = request.getParameter("month");
        String yearParam = request.getParameter("year");

        // Check missing or empty parameters
        if (isEmpty(dayParam)
                || isEmpty(monthParam)
                || isEmpty(yearParam)) {

            sendBadRequest(response, "Missing or empty parameter");
            return;
        }

        int day;
        int month;
        int year;

        try {

            day = Integer.parseInt(dayParam.trim());
            month = Integer.parseInt(monthParam.trim());
            year = Integer.parseInt(yearParam.trim());

        } catch (NumberFormatException e) {

            sendBadRequest(
                response,
                "Day, month and year must be integers"
            );

        return;
        }

        CheckDate checker = new CheckDate();

        boolean valid =
            checker.isValidDate(day, month, year);

        String message;

        if (valid) {
            message = "Valid date";
        } else {
            message = "Invalid date";
        }

        CheckDateResult result =
                new CheckDateResult(
                    day,
                    month,
                    year,
                    valid,
                    message
            );

        String json =
            "{"
            + "\"day\":" + result.getDay() + ","
            + "\"month\":" + result.getMonth() + ","
            + "\"year\":" + result.getYear() + ","
            + "\"valid\":" + result.isValid() + ","
            + "\"message\":\"" + result.getMessage() + "\""
            + "}";

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(json);
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private void sendBadRequest(
            HttpServletResponse response,
            String message)
        throws IOException {

        response.setStatus(
            HttpServletResponse.SC_BAD_REQUEST
        );

        String json =
            "{"
            + "\"error\":\"Invalid input\","
            + "\"message\":\"" + message + "\""
            + "}";

        response.getWriter().write(json);
    }
}