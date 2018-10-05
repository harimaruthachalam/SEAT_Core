package services.dataInput.dataTransformFromCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.sql.*;

public class ImportCourses {

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
                    insertQuery = "Insert into tbl_course_list (course_id, dept_code, max_students, max_outside_dept, " +
                            "ranking_criteria_id, credits, slot_id, additional_slot) values (?,?,?,?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, attributes[0]);
                    preparedStatement.setString(2, attributes[1]);
                    preparedStatement.setInt(3, Integer.valueOf(attributes[2]));
                    preparedStatement.setInt(4, Integer.valueOf(attributes[3]));
                    preparedStatement.setInt(5, Integer.valueOf(attributes[4]));
                    preparedStatement.setInt(6, Integer.valueOf(attributes[5]));
                    preparedStatement.setString(7, attributes[6]);


                    String onwards="";


                    for(int i=7;i<attributes.length;i++)
                        onwards=onwards+attributes[i]+",";

                    preparedStatement.setString(8, onwards);
                    try{
                    preparedStatement.executeUpdate();
                  } catch(Exception e)
                  {
                    System.out.println("Error: Duplicate entry "+ attributes[0] +" In courses");
                  }

                line = br.readLine();
              }
            }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
}
