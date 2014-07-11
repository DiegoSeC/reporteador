package com.promon.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.promon.app.R;
import com.promon.models.Connection;
import com.promon.util.Constants;
import com.promon.util.Util;

import java.sql.SQLException;

/**
 * Created by Diego on 10/07/14.
 */
public class SQLiteManager extends OrmLiteSqliteOpenHelper {

    // Dao para mi tabla Connection
    private RuntimeExceptionDao<Connection, Integer> connectionDAO = null;

    public SQLiteManager(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Util.log("Creando la Basde de Datos");
            TableUtils.createTable(connectionSource, Connection.class);
        } catch (SQLException e) {
            Util.log("No se pudo crear la base de datos: " + e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {

    }

    // Retornamos el nuevo objeto DAO de Connection o el guardado en memoria
    public RuntimeExceptionDao<Connection, Integer> getConnectionDAO() {
        if(connectionDAO == null) {
            connectionDAO = getRuntimeExceptionDao(Connection.class);
        }

        return connectionDAO;
    }

    /*
     * Borramos los datos en memoria
     */
    @Override
    public void close() {
        super.close();
        connectionDAO = null;
    }
}
