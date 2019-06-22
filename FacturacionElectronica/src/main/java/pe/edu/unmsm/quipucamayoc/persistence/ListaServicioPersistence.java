package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import pe.edu.unmsm.quipucamayoc.model.ListaServiciosModel;

public interface ListaServicioPersistence {
	
	String PROP_CODITEM = "codigo";
	String PROP_UDID = "udId";
	String PROP_UDCOD = "udCod";
	String PROP_UDDESC = "udDesc";
	String PROP_NUNIDADMEDIDA = "nUnidadMedida";
	String PROP_UNIDADMEDIDADESC = "unidadMedidaDesc";
	String PROP_DESCRIPCION = "descripcion";
	String PROP_PRECIO = "precio";
	String PROP_IDMONEDA = "idMoneda";
	String PROP_MONEDASIMB = "monedaSimb";
	String PROP_ESTADO = "estado";
	String PROP_CODIGOCATALOGO = "codigoCatalogo";
	String PROP_CODTIPOOPERACIONIGV = "codTipoperacionIgv";
	String PROP_DESCTIPOIGV = "descTipoIgv";
	String PROP_POSEEDESTRACCION = "poseeDetraccion";
	String PROP_PORCENTDETRACCION = "porcentDetraccion";	
	String PROP_IDSERVCATA = "idServCata";
	String PROP_DESCCATEGORIASERV = "descCategoriaServ";
	String PROP_RESOLDECANAL = "resolDecanal";
	String PROP_RESOLDIRECTORAL = "resolDirectoral";
	String PROP_RESOLRECTORAL = "resolRectoral";
	String PROP_IDUNIDADCONCEPTO = "idUnidadConcepto";
	String PROP_CODIGOCONCEPTO6DIGITOS= "codigoConcepto6digitos";
	String PROP_DESCRUNDIADCONCEPTO= "descrUnidadConcepto";
	String PROP_NUMRESOLUCION= "numResolucion";
	String PROP_TIPORESOLUCION= "tipoResolucion";
	String PROP_NAMETIPORESOL= "nameTipoResol";
	String PROP_NIVELTIPORESOL= "nivelTipoResol";
	
