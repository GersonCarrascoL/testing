package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.edu.unmsm.quipucamayoc.model.ComprobantePagoModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleNotaCredito;
import pe.edu.unmsm.quipucamayoc.model.NotaCreditoModel;
import pe.edu.unmsm.quipucamayoc.model.TipoNotaCredito;

public interface NotaCreditoPersistence {

	@Select(value = " select id_tipo,upper(descripcion) as descripcion,boleta,factura,est,detalle from tipo_nota_credito WHERE est=1 and boleta=1")
	@Results(value = { @Result(javaType = TipoNotaCredito.class), @Result(column = "id_Tipo", property = "idTipo"),
			@Result(column = "descripcion", property = "descripcion"), @Result(column = "boleta", property = "boleta"),
			@Result(column = "factura", property = "factura"), @Result(column = "est", property = "est"),
			@Result(column = "detalle", property = "detalle") })
	public List<TipoNotaCredito> getTipoNotaCreditoBoleta();

	@Select(value = " select id_tipo,upper(descripcion) as descripcion,boleta,factura,est,detalle from tipo_nota_credito WHERE est=1 and factura=1")
	@Results(value = { @Result(javaType = TipoNotaCredito.class), @Result(column = "id_Tipo", property = "idTipo"),
			@Result(column = "descripcion", property = "descripcion"), @Result(column = "boleta", property = "boleta"),
			@Result(column = "factura", property = "factura"), @Result(column = "est", property = "est"),
			@Result(column = "detalle", property = "detalle") })
	public List<TipoNotaCredito> getTipoNotaCreditoFactura();

	@Select(value = "SELECT S.* FROM (SELECT comprobante_facturador.NUM_DOCU, \n " + "comprobante.ANIO, \n " + "comprobante.MES, \n "
			+ "comprobante.TIPO, \n " + "comprobante.COD_ESTAB, \n " + "comprobante.SERIE, \n "
			+ "TO_CHAR(comprobante.FECHA_EMISION,'dd/mm/yyyy') as FECHA_EMISION, \n " + "comprobante.DOC_IDEN, \n " + "comprobante.NOMBRE_CLIENTE, \n "
			+ "comprobante.DIRECCION, \n " + "comprobante.GUIA, \n " + "comprobante.TOTAL, \n "
			+ "comprobante.FORMA_PAGO, \n " + "comprobante.MONEDA, \n " + "comprobante.IMPORTE_OPERACION, \n "
			+ "comprobante.BANCO, \n " + "comprobante.N_OPERACION, \n " + "comprobante.FECHA_OPERACION, \n "
			+ "comprobante.SUB_TOTAL, \n " + "comprobante.IGV, \n " + "comprobante.P_CAMBIO, \n "
			+ "comprobante.UNIDAD, \n " + "comprobante.FACULTAD, \n " + "comprobante.USUARIO, \n "
			+ "situacion_facturador.NOMBRE ESTADO, \n " + "comprobante.EST_USO, \n " + "comprobante.TIPO_DOC \n "
			+ "FROM             \n " + "WEBQPROTESORERIA.TXXXX_BANDFACT comprobante_facturador, \n "
			+ "WEBQPROTESORERIA.COMPROB_PAGO comprobante, \n "
			+ "WEBQPROTESORERIA.SITUACION_FACTURADOR situacion_facturador, \n "
			+ "WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO establecimiento \n "
			+ "INNER JOIN qprodataquipu.uni_dep dep on (dep.ud_id=establecimiento.UD_ID) \n " + "WHERE \n "
			+ "establecimiento.codigo_estab=comprobante.COD_ESTAB and \n "
			+ "dep.ud_cod like #{cod_dependencia}||'%' and \n " + "comprobante.TIPO=#{tipo_comprobante} AND \n "
			+ "situacion_facturador.ID=comprobante_facturador.IND_SITU AND \n "
			+ "(comprobante.SERIE+0) like #{numeroComprobante} ||'%' AND \n"
			+ "(DECODE(comprobante.TIPO,1,'B',2,'F','B')||comprobante.COD_ESTAB||'-'|| comprobante.SERIE)=comprobante_facturador.NUM_DOCU AND    \n "
			+ "(comprobante_facturador.IND_SITU='03' OR \n " + "comprobante_facturador.IND_SITU='04') AND \n "
			+ "(comprobante.TIPO, comprobante.COD_ESTAB,comprobante.SERIE ) not in "
			+ " ( select TIPO_ASOC ,COD_ESTAB_ASOC ,SERIE_ASOC from WEBQPROTESORERIA.NOTA_CREDITO nota_credito ) \n"
			+" ORDER BY  comprobante.SERIE ASC) S WHERE rownum<=10")
	@Results(value = { @Result(javaType = ComprobantePagoModel.class),
			@Result(property = "numeroDocumento", column = "NUM_DOCU"), @Result(property = "anio", column = "ANIO"),
			@Result(property = "mes", column = "MES"), @Result(property = "tipo", column = "TIPO"),
			@Result(property = "codEst", column = "COD_ESTAB"), @Result(property = "serie", column = "SERIE"),
			@Result(property = "fechaEmision", column = "FECHA_EMISION"),
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
			@Result(property = "tipoDoc", column = "TIPO_DOC") })
	public List<ComprobantePagoModel> getComprobantesParaNotas(@Param("cod_dependencia") String codDependencia,
			@Param("tipo_comprobante") Integer tipoComprobante,@Param("numeroComprobante") Integer numeroComprobante);

