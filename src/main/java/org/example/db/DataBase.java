package org.example.db;

import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.example.repository.CardRepository;
import org.example.repository.ProfileRepository;
import org.example.util.MD5;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class DataBase {

    private final ProfileRepository profileRepository;
    private final CardRepository cardRepository;

    private final JdbcTemplate jdbcTemplate;

    public DataBase(ProfileRepository profileRepository, CardRepository cardRepository, JdbcTemplate jdbcTemplate) {
        this.profileRepository = profileRepository;
        this.cardRepository = cardRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        createProfileTable();
        createCardTable();
        createTerminalTable();
        createTransactionTable();
        createPayment_Procedure();
        adminInit();
        addCompanyCard();

    }


    private void createProfileTable() {

        String sql = "create table if not exists profile( id serial primary key, " +
                "name varchar(20) ," +
                " surname varchar(20) ," +
                "  phone varchar(12) unique not null, " +
                "password varchar, " +
                "created_date timestamp default now(), " +
                "status varchar, role varchar ) ;";

        jdbcTemplate.update(sql);


    }

    private void createCardTable() {
        String sql = "create table if not exists card( id serial primary key, " +
                "number varchar(20)  unique not null," +
                " exp_date date  ," +
                "  balance bigint, " +
                "status varchar, " +
                "phone varchar(12) , " +
                "created_date timestamp default now()," +
                "added_date timestamp default now()) ;";
        jdbcTemplate.update(sql);

    }

    private void createTerminalTable() {


        String sql = "create table if not exists terminal( id serial primary key, " +
                "code varchar unique not null ," +
                "address varchar ," +
                "status varchar, " +
                "created_date timestamp default now()) ;";
        jdbcTemplate.update(sql);


    }


    private void createTransactionTable() {

        String sql = "create table if not exists transaction( id serial primary key, " +
                "card_number varchar not null, " +
                "amount bigint , " +
                "terminal_code varchar , " +
                "type varchar , " +
                "created_date timestamp default now()," +
                " foreign key(card_number) references  card(number), " +
                " foreign key(terminal_code) references  terminal(code)) ;";
        jdbcTemplate.update(sql);
    }


    private void createPayment_Procedure() {


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

        jdbcTemplate.update(sql);


    }


    public void adminInit() {

        Profile profile = new Profile(0l,"Azimjon", "Nazarov", "123", MD5.md5("123"),
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
