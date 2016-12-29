/**
 * Created by oracle on 12/29/16.
 */
public class InputParameters {
    private String userName;
    private String databaseName;
    private String sqlFileName;
    private String userPass;

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }


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
