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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.hvacparts.parts.entity.Inventory;
import com.hvacparts.parts.entity.PartsOut;

@Repository
public class DefaultPartsOutDao implements PartsOutDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbc;

  @Autowired
  private DefaultInventoryDao inventory;

  public Optional<PartsOut> createPartOutOrder(PartsOut partsOut) {
    if (!partsOut.isValid(partsOut)) {
      return Optional.empty();
    }

    String sql = "INSERT INTO parts_out (employee_num_fk, part_num_fk, location_num_fk, amount_out)"
        + "VALUES (:employee_num_fk, :part_num_fk, :location_num_fk, :amount_out)";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("employee_num_fk", partsOut.getEmployee_num_fk());
    params.addValue("part_num_fk", partsOut.getPart_num_fk());
    params.addValue("location_num_fk", partsOut.getLocation_num_fk());
    params.addValue("amount_out", partsOut.getAmount_out());
    KeyHolder keyHolder = new GeneratedKeyHolder();
    
    Optional<Inventory> stock = inventory.getSpecificPartInventoryByLocation(
        partsOut.getPart_num_fk(), partsOut.getLocation_num_fk());
    if(stock.isEmpty()) {
      return Optional.empty();
    } 
    
    jdbc.update(sql, params, keyHolder);

    int order_num = keyHolder.getKey().intValue();

    Optional<PartsOut> order = getSpecificOrder(order_num);
    if (order.isPresent()) {
      Integer out = partsOut.getAmount_out();
      Integer finalOut = stock.get().getStock() - out;
      inventory.updateStock(partsOut.getPart_num_fk(), partsOut.getLocation_num_fk(), finalOut);
      return order;
      
    }
    return Optional.empty();

  }

  private Optional<PartsOut> getSpecificOrder(int order_num) {
    String sql = "SELECT * FROM parts_out WHERE order_num=:order_num";
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("order_num", order_num);

    List<PartsOut> orders = jdbc.query(sql, params, new RowMapper<PartsOut>() {

      public PartsOut mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PartsOut.builder().employee_num_fk(rs.getInt("employee_num_fk"))
            .part_num_fk(rs.getString("part_num_fk")).location_num_fk(rs.getInt("location_num_fk"))
            .amount_out(rs.getInt("amount_out")).build();
      }

    });

    if (!orders.isEmpty()) {
      return Optional.of(orders.get(0));
    }
    return Optional.empty();
  }

  public boolean checkForEmployee(Integer employee_num_fk) {
    String sql = "SELECT * FROM technicians WHERE employee_num = :employee_num";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("employee_num", employee_num_fk);
    List<Integer> rs = jdbc.query(sql, params, new RowMapper<Integer>() {

      public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        int rows = 1;
        return rows;
      }

    });
    if (rs.isEmpty()) {
      return false;
    } else {
      return true;
    }
  }

}
