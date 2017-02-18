package ballfish.com.jieba;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLEncoder;

import ballfish.com.util.Downloader;

/**
 * Created by yin on 2017/2/18.
 */

public class Jieba {
    private Jieba() {}

    public static final String HOST = "https://jieba-http-api.herokuapp.com/";
    public static final String CUT = "jieba-cut?";
    public static final String SENTENCE = "sentence=";

    public static String[] cut(String sentence) throws Exception {
        String query = URLEncoder.encode(sentence, "utf-8");
        String path = HOST + CUT + SENTENCE + query;
        try {
            URL url = new URL(path);
            String result = Downloader.Get(url);
            return parse(result);
        } catch (Exception e) {
            return new String[0];
        }
    }

    public static String[] parse(String content) {
        try {
            JSONObject json = new JSONObject(content);
            JSONArray arr = json.getJSONArray("words");
            String[] res = new String[arr.length()];
            for (int cnt = 0; cnt < arr.length(); cnt++) {
                res[cnt] = (String) arr.get(cnt);
            }
            return res;
        } catch (Exception e) {
            return new String[0];
        }

    }

}
