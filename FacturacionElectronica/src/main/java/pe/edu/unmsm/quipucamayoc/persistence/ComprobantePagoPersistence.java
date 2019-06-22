
package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pe.edu.unmsm.quipucamayoc.model.BandFactModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobantePagoModel;
import pe.edu.unmsm.quipucamayoc.model.CorreoModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleModel;
import pe.edu.unmsm.quipucamayoc.model.EstablecimientoBoletaYFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.EstadoModel;
import pe.edu.unmsm.quipucamayoc.model.NotificationModel;
import pe.edu.unmsm.quipucamayoc.model.RangoFecha;
import pe.edu.unmsm.quipucamayoc.util.Constantes;

public interface ComprobantePagoPersistence {

	@Select(value = Constantes.SELECT + "comprobante.ANIO,\n" + "comprobante.MES,\n" + "comprobante.TIPO,\n"
			+ "comprobante.COD_ESTAB,\n" + "comprobante.SERIE,\n" + "comprobante.FECHA_EMISION,\n"
			+ "comprobante.DOC_IDEN,\n" + "comprobante.NOMBRE_CLIENTE,\n" + "comprobante.DIRECCION,\n"
			+ "comprobante.GUIA,\n" + "comprobante.TOTAL,\n" + "comprobante.FORMA_PAGO,\n" + "comprobante.MONEDA,\n"
			+ "comprobante.IMPORTE_OPERACION,\n" + "comprobante.BANCO,\n" + "comprobante.N_OPERACION,\n"
			+ "comprobante.FECHA_OPERACION,\n" + "comprobante.SUB_TOTAL,\n" + "comprobante.IGV,\n"
			+ "comprobante.P_CAMBIO,\n" + "comprobante.UNIDAD,\n" + "comprobante.FACULTAD,\n" + "comprobante.USUARIO,\n"
			+ "NVL(comprobante.ESTADO,'00') AS ESTADO ,\n" + "comprobante.EST_USO,\n" + "comprobante.TIPO_DOC,\n" + "NVL(comprobante.ID,'00') AS ID,\n"
			+ "comprobante.NOMBRE,\n" + "NVL(comprobante.ENVIAR,1) AS ENVIAR,\n" 
			+ "comprobante.OBSERVACION, comprobante.NOTA_ASOC \n"
			+ Constantes.FROM + "(\n" + "SELECT * from QPRODATAQUIPU.TB_HIST_USU_PERF where QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL=#{usuario}" +") establecimiento,\n"
			+ "(\n" + "	SELECT\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.ANIO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.MES,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.TIPO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.SERIE,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.FECHA_EMISION,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.DOC_IDEN,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.NOMBRE_CLIENTE,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.DIRECCION,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.GUIA,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.TOTAL,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.FORMA_PAGO,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.MONEDA,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.IMPORTE_OPERACION,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.BANCO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.N_OPERACION,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.FECHA_OPERACION,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.SUB_TOTAL,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.IGV,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.P_CAMBIO,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.UNIDAD,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.FACULTAD,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.USUARIO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.ESTADO,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.EST_USO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.TIPO_DOC,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.UBIGEO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.MONTO_LETRAS,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.DETRACCION,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.GRAVADA,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.EXONERADA,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.INAFECTA,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.QR,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.OBSERVACION,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID AS udId,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CODIGO_ESTAB,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_FACT,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.TELEFONO,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CORREO,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_BOLE,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.NUM_RUC,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.TIP_DOCU,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.NUM_DOCU,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.FEC_CARG,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.FEC_GENE,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.FEC_ENVI,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.DES_OBSE,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.NOM_ARCH,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.IND_SITU,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.TIP_ARCH,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.FIRM_DIGITAL,\n"
			+ "	WEBQPROTESORERIA.SITUACION_FACTURADOR.ID,\n" + "	WEBQPROTESORERIA.SITUACION_FACTURADOR.NOMBRE,\n"
			+ " DECODE (WEBQPROTESORERIA.NOTA_CREDITO.TIPO_ASOC, 1, 'B',2, 'F','') || WEBQPROTESORERIA.NOTA_CREDITO.COD_ESTAB || '-'|| WEBQPROTESORERIA.NOTA_CREDITO.SERIE  AS NOTA_ASOC, \n"
			+ "	WEBQPROTESORERIA.SITUACION_FACTURADOR.ENVIAR\n" + "	FROM\n" + "		WEBQPROTESORERIA.TXXXX_BANDFACT\n"
			+ "	INNER JOIN WEBQPROTESORERIA.SITUACION_FACTURADOR ON WEBQPROTESORERIA.TXXXX_BANDFACT.IND_SITU = WEBQPROTESORERIA.SITUACION_FACTURADOR.ID\n"
			+ "	RIGHT JOIN WEBQPROTESORERIA.COMPROB_PAGO ON WEBQPROTESORERIA.TXXXX_BANDFACT.NUM_DOCU = DECODE (#{rango.tipo}, 1, 'B', 'F') || WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB || '-' || WEBQPROTESORERIA.COMPROB_PAGO.SERIE\n"
			+ " LEFT JOIN WEBQPROTESORERIA.NOTA_CREDITO ON WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB=WEBQPROTESORERIA.NOTA_CREDITO.COD_ESTAB_ASOC AND WEBQPROTESORERIA.COMPROB_PAGO.SERIE=WEBQPROTESORERIA.NOTA_CREDITO.SERIE_ASOC AND WEBQPROTESORERIA.COMPROB_PAGO.TIPO=WEBQPROTESORERIA.NOTA_CREDITO.TIPO_ASOC \n"
			+ "	INNER JOIN WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO ON WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB = WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CODIGO_ESTAB\n"
			+ "	WHERE\n" + "		WEBQPROTESORERIA.COMPROB_PAGO.TIPO = #{rango.tipo}\n" + ") comprobante\n"
			+ "WHERE establecimiento.UD_ID=comprobante.udId\n"
			+ "AND FECHA_EMISION BETWEEN TO_DATE(#{rango.fechaInicial}, 'dd/mm/yyyy hh24:mi:ss')\n"
			+ "AND TO_DATE(#{rango.fechaFinal},'dd/mm/yyyy hh24:mi:ss')\n" + "ORDER BY\n" + "COD_ESTAB ASC,\n"
			+ "SERIE ASC")
	@Results(value = { @Result(property = "anio", column = "ANIO"), @Result(property = "mes", column = "MES"),
			@Result(property = "tipo", column = "TIPO"), @Result(property = "codEst", column = "COD_ESTAB"),
			@Result(property = "serie", column = "SERIE"), @Result(property = "fechaEmision", column = "FECHA_EMISION"),
			@Result(property = "docIden", column = "DOC_IDEN"), @Result(property = "nombre", column = "NOMBRE_CLIENTE"),
			@Result(property = "direccion", column = "DIRECCION"), @Result(property = "guia", column = "GUIA"),
			@Result(property = "total", column = "TOTAL"), @Result(property = "formaPago", column = "FORMA_PAGO"),
			@Result(property = "moneda", column = "MONEDA"),
			@Result(property = "importeOperacion", column = "IMPORTE_OPERACION"),
			@Result(property = "banco", column = "BANCO"), @Result(property = "nOperacion", column = "N_OPERACION"),
			@Result(property = "fechaOperacion", column = "FECHA_OPERACION"),
			@Result(property = "subTotal", column = "SUB_TOTAL"), @Result(property = "igv", column = "IGV"),
			@Result(property = "pCambio", column = "P_CAMBIO"), @Result(property = "unidad", column = "UNIDAD"),
			@Result(property = "facultad", column = "FACULTAD"), @Result(property = "usuario", column = "USUARIO"),
			@Result(property = "estado", column = "ESTADO"), @Result(property = "estUso", column = "EST_USO"),
			@Result(property = "tipoDoc", column = "TIPO_DOC"), @Result(property = "id", column = "ID"),
			@Result(property = "nombre2", column = "NOMBRE"), @Result(property = "enviar", column = "ENVIAR"),
			@Result(property = "observacion", column = "OBSERVACION"),@Result(property = "notaAsoc", column = "NOTA_ASOC") })
	List<ComprobantePagoModel> comprobantePago(@Param("rango") RangoFecha rango, @Param("usuario") String usuario);

