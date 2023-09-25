package longbqph27075.fpoly.newsmusic;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class Customlistyeuthick extends BaseAdapter{
    public static List<MusicDTO> listyeuthich = new ArrayList<>();

    Context context;
    public Customlistyeuthick(List<MusicDTO> list,Context context){
        this.listyeuthich = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listyeuthich.size() ;
    }

    @Override
    public Object getItem(int position) {
        return listyeuthich.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        View view = LayoutInflater.from(context).inflate(R.layout.listyeuthick,parent,false);
        ImageView imageView = view.findViewById(R.id.icon);
        TextView tvtitle = view.findViewById(R.id.title);
        TextView casi = view.findViewById(R.id.subtitle);
        ImageView tim = view.findViewById(R.id.icon2);
        tvtitle.setText(listyeuthich.get(position).name);
        casi.setText(listyeuthich.get(position).casi);
        tim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });



        return view;
    }

}
