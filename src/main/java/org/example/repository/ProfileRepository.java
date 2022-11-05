package org.example.repository;


import org.example.dto.Profile;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public class ProfileRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Profile searchByPhone(String phone) {


        String sql = "select count(*) from profile where phone='" + phone + "' ;";

        int integer = jdbcTemplate.queryForObject(sql, Integer.class);
        if (integer != 0) {
            sql="select * from profile where phone='" + phone + "' ;";
            Profile profile= jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Profile.class));
            return profile;
        }
        return null;

 }

    public int addProfileToDb(Profile profile) {


        String sql = "insert into profile(name,surname,phone,password,created_date,status,role) " +
                "values (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, profile.getName(), profile.getSurname(), profile.getPhone(),
                profile.getPassword(), Timestamp.valueOf(profile.getCreated_date()), profile.getStatus().name(), profile.getRole().name());

    }


    public List<Profile> get_profile_list_fromDb() {

        String sql = "select * from profile ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class));

    }
}
