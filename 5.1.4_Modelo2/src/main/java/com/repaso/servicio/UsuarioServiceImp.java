package com.repaso.servicio;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repaso.Application;
import com.repaso.modelo.Usuario;
import com.repaso.repository.IUsuarioRepository;
import com.repaso.vo.NumberVO;
import com.repaso.vo.UsuarioVO;

@Service
public class UsuarioServiceImp implements IUsuarioServicio {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
	    @Autowired
	    IUsuarioRepository iUsuarioRepository;

//	    UsuarioVO usuarioVO;
	    
	    @Override
	    public UsuarioVO getAllUsuarios() {
		UsuarioVO usuarioVO = new UsuarioVO("101", "Ha ocurrido un error", new ArrayList<Usuario>());
		try {
		    usuarioVO.setUsuarios((List<Usuario>) iUsuarioRepository.findAll());
		    usuarioVO.setMensaje(String.format("Se ha/n encontrado %d registro/s", usuarioVO.getUsuarios().size()));
		    usuarioVO.setCodigo("0");
		} catch (Exception e) {
		    log.trace("Usuario Service: Error en getAllUsuarios", e);
		}
		return usuarioVO;
	    }

	    @Override
	    public UsuarioVO findByNombreAndClave(String nombre, String clave) {
		UsuarioVO usuarioVO = new UsuarioVO("101", "Ha ocurrido un error", new ArrayList<Usuario>());
		try {
		    List<Usuario> usuarios = iUsuarioRepository.findByNombreAndClave(nombre, clave);
		    if(usuarios.size() > 0) {
			usuarioVO.setUsuarios(usuarios);
			usuarioVO.setMensaje("Usuario encontrado correctamente");
			usuarioVO.setCodigo("0");
		    } else {
			usuarioVO.setMensaje("Usuario no encontrado");
		    }
		} catch (Exception e) {
		    log.trace("Usuario Service: Error en findByNombreAndClave", e);
		}
		return usuarioVO;
	    }

	    @Override
	    public UsuarioVO login(String nombre, String clave) {
		UsuarioVO usuarioVO = new UsuarioVO("103", "Credenciales incorrectas", new ArrayList<Usuario>());
		if(nombre.length() == 0 || clave.length() == 0)
		    return usuarioVO;
		usuarioVO = findByNombreAndClave(nombre, clave);
		if(usuarioVO.getCodigo().equals("0")) {
		    usuarioVO.setMensaje(String.format("Bienvenido %s", usuarioVO.getUsuarios().get(0).getNombre()));
		}
		return usuarioVO;
	    }

	    @Override
	    public UsuarioVO add(Usuario usuario) {
		UsuarioVO usuarioVO = new UsuarioVO("103", "Credenciales incorrectas", new ArrayList<Usuario>());
		try {
		    iUsuarioRepository.save(usuario);
		    usuarioVO.setMensaje(String.format("Se ha guardado correctamente al usuario %s", usuario.getNombre()));
		    usuarioVO.setCodigo("0");
		} catch (Exception e) {
		    log.trace("Usuario Service: Error en add", e);
		}
		return usuarioVO;
	    }

	    @Override
	    public UsuarioVO update(Usuario usuario) {
		UsuarioVO usuarioVO = new UsuarioVO("103", "Credenciales incorrectas", new ArrayList<Usuario>());
		try {
		    iUsuarioRepository.save(usuario);
		    usuarioVO.setMensaje(String.format("Se ha actualizado correctamente al usuario %s", usuario.getNombre()));
		    usuarioVO.setCodigo("0");
		} catch (Exception e) {
		    log.trace("Usuario Service: Error en add", e);
		}
		return usuarioVO;
	    }

	    @Override
	    public UsuarioVO delete(Usuario usuario) {
		UsuarioVO usuarioVO = new UsuarioVO("106", "Ha ocurrido un error", new ArrayList<Usuario>() );
			try {
        			iUsuarioRepository.delete(usuario);
        			usuarioVO.setMensaje("Se ha eliminado correctamente al usuario");
        			usuarioVO.setCodigo("0");
			} catch (Exception e) {
			    log.trace("Usuario Service: Error en delete", e);
			}
		return usuarioVO;
	    }

	    @Override
	    public UsuarioVO findById(Integer id) {
		UsuarioVO usuarioVO = new UsuarioVO("107", "Ha ocurrido un error", new ArrayList<Usuario>() );
		try {
			Usuario usuario = iUsuarioRepository.findById(id).get();
			usuarioVO.getUsuarios().add(usuario);
			usuarioVO.setMensaje("Se ha buscado correctamente al usuario");
			usuarioVO.setCodigo("0");
		} catch (Exception e) {
		    log.trace("Usuario Service: Error en delete", e);
		}
		return usuarioVO;
	    }
	    
	    @Override
	    @Transactional(readOnly = true)
	    public UsuarioVO getPage(Integer pagina, Integer cantidad) {
		UsuarioVO usuarioVO = new UsuarioVO("108", "Ha ocurrido un error", new ArrayList<Usuario>() );
	    try {
        	Pageable pageable = PageRequest.of(pagina,cantidad);
        	Page<Usuario> responsePage = iUsuarioRepository.findAll(pageable);
        	usuarioVO.setUsuarios(responsePage.getContent());
        	usuarioVO.setMensaje(String.format("Se ha/n encontrado %d registro/s",
        	usuarioVO.getUsuarios().size()));
        	usuarioVO.setCodigo("0");
	    } catch (Exception e) {
		log.trace("Usuario Service: Error en getPage", e);
	    }
	    return usuarioVO;
	    }
	    
	    @Override
	    @Transactional(readOnly = true)
	    public NumberVO getPageCount(long registrosPorPagina) {
		NumberVO respuesta = new NumberVO(0, "Ha ocurrido un error", "109" );
		try {
		    long registros = iUsuarioRepository.count();
		    if(registrosPorPagina == 0 && registros == 0) {
			respuesta.setValor(1);
		    }else {
			respuesta.setValor((registros/registrosPorPagina) + (registros %registrosPorPagina == 0 ? 0 : 1));
		    }
		    respuesta.setCodigo("201");
		    respuesta.setMensaje(String.format("Hay %d paginas", respuesta.getValor()));
		} catch (Exception e) {
		    log.trace("Usuario Service: Error en getPageCount", e);
		}
		return respuesta;
	    }
	    
}
