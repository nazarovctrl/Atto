package org.example.util;

import org.example.dto.Transaction;
import org.example.enums.TransactionType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TransactionUtil {

    public static Transaction get_terminal(ResultSet resultSet) {

        try {
            int id = resultSet.getInt("id");

            String card_number = resultSet.getString("card_number");
            Long amount = resultSet.getLong("amount");
            String terminal_code = resultSet.getString("terminal_code");
            String type = resultSet.getString("type");
            Timestamp c_d = resultSet.getTimestamp("created_date");

            return new Transaction(id, card_number, amount, terminal_code, TransactionType.valueOf(type), c_d.toLocalDateTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
