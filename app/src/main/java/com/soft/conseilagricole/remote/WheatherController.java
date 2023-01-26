package com.soft.conseilagricole.remote;

import java.util.concurrent.Callable;

import retrofit2.Callback;

public interface WheatherController extends Callback {
    String url="http://api/openwheather.org/data/2.5/";
}
