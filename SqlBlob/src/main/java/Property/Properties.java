package Property;

import Property.IProperties;
import Property.InputParameters;
import Property.InputProperties;

/**
 * Created by oracle on 1/2/17.
 */
public class Properties implements IProperties {
    private InputParameters iparams;
    private InputProperties iproper;

    public Properties(String... args) {
        iparams = new InputParameters(args);
        iproper = new InputProperties(iparams.getDatabaseName());
    }

    public String getHostName() {
        return iproper.getHostName();
    }

    public String getPortNumber() {
        return iproper.getPortNumber();
    }

    public String getUserPass() {
        return iparams.getUserPass();
    }

    public void setUserPass(String userPass) {
        iparams.setUserPass(userPass);
    }

    public String getDatabaseName() {
        return iproper.getDatabaseName();
    }

    public String getUserName() {
        return iparams.getUserName();
    }

    public String getSqlFileName() {
        return iparams.getSqlFileName();
    }
}
