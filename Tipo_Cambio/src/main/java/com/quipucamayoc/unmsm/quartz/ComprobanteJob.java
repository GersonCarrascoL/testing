package com.quipucamayoc.unmsm.quartz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.quipucamayoc.unmsm.db.ConexionDB;
import com.quipucamayoc.unmsm.tipocambio.BandFactModel;

import sun.font.FontUtilities;

public class ComprobanteJob implements Job{
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try{
			ConexionDB conexion = new ConexionDB();
			List<BandFactModel> comprobanteSinEnviar=null;
			comprobanteSinEnviar=conexion.getComprobanteSinEnviar();
			if(comprobanteSinEnviar!=null){
				for(BandFactModel comprobante : comprobanteSinEnviar){
					String response=consultarComprobante(comprobante);
					if(response.equals("0011")){
						System.out.println("comprobante a enviar:"+comprobante.getNum_doc());
						enviarComprobante(comprobante);						
					}else
						if(response.equals("0001")){
							System.out.println("comprobante para actualizar aprobado:"+comprobante.getNum_doc());
							conexion.updateComprobantesEnviados(comprobante);
						}else{
							if(response.equals("0002")){
								System.out.println("comprobante para actualizar rechazado:"+comprobante.getNum_doc());
								conexion.updateComprobantesRechazados(comprobante);
							}
						}
				}				
			}
			conexion.getConexion().close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}
	
	private void enviarComprobante(BandFactModel comprobante){
		  try {
			   String urlSistema;
				if(FontUtilities.isWindows){
					urlSistema="http://localhost:8080/FacturadorSunat/enviarFactura.htm";
				}else{
					urlSistema="http://quipucamayoc.unmsm.edu.pe/FacturadorSunat/enviarFactura.htm";
				}
				URL url = new URL(urlSistema);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");

				String input = "{\"num_doc\":\""+comprobante.getNum_doc()+"\"";
				input=input+",\"num_ruc\":\""+comprobante.getNum_ruc()+"\"";
				input=input+",\"tip_doc\":\""+comprobante.getTip_doc()+"\"}";
				
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();

//				if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//					throw new RuntimeException("Failed : HTTP error code : "
//						+ conn.getResponseCode());
//				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				String output;
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				conn.disconnect();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			 }		
	}
	public static void main(String[] args) {
		BandFactModel bandFactModel= new BandFactModel();
		bandFactModel.setNum_doc("B026-00000146");
		bandFactModel.setNum_ruc("20148092282");
		bandFactModel.setTip_doc("03");
		ComprobanteJob c= new ComprobanteJob();
		String ejemplo =c.consultarComprobante(bandFactModel);
		System.out.println(ejemplo);
	}
	
	private String consultarComprobante(BandFactModel comprobante){
		  try {
			   String urlSistema;
				if(FontUtilities.isWindows){
					urlSistema="http://localhost:8080/FacturadorSunat/consultarComprobante.htm";
				}else{
					urlSistema="http://quipucamayoc.unmsm.edu.pe/FacturadorSunat/consultarComprobante.htm";
				}
				URL url = new URL(urlSistema);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");

				String input = "{\"num_doc\":\""+comprobante.getNum_doc()+"\"";
				input=input+",\"num_ruc\":\""+comprobante.getNum_ruc()+"\"";
				input=input+",\"tip_doc\":\""+comprobante.getTip_doc()+"\"}";
				
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
				String output=br.readLine();
				JSONObject jObject  = new JSONObject(output);
				String projecname=(String) jObject.get("status");			
				conn.disconnect();
				return projecname;				
			  } catch (MalformedURLException e) {
				return "0000";
			  } catch (IOException e) {
				return "0000";
			 }	
	}

}
