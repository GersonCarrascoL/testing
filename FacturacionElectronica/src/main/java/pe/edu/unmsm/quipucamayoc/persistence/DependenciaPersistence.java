package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import pe.edu.unmsm.quipucamayoc.model.*;

public interface DependenciaPersistence {
	
	String PROP_IDUNIDAD= "idUnidad";
	
	String COL_IDUNIDAD = "ID_UNIDAD";
	
    @Select(value = "  SELECT DEPENDENCIA.UD_ID AS ID_UNIDAD, \n" +
            "          DEPENDENCIA.UD_COD AS COD_UNIDAD, \n" +
            "          DEPENDENCIA.UD_DSC AS DESCRIPCION, \n" +
            "          DEPENDENCIA.NIVUNICOD AS NIVEL,\n" +
            "		   UNICOD.CODIGO_UNIDAD AS NUM_UNIDAD, \n" +
            "		   UNICOD.ESTADO AS estado_codUnidad, \n" +
            "		   UNICOD.ESTADO_SOLICITUD AS est_sol_codUnidad \n" +
            "          FROM QPRODATAQUIPU.UNI_DEP DEPENDENCIA \n" +
            "          LEFT JOIN WEBQPROTESORERIA.UNIDAD_CODIGO UNICOD ON UNICOD.UD_ID = DEPENDENCIA.UD_ID \n" +
            "          WHERE (DEPENDENCIA.NIVUNICOD IN (1,2,3,4,5,6,7) AND  DEPENDENCIA.UD_FEC_CAD IS NULL)\n" +
            "          AND DEPENDENCIA.UD_COD NOT LIKE 'Z%'\n" +
            "          ORDER BY DEPENDENCIA.UD_COD")
    @Results(value = {@Result(javaType = DependenciaModel.class),
            @Result(property = PROP_IDUNIDAD, column = COL_IDUNIDAD),
            @Result(property = "codUnidad", column = "COD_UNIDAD"),
            @Result(property = "descripcion", column = "DESCRIPCION"),
            @Result(property = "nivel", column = "NIVEL"),
            @Result(property = "numUnidad", column = "NUM_UNIDAD"),
    		@Result(property = "estadoCodUnidad", column = "estado_codUnidad"),
    		@Result(property = "estSolCodUnidad", column = "est_sol_codUnidad")
    })
    public List<DependenciaModel> unidades();
    
    
    @Select(value = "  SELECT DEPENDENCIA.UD_ID AS ID_UNIDAD, \n" +
            "          DEPENDENCIA.UD_COD AS COD_UNIDAD, \n" +
            "          DEPENDENCIA.UD_DSC AS DESCRIPCION, \n" +
            "          DEPENDENCIA.NIVUNICOD AS NIVEL,\n" +
            "		   UNICOD.CODIGO_UNIDAD AS NUM_UNIDAD, \n" +
            "		   UNICOD.ESTADO AS estado_codUnidad, \n" +
            "		   UNICOD.ESTADO_SOLICITUD AS est_sol_codUnidad \n" +
            "          FROM QPRODATAQUIPU.UNI_DEP DEPENDENCIA \n" +
            "          LEFT JOIN WEBQPROTESORERIA.UNIDAD_CODIGO UNICOD ON UNICOD.UD_ID = DEPENDENCIA.UD_ID \n" +
            "          WHERE (DEPENDENCIA.NIVUNICOD IN (1,2,3,4,5,6,7) AND  DEPENDENCIA.UD_FEC_CAD IS NULL)\n" +
            "          AND DEPENDENCIA.UD_COD NOT LIKE 'Z%'\n" +
            "          AND DEPENDENCIA.UD_COD NOT LIKE 'F%'\n" +
            "		   AND substr( ud_cod,1,5) not in ('E0506','C0333', 'C0310', 'D0407','C0334','C0316','C0317','C0318'  )\n" +
            "          ORDER BY DEPENDENCIA.UD_COD")
    @Results(value = {@Result(javaType = DependenciaModel.class),
            @Result(property = PROP_IDUNIDAD, column = COL_IDUNIDAD),
            @Result(property = "codUnidad", column = "COD_UNIDAD"),
            @Result(property = "descripcion", column = "DESCRIPCION"),
            @Result(property = "nivel", column = "NIVEL"),
            @Result(property = "numUnidad", column = "NUM_UNIDAD"),
    		@Result(property = "estadoCodUnidad", column = "estado_codUnidad"),
    		@Result(property = "estSolCodUnidad", column = "est_sol_codUnidad")
    })
    public List<DependenciaModel> unidadesXTeso();
    
    
    
