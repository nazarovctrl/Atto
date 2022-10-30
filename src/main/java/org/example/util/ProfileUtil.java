package org.example.util;

import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProfileUtil {


    public static Profile get_profile(ResultSet resultSet) {

        try {
            int id = resultSet.getInt("id");

            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String p_phone = resultSet.getString("phone");
            String password = resultSet.getString("password");
            String status = resultSet.getString("status");
            String role = resultSet.getString("role");
            Timestamp c_d = resultSet.getTimestamp("created_date");

            return new Profile(id, name, surname, p_phone, password, c_d.toLocalDateTime(),
                    GeneralStatus.valueOf(status), ProfileRole.valueOf(role));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
