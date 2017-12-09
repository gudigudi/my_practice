package com.gudigudigudi.mdtemplate.collector;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gu on 12/9/17.
 */

public class DumpSysCollector {

    private static final String TAG = "DumpSysCollector";

    private static final int DEFAULT_BUFFER_SIZE_IN_BYTES = 8192;

    public static String collectMemInfo() {
        final StringBuilder memInfo = new StringBuilder();

        BufferedReader bufferedReader = null;

        try {
            final List<String> commandLine = new ArrayList<String>();
            commandLine.add("dumpsys");
            commandLine.add("meminfo");
            commandLine.add(Integer.toString(android.os.Process.myPid()));

            final Process process = Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()), DEFAULT_BUFFER_SIZE_IN_BYTES);

            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                memInfo.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "DumpSysCollector.meminfo could not retrieve data", e);
        }

        try {
            if (null != bufferedReader) {
                bufferedReader.close();
            }
        } catch (IOException e) {

        }

        return memInfo.toString();
    }
}
