package com.vg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dao {

    private Connection cn;

    public void Conexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Persona;user=sa;password=vallegrande2018");
            System.out.println("Conectado");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void Cerrar() throws SQLException {      //Cerrar la coneccion
        if (cn != null) {
            if (cn.isClosed() == false) {
                cn.close();
            }
        }
    }

//    public static void main(String[] args) {
//        dao dao = new dao();
//        dao.Conexion();
//    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

}
