package jfxcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translate {
    public static String translateText(String text ,String langfrom,String langto) throws UnsupportedEncodingException, IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbyyOZ8m5BEhlL74JcbBs7VygJVeAO5NWMX_8edPVT0qnAPmvFA27DmOYLQrPLRPgtwJhA/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langto +
                "&source=" + langfrom;
        //System.out.println(urlStr);
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
