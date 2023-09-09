package digitalHouse.integrador8.travels.layers.service.reserva;

import digitalHouse.integrador8.travels.dto.ReservaCreadaDTO;
import digitalHouse.integrador8.travels.dto.SolicitudReservaDTO;
import digitalHouse.integrador8.travels.entity.Experiencia;
import digitalHouse.integrador8.travels.entity.Reserva;
import digitalHouse.integrador8.travels.entity.Usuario;
import digitalHouse.integrador8.travels.exception.CuposNoDisponiblesException;
import digitalHouse.integrador8.travels.exception.LimiteCupoException;
import digitalHouse.integrador8.travels.layers.repository.ReservaRepository;
import digitalHouse.integrador8.travels.layers.service.ContarCuposReservaService;
import digitalHouse.integrador8.travels.layers.service.experiencia.DetalleExperienciaService;
import digitalHouse.integrador8.travels.layers.service.serviciosSecundarios.UsuarioServiceImpl;
import digitalHouse.integrador8.travels.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CrearReservaService {

    @Autowired
    UsuarioServiceImpl usuarioService;
    @Autowired
    DetalleExperienciaService experienciaService;
    @Autowired
    CuposDisponiblesReservaService cuposDisponiblesReservaService;
    @Autowired
    ReservaRepository reservaRepository;

    public ReservaCreadaDTO crearReserva(SolicitudReservaDTO solicitudReserva, Long idExperiencia) {

        Experiencia experiencia = experienciaService.obtenerExperienciaDB(idExperiencia);
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(solicitudReserva.getEmail());
        boolean cuposLlenos = cuposLlenos(solicitudReserva.getCantidadCupos(), experiencia, solicitudReserva.getFechaInicio());

        if (solicitudReserva.getCantidadCupos() > experiencia.getCupoMaximo()) {
            throw new LimiteCupoException(experiencia.getNombreExperiencia(), experiencia.getCupoMaximo());
        }
        if (cuposLlenos) {
            Integer cuposDisponibles = contarCuposDisponibles(experiencia, solicitudReserva.getFechaInicio());
            throw new CuposNoDisponiblesException(cuposDisponibles);
        }
        return asistirCreacionReserva(solicitudReserva, experiencia, usuario);
    }

    public boolean cuposLlenos(Integer cantidadCupos, Experiencia experiencia, LocalDate fechaNuevaReserva) {

        List<Reserva> reservasPrevias = experiencia.getReservas().stream()
                .filter(reserva -> reserva.getFechaInicio().equals(fechaNuevaReserva))
                .toList();
        if (reservasPrevias.isEmpty()) {
            return false;
        } else {
            Integer cuposUsados = cuposDisponiblesReservaService.contarCuposUsados(experiencia, fechaNuevaReserva);
            cuposUsados += cantidadCupos;
            return cuposUsados > experiencia.getCupoMaximo();
        }
    }

    public static boolean cuposLlenos(Experiencia experiencia, LocalDate fechaNuevaReserva) {

        List<Reserva> reservasPrevias = experiencia.getReservas().stream()
                .filter(reserva -> reserva.getFechaInicio().equals(fechaNuevaReserva))
                .toList();
        if (reservasPrevias.isEmpty()) {
            return false;
        } else {
            Integer cuposUsados = reservasPrevias.stream()
                    .map(Reserva::getCantidadCupos).toList()
                    .stream().
                    reduce(0, Integer::sum);
            return cuposUsados.equals(experiencia.getCupoMaximo());
        }
    }

    public Integer contarCuposDisponibles(Experiencia experiencia, LocalDate fechaNuevaReserva) {
        return cuposDisponiblesReservaService.obtenerCuposDisponibles(experiencia.getId(), fechaNuevaReserva);
    }

    public ReservaCreadaDTO asistirCreacionReserva(SolicitudReservaDTO solicitudReserva, Experiencia experienciaReserva, Usuario usuarioReserva) {

        try {
            Reserva reserva = Mapper.convertSolicitudReservaToReserva(solicitudReserva, experienciaReserva, usuarioReserva);
            reserva.setPrecioTotal(asignarPrecioReserva(solicitudReserva.getTipoPaquete(), experienciaReserva, solicitudReserva.getCantidadCupos()));
            reservaRepository.save(reserva);
            return Mapper.convertReservaToReservaCreadaDTO(reserva);
        } catch (RuntimeException ex) {
            throw new DataIntegrityViolationException("Hay uno o mas campos vacios en la solicitud");
        }
    }

    public Double asignarPrecioReserva(String tipoPaquete, Experiencia experiencia, Integer cantidadCupos) {

        Double precio;
        if (tipoPaquete.equals("basic")) {
            precio = cantidadCupos * experiencia.getPaqueteMap().get("basic").getPrecio();
        } else {
            precio = cantidadCupos * experiencia.getPaqueteMap().get("premium").getPrecio();
        }
        return precio;
    }
}
