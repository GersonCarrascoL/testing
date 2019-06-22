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

import pe.edu.unmsm.quipucamayoc.model.ConceptoUnidadModel;

public interface ConceptoUnidadPersistence {
	
	String IDCPU = "idCPU";
	String IDCPAGO = "idCPago";
	String CODIGO_UNIDAD = "codigoUnidad";
	String CODCPAGO = "codCPago";
	String CODIGO_CONCEPTO_6DIGITOS = "codigoConcepto6digitos";
	String CONCEPTO = "concepto";
	String CPDESCRIP = "cpDescrip";
	String DESCR = "descr";
	String DESCRIPCION = "descripcion";
	String ID_TIPOCPAGO = "idTipoCpago";
	String TCPDESCR = "tcpDescr";
	String TCP_DESCR = "tcp_descr";
	String MONTO = "monto";
	String ESTADO = "estado";
	String CP_ESTADO = "cpEstado";
	String ID_MONEDA = "idMoneda";
	String FACTURABLE = "facturable";
	String CP_FACTURABLE = "cpFacturable";
	String RESOLRECTORAL = "resolRectoral";
	String RESOL_RECTORAL = "resol_rectoral";
	String UDID = "udId";
	String UDCOD = "udCod";
	String UDDESC = "udDesc";
	String MONEDA_DESC = "monedaDesc";
	String MON_DESC = "monDesc";
	String MONEDA_SIMB = "monedaSimb";
	String MON_SIMB = "monSimb";
	String IGV = "igv";
	String FECHAREG = "fechaReg";
	String FECHA_REG = "fecha_reg";
	String CODBANCO = "codBanco";
	String COD_BANCO = "codBanco";
	String NOMBREBANCO = "nombreBanco";
	String NOMBRE_BANCO = "nombre_banco";
	String ESTADOSOLICITUD = "estadoSolicitud";
	String ESTADO_SOLICITUD = "estado_solicitud";
	String OBSERVACIONES = "observaciones";
	String MOTIVORECHAZO = "motivoRechazo";
	String MOTIVO_RECHAZO = "motivo_rechazo";
	String POSEEDETRACCION = "poseeDetraccion";
	String CODDETRACCION = "codDetraccion";
	String PORCENTDETRACCION = "porcentDetraccion";
	String DESCR_DETRACCION = "descrDetraccion";
	String FECHAMODIF = "fechaModif";
	String FECHA_MODIF = "fecha_modif";
	String ESTEDIT = "estEdit";
	String EST_EDIT = "est_edit";
	String ESTADOSOLICITUDMAESTRO = "estadoSolicitudMaestro";
	String ESTADO_SOLICITUD_MAESTRO = "estado_solicitud_maestro";
		
	String IN_IDCPAGO = "#{concepto.idCPago, mode=IN, jdbcType=INTEGER}";
	String IN_DESCR = "#{concepto.descr, mode=IN, jdbcType=VARCHAR}";	
	String IN_MONTO = "#{concepto.monto, mode=IN, jdbcType=INTEGER}";
	String IN_UD_ID = "#{concepto.udId, mode=IN, jdbcType=INTEGER}";
	String IN_IDMONEDA = "#{concepto.idMoneda, mode=IN, jdbcType=INTEGER}";
	String IN_RESOL_RECTORAL = "#{concepto.resolRectoral, mode=IN, jdbcType=VARCHAR}";
	String IN_UDIDSOL = "#{concepto.udIdSol, mode=IN, jdbcType=VARCHAR}";
	String IN_OBSERVACIONES = "#{concepto.observaciones, mode=IN, jdbcType=VARCHAR}";
	String IN_UDADMINISTRATIVA = "#{concepto.udIdAdministrativa, mode=IN, jdbcType=INTEGER}";
	String OUT_IDCPU = "#{concepto.idCPU, mode=OUT, jdbcType=INTEGER}";
	String OUT_STATUS = "#{concepto.status, mode=OUT, jdbcType=INTEGER}";
	String OUT_ERROR_MSG = "#{concepto.errorMsg, mode=OUT, jdbcType=VARCHAR}";
	
