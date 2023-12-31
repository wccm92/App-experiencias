package digitalHouse.integrador8.travels.layers.service.reserva;

import digitalHouse.integrador8.travels.dto.SolicitudCupoFechaDTO;
import digitalHouse.integrador8.travels.entity.Experiencia;
import digitalHouse.integrador8.travels.entity.Reserva;
import digitalHouse.integrador8.travels.layers.repository.ReservaRepository;
import digitalHouse.integrador8.travels.layers.service.ContarCuposReservaService;
import digitalHouse.integrador8.travels.layers.service.experiencia.DetalleExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CuposDisponiblesReservaService implements ContarCuposReservaService {

    @Autowired
    DetalleExperienciaService experienciaService;


    public Integer obtenerCuposDisponibles(Long idExperiencia, SolicitudCupoFechaDTO fechaReserva) {
        return obtenerCuposDisponibles(idExperiencia, fechaReserva.getFecha());
    }

    public Integer obtenerCuposDisponibles(Long idExperiencia, LocalDate fechaReserva) {
        Experiencia experiencia = experienciaService.obtenerExperienciaDB(idExperiencia);
        return experiencia.getCupoMaximo() - contarCuposUsados(experiencia, fechaReserva);
    }

    @Override
    public Integer contarCuposUsados(Experiencia experiencia, LocalDate fechaReserva) {
        return experiencia.getReservas()
                .stream()
                .filter(reserva -> reserva.getFechaInicio().equals(fechaReserva)).toList()
                .stream()
                .map(Reserva::getCantidadCupos).toList()
                .stream()
                .reduce(0, Integer::sum);
    }
}
