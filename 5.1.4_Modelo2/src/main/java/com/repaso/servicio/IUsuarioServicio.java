package com.repaso.servicio;

import com.repaso.modelo.Usuario;
import com.repaso.vo.NumberVO;
import com.repaso.vo.UsuarioVO;

public interface IUsuarioServicio {
    
    public UsuarioVO getAllUsuarios();
    public UsuarioVO findByNombreAndClave(String nombre, String clave);
    public UsuarioVO login(String nombre, String clave);
    public UsuarioVO add(Usuario usuario);
    public UsuarioVO update(Usuario usuario);
    public UsuarioVO delete(Usuario usuario);
    public UsuarioVO findById(Integer id);
    public UsuarioVO getPage(Integer pagina, Integer cantidad);
    public NumberVO getPageCount(long registrosPorPagina);

}