	String COL_CODITEM = "CODIGO_ITEM";
	String COL_UDID= "UD_ID";
	String COL_UDCOD= "UD_COD";
	String COL_UDDESC= "UD_DESCRIP";
	String COL_NUNIDADMEDIDA = "UNIDAD_MEDIDA";
	String COL_UNIDADMEDIDADESC = "UNIDAD_MEDIDA_DESC";
	String COL_DESCRIPCION = "DESCRIP_ITEM";
	String COL_PRECIO = "PRECIO_UNITARIO";
	String COL_IDMONEDA = "ID_MONEDA";
	String COL_MONEDASIMB = "SIMB_MONEDA";
	String COL_ESTADO = "ESTADO_ITEM";
	String COL_CODIGOCATALOGO = "CODIGO_CATALOGO";	
	String COL_CODTIPOOPERACIONIGV = "COD_TIPO_IGV";
	String COL_DESCTIPOIGV = "DESC_TIPO_IGV";
	String COL_POSEEDESTRACCION = "poseeDetraccion";
	String COL_PORCENTDETRACCION = "porcentDetraccion";
	String COL_IDSERVCATA = "id_serv_cata";
	String COL_DESCCATEGORIASERV = "desc_categoria_serv";
	String COL_RESOLDECANAL = "RESOL_DECANAL";
	String COL_RESOLDIRECTORAL = "RESOL_DIRECTORAL";
	String COL_RESOLRECTORAL = "RESOL_RECTORAL";
	String COL_IDUNIDADCONCEPTO = "idUnidadConcepto";
	String COL_CODIGOCONCEPTO6DIGITOS= "codigoConcepto6digitos";
	String COL_DESCRUNDIADCONCEPTO= "descrUnidadConcepto";	
	String COL_NUMRESOLUCION= "numResolucion";
	String COL_TIPORESOLUCION= "tipoResolucion";
	String COL_NAMETIPORESOL= "nameTipoResol";
	String COL_NIVELTIPORESOL= "nivelTipoResol";
	
	
    String GET_SERVICIOS_QUERY = "SELECT LP.COD_ITEM AS CODIGO_ITEM, LP.UD_ID AS UD_ID, " +
								" DEP.UD_COD AS UD_COD, " +
								" DEP.UD_DSC AS UD_DESCRIP, " +
								" LP.DESCRIPCION AS DESCRIP_ITEM, " +
								" LP.PRECIO AS PRECIO_UNITARIO, " +
								" LP.ID_MONEDA AS ID_MONEDA, " +
								" LP.N_UNIDAD_MEDIDA AS UNIDAD_MEDIDA, " +
								" UNIMED.UNIMEDDES AS UNIDAD_MEDIDA_DESC, " +
								" MON.MONEDA AS DESCRIP_MONEDA, " +
								" MON.SIMB AS SIMB_MONEDA, " +
								" LP.EST AS ESTADO_ITEM, " +
								" LP.ID_CATA AS CODIGO_CATALOGO, " + 
								" LP.COD_TIPOPERACION_IGV AS COD_TIPO_IGV, " +
								" TOI.DESC_TIPO AS DESC_TIPO_IGV, " +
								" LP.POSEE_DETRACCION AS poseeDetraccion, " +
								" LP.COD_CATA_DETRAC AS codDetraccion, " +
								" LP.PORCENT_DETRAC AS porcentDetraccion, " +
								" LP.ID_SERV_CATA AS id_serv_cata, " +
								" SCAT.DESC_CATEGORIA AS desc_categoria_serv, " +
								" LP.RESOLUCION_DECANAL AS RESOL_DECANAL, " +
								" LP.RESOLUCION_DIRECTORAL AS RESOL_DIRECTORAL, " +
								" LP.RESOLUCION_RECTORAL AS RESOL_RECTORAL, " +
								" LP.ID_UNIDAD_CONCEPTO AS idUnidadConcepto, " +
								" COD.CODIGO_UNIDAD || CPAGO.COD_CPAGO AS codigoConcepto6digitos, " +
								" CPU.DESCR AS descrUnidadConcepto, " +
								" LP.NUM_RESOLUCION AS numResolucion, " +
								" LP.TIPO_RESOLUCION AS tipoResolucion, " +
								" TRS.NAME_TIPO_RESOL AS nameTipoResol, " +
								" TRS.NIVEL AS nivelTipoResol " +
								" FROM WEBQPROTESORERIA.LISTA_ITEM_SERVICIO LP " +
								" INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON (DEP.UD_ID = LP.UD_ID) " +
								" INNER JOIN MONEDA MON ON (MON.ID_MONEDA = LP.ID_MONEDA) " +
								" INNER JOIN WEBQPROTESORERIA.UNI_MED_ART UNIMED ON (UNIMED.UNIMEDCOD = LP.N_UNIDAD_MEDIDA) " +
								" INNER JOIN WEBQPROTESORERIA.TIPO_OPERACION_IGV TOI ON LP.COD_TIPOPERACION_IGV = TOI.COD_TIPO "+
								" LEFT JOIN WEBQPROTESORERIA.SERVICIO_CATEGORIA SCAT ON LP.ID_SERV_CATA = SCAT.ID_SERV_CATA "+
								" LEFT JOIN WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD CPU ON LP.ID_UNIDAD_CONCEPTO = CPU.ID_CU " +
								" LEFT JOIN WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO ON CPU.ID_CP = CPAGO.ID_CPAGO " +
								" LEFT JOIN WEBQPROTESORERIA.UNIDAD_CODIGO COD ON CPU.UD_ID = COD.UD_ID " +
								" LEFT JOIN WEBQPROTESORERIA.TIPO_RESOLUCION TRS ON LP.TIPO_RESOLUCION = TRS.COD_TIPO_RESOL ";
	   
