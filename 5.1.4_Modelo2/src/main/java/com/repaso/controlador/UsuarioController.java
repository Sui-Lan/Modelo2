package com.repaso.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.repaso.modelo.Usuario;
import com.repaso.servicio.ISecurityService;
import com.repaso.servicio.IUsuarioServicio;
import com.repaso.vo.UsuarioVO;
import com.repaso.util.Util;

@Controller
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    
	    @Autowired
	    private IUsuarioServicio iUsuarioServicio;
	    
	    @Autowired
	    private ISecurityService secSvc;
	    
	    @GetMapping({"/", "/home"})
	    public String home(Model model, @RequestParam(defaultValue = "1") Integer p) {
		if(!secSvc.isLoggedIn()) return "redirect:/login";
		model.addAttribute("usuarioConectado", String.format("Conectado como %s", secSvc.getUsuarioConectado().getNombre()));
		model.addAttribute("paginaActual", p);
	    	model.addAttribute("VO", iUsuarioServicio.getAllUsuarios());
		return "home";
	    }
	    
	    @GetMapping({"/editarForm"})
	    public ModelAndView editarForm(Model model, @RequestParam String idUsuario, RedirectAttributes ra) {
		if(!secSvc.isLoggedIn()) return new ModelAndView("redirect:/login");
		UsuarioVO respuestaServicio = new UsuarioVO();
		respuestaServicio.setMensaje("No se pudo cargar la vista de edición, intente nuevamente.");
		try {
		    Integer id = (Integer.parseInt(idUsuario));
		    respuestaServicio = iUsuarioServicio.findById(id);
		    model.addAttribute("mensaje", respuestaServicio.getMensaje());
		    model.addAttribute("VO", respuestaServicio.getUsuarios().get(0));
		    return new ModelAndView("editar");
		} catch (Exception e) {
		    log.error("Error en UsuarioController eliminar", e);
		}
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		respuestaServicio = iUsuarioServicio.getAllUsuarios();
		return new ModelAndView("redirect:/home");
	    }
	    
	    @PostMapping("/editar")
	    public ModelAndView editar(@ModelAttribute Usuario usuario, RedirectAttributes ra) {
		if(!secSvc.isLoggedIn()) return new ModelAndView("redirect:/login");
		UsuarioVO respuestaServicio = iUsuarioServicio.update(usuario);
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		if (respuestaServicio.getCodigo().equals("0")) {
		    return new ModelAndView("redirect:/home");
		} else {
		    return new ModelAndView("redirect:/editarForm");
		}
	    }
	    
	    @GetMapping("/agregarForm")
	    public String agregarForm(Model model) {
		if(!secSvc.isLoggedIn()) return "redirect:/login";
		return "agregar";
	    }
	    
	    @PostMapping("/agregar")
	    public ModelAndView agregarSubmit(@ModelAttribute Usuario usuario, RedirectAttributes ra) {
		if(!secSvc.isLoggedIn()) return new ModelAndView("redirect:/login");
        	UsuarioVO respuestaServicio = iUsuarioServicio.add(usuario);
        	ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
        	if (respuestaServicio.getCodigo().equals("0")) {
        	    return new ModelAndView("redirect:/home");
        	} else {
        	    return new ModelAndView("redirect:/agregarForm");
        	}
	    }
	    
	    @GetMapping("/eliminar")
	    public ModelAndView eliminar(Model model, @RequestParam String idUsuario, RedirectAttributes ra) {
		if(!secSvc.isLoggedIn()) return new ModelAndView("redirect:/login");
        	    UsuarioVO respuestaServicio = new UsuarioVO();
        	    respuestaServicio.setMensaje("No se pudo eliminar al usuario, intente nuevamente.");
        	    try {
        		Usuario eliminado = new Usuario();
        	    	eliminado.setIdUsuario(Integer.parseInt(idUsuario));
        	    	respuestaServicio = iUsuarioServicio.delete(eliminado);
        	    	ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
        	    	return new ModelAndView("redirect:/home");
        	    } catch (Exception e) {
        		log.error("Error en UsuarioController eliminar", e);
        	    }
            	    ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
            	    respuestaServicio = iUsuarioServicio.getAllUsuarios();
            	    ra.addAttribute("VO", respuestaServicio);
        	    return new ModelAndView("redirect:/home");
	    }
	    
}
