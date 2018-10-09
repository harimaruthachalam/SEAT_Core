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
public class ImportStudentPreferenceList {

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
                // We will have 7 or 8 attributes
                // Course Number,Department,TotalCapacity,OutsideDepartmentCapacity,RankingCriteria,Credits,Slots[,AdditionalSlots]
                // RollNo,CourseNo,ColorGroup,Type,SortOrder
                // roll, couse, color, pref_numb, type

                    insertQuery = "Insert into tbl_student_preference_list values (?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, attributes[0]);
                    preparedStatement.setString(2, attributes[1]);
                    if (attributes[2].equals("NULL")) {
                      preparedStatement.setNull(3, Types.INTEGER);

} else {
   preparedStatement.setInt(3, Integer.valueOf(attributes[2]));
}
                    preparedStatement.setString(5, attributes[3]);
if (attributes[4].equals("NULL")) {
  preparedStatement.setNull(4, Types.INTEGER);

} else {
preparedStatement.setInt(4, Integer.valueOf(attributes[4]));
}
try{
preparedStatement.executeUpdate();
}
catch(Exception e)
{
System.out.println("Error: Duplicate entries in "+ attributes[0] +" and "+ attributes[1] +" In student preference list");
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
