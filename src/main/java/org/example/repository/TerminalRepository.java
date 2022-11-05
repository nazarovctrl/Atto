package org.example.repository;

import org.example.dto.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.List;

@Repository
public class TerminalRepository {
    private final JdbcTemplate jdbcTemplate;

    public TerminalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addTerminalToDb(Terminal terminal) {
        String sql = "insert into terminal(code,address,created_date,status) " +
                "values (?,?,?,?)";
        return jdbcTemplate.update(sql, terminal.getCode(), terminal.getAddress(), Timestamp.valueOf(terminal.getCreated_date()), terminal.getStatus().name());
    }


    public Terminal getTerminal(String code) {
        String sql = "select count(*) from terminal where code='" + code + "' ;";

        int integer = jdbcTemplate.queryForObject(sql, Integer.class);

        if (integer == 0) {
            return null;
        }
        sql = "select * from terminal where code='" + code + "' ;";
        ;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Terminal.class));
    }

    public List<Terminal> get_terminal_list_fromDb() {
        String sql = "select * from terminal ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Terminal.class));
    }

    public int updateTerminal_address_fromDB(String code, String address) {
        String sql = "update terminal set address=? where code=? ;";
        return jdbcTemplate.update(sql, address, code);
    }

    public int changeTerminal_status_fromDB(String code, String status) {
        String sql = "update terminal set status=? where code=? ;";
        return jdbcTemplate.update(sql, status, code);
    }

    public int deleteTerminal_fromDb(String code) {
        String sql = "delete from terminal where code=? ;";
        return jdbcTemplate.update(sql, code);
    }
}
