package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by oracle on 12/29/16.
 */
public class Constants {
    private Constants(){}

    public static final String FOLDER_NAME = (new SimpleDateFormat("yyyy_MM_dd_HHmmss")).format(Calendar.getInstance().getTime());


}