    String ADD_FILTER_UD_FEC_CAD = " AND DEP.UD_FEC_CAD is null ";
    String ORDER_BY_FECHA_REG = " ORDER BY LP.FECHA_REG DESC ";
    
    
	@Select(value = GET_SERVICIOS_QUERY
					+ " where DEP.UD_COD like #{codigoDependencia}||'%' "
					+ ADD_FILTER_UD_FEC_CAD
					+ ORDER_BY_FECHA_REG
					)
	@Results(value = {
	@Result(javaType = ListaServiciosModel.class),
	@Result(property = PROP_CODITEM,column = COL_CODITEM),
	@Result(property = PROP_UDID,column = COL_UDID),
	@Result(property = PROP_UDCOD,column = COL_UDCOD),
	@Result(property = PROP_UDDESC,column = COL_UDDESC),
	@Result(property = PROP_NUNIDADMEDIDA,column = COL_NUNIDADMEDIDA),
	@Result(property = PROP_UNIDADMEDIDADESC,column = COL_UNIDADMEDIDADESC),
	@Result(property = PROP_DESCRIPCION,column = COL_DESCRIPCION),
	@Result(property = PROP_PRECIO,column = COL_PRECIO),
	@Result(property = PROP_IDMONEDA,column = COL_IDMONEDA),
	@Result(property = PROP_MONEDASIMB,column = COL_MONEDASIMB),
	@Result(property = PROP_ESTADO,column = COL_ESTADO),
	@Result(property = PROP_CODIGOCATALOGO,column = COL_CODIGOCATALOGO),
	@Result(property = PROP_CODTIPOOPERACIONIGV,column = COL_CODTIPOOPERACIONIGV),
	@Result(property = PROP_DESCTIPOIGV,column = COL_DESCTIPOIGV),
	@Result(property = PROP_POSEEDESTRACCION,column = COL_POSEEDESTRACCION),
	@Result(property = PROP_PORCENTDETRACCION,column = COL_PORCENTDETRACCION),
	@Result(property = PROP_IDSERVCATA,column = COL_IDSERVCATA),
	@Result(property = PROP_DESCCATEGORIASERV,column = COL_DESCCATEGORIASERV),
	@Result(property = PROP_RESOLDECANAL,column = COL_RESOLDECANAL),
	@Result(property = PROP_RESOLDIRECTORAL,column = COL_RESOLDIRECTORAL),
	@Result(property = PROP_RESOLRECTORAL,column = COL_RESOLRECTORAL),
	@Result(property = PROP_IDUNIDADCONCEPTO,column = COL_IDUNIDADCONCEPTO),
	@Result(property = PROP_CODIGOCONCEPTO6DIGITOS,column = COL_CODIGOCONCEPTO6DIGITOS),
	@Result(property = PROP_DESCRUNDIADCONCEPTO,column = COL_DESCRUNDIADCONCEPTO),	
	@Result(property = PROP_NUMRESOLUCION,column = COL_NUMRESOLUCION),
	@Result(property = PROP_TIPORESOLUCION,column = COL_TIPORESOLUCION),
	@Result(property = PROP_NAMETIPORESOL,column = COL_NAMETIPORESOL),
	@Result(property = PROP_NIVELTIPORESOL,column = COL_NIVELTIPORESOL)	
	})
	public List<ListaServiciosModel> getListaServiciosXDependencia(@Param("codigoDependencia") String codigoDependencia);
	
