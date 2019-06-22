package pe.edu.unmsm.quipucamayoc.persistence;

import pe.edu.unmsm.quipucamayoc.model.ListaItemPrecioModel;

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

public interface ListaItemPrecioPersistence {
	
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
	String PROP_NOMBRECATALOGO = "nombreCatalogo";
	String PROP_IDBIENCATA = "idBienCata";
	String PROP_DESCCATEGORIABIEN = "descCategoriaBien";
	String PROP_CODTIPOOPERACIONIGV = "codTipoperacionIgv";
	String PROP_DESCTIPOIGV = "descTipoIgv";
	String PROP_POSEEDESTRACCION = "poseeDetraccion";
	String PROP_PORCENTDETRACCION = "porcentDetraccion";	
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
	String COL_NOMBRECATALOGO = "NOMBRE_CATALOGO";
	String COL_IDBIENCATA = "id_bien_cata";
	String COL_DESCCATEGORIABIEN = "desc_categoria_bien";
	String COL_CODTIPOOPERACIONIGV = "COD_TIPO_IGV";
	String COL_DESCTIPOIGV = "DESC_TIPO_IGV";
	String COL_POSEEDESTRACCION = "poseeDetraccion";
	String COL_PORCENTDETRACCION = "porcentDetraccion";
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
	
	String GET_BIENES_QUERY = "SELECT LP.COD_ITEM AS CODIGO_ITEM, LP.UD_ID AS UD_ID, " +
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
							" CCP.NOMBRE AS NOMBRE_CATALOGO, " +
							" LP.COD_TIPOPERACION_IGV AS COD_TIPO_IGV, " +
							" TOI.DESC_TIPO AS DESC_TIPO_IGV, " +
							" LP.ID_BIEN_CATA AS id_bien_cata, " +
							" BCAT.DESC_CATEGORIA AS desc_categoria_bien, " +	
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
							" FROM WEBQPROTESORERIA.LISTA_ITEM_PRECIO LP " +
							" INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON (DEP.UD_ID = LP.UD_ID) " +
							" INNER JOIN MONEDA MON ON (MON.ID_MONEDA = LP.ID_MONEDA) " +
							" INNER JOIN WEBQPROTESORERIA.UNI_MED_ART UNIMED ON (UNIMED.UNIMEDCOD = LP.N_UNIDAD_MEDIDA) " +
							" INNER JOIN WEBQPROTESORERIA.CATALOGO_CODIGO_PRODUCTO CCP ON LP.ID_CATA = CCP.CODIGO "+
							" INNER JOIN WEBQPROTESORERIA.TIPO_OPERACION_IGV TOI ON LP.COD_TIPOPERACION_IGV = TOI.COD_TIPO "+
							" LEFT JOIN WEBQPROTESORERIA.BIEN_CATEGORIA BCAT ON LP.ID_BIEN_CATA = BCAT.ID_BIEN_CATA "+
							" LEFT JOIN WEBQPROTESORERIA.CONCEPTO_PAGO_UNIDAD CPU ON LP.ID_UNIDAD_CONCEPTO = CPU.ID_CU " +
							" LEFT JOIN WEBQPROTESORERIA.CONCEPTO_PAGO CPAGO ON CPU.ID_CP = CPAGO.ID_CPAGO " +
							" LEFT JOIN WEBQPROTESORERIA.UNIDAD_CODIGO COD ON CPU.UD_ID = COD.UD_ID " +
							" LEFT JOIN WEBQPROTESORERIA.TIPO_RESOLUCION TRS ON LP.TIPO_RESOLUCION = TRS.COD_TIPO_RESOL ";
	
