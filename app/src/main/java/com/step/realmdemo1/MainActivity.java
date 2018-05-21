package com.step.realmdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.step.realmdemo1.adapters.ValutesAdapter;
import com.step.realmdemo1.realmEntities.Valute;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ValuteApi api;
    List<Valute> listValutes;
    RecyclerView recyclerView;
    ValutesAdapter adapter;

    private void retrofitInit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua/p24api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ValuteApi.class);
    }


    private void loadFromRealm(){
        Realm realm =Realm.getDefaultInstance();
        List<Valute> valutes = realm.where(Valute.class).findAll();
        listValutes = realm.copyFromRealm(valutes);
        adapter.setValutes(listValutes);
        realm.close();
    }

    private void initRecycler(){
        recyclerView = findViewById(R.id.recycler);
        adapter = new ValutesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updateBase(){
        api.getValutes().enqueue(new Callback<List<Valute>>() {
            @Override
            public void onResponse(Call<List<Valute>> call, Response<List<Valute>> response) {
                Realm realm =Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(response.body());
                realm.commitTransaction();
                realm.close();
                loadFromRealm();

                Toast.makeText(MainActivity.this,"internet",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<List<Valute>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"local",Toast.LENGTH_LONG).show();
            }
        });
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitInit();
        setContentView(R.layout.activity_main);
        initRecycler();
        loadFromRealm();
        updateBase();


    }
}
