package com.example.tema2.network;

public interface Callback<R>{
    void runResultOnUiThread(R result);
}