	String ORDER_BY_FECHA_REG = " ORDER BY LP.FECHA_REG DESC ";
	String ADD_FILTER_UD_FEC_CAD = " AND DEP.UD_FEC_CAD is null ";
	
	
	@Select(value="select * from WEBQPROTESORERIA.LISTA_ITEM_PRECIO")
	@Results(value={
			@Result(javaType=ListaItemPrecioModel.class),
			@Result(property=PROP_UDID,column=COL_UDID),
			@Result(property="anio",column="ANIO"),
			@Result(property=PROP_CODITEM,column="COD_ITEM"),
			@Result(property=PROP_NUNIDADMEDIDA,column="N_UNIDAD_MEDIDA"),
			@Result(property=PROP_DESCRIPCION,column="DESCRIPCION"),
			@Result(property=PROP_PRECIO,column="PRECIO"),
			@Result(property=PROP_ESTADO,column="ESTADO"),
			@Result(property=PROP_IDMONEDA,column=COL_IDMONEDA)})
	public List<ListaItemPrecioModel> items();
	
	
	@Select(value = "SELECT\n" +
					"LP.COD_ITEM AS CODIGO_ITEM,\n" +
					"LP.UD_ID AS UD_ID,\n" +
					"DEP.UD_COD AS UD_COD,\n" +
					"DEP.UD_DSC AS UD_DESCRIP,\n" +
					"LP.DESCRIPCION AS DESCRIP_ITEM,\n" +
					"BCAT.DESC_CATEGORIA AS DESCRIP_CATEGORIA,\n" +
					"LP.DESCRIPCION || DECODE(BCAT.DESC_CATEGORIA, NULL, '' ,   ' - ' || BCAT.DESC_CATEGORIA) || DECODE(UCOD1.NOMBRE_CORTO, NULL, '' ,   ' (' || UCOD1.NOMBRE_CORTO || ')') AS DESCRIP_ITEM_Y_CATEGORIA,\n" +
					"LP.PRECIO AS PRECIO_UNITARIO,\n" +
					"LP.ID_MONEDA AS ID_MONEDA,\n" +
					"MON.MONEDA AS DESCRIP_MONEDA,\n" +
					"MON.SIMB AS SIMB_MONEDA,\n" +
					"LP.EST AS ESTADO_ITEM,\n" +
					"LP.PRECIO*(ABS(2.5-(0.1*LP.COD_TIPOPERACION_IGV))-0.5)*WEBQPROTESORERIA.TIPO_IMPUESTO.IMPPOR AS IGV,\n" +
					"LP.PRECIO+(LP.PRECIO*(ABS(2.5-(0.1*LP.COD_TIPOPERACION_IGV))-0.5)*WEBQPROTESORERIA.TIPO_IMPUESTO.IMPPOR) AS CON_IGV,\n" +
					"WEBQPROTESORERIA.UNI_MED_ART.UNIMEDCOD,\n" +
					"WEBQPROTESORERIA.UNI_MED_ART.UNIMEDABR,\n" +
					"WEBQPROTESORERIA.UNI_MED_ART.UNIMEDDES,\n" +
					"0 As TIPO,\n" +
					"ABS(2.5-(0.1*LP.COD_TIPOPERACION_IGV))-0.5 AS ESTADO_IGV,\n" +
					"LP.ID_CATA,\n" +
					"LP.POSEE_DETRACCION,\n" +
					"LP.COD_CATA_DETRAC,\n" +
					"LP.PORCENT_DETRAC,\n" +
					"LP.COD_TIPOPERACION_IGV,\n" +
					"'BN' AS TIPO_ITEM \n" +
					"FROM\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_PRECIO LP\n" +
					"INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON (DEP.UD_ID = LP.UD_ID)\n" +
					"INNER JOIN WEBQPROTESORERIA.MONEDA MON ON (MON.ID_MONEDA = LP.ID_MONEDA)\n" +
					"LEFT JOIN WEBQPROTESORERIA.BIEN_CATEGORIA BCAT ON (LP.ID_BIEN_CATA = BCAT.ID_BIEN_CATA)" +
					"LEFT JOIN WEBQPROTESORERIA.UNIDAD_CODIGO UCOD1 ON LP.UD_ID = UCOD1.UD_ID,\n" +
					"WEBQPROTESORERIA.TIPO_IMPUESTO,\n" +
					"WEBQPROTESORERIA.UNI_MED_ART\n" +
					"WHERE\n" +
					"decode(#{codigoDependencia}, 'E050303', to_char(LP.UD_ID_ADMINISTRATIVA), DEP.UD_COD) LIKE decode (#{codigoDependencia},  'E050303' , '11327' , #{codigoDependencia} || '%') AND\n" +
					"DEP.UD_FEC_CAD IS null AND\n" +
					"WEBQPROTESORERIA.TIPO_IMPUESTO.TIPIMPDES = 'IGV' AND\n" +
					"LP.N_UNIDAD_MEDIDA = WEBQPROTESORERIA.UNI_MED_ART.UNIMEDCOD\n" +
					"UNION\n" +
					"SELECT\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.COD_ITEM,\n" +
					"QPRODATAQUIPU.UNI_DEP.UD_ID,\n" +
					"QPRODATAQUIPU.UNI_DEP.UD_COD,\n" +
					"QPRODATAQUIPU.UNI_DEP.UD_DSC,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.DESCRIPCION,\n" +					
					"SCAT.DESC_CATEGORIA,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.DESCRIPCION || DECODE(SCAT.DESC_CATEGORIA, NULL, '' ,   ' - ' || SCAT.DESC_CATEGORIA) || DECODE(UCOD2.NOMBRE_CORTO, NULL, '' , ' (' || UCOD2.NOMBRE_CORTO || ')'),\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.PRECIO,\n" +
					"WEBQPROTESORERIA.MONEDA.ID_MONEDA,\n" +
					"WEBQPROTESORERIA.MONEDA.MONEDA,\n" +
					"WEBQPROTESORERIA.MONEDA.SIMB,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.EST,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.PRECIO*(ABS(2.5-(0.1*WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.COD_TIPOPERACION_IGV))-0.5)*WEBQPROTESORERIA.TIPO_IMPUESTO.IMPPOR AS IGV,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.PRECIO+(WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.PRECIO*(ABS(2.5-(0.1*WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.COD_TIPOPERACION_IGV))-0.5)*WEBQPROTESORERIA.TIPO_IMPUESTO.IMPPOR) AS CON_IGV,\n" +
					"WEBQPROTESORERIA.UNI_MED_ART.UNIMEDCOD,\n" +
					"WEBQPROTESORERIA.UNI_MED_ART.UNIMEDABR,\n" +
					"WEBQPROTESORERIA.UNI_MED_ART.UNIMEDDES,\n" +
					"1,\n" +
					"ABS(2.5-(0.1*WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.COD_TIPOPERACION_IGV))-0.5,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.ID_CATA,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.POSEE_DETRACCION,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.COD_CATA_DETRAC,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.PORCENT_DETRAC,\n" +
					"WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.COD_TIPOPERACION_IGV,\n" +
					"'SV' AS TIPO_ITEM \n" +
					"FROM\n" +
					"QPRODATAQUIPU.UNI_DEP\n" +
					"INNER JOIN WEBQPROTESORERIA.LISTA_ITEM_SERVICIO ON QPRODATAQUIPU.UNI_DEP.UD_ID = WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.UD_ID\n" +
					"INNER JOIN WEBQPROTESORERIA.MONEDA ON WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.ID_MONEDA = WEBQPROTESORERIA.MONEDA.ID_MONEDA\n" +
					"INNER JOIN WEBQPROTESORERIA.UNI_MED_ART ON WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.N_UNIDAD_MEDIDA = WEBQPROTESORERIA.UNI_MED_ART.UNIMEDCOD\n" +
					"LEFT JOIN WEBQPROTESORERIA.SERVICIO_CATEGORIA SCAT ON WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.ID_SERV_CATA = SCAT.ID_SERV_CATA\n" +
					"LEFT JOIN WEBQPROTESORERIA.UNIDAD_CODIGO UCOD2 ON WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.UD_ID = UCOD2.UD_ID,\n" +
					"WEBQPROTESORERIA.TIPO_IMPUESTO\n" +
					"WHERE\n" +
					"decode(#{codigoDependencia}, 'E050303', to_char(WEBQPROTESORERIA.LISTA_ITEM_SERVICIO.UD_ID_ADMINISTRATIVA), QPRODATAQUIPU.UNI_DEP.UD_COD) LIKE decode (#{codigoDependencia},  'E050303' , '11327' , #{codigoDependencia} || '%') AND\n" +
					"QPRODATAQUIPU.UNI_DEP.UD_FEC_CAD IS null AND\n" +
					"WEBQPROTESORERIA.TIPO_IMPUESTO.TIPIMPDES = 'IGV'")
	@Results(value = {
	    @Result(javaType = ListaItemPrecioModel.class),
	    @Result(property = PROP_CODITEM,column = COL_CODITEM),
	    @Result(property = PROP_UDID,column = COL_UDID),
	    @Result(property = PROP_UDCOD,column = COL_UDCOD),
	    @Result(property = PROP_UDDESC,column = COL_UDDESC),
	    @Result(property = PROP_DESCRIPCION,column = COL_DESCRIPCION),
	    @Result(property = PROP_PRECIO,column = COL_PRECIO),
	    @Result(property = PROP_IDMONEDA,column = COL_IDMONEDA),
	    @Result(property = PROP_MONEDASIMB,column = COL_MONEDASIMB),
	    @Result(property = PROP_ESTADO,column = COL_ESTADO),
	    @Result(property = "igv",column = "IGV"),
	    @Result(property = "conIgv",column = "CON_IGV"),
	    @Result(property = PROP_NUNIDADMEDIDA,column = "UNIMEDCOD"),
	    @Result(property = "abreviatura",column = "UNIMEDABR"),
	    @Result(property = PROP_UNIDADMEDIDADESC,column = "UNIMEDDES"),
	    @Result(property = "tipo",column = "TIPO"),
	    @Result(property = "estadoIGV",column = "ESTADO_IGV"),
	    @Result(property = "codProductoSUNAT",column = "ID_CATA"),
	    @Result(property = PROP_POSEEDESTRACCION,column = "POSEE_DETRACCION"),
	    @Result(property = "codDetraccion",column = "COD_CATA_DETRAC"),
	    @Result(property = PROP_PORCENTDETRACCION,column = "PORCENT_DETRAC"),
	    @Result(property = "codTipoIgv",column = "COD_TIPOPERACION_IGV"),
	    @Result(property = "descripcionCategoria",column = "DESCRIP_CATEGORIA"),
	    @Result(property = "descripItemYCategoria",column = "DESCRIP_ITEM_Y_CATEGORIA"),
	    @Result(property = "tipoItem",column = "TIPO_ITEM")
	})
	public List<ListaItemPrecioModel> getListaItemYConceptoXDependencia(@Param("codigoDependencia") String codigoDependencia);
	
	
	@Select(value = GET_BIENES_QUERY
					+ " where DEP.UD_COD like #{codigoDependencia}||'%' "
					+ ADD_FILTER_UD_FEC_CAD
					+ ORDER_BY_FECHA_REG
					)
	@Results(value = {
	@Result(javaType = ListaItemPrecioModel.class),
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
	@Result(property = PROP_NOMBRECATALOGO,column = COL_NOMBRECATALOGO),
	@Result(property = PROP_CODTIPOOPERACIONIGV,column = COL_CODTIPOOPERACIONIGV),
	@Result(property = PROP_DESCTIPOIGV,column = COL_DESCTIPOIGV),
	@Result(property = PROP_IDBIENCATA,column = COL_IDBIENCATA),
	@Result(property = PROP_DESCCATEGORIABIEN,column = COL_DESCCATEGORIABIEN),
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
	public List<ListaItemPrecioModel> getListaItemPrecioXDependencia(@Param("codigoDependencia") String codigoDependencia);
	
	@Select(value = GET_BIENES_QUERY
					+ " where DEP.UD_COD like #{codigoDependencia}||'%' "
					+ ADD_FILTER_UD_FEC_CAD
					+ " AND LP.EST = 1 "
					+ ORDER_BY_FECHA_REG
					)
	@Results(value = {
	@Result(javaType = ListaItemPrecioModel.class),
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
	@Result(property = PROP_NOMBRECATALOGO,column = COL_NOMBRECATALOGO),
	@Result(property = PROP_CODTIPOOPERACIONIGV,column = COL_CODTIPOOPERACIONIGV),
	@Result(property = PROP_DESCTIPOIGV,column = COL_DESCTIPOIGV),
	@Result(property = PROP_IDBIENCATA,column = COL_IDBIENCATA),
	@Result(property = PROP_DESCCATEGORIABIEN,column = COL_DESCCATEGORIABIEN),
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
	public List<ListaItemPrecioModel> getItemsPrecioXDependenciaHabilitados(@Param("codigoDependencia") String codigoDependencia);
	
	
	@Select(value = GET_BIENES_QUERY
					+ " where LP.UD_ID_ADMINISTRATIVA = #{udIdAdministrativa} "
					+ ADD_FILTER_UD_FEC_CAD
					+ ORDER_BY_FECHA_REG
					)
	@Results(value = {
	@Result(javaType = ListaItemPrecioModel.class),
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
	@Result(property = PROP_NOMBRECATALOGO,column = COL_NOMBRECATALOGO),
	@Result(property = PROP_CODTIPOOPERACIONIGV,column = COL_CODTIPOOPERACIONIGV),
	@Result(property = PROP_DESCTIPOIGV,column = COL_DESCTIPOIGV),
	@Result(property = PROP_IDBIENCATA,column = COL_IDBIENCATA),
	@Result(property = PROP_DESCCATEGORIABIEN,column = COL_DESCCATEGORIABIEN),
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
	public List<ListaItemPrecioModel> getListaPreciosXAdmiCentral(@Param("udIdAdministrativa") Integer udIdAdministrativa);
	
	@Select(value = GET_BIENES_QUERY
					+ " where LP.UD_ID_ADMINISTRATIVA = #{udIdAdministrativa} "
					+ ADD_FILTER_UD_FEC_CAD
					+ " AND LP.EST = 1 "
					+ ORDER_BY_FECHA_REG
					)
	@Results(value = {
	@Result(javaType = ListaItemPrecioModel.class),
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
	@Result(property = PROP_NOMBRECATALOGO,column = COL_NOMBRECATALOGO),
	@Result(property = PROP_CODTIPOOPERACIONIGV,column = COL_CODTIPOOPERACIONIGV),
	@Result(property = PROP_DESCTIPOIGV,column = COL_DESCTIPOIGV),
	@Result(property = PROP_IDBIENCATA,column = COL_IDBIENCATA),
	@Result(property = PROP_DESCCATEGORIABIEN,column = COL_DESCCATEGORIABIEN),
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
	public List<ListaItemPrecioModel> getListaPreciosXAdmiCentralHabilitados(@Param("udIdAdministrativa") Integer udIdAdministrativa);
	
	@Insert(value="{CALL REGISTRAR_ITEM_PRECIO ("
			+"#{nuevoItem.udId, mode=IN, jdbcType=INTEGER},"
			+"#{nuevoItem.descripcion, mode=IN, jdbcType=VARCHAR},"
			+"#{nuevoItem.nUnidadMedida, mode=IN, jdbcType=VARCHAR},"
			+"#{nuevoItem.precio, mode=IN, jdbcType=INTEGER},"
			+"#{nuevoItem.estado, mode=IN, jdbcType=INTEGER},"
			+"#{nuevoItem.idMoneda, mode=IN, jdbcType=INTEGER},"
			+"#{nuevoItem.usuarioReg, mode=IN, jdbcType=VARCHAR},"
			+"#{nuevoItem.codigoCatalogo, mode=IN, jdbcType=VARCHAR},"
			+"#{nuevoItem.codTipoperacionIgv, mode=IN, jdbcType=INTEGER},"
			+"#{nuevoItem.numResolucion, mode=IN, jdbcType=VARCHAR},"
			+"#{nuevoItem.tipoResolucion, mode=IN, jdbcType=VARCHAR},"
			+"#{nuevoItem.idBienCata, mode=IN, jdbcType=INTEGER},"
			+"#{nuevoItem.udIdAdministrativa, mode=IN, jdbcType=INTEGER},"
			+"#{nuevoItem.idUnidadConcepto, mode=IN, jdbcType=INTEGER},"
			+"#{nuevoItem.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarItem(@Param("nuevoItem") ListaItemPrecioModel item);
	
	@Update(value = "UPDATE WEBQPROTESORERIA.LISTA_ITEM_PRECIO \n " +
			" SET EST = #{estado} \n " +
			" WHERE  UD_ID = #{udId} AND trim(COD_ITEM) = trim(#{cod_item}) ")
	public void actualizarEstado(@Param("udId") int udId, @Param("cod_item") String codItem, @Param("estado") Integer estado);
	
	@Update(value="{CALL UPDATE_ITEM_PRECIO ("
			+"#{item.codigo, mode=IN, jdbcType=VARCHAR},"
			+"#{item.udId, mode=IN, jdbcType=INTEGER},"
			+"#{item.descripcion, mode=IN, jdbcType=VARCHAR},"
			+"#{item.nUnidadMedida, mode=IN, jdbcType=VARCHAR},"
			+"#{item.precio, mode=IN, jdbcType=INTEGER},"
			+"#{item.estado, mode=IN, jdbcType=INTEGER},"
			+"#{item.idMoneda, mode=IN, jdbcType=INTEGER},"
			+"#{item.usuarioModif, mode=IN, jdbcType=VARCHAR},"
			+"#{item.codigoCatalogo, mode=IN, jdbcType=VARCHAR},"
			+"#{item.codTipoperacionIgv, mode=IN, jdbcType=INTEGER},"
			+"#{item.idBienCata, mode=IN, jdbcType=INTEGER},"
			+"#{item.numResolucion, mode=IN, jdbcType=VARCHAR},"
			+"#{item.tipoResolucion, mode=IN, jdbcType=VARCHAR},"
			+"#{item.idUnidadConcepto, mode=IN, jdbcType=INTEGER},"
			+"#{item.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarItem(@Param("item") ListaItemPrecioModel item);
	
	@Delete(value="{CALL DELETE_ITEM_PRECIO ("
			+"#{item.udId, mode=IN, jdbcType=INTEGER},"
			+"#{item.codigo, mode=IN, jdbcType=VARCHAR},"
			+"#{item.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void deleteItemPrecio(@Param("item") ListaItemPrecioModel item);

	
}