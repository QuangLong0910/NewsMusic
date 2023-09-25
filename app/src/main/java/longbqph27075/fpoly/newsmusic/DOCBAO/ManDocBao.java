package longbqph27075.fpoly.newsmusic.DOCBAO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import longbqph27075.fpoly.newsmusic.R;

public class ManDocBao extends AppCompatActivity {
String tvrss;
    Toolbar toolbar ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_man_doc_bao);

            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            String auto = "https://vnexpress.net/rss/tin-moi-nhat.rss";
            DownLoadTinTuc downloadTinTuc = new DownLoadTinTuc(ManDocBao.this);
            downloadTinTuc.execute(auto);

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_item,menu);
            return super.onCreateOptionsMenu(menu);
        }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.trangchu:
                    tvrss="https://vnexpress.net/rss/tin-moi-nhat.rss";
                    DownLoadTinTuc downloadTinTuc = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc.execute(tvrss);
                    return true;
                case R.id.thegioi:
                    tvrss="https://vnexpress.net/rss/the-gioi.rss";
                    DownLoadTinTuc downloadTinTuc1 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc1.execute(tvrss);
                    return true;
                case R.id.thoisu:
                    tvrss="https://vnexpress.net/rss/thoi-su.rss";
                    DownLoadTinTuc downloadTinTuc2 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc2.execute(tvrss);
                    return true;
                case R.id.kinhdoanh:
                    tvrss="https://vnexpress.net/rss/kinh-doanh.rss";
                    DownLoadTinTuc downloadTinTuc3 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc3.execute(tvrss);
                    return true;
                case R.id.startup:
                    tvrss="https://vnexpress.net/rss/startup.rss";
                    DownLoadTinTuc downloadTinTuc4 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc4.execute(tvrss);
                    return true;
                case R.id.giaitri:
                    tvrss="https://vnexpress.net/rss/giai-tri.rss";
                    DownLoadTinTuc downloadTinTuc5 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc5.execute(tvrss);
                    return true;
                case R.id.thethao:
                    tvrss="https://vnexpress.net/rss/the-thao.rss";
                    DownLoadTinTuc downloadTinTuc6 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc6.execute(tvrss);
                    return true;
                case R.id.phapluat:
                    tvrss="https://vnexpress.net/rss/phap-luat.rss";
                    DownLoadTinTuc downloadTinTuc7 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc7.execute(tvrss);
                    return true;
                case R.id.giaoduc:
                    tvrss="https://vnexpress.net/rss/giao-duc.rss";
                    DownLoadTinTuc downloadTinTuc8 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc8.execute(tvrss);
                    return true;
                case R.id.tinmoinhat:
                    tvrss="https://vnexpress.net/rss/tin-moi-nhat.rss";
                    DownLoadTinTuc downloadTinTuc9 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc9.execute(tvrss);
                    return true;
                case R.id.tinnoibat:
                    tvrss="https://vnexpress.net/rss/tin-noi-bat.rss";
                    DownLoadTinTuc downloadTinTuc10 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc10.execute(tvrss);
                    return true;
                case R.id.suckhoe:
                    tvrss="https://vnexpress.net/rss/suc-khoe.rss";
                    DownLoadTinTuc downloadTinTuc11 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc11.execute(tvrss);
                    return true;
                case R.id.doisong:
                    tvrss="https://vnexpress.net/rss/doi-song.rss";
                    DownLoadTinTuc downloadTinTuc12 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc12.execute(tvrss);
                    return true;
                case R.id.dulich:
                    tvrss="https://vnexpress.net/rss/du-lich.rss";
                    DownLoadTinTuc downloadTinTuc13 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc13.execute(tvrss);
                    return true;
                case R.id.khoahoc:
                    tvrss="https://vnexpress.net/rss/khoa-hoc.rss";
                    DownLoadTinTuc downloadTinTuc14 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc14.execute(tvrss);
                    return true;
                case R.id.sohoa:
                    tvrss="https://vnexpress.net/rss/so-hoa.rss";
                    DownLoadTinTuc downloadTinTuc15 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc15.execute(tvrss);
                    return true;
                case R.id.xe:
                    tvrss="https://vnexpress.net/rss/xe.rss";
                    DownLoadTinTuc downloadTinTuc16 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc16.execute(tvrss);
                    return true;
                case R.id.ykien:
                    tvrss="https://vnexpress.net/rss/y-kien.rss";
                    DownLoadTinTuc downloadTinTuc17 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc17.execute(tvrss);
                    return true;
                case R.id.tamsu:
                    tvrss="https://vnexpress.net/rss/tam-su.rss";
                    DownLoadTinTuc downloadTinTuc18 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc18.execute(tvrss);
                    return true;
                case R.id.cuoi:
                    tvrss="https://vnexpress.net/rss/cuoi.rss";
                    DownLoadTinTuc downloadTinTuc19 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc19.execute(tvrss);
                    return true;
                case R.id.tinxemnhieu:
                    tvrss="https://vnexpress.net/rss/tin-xem-nhieu.rss";
                    DownLoadTinTuc downloadTinTuc20 = new DownLoadTinTuc(ManDocBao.this);
                    downloadTinTuc20.execute(tvrss);
                    return true;



            }
            return super.onOptionsItemSelected(item);
        }

    }
