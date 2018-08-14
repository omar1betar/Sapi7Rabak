package com.betaromar.omar1betar.sapi7rabak;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AzkarSpahActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar_spah);
        ListView list = (ListView) findViewById(R.id.list_view);
        String[] azkar = getResources().getStringArray(R.array.azkar_spah);
        String[] number = getResources().getStringArray(R.array.numbers_spah);
        CustomAdapter customAdapter = new CustomAdapter();
        list.setAdapter(customAdapter);
        //to dispay back button on nav bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.row_item, R.id.content, azkar);
//        list.setAdapter(adapter);

    }
    // to display back button on nav bar
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    class CustomAdapter extends BaseAdapter{

        String[] azkar = getResources().getStringArray(R.array.azkar_spah);
        String[] number = getResources().getStringArray(R.array.numbers_spah);
        @Override
        public int getCount() {
            return azkar.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.row_item,null);
            ImageView img =  (ImageView)view.findViewById(R.id.img);
            TextView content = (TextView)view.findViewById(R.id.content);
            TextView num = (TextView)view.findViewById(R.id.num);

            String[] azkar = getResources().getStringArray(R.array.azkar_spah);
            String[] number = getResources().getStringArray(R.array.numbers_spah);

            img.setImageResource(R.drawable.azkar_spah2);
            content.setText(azkar[i]);
            num.setText(number[i]);

            return view;
        }
    }


}
