package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.EstablecimientoTicketModel;
public interface EstablecimientoTicketPersistence {
	 
	@Select(value = " SELECT " +
			" UD_ID AS ud_id, " +
			" ESTADO AS estado, " +   
			" CODIGO_ESTAB AS cod_estab, " +
			" UD_DSC AS descripcion, " + 
			" ULT_TICKET AS ult_ticket, " + 
			" DIRECCION AS direccion, " +
			" TELEFONO AS telefono, " +
			" TICKETERA AS ticketera " +  
			" FROM WEBQPROTESORERIA.UNIDAD_ESTAB_TICKET ue " +
			" inner join QPRODATAQUIPU.UNI_DEP ud " +
			" ON ud.UD_ID=ue.UD_ID BY UD_DSC ASC ")
	@Results(value = {@Result(javaType = EstablecimientoTicketModel.class),
	@Result(property = "udId", column = "ud_id"),
	@Result(property = "estado", column = "estado"),
	@Result(property = "codigoEstablecimiento", column = "cod_estab"),
	@Result(property = "unidad", column = "descripcion"),
	@Result(property = "ultimoTicket", column = "ult_ticket"),
	@Result(property = "direccion", column = "direccion"),
	@Result(property = "telefono", column = "telefono"),
	@Result(property = "ticketera", column = "ticketera")
	})
	public List<EstablecimientoTicketModel> listarEstablecimientoTicket();
}
