package com.promon.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Modelo para la Conecciones de Base de Datos
 * Created by Diego on 10/07/14.
 */

@DatabaseTable(tableName = "connection")
public class Connection {

    @DatabaseField(id = true)
    private int id;

    @DatabaseField(dataType = DataType.STRING)
    private String nombre;

    @DatabaseField(dataType = DataType.STRING)
    private String user;

    @DatabaseField(dataType = DataType.STRING)
    private String password;

    public Connection() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