	@Select(value = GET_SERVICIOS_QUERY
					+ " where DEP.UD_COD like #{codigoDependencia}||'%' "
					+ ADD_FILTER_UD_FEC_CAD
					+ " and LP.EST = 1 " 
					+ ORDER_BY_FECHA_REG
					)
	@Results(value = {
	@Result(javaType = ListaServiciosModel.class),
	@Result(property = PROP_CODITEM,column = COL_CODITEM),
	@Result(property = PROP_UDID,column = COL_UDID),
	@Result(property = PROP_UDCOD,column = COL_UDCOD),
	@Result(property = PROP_UDDESC,column = COL_UDDESC),
	@Result(property = PROP_NUNIDADMEDIDA,column = COL_NUNIDADMEDIDA),
	@Result(property = PROP_UNIDADMEDIDADESC,column = COL_UNIDADMEDIDADESC),
	@Result(property = PROP_DESCRIPCION,column = COL_DESCRIPCION),
	@Result(property = PROP_PRECIO,column = COL_PRECIO),
	@Result(property = PROP_IDMONEDA,column = COL_IDMONEDA),
	@Result(property = PROP_MONEDASIMB,column = COL_MONEDASIMB),
	@Result(property = PROP_ESTADO,column = COL_ESTADO),
	@Result(property = PROP_CODIGOCATALOGO,column = COL_CODIGOCATALOGO),
	@Result(property = PROP_CODTIPOOPERACIONIGV,column = COL_CODTIPOOPERACIONIGV),
	@Result(property = PROP_DESCTIPOIGV,column = COL_DESCTIPOIGV),
	@Result(property = PROP_POSEEDESTRACCION,column = COL_POSEEDESTRACCION),
	@Result(property = PROP_PORCENTDETRACCION,column = COL_PORCENTDETRACCION),
	@Result(property = PROP_IDSERVCATA,column = COL_IDSERVCATA),
	@Result(property = PROP_DESCCATEGORIASERV,column = COL_DESCCATEGORIASERV),
	@Result(property = PROP_RESOLDECANAL,column = COL_RESOLDECANAL),
	@Result(property = PROP_RESOLDIRECTORAL,column = COL_RESOLDIRECTORAL),
	@Result(property = PROP_RESOLRECTORAL,column = COL_RESOLRECTORAL),
	@Result(property = PROP_IDUNIDADCONCEPTO,column = COL_IDUNIDADCONCEPTO),
	@Result(property = PROP_CODIGOCONCEPTO6DIGITOS,column = COL_CODIGOCONCEPTO6DIGITOS),
	@Result(property = PROP_DESCRUNDIADCONCEPTO,column = COL_DESCRUNDIADCONCEPTO),
	@Result(property = PROP_NUMRESOLUCION,column = COL_NUMRESOLUCION),
	@Result(property = PROP_TIPORESOLUCION,column = COL_TIPORESOLUCION),
	@Result(property = PROP_NAMETIPORESOL,column = COL_NAMETIPORESOL),
	@Result(property = PROP_NIVELTIPORESOL,column = COL_NIVELTIPORESOL)	
	})
	public List<ListaServiciosModel> getListaServiciosXDependenciaHabilitados(@Param("codigoDependencia") String codigoDependencia);
	
	
	@Select(value = GET_SERVICIOS_QUERY
					+ " where "
					+ " LP.UD_ID_ADMINISTRATIVA = #{udIdAdministrativa} "
					+ ADD_FILTER_UD_FEC_CAD
					+ ORDER_BY_FECHA_REG
					)
	@Results(value = {
	@Result(javaType = ListaServiciosModel.class),
	@Result(property = PROP_CODITEM,column = COL_CODITEM),
	@Result(property = PROP_UDID,column = COL_UDID),
	@Result(property = PROP_UDCOD,column = COL_UDCOD),
	@Result(property = PROP_UDDESC,column = COL_UDDESC),
	@Result(property = PROP_NUNIDADMEDIDA,column = COL_NUNIDADMEDIDA),
	@Result(property = PROP_UNIDADMEDIDADESC,column = COL_UNIDADMEDIDADESC),
	@Result(property = PROP_DESCRIPCION,column = COL_DESCRIPCION),
	@Result(property = PROP_PRECIO,column = COL_PRECIO),
	@Result(property = PROP_IDMONEDA,column = COL_IDMONEDA),
	@Result(property = PROP_MONEDASIMB,column = COL_MONEDASIMB),
	@Result(property = PROP_ESTADO,column = COL_ESTADO),
	@Result(property = PROP_CODIGOCATALOGO,column = COL_CODIGOCATALOGO),
	@Result(property = PROP_CODTIPOOPERACIONIGV,column = COL_CODTIPOOPERACIONIGV),
	@Result(property = PROP_DESCTIPOIGV,column = COL_DESCTIPOIGV),
	@Result(property = PROP_POSEEDESTRACCION,column = COL_POSEEDESTRACCION),
	@Result(property = PROP_PORCENTDETRACCION,column = COL_PORCENTDETRACCION),
	@Result(property = PROP_IDSERVCATA,column = COL_IDSERVCATA),
	@Result(property = PROP_DESCCATEGORIASERV,column = COL_DESCCATEGORIASERV),
	@Result(property = PROP_RESOLDECANAL,column = COL_RESOLDECANAL),
	@Result(property = PROP_RESOLDIRECTORAL,column = COL_RESOLDIRECTORAL),
	@Result(property = PROP_RESOLRECTORAL,column = COL_RESOLRECTORAL),
	@Result(property = PROP_IDUNIDADCONCEPTO,column = COL_IDUNIDADCONCEPTO),
	@Result(property = PROP_CODIGOCONCEPTO6DIGITOS,column = COL_CODIGOCONCEPTO6DIGITOS),
	@Result(property = PROP_DESCRUNDIADCONCEPTO,column = COL_DESCRUNDIADCONCEPTO),
	@Result(property = PROP_NUMRESOLUCION,column = COL_NUMRESOLUCION),
	@Result(property = PROP_TIPORESOLUCION,column = COL_TIPORESOLUCION),
	@Result(property = PROP_NAMETIPORESOL,column = COL_NAMETIPORESOL),
	@Result(property = PROP_NIVELTIPORESOL,column = COL_NIVELTIPORESOL)	
	})
	public List<ListaServiciosModel> getListaServiciosXAdmiCentral(@Param("udIdAdministrativa") Integer udIdAdministrativa);
	
