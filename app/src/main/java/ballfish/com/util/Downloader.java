package ballfish.com.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yin on 2017/2/18.
 */

public final class Downloader {
    private Downloader() {}

    public static String Get(URL url) {
        HttpURLConnection httpURLConnection = null;
        String response = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            response = stringBuffer.toString();

            bufferedReader.close();
            inputStream.close();
        } catch (Exception e) {
            return "";
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return response;
    }

    public static String Post(JSONObject json, URL url) {
        return null;
    }

    public static boolean Put(JSONObject json, URL url) {
        return false;
    }

    public static boolean Delete(JSONObject json, URL url) {
        return false;
    }

}
