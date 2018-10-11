package services.dataInput.dataTransformFromCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.sql.*;
public class ImportExchangeUnstablePairs {

  public static void execute(String inputFile) {
    Path pathToFile = Paths.get(inputFile);
    Properties configFile = new java.util.Properties();
    try {
      FileInputStream stream = new FileInputStream(new File("config/config.cfg"));
      configFile.load(stream);
    } catch (Exception eta) {
      eta.printStackTrace();
    }
    try {
      BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
      String line = br.readLine(); // Skipping first row as it have column name
      line = br.readLine();
      String insertQuery;
      PreparedStatement preparedStatement;
      Class.forName("com.mysql.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://" +
      configFile.getProperty("hostname") +
      ":" +
      configFile.getProperty("port") +
      "/" +
      configFile.getProperty("dbname") +
      "?autoReconnect=true&useSSL=false",
      configFile.getProperty("username"), configFile.getProperty("password"));

      while (line != null) {
        String[] attributes = line.split(",");

        String[] coursesOne = attributes[1].split("\\$");
        String[] coursesTwo = attributes[3].split("\\$");
        insertQuery = "Insert into tbl_exchange_unstable_pairs values (?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, attributes[0]);
        preparedStatement.setString(2, coursesOne[0]);
        preparedStatement.setString(3, coursesOne[1]);
        preparedStatement.setString(4, attributes[2]);
        preparedStatement.setString(5, coursesTwo[0]);
        preparedStatement.setString(6, coursesTwo[1]);
        preparedStatement.executeUpdate();
        line = br.readLine();
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
