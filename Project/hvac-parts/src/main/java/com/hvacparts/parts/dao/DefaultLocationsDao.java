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
import com.hvacparts.parts.entity.Location;

@Repository
public class DefaultLocationsDao implements LocationsDao{

  @Autowired
  private NamedParameterJdbcTemplate jdbc;
  
  public List<Location> showAllLocations() {
    String sql = "SELECT * FROM locations";
    return jdbc.query(sql, new RowMapper<Location>() {

      public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Location.builder()
            .location_num(rs.getInt("location_num"))
            .name(rs.getString("location_name"))
            .build();
      }
    });
  }

  public Optional<Location> getSpecificLocation(Integer location_num) {
    String sql = "SELECT * FROM locations WHERE location_num=:location_num";
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("location_num", location_num);
    
    List<Location> locations = jdbc.query(sql, params, new RowMapper<Location>() {

      public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Location.builder()
            .location_num(rs.getInt("location_num"))
            .name(rs.getString("location_name"))
            .build();
      }
      
    });
    
    if(!locations.isEmpty()) {
      return Optional.of(locations.get(0));
    }
    return Optional.empty();
  }


  public Optional<Location> save(Location location) {
    Integer location_num = location.getLocation_num();
    
    if((location_num == null) || (location_num == 0)) {
      return Optional.empty();
    }
    
    String sql;
    Optional<Location> existing = getSpecificLocation(location_num);
    
    if(existing.isPresent()) {
      sql = "UPDATE locations SET location_name =:location_name WHERE location_num=:location_num";
    }else {
      sql = "INSERT INTO locations (location_num, location_name) VALUES (:location_num, :location_name)";
    }
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("location_num", location.getLocation_num());
    params.addValue("location_name", location.getName());
    
    int rows = jdbc.update(sql, params);
    if(rows > 0) {
      Optional<Location> newLocation = getSpecificLocation(location.getLocation_num());
      if(newLocation.isPresent()) {
        return newLocation;
      }
    }
    return Optional.empty();
  }

  @SuppressWarnings("null")
  public void delete(Location location) {
    String sql;
    Integer location_num = location.getLocation_num();
    if(checkIfLocationInInventory(location_num)) {
      deleteFromInventory(location_num);
    }
    sql = "DELETE FROM locations WHERE location_num=:location_num";
    
    
    if((location_num != null) || (location_num != 0)) {
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("location_num", location_num);
      jdbc.update(sql, params);
      System.out.println("Location deleted");
    }
  }
  
  private boolean checkIfLocationInInventory(Integer location_num) {
    String sql = "SELECT * FROM inventory WHERE location_num_fk = :location_num";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("location_num", location_num);
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
  
  private void deleteFromInventory(Integer location_num) {
    String sql = "DELETE FROM inventory WHERE location_num_fk = :location_num";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("location_num", location_num);
    jdbc.update(sql, params);
    System.out.println("Part deleted from inventory");
  }

}
