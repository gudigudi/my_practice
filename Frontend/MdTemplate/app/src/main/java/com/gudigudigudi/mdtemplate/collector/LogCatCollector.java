package com.gudigudigudi.mdtemplate.collector;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gu on 12/9/17.
 */

public class LogCatCollector {
    private static final String TAG = "LogCatCollector";

    private static final int DEFAULT_TAIL_COUNT = 100;
    private static final int DEFAULT_BUFFER_SIZE_IN_BYTES = 8192;

    public String collectLogCat(@Nullable String bufferName, boolean logcatFilterByPid, String[] logcatArguments) {
        final int myPid = android.os.Process.myPid();
        String myPidStr = null;
        if (logcatFilterByPid && myPid > 0) {
            myPidStr = Integer.toString(myPid) + "):";
        }

        final List<String> commandLine = new ArrayList<String>();
        commandLine.add("logcat");
        if (bufferName != null) {
            commandLine.add("-b");
            commandLine.add(bufferName);
        }

        final int tailCount;
        final List<String> logcatArgumentsList = new ArrayList<String>(Arrays.asList(logcatArguments));
        final int tailIndex = logcatArgumentsList.indexOf("-t");
        if (tailIndex > -1 && tailIndex < logcatArgumentsList.size()) {
            tailCount = Integer.parseInt(logcatArgumentsList.get(tailIndex + 1));
        } else {
            tailCount = -1;
        }

        final LinkedList<String> logcatBuf = new LinkedList<String>();

        commandLine.addAll(logcatArgumentsList);

        BufferedReader bufferedReader = null;
        try {
            final Process process = Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()), DEFAULT_BUFFER_SIZE_IN_BYTES);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream stderr = process.getErrorStream();
                        byte[] dummy = new byte[DEFAULT_BUFFER_SIZE_IN_BYTES];
                        while (stderr.read(dummy) >= 0) {
                            ;
                        }
                    } catch (IOException e) {
                    }
                }
            }).start();

            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (myPidStr == null || line.contains(myPidStr)) {
                    logcatBuf.add(line + "\n");
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "LogCatCollector.collectLogCat could not retrieve data.", e);
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
            }
        }

        return logcatBuf.toString();
    }
}
