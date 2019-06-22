package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import pe.edu.unmsm.quipucamayoc.model.ComprobanteTicketModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.MaquinaRegistradoraModel;
import pe.edu.unmsm.quipucamayoc.model.RangoFechaDependenciaModel;
import pe.edu.unmsm.quipucamayoc.util.Constantes;

public interface ComprobanteTicketPersistence {
	
	@Select(value = "select ct.COD_ESTAB_ANEXO anexo, "+
        "ct.TOTAL total, "+
        "ct.FORMA_PAGO formaPago, "+
        "ct.MONEDA moneda, "+
        "ct.IMPORTE_OPERACION importeOperacion, "+
        "ct.SUBTOTAL subtotal, "+
        "ct.IGV igv, "+
        "ct.UNIDAD unidad, "+
        "ct.FACULTAD facultad, "+
        "ct.USUARIO usuario, "+
        "ct.SERIE_FAB_MAQ serieFabMaq, "+
        "ct.NRO_AUTORIZACION_MAQ nroAutoMaq, "+
        "ct.DIRECCION direccion, "+
        "ml.CODIGO_ESTAB codEstab, "+
        "ml.NOMB_CORTO nomCorto, "+
        "ct.CORRELATIVO correlativo, "+
        "ct.FECHA_EMISION fechaEmision, "+
        "ct.ESTADO estado "+
        "from WEBQPROTESORERIA.COMP_TICKET ct inner join WEBQPROTESORERIA.MAQ_REGISTRADORA mr "+
        "on ct.COD_ESTAB_ANEXO=mr.CODIGO_ESTAB_ANEXO inner join WEBQPROTESORERIA.MAQ_LOCAL ml "+
        "on ml.UD_ID=mr.UD_ID "+
        "where "+
        	"ml.NOMB_CORTO=ct.NOMB_CORTO_LOCAL and "+
        	"mr.CODIGO_ESTAB_ANEXO=ct.COD_ESTAB_ANEXO and "+
        	"mr.SERIE_FABRICACION=ct.SERIE_FAB_MAQ and "+
	        "mr.UD_ID=#{rango.udId} and "+
	        "ct.FECHA_EMISION BETWEEN TO_DATE(#{rango.fechaInicial}, 'dd/mm/yyyy hh24:mi:ss') and "+
	        "TO_DATE(#{rango.fechaFinal} ,'dd/mm/yyyy hh24:mi:ss')")
	@Results(value = { @Result(property = "anexo", column = "anexo"), @Result(property = "total", column = "total"),
			@Result(property = "formaPago", column = "formaPago"), @Result(property = "moneda", column = "moneda"),
			@Result(property = "importeOperacion", column = "importeOperacion"), @Result(property = "subtotal", column = "subtotal"),
			@Result(property = "igv", column = "igv"),
			@Result(property = "unidad", column = "unidad"), @Result(property = "facultad", column = "facultad"),
			@Result(property = "usuario", column = "usuario"), @Result(property = "serieFabMaq", column = "serieFabMaq"),
			@Result(property = "nroAutoMaq", column = "nroAutoMaq"),
			@Result(property = "direccion", column = "direccion"),
			@Result(property = "codEstab", column = "codEstab"), @Result(property = "nomCorto", column = "nomCorto"),
			@Result(property = "correlativo", column = "correlativo"),
			@Result(property = "fechaEmision", column = "fechaEmision"),
			@Result(property = "estado", column = "estado")})
	List<ComprobanteTicketModel> comprobanteTicket(@Param("rango") RangoFechaDependenciaModel rango);
	
	@Select(value = "select mr.MARCA marca, "+
	        "mr.MODELO modelo, "+
	        "mr.SERIE_FABRICACION serieFabricacion, "+
	        "ml.NOMB_CORTO nombCortoLocal, "+
	        "mr.NRO_AUTORIZACION numeroAutorizacion, "+
	        "ml.DIRECCION direccion, "+
	        "TO_CHAR(SYSDATE,'DD/MM/YYYY') fechaSistema "+
	        "from WEBQPROTESORERIA.MAQ_REGISTRADORA mr "+
	        "inner join WEBQPROTESORERIA.MAQ_LOCAL ml "+
	        "on mr.UD_ID=ml.UD_ID "+
	        "where "+
	        	"mr.UD_ID=#{udId} ")
		@Results(value = { 
				@Result(property = "marca", column = "marca"), 
				@Result(property = "modelo", column = "modelo"),
				@Result(property = "serieFabricacion", column = "serieFabricacion"),
				@Result(property = "nombCortoLocal", column = "nombCortoLocal"),
				@Result(property = "numeroAutorizacion", column = "numeroAutorizacion"),
				@Result(property = "direccion", column = "direccion"),
				@Result(property = "fechaSistema", column = "fechaSistema")})
		List<MaquinaRegistradoraModel> getMaqRegistradora(@Param("udId") Integer udId);
	
