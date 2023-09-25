package longbqph27075.fpoly.newsmusic;

import static longbqph27075.fpoly.newsmusic.CustomListAdapter.listyeuthich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Nhacyeuthick extends AppCompatActivity {
Customlistyeuthick  adapter;
Uri uri;
Mannghenhac mannghenhac;
MediaPlayer mediaPlayer;
ArrayList<MusicDTO> songArr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhacyeuthick);
        ListView listView = findViewById(R.id.lvthick);

        adapter = new Customlistyeuthick(listyeuthich,Nhacyeuthick.this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MusicDTO musicDTO = songArr.get(position);

                uri = Uri.parse(musicDTO.file_path);

                Log.d("zzzz", "onItemClick: play = " + musicDTO.file_path);

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;

                }
                mannghenhac.PlayOrStopMusic();

            }
        });


    }

    @Override
    protected void onResume() {

        super.onResume();
    }
}