//package services.dataTransformToCSV;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.sql.*;

public class ExportCourses {

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
            writer.append("Course Number,Department,TotalCapacity,OutsideDepartmentCapacity,RankingCriteria,Credits,Slots");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" +
                            configFile.getProperty("hostname") +
                            ":" +
                            configFile.getProperty("port") +
                            "/" +
                            configFile.getProperty("dbname") +
                            "?autoReconnect=true&useSSL=false",
                    configFile.getProperty("username"), configFile.getProperty("password"));

            String query = "SELECT * FROM tbl_course_list";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // For the time being, skipping additional slot column
                String courseId = resultSet.getString("course_id");
                String deptCode = resultSet.getString("dept_code");
                int maxStudents = resultSet.getInt("max_students");
                int maxOutsideDept = resultSet.getInt("max_outside_dept");
                int rankingCriteriaId = resultSet.getInt("ranking_criteria_id");
                int credits = resultSet.getInt("credits");
                String slotId = resultSet.getString("slot_id");

                writer.append("\n" + courseId + "," + deptCode + "," + Integer.toString(maxStudents) + "," +
                        Integer.toString(maxOutsideDept) + "," + Integer.toString(rankingCriteriaId) + "," +
                        Integer.toString(credits) + "," + slotId);
            }
            writer.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


