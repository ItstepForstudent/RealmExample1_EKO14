package com.step.realmdemo1;

import com.step.realmdemo1.realmEntities.Valute;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ValuteApi {
    @GET("pubinfo?json&exchange&coursid=5")
    public Call<List<Valute>> getValutes();
}
