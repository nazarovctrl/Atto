package org.example.util;

import org.example.dto.Card;
import org.example.enums.GeneralStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;

public class CardUtil {

    public static Card getFromScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number: ");
        String number = scanner.next();

        LocalDate localDate;

        while (true) {
            try {
                System.out.print("Enter exp_date (yyyy-MM-dd) : ");
                String exp_date = scanner.next();
                localDate = LocalDate.parse(exp_date);
                break;
            } catch (RuntimeException e) {
                System.out.println("Mazgi exp_date ni togri kirit");
            }
        }

        Card card = new Card();
        card.setNumber(number);
        card.setExp_date(localDate);

        return card;
    }


    public static Card get_card(ResultSet resultSet) {

        try {
            Integer id = resultSet.getInt("id");

            String number = resultSet.getString("number");
            java.sql.Date sqlDate = resultSet.getDate("exp_date");
            Long balance = resultSet.getLong("balance");
            String status = resultSet.getString("status");
            String phone = resultSet.getString("phone");
            Timestamp c_d = resultSet.getTimestamp("created_date");

            LocalDate localDate = sqlDate.toLocalDate();

            return new Card(id, number, localDate, balance, GeneralStatus.valueOf(status), phone, c_d.toLocalDateTime());

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
