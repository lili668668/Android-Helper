package ballfish.com.helper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class TestASRActivity extends AppCompatActivity {

    private Intent asr = null;
    private TextToSpeech tts;
    private Context context = null;
    private Button btn = null;
    private TextView text = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tesrrt_asr);

        btn = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.text);

        context = this;

        asr = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        if (!hasRecognizer()) {
            Toast.makeText(context, "no asr", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asr.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
                asr.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please...");
                asr.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
                startActivityForResult(asr, 1);
            }
        });

        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    Locale l = Locale.CHINA;

                    if (tts.isLanguageAvailable(l) == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                        tts.setLanguage(l);
                    } else {
                        Toast.makeText(context, "No Lang", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "No tts", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.shutdown();
        }
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent it) {
        if (requestCode != 1 || resultCode != RESULT_OK) {
            return ;
        }

        List<String> list = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        String res = list.get(0);

        text.setText(res);

        tts.speak(res, TextToSpeech.QUEUE_FLUSH, null, "test");

        Intent sendKeep = context.getPackageManager().getLaunchIntentForPackage("com.google.android.keep");
        sendKeep.setAction(Intent.ACTION_SEND);
        sendKeep.putExtra(Intent.EXTRA_TEXT, res);
        sendKeep.setType("text/plain");

        startActivity(sendKeep);
    }

    private boolean hasRecognizer() {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(asr, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() != 0;
    }

    private  
}
