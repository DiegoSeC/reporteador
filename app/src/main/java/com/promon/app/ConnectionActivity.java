package com.promon.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.promon.models.Connection;
import com.promon.sqlite.SQLiteManager;

public class ConnectionActivity extends Activity {

    EditText txtServidor;
    EditText txtUser;
    EditText txtPassword;
    Button btnGrabar;

    RuntimeExceptionDao<Connection, Integer> connectionDao;
    private SQLiteManager sm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        txtServidor = (EditText) findViewById(R.id.txtServer);
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnGrabar = (Button) findViewById(R.id.btnGrabar);
        connectionDao = getHelper().getConnectionDAO();
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = new Connection();
                
                connection.setNombre(txtServidor.getText().toString());
                connection.setUser(txtUser.getText().toString());
                connection.setPassword(txtPassword.getText().toString());

                connectionDao.create(connection);
                Toast.makeText(getApplicationContext(), "Conexión agregado", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ConnectionActivity.this, MainActivity.class);
                txtServidor.setText("");
                txtUser.setText("");
                txtPassword.setText("");

                startActivity(intent);
            }
        });
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
