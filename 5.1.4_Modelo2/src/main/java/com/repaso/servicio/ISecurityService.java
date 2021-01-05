package com.repaso.servicio;

import com.repaso.modelo.Usuario;
import com.repaso.vo.UsuarioVO;

public interface ISecurityService {

    public boolean isLoggedIn();
    public void setUsuarioConectado(Usuario usuario);
    public Usuario getUsuarioConectado();
    
}
