package com.company.NetWorking;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by gudi on 4/15/15.
 */
public class URLConnDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL(args[0]);
            URLConnection urlConnection = url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
