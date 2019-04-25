package me.hpainter.boa2.m_JSON;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connector {

    public static Object connect(String jsonURL) {
        try {
            URL url = new URL(jsonURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

//Set Requests
            con.setRequestProperty("Accept-Language", "en-US");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("device-type", "APPLE");
            con.setRequestProperty("platformType", "SMRTPH");
            con.setRequestMethod("GET");
            con.setConnectTimeout(18000);
            con.setReadTimeout(18000);
            con.setDoInput(true);

            return con;
//Handling Errors
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error"+e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error"+e.getMessage();
        }
    }
}
