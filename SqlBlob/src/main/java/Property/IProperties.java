package Property;

/**
 * Created by oracle on 1/2/17.
 */
public interface IProperties {
    //IProperties(String[] args);
    String getDatabaseName();
    String getHostName();
    String getPortNumber();
    String getUserPass();
    void setUserPass(String userPass);
    String getUserName();
    String getSqlFileName();
}
