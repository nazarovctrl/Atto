package org.example.repository;

import org.example.dto.Card;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public class CardRepository {
    private final JdbcTemplate jdbcTemplate;

    public CardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int addCardToDb(Card card) {
        String sql = "insert into card(number,exp_date,created_date,status,balance) " + "values (?,?,?,?,?)";

        return jdbcTemplate.update(sql, card.getNumber(), card.getExp_date(), Timestamp.valueOf(card.getCreated_date()), card.getStatus().name(), card.getBalance());

    }


    public List<Card> get_card_list() {
        String sql = "select * from card ;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Card.class));
    }


    public Card searchCardByNumber(String number) {

        String sql = "select count(*) from card where number= '" + number + "'";

        int integer = jdbcTemplate.queryForObject(sql, Integer.class);
        if (integer == 0) {
            return null;
        }

        sql = "select * from card where number= '" + number + "'";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Card.class));

    }

    public int updateCardFromDb(Card card) {

        String sql = "update card set exp_date=? where number=? ;";
        return jdbcTemplate.update(sql, card.getExp_date(), card.getNumber());

    }

    public int changeStatus(String number, String status) {

        String sql = "update card set status=? where number=? ;";
        return jdbcTemplate.update(sql, status, number);

    }


    public int deleteCardFromDb(String number) {

        String sql = "delete  from card  where number=? ;";
        return jdbcTemplate.update(sql, number);

    }

    public int addPhone_to_Card(Card card) {

        String sql = "update card set phone=?, added_date=? where number=? ;";
        return jdbcTemplate.update(sql, card.getPhone(), Timestamp.valueOf(card.getAdded_date()), card.getNumber());
    }

    public List<Card> get_profile_card_list_fromDb(String phone) {

        String sql = "select * from card where phone='" + phone + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Card.class));

    }


    public int delete_phone_from_card(String number) {
        String sql = "update   card set phone=null  where number=? ;";
        return jdbcTemplate.update(sql, number);
    }

    public int refillCard(Long amount, String number) {
        String sql = " update card set balance=balance+? where number=? ;";
        return jdbcTemplate.update(sql, amount, number);
    }
}
