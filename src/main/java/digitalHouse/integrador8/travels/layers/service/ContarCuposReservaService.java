package digitalHouse.integrador8.travels.layers.service;

import digitalHouse.integrador8.travels.entity.Experiencia;

import java.time.LocalDate;

public interface ContarCuposReservaService {

    public Integer contarCuposUsados(Experiencia experiencia, LocalDate fechaReserva);
}
