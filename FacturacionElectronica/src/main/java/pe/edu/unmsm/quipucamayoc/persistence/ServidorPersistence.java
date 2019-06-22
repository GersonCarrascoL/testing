package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.ServidorModel;


public interface ServidorPersistence {
	
	String PROP_CODANT = "codAnt";
	String PROP_CESANTIA = "cesantia";
	String PROP_DOMICILIO = "domicilio";
	String PROP_ESTADO = "estado";
	String PROP_TIPODOC = "tipoDoc";
	String PROP_NOMBREDOC = "nombreDoc";
	
	String COL_CODANT = "codAnt";
	String COL_CESANTIA = "cesantia";
	String COL_DOMICILIO = "domicilio";
	String COL_ESTADO = "estado";
	String COL_TIPODOC = "tipoDoc";
	String COL_NOMBREDOC = "nombreDoc";
	
	@Select(value = "SELECT VSER.ser_cod   AS ser_cod, " +
            "  VSER.dni                AS dni,    "+
            "  VSER.ser_cod_ant        AS codAnt,"+
            "  VSER.abv_est            AS abrevEst,"+
            "  VSER.des_abr_tip_ser    AS abrevTip,"+
            "  VSER.ser_ape_pat        AS apePat, " +
            "  VSER.ser_ape_mat        AS apeMat, " +
            "  VSER.ser_nom            AS nombres, "+
            "  VSER.DES_TIP_SER        AS cargo, "+
            "  VSER.des_dep_cesantes   AS cesantia, "+
            "  VSER.ser_dom            AS domicilio, "+
            "  VSER.desc_est           AS estado, "+
            "  VSER.num_serest         AS  estadoActual, "+
            "  (case when VSER.SER_TIP_DOC_ID_ACT = 0 then 1 else nvl(VSER.SER_TIP_DOC_ID_ACT, 1 ) end ) AS tipoDoc, "+
            "  DOC.DES_DOC_ID     	   AS nombreDoc "+
            "  FROM DATAPERSUEL.LISTA_SERVIDOR VSER " +
            "  INNER JOIN DATAPERSUEL.DOC_IDENTIDAD DOC on (DOC.COD_DOC_ID = VSER.SER_TIP_DOC_ID_ACT) " +
            "  where VSER.ser_est_act != 3 and VSER.ser_con_pla_act in (1,2,3,4,8) " +
            "  and UPPER( decode(TRIM(VSER.SER_APE_PAT), null, '', TRIM(VSER.SER_APE_PAT) || ' ') || decode(TRIM(VSER.SER_APE_MAT), null, '',TRIM(VSER.SER_APE_MAT) || ' ') ||TRIM(VSER.SER_NOM) ) LIKE UPPER('%'||TRIM(#{nombre})||'%') " +
            "  and rownum<100" +
            "  ORDER BY apePat asc, apeMat asc, nombres asc ")
    @Results(value={
    		@Result(javaType = ServidorModel.class),
            @Result(property = "codigo", column = "ser_cod"),
            @Result(property = "numDoc", column = "dni"),
            @Result(property = PROP_CODANT, column = COL_CODANT),
            @Result(property = "abvEst", column = "abrevEst"),
            @Result(property = "abvTipSer", column = "abrevTip"),
            @Result(property = "paterno", column = "apePat"),
            @Result(property = "materno", column = "apeMat"),
            @Result(property = "nombre", column = "nombres"),
            @Result(property = "tipoServicio", column = "cargo"),
            @Result(property = PROP_CESANTIA, column = COL_CESANTIA),
            @Result(property = PROP_DOMICILIO, column = COL_DOMICILIO),
            @Result(property = PROP_ESTADO, column = COL_ESTADO) ,
            @Result(property = "estadoTrabaActual", column = "estadoActual"),
            @Result(property = PROP_TIPODOC, column = COL_TIPODOC) ,
            @Result(property = PROP_NOMBREDOC, column = COL_NOMBREDOC)
    })
	public List<ServidorModel> listarXNombres(@Param("nombre") String nombre);
	
	@Select(value = "SELECT ser_cod   AS ser_cod, " +
            "  VSER.dni                AS dni,    "+
            "  VSER.ser_cod_ant        AS codAnt,"+
            "  VSER.abv_est            AS abrevEst,"+
            "  VSER.des_abr_tip_ser    AS abrevTip,"+
            "  VSER.ser_ape_pat        AS apePat, " +
            "  VSER.ser_ape_mat        AS apeMat, " +
            "  VSER.ser_nom            AS nombres, "+
            "  VSER.DES_TIP_SER        AS cargo, "+
            "  VSER.des_dep_cesantes   AS cesantia, "+
            "  VSER.ser_dom            AS domicilio, "+
            "  VSER.desc_est           AS estado, "+
            "  VSER.num_serest         AS  estadoActual, "+
            "  (case when VSER.SER_TIP_DOC_ID_ACT = 0 then 1 else nvl(VSER.SER_TIP_DOC_ID_ACT, 1 ) end ) AS tipoDoc, "+
            "  DOC.DES_DOC_ID     	   AS nombreDoc "+
            "  FROM DATAPERSUEL.LISTA_SERVIDOR VSER " +
            "  INNER JOIN DATAPERSUEL.DOC_IDENTIDAD DOC on (DOC.COD_DOC_ID = VSER.SER_TIP_DOC_ID_ACT) " +
            "  where VSER.ser_est_act != 3 and VSER.ser_con_pla_act in (1,2,3,4,8) "+
            "  AND trim(VSER.ser_doc_id_act) like trim(#{numDoc}||'%') and rownum<100")
    @Results(value={
    		@Result(javaType = ServidorModel.class),
            @Result(property = "codigo", column = "ser_cod"),
            @Result(property = "numDoc", column = "dni"),
            @Result(property = PROP_CODANT, column = COL_CODANT),
            @Result(property = "abvEst", column = "abrevEst"),
            @Result(property = "abvTipSer", column = "abrevTip"),
            @Result(property = "paterno", column = "apePat"),
            @Result(property = "materno", column = "apeMat"),
            @Result(property = "nombre", column = "nombres"),
            @Result(property = "tipoServicio", column = "cargo"),
            @Result(property = PROP_CESANTIA, column = COL_CESANTIA),
            @Result(property = PROP_DOMICILIO, column = COL_DOMICILIO),
            @Result(property = PROP_ESTADO, column = COL_ESTADO) ,
            @Result(property = "estadoTrabaActual", column = "estadoActual"),
            @Result(property = PROP_TIPODOC, column = COL_TIPODOC) ,
            @Result(property = PROP_NOMBREDOC, column = COL_NOMBREDOC)
    })
	public List<ServidorModel> listarXNumDocumento(@Param("numDoc") String numDoc);

}