	    @Select(value="	select  dep.ud_cod as cod_padre,\n"+  
	    			  " dep.ud_dsc as name_padre,\n"+
	    			  " dep.ud_id as id_unidad\n"+
	    			  "	from qprodataquipu.uni_dep dep\n"+
	    			  " where (dep.ud_cod = substr(#{cod_hijo},1,5))")
	    @Results(value =  {
	            @Result(javaType = DependenciaModel.class),
	            @Result(property = "codPadre", column = "cod_padre"),
	            @Result(property = "namePadre", column = "name_padre"),
	            @Result(property = PROP_IDUNIDAD, column = "id_unidad")
	    })
	    public DependenciaModel nombrePadreFacultad(@Param("cod_hijo")String codHijo);
	    
	    
	
	    
	    @Select(value="	select  dep.ud_cod as cod_padre,\n"+  
  			  " dep.ud_dsc as name_padre,\n"+
  			  " dep.ud_id as id_unidad\n"+
  			  "	from qprodataquipu.uni_dep dep\n"+
  			  " where (dep.ud_cod = substr(#{cod_hijo},1,3))")
	      @Results(value =  {
	    		  @Result(javaType = DependenciaModel.class),
	    		  @Result(property = "codPadre", column = "cod_padre"),
		          @Result(property = "namePadre", column = "name_padre"),
		          @Result(property = PROP_IDUNIDAD, column = "id_unidad")
	      	})
	    public DependenciaModel nombrePadreDependencia(@Param("cod_hijo")String codHijo);
	    
	    @Select(value = "SELECT\n" +
	    		"DEP.UD_ID AS ID_UNIDAD,\n" +
	    		"DEP.UD_COD AS COD_UNIDAD,\n" +
	    		"DEP.UD_DSC AS DESCRIPCION,\n" +
	    		"ESTAB.CODIGO_ESTAB,\n" +
	    		"ESTAB.NRO_ULT_COMP\n" +
	    		"FROM WEBQPROTESORERIA.UNIDAD_ESTABLECIMENTO ESTAB\n" +
	    		"INNER JOIN QPRODATAQUIPU.UNI_DEP DEP ON (ESTAB.UD_ID = DEP.UD_ID)\n" +
	    		"WHERE (ESTAB.ESTADO = 1 AND DEP.UD_FEC_CAD is null)\n" +
	    		"ORDER BY\n" +
	    		"DEP.UD_DSC ASC")
		@Results(value = {@Result(javaType = DependenciaModel.class),
		        @Result(property = PROP_IDUNIDAD, column = COL_IDUNIDAD),
		        @Result(property = "codUnidad", column = "COD_UNIDAD"),
		        @Result(property = "descripcion", column = "DESCRIPCION"),
		        @Result(property = "nEstablecimiento", column = "CODIGO_ESTAB"),
		        @Result(property = "nSerie", column = "NRO_ULT_COMP")
		})
		public List<DependenciaModel> unidadesBase();
	     
	    
	    @Select(value = "SELECT \n" +
						" FAC.UD_ID AS UD_ID, \n" +
						" FAC.UD_DSC AS UD_DSC \n" +
						" FROM \n" +
						" WEBQPROTESORERIA.FACULTAD FAC \n" +
						" ORDER BY \n" +
						" UD_DSC ASC")
		@Results(value = {@Result(javaType = DependenciaModel.class),
		        @Result(property = "udId", column = "UD_ID"),
		        @Result(property = "udDsc", column = "UD_DSC")
		})
		public List<UnidadModel> facultadesYDependencias();

}
