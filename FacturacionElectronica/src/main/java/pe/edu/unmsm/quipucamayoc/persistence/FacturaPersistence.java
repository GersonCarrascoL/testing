
package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.edu.unmsm.quipucamayoc.model.BancoModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.FacturaModel;
import pe.edu.unmsm.quipucamayoc.model.FormaPagoModel;
import pe.edu.unmsm.quipucamayoc.model.PrecioCambioModel;
import pe.edu.unmsm.quipucamayoc.model.TipoImpuestoModel;
import pe.edu.unmsm.quipucamayoc.model.UnidadModel;
import pe.edu.unmsm.quipucamayoc.util.Constantes;

public interface FacturaPersistence {

	@Insert(value = "{CALL INSERTAR_COMPROBANTE (\n" + "#{i.anio, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.mes, mode=IN, jdbcType=INTEGER},\n" + "#{i.tipo, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.establecimiento, mode=IN, jdbcType=VARCHAR},\n" + "#{i.serie, mode=OUT, jdbcType=VARCHAR},\n"
			+ "TO_DATE(#{i.fecha}, 'dd/mm/yyyy hh24:mi:ss'),\n" + "#{i.documento, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.nombre, mode=IN, jdbcType=VARCHAR},\n" + "#{i.direccion, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.guia, mode=IN, jdbcType=VARCHAR},\n" + "#{i.sub, mode=IN, jdbcType=DOUBLE},\n"
			+ "#{i.igv, mode=IN, jdbcType=DOUBLE},\n" + "#{i.total, mode=IN, jdbcType=DOUBLE},\n"
			+ "#{i.formaPago, mode=IN, jdbcType=VARCHAR},\n" + "#{i.moneda, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.importe, mode=IN, jdbcType=VARCHAR},\n" + "#{i.unidad, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.facultad, mode=IN, jdbcType=VARCHAR},\n" + "#{usuario, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.estado, mode=IN, jdbcType=VARCHAR},\n" + "#{i.estUso, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.tipoDoc, mode=IN, jdbcType=VARCHAR},\n" + "#{i.codUbigeoCliente, mode=IN, jdbcType=CHAR},\n"
			+ "PCK_UTIL.ALETRAS(#{i.total}),\n" + "#{detraccion, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.gravada, mode=IN, jdbcType=DOUBLE},\n" + "#{i.exonerada, mode=IN, jdbcType=DOUBLE},\n"
			+ "#{i.inafecta, mode=IN, jdbcType=DOUBLE},\n"
			+ "#{i.observacion, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	void ingresar(@Param("i") FacturaModel item, @Param("usuario") String usuario, @Param("detraccion") int detraccion);

	@Insert("insert into DET_COMPB_PAGO (COD_ESTAB,SERIE,ID_ITEM,CANTIDAD,DESCRIPCION,PRECIO_U,PRECIO_T,MONEDA,IGV_U,IGV_T,TIPO,COD_PROD_SUNAT,DETRACCION,COD_TIPO_IGV,UNI_MEDIDA) values (#{establecimiento},#{serie},#{i.codigo},#{i.cantidad},#{i.descripcion},ROUND(#{i.precioU},2),ROUND(#{i.precioT},2),#{i.moneda},ROUND(#{i.igvU},2),ROUND(#{i.igvT},2),#{tipo},#{i.codProductoSUNAT, jdbcType=VARCHAR},#{i.codDetraccion},#{i.codTipoIgv},#{i.nUnidadMedida})")
	void ingresarDetalle(@Param("establecimiento") String establecimiento, @Param("serie") String serie,
			@Param("tipo") Integer tipo, @Param("i") DetalleFacturaModel item);

	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.BANCO.BANCOD,\n" + "WEBQPROTESORERIA.BANCO.BANRAZSOC\n" + Constantes.FROM
			+ "WEBQPROTESORERIA.BANCO \n" + "ORDER BY BANCO.BANCOD")
	@Results(value = { @Result(property = "codigo", column = "BANCOD"),
			@Result(property = "nombre", column = "BANRAZSOC") })
	List<BancoModel> listarBancos();

