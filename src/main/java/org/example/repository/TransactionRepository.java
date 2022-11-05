package org.example.repository;

import org.example.dto.Transaction;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int reFill(Transaction transaction) {


        String sql = "insert into transaction(card_number,amount,type) values (?,?,?)";
        return jdbcTemplate.update(sql, transaction.getCard_number(), transaction.getAmount(), transaction.getTransactionType());


    }

    public List<Transaction> get_transaction_list_from_db() {


        String sql = "select * from transaction ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class));


    }


    public int addTransaction(Transaction transaction) {
        String sql = "insert into transaction(card_number,amount,type,created_date,terminal_code) values (?,?,?,?,?);";
        return jdbcTemplate.update(sql, transaction.getCard_number(), transaction.getAmount(), transaction.getTransactionType().name(),
                Timestamp.valueOf(transaction.getCreated_date()), transaction.getTerminal_code());


    }

    public List<Transaction> get_profile_transaction_list_fromDb(String phone) {
        String sql = String.format("select * from transaction  where card_number in (select number from card where phone='%s' ) order by created_date DESC;",phone);

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class));

    }

    public int make_payment(Transaction transaction, String phone) {

        String sql = "call transfer_mony(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, transaction.getCard_number(), "444",
                transaction.getAmount(), transaction.getTransactionType().name(),
                Timestamp.valueOf(transaction.getCreated_date()), transaction.getTerminal_code(), phone);

    }


}
