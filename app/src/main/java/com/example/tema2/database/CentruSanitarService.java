package com.example.tema2.database;

import android.content.Context;

import com.example.tema2.network.AsyncTaskRunner;
import com.example.tema2.network.Callback;


import java.util.List;
import java.util.concurrent.Callable;
public class CentruSanitarService {

    private final CentruSanitarDao centruDao;
    private final AsyncTaskRunner asyncTaskRunner;

    public CentruSanitarService(Context context) {
        this.centruDao = DatabaseManager.getInstance(context).getCentruDao();
        this.asyncTaskRunner = new AsyncTaskRunner();
    }

    public void insert(CentruSanitar centru, Callback<CentruSanitar> activityThread) {
        Callable<CentruSanitar> insertOperation = new Callable<CentruSanitar>() {
            @Override
            public CentruSanitar call() {
                if (centru == null || centru.getId() > 0) {
                    return null;
                }
                long id = centruDao.insert(centru);
                if (id < 0) {
                    return null;
                }
                centru.setId(id);
                return centru;
            }
        };
        asyncTaskRunner.executeAsync(insertOperation, activityThread);
    }

    public void getAll(Callback<List<CentruSanitar>> activityThread){
        Callable<List<CentruSanitar>> getAllOperation = new Callable<List<CentruSanitar>>() {
            @Override
            public List<CentruSanitar> call() throws Exception {
                return centruDao.getAll();
            }
        };
        asyncTaskRunner.executeAsync(getAllOperation,activityThread);
    }

}