	@Select(value = "select detalle_comprobante.id_det_comp, detalle_comprobante.ID_ITEM, \n"
			+ "detalle_comprobante.CANTIDAD, \n" + "detalle_comprobante.DESCRIPCION, \n"
			+ "detalle_comprobante.PRECIO_U, \n" + "detalle_comprobante.PRECIO_T, \n" + "detalle_comprobante.MONEDA, \n"
			+ "detalle_comprobante.IGV_T, detalle_comprobante.IGV_U, \n" + "detalle_comprobante.UNI_MEDIDA, \n" + "operacion_igv.DESC_TIPO,\n"
			+ "operacion_igv.COD_TIPO \n" + "from \n" + "WEBQPROTESORERIA.TXXXX_BANDFACT comprobante_facturador, \n"
			+ "WEBQPROTESORERIA.tipo_comp_pago tipo_comprobante, \n"
			+ "WEBQPROTESORERIA.tipo_operacion_igv operacion_igv, \n" + "WEBQPROTESORERIA.COMPROB_PAGO comprobante \n"
			+ "INNER JOIN WEBQPROTESORERIA.det_compb_pago detalle_comprobante ON (detalle_comprobante.tipo=comprobante.tipo and detalle_comprobante.cod_estab=comprobante.cod_estab \n"
			+ "and detalle_comprobante.serie=comprobante.serie) \n"
			+ "where comprobante_facturador.num_docu= #{numero_documento} and \n"
			+ "(DECODE(tipo_comprobante.id_tipo_cpago,1,'B',2,'F','B')||comprobante.COD_ESTAB||'-'|| comprobante.SERIE)=comprobante_facturador.NUM_DOCU AND \n"
			+ "tipo_comprobante.sunat_eq=comprobante_facturador.tip_docu and \n"
			+ "comprobante.tipo=tipo_comprobante.id_tipo_cpago and \n"
			+ "operacion_igv.COD_TIPO=detalle_comprobante.COD_TIPO_IGV  and comprobante.tipo=#{tipo_comprobante}")
	@Results(value = { @Result(javaType = DetalleFacturaModel.class),
			@Result(column = "id_det_comp", property = "idDetComp"), @Result(column = "id_item", property = "codigo"),
			@Result(column = "cantidad", property = "cantidad"),
			@Result(column = "descripcion", property = "descripcion"),@Result(column = "igv_u", property = "igvU"),
			@Result(column = "precio_u", property = "precioU"), @Result(column = "precio_t", property = "precioT"),
			@Result(column = "moneda", property = "moneda"), @Result(column = "igv_t", property = "igvT"),
			@Result(column = "uni_medida", property = "nUnidadMedida"),
			@Result(column = "desc_tipo", property = "descTipoIgv"),
			@Result(column = "cod_tipo", property = "codTipoIgv") })
	public List<DetalleFacturaModel> getDetalleComprobante(@Param("numero_documento") String numeroDocumento,
			@Param("tipo_comprobante") Integer tipoComprobante);

