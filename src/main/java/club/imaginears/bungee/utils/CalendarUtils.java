package club.imaginears.bungee.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarUtils {

    public static String dateFormat = "MM-dd-yyyy hh:mm";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    public static String ConvertMilliSecondsToFormattedDate(String milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return simpleDateFormat.format(calendar.getTime());
    }
}
