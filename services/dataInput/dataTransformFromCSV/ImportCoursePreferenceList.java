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
public class ImportCoursePreferenceList {

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
        if (attributes.length > 1){
          String[] courses = attributes[0].split("\\$");

          for(int i=1;i<attributes.length;i++){
          insertQuery = "Insert into tbl_course_preference values (?,?,?,?)";
          preparedStatement = connection.prepareStatement(insertQuery);
          preparedStatement.setString(1, attributes[i]);
          preparedStatement.setString(2, courses[0]);
          preparedStatement.setString(3, courses[1]);
          preparedStatement.setInt(4, i);
          try{
          preparedStatement.executeUpdate();
        }
        catch(Exception e)
        {
          System.out.println("Error: Duplicate entries in "+ attributes[0] +" In courses preference list");
        }
      }
        }
        line = br.readLine();
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
