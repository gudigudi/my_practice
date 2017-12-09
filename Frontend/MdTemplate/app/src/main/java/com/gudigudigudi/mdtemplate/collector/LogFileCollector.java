package com.gudigudigudi.mdtemplate.collector;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by gu on 12/9/17.
 */

public class LogFileCollector {

    @NonNull
    public String collectLogFile(@NonNull Context context, @NonNull String fileName, int numberOfLines) throws IOException {
        final LinkedList<String> resultBuffer = new LinkedList<String>();
        final BufferedReader reader = getReader(context, fileName);

        try {
            String line = reader.readLine();
            while (line != null) {
                resultBuffer.add(line + "\n");
                line = reader.readLine();
            }
        } finally {
            try {
                reader.close();
            } catch (IOException e) {

            }
        }

        return resultBuffer.toString();
    }

    @NonNull
    private BufferedReader getReader(Context context, String fileName) {
        try {
            final FileInputStream inputStream;
            if (fileName.startsWith("/")) {
                inputStream = new FileInputStream(fileName);
            } else if (fileName.contains("/")) {
                inputStream = new FileInputStream(new File(context.getFilesDir(), fileName));
            } else {
                inputStream = context.openFileInput(fileName);
            }

            return new BufferedReader(new InputStreamReader(inputStream), 1024);
        } catch (FileNotFoundException e) {
            return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(new byte[0])));
        }
    }
}
