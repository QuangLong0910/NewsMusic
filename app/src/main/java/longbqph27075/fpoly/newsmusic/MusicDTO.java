package longbqph27075.fpoly.newsmusic;

import android.util.Log;

public class MusicDTO {
    int img;
    String casi;
    String name;
    String file_path;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCasi() {
        return casi;
    }

    public void setCasi(String casi) {
        this.casi = casi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        Log.d("zzzzzz", "toString: file path " + file_path);
        return  name;
    }
}
