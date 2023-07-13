package digitalHouse.integrador8.travels.layers.service.experiencia;

import digitalHouse.integrador8.travels.dto.SolicitudExperienciaDTO;
import digitalHouse.integrador8.travels.entity.Experiencia;
import digitalHouse.integrador8.travels.exception.EntidadExistenteException;
import digitalHouse.integrador8.travels.layers.repository.ExperienciaRepository;
import digitalHouse.integrador8.travels.layers.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrearExperienciaService implements TravelService<Experiencia> {

    @Autowired
    ExperienciaRepository experienciaRepository;
    @Autowired
    AuxiliarExperienciaService auxiliarExperienciaService;

    public Experiencia guardarExperiencia(SolicitudExperienciaDTO experienciaDTO) {
        if (entidadPresente(experienciaDTO.getNombreExperiencia())) {
            throw new EntidadExistenteException(experienciaDTO.getNombreExperiencia());
        } else {
            return auxiliarExperienciaService.guardarExperiencia(experienciaDTO);
        }
    }

    @Override
    public Experiencia desenvolvedorSeguro(Optional<Experiencia> entidad, Long entidadId) {
        return null;
    }

    @Override
    public boolean entidadPresente(Experiencia entidad) {
        return false;
    }

    public boolean entidadPresente(String nombreExperiencia) {
        return experienciaRepository.findByNombreExperiencia(nombreExperiencia).isPresent();
    }
}
