package org.example.repository;

import org.example.db.DataBase;
import org.example.dto.Terminal;
import org.example.util.TerminalUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TerminalRepository {

    public int addTerminalToDb(Terminal terminal) {


        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into terminal(code,address,created_date,status) " +
                            "values (?,?,?,?)");
            statement.setString(1, terminal.getCode());
            statement.setString(2, terminal.getAddress());
            statement.setTimestamp(3, Timestamp.valueOf(terminal.getCreated_date()));
            statement.setString(4, terminal.getStatus().name());

            int resultSet = statement.executeUpdate();
            return resultSet;


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


    public Terminal getTerminal(String code) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from terminal where code=?");
            statement.setString(1, code);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                return TerminalUtil.get_terminal(resultSet);
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

    public List<Terminal> get_terminal_list_fromDb() {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from terminal ");


            ResultSet resultSet = statement.executeQuery();

            List<Terminal> terminals = new LinkedList<>();
            while (resultSet.next()) {
                terminals.add(TerminalUtil.get_terminal(resultSet));
            }

            return terminals;

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

    public int updateTerminal_address_fromDB(String code, String address) {


        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update terminal set address=? where code=? ;");
            statement.setString(1, address);
            statement.setString(2, code);

            int resultSet = statement.executeUpdate();

            return resultSet;


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

    public int changeTerminal_status_fromDB(String code, String status) {


        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update terminal set status=? where code=? ;");
            statement.setString(1, status);
            statement.setString(2, code);

            int resultSet = statement.executeUpdate();

            return resultSet;


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

    public int deleteTerminal_fromDb(String code) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from terminal where code=? ;");

            statement.setString(1, code);

            int resultSet = statement.executeUpdate();

            return resultSet;


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
