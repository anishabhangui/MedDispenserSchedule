package com.example.meddispenserschedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<medicationinfo> mMedInfoList = new ArrayList<medicationinfo>();
    private Button btnaddmed;
    private String med;
    private String time;
    private String pillnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            med = bundle.getString("medItem");
            time = bundle.getString("timeformed");
            pillnumber = bundle.getString("pillnumber");
        }

        loadData();
        buildRecyclerView();
        createMedList();
        saveData();

        //mAdapter.notifyDataSetChanged();


        btnaddmed = findViewById(R.id.addButton);
        btnaddmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AddMed.class);
                startActivity(intent);
            }
        });
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mMedInfoList);
        editor.putString("med list",json);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("med list",null);
        Type type = new TypeToken<ArrayList<medicationinfo>>() {}.getType();
        mMedInfoList = gson.fromJson(json,type);

        if(mMedInfoList == null) {
            mMedInfoList = new ArrayList<>();
        }
    }



    public void createMedList() {
       /* mMedInfoList.add(new medicationinfo("Tylenol", "1:00 pm","2"));
        mMedInfoList.add(new medicationinfo("Tylenol", "4:00 pm","1"));*/
        //mMedInfoList = new ArrayList<medicationinfo>();
        mMedInfoList.add(new medicationinfo(med,time,pillnumber));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MedicationAdapter(mMedInfoList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        new ItemTouchHelper(itemtouchHelperCallBack).attachToRecyclerView(mRecyclerView);
    }

    ItemTouchHelper.SimpleCallback itemtouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            mMedInfoList.remove(viewHolder.getBindingAdapterPosition());
            mAdapter.notifyDataSetChanged();

        }
    };
}