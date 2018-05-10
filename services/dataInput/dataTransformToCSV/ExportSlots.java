//package services.dataTransformToCSV;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.sql.*;

public class ExportSlots {

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
            writer.append("SlotName,Timings(comma seperated),,,");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" +
                            configFile.getProperty("hostname") +
                            ":" +
                            configFile.getProperty("port") +
                            "/" +
                            configFile.getProperty("dbname") +
                            "?autoReconnect=true&useSSL=false",
                    configFile.getProperty("username"), configFile.getProperty("password"));

            String query = "SELECT * FROM tbl_slot";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // For the time being, skipping additional slot column
                String slotId = resultSet.getString("slot_id");
                String lecture1 = resultSet.getString("lecture_1");
                String lecture2 = resultSet.getString("lecture_2");
                String lecture3 = resultSet.getString("lecture_3");
                String lecture4 = resultSet.getString("lecture_4");

                writer.append("\n" + slotId + "," + lecture1 + "," + lecture2 + "," + lecture3 + "," + lecture4);
            }
            writer.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


