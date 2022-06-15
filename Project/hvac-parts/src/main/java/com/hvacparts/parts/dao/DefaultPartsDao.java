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
import com.hvacparts.parts.entity.Part;
import com.hvacparts.parts.entity.Type;

@Repository
public class DefaultPartsDao implements PartsDao{
  
  @Autowired
  private NamedParameterJdbcTemplate jdbc;
  

  public List<Part> showAllParts() {
    String sql = "SELECT part_num, part_name, type FROM parts";
    return jdbc.query(sql, new RowMapper<Part>() {

      public Part mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Part.builder()
            .part_num(rs.getString("part_num"))
            .name(rs.getString("part_name"))
            .type(Type.valueOf(rs.getString("type")))
            .build();
      }
    });
  }

  public Optional<Part> getSpecificPart(String part_num) {
    String sql = "SELECT * FROM parts WHERE part_num=:part_num";
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("part_num", part_num);
    
    List<Part> parts = jdbc.query(sql, params, new RowMapper<Part>() {

      public Part mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Part.builder()
            .part_num(rs.getString("part_num"))
            .name(rs.getString("part_name"))
            .type(Type.valueOf(rs.getString("type")))
            .build();
      }
      
    });
    
    if(!parts.isEmpty()) {
      return Optional.of(parts.get(0));
    }
    return Optional.empty();
  }

  @SuppressWarnings("null")
  public void delete(Part part) {
//    String sql = "DELETE i, p FROM inventory i JOIN parts p ON p.part_num = i.part_num_fk WHERE i.part_num_fk =:part_num";
    String part_num = part.getPart_num();
    if(checkIfPartInInventory(part_num)) {
      deleteFromInventory(part_num);
    }
    
    String sql = "DELETE FROM parts WHERE part_num=:part_num";
    if((part_num != null) || (!part_num.isEmpty())) {
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("part_num", part_num);
      jdbc.update(sql, params);
      System.out.println("Part delted");
    } 
  }
  
  private void deleteFromInventory(String part_num) {
    String sql = "DELETE FROM inventory WHERE part_num_fk=:part_num";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("part_num", part_num);
    jdbc.update(sql, params);
    System.out.println("Part deleted from inventory");
  }

  private boolean checkIfPartInInventory(String part_num) {
    String sql = "SELECT * FROM inventory WHERE part_num_fk = :part_num";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("part_num", part_num);
    List<Integer> rs = jdbc.query(sql, params, new RowMapper<Integer>() {

      public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        int rows = 1;
        return rows;
      }
      
    });
    if(rs.isEmpty()) {
      return false;
    }else {
      return true;
    }
  }
  
  


  public Optional<Part> save(Part part) {
    String part_num = part.getPart_num();
    
    if((part_num == null) || (part_num.isEmpty())) {
      return Optional.empty();
    }
    
    String sql;
    Optional<Part> existing = getSpecificPart(part_num);
    
    if(existing.isPresent()) {
      sql = "UPDATE parts SET part_name =:part_name, type =:type WHERE part_num=:part_num";
    }else {
      sql = "INSERT INTO parts (part_num, part_name, type) VALUES (:part_num, :part_name, :type)";
    }
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("part_num", part.getPart_num());
    params.addValue("part_name", part.getName());
    params.addValue("type", part.getType().toString());
    
    int rows = jdbc.update(sql, params);
    if(rows > 0) {
      Optional<Part> newPart = getSpecificPart(part.getPart_num());
      if(newPart.isPresent()) {
        return newPart;
      }
    }
    return Optional.empty();
  }

}
