package dev.bertin.CodersClub_Spring_Swing.servicio;

import dev.bertin.CodersClub_Spring_Swing.modelo.Socio;
import dev.bertin.CodersClub_Spring_Swing.repositorio.SocioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioServicio implements ISocioServicio{

    @Autowired
    private SocioRepositorio socioRepositorio;

    @Override
    public List<Socio> listarSocios() {
        List<Socio> socios = socioRepositorio.findAll();
        return socios;
    }

    @Override
    public Socio buscarSocioPorId(Integer idSocio) {
        Socio socio = socioRepositorio.findById(idSocio).orElse(null);
        return socio;
    }

    @Override
    public void guardarSocio(Socio socio) {
        //Aqui es donde si el id=null se hace insert, sino se hace update
        socioRepositorio.save(socio);
    }

    @Override
    public void eliminarSocio(Socio socio) {
        socioRepositorio.delete(socio);

    }
}