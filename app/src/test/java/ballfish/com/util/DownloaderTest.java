package ballfish.com.util;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by yin on 2017/2/18.
 */
public class DownloaderTest {
    @Test
    public void get() throws Exception {
        String path = "https://jieba-http-api.herokuapp.com/jieba-cut?sentence=%E6%B8%AC%E8%A9%A6%E6%B8%AC%E8%A9%A6";
        URL url = new URL(path);

        String excepted = "{\"words\":[\"測試\",\"測試\"]}";

        String actual = Downloader.Get(url);

        assertEquals(excepted, actual);
    }

}