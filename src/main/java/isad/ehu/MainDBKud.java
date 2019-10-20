package isad.ehu;

import isad.ehu.kud.DBKudeatzaile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

public class MainDBKud {

  public static void main(String[] args) throws IOException {


    DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();

    try {

      //Declare a SELECT statement
      String selectStmt = "SELECT * FROM autobusa";

      ResultSet rs = dbkud.execSQL(selectStmt);


      while (rs.next()) {

        int edukiera = rs.getInt("edukiera");
        String matrikula = rs.getString("matrikula");
        Date noizkoa = rs.getDate("noizko");

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
    }
  }
}