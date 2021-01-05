package com.repaso.vo;

import java.util.List;

import com.repaso.modelo.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioVO extends GenericVO {

    List<Usuario> usuarios;
    
    public UsuarioVO(String mensaje, String codigo, List<Usuario> usuarios) {
        super(mensaje, codigo);
        this.usuarios = usuarios;
    }
    
}