	@Select(value = GET_SERVICIOS_QUERY
					+ " where LP.UD_ID_ADMINISTRATIVA = #{udIdAdministrativa} " 
					+ ADD_FILTER_UD_FEC_CAD
					+ " AND LP.EST = 1 " 
					+ ORDER_BY_FECHA_REG)
	@Results(value = {
	@Result(javaType = ListaServiciosModel.class),
	@Result(property = PROP_CODITEM,column = COL_CODITEM),
	@Result(property = PROP_UDID,column = COL_UDID),
	@Result(property = PROP_UDCOD,column = COL_UDCOD),
	@Result(property = PROP_UDDESC,column = COL_UDDESC),
	@Result(property = PROP_NUNIDADMEDIDA,column = COL_NUNIDADMEDIDA),
	@Result(property = PROP_UNIDADMEDIDADESC,column = COL_UNIDADMEDIDADESC),
	@Result(property = PROP_DESCRIPCION,column = COL_DESCRIPCION),
	@Result(property = PROP_PRECIO,column = COL_PRECIO),
	@Result(property = PROP_IDMONEDA,column = COL_IDMONEDA),
	@Result(property = PROP_MONEDASIMB,column = COL_MONEDASIMB),
	@Result(property = PROP_ESTADO,column = COL_ESTADO),
	@Result(property = PROP_CODIGOCATALOGO,column = COL_CODIGOCATALOGO),
	@Result(property = PROP_CODTIPOOPERACIONIGV,column = COL_CODTIPOOPERACIONIGV),
	@Result(property = PROP_DESCTIPOIGV,column = COL_DESCTIPOIGV),
	@Result(property = PROP_POSEEDESTRACCION,column = COL_POSEEDESTRACCION),
	@Result(property = PROP_PORCENTDETRACCION,column = COL_PORCENTDETRACCION),
	@Result(property = PROP_IDSERVCATA,column = COL_IDSERVCATA),
	@Result(property = PROP_DESCCATEGORIASERV,column = COL_DESCCATEGORIASERV),
	@Result(property = PROP_RESOLDECANAL,column = COL_RESOLDECANAL),
	@Result(property = PROP_RESOLDIRECTORAL,column = COL_RESOLDIRECTORAL),
	@Result(property = PROP_RESOLRECTORAL,column = COL_RESOLRECTORAL),
	@Result(property = PROP_IDUNIDADCONCEPTO,column = COL_IDUNIDADCONCEPTO),
	@Result(property = PROP_CODIGOCONCEPTO6DIGITOS,column = COL_CODIGOCONCEPTO6DIGITOS),
	@Result(property = PROP_DESCRUNDIADCONCEPTO,column = COL_DESCRUNDIADCONCEPTO),
	@Result(property = PROP_NUMRESOLUCION,column = COL_NUMRESOLUCION),
	@Result(property = PROP_TIPORESOLUCION,column = COL_TIPORESOLUCION),
	@Result(property = PROP_NAMETIPORESOL,column = COL_NAMETIPORESOL),
	@Result(property = PROP_NIVELTIPORESOL,column = COL_NIVELTIPORESOL)	
	})
	public List<ListaServiciosModel> getListaServiciosXAdmiCentralHabilitados(@Param("udIdAdministrativa") Integer udIdAdministrativa);


