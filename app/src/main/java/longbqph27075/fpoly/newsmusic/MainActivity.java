package longbqph27075.fpoly.newsmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import longbqph27075.fpoly.newsmusic.DOCBAO.ManDocBao;

public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
DrawerLayout drawerLayout ;
NavigationView navigationView;
ListView listView ;
ArrayList<ItemMenu> arrayList;
MenuAdapter adapter;
ImageView imageView,nhacyeuthick;
String currentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
ImageView music,news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        actionToolbar();
        actionMenu();
        onClick();
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Mannghenhac.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Phát nhạc", Toast.LENGTH_SHORT).show();
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ManDocBao.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Đọc báo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void actionMenu() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemMenu(1,"Màn hình chính",R.drawable.ic_baseline_home_24));
        arrayList.add(new ItemMenu(2,"Máy phát nhạc",R.drawable.ic_baseline_music_note_24));
        arrayList.add(new ItemMenu(3,"Đọc báo",R.drawable.ic_baseline_tablet_mac_24));
        arrayList.add(new ItemMenu(4,"Đăng nhập",R.drawable.ic_baseline_send_24));
        arrayList.add(new ItemMenu(5,"Đăng xuất",R.drawable.ic_baseline_exit_to_app_24));

        adapter = new MenuAdapter(this,R.layout.dong_item,arrayList);
        listView.setAdapter(adapter);


    }

    public  void anhxa(){
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigation);
        listView = findViewById(R.id.listview);
        music = findViewById(R.id.music);
        news = findViewById(R.id.news);
        imageView = findViewById(R.id.imageView);

    }
    public void actionToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }
    private void onClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(MainActivity.this, "Màn hình chính", Toast.LENGTH_SHORT).show();
                } else if  (position==1){
                    Intent intent = new Intent(MainActivity.this,Mannghenhac.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Phát nhạc", Toast.LENGTH_SHORT).show();
                } else if  (position==2){
                    Intent intent = new Intent(MainActivity.this,ManDocBao.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Đọc báo", Toast.LENGTH_SHORT).show();
                } else if  (position==3){
                    Intent intent = new Intent(MainActivity.this,DangNhap.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(MainActivity.this, "Màn hình chính", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openCamera(View view) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "longbqph27075.fpoly.newsmusic.fileprovider",
                        photoFile);


                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic();
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
        imageView.setRotation(90);
    }


}