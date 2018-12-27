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
public class ImportCommandLine {

    public static void execute() {
        Properties configFile = new java.util.Properties();
        try {
            FileInputStream stream = new FileInputStream(new File("config/config.cfg"));
            configFile.load(stream);
        } catch (Exception eta) {
            eta.printStackTrace();
        }
        try {
              Path pathToFile = Paths.get("files/cliInput");
                  BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
                  String line = br.readLine();
                  String commandLine = "";
                  while (line != null) {
                    commandLine = commandLine + line;
                      commandLine = commandLine + "\n";
                    line = br.readLine();
                  }
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
                    insertQuery = "INSERT INTO tbl_command_line values(?)";
                    preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, commandLine);
                    preparedStatement.executeUpdate();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