	String GET_CONCEPTOSPAGO_X_DEPENDENCIA = "SELECT " +
											" CPU.ID_CU AS idCPU," +
											" CPAGO.ID_CPAGO AS idCPago, " +
											" COD.CODIGO_UNIDAD AS codigoUnidad, " +
											" CPAGO.COD_CPAGO AS codCPago, " +
											" COD.CODIGO_UNIDAD || '-' || CPAGO.COD_CPAGO AS codigoConcepto6digitos, " +
											" CPAGO.CONCEPTO AS cpDescrip, " +
											" CPU.DESCR AS descripcion, " +
											" CPAGO.ID_TIPO_CPAGO AS idTipoCPago, " +
											" TCP.NOMBRE AS tcp_descr, " +
											" CPU.MONTO AS monto, " +
											" CPU.ESTADO AS cpEstado, " +
											" CPU.UD_ID AS udId, " +
											" CPU.ID_MONEDA AS idMoneda, " +
											" CPAGO.FACTURABLE AS cpFacturable, " +
											" nvl(TO_char(CPU.FECHA_REG, 'DD/MM/YYYY HH24:MI:SS'),null) AS fecha_reg, " +
											" CPU.EST_SOLICITUD AS estado_solicitud, " +
											" DEP.UD_COD AS udCod, " +
											" DEP.UD_DSC AS udDesc, " +
											" MON.SIMB AS monSimb, " +
											" MON.MONEDA AS monDesc, " +
											" CPAGO.IGV AS igv, " +
											" CPAGO.COD_BANCO AS cod_banco, " +
											" BCO.BANRAZSOC AS nombre_banco,  " +
											" CPU.RESOL_RECTORAL AS resol_rectoral, " +											
											" CPU.MOTIVO_RECHAZO AS motivo_rechazo, " +
											" CPU.OBSERVACIONES AS observaciones , " +
											" CPU.POSEE_DETRACCION AS poseeDetraccion , " +
											" CPU.COD_CATA_DETRAC AS codDetraccion , " +
											" CPU.PORCENT_DETRAC AS porcentDetraccion , " +
											" CATAN15.DESCRIPCION AS descrDetraccion \n" +
											" FROM \n" +
											" WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD CPU \n" +
											" INNER JOIN WEBQPROTESORERIA.MONEDA MON ON CPU.ID_MONEDA = MON.ID_MONEDA\n" +
											" INNER JOIN WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO ON CPU.ID_CP = CPAGO.ID_CPAGO\n" +
											" INNER JOIN WEBQPROTESORERIA.BANCO BCO ON CPAGO.COD_BANCO = BCO.BANCOD\n" +
											" INNER JOIN WEBQPROTESORERIA.TIPO_CONCEPTO_PAGO TCP ON CPAGO.ID_TIPO_CPAGO = TCP.ID_TIPO_CPAGO\n" +
											" INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON CPU.UD_ID = DEP.UD_ID\n" +
											" INNER JOIN WEBQPROTESORERIA.UNIDAD_CODIGO COD ON CPU.UD_ID = COD.UD_ID\n" +
											" LEFT JOIN WEBQPROTESORERIA.SUNAT_CATALOGO_N15 CATAN15 ON CPU.COD_CATA_DETRAC = CATAN15.COD_CATA \n"+
											" WHERE\n" +
											" DEP.UD_FEC_CAD IS NULL AND\n" +
											" DEP.UD_COD LIKE #{codigoDependencia} AND\n" +
											" MON.EST = 1\n";
	
	String ORDER_BY_IDCPAGO = " ORDER BY idCPago ASC ";
	
	String GET_CONCEPTOPAGO_PENDIENTES = "SELECT \n" +
											" CPU.ID_CU AS idCPU," +
											" CPAGO.ID_CPAGO AS idCPago," +
											" COD.CODIGO_UNIDAD AS codigoUnidad," +
											" CPAGO.COD_CPAGO AS codCPago," +
											" COD.CODIGO_UNIDAD || '-' || CPAGO.COD_CPAGO AS codigoConcepto6digitos," +
											" CPAGO.CONCEPTO AS cpDescrip," +
											" CPU.DESCR AS descripcion," +
											" CPAGO.ID_TIPO_CPAGO AS idTipoCPago," +
											" TCP.NOMBRE AS tcp_descr," +
											" CPU.MONTO AS monto," +
											" CPU.ESTADO AS cpEstado," +
											" CPU.UD_ID AS udId," +
											" CPU.ID_MONEDA AS idMoneda," +
											" CPAGO.FACTURABLE AS cpFacturable," +
											" nvl(TO_char(CPU.FECHA_REG, 'DD/MM/YYYY'),null) AS fecha_reg," +
											" nvl(TO_char(CPU.FECHA_MODIF, 'DD/MM/YYYY'),null) AS fecha_modif," +
											" CPU.EST_EDIT AS est_edit," +
											" CPU.EST_SOLICITUD AS estado_solicitud," +
											" DEP.UD_COD AS udCod," +
											" DEP.UD_DSC AS udDesc," +
											" MON.SIMB AS monSimb," +
											" MON.MONEDA AS monDesc," +
											" CPAGO.IGV AS igv," +
											" CPAGO.COD_BANCO AS cod_banco," +
											" BCO.BANRAZSOC AS nombre_banco, " +
											" CPU.RESOL_RECTORAL AS resol_rectoral, " +
											" CPU.OBSERVACIONES AS observaciones, " +
											" CPU.MOTIVO_RECHAZO AS motivo_rechazo, " +
											" CPAGO.EST_SOLICITUD AS estado_solicitud_maestro, " +
											" CPU.POSEE_DETRACCION AS poseeDetraccion, " +
											" CPU.COD_CATA_DETRAC AS codDetraccion, " +
											" CPU.PORCENT_DETRAC AS porcentDetraccion, " +
											" CATAN15.DESCRIPCION AS descrDetraccion " +
											" FROM " +
											" WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD CPU " +
											" INNER JOIN WEBQPROTESORERIA.MONEDA MON ON CPU.ID_MONEDA = MON.ID_MONEDA " +
											" INNER JOIN WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO ON CPU.ID_CP = CPAGO.ID_CPAGO " +
											" INNER JOIN WEBQPROTESORERIA.BANCO BCO ON CPAGO.COD_BANCO = BCO.BANCOD " +
											" INNER JOIN WEBQPROTESORERIA.TIPO_CONCEPTO_PAGO TCP ON CPAGO.ID_TIPO_CPAGO = TCP.ID_TIPO_CPAGO " +
											" INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON CPU.UD_ID = DEP.UD_ID " +
											" INNER JOIN WEBQPROTESORERIA.UNIDAD_CODIGO COD ON CPU.UD_ID = COD.UD_ID " +
											" LEFT JOIN WEBQPROTESORERIA.SUNAT_CATALOGO_N15 CATAN15 ON CPU.COD_CATA_DETRAC = CATAN15.COD_CATA "+
											" WHERE " +
											" DEP.UD_FEC_CAD IS NULL AND " +
											" MON.EST = 1 " +
											" AND CPU.EST_SOLICITUD = 0 ";
	
