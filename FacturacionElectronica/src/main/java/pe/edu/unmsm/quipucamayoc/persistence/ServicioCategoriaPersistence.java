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

import pe.edu.unmsm.quipucamayoc.model.ServicioCategoriaModel;

public interface ServicioCategoriaPersistence {
	
	@Select(value = " SELECT " +
					" ID_SERV_CATA AS id_serv_cata, " +
					" UD_ID_ADMINISTRATIVA AS ud_id_categoria, " +   
					" DESC_CATEGORIA AS desc_categoria_serv, " +
					" DESC_CATEGORIA AS filter_categoria_serv " + 
					" FROM WEBQPROTESORERIA.SERVICIO_CATEGORIA " +
					" where UD_ID_ADMINISTRATIVA = #{ud_id_cat} " +
					" ORDER BY DESC_CATEGORIA ASC ")
	@Results(value = {@Result(javaType = ServicioCategoriaModel.class),
	@Result(property = "idServCata", column = "id_serv_cata"),
	@Result(property = "udIdCategoria", column = "ud_id_categoria"),
	@Result(property = "descCategoriaServ", column = "desc_categoria_serv"),
	@Result(property = "filterCategoriaServ", column = "filter_categoria_serv")
	})
	public List<ServicioCategoriaModel> getCategoriasServicios(@Param("ud_id_cat") Integer udIdCat);
	
	@Insert(value="{CALL REGISTRAR_CATEGORIA_SERVICIO ("
			+"#{categoria.udIdCategoria, mode=IN, jdbcType=INTEGER},"
			+"#{categoria.descCategoriaServ, mode=IN, jdbcType=VARCHAR},"
			+"#{categoria.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarcategoria(@Param("categoria") ServicioCategoriaModel categoria);


	@Update(value="{CALL UPDATE_CATEGORIA_SERVICIO ("
			+"#{categoria.idServCata, mode=IN, jdbcType=INTEGER},"
			+"#{categoria.udIdCategoria, mode=IN, jdbcType=INTEGER},"
			+"#{categoria.descCategoriaServ, mode=IN, jdbcType=VARCHAR},"
			+"#{categoria.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarcategoria(@Param("categoria") ServicioCategoriaModel categoria);	
	
	
	@Delete(value="{CALL DELETE_CATEGORIA_SERVICIO ("
			+"#{categoria.idServCata, mode=IN, jdbcType=INTEGER},"			
			+"#{categoria.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void deleteCategoria(@Param("categoria") ServicioCategoriaModel categoria);
	

}
