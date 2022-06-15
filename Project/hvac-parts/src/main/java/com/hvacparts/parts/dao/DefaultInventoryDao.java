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
import com.hvacparts.parts.entity.Inventory;
import com.hvacparts.parts.entity.Part;

@Repository
public class DefaultInventoryDao implements InventoryDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbc;

  public void createInventory(Part part) {
    String sql =
        "INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES (:part_num, 1, 0)";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("part_num", part.getPart_num());

    jdbc.update(sql, params);

  }

  public List<Inventory> showAllInventory() {
    String sql =
        "SELECT i.part_num_fk, i.location_num_fk, p.part_name, i.stock, l.location_name "
            + "FROM inventory i INNER JOIN parts p ON p.part_num = i.part_num_fk "
            + "INNER JOIN locations l ON l.location_num = i.location_num_fk";
    return jdbc.query(sql, new RowMapper<Inventory>() {

      public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Inventory.builder().part_num_fk(rs.getString("part_num_fk"))
            .location_num_fk(rs.getInt("location_num_fk")).stock(rs.getInt("stock"))
            .location_name(rs.getString("location_name"))
            .part_name(rs.getString("part_name")).build();
      }
    });
  }

  public List<Inventory> getSpecificPartInventory(String part_num) {
    String sql =
        "SELECT i.part_num_fk, i.location_num_fk, p.part_name, i.stock, l.location_name "
        + "FROM inventory i INNER JOIN parts p ON p.part_num = i.part_num_fk "
        + "INNER JOIN locations l ON l.location_num = i.location_num_fk WHERE i.part_num_fk =:part_num";
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("part_num", part_num);

    return jdbc.query(sql, params, new RowMapper<Inventory>() {

      public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Inventory.builder().part_num_fk(rs.getString("part_num_fk"))
            .part_name(rs.getString("part_name")).location_num_fk(rs.getInt("location_num_fk"))
            .location_name(rs.getString("location_name")).stock(rs.getInt("stock")).build();
      }

    });
  }

  @SuppressWarnings("null")
  public void updateStock(String part_num, Integer location_num, Integer stock) {
    if(location_num != null || location_num != 0 || part_num != null || !part_num.isEmpty() || stock != null){
      String sql = "UPDATE inventory SET stock=:stock WHERE part_num_fk=:part_num AND location_num_fk =:location_num";
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("part_num", part_num);
      params.addValue("location_num", location_num);
      params.addValue("stock", stock);
      
      jdbc.update(sql, params);
      System.out.println("Part updated");
      
      if(stock == 0) {
        deleteInventory(part_num, location_num, stock);
      }
    }

  }

  public List<Inventory> showAllInventorySpecificLocation(Integer location_num) {
    String sql =
        "SELECT i.part_num_fk, i.location_num_fk, p.part_name, i.stock, l.location_name "
        + "FROM inventory i INNER JOIN parts p ON p.part_num = i.part_num_fk"
        + " INNER JOIN locations l ON l.location_num = i.location_num_fk WHERE i.location_num_fk =:location_num";
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("location_num", location_num);

    return jdbc.query(sql, params, new RowMapper<Inventory>() {

      public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Inventory.builder().part_num_fk(rs.getString("part_num_fk"))
            .part_name(rs.getString("part_name")).location_num_fk(rs.getInt("location_num_fk"))
            .location_name(rs.getString("location_name")).stock(rs.getInt("stock")).build();
      }

    });
  }
  
  private void deleteInventory(String part_num, Integer location_num, Integer stock) {
    if(stock == 0 && location_num != 1) {
      String sql = "DELETE FROM inventory WHERE part_num_fk=:part_num AND location_num_fk=:location_num";
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("part_num", part_num);
      params.addValue("location_num", location_num);
      
      jdbc.update(sql, params);
    }
    
  }

  @SuppressWarnings("null")
  public Optional<Inventory> getSpecificPartInventoryByLocation(String part_num, Integer location_num) {
    if(part_num != null || !part_num.isEmpty() || location_num != null) {
      String sql = "SELECT i.part_num_fk, p.part_name, i.location_num_fk, l.location_name, i.stock "
          + "FROM inventory i "
          + "INNER JOIN parts p ON p.part_num = i.part_num_fk "
          + "INNER JOIN locations l ON l.location_num = i.location_num_fk "
          + "WHERE i.location_num_fk =:location_num AND i.part_num_fk =:part_num";
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("location_num", location_num);
      params.put("part_num", part_num);

      List<Inventory> inventory = jdbc.query(sql, params, new RowMapper<Inventory>() {

        public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
          return Inventory.builder().part_num_fk(rs.getString("part_num_fk")).part_name(rs.getString("part_name"))
              .location_num_fk(rs.getInt("location_num_fk")).location_name(rs.getString("location_name"))
              .stock(rs.getInt("stock")).build();
        }
      });
      
      if(!inventory.isEmpty()) {
        return Optional.of(inventory.get(0));
      }
    }
    return Optional.empty();
    
  }

  public Optional<Inventory> createNewInventoryPartsAtLocation(String part_num, Integer location_num,
      Integer stock) {
    String sql =
        "INSERT INTO inventory(part_num_fk, location_num_fk, stock) VALUES (:part_num, :location_num, :stock)";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("part_num", part_num);
    params.addValue("location_num", location_num);
    params.addValue("stock", stock);

    int rows = jdbc.update(sql, params);
    if(rows > 0) {
      return getSpecificPartInventoryByLocation(part_num, location_num);
    }
    
    return Optional.empty();
    

  }
   
}
