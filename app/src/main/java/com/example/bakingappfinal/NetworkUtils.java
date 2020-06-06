package com.example.bakingappfinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class NetworkUtils {

    public static URL buildUrl(String url) {

        String url_this = url;
        URL url_link = null;

        try {
            url_link = new URL(url_this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url_link;
    }

    public static String httpsResponse (URL url_path){
        String json_file = "";
        try {

            HttpURLConnection httpURLConnection = (HttpURLConnection) url_path.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while (line!= null){
                line = bufferedReader.readLine();
                json_file = json_file + line;
            }
            return json_file;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