	@Select(value = "SELECT * FROM WEBQPROTESORERIA.FORMA_PAGO")
	@Results(value = { @Result(property = "id", column = "ID_FORMA"),
			@Result(property = "descripcion", column = "DESC_FORMA") })
	List<FormaPagoModel> listarFormasPago();

	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.COMPROB_PAGO.MONTO_LETRAS\n" + Constantes.FROM + "WEBQPROTESORERIA.COMPROB_PAGO\n"
			+ Constantes.WHERE + "WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB = #{establecimiento} AND\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.SERIE = #{serie} AND\n" + "WEBQPROTESORERIA.COMPROB_PAGO.TIPO = #{tipo}")
	String montoLetras(@Param("tipo") Integer tipo, @Param("establecimiento") String establecimiento,
			@Param("serie") String serie);

	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CODIGO_ESTAB,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.SUNAT,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_FACT,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_BOLE,\n" + "QPRODATAQUIPU.UNI_DEP.UD_COD,\n"
			+ "QPRODATAQUIPU.UNI_DEP.UD_DSC,\n" + "QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL,\n"
			+ "WEBQPROTESORERIA.FACULTAD.UD_DSC as FACULTAD\n" + Constantes.FROM + "WEBQPROTESORERIA.FACULTAD,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO\n"
			+ "INNER JOIN QPRODATAQUIPU.UNI_DEP ON WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID = QPRODATAQUIPU.UNI_DEP.UD_ID\n"
			+ "INNER JOIN QPRODATAQUIPU.TB_HIST_USU_PERF ON QPRODATAQUIPU.TB_HIST_USU_PERF.UD_ID = QPRODATAQUIPU.UNI_DEP.UD_ID\n"
			+ Constantes.WHERE + "QPRODATAQUIPU.TB_HIST_USU_PERF.MODCOD = 3 AND\n" + "(PERF_COD = 703 OR PERF_COD = 706) AND\n"
			+ "QPRODATAQUIPU.TB_HIST_USU_PERF.UD_ID = WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID AND\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ESTADO = 1 AND\n" + "QPRODATAQUIPU.TB_HIST_USU_PERF.EST = 1 AND\n"
			+ "QPRODATAQUIPU.UNI_DEP.UNINIV2 = WEBQPROTESORERIA.FACULTAD.UD_ID AND\n"
			+ "QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL = #{correo} AND\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ESTADO = 1")
	@Results(value = { @Result(property = "idUnidad", column = "UD_ID"),
			@Result(property = "nEstablecimiento", column = "CODIGO_ESTAB"),@Result(property = "sunat", column = "SUNAT"),
			@Result(property = "ultFact", column = "ULT_FACT"), @Result(property = "ultBole", column = "ULT_BOLE"),
			@Result(property = "codUnidad", column = "UD_COD"), @Result(property = "descripcion", column = "UD_DSC"),
			@Result(property = "usuario", column = "T_MAIL"), @Result(property = "facultad", column = "FACULTAD") })
	List<ComprobanteUsuarioModel> listarUnidades(@Param("correo") String correo);
	
	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.UNIDAD_ESTAB_TICKET.UD_ID,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTAB_TICKET.CODIGO_ESTAB,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTAB_TICKET.ULT_TICKET,\n"
			+ "QPRODATAQUIPU.UNI_DEP.UD_COD,\n"
			+ "QPRODATAQUIPU.UNI_DEP.UD_DSC,\n" 
			+ "QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL,\n"
			+ "WEBQPROTESORERIA.FACULTAD.UD_DSC as FACULTAD\n" 
			+ Constantes.FROM + "WEBQPROTESORERIA.FACULTAD,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTAB_TICKET\n"
			+ "INNER JOIN QPRODATAQUIPU.UNI_DEP ON WEBQPROTESORERIA.UNIDAD_ESTAB_TICKET.UD_ID = QPRODATAQUIPU.UNI_DEP.UD_ID\n"
			+ "INNER JOIN QPRODATAQUIPU.TB_HIST_USU_PERF ON QPRODATAQUIPU.TB_HIST_USU_PERF.UD_ID = QPRODATAQUIPU.UNI_DEP.UD_ID\n"
			+ Constantes.WHERE + "QPRODATAQUIPU.TB_HIST_USU_PERF.MODCOD = 3 AND\n" + "(PERF_COD = 703 OR PERF_COD = 706) AND\n"
			+ "QPRODATAQUIPU.TB_HIST_USU_PERF.UD_ID = WEBQPROTESORERIA.UNIDAD_ESTAB_TICKET.UD_ID AND\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTAB_TICKET.ESTADO = 1 AND\n" + "QPRODATAQUIPU.TB_HIST_USU_PERF.EST = 1 AND\n"
			+ "QPRODATAQUIPU.UNI_DEP.UNINIV2 = WEBQPROTESORERIA.FACULTAD.UD_ID AND\n"
			+ "QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL = #{correo}")
	@Results(value = { @Result(property = "idUnidad", column = "UD_ID"),
			@Result(property = "nEstablecimiento", column = "CODIGO_ESTAB"),
			@Result(property = "ultTicket", column = "ULT_TICKET"),
			@Result(property = "codUnidad", column = "UD_COD"), 
			@Result(property = "descripcion", column = "UD_DSC"),
			@Result(property = "usuario", column = "T_MAIL"), 
			@Result(property = "facultad", column = "FACULTAD") })
	List<ComprobanteUsuarioModel> listarUnidadesTicket(@Param("correo") String correo);
	
	

	@Select(value = Constantes.SELECT + "QPRODATAQUIPU.UNI_DEP.UD_ID,\n" + "QPRODATAQUIPU.UNI_DEP.UD_COD,\n"
			+ "QPRODATAQUIPU.UNI_DEP.UD_DSC,\n" + "QPRODATAQUIPU.UNI_DEP.UD_PAD,\n"
			+ "QPRODATAQUIPU.UNI_DEP.NIVUNICOD,\n" + "QPRODATAQUIPU.UNI_DEP.UNIDEPCALDEP\n" + Constantes.FROM
			+ "QPRODATAQUIPU.UNI_DEP\n" + Constantes.WHERE + "QPRODATAQUIPU.UNI_DEP.UD_COD = #{udCod}")
	@Results(value = { @Result(property = "udId", column = "UD_ID"), @Result(property = "udCod", column = "UD_COD"),
			@Result(property = "udDsc", column = "UD_DSC"), @Result(property = "udPad", column = "UD_PAD"),
			@Result(property = "nivunicod", column = "NIVUNICOD"),
			@Result(property = "unidepcaldep", column = "UNIDEPCALDEP") })
	UnidadModel unidad(@Param("udCod") String udCod);

	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.TIPO_CAMBIO.MONTOC\n" + "FROM WEBQPROTESORERIA.TIPO_CAMBIO\n" + Constantes.WHERE
			+ "WEBQPROTESORERIA.TIPO_CAMBIO.TIPCAFEC = TO_DATE(#{hoy}, 'yyyy-mm-dd')")
	@Results(value = { @Result(property = "precioCambio", column = "MONTOC") })
	PrecioCambioModel precioCambio(@Param("hoy") String hoy);

	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.TIPO_IMPUESTO.TIPIMPCOD,\n"
			+ "WEBQPROTESORERIA.TIPO_IMPUESTO.TIPIMPCOM,\n" + "WEBQPROTESORERIA.TIPO_IMPUESTO.TIPIMPDES,\n"
			+ "WEBQPROTESORERIA.TIPO_IMPUESTO.IMPPOR\n" + Constantes.FROM + "WEBQPROTESORERIA.TIPO_IMPUESTO\n" + Constantes.WHERE
			+ "WEBQPROTESORERIA.TIPO_IMPUESTO.TIPIMPDES = #{tipo}")
	@Results(value = { @Result(property = "codigo", column = "TIPIMPCOD"),
			@Result(property = "descripcion", column = "TIPIMPCOM"),
			@Result(property = "abreviatura", column = "TIPIMPDES"), @Result(property = "monto", column = "IMPPOR") })
	List<TipoImpuestoModel> listarTipoImpuesto(@Param("tipo") String tipo);

}
