import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by oracle on 12/29/16.
 */
public class OracleJDBC {
    public Connection getConnection(InputProperties iproper, InputParameters iparams) {

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
                    "jdbc:oracle:thin:@"
                            + iproper.getHostName()
                            + ":"
                            + iproper.getPortNumber()
                            + ":"
                            + iproper.getDatabaseName(), iparams.getUserName(),
                    iparams.getUserPass());

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
