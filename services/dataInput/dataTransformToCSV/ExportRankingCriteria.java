//package services.dataTransformToCSV;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.sql.*;

public class ExportRankingCriteria {

    public static void execute(String inputFile) {
        Properties configFile = new java.util.Properties();
        try {
            FileInputStream stream = new FileInputStream(new File("config/config.cfg"));
            configFile.load(stream);
        } catch (Exception eta) {
            eta.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(inputFile);
            writer.append("RankingCriteria,RankingCriteriaType");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" +
                            configFile.getProperty("hostname") +
                            ":" +
                            configFile.getProperty("port") +
                            "/" +
                            configFile.getProperty("dbname") +
                            "?autoReconnect=true&useSSL=false",
                    configFile.getProperty("username"), configFile.getProperty("password"));

            String query = "SELECT * FROM tbl_ranking_criteria";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // For the time being, skipping additional slot column
                int rankingCriteriaId = resultSet.getInt("ranking_criteria_id");
                String rankingCriteriaType = resultSet.getString("ranking_criteria_type");

                writer.append("\n" + Integer.toString(rankingCriteriaId) + "," + rankingCriteriaType);
            }
            writer.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