	@Insert(value = "{CALL INSERTAR_TICKET (\n"
			+ "#{i.anexo, mode=IN, jdbcType=VARCHAR},\n" 
			+ "#{i.nomCorto, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.total, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.formaPago, mode=IN, jdbcType=VARCHAR},\n" 
			+ "#{i.moneda, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.importeOperacion, mode=IN, jdbcType=VARCHAR},\n" 
			+ "#{i.subtotal, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.igv, mode=IN, jdbcType=INTEGER},\n" 
			+ "#{i.unidad, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.facultad, mode=IN, jdbcType=VARCHAR},\n" 
			+ "#{usuario, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.serieFabMaq, mode=IN, jdbcType=VARCHAR},\n" 
			+ "#{i.nroAutoMaq, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.direccion, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.correlativo, mode=OUT, jdbcType=VARCHAR})}\n")
	@Options(statementType = StatementType.CALLABLE)
	void insertarTicket(@Param("i") ComprobanteTicketModel item, @Param("usuario") String usuario);
	
	@Insert(value = "{CALL INSERTAR_DET_TICKET(\n"
			+ "#{anexo, mode=IN, jdbcType=VARCHAR},\n" 
			+ "#{nombCorto, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{correlativo, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.codigo, mode=IN, jdbcType=VARCHAR},\n" 
			+ "#{i.cantidad, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.descripcion, mode=IN, jdbcType=VARCHAR},\n" 
			+ "#{i.precioU, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.precioT, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.moneda, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.igvU, mode=IN, jdbcType=INTEGER},\n" 
			+ "#{i.igvT, mode=IN, jdbcType=INTEGER},\n"
			+ "#{serieFabMaq, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	void ingresarDetalle(@Param("anexo") String anexo,@Param("nombCorto") String nombCorto, @Param("correlativo") String correlativo,@Param("serieFabMaq") String serieFabMaq, @Param("i") DetalleFacturaModel item);
	
	@Update(value="{CALL ANULAR_TICKET("
			+"#{codEstabAnexo, mode=IN, jdbcType=VARCHAR},"
			+"#{correlativo, mode=IN, jdbcType=VARCHAR}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void anularTicket(@Param("codEstabAnexo") String codEstabAnexo, @Param("correlativo") String correlativo);	

	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.MAQ_REGISTRADORA.UD_ID,\n"
			+ "WEBQPROTESORERIA.MAQ_REGISTRADORA.CODIGO_ESTAB_ANEXO,\n"
			+ "WEBQPROTESORERIA.MAQ_REGISTRADORA.ULT_CORRELATIVO,\n"
			+ "QPRODATAQUIPU.UNI_DEP.UD_COD,\n"
			+ "QPRODATAQUIPU.UNI_DEP.UD_DSC,\n" 
			+ "QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL,\n"
			+ "WEBQPROTESORERIA.FACULTAD.UD_DSC as FACULTAD\n" 
			+ Constantes.FROM + "WEBQPROTESORERIA.FACULTAD,\n"
			+ "WEBQPROTESORERIA.MAQ_REGISTRADORA\n"
			+ "INNER JOIN QPRODATAQUIPU.UNI_DEP ON WEBQPROTESORERIA.MAQ_REGISTRADORA.UD_ID = QPRODATAQUIPU.UNI_DEP.UD_ID\n"
			+ "INNER JOIN QPRODATAQUIPU.TB_HIST_USU_PERF ON QPRODATAQUIPU.TB_HIST_USU_PERF.UD_ID = QPRODATAQUIPU.UNI_DEP.UD_ID\n"
			+ Constantes.WHERE + "QPRODATAQUIPU.TB_HIST_USU_PERF.MODCOD = 3 AND\n" + "(PERF_COD = 707) AND\n"
			+ "QPRODATAQUIPU.TB_HIST_USU_PERF.UD_ID = WEBQPROTESORERIA.MAQ_REGISTRADORA.UD_ID AND\n"
			+ "WEBQPROTESORERIA.MAQ_REGISTRADORA.ESTADO = 1 AND\n" + "QPRODATAQUIPU.TB_HIST_USU_PERF.EST = 1 AND\n"
			+ "QPRODATAQUIPU.UNI_DEP.UNINIV2 = WEBQPROTESORERIA.FACULTAD.UD_ID AND\n"
			+ "QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL = #{correo}")
	@Results(value = { @Result(property = "idUnidad", column = "UD_ID"),
			@Result(property = "nEstablecimiento", column = "CODIGO_ESTAB_ANEXO"),
			@Result(property = "ultTicket", column = "ULT_CORRELATIVO"),
			@Result(property = "codUnidad", column = "UD_COD"), 
			@Result(property = "descripcion", column = "UD_DSC"),
			@Result(property = "usuario", column = "T_MAIL"), 
			@Result(property = "facultad", column = "FACULTAD") })
	List<ComprobanteUsuarioModel> listarUnidadesTicket(@Param("correo") String correo);

}
