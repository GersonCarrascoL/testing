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

import pe.edu.unmsm.quipucamayoc.model.BienCategoriaModel;

public interface BienCategoriaPersistence {
	
	@Select(value = " SELECT " +
				" ID_BIEN_CATA AS id_bien_cata, " +
				" UD_ID_ADMINISTRATIVA AS ud_id_categoria, " +   
				" DESC_CATEGORIA AS desc_categoria_bien, " +
				" DESC_CATEGORIA AS filter_categoria_bien " + 
				" FROM WEBQPROTESORERIA.BIEN_CATEGORIA " +
				" where UD_ID_ADMINISTRATIVA = #{ud_id_cat} " +
				" ORDER BY DESC_CATEGORIA ASC ")
	@Results(value = {@Result(javaType = BienCategoriaModel.class),
	@Result(property = "idBienCata", column = "id_bien_cata"),
	@Result(property = "udIdCategoria", column = "ud_id_categoria"),
	@Result(property = "descCategoriaBien", column = "desc_categoria_bien"),
	@Result(property = "filterCategoriaBien", column = "filter_categoria_bien")
	})
	public List<BienCategoriaModel> getCategoriasBienes(@Param("ud_id_cat") Integer udIdCat);
	
	@Insert(value="{CALL REGISTRAR_CATEGORIA_BIEN ("
		+"#{categoria.udIdCategoria, mode=IN, jdbcType=INTEGER},"
		+"#{categoria.descCategoriaBien, mode=IN, jdbcType=VARCHAR},"
		+"#{categoria.status, mode=OUT, jdbcType=INTEGER}"
		+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarcategoria(@Param("categoria") BienCategoriaModel categoria);
	
	
	@Update(value="{CALL UPDATE_CATEGORIA_BIEN ("
		+"#{categoria.idBienCata, mode=IN, jdbcType=INTEGER},"
		+"#{categoria.udIdCategoria, mode=IN, jdbcType=INTEGER},"
		+"#{categoria.descCategoriaBien, mode=IN, jdbcType=VARCHAR},"
		+"#{categoria.status, mode=OUT, jdbcType=INTEGER}"
		+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarcategoria(@Param("categoria") BienCategoriaModel categoria);	
	
	
	@Delete(value="{CALL DELETE_CATEGORIA_BIEN ("
		+"#{categoria.idBienCata, mode=IN, jdbcType=INTEGER},"			
		+"#{categoria.status, mode=OUT, jdbcType=INTEGER}"
		+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void deleteCategoria(@Param("categoria") BienCategoriaModel categoria);

}
