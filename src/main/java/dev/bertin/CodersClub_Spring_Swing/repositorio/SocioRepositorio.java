package dev.bertin.CodersClub_Spring_Swing.repositorio;

import dev.bertin.CodersClub_Spring_Swing.modelo.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepositorio extends JpaRepository<Socio,Integer> {
}
