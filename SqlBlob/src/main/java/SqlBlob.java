/**
 * Created by oracle on 12/27/16.
 */
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

class InputParameters{
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

            input = getClass().getResourceAsStream("config.properties");
            prop.load(input);

            databaseName = prop.getProperty(dbName.concat("_database"));
            hostName = prop.getProperty(dbName.concat("_host"));
            portNumber = prop.getProperty(dbName.concat("_port"));

            //System.out.println(databaseName);
            //System.out.println(hostName);
            //System.out.println(portNumber);

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

class DatabaseConnectAndSave{
    private InputProperties l_iproper;
    private InputParameters l_iparams;

    DatabaseConnectAndSave(InputProperties iproper, InputParameters iparams){
        l_iproper = iproper;
        l_iparams = iparams;
    }
    public void SaveData() throws SQLException, IOException {

        DateFormat df = new SimpleDateFormat("yyyyddmmHHmmss");

        Date today = Calendar.getInstance().getTime();
// Using DateFormat format method we can create a string
// representation of a date with the defined format.
        String folderName = df.format(today);
        File theDir = new File(folderName);
// if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + folderName);
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
                System.out.println("DIR created");
            }
        }


        Connection orclConn = (new OracleJDBC()).getConnection(l_iproper, l_iparams);
        //String stSql = "select system_trade_id as file_name, message_body from inbound_trades where id = 58145834";
        //String stSql = "select system_trade_id as file_name, message_body from inbound_ged_notification where system_trade_id like '%643783'";
        String stSql = "select system_trade_id as file_name, message_body from inbound_ged_notification";

        PreparedStatement pstmt = orclConn.prepareStatement(stSql);
        ResultSet rs = pstmt.executeQuery();

        String stColumnType = rs.getMetaData().getColumnTypeName(rs.getMetaData().getColumnCount());

        while( rs.next() ) {
            File outputTextFile1 = new File(folderName +"/"+rs.getString("file_name"));
            FileOutputStream outputFileOutputStream = new FileOutputStream(outputTextFile1);

            Clob clob;
            Blob blob;
            int chunkSize = 100000;
            long lobLength;
            char[] textBuffer;
            byte[] byteBuffer;
            int position;
            int charsRead = 0;
            int totCharsRead = 0;
            int totCharsWritten = 0;
            InputStream in;


                if (stColumnType.equals("CLOB")){
                    OutputStreamWriter outputOutputStreamWriter = new OutputStreamWriter(outputFileOutputStream);
                    BufferedWriter outputBufferedWriter = new BufferedWriter(outputOutputStreamWriter);
                    clob = rs.getClob("message_body");
                    lobLength = clob.length();
                    textBuffer = new char[chunkSize];
                for (position = 1; position <= lobLength; position += chunkSize) {
                    charsRead = clob.getCharacterStream().read(textBuffer, position-1, chunkSize);
                    outputBufferedWriter.write(textBuffer, totCharsRead, charsRead);
                    totCharsRead += charsRead;
                    totCharsWritten += charsRead;

                    System.out.println("charsRead = " + charsRead);
                    System.out.println("totCharsRead = " + totCharsRead);
                    System.out.println("totCharsWritten = " + totCharsWritten);
                }
                    outputBufferedWriter.close();
                    outputOutputStreamWriter.close();
                }else{
                    blob = rs.getBlob("message_body");
                    lobLength = blob.length();
                    byteBuffer = new byte[chunkSize];
                    in = blob.getBinaryStream();
                    int len = 0;

                    while ((len = in.read(byteBuffer)) != -1) {
                        outputFileOutputStream.write(byteBuffer, 0, len);
                    }

                }

            outputFileOutputStream.close();
        }
        orclConn.close();
    }

}
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
