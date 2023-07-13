package digitalHouse.integrador8.travels.layers.service.serviciosSecundarios;

import digitalHouse.integrador8.travels.entity.Destino;
import digitalHouse.integrador8.travels.layers.repository.DestinoRepository;
import digitalHouse.integrador8.travels.layers.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class CreadorDestinoServiceImpl implements TravelService<Destino> {

    @Autowired
    DestinoRepository destinoRepository;

    public Destino guardarDestino(String nombreDestino) {
        if (entidadPresente(nombreDestino)) {
            return destinoRepository.findByNombreDestino(nombreDestino).get();
        } else {
            Destino destino = new Destino(nombreDestino);
            return destinoRepository.save(destino);
        }
    }

    @Override
    public Destino desenvolvedorSeguro(Optional<Destino> entidad, Long entidadId) {
        return null;
    }

    @Override
    public boolean entidadPresente(Destino entidad) {
        return false;
    }

    public boolean entidadPresente(String nombreDestino) {
        return destinoRepository.findByNombreDestino(nombreDestino).isPresent();
    }
}
