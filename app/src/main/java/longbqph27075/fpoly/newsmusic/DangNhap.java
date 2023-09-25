package longbqph27075.fpoly.newsmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DangNhap extends AppCompatActivity {
Taikhoandatabase taikhoandatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        EditText edtname = findViewById(R.id.edt_user);
        EditText edtpass = findViewById(R.id.edt_pass);
        edtname.setText("QuangLong");
        Button button = findViewById(R.id.dangnhap);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(edtname.getText().equals("QuangLong")&& edtpass.getText().equals("123456"));
                        Intent intent = new Intent(DangNhap.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
    }
}