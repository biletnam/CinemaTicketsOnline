package com.netcracker.cinema.dao.impl;

import com.netcracker.cinema.model.Dummy;
import com.netcracker.cinema.dao.DummyDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class DummyDaoImpl implements DummyDao {
    private static final Logger logger = Logger.getLogger(DummyDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DummyDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Dummy> findAll() throws DataAccessException {
        return jdbcTemplate.query(findAllSQL, (rs, rowNum) -> {
            Dummy dummy = new Dummy();
            dummy.setId(rs.getLong("id"));
            dummy.setName(rs.getString("name"));
            return dummy;
        });
    }

    @Override
    public Dummy findById(long id) throws DataAccessException {
        return jdbcTemplate.queryForObject(findByIdSQL, new Object[]{id}, (rs, rowNum) -> {
            Dummy dummy = new Dummy();
            dummy.setId(rs.getLong("id"));
            dummy.setName(rs.getString("name"));
            return dummy;
        });
    }

    @Override
    public void save(Dummy dummy) throws DataAccessException {
        namedParameterJdbcTemplate.update(saveSQL, getNamedParameter(dummy));
    }

    private SqlParameterSource getNamedParameter(Dummy dummy) {
        return new BeanPropertySqlParameterSource(dummy);
    }

    private static final String saveSQL = "INSERT ALL " +
            "INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) " +
            "VALUES (:id, NULL, 1, NULL, NULL) " +
            "INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) " +
            "VALUES (1, :id, :name, NULL) " +
            "SELECT * FROM dual";

    private static final String findByIdSQL = "SELECT OBJECTS.OBJECT_ID id, ATT_NAME.VALUE name " +
            "FROM OBJECTS " +
            "INNER JOIN ATTRIBUTES ATT_NAME " +
            "ON ATT_NAME.OBJECT_ID = OBJECTS.OBJECT_ID " +
            "WHERE OBJECTS.OBJECT_ID = ? " +
            "AND ATT_NAME.ATTR_ID = 1";

    private static final String findAllSQL = "SELECT OBJECTS.OBJECT_ID id, ATT_NAME.VALUE name " +
            "FROM OBJECTS " +
            "INNER JOIN ATTRIBUTES ATT_NAME " +
            "ON ATT_NAME.OBJECT_ID = OBJECTS.OBJECT_ID " +
            "WHERE ATT_NAME.ATTR_ID = 1";
}