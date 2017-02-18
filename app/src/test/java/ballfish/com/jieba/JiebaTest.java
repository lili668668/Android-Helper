package ballfish.com.jieba;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yin on 2017/2/18.
 */
public class JiebaTest {
    @Test
    public void cut() throws Exception {
        String sentence = "測試測試";
        String[] excepted = new String[]{"測試", "測試"};

        String[] actual = Jieba.cut(sentence);

        assertArrayEquals(excepted, actual);
    }

    @Test
    public void parse() throws Exception {
        // describe
        String content = "{\"words\":[\"測試\",\"測試\"]}";
        String[] excepted = new String[]{"測試", "測試"};

        // it
        String[] actual = Jieba.parse(content);

        // assert
        assertArrayEquals(excepted, actual);

    }

}