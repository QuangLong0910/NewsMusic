package longbqph27075.fpoly.newsmusic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Taikhoandatabase extends SQLiteOpenHelper {

    public Taikhoandatabase(@Nullable Context context) {
        super(context,"QUANGLONG", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table nguoidung (id integer primary key autoincrement, username text, pass text )";
        db.execSQL(sql);
        sql = "INSERT INTO nguoidung VALUES(1,'quanglong','123456')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists nguoidung");
    }
}