	@Select(value =  Constantes.SELECT +" comprobante.TIPO_NOTA,comprobante.MOTIVO, \n" +
		    "                        comprobante.ANIO,comprobante.MES, \n" +
			"                        comprobante.TIPO,comprobante.COD_ESTAB, \n" +
			"                        comprobante.SERIE,comprobante.FECHA_EMISION, \n" +
			"                        comprobante.DOC_IDEN,comprobante.NOMBRE_CLIENTE, \n" +
			"                        comprobante.DIRECCION,comprobante.GUIA, \n" +
			"                        comprobante.TOTAL, comprobante.FORMA_PAGO, \n" +
			"                        comprobante.MONEDA,comprobante.IMPORTE_OPERACION, \n" +
			"                        comprobante.BANCO,comprobante.N_OPERACION, \n" +
			"                        comprobante.FECHA_OPERACION,comprobante.SUB_TOTAL, \n" +
			"                        comprobante.IGV,comprobante.P_CAMBIO, \n" +
			"                        comprobante.UNIDAD,comprobante.FACULTAD, \n" +
			"                        comprobante.USUARIO,comprobante.ESTADO_DESCR, \n" +
			"                        NVL(comprobante.ESTADO,'00') AS ESTADO,comprobante.TIPO_DOC, \n" +
			"                        NVL(comprobante.ENVIAR,1) AS ENVIAR \n" +
			"            FROM (SELECT * from QPRODATAQUIPU.TB_HIST_USU_PERF where QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL=#{usuario}) establecimiento,\n" +
			"            (     SELECT     \n" +
			"                                        upper(WEBQPROTESORERIA.TIPO_NOTA_CREDITO.DESCRIPCION) AS TIPO_NOTA, \n" +
			"                                WEBQPROTESORERIA.NOTA_CREDITO.MOTIVO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.ANIO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.MES, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.TIPO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.SERIE, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.FECHA_EMISION AS FECHA_EMISION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.DOC_IDEN, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.NOMBRE_CLIENTE, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.DIRECCION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.GUIA, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.TOTAL, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.FORMA_PAGO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.MONEDA, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.IMPORTE_OPERACION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.BANCO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.N_OPERACION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.FECHA_OPERACION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.SUB_TOTAL, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.IGV, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.P_CAMBIO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.UNIDAD, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.FACULTAD, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.USUARIO, \n" +
			"                                WEBQPROTESORERIA.SITUACION_FACTURADOR.NOMBRE AS ESTADO_DESCR, \n" +
			"                                WEBQPROTESORERIA.SITUACION_FACTURADOR.ID AS ESTADO, \n" +
			"                                WEBQPROTESORERIA.SITUACION_FACTURADOR.ENVIAR AS ENVIAR, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.TIPO_DOC, \n" +
			"                                WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID AS udId    \n" +
			"                FROM  WEBQPROTESORERIA.TIPO_NOTA_CREDITO,    WEBQPROTESORERIA.NOTA_CREDITO ,   WEBQPROTESORERIA.TXXXX_BANDFACT\n" +
			"                INNER JOIN WEBQPROTESORERIA.SITUACION_FACTURADOR ON WEBQPROTESORERIA.TXXXX_BANDFACT.IND_SITU = WEBQPROTESORERIA.SITUACION_FACTURADOR.ID\n" +
			"                RIGHT JOIN WEBQPROTESORERIA.COMPROB_PAGO ON WEBQPROTESORERIA.TXXXX_BANDFACT.NUM_DOCU = DECODE (#{rango.tipo}, 4, 'F',5,'B','') || WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB || '-' || WEBQPROTESORERIA.COMPROB_PAGO.SERIE\n" +
			"                INNER JOIN WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO ON WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB = WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CODIGO_ESTAB\n" +
			"                WHERE        \n" +
			"                 WEBQPROTESORERIA.COMPROB_PAGO.TIPO = #{rango.tipo} AND\n" +
			"                 WEBQPROTESORERIA.NOTA_CREDITO.TIPO=WEBQPROTESORERIA.COMPROB_PAGO.TIPO  AND WEBQPROTESORERIA.NOTA_CREDITO.COD_ESTAB= WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB AND WEBQPROTESORERIA.NOTA_CREDITO.SERIE=WEBQPROTESORERIA.COMPROB_PAGO.SERIE\n" +
			"                 and  WEBQPROTESORERIA.TIPO_NOTA_CREDITO.ID_TIPO=WEBQPROTESORERIA.NOTA_CREDITO.TIPO_NOTA ) comprobante\n" +
			"            WHERE establecimiento.UD_ID=comprobante.udId\n" +
			"            AND FECHA_EMISION BETWEEN TO_DATE(#{rango.fechaInicial}, 'dd/mm/yyyy hh24:mi:ss')\n" +
			"            AND TO_DATE(#{rango.fechaFinal},'dd/mm/yyyy hh24:mi:ss') ORDER BY COD_ESTAB ASC,\n" +
			"            TO_NUMBER(SERIE) ASC")
	@Results(value = { @Result(property = "tipoNota", column = "TIPO_NOTA"),
			@Result(property = "motivo", column = "MOTIVO"), @Result(property = "anio", column = "ANIO"),
			@Result(property = "mes", column = "MES"), @Result(property = "tipo", column = "TIPO"),
			@Result(property = "codEst", column = "COD_ESTAB"), @Result(property = "serie", column = "SERIE"),
			@Result(property = "fechaEmision", column = "FECHA_EMISION"),
			@Result(property = "docIden", column = "DOC_IDEN"), @Result(property = "nombre", column = "NOMBRE_CLIENTE"),
			@Result(property = "direccion", column = "DIRECCION"), @Result(property = "guia", column = "GUIA"),
			@Result(property = "total", column = "TOTAL"), @Result(property = "formaPago", column = "FORMA_PAGO"),
			@Result(property = "moneda", column = "MONEDA"),@Result(property = "enviar", column = "ENVIAR"),
			@Result(property = "importeOperacion", column = "IMPORTE_OPERACION"),
			@Result(property = "banco", column = "BANCO"), @Result(property = "nOperacion", column = "N_OPERACION"),
			@Result(property = "fechaOperacion", column = "FECHA_OPERACION"),
			@Result(property = "subTotal", column = "SUB_TOTAL"), @Result(property = "igv", column = "IGV"),
			@Result(property = "pCambio", column = "P_CAMBIO"), @Result(property = "unidad", column = "UNIDAD"),
			@Result(property = "facultad", column = "FACULTAD"), @Result(property = "usuario", column = "USUARIO"),
			@Result(property = "estadoFacturadorDescr", column = "ESTADO_DESCR"),
			@Result(property = "estadoFacturador", column = "ESTADO"),
			@Result(property = "tipoDoc", column = "TIPO_DOC") })
	List<ComprobantePagoModel> getNotasDeCredito(@Param("rango") RangoFecha rango, @Param("usuario") String usuario);

