package com.repaso.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.repaso.modelo.Usuario;

@SessionScope
@Service
public class SecurityServiceImpl implements ISecurityService{

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);
    Usuario usuarioConectado = null;
    
    @Override
    public boolean isLoggedIn() {
	log.info("Consultando por usuario autenticado");
	return null != this.usuarioConectado;
    }
    
    @Override
    public void setUsuarioConectado(Usuario usuario) {
	log.info("Estableciendo usuario conectado");
	this.usuarioConectado = usuario;
	
    }
    @Override
    public Usuario getUsuarioConectado() {
	log.info("Devolviendo al usuario conectado");
	return usuarioConectado;
    }
    
    
}
