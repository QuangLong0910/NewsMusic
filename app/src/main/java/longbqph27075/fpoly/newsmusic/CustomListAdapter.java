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

public class CustomListAdapter extends BaseAdapter {

   public static List<MusicDTO> listyeuthich = new ArrayList<>();
    private  List<MusicDTO> list;
    Context context;
    public CustomListAdapter(List<MusicDTO> list,Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size() ;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        View view = LayoutInflater.from(context).inflate(R.layout.listviewnhac,parent,false);
        ImageView imageView = view.findViewById(R.id.icon);
        TextView tvtitle = view.findViewById(R.id.title);
        TextView casi = view.findViewById(R.id.subtitle);
        ImageView tim = view.findViewById(R.id.icon2);
        tvtitle.setText(list.get(position).name);
        casi.setText(list.get(position).casi);
        tim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tim.setImageResource(R.drawable.tim);
                listyeuthich.add(list.get(position));

            }
        });



        return view;
    }


}
