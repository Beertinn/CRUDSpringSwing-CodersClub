package dev.bertin.CodersClub_Spring_Swing.servicio;

import dev.bertin.CodersClub_Spring_Swing.modelo.Socio;

import java.util.List;

public interface ISocioServicio {
    public List<Socio> listarSocios();
    public Socio buscarSocioPorId(Integer idSocio);
    public void guardarSocio(Socio socio);
    public void eliminarSocio(Socio socio);

}
