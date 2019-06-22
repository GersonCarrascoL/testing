package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.MaqLocalModel;
import pe.edu.unmsm.quipucamayoc.service.MaqLocalService;

@Controller
@RequestMapping("/rest/MaqLocalController")
public class MaqLocalController {
	
	@Autowired private MaqLocalService maqLocalService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/getMaqLocalesTodos")
    @ResponseBody
    public List<MaqLocalModel> listarLocalesTodos(@PathVariable(value = "ud_id_cat") Integer udIdCat){
		return maqLocalService.getLocalesTodos();
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/getMaqLocales/{udId}")
    @ResponseBody
    public List<MaqLocalModel> listarLocalesXudId(@PathVariable(value = "udId") Integer udId){
		return maqLocalService.getLocalesXUdId(udId);
    }

}