	@Update("UPDATE COMPROB_PAGO SET ESTADO=#{i.estado} WHERE COD_ESTAB=#{i.codEst} and SERIE=#{i.serie} and TIPO=#{i.tipo}")
	void modificar(@Param("i") ComprobantePagoModel item);

	@Select(value = Constantes.SELECT + "	caja.udId,\n" + "	caja.ESTADO,\n" + "	caja.CODIGO_ESTAB,\n" + "	caja.UD_DSC,\n"
			+ "	caja.DIRECCION,\n" + "	caja.TELEFONO,\n" + "	caja.CORREO,\n" + "	caja.ULT_FACT,\n"+ "	caja.SUNAT,\n"
			+ "	caja.ULT_BOLE\n" + Constantes.FROM + "	(\n" + "		SELECT\n" + "			*\n" + "		FROM\n"
			+ "			UNI_DEP\n" + "		WHERE\n" + "			UD_ID IN (\n" + "				SELECT\n"
			+ "					dep.ud_id\n" + "				FROM\n"
			+ "					qprodataquipu.uni_dep dep START WITH UD_ID = (\n" + "						SELECT\n"
			+ "							DECODE(PERF_COD, 606,10000, UD_ID)\n" + "						FROM\n"
			+ "							QPRODATAQUIPU.TB_HIST_USU_PERF\n" + "						WHERE\n"
			+ "							QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL = #{usuario}\n"
			+ "					) CONNECT BY PRIOR UD_ID = UD_PAD\n" + "				AND ud_fec_cad IS NULL\n"
			+ "			)\n" + "	) establecimiento,\n" + "	(\n" + "		SELECT\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID AS UDID,\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ESTADO,\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CODIGO_ESTAB,\n"
			+ "			QPRODATAQUIPU.UNI_DEP.UD_DSC,\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.DIRECCION,\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.SUNAT,\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.TELEFONO,\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CORREO,\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_FACT,\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_BOLE\n" + "		FROM\n"
			+ "			WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO\n"
			+ "		INNER JOIN QPRODATAQUIPU.UNI_DEP ON WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID = QPRODATAQUIPU.UNI_DEP.UD_ID\n"
			+ "	)  caja\n" + Constantes.WHERE + "	establecimiento.UD_ID = caja.udId")
	@Results(value = { @Result(property = "udId", column = "UD_ID"),
			@Result(property = "codEst", column = "CODIGO_ESTAB"), @Result(property = "ultFact", column = "ULT_FACT"),
			@Result(property = "ultBole", column = "ULT_BOLE"), @Result(property = "unidad", column = "UD_DSC"),
			@Result(property = "estado", column = "ESTADO"), @Result(property = "direccion", column = "DIRECCION"),
			@Result(property = "telefono", column = "TELEFONO"), @Result(property = "sunat", column = "SUNAT"),
			@Result(property = "correo", column = "CORREO") })
	List<EstablecimientoBoletaYFacturaModel> listarEstablecimientoBoletaYFacturaxUsuario(
			@Param("usuario") String usuario);
	
