package com.vg.dao;

import com.vg.model.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class daoPersona extends dao {

    public List<model> lstCantDistCañete() throws SQLException {
        List<model> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT \n"
                    + "UBIGEO.DIST AS DISTRITO,\n"
                    + "COUNT(PERSONATEMP.NOM_EST) AS COUNTERE\n"
                    + "FROM PERSONATEMP\n"
                    + "INNER JOIN UBIGEO ON PERSONATEMP.UBIGEO_EST = UBIGEO.UBIGEO\n"
                    + "WHERE UBIGEO.PROV = 'CA�ETE' \n"
                    + "GROUP BY UBIGEO.DIST\n"
                    + "ORDER BY COUNTERE DESC";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            model emp;
            while (rs.next()) {
                emp = new model();
                emp.setNomDist(rs.getString("DISTRITO"));
                emp.setCantProv(rs.getInt("COUNTERE"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;

    }
}
