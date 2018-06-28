package com.vg.controller;

import com.vg.dao.daoPersona;
import com.vg.model.model;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "controller")
@SessionScoped
public class controller implements Serializable {
    
    private PieChartModel pieModel;
    private List<model> lstCantDistCañete;

   @PostConstruct
    public void init() {
        try {
            lstCantDistCañete();
        } catch (Exception ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void lstCantDistCañete() throws Exception {
        daoPersona dao;
        try {
            dao = new daoPersona();
            lstCantDistCañete = dao.lstCantDistCañete();
            graficarCantDistCañete(lstCantDistCañete);
        } catch (SQLException e) {
            throw e;
        }
    }

    //Graficar un grafico estadistico en forma de pie con la información de listaCantAlumXCar()
    public void graficarCantDistCañete(List<model> lista) {
        pieModel = new PieChartModel();
        for (model alu : lstCantDistCañete) {
            pieModel.set(alu.getNomDist(), alu.getCantProv());
        }
        pieModel.setTitle("Porcentaje de Alumnos del Distrito de Cañete");
        pieModel.setLegendPosition("ne");
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public List<model> getLstCantDistCañete() {
        return lstCantDistCañete;
    }

    public void setLstCantDistCañete(List<model> lstCantDistCañete) {
        this.lstCantDistCañete = lstCantDistCañete;
    }
    
}