	@Insert(value="{CALL REGISTRAR_SERVICIO ("
			+"#{item.udId, mode=IN, jdbcType=INTEGER},"
			+"#{item.descripcion, mode=IN, jdbcType=VARCHAR},"
			+"#{item.nUnidadMedida, mode=IN, jdbcType=VARCHAR},"
			+"#{item.precio, mode=IN, jdbcType=INTEGER},"
			+"#{item.estado, mode=IN, jdbcType=INTEGER},"
			+"#{item.idMoneda, mode=IN, jdbcType=INTEGER},"
			+"#{item.usuarioReg, mode=IN, jdbcType=VARCHAR},"
			+"#{item.codigoCatalogo, mode=IN, jdbcType=VARCHAR},"
			+"#{item.codTipoperacionIgv, mode=IN, jdbcType=INTEGER},"
			+"#{item.poseeDetraccion, mode=IN, jdbcType=INTEGER},"
			+"#{item.codDetraccion, mode=IN, jdbcType=VARCHAR},"
			+"#{item.porcentDetraccion, mode=IN, jdbcType=INTEGER},"
			+"#{item.idServCata, mode=IN, jdbcType=INTEGER},"
			+"#{item.udIdAdministrativa, mode=IN, jdbcType=INTEGER},"
			+"#{item.numResolucion, mode=IN, jdbcType=VARCHAR},"
			+"#{item.tipoResolucion, mode=IN, jdbcType=VARCHAR},"
			+"#{item.idUnidadConcepto, mode=IN, jdbcType=INTEGER},"
			+"#{item.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarServicio(@Param("item") ListaServiciosModel item);
	
	@Update(value = "UPDATE WEBQPROTESORERIA.LISTA_ITEM_SERVICIO \n " +
			" SET EST = #{estado} \n " +
			" WHERE  UD_ID = #{udId} AND trim(COD_ITEM) = trim(#{cod_item}) ")
	public void actualizarEstado(@Param("udId") Integer udId, @Param("cod_item") String codItem, @Param("estado") Integer estado);
	
	@Update(value="{CALL UPDATE_SERVICIO ("
			+"#{servicio.udId, mode=IN, jdbcType=INTEGER},"
			+"#{servicio.codigo, mode=IN, jdbcType=VARCHAR},"
			+"#{servicio.descripcion, mode=IN, jdbcType=VARCHAR},"
			+"#{servicio.nUnidadMedida, mode=IN, jdbcType=VARCHAR},"
			+"#{servicio.precio, mode=IN, jdbcType=INTEGER},"
			+"#{servicio.idMoneda, mode=IN, jdbcType=INTEGER},"
			+"#{servicio.usuarioModif, mode=IN, jdbcType=VARCHAR},"
			+"#{servicio.codTipoperacionIgv, mode=IN, jdbcType=INTEGER},"
			+"#{servicio.poseeDetraccion, mode=IN, jdbcType=INTEGER},"
			+"#{servicio.codDetraccion, mode=IN, jdbcType=VARCHAR},"
			+"#{servicio.porcentDetraccion, mode=IN, jdbcType=INTEGER},"
			+"#{servicio.idServCata, mode=IN, jdbcType=INTEGER},"
			+"#{servicio.numResolucion, mode=IN, jdbcType=VARCHAR},"
			+"#{servicio.tipoResolucion, mode=IN, jdbcType=VARCHAR},"
			+"#{servicio.idUnidadConcepto, mode=IN, jdbcType=INTEGER},"
			+"#{servicio.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarServicio(@Param("servicio") ListaServiciosModel servicio);	
	
	
	@Delete(value="{CALL DELETE_ITEM_SERVICIO ("
			+"#{item.udId, mode=IN, jdbcType=INTEGER},"
			+"#{item.codigo, mode=IN, jdbcType=VARCHAR},"
			+"#{item.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void deleteItemServicio(@Param("item") ListaServiciosModel item);

}
