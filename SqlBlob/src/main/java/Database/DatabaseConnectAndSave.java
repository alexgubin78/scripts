package Database;

import Property.IProperties;

import java.io.*;
import java.sql.*;

/**
 * Created by oracle on 12/29/16.
 */
public class DatabaseConnectAndSave {
    private IProperties l_iproper;

    private void CheckDirectoryExists(){
        File theDir = new File(Constants.FOLDER_NAME);
        if (!theDir.exists()) {

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
    }
    public DatabaseConnectAndSave(IProperties properties){
        l_iproper = properties;
    }
    public void SaveData() throws SQLException, IOException {

        CheckDirectoryExists();

        Connection orclConn = (new OracleJDBC()).getConnection(l_iproper);
        //String stSql = "select system_trade_id as file_name, message_body from inbound_trades where id = 58145834";
        //String stSql = "select system_trade_id as file_name, message_body from inbound_ged_notification where system_trade_id like '%643783'";
        String stSql = "select system_trade_id as file_name, message_body from inbound_ged_notification";

        PreparedStatement pstmt = orclConn.prepareStatement(stSql);
        ResultSet rs = pstmt.executeQuery();

        String stColumnType = rs.getMetaData().getColumnTypeName(rs.getMetaData().getColumnCount());

        while( rs.next() ) {
            File outputTextFile1 = new File(Constants.FOLDER_NAME +"/"+rs.getString("file_name"));
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
