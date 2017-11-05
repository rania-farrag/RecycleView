package com.example.wallymisr.recycleview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String content;
    testclass testclass;
    ArrayList<testclass> arrayList;

    private final String names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private final String urls[] = {
            "https://3.bp.blogspot.com/-nEV5cidSCWI/WIH8qFR7_II/AAAAAAAAkqM/vES7DCO-S64Exwaf2LockyoTDM9qpp3jwCLcB/s400/Love%2Band%2BRomance%2B%252833%2529.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTemhwQwK-b0_t6VWFPuVfMHvXHwhQqNrVI7EYNLCc5ZQoMvn-m",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTemhwQwK-b0_t6VWFPuVfMHvXHwhQqNrVI7EYNLCc5ZQoMvn-m"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeddingBackground weddingBackground = new WeddingBackground();
        weddingBackground.execute("http://192.168.1.6/flower_webservices/weddingshops.php");
        //initViews();

    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList androidVersions = prepareData();
        Adapter adapter = new Adapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);

    }

    private ArrayList prepareData(){

        ArrayList android_version = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            testclass testclass = new testclass();
            testclass.setName(names[i]);
            testclass.setImage(urls[i]);
            android_version.add(testclass);
        }
        return android_version;
    }


    class WeddingBackground extends AsyncTask<String , String , String>{


        @Override
        protected String doInBackground(String... strings) {
            content = DBconnection.getdata(strings);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray jsonArray = JsonParser.parser(s);
            testclass = new testclass();
            arrayList = new ArrayList<testclass>();

            for (int i=0 ; i<jsonArray.length() ; i++){
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
                    testclass.setName(jsonObject.getString("name"));
                    testclass.setImage(jsonObject.getString("pic"));

                    arrayList.add(testclass);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);

            //ArrayList androidVersions = prepareData();
            Adapter adapter = new Adapter(getApplicationContext(),arrayList);
            recyclerView.setAdapter(adapter);
        }
    }
}
