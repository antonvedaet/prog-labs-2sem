package ifmo.utils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.logging.Logger;

import ifmo.data.Color;
import ifmo.data.Coordinates;
import ifmo.data.Location;
import ifmo.data.Person;
import ifmo.data.User;

public class DatabaseHandler {

    Connection conn = null;
    String user = "anton";
    String password = "A9152!208-";
    // String schema = "?currentSchema=anton_schema";
    String url = "jdbc:postgresql://localhost:5432/labs";
    private Logger logger = Logger.getLogger("logger");

    public Connection connect(){
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
        if(!person.getSaved()){
            try{
                PreparedStatement statement = conn.prepareStatement(
                  "INSERT INTO " +   "person " +
                  "(name, coordinates_x, coordinates_y, creation_date, height, birthday, eye_color, hair_color, location_x, location_y, location_z, location_name, creator) " +
                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
              statement.setString(1, person.getName());
              statement.setInt(2, person.getCoordinates().getX());
              statement.setDouble(3, person.getCoordinates().getY());
              LocalDate creationDate = person.getCreationDate();
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
              statement.setString(13, person.getCreator());

              person.setSaved();
              int rowsAffected = statement.executeUpdate();
              if (rowsAffected == 0) {
                  throw new SQLException("Inserting person failed, no rows affected.");
              }
              try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                  if (generatedKeys.next()) {
                      person.setId(generatedKeys.getInt(1));
                  }
              }
          } catch (SQLException e) {
              e.printStackTrace();
              }
        }
    }
    public LinkedList<Person> getAllPersons(Connection conn) throws SQLException {
        LinkedList<Person> persons = new LinkedList<Person>();
            final String SELECT_ALL_PERSONS = "SELECT * FROM person";
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_PERSONS); {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int coordinates_x = resultSet.getInt("coordinates_x");
                long coordinates_y = resultSet.getLong("coordinates_y");
                LocalDate creationDate = resultSet.getTimestamp("creation_date").toLocalDateTime().toLocalDate();
                float height = resultSet.getFloat("height");
                LocalDateTime birthday = resultSet.getTimestamp("birthday").toLocalDateTime();
                Color eyeColor = Color.valueOf(resultSet.getString("eye_color"));
                Color hairColor = null;
                if (resultSet.getString("hair_color") != null) {
                    hairColor = Color.valueOf(resultSet.getString("hair_color"));
                }
                int location_x = resultSet.getInt("location_x");
                double location_y = resultSet.getDouble("location_y");
                double location_z = resultSet.getDouble("location_z");
                String location_name = resultSet.getString("location_name");
                String creator = resultSet.getString("creator");

                Coordinates coordinates = new Coordinates(coordinates_x, coordinates_y);
                Location location = new Location(location_x, location_y, location_z, location_name);
                Person person = new Person(id, name, coordinates, creationDate, height, birthday, eyeColor, hairColor, location, creator);
                person.setSaved();
                persons.add(person);
            }
        }

        return persons;
    }

    public void register(String login, String pwd, Connection conn){
        try{
            PreparedStatement statement = conn.prepareStatement(
            "INSERT INTO users" +
            "(login, password)" +
            "VALUES (?, ?)");
            
            Hasher hasher = new Hasher("SHA-256");
        statement.setString(1, login);
        statement.setString(2, hasher.encode(pwd));

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) {
            throw new SQLException("Inserting person failed, no rows affected.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        }
    }
}




