package longbqph27075.fpoly.newsmusic.DOCBAO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import longbqph27075.fpoly.newsmusic.R;

public class ManTin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tin);
        WebView web = findViewById(R.id.web);
        Intent i = getIntent();
        String title = i.getExtras().getString("title");
        String url = i.getExtras().getString("link");
        setTitle(title);
        web.loadUrl(url);
    }
}