package com.ingesoft.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesoft.biblioteca.model.CopiaLibro;
import com.ingesoft.biblioteca.model.Prestamo;
import com.ingesoft.biblioteca.model.Usuario;
import com.ingesoft.biblioteca.repository.CopiaLibroRepository;
import com.ingesoft.biblioteca.repository.PrestamoRepository;
import com.ingesoft.biblioteca.repository.UsuarioRepository;

//Clase encargada de los casos de uso
//es decir, de la logica de negocio
//es decir, de las validaciones, las reglas, los calculos
//que define el negocio para sus entidades

@Service
public class BibliotecaService {
    @Autowired
    CopiaLibroRepository copiaLibrorepository;

    @Autowired
    UsuarioRepository UsuarioRepository;

    @Autowired
    PrestamoRepository prestamoRepository;


 //CU01
 /*
 Actor ingresa codigo de la copia del libro
Sistema valida que exista una copia con ese codigo
Sistema valida que la copia este disponible
Actor ingresa codigo del usuario
Sistema valida que exista un usuario con ese codigo
Almacena un nuevo prestamo con el codigo de la copia, el codigo del libro y la fecha actual
  * 
  */
    public void prestarCopiaDeLibro(long codigoCopia, long codigoUsuario) throws Exception{
        Optional<CopiaLibro> copiaOpcional = copiaLibrorepository.findById(codigoCopia);
        if (copiaOpcional.isEmpty())
            throw new Exception(message:"No existe una copia con ese codigo")

        CopiaLibro copia = copiaOpcional.get();
        if (copia.getDisponible() == false) {
            throw new Exception(message: "La copia no esta disponible")
            
        }

        Optional<Usuario> usrOpcional = UsuarioRepository.findById(codigoUsuario);
        if(usrOpcional.isEmpty()){
            throw new Exception(message: "No existe un usuario con ese codigo");
        }
    }

    Prestamo prestamo = new Prestamo();

    prestamo.setFechaPrestamo(Instant.now());
    prestamo.setFechaVencimiento(Instant.now());
    prestamo.setFechaDevolucion(fechaDevolucion:null);

    prestamo.setCopia(copia);
    prestamo.setUsuario(usr);

    prestamoRepository.save(prestamo);


}