	@Select(value = "select comprobante.doc_iden,\n" + "comprobante.nombre_cliente,\n" + "comprobante.moneda,\n"
			+ "comprobante.total,\n" + "comprobante.igv,\n" + "comprobante.tipo_doc,\n" + "comprobante.gravada,\n"
			+ "comprobante.exonerada,\n" + "comprobante.inafecta    \n" + "from \n"
			+ "WEBQPROTESORERIA.TXXXX_BANDFACT comprobante_facturador,\n"
			+ "WEBQPROTESORERIA.tipo_comp_pago tipo_comprobante,\n" + "WEBQPROTESORERIA.COMPROB_PAGO comprobante   \n"
			+ "where comprobante_facturador.num_docu=#{numero_documento} and \n"
			+ "(DECODE(tipo_comprobante.id_tipo_cpago,1,'B',2,'F','B')||comprobante.COD_ESTAB||'-'|| comprobante.SERIE)=comprobante_facturador.NUM_DOCU AND \n"
			+ "tipo_comprobante.sunat_eq=comprobante_facturador.tip_docu and \n"
			+ "comprobante.tipo=tipo_comprobante.id_tipo_cpago and \n" + "comprobante.tipo=#{tipo_comprobante}")
	@Results(value = { @Result(javaType = ComprobantePagoModel.class),
			@Result(column = "doc_iden", property = "numeroDocumento"),
			@Result(column = "nombre_cliente", property = "nombre"), @Result(column = "moneda", property = "moneda"),
			@Result(column = "total", property = "total"), @Result(column = "igv", property = "igv"),
			@Result(column = "tipo_doc", property = "tipoDoc"), @Result(column = "gravada", property = "gravada"),
			@Result(column = "exonerada", property = "exonerada"),
			@Result(column = "inafecta", property = "inafecta") })
	public ComprobantePagoModel getInformacionComprobante(@Param("numero_documento") String codDependencia,
			@Param("tipo_comprobante") Integer tipoComprobante);

	@Insert(value = "{CALL INSERTAR_NOTA_CREDITO (" +"#{notaCredito.usuario, mode=IN, jdbcType=VARCHAR},"
			+ "#{notaCredito.tipo, mode=IN, jdbcType=VARCHAR},"
			+ "#{notaCredito.motivo, mode=IN, jdbcType=VARCHAR},"
			+ "#{notaCredito.numeroDocumentoAsociado, mode=IN, jdbcType=VARCHAR},"
			+ "#{notaCredito.codEstab, mode=OUT, jdbcType=VARCHAR},"
			+ "#{notaCredito.serie, mode=OUT, jdbcType=VARCHAR},"
			+ "#{notaCredito.tipoComprobante, mode=OUT, jdbcType=INTEGER}" + ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarNotaCredito(@Param("notaCredito") NotaCreditoModel notaCredito);

	@Insert(value = "{CALL DETALLE_NOTA_CREDITO (" + "#{notaCredito.codEstab, mode=IN, jdbcType=VARCHAR},"
			+ "#{notaCredito.serie, mode=IN, jdbcType=VARCHAR},"
			+ "#{notaCredito.tipoComprobante, mode=IN, jdbcType=INTEGER},"
			+ "#{notaCredito.tipo, mode=IN, jdbcType=INTEGER},"
			+ "#{detalle.idDetalleAsociado, mode=IN, jdbcType=INTEGER},"
			+ "#{detalle.cantidad, mode=IN, jdbcType=INTEGER},"
			+ "#{detalle.precioUnitario, mode=IN, jdbcType=INTEGER},"
			+ "#{detalle.descripcion, mode=IN, jdbcType=VARCHAR}" + ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarNotaCreditoDetalle(@Param("notaCredito") NotaCreditoModel notaCredito,
			@Param("detalle") DetalleNotaCredito detalle);
}
