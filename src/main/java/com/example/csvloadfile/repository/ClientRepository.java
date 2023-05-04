package com.example.csvloadfile.repository;

import com.example.csvloadfile.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClientRepository {

    private static final String SQL_CREATE_CLIENT = "insert into clients (name, age, group_id, phone, date) " +
            "values(:name, :age, :group_id, :phone, :date)";
    private static final String SQL_GET_CLIENT_BY_AGE = "select * from clients where age < :age";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public int save(Client client) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", client.getName());
        params.addValue("age", client.getAge());
        params.addValue("group_id", client.getGroupId());
        params.addValue("phone", client.getPhone());
        params.addValue("date", client.getDate());
        return jdbcTemplate.update(SQL_CREATE_CLIENT, params);
    }

    public List<Client> getClientsByAge(Integer age) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("age", age);
        return jdbcTemplate.query(SQL_GET_CLIENT_BY_AGE, params, (resultSet, rowNum) -> {
            Client client = new Client();
            client.setId(resultSet.getLong("id"));
            client.setName(resultSet.getString("name"));
            client.setAge(resultSet.getInt("age"));
            client.setGroupId(resultSet.getInt("group_id"));
            client.setPhone(resultSet.getString("phone"));
            client.setDate(resultSet.getDate("date").toLocalDate());
            return client;
        });
    }
}
