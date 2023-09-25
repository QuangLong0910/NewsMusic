package longbqph27075.fpoly.newsmusic.DOCBAO;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import longbqph27075.fpoly.newsmusic.R;


public class DownLoadTinTuc extends AsyncTask<String,Void, List<TinTuc>> {
    ManDocBao activity = null;
    List<TinTuc> list = new ArrayList<TinTuc>();

    public DownLoadTinTuc(ManDocBao activity) {
        this.activity = activity;
    }

    @Override
    protected List<TinTuc> doInBackground(String... strings) {

        TinTucLoader loader = new TinTucLoader();



        String urlRss = strings[0];

        try {
            URL url = new URL(urlRss);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            if(urlConnection.getResponseCode() ==200){

                InputStream inputStream = urlConnection.getInputStream();
                list = loader.getTinTucList( inputStream );

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }


        return list;
    }

    @Override
    protected void onPostExecute(List<TinTuc> tinTucs) {
        super.onPostExecute(tinTucs);

        Log.d("zzzzz", "onPostExecute: số lượng bài viết = " + tinTucs.size());
        for(int i = 0; i< tinTucs.size(); i++){
            Log.d("zzzzz", "Tiêu đề bài viết:  " + tinTucs.get(i).getTitle()  );
        }

        AdapterRSS adapter = new AdapterRSS(activity,R.layout.dong1_item,list);
        ListView lv = activity.findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TinTuc m= list.get(position);
                Intent i = new Intent(activity,ManTin.class);
                i.putExtra("title",m.title);
                i.putExtra("description",m.description);
                i.putExtra("link",m.link);
                activity.startActivity(i);


            }
        });



    }
}