	String ORDER_BY_FECHA_MODIF = " ORDER BY CPU.FECHA_MODIF ASC";
	
	String GET_CONCEPTOPAGO_TODOS = "SELECT " +
									" CPU.ID_CU AS idCPU, " +
									" CPAGO.ID_CPAGO AS idCPago, " +
									" COD.CODIGO_UNIDAD AS codigoUnidad, " +
									" CPAGO.COD_CPAGO AS codCPago, " +
									" COD.CODIGO_UNIDAD || '-' || CPAGO.COD_CPAGO AS codigoConcepto6digitos, " +
									" CPAGO.CONCEPTO AS cpDescrip, " +
									" CPU.DESCR AS descripcion, " +
									" CPAGO.ID_TIPO_CPAGO AS idTipoCPago, " +
									" TCP.NOMBRE AS tcp_descr, " +
									" CPU.MONTO AS monto, " +
									" CPU.ESTADO AS cpEstado, " +
									" CPU.UD_ID AS udId, " +
									" CPU.ID_MONEDA AS idMoneda, " +
									" CPAGO.FACTURABLE AS cpFacturable, " +
									" nvl(TO_char(CPU.FECHA_REG, 'DD/MM/YYYY'),null) AS fecha_reg, " +
									" nvl(TO_char(CPU.FECHA_MODIF, 'DD/MM/YYYY'),null) AS fecha_modif, " +
									" CPU.EST_EDIT AS est_edit, " +
									" CPU.EST_SOLICITUD AS estado_solicitud, " +
									" DEP.UD_COD AS udCod, " +
									" DEP.UD_DSC AS udDesc, " +
									" MON.SIMB AS monSimb, " +
									" MON.MONEDA AS monDesc, " +
									" CPAGO.IGV AS igv, " +
									" CPAGO.COD_BANCO AS cod_banco, " +
									" BCO.BANRAZSOC AS nombre_banco,  " +
									" CPU.RESOL_RECTORAL AS resol_rectoral,  " +
									" DEP2.UD_DSC AS udDescPadre,  " +
									" DECODE(CPU.UD_ID_ADMINISTRATIVA, NULL, DEP2.UD_ID, CPU.UD_ID_ADMINISTRATIVA) AS udIdPadre,  " +
									" CPU.OBSERVACIONES AS observaciones, " +
									" CPU.MOTIVO_RECHAZO AS motivo_rechazo,  " +
									" CPAGO.EST_SOLICITUD AS estado_solicitud_maestro,  " +
									" to_char(CPU.FECHA_MODIF, 'yyyy') AS reg_year, " +
									" to_char(CPU.FECHA_MODIF, 'mm') As reg_month,  " +
									" CPU.POSEE_DETRACCION AS poseeDetraccion, " +
									" CPU.COD_CATA_DETRAC AS codDetraccion, " +
									" CPU.PORCENT_DETRAC AS porcentDetraccion, " +
									" CATAN15.DESCRIPCION AS descrDetraccion " +
									" FROM " +
									" WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD CPU " +
									" INNER JOIN WEBQPROTESORERIA.MONEDA MON ON CPU.ID_MONEDA = MON.ID_MONEDA " +
									" INNER JOIN WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO ON CPU.ID_CP = CPAGO.ID_CPAGO " +
									" INNER JOIN WEBQPROTESORERIA.BANCO BCO ON CPAGO.COD_BANCO = BCO.BANCOD " +
									" INNER JOIN WEBQPROTESORERIA.TIPO_CONCEPTO_PAGO TCP ON CPAGO.ID_TIPO_CPAGO = TCP.ID_TIPO_CPAGO " +
									" INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON CPU.UD_ID = DEP.UD_ID " +
									" INNER JOIN QPRODATAQUIPU.UNI_DEP DEP2 ON (DEP2.UD_ID = DEP.UNINIV2) "+
									" LEFT JOIN WEBQPROTESORERIA.UNIDAD_CODIGO COD ON CPU.UD_ID = COD.UD_ID " +
									" LEFT JOIN WEBQPROTESORERIA.SUNAT_CATALOGO_N15 CATAN15 ON CPU.COD_CATA_DETRAC = CATAN15.COD_CATA "+
									" WHERE " +
									" DEP.UD_FEC_CAD IS NULL AND " +
									" DEP2.UD_FEC_CAD IS NULL AND " +
									" MON.EST = 1 " +
									" ORDER BY CPU.FECHA_MODIF ASC";
	
	String GET_EXISTE_CONCEPTO = " SELECT \n"+
			" count(*) As contador\n"+
			" FROM \n"+
			" WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD CPU \n"+
			" WHERE \n"+
			" CPU.UD_ID = #{ud_id} AND \n"+
			" CPU.ID_CP = #{idCP}";
	
	
	
