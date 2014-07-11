package com.promon.util;

import android.util.Log;

/**
 * Created by Diego on 10/07/14.
 */
public class Util {

    protected static boolean displayLog = true;

    public static void log(String obj) {
        if(displayLog) {
            Log.d("Reporte BD", obj);
        }
    }


}
