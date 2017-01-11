package Property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by oracle on 12/29/16.
 */
public class InputProperties {

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
            input = getClass().getResourceAsStream("..//config.properties");
            prop.load(input);

            databaseName = prop.getProperty(dbName.concat("_database"));
            hostName = prop.getProperty(dbName.concat("_host"));
            portNumber = prop.getProperty(dbName.concat("_port"));
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
