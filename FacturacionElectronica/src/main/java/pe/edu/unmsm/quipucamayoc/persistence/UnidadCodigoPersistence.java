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


import pe.edu.unmsm.quipucamayoc.model.UnidadCodigoModel;

public interface UnidadCodigoPersistence {
	
	String STR_UNIDAD = "unidad";
	String IN_UDID_UNIDAD = "#{unidad.udIdUnidad, mode=IN, jdbcType=INTEGER}";
	String IN_NUM_UNIDAD = "#{unidad.numUnidad, mode=IN, jdbcType=VARCHAR}";
	
	@Select(value = " SELECT  \n" +
					" UNCOD.UD_ID AS ud_id_unidad, \n" +
					" UNCOD.UD_DSC AS ud_dsc_unidad, \n" +
					" UNCOD.COD_FAC AS cod_fac_unidad, \n" +
					" UNCOD.CODIGO_UNIDAD AS num_unidad, \n" +
					" UNCOD.ESTADO AS est_unidad, \n" +
					" UNCOD.ESTADO_SOLICITUD AS est_solicitud_unidad, \n" +
					" UNCOD.UD_ID_SOL AS ud_id_solicitante, \n" +
					" FAC.UD_ID AS ud_id_fac, \n" +
					" FAC.UD_DSC AS ud_dsc_fac, \n" +
					" nvl(TO_char(UNCOD.FECHA_REG, 'DD/MM/YYYY'),null) AS fecha_reg,\n" +
					" nvl(TO_char(UNCOD.FECHA_MODIF, 'DD/MM/YYYY'),null) AS fecha_modif,\n" +
					" UNCOD.EST_EDIT AS est_edit,\n" +
					" UNCOD.MOTIVO_RECHAZO AS motivo_rechazo, \n" +
					" to_char(UNCOD.FECHA_MODIF, 'yyyy') AS reg_year, \n " +
					" to_char(UNCOD.FECHA_MODIF, 'mm') As reg_month \n " +
					" FROM \n" +
					" WEBQPROTESORERIA.UNIDAD_CODIGO UNCOD \n" +
					" INNER JOIN WEBQPROTESORERIA.FACULTAD FAC ON UNCOD.COD_FAC = FAC.CODIGO \n" +
					" WHERE \n" +
//					" UNCOD.ESTADO_SOLICITUD IN(0, 1) AND \n" +
					" UNCOD.UD_ID != 0 AND \n" +
					" FAC.UD_ID = #{udId_padreRegistrado} \n" +
					" ORDER BY UNCOD.FECHA_MODIF DESC")
    @Results(value = {@Result(javaType = UnidadCodigoModel.class),
    		@Result(property = "udIdUnidad", column = "ud_id_unidad"),
    	    @Result(property = "udDscUnidad", column = "ud_dsc_unidad"),
    	    @Result(property = "codFacUnidad", column = "cod_fac_unidad"),	    
    	    @Result(property = "numUnidad", column = "num_unidad"),
    	    @Result(property = "estUnidad", column = "est_unidad"),
    	    @Result(property = "estSolicitudUnidad", column = "est_solicitud_unidad"),
    	    @Result(property = "udIdSolicitante", column = "ud_id_solicitante"),
    	    @Result(property = "udIdFac", column = "ud_id_fac"),
    	    @Result(property = "udDscFac", column = "ud_dsc_fac"),
    	    @Result(property = "fechaReg", column = "fecha_reg"),
            @Result(property = "fechaModif", column = "fecha_modif"),
            @Result(property = "estEdit", column = "est_edit"),
            @Result(property = "motivoRechazo", column = "motivo_rechazo"),
            @Result(property = "regYear", column = "reg_year"),
            @Result(property = "regMonth", column = "reg_month")
    })
    public List<UnidadCodigoModel> unidadesCodigoXDependencia(@Param("udId_padreRegistrado")String udIdPadreRegistrado);
	
	
	@Select(value = " SELECT  \n" +
			" UNCOD.UD_ID AS ud_id_unidad, \n" +
			" UNCOD.UD_DSC AS ud_dsc_unidad, \n" +
			" UNCOD.COD_FAC AS cod_fac_unidad, \n" +
			" UNCOD.CODIGO_UNIDAD AS num_unidad, \n" +
			" UNCOD.ESTADO AS est_unidad, \n" +
			" UNCOD.ESTADO_SOLICITUD AS est_solicitud_unidad, \n" +
			" UNCOD.UD_ID_SOL AS ud_id_solicitante, \n" +
			" FAC.UD_ID AS ud_id_fac, \n" +
			" FAC.UD_DSC AS ud_dsc_fac, \n" +
			" nvl(TO_char(UNCOD.FECHA_REG, 'DD/MM/YYYY'),null) AS fecha_reg,\n" +
			" nvl(TO_char(UNCOD.FECHA_MODIF, 'DD/MM/YYYY'),null) AS fecha_modif,\n" +
			" UNCOD.EST_EDIT AS est_edit,\n" +
			" UNCOD.MOTIVO_RECHAZO AS motivo_rechazo, \n" +
			" to_char(UNCOD.FECHA_MODIF, 'yyyy') AS reg_year, \n " +
			" to_char(UNCOD.FECHA_MODIF, 'mm') As reg_month \n " +
			" FROM \n" +
			" WEBQPROTESORERIA.UNIDAD_CODIGO UNCOD \n" +
			" INNER JOIN WEBQPROTESORERIA.FACULTAD FAC ON UNCOD.COD_FAC = FAC.CODIGO \n" +
			" WHERE \n" +
//			" UNCOD.ESTADO_SOLICITUD IN(0, 1) AND \n" +
			" UNCOD.UD_ID != 0 AND \n" +
			" FAC.UD_ID = #{udId_padreRegistrado} AND \n"+
			" UNCOD.CODIGO_UNIDAD LIKE TRIM('%'||#{codUnidad}||'%')")
	@Results(value = {@Result(javaType = UnidadCodigoModel.class),
	    @Result(property = "udIdUnidad", column = "ud_id_unidad"),
	    @Result(property = "udDscUnidad", column = "ud_dsc_unidad"),
	    @Result(property = "codFacUnidad", column = "cod_fac_unidad"),	    
	    @Result(property = "numUnidad", column = "num_unidad"),
	    @Result(property = "estUnidad", column = "est_unidad"),
	    @Result(property = "estSolicitudUnidad", column = "est_solicitud_unidad"),
	    @Result(property = "udIdSolicitante", column = "ud_id_solicitante"),
	    @Result(property = "udIdFac", column = "ud_id_fac"),
	    @Result(property = "udDscFac", column = "ud_dsc_fac"),
	    @Result(property = "fechaReg", column = "fecha_reg"),
        @Result(property = "fechaModif", column = "fecha_modif"),
        @Result(property = "estEdit", column = "est_edit"),
        @Result(property = "motivoRechazo", column = "motivo_rechazo"),
        @Result(property = "regYear", column = "reg_year"),
        @Result(property = "regMonth", column = "reg_month")
	})
	public List<UnidadCodigoModel> unidadesCodigoXDep(@Param("udId_padreRegistrado")String udIdPadreRegistrado, @Param("codUnidad")String codUnidad);
	
	
	 @Insert(value="{CALL REGISTRAR_UNIDAD_CODIGO ("
				+ IN_UDID_UNIDAD + ","
				+ IN_NUM_UNIDAD + ","
				+"#{unidad.typeOfUser, mode=IN, jdbcType=INTEGER},"
				+"#{unidad.status, mode=OUT, jdbcType=INTEGER},"
				+"#{unidad.errorMsg, mode=OUT, jdbcType=VARCHAR}"
				+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarUnidadCodigo(@Param(STR_UNIDAD) UnidadCodigoModel unidad);
	 
	
	 @Update(value="{CALL ACTUALIZAR_UNIDAD_CODIGO ("
			 	+ IN_UDID_UNIDAD + ","			 	
			 	+ IN_NUM_UNIDAD + ","
				+"#{unidad.status, mode=OUT, jdbcType=INTEGER}"
				+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarUnidadCodigo(@Param(STR_UNIDAD) UnidadCodigoModel unidad);
	 
	
	@Update(value="{CALL VOLVER_ENVIAR_UNI_COD ("
				+ IN_UDID_UNIDAD + ","
				+ IN_NUM_UNIDAD + ","
				+"#{unidad.status, mode=OUT, jdbcType=INTEGER}"
				+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void volverEnviarUnidadCodigo(@Param(STR_UNIDAD) UnidadCodigoModel unidad);
	  
	 
	@Delete(value="{CALL DELETE_UNIDAD_CODIGO ("
				+ IN_UDID_UNIDAD + ","
				+ IN_NUM_UNIDAD + ","
				+"#{unidad.status, mode=OUT, jdbcType=INTEGER},"
				+"#{unidad.errorMsg, mode=OUT, jdbcType=VARCHAR}"
				+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void deleteUnidadCodigo(@Param(STR_UNIDAD) UnidadCodigoModel unidad);
	
	@Select(value = " SELECT \n" +
					" FAC.UD_ID AS UD_ID_PADRE, \n" +
					" FAC.UD_DSC AS NOMBRE_PADRE, \n" +
					" UNICOD.UD_ID AS UD_ID_UNIDAD, \n" +
					" UNICOD.UD_DSC AS NOMBRE_UNIDAD, \n" +
					" UNICOD.CODIGO_UNIDAD AS NUM_UNIDAD, \n" +
					" UNICOD.ESTADO AS ESTADO, \n" +
					" UNICOD.ESTADO_SOLICITUD AS ESTADO_SOLICITUD, \n" +
					" nvl(TO_char(UNICOD.FECHA_REG, 'DD/MM/YYYY'),null) AS FECHA_REG,\n" +
					" nvl(TO_char(UNICOD.FECHA_APROBADO, 'DD/MM/YYYY'),null) AS FECHA_APROBADO,\n" +
					" nvl(TO_char(UNICOD.FECHA_RECHAZO, 'DD/MM/YYYY'),null) AS FECHA_RECHAZO,\n" +
					" nvl(TO_char(UNICOD.FECHA_MODIF, 'DD/MM/YYYY'),null) AS FECHA_MODIF,\n" +
					" UNICOD.MOTIVO_RECHAZO AS MOTIVO_RECHAZO, \n" +
					" to_char(UNICOD.FECHA_MODIF, 'yyyy') AS reg_year, \n " +
					" to_char(UNICOD.FECHA_MODIF, 'mm') As reg_month, \n " +
					" UNICOD.EST_EDIT AS est_edit\n" +
					" FROM \n" +
					" WEBQPROTESORERIA.UNIDAD_CODIGO UNICOD \n" +
					" INNER JOIN WEBQPROTESORERIA.FACULTAD FAC ON UNICOD.COD_FAC = FAC.CODIGO \n" +
					" WHERE \n" +
					" UNICOD.UD_ID <> 0 AND \n" +
					" UNICOD.UD_ID IS NOT NULL \n" +
					" ORDER BY \n" +
					" UNICOD.FECHA_MODIF ASC")
	@Results(value = {@Result(javaType = UnidadCodigoModel.class),
	@Result(property = "udIdPadre", column = "UD_ID_PADRE"),
	@Result(property = "udDscFac", column = "NOMBRE_PADRE"),
	@Result(property = "udIdUnidad", column = "UD_ID_UNIDAD"),
	@Result(property = "udDscUnidad", column = "NOMBRE_UNIDAD"),
	@Result(property = "numUnidad", column = "NUM_UNIDAD"),
	@Result(property = "estUnidad", column = "ESTADO"),
	@Result(property = "estSolicitudUnidad", column = "ESTADO_SOLICITUD"),
	@Result(property = "fechaReg", column = "FECHA_REG"),
	@Result(property = "fechaAprobado", column = "FECHA_APROBADO"),
	@Result(property = "fechaRechazo", column = "FECHA_RECHAZO"),
	@Result(property = "fechaModif", column = "FECHA_MODIF"),
	@Result(property = "motivoRechazo", column = "MOTIVO_RECHAZO"),
	@Result(property = "regYear", column = "reg_year"),
	@Result(property = "regMonth", column = "reg_month"),
	@Result(property = "estEdit", column = "est_edit")
	})
	public List<UnidadCodigoModel> codigoUnidadesTodos();
	
	
	@Update(value = "UPDATE WEBQPROTESORERIA.UNIDAD_CODIGO \n " +
			" SET ESTADO = 1, \n " +
			" ESTADO_SOLICITUD = 1, \n " +
			" FECHA_APROBADO = SYSDATE \n" +
			" WHERE  UD_ID= #{ud_id}")
	public void aprobarUnidadCodigo(@Param("ud_id") Integer udId);
	
	@Update(value = "UPDATE WEBQPROTESORERIA.UNIDAD_CODIGO \n " +
			" SET ESTADO_SOLICITUD = 2, \n " +
			" ESTADO = 0, \n " +
			" FECHA_RECHAZO = SYSDATE, \n" +
			" MOTIVO_RECHAZO = #{motivo_rechazo} \n " +
			" WHERE  UD_ID = #{ud_id} ")
	public void rechazarUnidadCodigo(@Param("ud_id") Integer udId, @Param("motivo_rechazo") String motivoRechazo);

}
