package pe.edu.unmsm.quipucamayoc.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pe.edu.unmsm.quipucamayoc.service.UsuarioService;
import pe.edu.unmsm.quipucamayoc.model.*;
@Controller
public class ApplicationController {
    @Autowired
    private UsuarioService usuarioService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout,@RequestParam(value = "sinPerfil", required = false) String sinPerfil,HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Usuario y/o password incorrecto(s)!");
            model.addObject("display", "block");
        }
        if (logout != null) {
            model.addObject("msg", "Gracias por usar el sistema.");
            model.addObject("display", "block");
        }
        if(sinPerfil!=null){
            model.addObject("error", "Usuario no registrado!");
            model.addObject("display", "block");       	
        }
        HttpSession misession = request.getSession();
        UsuarioModel usuario = (UsuarioModel) misession.getAttribute("usuario");
        if(usuario==null){
            model.setViewName("login");
        }
        else{
            model.setViewName("redirect:/admin");
        }
        return model;
    }
    
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpSession misession = request.getSession();
        UsuarioModel usuario = (UsuarioModel) misession.getAttribute("usuario");
        if(usuario==null){
            usuario = usuarioService.datosDeUsuario(userDetails.getUsername());
            misession.setAttribute("usuario",usuario);
        }
        if(usuario!=null){
        model.addObject("userDetails", userDetails);
        model.addObject("userAuthorities",userDetails.getAuthorities());
        model.addObject("userPerfil",usuario.getPerfil());
        model.addObject("userCodDependencia", usuario.getCodDependencia());
        model.addObject("userDscDependenciaPad", usuario.getDscDependencian2());
        model.addObject("userDscDependencia", usuario.getDscDependencia());
        model.addObject("nombre", usuario.getNombre());
        model.addObject("userIdDependencia", usuario.getIdDependencia());
        model.addObject("idDepPadre", usuario.getIdDepPadre());
        model.addObject("idUnidadAdministrativa", usuario.getIdUnidadAdministrativa());
        model.addObject("dscUnidadAdministrativa", usuario.getDscUnidadAdministrativa());
        model.setViewName("admin");
        }else
        	model.setViewName("redirect:/login?sinPerfil");
        return model;
    }
    @RequestMapping(value = { "/", "/home**" }, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }
}

