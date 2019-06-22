package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.ConceptoMaestroModel;

public interface ConceptoMaestroPersistence {
	
	String PROP_CONCEPTO = "concepto";
	String PROP_CPESTADO = "cpEst";
	String PROP_FACTURABLE = "facturable";
	String PROP_IGV = "igv";
	
	String COL_CONCEPTO = "concepto";
	String COL_CPESTADO = "cpEst";
	String COL_FACTURABLE = "facturable";
	String COL_IGV = "igv";
	
	@Select(value = "SELECT \n " +
			" CPAGO.ID_CPAGO AS id_cpago, \n " +
			" CPAGO.COD_CPAGO AS cod_cpago, \n " +
			" CPAGO.CONCEPTO AS concepto, \n " +
			" CPAGO.ID_TIPO_CPAGO AS id_tipo_cpago, \n " +
			" CPAGO.EST AS cpEst, \n " +
			" CPAGO.FACTURABLE AS facturable, \n " +
			" CPAGO.USUARIO AS usuario, \n " +
			" CPAGO.IGV AS igv, \n " +
			" CPAGO.FECHA_REG AS fecha_reg, \n " +
			" CPAGO.EST_SOLICITUD AS est_solicitud, \n " +
			" CPAGO.COD_BANCO AS cod_banco, \n " +
			" BAN.BANRAZSOC AS nombre_banco, \n " +
			" TCP.NOMBRE AS nombre_tipo_cpago \n " +
			" FROM \n " +
			" WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO \n " +
			" INNER JOIN WEBQPROTESORERIA.BANCO BAN ON CPAGO.COD_BANCO = BAN.BANCOD \n " +
			" INNER JOIN WEBQPROTESORERIA.TIPO_CONCEPTO_PAGO TCP ON CPAGO.ID_TIPO_CPAGO = TCP.ID_TIPO_CPAGO \n " +
			" WHERE \n " +
			" CPAGO.EST = 1 AND \n " +
			" TCP.EST = 1 \n " +
			" ORDER BY \n " +
			" id_cpago ASC")
	@Results(value = {@Result(javaType = ConceptoMaestroModel.class),
	    @Result(property = "idCpago", column = "id_cpago"),
	    @Result(property = "codCpago", column = "cod_cpago"),
	    @Result(property = PROP_CONCEPTO, column = COL_CONCEPTO),
	    @Result(property = "idTipoCpago", column = "id_tipo_cpago"),
	    @Result(property = PROP_CPESTADO, column = COL_CPESTADO),
	    @Result(property = PROP_FACTURABLE, column = COL_FACTURABLE),
	    @Result(property = PROP_IGV, column = COL_IGV),
	    @Result(property = "fechaReg", column = "fecha_reg"),
	    @Result(property = "codBanco", column = "cod_banco"),
	    @Result(property = "estSolicitud", column = "est_solicitud"),
	    @Result(property = "nombreBanco", column = "nombre_banco"),
	    @Result(property = "nombreTipoCpago", column = "nombre_tipo_cpago")
	})
	public List<ConceptoMaestroModel> conceptosPagoMaestro();
	
	@Select(value = "SELECT \n " +
			" CPAGO.ID_CPAGO AS id_cpago, \n " +
			" CPAGO.COD_CPAGO AS cod_cpago, \n " +
			" CPAGO.CONCEPTO AS concepto, \n " +
			" CPAGO.ID_TIPO_CPAGO AS id_tipo_cpago, \n " +
			" CPAGO.EST AS cpEst, \n " +
			" CPAGO.FACTURABLE AS facturable, \n " +
			" CPAGO.USUARIO AS usuario, \n " +
			" CPAGO.IGV AS igv, \n " +
			" CPAGO.FECHA_REG AS fecha_reg, \n " +
			" CPAGO.EST_SOLICITUD AS est_solicitud, \n " +
			" CPAGO.COD_BANCO AS cod_banco, \n " +
			" BAN.BANRAZSOC AS nombre_banco, \n " +
			" TCP.NOMBRE AS nombre_tipo_cpago \n " +
			" FROM \n " +
			" WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO \n " +
			" INNER JOIN WEBQPROTESORERIA.BANCO BAN ON CPAGO.COD_BANCO = BAN.BANCOD \n " +
			" INNER JOIN WEBQPROTESORERIA.TIPO_CONCEPTO_PAGO TCP ON CPAGO.ID_TIPO_CPAGO = TCP.ID_TIPO_CPAGO \n " +
			" WHERE \n " +
			" CPAGO.EST_SOLICITUD != 2 \n" +
			" AND TCP.EST = 1 \n" +
			" AND TRIM(CPAGO.COD_CPAGO) like TRIM(#{codigo}||'%') " +			//OR  UPPER(TRIM(CPAGO.CONCEPTO)) LIKE UPPER(TRIM('%'||#{nombre}||'%'))
			" AND ( CPAGO.UD_ID_SOL = #{udIdDepPadre} OR CPAGO.UD_ID_SOL IS NULL )" +
			" ORDER BY \n " +
			" id_cpago ASC")
	@Results(value = {@Result(javaType = ConceptoMaestroModel.class),
	    @Result(property = "idCpago", column = "id_cpago"),
	    @Result(property = "codCpago", column = "cod_cpago"),
	    @Result(property = PROP_CONCEPTO, column = COL_CONCEPTO),
	    @Result(property = "idTipoCpago", column = "id_tipo_cpago"),
	    @Result(property = PROP_CPESTADO, column = COL_CPESTADO),
	    @Result(property = PROP_FACTURABLE, column = COL_FACTURABLE),
	    @Result(property = PROP_IGV, column = COL_IGV),
	    @Result(property = "fechaReg", column = "fecha_reg"),
	    @Result(property = "codBanco", column = "cod_banco"),
	    @Result(property = "estSolicitud", column = "est_solicitud"),
	    @Result(property = "nombreBanco", column = "nombre_banco"),
	    @Result(property = "nombreTipoCpago", column = "nombre_tipo_cpago")
	})
	public List<ConceptoMaestroModel> conceptosPagoXnombreCodigo(@Param("nombre") String nombre, @Param("codigo") String codigo, @Param("udIdDepPadre") String udIdDepPadre);

}
