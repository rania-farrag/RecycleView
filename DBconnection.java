package com.example.wallymisr.recycleview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by WallyMisr on 05/11/2017.
 */
public class DBconnection {

    public static String getdata(String []array){
        BufferedReader bufferedReader = null;

        if(array.length == 1){
            String uri = array[0];

            URL url = null;

            try {
                url = new URL(uri);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }


}