	@Select(value = GET_CONCEPTOSPAGO_X_DEPENDENCIA + ORDER_BY_IDCPAGO)
	@Results(value = {
			@Result(javaType = ConceptoUnidadModel.class),
			@Result(property = IDCPU, column = IDCPU),
		    @Result(property = IDCPAGO, column = IDCPAGO),
		    @Result(property = CODIGO_UNIDAD, column = CODIGO_UNIDAD),
		    @Result(property = CODCPAGO, column = CODCPAGO),
		    @Result(property = CODIGO_CONCEPTO_6DIGITOS, column = CODIGO_CONCEPTO_6DIGITOS),
		    @Result(property = CONCEPTO, column = CPDESCRIP),
		    @Result(property = DESCR, column = DESCRIPCION),
		    @Result(property = ID_TIPOCPAGO, column = ID_TIPOCPAGO),
		    @Result(property = TCPDESCR, column = TCP_DESCR),
		    @Result(property = MONTO, column = MONTO),
		    @Result(property = ESTADO, column = CP_ESTADO),
		    @Result(property = ID_MONEDA, column = ID_MONEDA),
		    @Result(property = FACTURABLE, column = CP_FACTURABLE),
		    @Result(property = RESOLRECTORAL, column = RESOL_RECTORAL),
		    @Result(property = UDID, column = UDID),
		    @Result(property = UDCOD, column = UDCOD),
		    @Result(property = UDDESC, column = UDDESC),
		    @Result(property = MONEDA_DESC, column = MON_DESC),
		    @Result(property = MONEDA_SIMB, column = MON_SIMB),
		    @Result(property = IGV, column = IGV),
		    @Result(property = FECHAREG, column = FECHA_REG),
		    @Result(property = CODBANCO, column = COD_BANCO),
		    @Result(property = NOMBREBANCO, column = NOMBRE_BANCO),
		    @Result(property = ESTADOSOLICITUD, column = ESTADO_SOLICITUD),
		    @Result(property = OBSERVACIONES, column = OBSERVACIONES),
		    @Result(property = MOTIVORECHAZO, column = MOTIVO_RECHAZO),
		    @Result(property = POSEEDETRACCION,column = POSEEDETRACCION),
			@Result(property = CODDETRACCION,column = CODDETRACCION),
			@Result(property = PORCENTDETRACCION,column = PORCENTDETRACCION),
			@Result(property = DESCR_DETRACCION,column = DESCR_DETRACCION)
	})
	public List<ConceptoUnidadModel> conceptosPagoXDependencia(@Param("codigoDependencia") String codigoDependencia);
	
	@Select(value = GET_CONCEPTOSPAGO_X_DEPENDENCIA 
			+ " AND CPU.ESTADO = #{estado} \n"
			+ ORDER_BY_IDCPAGO
			)
	@Results(value = {
				@Result(javaType = ConceptoUnidadModel.class),
				@Result(property = IDCPU, column = IDCPU),
			    @Result(property = IDCPAGO, column = IDCPAGO),
			    @Result(property = CODIGO_UNIDAD, column = CODIGO_UNIDAD),
			    @Result(property = CODCPAGO, column = CODCPAGO),
			    @Result(property = CODIGO_CONCEPTO_6DIGITOS, column = CODIGO_CONCEPTO_6DIGITOS),
			    @Result(property = CONCEPTO, column = CPDESCRIP),
			    @Result(property = DESCR, column = DESCRIPCION),
			    @Result(property = ID_TIPOCPAGO, column = ID_TIPOCPAGO),
			    @Result(property = TCPDESCR, column = TCP_DESCR),
			    @Result(property = MONTO, column = MONTO),
			    @Result(property = ESTADO, column = CP_ESTADO),
			    @Result(property = ID_MONEDA, column = ID_MONEDA),
			    @Result(property = FACTURABLE, column = CP_FACTURABLE),
			    @Result(property = RESOLRECTORAL, column = RESOL_RECTORAL),
			    @Result(property = UDID, column = UDID),
			    @Result(property = UDCOD, column = UDCOD),
			    @Result(property = UDDESC, column = UDDESC),
			    @Result(property = MONEDA_DESC, column = MON_DESC),
			    @Result(property = MONEDA_SIMB, column = MON_SIMB),
			    @Result(property = IGV, column = IGV),
			    @Result(property = FECHAREG, column = FECHA_REG),
			    @Result(property = CODBANCO, column = COD_BANCO),
			    @Result(property = NOMBREBANCO, column = NOMBRE_BANCO),
			    @Result(property = ESTADOSOLICITUD, column = ESTADO_SOLICITUD),
			    @Result(property = OBSERVACIONES, column = OBSERVACIONES),
			    @Result(property = MOTIVORECHAZO, column = MOTIVO_RECHAZO),
			    @Result(property = POSEEDETRACCION,column = POSEEDETRACCION),
				@Result(property = CODDETRACCION,column = CODDETRACCION),
				@Result(property = PORCENTDETRACCION,column = PORCENTDETRACCION),
				@Result(property = DESCR_DETRACCION,column = DESCR_DETRACCION)
	})
	public List<ConceptoUnidadModel> conceptosPagoXDependenciaXEstado(@Param("codigoDependencia") String codigoDependencia, @Param("estado") Integer estado);
	
