package com.quipucamayoc.unmsm.quartz;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.quipucamayoc.unmsm.db.ConexionDB;

public class TipoCambioJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        ConexionDB conexion = new ConexionDB();
        try {
            Calendar fechaHoy = Calendar.getInstance();
            int dia = fechaHoy.get(Calendar.DATE);
            int mes = fechaHoy.get(Calendar.MONTH) + 1;
            int anio = fechaHoy.get(Calendar.YEAR);
            conexion.ingresarTipoCambio(dia, mes, anio);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println("Tipo de Cambio registrado con éxito!");
        System.out.println(Calendar.getInstance().toString());
    }
}
