package com.quipucamayoc.unmsm.tipocambio;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class pruebaWebScrapping {

	public static TipoCambio ObtenerTipoDeCambioXmes(int dia, int mes, int anio) {
		TipoCambio tcObtenido=new TipoCambio();
		int mesCalendario=seleccionarMesCalendario(mes);
		Calendar calendario2=new GregorianCalendar(anio, mesCalendario, 1);
		int dias=calendario2.getActualMaximum(Calendar.DAY_OF_MONTH);
		Document sunat;
		int indiceAnterior=0;
		try {
			TipoCambio tipoCambio=new TipoCambio();
			
			sunat= Jsoup.connect("http://www.sunat.gob.pe/cl-at-ittipcam/tcS01Alias?mes="+mes+"&anho="+anio+"").get();
			
			Elements listTipo=sunat.select("div>center>table>tbody>tr>td.tne10");
			Elements listDia=sunat.select("div>center>table>tbody>tr>td.H3");
			
			ArrayList<Integer> arregloDia=new ArrayList<Integer>();
			ArrayList<Double> arregloTipo = new ArrayList<Double>();
			ArrayList<Double> arregloTipoCompra=new ArrayList<Double>();
			ArrayList<Double> arregloTipoVenta = new ArrayList<Double>();
			ArrayList<TipoCambio> listTC=new ArrayList<TipoCambio>();
			ArrayList<TipoCambio> showlistTC=new ArrayList<TipoCambio>();

			for (Element listaTipo: listTipo) {
				arregloTipo.add(Double.parseDouble(listaTipo.text()));
			}
			
			for (Element listaDia:listDia){
				arregloDia.add(Integer.parseInt(listaDia.text()));
			}
			
			for(int j=0;j<arregloTipo.size();j++){
				if(j%2==0){
					arregloTipoCompra.add(arregloTipo.get(j));
				}else{
					arregloTipoVenta.add(arregloTipo.get(j));
				}
			}
			
			for(int k=0;k<arregloDia.size();k++){
					TipoCambio tc=new TipoCambio();
					FechaTipoCambio ftc=new FechaTipoCambio();
					ftc.setDia(arregloDia.get(k));
					ftc.setMes(mes);
					ftc.setAnio(anio);
					tc.setFecha(ftc);
					tc.setTipoCompra(arregloTipoCompra.get(k));
					tc.setTipoVenta(arregloTipoVenta.get(k));
					listTC.add(tc);
			}
			
			for (int i = 0; i < dias; i++) {
				TipoCambio tcr=new TipoCambio();
				FechaTipoCambio ftcr=new FechaTipoCambio();
				ftcr.setDia(i+1);
				ftcr.setMes(mes);
				ftcr.setAnio(anio);
				tcr.setFecha(ftcr);
				if(listTC.isEmpty()){
					tcr.setTipoCompra(0.00);
					tcr.setTipoVenta(0.00);
				}else{
					if(buscarEnLista(i+1,listTC)){
						tcr.setTipoCompra(listTC.get(indiceAnterior).getTipoCompra());
						tcr.setTipoVenta(listTC.get(indiceAnterior).getTipoVenta());
						indiceAnterior++;
					}else{
						if(i+1<listTC.get(0).getFecha().getDia()){
							if(mes==1){
								tipoCambio=ObtenerUltimoTC(12,anio-1);
								tcr.setTipoCompra(tipoCambio.getTipoCompra());
								tcr.setTipoVenta(tipoCambio.getTipoVenta());
							}else{
								tipoCambio=ObtenerUltimoTC(mes-1,anio);
								tcr.setTipoCompra(tipoCambio.getTipoCompra());
								tcr.setTipoVenta(tipoCambio.getTipoVenta());
							}
						}else{
							if(!MayorFechaActual(i+1,mes,anio)){
								tcr.setTipoCompra(0.00);
								tcr.setTipoVenta(0.00);
							}else{
								tcr.setTipoCompra(listTC.get(indiceAnterior-1).getTipoCompra());
								tcr.setTipoVenta(listTC.get(indiceAnterior-1).getTipoVenta());
							}
						}
					}
				}
				showlistTC.add(tcr);
			}
			int i=0;
			while(i<showlistTC.size()){
				if(showlistTC.get(i).getFecha().getDia()==dia){
					tcObtenido=showlistTC.get(i);
					break;
				}
				i++;
			}
		} catch (IOException  pex) {
			System.out.println("Error 1: "+pex.toString());
		}
		return tcObtenido;
	}

	private static boolean MayorFechaActual(int dia, int mes, int anio) {
		boolean condicion=false;
		try {
			boolean mayor=false;
			String fechaIN=String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(anio);
			Date fechaActual=new Date();
			SimpleDateFormat formateador=new SimpleDateFormat("dd/MM/yyyy");
			String fechaSistema=formateador.format(fechaActual);
			
			Date fechaInicio = formateador.parse(fechaSistema);
			Date fechaHoy=formateador.parse(fechaIN);
			
			if(fechaInicio.before(fechaHoy)){
				mayor=false;
			}else if(fechaHoy.before(fechaInicio)){
				mayor=true;
			}else if(fechaHoy.equals(fechaInicio)){
				mayor=true;
			}
			 condicion=mayor;
		} catch (ParseException e) {
			System.out.println("Error: "+e.toString());
		}
		return condicion;
	}

	public static int seleccionarMesCalendario(int mes) {
		int MesCalendario;
		switch(mes){
		case 1:
			MesCalendario=Calendar.JANUARY;
			break;
		case 2:
			MesCalendario=Calendar.FEBRUARY;
			break;
		case 3:
			MesCalendario=Calendar.MARCH;
			break;
		case 4:
			MesCalendario=Calendar.APRIL;
			break;
		case 5:
			MesCalendario=Calendar.MAY;
			break;
		case 6:
			MesCalendario=Calendar.JUNE;
			break;
		case 7:
			MesCalendario=Calendar.JULY;
			break;
		case 8:
			MesCalendario=Calendar.AUGUST;
			break;
		case 9:
			MesCalendario=Calendar.SEPTEMBER;
			break;
		case 10:
			MesCalendario=Calendar.OCTOBER;
			break;
		case 11:
			MesCalendario=Calendar.NOVEMBER;
			break;
		case 12:
			MesCalendario=Calendar.DECEMBER;
			break;
		default:
            throw new IllegalArgumentException("Invalidado: " +mes );
		}
		
		return MesCalendario;
	}
	
	public static TipoCambio ObtenerUltimoTC(int mes,int anio) throws IOException{
		Document sunat;
		TipoCambio ultimoTc=new TipoCambio();
			//--Enviando Petición--
			sunat= Jsoup.connect("http://www.sunat.gob.pe/cl-at-ittipcam/tcS01Alias?mes="+mes+"&anho="+anio+"").get();
			Elements listTipo=sunat.select("div>center>table>tbody>tr>td.tne10");
			Elements listDia=sunat.select("div>center>table>tbody>tr>td.H3");
			
			ArrayList<Integer> arregloDia=new ArrayList<Integer>();
			ArrayList<Double> arregloTipo = new ArrayList<Double>();
			ArrayList<Double> arregloTipoCompra=new ArrayList<Double>();
			ArrayList<Double> arregloTipoVenta = new ArrayList<Double>();
			ArrayList<TipoCambio> listTC=new ArrayList<TipoCambio>();

			
			for (Element listaTipo: listTipo) {
				arregloTipo.add(Double.parseDouble(listaTipo.text()));
			}
			
			for (Element listaDia:listDia){
				arregloDia.add(Integer.parseInt(listaDia.text()));
			}
			
			for(int j=0;j<arregloTipo.size();j++){
				if(j%2==0){
					arregloTipoCompra.add(arregloTipo.get(j));
				}else{
					arregloTipoVenta.add(arregloTipo.get(j));
				}
			}
			
			for(int k=0;k<arregloDia.size();k++){
					TipoCambio tipo=new TipoCambio();
					FechaTipoCambio ftc=new FechaTipoCambio();
					ftc.setDia(arregloDia.get(k));
					ftc.setMes(mes);
					ftc.setAnio(anio);
					tipo.setFecha(ftc);
					tipo.setTipoCompra(arregloTipoCompra.get(k));
					tipo.setTipoVenta(arregloTipoVenta.get(k));
					listTC.add(tipo);
			}
			ultimoTc=listTC.get(listTC.size()-1);
		return ultimoTc;
	}
	
	public static boolean buscarEnLista(int dia, ArrayList<TipoCambio> listTC){
		int i=0;
		boolean buscado=false;
		while(i<listTC.size() && !buscado){
			if(listTC.get(i).getFecha().getDia()==dia){
				buscado=true;
				break;
			}
			i++;
		}
		return buscado;
	}
}
