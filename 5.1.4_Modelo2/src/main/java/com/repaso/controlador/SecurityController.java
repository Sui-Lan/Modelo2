package com.repaso.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.repaso.modelo.Usuario;
import com.repaso.servicio.ISecurityService;
import com.repaso.servicio.IUsuarioServicio;
import com.repaso.vo.UsuarioVO;

@Controller
public class SecurityController {

    private static final Logger log= LoggerFactory.getLogger(UsuarioController.class);
    
    @Autowired
    private IUsuarioServicio iUsuarioServicio;
    
    @Autowired
    private ISecurityService iSecurityService; 
    
    @GetMapping({"/", "/login"})
    public String login(Model model) {
	return iSecurityService.isLoggedIn() ? "redirect:/home" : "login";
    }
    
    @PostMapping({"/handleLogin"})
    public ModelAndView handledLogin(@ModelAttribute Usuario usuario, RedirectAttributes ra) {
	UsuarioVO respuestaServicio = iUsuarioServicio.login(usuario.getNombre(), usuario.getClave());
	log.info(String.format("Consultando por usuario autenticado (%s)", respuestaServicio));
	ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
	if(respuestaServicio.getCodigo().equals("0")) {;
		iSecurityService.setUsuarioConectado(respuestaServicio.getUsuarios().get(0));
		return new ModelAndView("redirect:/home");
	} else {
	    return new ModelAndView("redirect:/login");
	}
    }
    
    @PostMapping({"/handledLogout"})
    public String handledLogout(@ModelAttribute Usuario usuario, RedirectAttributes ra) {
	iSecurityService.setUsuarioConectado(null);
	return "redirect:/login/";
    }
    
}
