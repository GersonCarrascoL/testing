
package pe.edu.unmsm.quipucamayoc.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.StatementType;
import pe.edu.unmsm.quipucamayoc.model.FacturaModel;

public interface BoletaPersistence {

	@Insert(value = "{CALL INSERTAR_COMPROBANTE (\n" + "#{i.anio, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.mes, mode=IN, jdbcType=INTEGER},\n" + "#{i.tipo, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.establecimiento, mode=IN, jdbcType=VARCHAR},\n" + "#{i.serie, mode=OUT, jdbcType=VARCHAR},\n"
			+ "TO_DATE(#{i.fecha}, 'dd/mm/yyyy hh24:mi:ss'),\n" + "#{i.documento, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.nombre, mode=IN, jdbcType=VARCHAR},\n" + "#{i.direccion, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.guia, mode=IN, jdbcType=VARCHAR},\n" + "#{i.sub, mode=IN, jdbcType=DOUBLE},\n"
			+ "#{i.igv, mode=IN, jdbcType=DOUBLE},\n" + "#{i.total, mode=IN, jdbcType=DOUBLE},\n"
			+ "#{i.formaPago, mode=IN, jdbcType=VARCHAR},\n" + "#{i.moneda, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.importe, mode=IN, jdbcType=VARCHAR},\n" + "#{i.unidad, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.facultad, mode=IN, jdbcType=VARCHAR},\n" + "#{usuario, mode=IN, jdbcType=VARCHAR},\n"
			+ "#{i.estado, mode=IN, jdbcType=VARCHAR},\n" + "#{i.estUso, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.tipoDoc, mode=IN, jdbcType=VARCHAR},\n" + "#{i.codUbigeoCliente, mode=IN, jdbcType=CHAR},\n"
			+ "PCK_UTIL.ALETRAS(#{i.total}),\n" + "#{detraccion, mode=IN, jdbcType=INTEGER},\n"
			+ "#{i.gravada, mode=IN, jdbcType=DOUBLE},\n" + "#{i.exonerada, mode=IN, jdbcType=DOUBLE},\n"
			+ "#{i.inafecta, mode=IN, jdbcType=DOUBLE},\n"
			+ "#{i.observacion, mode=IN, jdbcType=VARCHAR})}")
	@Options(statementType = StatementType.CALLABLE)
	void insertarBoletaEfectivoS(@Param("i") FacturaModel item, @Param("usuario") String usuario,
			@Param("detraccion") int detraccion);

}
