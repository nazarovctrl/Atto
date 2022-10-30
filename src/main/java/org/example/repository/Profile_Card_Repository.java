package org.example.repository;

import org.example.db.DataBase;
import org.example.dto.Profile_Card;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


@Repository
public class Profile_Card_Repository {
    public int addCard_to_user(Profile_Card profile_card) {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into profile_card(profile_phone,card_number,added_date) values(?,?,?) ;");
            statement.setString(1, profile_card.getProfile_phone());
            statement.setString(2, profile_card.getCard_number());
            statement.setTimestamp(3, Timestamp.valueOf(profile_card.getAdded_date()));

            return statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        return 0;
    }


    public int delete_profile_card(String phone, String number) {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from profile_card where profile_phone=? and card_number=?;");
            statement.setString(1, phone);
            statement.setString(2, number);

            return statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        return 0;

    }
}
