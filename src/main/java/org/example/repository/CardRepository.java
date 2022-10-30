package org.example.repository;

import org.example.db.DataBase;
import org.example.dto.Card;
import org.example.util.CardUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


@Repository
public class CardRepository {


    public int addCardToDb(Card card) {


        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into card(number,exp_date,created_date,status,balance) " +
                            "values (?,?,?,?,?)");
            statement.setString(1, card.getNumber());
            statement.setDate(2, Date.valueOf(card.getExp_date()));
            statement.setTimestamp(3, Timestamp.valueOf(card.getCreated_date()));
            statement.setString(4, card.getStatus().name());
            statement.setLong(5, card.getBalance());


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


    public List<Card> get_card_list() {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from card");

            ResultSet resultSet = statement.executeQuery();
            List<Card> cardList = new LinkedList<>();
            while (resultSet.next()) {

                cardList.add(CardUtil.get_card(resultSet));
            }

            return cardList;


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

        return null;

    }


    public Card searchCardByNumber(String number) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from card where number=?");
            statement.setString(1, number);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                return CardUtil.get_card(resultSet);

            }


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

        return null;
    }

    public int updateCardFromDb(Card card) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update card set exp_date=? where number=? ;");
            statement.setDate(1, Date.valueOf(card.getExp_date()));
            statement.setString(2, card.getNumber());

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

    public int changeStatus(String number, String status) {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update card set status=? where number=? ;");
            statement.setString(1, status);
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


    public int deleteCardFromDb(String number) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete  from card  where number=? ;");
            statement.setString(1, number);


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

    public int addPhone_to_Card(String number, String phone) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update card set phone=? where number=? ;");
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

    public List<Card> get_profile_card_list_fromDb(String phone) {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from card where phone=?");
            statement.setString(1, phone);

            ResultSet resultSet = statement.executeQuery();
            List<Card> cardList = new LinkedList<>();
            while (resultSet.next()) {

                cardList.add(CardUtil.get_card(resultSet));
            }

            return cardList;


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

        return null;
    }

    public int change_profile_card_status_fromDB(String phone, String number, String status) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update card set status=? where number=? and phone=? ;");
            statement.setString(1, status);
            statement.setString(2, number);
            statement.setString(3, phone);

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

    public int delete_phone_from_card(String number) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update   card set phone=null  where number=? ;");
            statement.setString(1, number);


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
