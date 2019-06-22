package com.quipucamayoc.unmsm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quipucamayoc.unmsm.tipocambio.BandFactModel;
import com.quipucamayoc.unmsm.tipocambio.TipoCambio;
import com.quipucamayoc.unmsm.tipocambio.pruebaWebScrapping;

public class ConexionDB {

	private String nombreBD = "QUIPUDES";
	//private String nombreBD = "QUIPUPRO";
	private String login = "WEBQPROTESORERIA";
    private String password = "qaz";
    private String puerto = "1521";
    private String url = "jdbc:oracle:thin:@172.16.179.115:" + puerto + "/" + nombreBD;
    //private String url = "jdbc:oracle:thin:@172.16.156.7:" + puerto + "/" + nombreBD;
    private Connection conexion = null;
    private PreparedStatement pstmt = null;

    public ConexionDB() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conexion = DriverManager.getConnection(url, login, password);

            if (conexion != null) {
                System.out.println("Conexion a base de datos " + url + " ... Ok");
            }
        } catch (Exception ex) {
            System.out.println("Hubo un problema al intentar conectarse con la base de datos " + url);
            ex.printStackTrace();
        }
    }
    public static void main (String [ ] args) {
        ConexionDB c= new ConexionDB();
        try {
            c.ingresarTipoCambio(21, 03, 2017);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void ingresarTipoCambio(int dia, int mes, int anio) throws Exception {

        try {
            //Registrar BD
            TipoCambio prueba = new TipoCambio();
            prueba = pruebaWebScrapping.ObtenerTipoDeCambioXmes(dia, mes, anio);
            String query = "INSERT INTO TIPO_CAMBIO(TIPCAFEC,MONTOC, MONTOV,PROMEDIOC, PROMEDIOV) VALUES (?,?,?,?,?)";
            setPstmt(getConexion().prepareStatement(query));
            getPstmt().setString(1, prueba.getFecha().toDate());
            getPstmt().setDouble(2, prueba.getTipoCompra());
            getPstmt().setDouble(3, prueba.getTipoVenta());
            getPstmt().setDouble(4, 0.00);
            getPstmt().setDouble(5, 0.00);
            getPstmt().executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("error exportando DBF");
            e.printStackTrace();
        } finally {
            if (getPstmt() != null) {
                getPstmt().close();
            }
            getConexion().close();
        }
    }
    public List<BandFactModel> getComprobanteSinEnviar(){
        try {
        	List<BandFactModel> comprobantes=new ArrayList<BandFactModel>();
            Connection conn = getConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT '20148092282' AS NUM_RUC, comprobante.SUNAT_EQ AS TIP_DOCU,  comprobante.INICIAL || comprobante.COD_ESTAB ||'-'|| comprobante.SERIE AS NUM_DOCU, \n"+
            "band_fact.IND_SITU    AS IND_SITU FROM "+
            "(SELECT comp.SERIE,comp.COD_ESTAB,tipo.inicial,tipo.SUNAT_EQ,tipo.ID_TIPO_CPAGO,tipo.\"DESC\" AS DESCR "+
            "FROM WEBQPROTESORERIA.COMPROB_PAGO comp,WEBQPROTESORERIA.TIPO_COMP_PAGO tipo WHERE comp.TIPO=tipo.id_tipo_cpago) comprobante "+           
            "LEFT JOIN WEBQPROTESORERIA.TXXXX_BANDFACT band_fact ON (band_fact.NUM_DOCU = comprobante.inicial || comprobante.COD_ESTAB || '-' || comprobante.SERIE AND band_fact.TIP_DOCU=comprobante.SUNAT_EQ) "+
            "WHERE decode(band_fact.IND_SITU,'','01',band_fact.IND_SITU) !='03' AND decode(band_fact.IND_SITU,'','01',band_fact.IND_SITU) !='04' "+
            "AND decode(band_fact.IND_SITU,'','01',band_fact.IND_SITU) !='05'");
            while ( rs.next() ) {
            	BandFactModel comprobante= new BandFactModel();
            	comprobante.setNum_ruc(rs.getString("NUM_RUC"));
            	comprobante.setTip_doc(rs.getString("TIP_DOCU"));
            	comprobante.setNum_doc(rs.getString("NUM_DOCU"));
            	comprobante.setInd_situ(rs.getString("IND_SITU"));
            	comprobantes.add(comprobante);
            }
            //conn.close();
            return comprobantes;
        } catch (Exception e) {        	
        	return null;
        }    	
    }
    public void updateComprobantesEnviados(BandFactModel comprobante){
    	String query = "UPDATE TXXXX_BANDFACT SET IND_SITU = '03' WHERE NUM_RUC=? AND TIP_DOCU=? AND NUM_DOCU=?";   
    	PreparedStatement ps=null;
    	Connection conn=null;
        try {
        	conn = getConexion();
            ps = conn.prepareStatement(query);
            ps.setString(1,comprobante.getNum_ruc());
            ps.setString(2,comprobante.getTip_doc());
            ps.setString(3,comprobante.getNum_doc());
            ps.executeUpdate();
        } catch (SQLException  e) {
        	System.out.println(e.getMessage());
        } finally {
            if (ps!= null) {
                try {
                	ps.close();
				} catch (SQLException e) {
				}
            }
        }    	
    }
    public void updateComprobantesRechazados(BandFactModel comprobante){
    	String query = "UPDATE TXXXX_BANDFACT SET IND_SITU = '05' WHERE NUM_RUC=? AND TIP_DOCU=? AND NUM_DOCU=?";   
    	PreparedStatement ps=null;
    	Connection conn=null;
        try {
        	conn = getConexion();
            ps = conn.prepareStatement(query);
            ps.setString(1,comprobante.getNum_ruc());
            ps.setString(2,comprobante.getTip_doc());
            ps.setString(3,comprobante.getNum_doc());
            ps.executeUpdate();
        } catch (SQLException  e) {
        	System.out.println(e.getMessage());
        } finally {
            if (ps!= null) {
                try {
                	ps.close();
				} catch (SQLException e) {
				}
            }
        }    	
    }    
    /**
     * @return the nombreBD
     */
    public String getNombreBD() {
        return nombreBD;
    }

    /**
     * @param nombreBD the nombreBD to set
     */
    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the puerto
     */
    public String getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the conexion
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * @param conexion the conexion to set
     */
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * @return the pstmt
     */
    public PreparedStatement getPstmt() {
        return pstmt;
    }

    /**
     * @param pstmt the pstmt to set
     */
    public void setPstmt(PreparedStatement pstmt) {
        this.pstmt = pstmt;
    }
}
