package com.example.homerental.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.homerental.Adapter.ItemsAdapter;
import com.example.homerental.Domain.ItemsDomain;
import com.example.homerental.R;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular, adapterNew;
    private RecyclerView recyclerViewPopular, recyclerViewNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initRecyclerView();
    }

    private void initRecyclerView(){
        ArrayList<ItemsDomain> ItemsArraylist=new ArrayList<>();
        ItemsArraylist.add(new ItemsDomain("House with great view","San francisco, CA 94110","This 2 bed /1 bath home boosts an enormous,\n"+
                "open-living plan, accented by striking\n"+
                "architectural features and hight-end finishes"+
                "feel inspired by open sight lines that \n"+
                "embrace the outdoors,crowned by stunning\n"+
                "coffered cellings",2,1,84000,"pic1",true));

        ItemsArraylist.add(new ItemsDomain("House with great view","San francisco, CA 94110","This 2 bed /1 bath home boosts an enormous,\n"+
                "open-living plan, accented by striking\n"+
                "architectural features and hight-end finishes"+
                "feel inspired by open sight lines that \n"+
                "embrace the outdoors,crowned by stunning\n"+
                "coffered cellings",3,1,86000,"pic2",false));

        ItemsArraylist.add(new ItemsDomain("House with great view","San francisco, CA 94110","This 2 bed /1 bath home boosts an enormous,\n"+
                "open-living plan, accented by striking\n"+
                "architectural features and hight-end finishes"+
                "feel inspired by open sight lines that \n"+
                "embrace the outdoors,crowned by stunning\n"+
                "coffered cellings",2,2,85000,"pic3",true));

        recyclerViewPopular=findViewById(R.id.viewPupolar);
        recyclerViewNew=findViewById(R.id.viewNew);

        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerViewNew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        adapterPopular=new ItemsAdapter(ItemsArraylist);
        adapterNew=new ItemsAdapter(ItemsArraylist);

        recyclerViewPopular.setAdapter(adapterPopular);
        recyclerViewNew.setAdapter(adapterNew);

    }
}