package org.example.db;

import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.example.repository.CardRepository;
import org.example.repository.ProfileRepository;
import org.example.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class DataBase {

    private final ProfileRepository profileRepository;
    private final CardRepository cardRepository;

    public DataBase(CardRepository cardRepository, ProfileRepository profileRepository) {
        this.cardRepository = cardRepository;
        this.profileRepository = profileRepository;
    }


    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Atto", "java_db_user", "123");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        }
        return null;

    }



    public  void createTable() {
        createProfileTable();
        createCardTable();
        createTerminalTable();
        createTransactionTable();
        createProfile_CardTable();
        createPayment_Procedure();
        adminInit();
        addCompanyCard();

    }


    private  void createProfileTable() {

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists profile( id serial primary key, " +
                    "name varchar(20) ," +
                    " surname varchar(20) ," +
                    "  phone varchar(12) unique not null, " +
                    "password varchar, " +
                    "created_date timestamp default now(), " +
                    "status varchar, role varchar ) ;";
            statement.executeUpdate(sql);


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


    }

    private  void createCardTable() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists card( id serial primary key, " +
                    "number varchar(20)  unique not null," +
                    " exp_date date  ," +
                    "  balance bigint, " +
                    "status varchar, " +
                    "phone varchar(12) , " +
                    "created_date timestamp default now()) ;";
            statement.executeUpdate(sql);


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

    }

    private  void createTerminalTable() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists terminal( id serial primary key, " +
                    "code varchar unique not null ," +
                    "address varchar ," +
                    "status varchar, " +
                    "created_date timestamp default now()) ;";
            statement.executeUpdate(sql);


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

    }


    private  void createTransactionTable() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists transaction( id serial primary key, " +
                    "card_number varchar not null, " +
                    "amount bigint , " +
                    "terminal_code varchar , " +
                    "type varchar , " +
                    "created_date timestamp default now()," +
                    " foreign key(card_number) references  card(number), " +
                    " foreign key(terminal_code) references  terminal(code)) ;";
            statement.executeUpdate(sql);


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

    }

    private  void createProfile_CardTable() {

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists profile_card( id serial primary key, " +
                    "profile_phone varchar(12) ," +
                    "card_number varchar ," +
                    "added_date timestamp default now() ) ";
            statement.executeUpdate(sql);


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


    }


    private  void createPayment_Procedure() {

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "CREATE OR replace procedure transfer_mony (from_card varchar, to_card varchar, amount dec,type varchar,created_date timestamp,terminal_code varchar,u_phone varchar)\n" +
                    "language plpgsql\n" +
                    "AS $$\n" +
                    "declare\n" +
                    "current_balance  dec;\n" +
                    "   begin\n" +
                    "  \n" +
                    "  update card set balance = balance - amount where number = from_card;\n" +
                    "  update card set balance = balance + amount where number = to_card;\n" +
                    "  \n" +
                    "  select balance into current_balance from card where number = from_card;\n" +
                    "  \n" +
                    "  \n" +
                    "  if current_balance < amount and from_card in(select number from card where phone=u_phone)\n" +
                    "  then \n" +
                    "    rollback;\n" +
                    "    return;\n" +
                    "  end if;  \n" +
                    "  \n" +
                    "  commit;\n" +
                    "  insert into transaction(card_number,amount,type,created_date,terminal_code) values (from_card,amount,type,created_date,terminal_code);\n" +
                    "end; $$";
            statement.executeUpdate(sql);


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


    }


    public  void adminInit() {

        Profile profile = new Profile("Azimjon", "Nazarov", "123", MD5.md5("123"),
                LocalDateTime.now(), GeneralStatus.ACTIVE, ProfileRole.ADMIN);


        Profile profile1 = profileRepository.searchByPhone(profile.getPhone());
        if (profile1 != null) {
            return;
        }

        profileRepository.addProfileToDb(profile);


    }

    public void addCompanyCard() {

        Card card = new Card("444", LocalDate.parse("2025-08-08"), 0L, GeneralStatus.ACTIVE, null, LocalDateTime.now());
        Card card1 = cardRepository.searchCardByNumber(card.getNumber());

        if (card1 != null) {
            return;
        }
        cardRepository.addCardToDb(card);

    }

}
