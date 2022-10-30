package org.example.util;

import org.example.dto.Terminal;
import org.example.enums.GeneralStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TerminalUtil {
    public static Terminal get_terminal(ResultSet resultSet) {
        try {
            Integer id = resultSet.getInt("id");
            String code1 = resultSet.getString("code");
            String address = resultSet.getString("address");
            String status = resultSet.getString("status");
            Timestamp c_d = resultSet.getTimestamp("created_date");
            return new Terminal(id, code1, address, GeneralStatus.valueOf(status), c_d.toLocalDateTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
