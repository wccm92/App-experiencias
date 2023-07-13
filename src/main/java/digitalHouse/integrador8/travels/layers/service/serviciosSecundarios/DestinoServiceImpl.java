package digitalHouse.integrador8.travels.layers.service.serviciosSecundarios;

import digitalHouse.integrador8.travels.entity.Destino;
import digitalHouse.integrador8.travels.layers.repository.DestinoRepository;
import digitalHouse.integrador8.travels.layers.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class DestinoServiceImpl implements TravelService<Destino> {

    @Autowired
    DestinoRepository destinoRepository;

    public Destino guardarEntidad(Destino entidad) {
        if (entidadPresente(entidad)) {
            throw new RuntimeException();
        } else {
            return destinoRepository.save(entidad);
        }
    }

    public Destino obtenerEntidad(Long entidadId) {
        Optional<Destino> destinoOptional = destinoRepository.findById(entidadId);
        return desenvolvedorSeguro(destinoOptional, entidadId);
    }

    @Override
    public Destino desenvolvedorSeguro(Optional<Destino> entidad, Long entidadId) {
        if(entidad.isPresent()) {
            return entidad.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean entidadPresente(Destino entidad) {
        return destinoRepository.findByNombreDestino(entidad.getNombreDestino()).isPresent();
    }
}
