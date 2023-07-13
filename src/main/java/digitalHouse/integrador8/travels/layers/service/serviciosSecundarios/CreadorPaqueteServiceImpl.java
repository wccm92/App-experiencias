package digitalHouse.integrador8.travels.layers.service.serviciosSecundarios;

import digitalHouse.integrador8.travels.entity.Paquete;
import digitalHouse.integrador8.travels.layers.repository.PaqueteRepository;
import digitalHouse.integrador8.travels.layers.repository.ServiciosRepository;
import digitalHouse.integrador8.travels.layers.service.TravelService;
import digitalHouse.integrador8.travels.utils.TravelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreadorPaqueteServiceImpl implements TravelService<Paquete> {

    @Autowired
    PaqueteRepository paqueteRepository;
    @Autowired
    ServiciosRepository serviciosRepository;


    public Paquete guardarPaquete(String nombrePaquete, Double precio, Long idServicio) {
        return paqueteRepository.save(TravelFactory.getInstance().creadorPaquetes(nombrePaquete, precio, serviciosRepository.findById(idServicio).get()));
    }

    @Override
    public Paquete desenvolvedorSeguro(Optional<Paquete> entidad, Long entidadId) {
        return null;
    }

    @Override
    public boolean entidadPresente(Paquete entidad) {
        return false;
    }
}
