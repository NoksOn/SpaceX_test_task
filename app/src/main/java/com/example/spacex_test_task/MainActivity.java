package com.example.spacex_test_task;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.spacex_test_task.models.RetrofitModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 MainActivity class. method onBackPressed used to close a fragment and return to Activity, here i initialize RecyclerView
 */

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private Launch_Details fragment;
    private FragmentTransaction fragmentTransaction;
    private boolean FragmentOpen = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(FragmentOpen) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            FragmentOpen = false;
        }
        else {
            this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.SetRecyclerView();
    }


    @Override
    public void OnItemClick(View v, int position) {
            fragment = Launch_Details.newInstance(mAdapter.getItem(position));
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.FragmentContainer, fragment, "BLANK_FRAGMENT").commit();
            FragmentOpen = true;
    }

    public void SetRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NetworkService.getInstance().getMyAPI().getAllLauches()
                .enqueue(new Callback<List<RetrofitModel>>() {
                    @Override
                    public void onResponse(Call<List<RetrofitModel>> call, Response<List<RetrofitModel>> response) {
                        mAdapter = new RecyclerViewAdapter(response.body(),MainActivity.this);
                        mAdapter.setmClickListener(MainActivity.this);
                        recyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<RetrofitModel>> call, Throwable t) {
                       Log.d("Retrofit",t.getMessage());
                    }
                });
    }
}
