/**
 * Created by oracle on 12/27/16.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

class InputParameters
{
    private String userName;
    private String databaseName;
    private String sqlFileName;

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    private String userPass;

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public String getSqlFileName() {
        return sqlFileName;
    }

    InputParameters(String[] args){
        userName = args[0];
        databaseName = args[1];
        sqlFileName = args[2];

        System.out.println(userName);
        System.out.println(databaseName);
        System.out.println(sqlFileName);
    }
}
class InputProperties{

    private String databaseName;
    private String hostName;
    private String portNumber;

    public String getDatabaseName() {
        return databaseName;
    }

    public String getHostName() {
        return hostName;
    }

    public String getPortNumber() {
        return portNumber;
    }


    InputProperties(String dbName){

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/main/config.properties");

            // load a properties file
            prop.load(input);

            databaseName = prop.getProperty(dbName.concat("_database"));
            hostName = prop.getProperty(dbName.concat("_host"));
            portNumber = prop.getProperty(dbName.concat("_port"));

            System.out.println(databaseName);
            System.out.println(hostName);
            System.out.println(portNumber);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
class OracleJDBC {

    public Connection getConnection() {

        Connection connection = null;

        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return connection;

        }

        System.out.println("Oracle JDBC Driver Registered!");


        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "itap_owner",
                    "itap_owner");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return connection;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        return connection;
    }

}

class DatabaseConnectAndSave{
    DatabaseConnectAndSave(InputProperties iproper, InputParameters iparams){

    }
    public void SaveData(){
        Connection orclConn = (new OracleJDBC()).getConnection();
    }

}
public class SqlBlob {
    public static void main(String[] args) {

        InputParameters iparams = new InputParameters(args);
        InputProperties iproper = new InputProperties(iparams.getDatabaseName());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        iparams.setUserPass(scanner.next());

        DatabaseConnectAndSave databaseConn = new DatabaseConnectAndSave(iproper, iparams);

        databaseConn.SaveData();

    }
}
