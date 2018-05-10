//package services.dataTransformToCSV;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.sql.*;

public class ExportDepartment {

    public static void execute(String inputFile) {
        Properties configFile = new java.util.Properties();
        try {
            FileInputStream stream = new FileInputStream(new File("../../../config/config.cfg"));
            configFile.load(stream);
        } catch (Exception eta) {
            eta.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(inputFile);
            writer.append("Department Code,Department Name");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" +
                            configFile.getProperty("hostname") +
                            ":" +
                            configFile.getProperty("port") +
                            "/" +
                            configFile.getProperty("dbname") +
                            "?autoReconnect=true&useSSL=false",
                    configFile.getProperty("username"), configFile.getProperty("password"));

            String query = "SELECT * FROM tbl_department";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // For the time being, skipping additional slot column
                String deptCode = resultSet.getString("dept_code");
                String deptName = resultSet.getString("dept_name");

                writer.append("\n" + deptCode + "," + deptName);
            }
            writer.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


