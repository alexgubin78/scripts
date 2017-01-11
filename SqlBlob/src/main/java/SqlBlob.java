/**
 * Created by oracle on 12/27/16.
 */
import Database.DatabaseConnectAndSave;
import Property.Properties;
import Property.IProperties;

import java.io.*;
import java.sql.*;
import java.util.Scanner;


public class SqlBlob {
    public static void main(String[] args) throws IOException, SQLException {


        IProperties properties = new Properties(args);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        properties.setUserPass(scanner.next());

        DatabaseConnectAndSave databaseConn = new DatabaseConnectAndSave(properties);

        databaseConn.SaveData();

    }
}
