package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.DetraccionSunatModel;

public interface DetraccionSunatPersistence {
	
	@Select(value = " SELECT fecha, importe_minimo_soles \n" +
					" FROM (SELECT DETFEC AS fecha, IMPORTE_MINIMO_SOLES AS importe_minimo_soles \n" +
					" FROM WEBQPROTESORERIA.SUNAT_DETRACCION \n" +
					" order by DETFEC desc) \n" +
					" WHERE rownum = 1")
	@Results(value = {@Result(javaType = DetraccionSunatModel.class),
	@Result(property = "fecha", column = "fecha"),
	@Result(property = "importeMinimoSoles", column = "importe_minimo_soles")
	})
	public List<DetraccionSunatModel> importeMinimoDetraccion();

	@Select(value = " SELECT " +
					" PORCENT_SERV_DETRAC AS porcentaje " +
					" FROM WEBQPROTESORERIA.SUNAT_SERVICIO_DETRACCION " +
					" where USO_GENERAL = 1")
	@Results(value = {@Result(javaType = DetraccionSunatModel.class),
	@Result(property = "porcentaje", column = "porcentaje")
	})
	public List<DetraccionSunatModel> porcentajeDetraccionUsoGeneral();
}
