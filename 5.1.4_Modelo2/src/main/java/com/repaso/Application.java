package com.repaso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.repaso.modelo.Usuario;
import com.repaso.repository.IUsuarioRepository;
import com.repaso.servicio.IUsuarioServicio;

@SpringBootApplication
public class Application {

    	private static final Logger log = LoggerFactory.getLogger(Application.class);
    
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	    
//	    AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(AppConfig.class);
	    
//	    IUsuarioRepository iUsuarioRepository = acac.getBean(IUsuarioRepository.class);
//	    IUsuarioServicio iUsuarioServicio = acac.getBean(IUsuarioServicio.class);
//	    Usuario usuario = iUsuarioServicio.findById(1).getUsuarios().get(0);
	    
//	    Usuario sui = new Usuario(22, "Sui-Lan González Mac", "1234", 16366014, "7777");
//	    Usuario jose = iUsuarioServicio.findById(2).getUsuarios().get(0);
//	    jose.setClave("1222");
//	    System.out.println(iUsuarioServicio.getAllUsuarios());
//	    System.out.println(iUsuarioServicio.findByNombreAndClave("Sui-Lan González", "1234"));
//	    System.out.println(iUsuarioServicio.login("Sui-Lan González", "1234").getMensaje());
//	    System.out.println(iUsuarioServicio.delete(usuario));
//	    System.out.println(iUsuarioServicio.findById(1).getUsuarios().get(0).getRut());
//	    System.out.println(iUsuarioServicio.add(sui));
//	    System.out.println(iUsuarioServicio.update(sui));
//	    System.out.println(iUsuarioServicio.update(jose).getMensaje());
	    
	}

//	@Bean
//	public CommandLineRunner demo(IUsuarioRepository repo) {
//        	return (args) -> {
//                	// agregamos dos usuarios
//                	repo.save(new Usuario(null, "usuario 1", "clave 1", 12345, "k"));
//                	repo.save(new Usuario(null, "usuario 2", "clave 2", 54321, "9"));
//                	// leemos todos los usuarios
//                	log.info("Lista de usuarios");
//                	for (Usuario usuario : repo.findAll()) {
//                	    log.info(usuario.toString());
//            	}
//        	};
//	}
}
