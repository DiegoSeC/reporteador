package com.promon.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.promon.adapters.TablesAdapter;
import com.promon.models.Connection;
import com.promon.sqlite.SQLiteManager;
import com.promon.util.Util;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    TablesAdapter adapter;
    ListView list;

    RuntimeExceptionDao<Connection, Integer> connectionDao;
    private SQLiteManager sm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.log("Comenzando el Main");

        connectionDao = getHelper().getConnectionDAO();
        List<Connection> data = connectionDao.queryForAll();

        if(data.size() == 0) {
            Intent intent = new Intent(MainActivity.this, ConnectionActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Liberamos a nuestro DAO
        if(sm != null) {
            OpenHelperManager.releaseHelper();
            sm = null;
        }
    }

    private SQLiteManager getHelper() {
        if(sm == null) {
            sm = OpenHelperManager.getHelper(this, SQLiteManager.class);
        }

        return sm;
    }
}
