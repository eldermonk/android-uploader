package com.nightscout.android.dexcom;

import java.util.Date;
import java.util.TimeZone;

public class Utils {

    public static Date receiverTimeToDate(long delta) {
        int currentTZOffset = TimeZone.getDefault().getRawOffset();
        long epochMS = 1230768000000L;  // Jan 01, 2009 00:00 in UTC
        long milliseconds = epochMS - currentTZOffset;
        long timeAdd = milliseconds + (1000L * delta);
        TimeZone tz = TimeZone.getDefault();
        if (tz.inDaylightTime(new Date())) timeAdd = timeAdd - 3600000L;
        return new Date(timeAdd);
    }

    public static String getTimeString(long timeDeltaMS) {
        long minutes = (timeDeltaMS / 1000) / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long weeks = days / 7;
        minutes= minutes - hours * 60;
        hours = hours - days * 24;
        days= days - weeks * 7;

        String timeAgoString="";
        if (weeks > 0) {
            timeAgoString+=weeks+" weeks ";
        }
        if (days > 0) {
            timeAgoString+=days+" days ";
        }
        if (hours > 0) {
            timeAgoString+=hours+" hours ";
        }
        if (minutes >= 0) {
            timeAgoString+=minutes+" min ";
        }

        return (timeAgoString.equals("") ? "--" : timeAgoString + "ago");
    }
}