	@Select(value = GET_CONCEPTOSPAGO_X_DEPENDENCIA + ORDER_BY_IDCPAGO
			)
	@Results(value = {
				@Result(javaType = ConceptoUnidadModel.class),
				@Result(property = IDCPU, column = IDCPU),
			    @Result(property = IDCPAGO, column = IDCPAGO),
			    @Result(property = CODIGO_UNIDAD, column = CODIGO_UNIDAD),
			    @Result(property = CODCPAGO, column = CODCPAGO),
			    @Result(property = CODIGO_CONCEPTO_6DIGITOS, column = CODIGO_CONCEPTO_6DIGITOS),
			    @Result(property = CONCEPTO, column = CPDESCRIP),
			    @Result(property = DESCR, column = DESCRIPCION),
			    @Result(property = ID_TIPOCPAGO, column = ID_TIPOCPAGO),
			    @Result(property = TCPDESCR, column = TCP_DESCR),
			    @Result(property = MONTO, column = MONTO),
			    @Result(property = ESTADO, column = CP_ESTADO),
			    @Result(property = ID_MONEDA, column = ID_MONEDA),
			    @Result(property = FACTURABLE, column = CP_FACTURABLE),
			    @Result(property = RESOLRECTORAL, column = RESOL_RECTORAL),
			    @Result(property = UDID, column = UDID),
			    @Result(property = UDCOD, column = UDCOD),
			    @Result(property = UDDESC, column = UDDESC),
			    @Result(property = MONEDA_DESC, column = MON_DESC),
			    @Result(property = MONEDA_SIMB, column = MON_SIMB),
			    @Result(property = IGV, column = IGV),
			    @Result(property = FECHAREG, column = FECHA_REG),
			    @Result(property = CODBANCO, column = COD_BANCO),
			    @Result(property = NOMBREBANCO, column = NOMBRE_BANCO),
			    @Result(property = ESTADOSOLICITUD, column = ESTADO_SOLICITUD),
			    @Result(property = OBSERVACIONES, column = OBSERVACIONES),
			    @Result(property = MOTIVORECHAZO, column = MOTIVO_RECHAZO),
			    @Result(property = POSEEDETRACCION,column = POSEEDETRACCION),
				@Result(property = CODDETRACCION,column = CODDETRACCION),
				@Result(property = PORCENTDETRACCION,column = PORCENTDETRACCION),
				@Result(property = DESCR_DETRACCION,column = DESCR_DETRACCION)
	})
	public List<ConceptoUnidadModel> conceptosPagoXUnaDependencia(@Param("codigoDependencia") String codigoDependencia);
	
	@Select(value = "SELECT\n" +
			" CPU.ID_CU AS idCPU,\n" +
			" CPAGO.ID_CPAGO AS idCPago,\n" +
			" COD.CODIGO_UNIDAD AS codigoUnidad,\n" +
			" CPAGO.COD_CPAGO AS codCPago,\n" +
			" COD.CODIGO_UNIDAD || CPAGO.COD_CPAGO AS codigoConcepto6digitos,\n" +
			" CPU.DESCR AS descripcion,\n" +
			" CPU.UD_ID AS udId,\n" +
			" DEP.UD_COD AS udCod,\n" +
			" DEP.UD_DSC AS udDesc,\n" +
			" COD.CODIGO_UNIDAD || CPAGO.COD_CPAGO || ' - ' || CPU.DESCR AS readableName \n" +
			" FROM \n" +
			" WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD CPU \n" +
			" INNER JOIN WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO ON CPU.ID_CP = CPAGO.ID_CPAGO\n" +
			" INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON CPU.UD_ID = DEP.UD_ID\n" +
			" INNER JOIN WEBQPROTESORERIA.UNIDAD_CODIGO COD ON CPU.UD_ID = COD.UD_ID\n" +
			" WHERE\n" +
			" DEP.UD_FEC_CAD IS NULL AND\n" +
			" DEP.UD_ID = #{udIdDependencia} \n" +
			" ORDER BY codigoConcepto6digitos ASC")
	@Results(value = {@Result(javaType = ConceptoUnidadModel.class),
		@Result(property = IDCPU, column = IDCPU),
	    @Result(property = IDCPAGO, column = IDCPAGO),
	    @Result(property = CODIGO_UNIDAD, column = CODIGO_UNIDAD),
	    @Result(property = CODCPAGO, column = CODCPAGO),
	    @Result(property = CODIGO_CONCEPTO_6DIGITOS, column = CODIGO_CONCEPTO_6DIGITOS),
	    @Result(property = DESCR, column = DESCRIPCION),
	    @Result(property = UDID, column = UDID),
	    @Result(property = UDCOD, column = UDCOD),
	    @Result(property = UDDESC, column = UDDESC),
	    @Result(property = "readableName", column = "readableName")
	})
	public List<ConceptoUnidadModel> listarConceptosPagoHabilitadosXUnaDep(@Param("udIdDependencia") Integer udIdDependencia);	
		
