package isad.ehu;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

public class Main {

  public static void main(String[] args) throws IOException {

    Properties properties;
    InputStream in = null;
    try {
      in = Main.class.getResourceAsStream("/setup.properties");
      properties = new Properties();
      properties.load(in);
    } finally {
      in.close();
    }

    Connection conn = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", properties);
      conn.setCatalog(properties.getProperty("dbname"));

      //Declare a SELECT statement
      String selectStmt = "SELECT * FROM autobusa";

      //Create statement
      Statement stmt = conn.createStatement();

      //Execute select (query) operation
      ResultSet rs = stmt.executeQuery(selectStmt);

      while (rs.next()) {

        int edukiera = rs.getInt("edukiera");
        String matrikula = rs.getString("matrikula");
        Date noizkoa = rs.getDate("noizkoa");

        System.out.println("Matrikula:" + matrikula);
        System.out.println("Edukiera:" + edukiera);
        System.out.println("Noizkoa:" + noizkoa);

        System.out.println();
      }

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
      e.printStackTrace();
    }
  }
}