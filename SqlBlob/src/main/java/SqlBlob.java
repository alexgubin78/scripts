/**
 * Created by oracle on 12/27/16.
 */
import java.io.*;
import java.sql.*;
import java.util.Scanner;


public class SqlBlob {
    public static void main(String[] args) throws IOException, SQLException {

        InputParameters iparams = new InputParameters(args);
        InputProperties iproper = new InputProperties(iparams.getDatabaseName());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        iparams.setUserPass(scanner.next());

        DatabaseConnectAndSave databaseConn = new DatabaseConnectAndSave(iproper, iparams);

        databaseConn.SaveData();

    }
}