	@Select(value = Constantes.SELECT 
			+ " establecimiento.ud_id AS UD_ID, establecimiento.UD_DSC AS UD_DSC"
			+ " FROM(   SELECT * FROM UNI_DEP "
			+ "		WHERE "
			+ "		UD_ID IN ( select dep.ud_id from qprodataquipu.uni_dep dep START " 
			+ "				WITH UD_ID = ( SELECT "
			+ "						QPRODATAQUIPU.TB_HIST_USU_PERF.UD_ID "  
			+ "						FROM QPRODATAQUIPU.TB_HIST_USU_PERF  "
			+ "						WHERE QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL = #{usuario} ) "
			+ "				CONNECT BY PRIOR UD_ID=UD_PAD and ud_fec_cad is null    )) establecimiento " 
			+ " INNER JOIN WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO ON establecimiento.UD_ID = WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID "
			+ " ORDER BY establecimiento.ud_id ASC")
	@Results(value = { 
			@Result(property = "udId", column = "UD_ID"),
			@Result(property = "unidad", column = "UD_DSC") })
	List<EstablecimientoBoletaYFacturaModel> listarEstablecimientosXFacultad(@Param("usuario") String usuario);

	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ESTADO,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.SUNAT,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CODIGO_ESTAB,\n" + "QPRODATAQUIPU.UNI_DEP.UD_DSC,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.DIRECCION,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.TELEFONO,\n" + "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CORREO,\n"
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_FACT,\n" + "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_BOLE\n"
			+ Constantes.FROM + "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO\n"
			+ "INNER JOIN QPRODATAQUIPU.UNI_DEP ON WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID = QPRODATAQUIPU.UNI_DEP.UD_ID")
	@Results(value = { @Result(property = "udId", column = "UD_ID"),
			@Result(property = "codEst", column = "CODIGO_ESTAB"), @Result(property = "ultFact", column = "ULT_FACT"),
			@Result(property = "ultBole", column = "ULT_BOLE"), @Result(property = "unidad", column = "UD_DSC"),
			@Result(property = "estado", column = "ESTADO"), @Result(property = "direccion", column = "DIRECCION"),
			@Result(property = "telefono", column = "TELEFONO"), @Result(property = "sunat", column = "SUNAT"),
			@Result(property = "correo", column = "CORREO") })
	List<EstablecimientoBoletaYFacturaModel> listarEstablecimientoBoletaYFactura();

	@Insert("INSERT INTO WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO(UD_ID,ESTADO,CODIGO_ESTAB,ULT_FACT,ULT_BOLE,DIRECCION,TELEFONO,CORREO) VALUES (#{i.udId},#{i.estado},lpad(#{i.codEst},3,'0'),lpad(#{i.ultFact},8,'0'),lpad(#{i.ultBole},8,'0'),#{i.direccion},#{i.telefono},#{i.correo})")
	void insertarEstablecimientoBoletaYFactura(@Param("i") EstablecimientoBoletaYFacturaModel item);

	@Update("UPDATE WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO SET DIRECCION=#{i.direccion},TELEFONO=#{i.telefono},CORREO=#{i.correo},SUNAT=#{i.sunat} WHERE CODIGO_ESTAB=#{i.codEst}")
	void modificarEstablecimientoBoletaYFactura(@Param("i") EstablecimientoBoletaYFacturaModel i);

	@Delete("DELETE WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO WHERE CODIGO_ESTAB=#{i.codEst}")
	void borrarEstablecimientoBoletaYFactura(@Param("i") EstablecimientoBoletaYFacturaModel i);

	@Update("UPDATE WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO SET ESTADO=#{i.estado} WHERE CODIGO_ESTAB=#{i.codEst}")
	void modificarEstadoEstablecimientoBoletaYFactura(@Param("i") EstablecimientoBoletaYFacturaModel item);
	
	@Update("UPDATE COMPROB_PAGO SET ESTADO=#{i.tipo} WHERE COD_ESTAB=#{i.establecimiento} and SERIE=#{i.serie} and TIPO=#{i.comprobante}")
	void estado(@Param("i") EstadoModel i);

	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.COMPROB_PAGO.ANIO,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.MES,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.TIPO,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.SERIE,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.FECHA_EMISION,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.DOC_IDEN,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.NOMBRE_CLIENTE,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.DIRECCION,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.GUIA,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.TOTAL,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.FORMA_PAGO,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.MONEDA,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.IMPORTE_OPERACION,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.BANCO,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.N_OPERACION,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.FECHA_OPERACION,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.SUB_TOTAL,WEBQPROTESORERIA.COMPROB_PAGO.GRAVADA,WEBQPROTESORERIA.COMPROB_PAGO.EXONERADA,WEBQPROTESORERIA.COMPROB_PAGO.INAFECTA, \n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.IGV,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.P_CAMBIO,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.UNIDAD,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.FACULTAD,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.USUARIO,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.ESTADO,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.EST_USO,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.TIPO_DOC,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.UBIGEO,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.MONTO_LETRAS,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.DETRACCION,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.TIPO || WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB || WEBQPROTESORERIA.COMPROB_PAGO.SERIE TEMP\n"
			+ Constantes.FROM + "WEBQPROTESORERIA.COMPROB_PAGO\n" + Constantes.WHERE + "WEBQPROTESORERIA.COMPROB_PAGO.TIPO = #{tipo} AND\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB = #{establecimiento} AND\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.SERIE = #{serie}")
	@Results(value = { @Result(property = "anio", column = "ANIO"), @Result(property = "mes", column = "MES"),
			@Result(property = "tipo", column = "TIPO"), @Result(property = "establecimiento", column = "COD_ESTAB"),
			@Result(property = "serie", column = "SERIE"), @Result(property = "emision", column = "FECHA_EMISION"),
			@Result(property = "nDocumento", column = "DOC_IDEN"),
			@Result(property = "nombre", column = "NOMBRE_CLIENTE"),
			@Result(property = "direccion", column = "DIRECCION"), @Result(property = "guia", column = "GUIA"),
			@Result(property = "total", column = "TOTAL"), @Result(property = "formaPago", column = "FORMA_PAGO"),
			@Result(property = "moneda", column = "MONEDA"),
			@Result(property = "importe", column = "IMPORTE_OPERACION"), @Result(property = "banco", column = "BANCO"),
			@Result(property = "nOperacion", column = "N_OPERACION"),
			@Result(property = "fechaOperacion", column = "FECHA_OPERACION"),
			@Result(property = "subTotal", column = "SUB_TOTAL"), @Result(property = "igv", column = "IGV"),
			@Result(property = "gravada", column = "gravada"),@Result(property = "inafecta", column = "inafecta"),@Result(property = "exonerada", column = "exonerada"),
			@Result(property = "pCambio", column = "P_CAMBIO"), @Result(property = "unidad", column = "UNIDAD"),
			@Result(property = "facultad", column = "FACULTAD"), @Result(property = "usuario", column = "USUARIO"),
			@Result(property = "estado", column = "ESTADO"), @Result(property = "estUso", column = "EST_USO"),
			@Result(property = "tipoDocumento", column = "TIPO_DOC"), @Result(property = "ubigeo", column = "UBIGEO"),
			@Result(property = "montoLetras", column = "MONTO_LETRAS"),
			@Result(property = "detraccion", column = "DETRACCION"),
			@Result(property = "detraccion", column = "DETRACCION"),
			@Result(property = "detalle", javaType = List.class, column = "TEMP", many = @Many(select = "detalle")) })
	ComprobanteModel comprobante(@Param("tipo") Integer tipo, @Param("establecimiento") String establecimiento,
			@Param("serie") String serie);

	@Select(value = Constantes.SELECT + "WEBQPROTESORERIA.DET_COMPB_PAGO.ID_DET_COMP,\n"
			+ "WEBQPROTESORERIA.DET_COMPB_PAGO.UNI_MEDIDA,\n" + "WEBQPROTESORERIA.DET_COMPB_PAGO.COD_TIPO_IGV,\n"
			+ "WEBQPROTESORERIA.DET_COMPB_PAGO.COD_ESTAB,\n" + "WEBQPROTESORERIA.DET_COMPB_PAGO.SERIE,\n"
			+ "WEBQPROTESORERIA.DET_COMPB_PAGO.ID_ITEM,\n" + "WEBQPROTESORERIA.DET_COMPB_PAGO.CANTIDAD,\n"
			+ "WEBQPROTESORERIA.DET_COMPB_PAGO.DESCRIPCION,\n" + "WEBQPROTESORERIA.DET_COMPB_PAGO.PRECIO_U,\n"
			+ "WEBQPROTESORERIA.DET_COMPB_PAGO.PRECIO_T,\n" + "WEBQPROTESORERIA.DET_COMPB_PAGO.MONEDA,\n"
			+ "WEBQPROTESORERIA.DET_COMPB_PAGO.IGV_U,\n" + "WEBQPROTESORERIA.DET_COMPB_PAGO.IGV_T,\n"
			+ "WEBQPROTESORERIA.DET_COMPB_PAGO.TIPO,\n" + "WEBQPROTESORERIA.DET_COMPB_PAGO.COD_PROD_SUNAT,\n"
			+ "WEBQPROTESORERIA.DET_COMPB_PAGO.DETRACCION\n" + Constantes.FROM + "WEBQPROTESORERIA.DET_COMPB_PAGO\n" + Constantes.WHERE
			+ "WEBQPROTESORERIA.DET_COMPB_PAGO.TIPO||WEBQPROTESORERIA.DET_COMPB_PAGO.COD_ESTAB||WEBQPROTESORERIA.DET_COMPB_PAGO.SERIE = #{temp}")
	@Results(value = { @Result(property = "id", column = "ID_DET_COMP"),
			@Result(property = "uniMedida", column = "UNI_MEDIDA"),
			@Result(property = "codTipoIgv", column = "COD_TIPO_IGV"),
			@Result(property = "establecimiento", column = "COD_ESTAB"), @Result(property = "serie", column = "SERIE"),
			@Result(property = "item", column = "ID_ITEM"), @Result(property = "cantidad", column = "CANTIDAD"),
			@Result(property = "descripcion", column = "DESCRIPCION"),
			@Result(property = "precioU", column = "PRECIO_U"), @Result(property = "precioT", column = "PRECIO_T"),
			@Result(property = "moneda", column = "MONEDA"), @Result(property = "igvU", column = "IGV_U"),
			@Result(property = "igvT", column = "IGV_T"), @Result(property = "tipo", column = "TIPO"),
			@Result(property = "codSunat", column = "COD_PROD_SUNAT"),
			@Result(property = "detraccion", column = "DETRACCION") })
	List<DetalleModel> detalle(@Param("temp") String temp);

	@Select(value = "SELECT \n"
			+ "TIPO_ASOC.INICIAL || WEBQPROTESORERIA.NOTA_CREDITO.COD_ESTAB_ASOC||'-'||WEBQPROTESORERIA.NOTA_CREDITO.SERIE_ASOC AS DOCUMENTO_MODIFICADO, \n"
			+ "WEBQPROTESORERIA.NOTA_CREDITO.TIPO_NOTA, \n" + "WEBQPROTESORERIA.NOTA_CREDITO.MOTIVO, \n"
			+ "TIPO_ASOC.SUNAT_EQ AS TIPO_ASOC,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.ANIO,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.MES,\n"
			+ "TIPO.INICIAL || WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB||'-'||WEBQPROTESORERIA.COMPROB_PAGO.SERIE AS DOCUMENTO,\n"
			+ "to_char(WEBQPROTESORERIA.COMPROB_PAGO.FECHA_EMISION,'YYYY-MM-DD') AS FECHA_EMISION,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.DOC_IDEN,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.NOMBRE_CLIENTE,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.DIRECCION,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.GUIA,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.TOTAL,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.FORMA_PAGO,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.MONEDA,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.IMPORTE_OPERACION,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.GRAVADA,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.INAFECTA,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.EXONERADA,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.SUB_TOTAL,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.IGV,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.P_CAMBIO,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.UNIDAD,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.FACULTAD,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.USUARIO,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.ESTADO,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.EST_USO,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.TIPO_DOC,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.UBIGEO,\n" + "WEBQPROTESORERIA.COMPROB_PAGO.MONTO_LETRAS,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.DETRACCION,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.TIPO || WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB || WEBQPROTESORERIA.COMPROB_PAGO.SERIE TEMP \n"
			+ "FROM WEBQPROTESORERIA.TIPO_COMP_PAGO TIPO_ASOC,\n" + "WEBQPROTESORERIA.TIPO_COMP_PAGO TIPO,\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO\n"
			+ "INNER JOIN WEBQPROTESORERIA.NOTA_CREDITO ON(WEBQPROTESORERIA.COMPROB_PAGO.TIPO=WEBQPROTESORERIA.NOTA_CREDITO.TIPO AND WEBQPROTESORERIA.COMPROB_PAGO.SERIE=WEBQPROTESORERIA.NOTA_CREDITO.SERIE AND WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB=WEBQPROTESORERIA.NOTA_CREDITO.COD_ESTAB) \n"
			+ Constantes.WHERE + "WEBQPROTESORERIA.COMPROB_PAGO.TIPO = #{tipo} AND\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB = #{establecimiento} AND\n"
			+ "WEBQPROTESORERIA.COMPROB_PAGO.SERIE = #{serie} AND \n"
			+ "TIPO_ASOC.ID_TIPO_CPAGO=NOTA_CREDITO.TIPO_ASOC AND \n" + "TIPO.ID_TIPO_CPAGO=NOTA_CREDITO.TIPO")
	@Results(value = { @Result(property = "documentoAsociado", column = "DOCUMENTO_MODIFICADO"),
			@Result(property = "tipoNota", column = "TIPO_NOTA"), @Result(property = "motivo", column = "MOTIVO"),
			@Result(property = "tipoAsocSunat", column = "TIPO_ASOC"), @Result(property = "anio", column = "ANIO"),
			@Result(property = "mes", column = "MES"), @Result(property = "documentoComprobante", column = "DOCUMENTO"),
			@Result(property = "emision", column = "FECHA_EMISION"),
			@Result(property = "nDocumento", column = "DOC_IDEN"),
			@Result(property = "nombre", column = "NOMBRE_CLIENTE"),
			@Result(property = "direccion", column = "DIRECCION"), @Result(property = "guia", column = "GUIA"),
			@Result(property = "total", column = "TOTAL"), @Result(property = "formaPago", column = "FORMA_PAGO"),
			@Result(property = "moneda", column = "MONEDA"),
			@Result(property = "importe", column = "IMPORTE_OPERACION"),
			@Result(property = "gravada", column = "GRAVADA"), @Result(property = "inafecta", column = "INAFECTA"),
			@Result(property = "exonerada", column = "EXONERADA"), @Result(property = "subTotal", column = "SUB_TOTAL"),
			@Result(property = "igv", column = "IGV"), @Result(property = "pCambio", column = "P_CAMBIO"),
			@Result(property = "unidad", column = "UNIDAD"), @Result(property = "facultad", column = "FACULTAD"),
			@Result(property = "usuario", column = "USUARIO"), @Result(property = "estado", column = "ESTADO"),
			@Result(property = "estUso", column = "EST_USO"), @Result(property = "tipoDocumento", column = "TIPO_DOC"),
			@Result(property = "ubigeo", column = "UBIGEO"), @Result(property = "montoLetras", column = "MONTO_LETRAS"),
			@Result(property = "detraccion", column = "DETRACCION"),
			@Result(property = "detraccion", column = "DETRACCION"),
			@Result(property = "detalle", javaType = List.class, column = "TEMP", many = @Many(select = "detalle")) })
	ComprobanteModel comprobanteNotaCredito(@Param("tipo") Integer tipo,
			@Param("establecimiento") String establecimiento, @Param("serie") String serie);
	
	@Select(value = "select tipo.INICIAL||''||comprobante.cod_estab||'-'||comprobante.serie  as documento, \n"+
			"coalesce(cliente.email,cliente2.email,servidor.SER_mail,alumno.email) as correoDestino \n"+
			"from webqprotesoreria.tipo_comp_pago tipo, webqprotesoreria.comprob_pago comprobante \n"+
			"left join webqprotesoreria.cliente_con_ruc cliente on (cliente.RUC=comprobante.doc_iden) \n"+
			"left join webqprotesoreria.cliente_sin_ruc cliente2 on(cliente2.num_doc=comprobante.doc_iden) \n"+
			"left join webqprotesoreria.alumnosum alumno on(TRIM(alumno.numero_documento)=comprobante.doc_iden) \n"+
			"left join datapersuel.servidor servidor on(TRIM(servidor.ser_cod)=comprobante.doc_iden) \n"+
			"where tipo.ID_TIPO_CPAGO=comprobante.TIPO AND comprobante.tipo=#{correo.tipo} AND comprobante.COD_ESTAB=#{correo.establecimiento} AND comprobante.SERIE=#{correo.serie}")
	@Results(value = {
			@Result(property = "documento", column = "documento"),
			@Result(property = "correoDestino", column = "correoDestino")})
	CorreoModel obtenerCorreoComprobante(@Param("correo") CorreoModel correo);
	
	@Update("UPDATE TXXXX_BANDFACT SET IND_SITU = #{bandfact.ind_situ} WHERE NUM_RUC=#{bandfact.num_ruc} AND TIP_DOCU=#{bandfact.tip_doc} AND NUM_DOCU=#{bandfact.num_doc}")
	void modificarEstadoSunat(@Param("bandfact") BandFactModel bandfact);	
	
	@Select("SELECT NUM_RUC,TIP_DOCU,NUM_DOCU,IND_SITU FROM TXXXX_BANDFACT WHERE IND_SITU!='03' AND IND_SITU!='04'")
	@Results(value = {
			@Result(property = "num_ruc", column = "NUM_RUC"),
			@Result(property = "tip_doc", column = "TIP_DOCU"),
			@Result(property = "num_doc", column = "NUM_DOCU"),
			@Result(property = "ind_situ", column = "IND_SITU")
			})
	List<BandFactModel> getComprobantesSinEnviar();

	@Select("SELECT comprobante.DESCR, comprobante.id_tipo_cpago,count(*) as total FROM \n"+
			"(SELECT comp.SERIE,comp.COD_ESTAB,tipo.inicial,tipo.SUNAT_EQ,tipo.ID_TIPO_CPAGO,tipo.\"DESC\" AS DESCR FROM WEBQPROTESORERIA.COMPROB_PAGO comp,WEBQPROTESORERIA.TIPO_COMP_PAGO tipo WHERE comp.TIPO=tipo.id_tipo_cpago) comprobante \n"+
			"INNER JOIN WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO establecimiento ON(comprobante.COD_ESTAB= establecimiento.CODIGO_ESTAB) \n"+
			"LEFT JOIN WEBQPROTESORERIA.TXXXX_BANDFACT band_fact ON (band_fact.NUM_DOCU = comprobante.inicial || comprobante.COD_ESTAB || '-' || comprobante.SERIE AND band_fact.TIP_DOCU=comprobante.SUNAT_EQ) \n"+
			"WHERE decode(band_fact.IND_SITU,'','01',band_fact.IND_SITU) !='03' AND decode(band_fact.IND_SITU,'','01',band_fact.IND_SITU) !='04'AND decode(band_fact.IND_SITU,'','01',band_fact.IND_SITU) !='05' AND establecimiento.UD_ID=#{ud_id} \n"+
			"group by comprobante.id_tipo_cpago,comprobante.DESCR ")
	@Results(value = {
			@Result(property = "total", column = "total"),
			@Result(property = "id_Cpago", column = "id_tipo_cpago"),
			@Result(property = "descripcion", column = "DESCR"),		
			})
	List<NotificationModel> notificacionComprobantes(Integer ud_id);
	
	@Select(value = Constantes.SELECT + "comprobante.ANIO,\n" + "comprobante.MES,\n" + "comprobante.TIPO,\n"
			+ "comprobante.COD_ESTAB,\n" + "comprobante.SERIE,\n" + "comprobante.FECHA_EMISION,\n"
			+ "comprobante.DOC_IDEN,\n" + "comprobante.NOMBRE_CLIENTE,\n" + "comprobante.DIRECCION,\n"
			+ "comprobante.GUIA,\n" + "comprobante.TOTAL,\n" + "comprobante.FORMA_PAGO,\n" + "comprobante.MONEDA,\n"
			+ "comprobante.IMPORTE_OPERACION,\n" + "comprobante.BANCO,\n" + "comprobante.N_OPERACION,\n"
			+ "comprobante.FECHA_OPERACION,\n" + "comprobante.SUB_TOTAL,\n" + "comprobante.IGV,\n"
			+ "comprobante.P_CAMBIO,\n" + "comprobante.UNIDAD,\n" + "comprobante.FACULTAD,\n" + "comprobante.USUARIO,\n"
			+ "NVL(comprobante.ESTADO,'00') AS ESTADO ,\n" + "comprobante.EST_USO,\n" + "comprobante.TIPO_DOC,\n" + "NVL(comprobante.ID,'00') AS ID,\n"
			+ "comprobante.NOMBRE,\n" + "NVL(comprobante.ENVIAR,1) AS ENVIAR,\n" 
			+ "comprobante.OBSERVACION, comprobante.NOTA_ASOC \n"
			+ Constantes.FROM + "(    SELECT * FROM UNI_DEP WHERE \n"
            + " UD_ID IN (        select dep.ud_id from qprodataquipu.uni_dep dep START  \n"
            + "       WITH UD_ID =          (                SELECT \n"
            + "               QPRODATAQUIPU.TB_HIST_USU_PERF.UD_ID                FROM \n"
            + "               QPRODATAQUIPU.TB_HIST_USU_PERF                WHERE \n"
            + "              QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL = #{usuario}        ) \n"
            + "       CONNECT BY PRIOR UD_ID=UD_PAD and ud_fec_cad is null    )) establecimiento,\n"
			+ "(\n" + "	SELECT\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.ANIO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.MES,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.TIPO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.SERIE,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.FECHA_EMISION,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.DOC_IDEN,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.NOMBRE_CLIENTE,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.DIRECCION,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.GUIA,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.TOTAL,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.FORMA_PAGO,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.MONEDA,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.IMPORTE_OPERACION,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.BANCO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.N_OPERACION,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.FECHA_OPERACION,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.SUB_TOTAL,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.IGV,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.P_CAMBIO,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.UNIDAD,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.FACULTAD,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.USUARIO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.ESTADO,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.EST_USO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.TIPO_DOC,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.UBIGEO,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.MONTO_LETRAS,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.DETRACCION,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.GRAVADA,\n" + "	WEBQPROTESORERIA.COMPROB_PAGO.EXONERADA,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.INAFECTA,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.QR,\n"
			+ "	WEBQPROTESORERIA.COMPROB_PAGO.OBSERVACION,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID AS udId,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CODIGO_ESTAB,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_FACT,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.TELEFONO,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CORREO,\n"
			+ "	WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.ULT_BOLE,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.NUM_RUC,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.TIP_DOCU,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.NUM_DOCU,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.FEC_CARG,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.FEC_GENE,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.FEC_ENVI,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.DES_OBSE,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.NOM_ARCH,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.IND_SITU,\n"
			+ "	WEBQPROTESORERIA.TXXXX_BANDFACT.TIP_ARCH,\n" + "	WEBQPROTESORERIA.TXXXX_BANDFACT.FIRM_DIGITAL,\n"
			+ "	WEBQPROTESORERIA.SITUACION_FACTURADOR.ID,\n" + "	WEBQPROTESORERIA.SITUACION_FACTURADOR.NOMBRE,\n"
			+ " DECODE (WEBQPROTESORERIA.NOTA_CREDITO.TIPO_ASOC, 1, 'B',2, 'F','') || WEBQPROTESORERIA.NOTA_CREDITO.COD_ESTAB || '-'|| WEBQPROTESORERIA.NOTA_CREDITO.SERIE  AS NOTA_ASOC, \n"
			+ "	WEBQPROTESORERIA.SITUACION_FACTURADOR.ENVIAR\n" + "	FROM\n" + "		WEBQPROTESORERIA.TXXXX_BANDFACT\n"
			+ "	INNER JOIN WEBQPROTESORERIA.SITUACION_FACTURADOR ON WEBQPROTESORERIA.TXXXX_BANDFACT.IND_SITU = WEBQPROTESORERIA.SITUACION_FACTURADOR.ID\n"
			+ "	RIGHT JOIN WEBQPROTESORERIA.COMPROB_PAGO ON WEBQPROTESORERIA.TXXXX_BANDFACT.NUM_DOCU = DECODE (#{rango.tipo}, 1, 'B', 'F') || WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB || '-' || WEBQPROTESORERIA.COMPROB_PAGO.SERIE\n"
			+ " LEFT JOIN WEBQPROTESORERIA.NOTA_CREDITO ON WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB=WEBQPROTESORERIA.NOTA_CREDITO.COD_ESTAB_ASOC AND WEBQPROTESORERIA.COMPROB_PAGO.SERIE=WEBQPROTESORERIA.NOTA_CREDITO.SERIE_ASOC AND WEBQPROTESORERIA.COMPROB_PAGO.TIPO=WEBQPROTESORERIA.NOTA_CREDITO.TIPO_ASOC \n"
			+ "	INNER JOIN WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO ON WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB = WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CODIGO_ESTAB\n"
			+ "	WHERE\n" + "		WEBQPROTESORERIA.COMPROB_PAGO.TIPO = #{rango.tipo}\n" + ") comprobante\n"
			+ "WHERE establecimiento.UD_ID=comprobante.udId\n"
			+ "AND FECHA_EMISION BETWEEN TO_DATE(#{rango.fechaInicial}, 'dd/mm/yyyy hh24:mi:ss')\n"
			+ "AND TO_DATE(#{rango.fechaFinal},'dd/mm/yyyy hh24:mi:ss')\n" + "ORDER BY\n" + "COD_ESTAB ASC,\n"
			+ "SERIE ASC")
	@Results(value = { @Result(property = "anio", column = "ANIO"), @Result(property = "mes", column = "MES"),
			@Result(property = "tipo", column = "TIPO"), @Result(property = "codEst", column = "COD_ESTAB"),
			@Result(property = "serie", column = "SERIE"), @Result(property = "fechaEmision", column = "FECHA_EMISION"),
			@Result(property = "docIden", column = "DOC_IDEN"), @Result(property = "nombre", column = "NOMBRE_CLIENTE"),
			@Result(property = "direccion", column = "DIRECCION"), @Result(property = "guia", column = "GUIA"),
			@Result(property = "total", column = "TOTAL"), @Result(property = "formaPago", column = "FORMA_PAGO"),
			@Result(property = "moneda", column = "MONEDA"),
			@Result(property = "importeOperacion", column = "IMPORTE_OPERACION"),
			@Result(property = "banco", column = "BANCO"), @Result(property = "nOperacion", column = "N_OPERACION"),
			@Result(property = "fechaOperacion", column = "FECHA_OPERACION"),
			@Result(property = "subTotal", column = "SUB_TOTAL"), @Result(property = "igv", column = "IGV"),
			@Result(property = "pCambio", column = "P_CAMBIO"), @Result(property = "unidad", column = "UNIDAD"),
			@Result(property = "facultad", column = "FACULTAD"), @Result(property = "usuario", column = "USUARIO"),
			@Result(property = "estado", column = "ESTADO"), @Result(property = "estUso", column = "EST_USO"),
			@Result(property = "tipoDoc", column = "TIPO_DOC"), @Result(property = "id", column = "ID"),
			@Result(property = "nombre2", column = "NOMBRE"), @Result(property = "enviar", column = "ENVIAR"),
			@Result(property = "observacion", column = "OBSERVACION"),@Result(property = "notaAsoc", column = "NOTA_ASOC") })
	List<ComprobantePagoModel> comprobantePagoAdminCaja(@Param("rango") RangoFecha rango, @Param("usuario") String usuario);
	
	@Select(value =  Constantes.SELECT +" comprobante.TIPO_NOTA,comprobante.MOTIVO, \n" +
		    "                        comprobante.ANIO,comprobante.MES, \n" +
			"                        comprobante.TIPO,comprobante.COD_ESTAB, \n" +
			"                        comprobante.SERIE,comprobante.FECHA_EMISION, \n" +
			"                        comprobante.DOC_IDEN,comprobante.NOMBRE_CLIENTE, \n" +
			"                        comprobante.DIRECCION,comprobante.GUIA, \n" +
			"                        comprobante.TOTAL, comprobante.FORMA_PAGO, \n" +
			"                        comprobante.MONEDA,comprobante.IMPORTE_OPERACION, \n" +
			"                        comprobante.BANCO,comprobante.N_OPERACION, \n" +
			"                        comprobante.FECHA_OPERACION,comprobante.SUB_TOTAL, \n" +
			"                        comprobante.IGV,comprobante.P_CAMBIO, \n" +
			"                        comprobante.UNIDAD,comprobante.FACULTAD, \n" +
			"                        comprobante.USUARIO,comprobante.ESTADO_DESCR, \n" +
			"                        NVL(comprobante.ESTADO,'00') AS ESTADO,comprobante.TIPO_DOC, \n" +
			"                        NVL(comprobante.ENVIAR,1) AS ENVIAR \n" +
			Constantes.FROM + "(    SELECT * FROM UNI_DEP WHERE \n" +
            " UD_ID IN (        select dep.ud_id from qprodataquipu.uni_dep dep START  \n" +
            "       WITH UD_ID =          (                SELECT \n" +
            "               QPRODATAQUIPU.TB_HIST_USU_PERF.UD_ID                FROM \n" +
            "               QPRODATAQUIPU.TB_HIST_USU_PERF                WHERE \n" +
            "              QPRODATAQUIPU.TB_HIST_USU_PERF.T_MAIL = #{usuario}        ) \n" +
            "       CONNECT BY PRIOR UD_ID=UD_PAD and ud_fec_cad is null    )) establecimiento,\n" +
			"            (     SELECT     \n" +
			"                                        upper(WEBQPROTESORERIA.TIPO_NOTA_CREDITO.DESCRIPCION) AS TIPO_NOTA, \n" +
			"                                WEBQPROTESORERIA.NOTA_CREDITO.MOTIVO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.ANIO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.MES, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.TIPO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.SERIE, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.FECHA_EMISION AS FECHA_EMISION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.DOC_IDEN, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.NOMBRE_CLIENTE, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.DIRECCION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.GUIA, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.TOTAL, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.FORMA_PAGO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.MONEDA, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.IMPORTE_OPERACION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.BANCO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.N_OPERACION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.FECHA_OPERACION, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.SUB_TOTAL, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.IGV, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.P_CAMBIO, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.UNIDAD, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.FACULTAD, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.USUARIO, \n" +
			"                                WEBQPROTESORERIA.SITUACION_FACTURADOR.NOMBRE AS ESTADO_DESCR, \n" +
			"                                WEBQPROTESORERIA.SITUACION_FACTURADOR.ID AS ESTADO, \n" +
			"                                WEBQPROTESORERIA.SITUACION_FACTURADOR.ENVIAR AS ENVIAR, \n" +
			"                                WEBQPROTESORERIA.COMPROB_PAGO.TIPO_DOC, \n" +
			"                                WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.UD_ID AS udId    \n" +
			"                FROM  WEBQPROTESORERIA.TIPO_NOTA_CREDITO,    WEBQPROTESORERIA.NOTA_CREDITO ,   WEBQPROTESORERIA.TXXXX_BANDFACT\n" +
			"                INNER JOIN WEBQPROTESORERIA.SITUACION_FACTURADOR ON WEBQPROTESORERIA.TXXXX_BANDFACT.IND_SITU = WEBQPROTESORERIA.SITUACION_FACTURADOR.ID\n" +
			"                RIGHT JOIN WEBQPROTESORERIA.COMPROB_PAGO ON WEBQPROTESORERIA.TXXXX_BANDFACT.NUM_DOCU = DECODE (#{rango.tipo}, 4, 'F',5,'B','') || WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB || '-' || WEBQPROTESORERIA.COMPROB_PAGO.SERIE\n" +
			"                INNER JOIN WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO ON WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB = WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO.CODIGO_ESTAB\n" +
			"                WHERE        \n" +
			"                 WEBQPROTESORERIA.COMPROB_PAGO.TIPO = #{rango.tipo} AND\n" +
			"                 WEBQPROTESORERIA.NOTA_CREDITO.TIPO=WEBQPROTESORERIA.COMPROB_PAGO.TIPO  AND WEBQPROTESORERIA.NOTA_CREDITO.COD_ESTAB= WEBQPROTESORERIA.COMPROB_PAGO.COD_ESTAB AND WEBQPROTESORERIA.NOTA_CREDITO.SERIE=WEBQPROTESORERIA.COMPROB_PAGO.SERIE\n" +
			"                 and  WEBQPROTESORERIA.TIPO_NOTA_CREDITO.ID_TIPO=WEBQPROTESORERIA.NOTA_CREDITO.TIPO_NOTA ) comprobante\n" +
			"            WHERE establecimiento.UD_ID=comprobante.udId\n" +
			"            AND FECHA_EMISION BETWEEN TO_DATE(#{rango.fechaInicial}, 'dd/mm/yyyy hh24:mi:ss')\n" +
			"            AND TO_DATE(#{rango.fechaFinal},'dd/mm/yyyy hh24:mi:ss') ORDER BY COD_ESTAB ASC,\n" +
			"            TO_NUMBER(SERIE) ASC")
	@Results(value = { @Result(property = "tipoNota", column = "TIPO_NOTA"),
			@Result(property = "motivo", column = "MOTIVO"), @Result(property = "anio", column = "ANIO"),
			@Result(property = "mes", column = "MES"), @Result(property = "tipo", column = "TIPO"),
			@Result(property = "codEst", column = "COD_ESTAB"), @Result(property = "serie", column = "SERIE"),
			@Result(property = "fechaEmision", column = "FECHA_EMISION"),
			@Result(property = "docIden", column = "DOC_IDEN"), @Result(property = "nombre", column = "NOMBRE_CLIENTE"),
			@Result(property = "direccion", column = "DIRECCION"), @Result(property = "guia", column = "GUIA"),
			@Result(property = "total", column = "TOTAL"), @Result(property = "formaPago", column = "FORMA_PAGO"),
			@Result(property = "moneda", column = "MONEDA"),@Result(property = "enviar", column = "ENVIAR"),
			@Result(property = "importeOperacion", column = "IMPORTE_OPERACION"),
			@Result(property = "banco", column = "BANCO"), @Result(property = "nOperacion", column = "N_OPERACION"),
			@Result(property = "fechaOperacion", column = "FECHA_OPERACION"),
			@Result(property = "subTotal", column = "SUB_TOTAL"), @Result(property = "igv", column = "IGV"),
			@Result(property = "pCambio", column = "P_CAMBIO"), @Result(property = "unidad", column = "UNIDAD"),
			@Result(property = "facultad", column = "FACULTAD"), @Result(property = "usuario", column = "USUARIO"),
			@Result(property = "estadoFacturadorDescr", column = "ESTADO_DESCR"),
			@Result(property = "estadoFacturador", column = "ESTADO"),
			@Result(property = "tipoDoc", column = "TIPO_DOC") })
	List<ComprobantePagoModel> getNotasDeCreditoAdminCaja(@Param("rango") RangoFecha rango, @Param("usuario") String usuario);
}
