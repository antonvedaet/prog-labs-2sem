package ifmo.utils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Logger;

import ifmo.data.Person;

public class DatabaseHandler {

    Connection conn = null;
    String user = "anton";
    String password = "A9152!208-";
    String schema = "anton_schema";
    String url = "jdbc:postgresql://localhost:5432/anton?currentSchema="+schema;
    private Logger logger = Logger.getLogger("logger");

    public Connection Connect(){
        try {
            conn = DriverManager.getConnection(url, user, password);
            logger.info("Connected to the PostgreSQL server successfully.");
            return conn;
          } catch (SQLException e) {
            System.out.println(e.getMessage());
          }
          return conn;
    }

    public void savePerson(Person person, Connection conn){
        try{
          PreparedStatement statement = conn.prepareStatement(
            "INSERT INTO " + schema + ".person " +
            "(name, coordinates_x, coordinates_y, creation_date, height, birthday, eye_color, hair_color, location_x, location_y, location_z, location_name) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, person.getName());
        statement.setInt(2, person.getCoordinates().getX());
        statement.setDouble(3, person.getCoordinates().getY());
        LocalDate creationDate = LocalDate.now();
        LocalTime creationTime = LocalTime.of(12, 0, 0);
        LocalDateTime dateTime = LocalDateTime.of(creationDate, creationTime);
        statement.setTimestamp(4, Timestamp.valueOf(dateTime));
        statement.setFloat(5, person.getHeight());
        statement.setTimestamp(6, Timestamp.valueOf(person.getBirthday()));
        statement.setObject(7, person.getEyeColor().name(), Types.OTHER);
        statement.setObject(8, person.getHairColor() == null ? null : person.getHairColor().name(),Types.OTHER);
        statement.setInt(9, person.getLocation().getX());
        statement.setDouble(10, person.getLocation().getY());
        statement.setDouble(11, person.getLocation().getZ());
        statement.setString(12, person.getLocation().getName());
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) {
            throw new SQLException("Inserting person failed, no rows affected.");
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                person.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Inserting person failed, no ID obtained.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}




