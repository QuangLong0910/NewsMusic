package longbqph27075.fpoly.newsmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Mannghenhac extends AppCompatActivity {


    private ArrayList<MusicDTO> songArrayList;
    private ListView lvSong;
    ProgressBar musicBar;
    ImageView playorstop;
    ImageView pre, next;
    TextView tvnamebaihat;
    TextView tvCurrentTime;
    Cursor cursor;
    CustomListAdapter adapter;

    ImageView add;


    MediaPlayer myMusicPlayer; // không đặt tên mediaPlayer vì có thể trùng biến ở một số hàm khác


    final String mp3 = "https://www.w3schools.com/html/horse.mp3";
    Uri uri = Uri.parse(mp3);

boolean isAll;
ImageView tim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mannghenhac);
        ImageView image = (ImageView) findViewById(R.id.xoay);
        Animation animFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.xoay);
        image.startAnimation(animFade);
        tim = findViewById(R.id.icon2);
        tvnamebaihat = findViewById(R.id.namebaihat);
        Animation animFade1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hien);
        tvnamebaihat.startAnimation(animFade1);
        musicBar = findViewById(R.id.musicBar);


        pre = findViewById(R.id.pre);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pre();
            }
        });
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
        playorstop = findViewById(R.id.playorpause);

        tvCurrentTime = findViewById(R.id.currentTime);


        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mannghenhac.this,Nhacyeuthick.class);
                startActivity(intent);
            }
        });
        playorstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayOrStopMusic();
            }
        });

        initPlayList();


    }


    /**
     * hàm định dạng lại thời gian hiển thị
     *
     * @param duration
     * @return
     */
    private String formatDuration(long duration) {
        long minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
        long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)
                - minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);

        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * runnable là dùng để tạo ra 1 tiến trình riêng chạy nhạc, cái này thay cho việc tạo service.
     */
    Handler handler = new Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (myMusicPlayer != null) {

                int totalTime = myMusicPlayer.getDuration();
                int currentTime = myMusicPlayer.getCurrentPosition();
                musicBar.setMax(totalTime);
                musicBar.setProgress(currentTime);
                handler.postDelayed(this, 100);

                Log.e("zzzzz", "Đang chạy... " + currentTime);

                tvCurrentTime.setText(formatDuration(currentTime));
            }
        }
    };


    // xử lý tạm dừng. Lưu ý myMusicPlayer phải được khai báo ở phạm vi class thì mới điều khiển khắp nơi được
    void PauseMusic() {
        if (myMusicPlayer != null) {
            myMusicPlayer.pause();
            playorstop.setImageResource(R.drawable.img);

        }
    }

    //xử lý chạy nhạc, dựa vào biến uri khởi tạo ở trên.
    // sau này có play list thì khi bấm vào playlist sẽ chỉ cần gán lại uri và gọi hàm play này là ok
    public void PlayOrStopMusic() {
        // bấm 1 lần thì chạy sau đó chuyển nút bấm về stop
        // bấm lại thì stop

        if (myMusicPlayer == null) {

            // chưa chạy
            myMusicPlayer = MediaPlayer.create(this, uri);

            int s = myMusicPlayer.getDuration();

            TextView tvTotal = findViewById(R.id.totalTime);

            tvTotal.setText(formatDuration(s));
            myMusicPlayer.start();

            runnable.run();
            playorstop.setImageResource(R.drawable.stop);

            myMusicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {


                    mediaPlayer.stop();
                    playorstop.setImageResource(R.drawable.img);


                }
            });

        } else {
            // media khác nulll

            if (myMusicPlayer.isPlaying()) {
                myMusicPlayer.pause();
                playorstop.setImageResource(R.drawable.img);


            } else {
                // media đang dừng
                try {
                    if (myMusicPlayer.isLooping())
                        myMusicPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myMusicPlayer.start();
                playorstop.setImageResource(R.drawable.stop);


            }

        }
    }

    int x;

    void initPlayList() {

        lvSong = findViewById(R.id.lv_song);
        songArrayList = new ArrayList<MusicDTO>();

        getSongList();  // lấy danh sách cho vào list xong rồi đổi lên adapter

        adapter = new CustomListAdapter(songArrayList,Mannghenhac.this);
        lvSong.setAdapter(adapter);

        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                MusicDTO musicDTO = songArrayList.get(i);
                tvnamebaihat.setText(musicDTO.name);
                uri = Uri.parse(musicDTO.file_path);

                Log.d("zzzz", "onItemClick: play = " + musicDTO.file_path);

                if (myMusicPlayer != null) {
                    myMusicPlayer.stop();
                    myMusicPlayer.release();
                    myMusicPlayer = null;
                    playorstop.setImageResource(R.drawable.img);
                }
                PlayOrStopMusic();
                x=i;



            }
        });


    }

    // lấy danh sách bài nhạc trong điện thoại có sẫn
    public void getSongList() {

        ContentResolver contentResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = contentResolver.query(musicUri, null, null, null, null, null);


        if (musicCursor != null && musicCursor.moveToFirst()) {

            //     xem thử xem có những cột gì tương ứng vơi những tên gì
            for (int i = 0; i < musicCursor.getColumnCount(); i++) {
                Log.d("zzzzzzz", "getSongList: " + i + "====" + musicCursor.getColumnName(i) + "===== " + musicCursor.getString(i));
            }


            //get Columns
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int casi = musicCursor.getColumnIndex(MediaStore.Audio.Media.COMPOSER);
            int file_path_Column = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);


            // Store the title, id and artist name in Song Array list.
            do {
                MusicDTO musicDTO = new MusicDTO();
                String thisTitle = musicCursor.getString(titleColumn);
                String thiscasi = musicCursor.getString(casi) ;

                // bạn có thể lấy nhiều thuộc tính khác để trình bày lên giao diện cho đẹp.

                musicDTO.name = thisTitle;
                musicDTO.casi = thiscasi;
                musicDTO.file_path = musicCursor.getString(file_path_Column);  // lấy đường dẫn file nhạc

                songArrayList.add(musicDTO); // đưa vào danh sách


            }
            while (musicCursor.moveToNext());


            // For best practices, close the cursor after use.
            musicCursor.close();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    void pre() {

if(x==0) {
    x = songArrayList.size()-1;
    MusicDTO musicDTO = songArrayList.get(x);
    tvnamebaihat.setText(musicDTO.name);
    uri = Uri.parse(musicDTO.file_path);

    Log.d("zzzz", "onItemClick: play = " + musicDTO.file_path);

    if (myMusicPlayer != null) {
        myMusicPlayer.stop();
        myMusicPlayer.release();
        myMusicPlayer = null;
        playorstop.setImageResource(R.drawable.img);
    }
    PlayOrStopMusic();
} else {
    MusicDTO musicDTO = songArrayList.get(x--);
    tvnamebaihat.setText(musicDTO.name);
    uri = Uri.parse(musicDTO.file_path);

    Log.d("zzzz", "onItemClick: play = " + musicDTO.file_path);

    if (myMusicPlayer != null) {
        myMusicPlayer.stop();
        myMusicPlayer.release();
        myMusicPlayer = null;
        playorstop.setImageResource(R.drawable.img);
    }
    PlayOrStopMusic();
}


    }

    void next() {
        if (x == songArrayList.size()) {
            x = 0;

            MusicDTO musicDTO = songArrayList.get(x);
            tvnamebaihat.setText(musicDTO.name);
            uri = Uri.parse(musicDTO.file_path);

            Log.d("zzzz", "onItemClick: play = " + musicDTO.file_path);

            if (myMusicPlayer != null) {
                myMusicPlayer.stop();
                myMusicPlayer.release();
                myMusicPlayer = null;
                playorstop.setImageResource(R.drawable.img);

            }
            PlayOrStopMusic();


        } else {
            MusicDTO musicDTO = songArrayList.get(x++);
            tvnamebaihat.setText(musicDTO.name);
            uri = Uri.parse(musicDTO.file_path);

            Log.d("zzzz", "onItemClick: play = " + musicDTO.file_path);

            if (myMusicPlayer != null) {
                myMusicPlayer.stop();
                myMusicPlayer.release();
                myMusicPlayer = null;
                playorstop.setImageResource(R.drawable.img);

            }
            PlayOrStopMusic();
        }

    }


}
