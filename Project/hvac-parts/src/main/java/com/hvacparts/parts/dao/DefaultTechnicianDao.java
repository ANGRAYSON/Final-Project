package com.hvacparts.parts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hvacparts.parts.entity.ActiveStatus;
import com.hvacparts.parts.entity.Technician;

@Repository
public class DefaultTechnicianDao implements TechnicianDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbc;

  public List<Technician> showAllTechs() {
    String sql = "SELECT * FROM technicians";
    return jdbc.query(sql, new RowMapper<Technician>() {

      public Technician mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Technician.builder().employee_num(rs.getInt("employee_num"))
            .first_name(rs.getString("first_name")).last_name(rs.getString("last_name"))
            .status(ActiveStatus.valueOf(rs.getString("active_status"))).build();
      }
    });
  }

  public Optional<Technician> showSpecificTech(Integer employee_num) {
    String sql = "SELECT * FROM technicians WHERE employee_num=:employee_num";
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("employee_num", employee_num);

    List<Technician> techs = jdbc.query(sql, params, new RowMapper<Technician>() {

      public Technician mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Technician.builder().employee_num(rs.getInt("employee_num"))
            .first_name(rs.getString("first_name")).last_name(rs.getString("last_name"))
            .status(ActiveStatus.valueOf(rs.getString("active_status"))).build();
      }

    });

    if (!techs.isEmpty()) {
      return Optional.of(techs.get(0));
    }
    return Optional.empty();
  }

  public Optional<Technician> save(Technician tech) {
    if (!tech.isValid(tech)) {
      return Optional.empty();
    }

    Integer employee_num = tech.getEmployee_num();

    String sql;
    Optional<Technician> existing = showSpecificTech(employee_num);

    if (existing.isPresent()) {
      sql =
          "UPDATE technicians SET first_name =:first_name, last_name =:last_name, active_status=:active_status WHERE employee_num=:employee_num";
    } else {
      sql =
          "INSERT INTO technicians (employee_num, first_name, last_name, active_status) VALUES (:employee_num, :first_name, :last_name, :active_status)";
    }
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("employee_num", tech.getEmployee_num());
    params.addValue("first_name", tech.getFirst_name());
    params.addValue("last_name", tech.getLast_name());
    params.addValue("active_status", tech.getStatus().toString());

    int rows = jdbc.update(sql, params);
    if (rows > 0) {
      Optional<Technician> newTech = showSpecificTech(tech.getEmployee_num());
      if (newTech.isPresent()) {
        return newTech;
      }
    }
    return Optional.empty();
  }


}
