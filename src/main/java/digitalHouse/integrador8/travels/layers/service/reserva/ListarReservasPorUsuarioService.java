package digitalHouse.integrador8.travels.layers.service.reserva;

import digitalHouse.integrador8.travels.dto.ReservaCreadaDTO;
import digitalHouse.integrador8.travels.layers.repository.ReservaRepository;
import digitalHouse.integrador8.travels.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarReservasPorUsuarioService {

    @Autowired
    ReservaRepository reservaRepository;

    public List<ReservaCreadaDTO> consultarReservasPorUsuario(Long idUsuario) {
        return reservaRepository.findByUsuarioId(idUsuario).stream()
                .map(reserva -> Mapper.convertReservaToReservaCreadaDTO(reserva))
                .toList();
    }
}