	@Insert(value="{CALL REGISTRAR_CONCEPTOPAGO_UNIDAD ("
					+ IN_IDCPAGO + ","
					+ IN_DESCR + ","
					+ IN_MONTO + ","
					+ IN_UD_ID + ","
					+ IN_IDMONEDA + ","
					+ IN_RESOL_RECTORAL + ","
					+ IN_UDIDSOL + ","
					+ IN_OBSERVACIONES + ","
					+ IN_UDADMINISTRATIVA + ","
					+ OUT_IDCPU + ","
					+ OUT_STATUS + ","
					+ OUT_ERROR_MSG
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarConceptoPagoUnidad(@Param("concepto") ConceptoUnidadModel conceptoPago);

	@Update(value = "UPDATE WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD \n " +
			" SET ESTADO = #{estado} \n " +
			" WHERE  ID_CU = #{idCPago} ")
	public void actualizarEstado(@Param("idCPago") Integer idCPago, @Param("estado") Integer estado);
	
	
	@Update(value="{CALL ACTUALIZAR_CONCEPTO_UNIDAD ("
			+ IN_IDCPAGO + ","
			+ IN_DESCR + ","
			+ IN_MONTO + ","
			+ IN_IDMONEDA + ","
			+ IN_RESOL_RECTORAL + ","
			+ IN_OBSERVACIONES + ","
			+ OUT_STATUS
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarConceptoPago(@Param("concepto") ConceptoUnidadModel concepto);
	
	@Update(value="{CALL VOLVER_ENVIAR_CONCEPTO ("
			+ IN_IDCPAGO + ","
			+ IN_DESCR + ","
			+ IN_MONTO + ","
			+ IN_IDMONEDA + ","
			+ IN_RESOL_RECTORAL + ","
			+ IN_OBSERVACIONES + ","
			+ OUT_STATUS
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void volverEnviarConcepto(@Param("concepto") ConceptoUnidadModel concepto);
	
	@Select(value = "SELECT\n" +
			" CPU.ID_CU AS idCPU,\n" +
			" CPAGO.ID_CPAGO AS idCPago,\n" +
			" COD.CODIGO_UNIDAD AS codigoUnidad,\n" +
			" CPAGO.COD_CPAGO AS codCPago,\n" +
			" CPAGO.CONCEPTO AS cpDescrip,\n" +
			" CPU.MONTO AS monto,\n" +
			" CPU.ESTADO AS cpEstado,\n" +
			" CPU.UD_ID AS udId,\n" +
			" CPU.ID_MONEDA AS idMoneda,\n" +
			" CPAGO.FACTURABLE AS cpFacturable,\n" +
			" DEP.UD_COD AS udCod,\n" +
			" DEP.UD_DSC AS udDesc,\n" +
			" MON.SIMB AS monSimb,\n" +
			" MON.MONEDA AS monDesc\n" +
			" FROM \n" +
			" WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD CPU \n" +
			" INNER JOIN WEBQPROTESORERIA.MONEDA MON ON CPU.ID_MONEDA = MON.ID_MONEDA\n" +
			" INNER JOIN WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO ON CPU.ID_CP = CPAGO.ID_CPAGO\n" +
			" INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON CPU.UD_ID = DEP.UD_ID\n" +
			" INNER JOIN WEBQPROTESORERIA.UNIDAD_CODIGO COD ON CPU.UD_ID = COD.UD_ID\n" +
			" WHERE\n" +
			" DEP.UD_FEC_CAD IS NULL AND\n" +
			" MON.EST = 1\n" +
			" AND CPU.ESTADO = 1 \n" +
			" ORDER BY idCPago ASC")
	@Results(value = {@Result(javaType = ConceptoUnidadModel.class),
	@Result(property = IDCPU, column = IDCPU),
	@Result(property = IDCPAGO, column = IDCPAGO),
	@Result(property = CODIGO_UNIDAD, column = CODIGO_UNIDAD),
	@Result(property = CODCPAGO, column = CODCPAGO),
	@Result(property = CONCEPTO, column = CPDESCRIP),
	@Result(property = MONTO, column = MONTO),
	@Result(property = ESTADO, column = CP_ESTADO),
	@Result(property = ID_MONEDA, column = ID_MONEDA),
	@Result(property = FACTURABLE, column = CP_FACTURABLE),
	@Result(property = UDID, column = UDID),
	@Result(property = UDCOD, column = UDCOD),
	@Result(property = UDDESC, column = UDDESC),
	@Result(property = MONEDA_DESC, column = MON_DESC),
	@Result(property = MONEDA_SIMB, column = MON_SIMB)
	})
	public List<ConceptoUnidadModel> conceptosPagoXDependenciaHabilitados(@Param("codigoDependencia") String codigoDependencia);
	
	
			@Select(value = "SELECT\n" +
					" CPU.ID_CU AS idCPU,\n" +
					" CPAGO.ID_CPAGO AS idCPago,\n" +
					" COD.CODIGO_UNIDAD AS codigoUnidad,\n" +
					" CPAGO.COD_CPAGO AS codCPago,\n" +
					" CPAGO.CONCEPTO AS cpDescrip,\n" +
					" CPU.MONTO AS monto,\n" +
					" CPU.ESTADO AS cpEstado,\n" +
					" CPU.UD_ID AS udId,\n" +
					" CPU.ID_MONEDA AS idMoneda,\n" +
					" CPAGO.FACTURABLE AS cpFacturable,\n" +				
					" DEP.UD_COD AS udCod,\n" +
					" DEP.UD_DSC AS udDesc,\n" +
					" MON.SIMB AS monSimb,\n" +
					" MON.MONEDA AS monDesc\n" +
					" FROM \n" +
					" WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD CPU \n" +
					" INNER JOIN WEBQPROTESORERIA.MONEDA MON ON CPU.ID_MONEDA = MON.ID_MONEDA\n" +
					" INNER JOIN WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO ON CPU.ID_CP = CPAGO.ID_CPAGO\n" +
					" INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON CPU.UD_ID = DEP.UD_ID\n" +
					" INNER JOIN WEBQPROTESORERIA.UNIDAD_CODIGO COD ON CPU.UD_ID = COD.UD_ID\n" +
					" WHERE\n" +
					" DEP.UD_FEC_CAD IS NULL \n" +
					" AND DEP.UD_COD like #{codigoDependencia} || '%'\n" +	
					" AND MON.EST = 1\n" +
					" AND CPU.ESTADO = 1 \n" +
					" AND MON.ID_MONEDA = #{codigoMoneda} \n" +	
					" ORDER BY idCPago ASC")
		@Results(value = {@Result(javaType = ConceptoUnidadModel.class),
		@Result(property = IDCPU, column = IDCPU),
		@Result(property = IDCPAGO, column = IDCPAGO),
		@Result(property = CODIGO_UNIDAD, column = CODIGO_UNIDAD),
		@Result(property = CODCPAGO, column = CODCPAGO),
		@Result(property = CONCEPTO, column = CPDESCRIP),
		@Result(property = MONTO, column = MONTO),
		@Result(property = ESTADO, column = CP_ESTADO),
		@Result(property = ID_MONEDA, column = ID_MONEDA),
		@Result(property = FACTURABLE, column = CP_FACTURABLE),
		@Result(property = UDID, column = UDID),
		@Result(property = UDCOD, column = UDCOD),
		@Result(property = UDDESC, column = UDDESC),
		@Result(property = MONEDA_DESC, column = MON_DESC),
		@Result(property = MONEDA_SIMB, column = MON_SIMB)
		})
		public List<ConceptoUnidadModel> conceptosPagoXDependenciaHabilitadosMoneda(@Param("codigoDependencia") String codigoDependencia,@Param("codigoMoneda") String codigoMoneda);
		
		
	@Select(value = GET_CONCEPTOPAGO_PENDIENTES
					+ " AND DEP.UD_COD LIKE #{codigoDependencia} "
					+ ORDER_BY_FECHA_MODIF
			)
	@Results(value = {@Result(javaType = ConceptoUnidadModel.class),
		@Result(property = IDCPU, column = IDCPU),
	    @Result(property = IDCPAGO, column = IDCPAGO),
	    @Result(property = CODIGO_UNIDAD, column = CODIGO_UNIDAD),
	    @Result(property = CODIGO_CONCEPTO_6DIGITOS, column = CODIGO_CONCEPTO_6DIGITOS),
	    @Result(property = CODCPAGO, column = CODCPAGO),
	    @Result(property = CONCEPTO, column = CPDESCRIP),
	    @Result(property = DESCR, column = DESCRIPCION),
	    @Result(property = ID_TIPOCPAGO, column = "idTipoCPago"),
	    @Result(property = TCPDESCR, column = TCP_DESCR),
	    @Result(property = MONTO, column = MONTO),
	    @Result(property = ESTADO, column = CP_ESTADO),
	    @Result(property = ID_MONEDA, column = ID_MONEDA),
	    @Result(property = FACTURABLE, column = CP_FACTURABLE),
	    @Result(property = RESOLRECTORAL, column = RESOL_RECTORAL),
	    @Result(property = UDID, column = UDID),
	    @Result(property = UDCOD, column = UDCOD),
	    @Result(property = UDDESC, column = UDDESC),
	    @Result(property = MONEDA_DESC, column = MON_DESC),
	    @Result(property = MONEDA_SIMB, column = MON_SIMB),
	    @Result(property = IGV, column = IGV),
	    @Result(property = FECHAREG, column = FECHA_REG),
	    @Result(property = FECHAMODIF, column = FECHA_MODIF),
		@Result(property = ESTEDIT, column = EST_EDIT),
	    @Result(property = CODBANCO, column = "cod_banco"),
	    @Result(property = NOMBREBANCO, column = NOMBRE_BANCO),
	    @Result(property = ESTADOSOLICITUD, column = ESTADO_SOLICITUD),
	    @Result(property = OBSERVACIONES, column = OBSERVACIONES),
	    @Result(property = MOTIVORECHAZO, column = MOTIVO_RECHAZO),
	    @Result(property = ESTADOSOLICITUDMAESTRO, column = ESTADO_SOLICITUD_MAESTRO),
	    @Result(property = POSEEDETRACCION,column = POSEEDETRACCION),
		@Result(property = CODDETRACCION,column = CODDETRACCION),
		@Result(property = PORCENTDETRACCION,column = PORCENTDETRACCION),
		@Result(property = DESCR_DETRACCION,column = DESCR_DETRACCION)
	})
	public List<ConceptoUnidadModel> conceptosPagoPendientesXDependencia(@Param("codigoDependencia") String codigoDependencia);
	
	@Select(value = GET_CONCEPTOPAGO_PENDIENTES + ORDER_BY_FECHA_MODIF
			)
	@Results(value = {
			@Result(javaType = ConceptoUnidadModel.class),
			@Result(property = IDCPU, column = IDCPU),
			@Result(property = IDCPAGO, column = IDCPAGO),
			@Result(property = CODIGO_UNIDAD, column = CODIGO_UNIDAD),
			@Result(property = CODIGO_CONCEPTO_6DIGITOS, column = CODIGO_CONCEPTO_6DIGITOS),
			@Result(property = CODCPAGO, column = CODCPAGO),
			@Result(property = CONCEPTO, column = CPDESCRIP),	
		    @Result(property = DESCR, column = DESCRIPCION),
		    @Result(property = ID_TIPOCPAGO, column = ID_TIPOCPAGO),
		    @Result(property = TCPDESCR, column = TCP_DESCR),
		    @Result(property = MONTO, column = MONTO),
		    @Result(property = ESTADO, column = CP_ESTADO),
		    @Result(property = ID_MONEDA, column = ID_MONEDA),
		    @Result(property = FACTURABLE, column = CP_FACTURABLE),    
		    @Result(property = FECHAMODIF, column = FECHAMODIF),
			@Result(property = ESTEDIT, column = EST_EDIT),	
		    @Result(property = CODBANCO, column = COD_BANCO),
		    @Result(property = NOMBREBANCO, column = NOMBRE_BANCO),
		    @Result(property = ESTADOSOLICITUD, column = ESTADO_SOLICITUD),
		    @Result(property = OBSERVACIONES, column = OBSERVACIONES),
		    @Result(property = MOTIVORECHAZO, column = MOTIVO_RECHAZO),    
		    @Result(property = ESTADOSOLICITUDMAESTRO, column = ESTADO_SOLICITUD_MAESTRO),    
		    @Result(property = POSEEDETRACCION,column = POSEEDETRACCION),
			@Result(property = CODDETRACCION,column = CODDETRACCION),
			@Result(property = PORCENTDETRACCION,column = PORCENTDETRACCION),
			@Result(property = DESCR_DETRACCION,column = DESCR_DETRACCION)
	})
	public List<ConceptoUnidadModel> conceptosPagoPendientesTodos();
			
	@Select(value = GET_CONCEPTOPAGO_TODOS
			)
	@Results(value = {
			@Result(javaType = ConceptoUnidadModel.class),
			@Result(property = IDCPU, column = IDCPU),
			@Result(property = IDCPAGO, column = IDCPAGO),
			@Result(property = CODIGO_UNIDAD, column = CODIGO_UNIDAD),
			@Result(property = CODIGO_CONCEPTO_6DIGITOS, column = CODIGO_CONCEPTO_6DIGITOS),
			@Result(property = CODCPAGO, column = CODCPAGO),
			@Result(property = CONCEPTO, column = CPDESCRIP),	
		    @Result(property = DESCR, column = DESCRIPCION),
		    @Result(property = ID_TIPOCPAGO, column = ID_TIPOCPAGO),
		    @Result(property = TCPDESCR, column = TCP_DESCR),
		    @Result(property = MONTO, column = MONTO),
		    @Result(property = ESTADO, column = CP_ESTADO),
		    @Result(property = ID_MONEDA, column = ID_MONEDA),
		    @Result(property = FACTURABLE, column = CP_FACTURABLE),		  
		    @Result(property = RESOLRECTORAL, column = RESOL_RECTORAL),	
			@Result(property = UDID, column = UDID),
			@Result(property = UDCOD, column = UDCOD),
		    @Result(property = UDDESC, column = UDDESC),
		    @Result(property = MONEDA_DESC, column = MON_DESC),
		    @Result(property = MONEDA_SIMB, column = MON_SIMB),	
		    @Result(property = IGV, column = IGV),
		    @Result(property = FECHAREG, column = FECHA_REG),
		    @Result(property = FECHAMODIF, column = FECHAMODIF),
			@Result(property = ESTEDIT, column = EST_EDIT),	
			@Result(property = "regYear", column = "reg_year"),
			@Result(property = "regMonth", column = "reg_month"),
			@Result(property = CODBANCO, column = COD_BANCO),
			@Result(property = NOMBREBANCO, column = NOMBRE_BANCO),	
			@Result(property = ESTADOSOLICITUD, column = ESTADO_SOLICITUD),	
		    @Result(property = "udDescPadre", column = "udDescPadre"),
		    @Result(property = "udIdPadre", column = "udIdPadre"),    
		    @Result(property = OBSERVACIONES, column = OBSERVACIONES),
		    @Result(property = MOTIVORECHAZO, column = MOTIVO_RECHAZO),    
		    @Result(property = ESTADOSOLICITUDMAESTRO, column = ESTADO_SOLICITUD_MAESTRO),    
		    @Result(property = POSEEDETRACCION,column = POSEEDETRACCION),
			@Result(property = CODDETRACCION,column = CODDETRACCION),
			@Result(property = PORCENTDETRACCION,column = PORCENTDETRACCION),
			@Result(property = DESCR_DETRACCION,column = DESCR_DETRACCION)
	})
	public List<ConceptoUnidadModel> conceptosPagoTodos();
	
	@Update(value = "UPDATE WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD \n " +
			" SET EST_SOLICITUD = 1, \n " +
			" FECHA_APROBADO = SYSDATE \n" +
			" WHERE  ID_CU= #{idCU}")
	public void aprobarConceptoPago(@Param("idCU") Integer idCPU);
	
	@Update(value = "UPDATE WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD \n " +
			" SET EST_SOLICITUD = 2, \n " +
			" ESTADO = 0, \n " +
			" FECHA_RECHAZO = SYSDATE, \n" +
			" MOTIVO_RECHAZO = #{motivo_rechazo} \n " +
			" WHERE  ID_CU = #{idCU} ")
	public void rechazarConceptoPago(@Param("idCU") Integer idCPU, @Param("motivo_rechazo") String motivoRechazo);
	
	
	@Insert(value="{CALL REG_CONCEPTO_UNIDAD_MATRIZ ("
			+"#{concepto.codCPago, mode=IN, jdbcType=VARCHAR},"
			+"#{concepto.concepto, mode=IN, jdbcType=VARCHAR},"
			+ IN_DESCR + ","
			+"#{concepto.idTipoCpago, mode=IN, jdbcType=INTEGER},"
			+"#{concepto.facturable, mode=IN, jdbcType=INTEGER},"
			+"#{concepto.igv, mode=IN, jdbcType=INTEGER},"			
			+"#{concepto.codBanco, mode=IN, jdbcType=VARCHAR},"
			+ IN_UDIDSOL + ","
			+ IN_UD_ID + ","
			+ IN_IDMONEDA + ","
			+ IN_MONTO + ","
			+ IN_RESOL_RECTORAL + ","
			+ IN_OBSERVACIONES + ","
			+ IN_UDADMINISTRATIVA + ","
			+"#{concepto.idCPago, mode=OUT, jdbcType=INTEGER},"
			+ OUT_IDCPU + ","
			+ OUT_STATUS + ","
			+ OUT_ERROR_MSG
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarConceptoPagoUnidadMatriz(@Param("concepto") ConceptoUnidadModel conceptoPago);
	
	
	@Delete(value="{CALL DELETE_CONCEPTO_UNIDAD ("
			+"#{concepto.idCPU, mode=IN, jdbcType=INTEGER},"
			+ IN_IDCPAGO + ","
			+ OUT_STATUS + ","
			+ OUT_ERROR_MSG
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void deleteConceptoUnidad(@Param("concepto") ConceptoUnidadModel conceptoPago);
	
	
	
	@Select(value = GET_EXISTE_CONCEPTO)
	@Results(value = {@Result(javaType = ConceptoUnidadModel.class),
	@Result(property = "contador", column = "contador")
	})
	public List<ConceptoUnidadModel> existeConceptoPago(@Param("idCP") Integer idCP, @Param("ud_id") Integer udId);
	
		
}
