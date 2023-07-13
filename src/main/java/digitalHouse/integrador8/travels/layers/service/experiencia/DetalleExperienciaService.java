package digitalHouse.integrador8.travels.layers.service.experiencia;

import digitalHouse.integrador8.travels.dto.ExperienciaDetalleDTO;
import digitalHouse.integrador8.travels.entity.Experiencia;
import digitalHouse.integrador8.travels.exception.ExperienciaNoExistenteException;
import digitalHouse.integrador8.travels.layers.repository.ExperienciaRepository;
import digitalHouse.integrador8.travels.layers.service.TravelService;
import digitalHouse.integrador8.travels.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetalleExperienciaService implements TravelService<Experiencia> {
	
	private final ExperienciaRepository experienciaRepository;
	
    @Autowired
    public DetalleExperienciaService(ExperienciaRepository experienciaRepository) {
        this.experienciaRepository = experienciaRepository;
    }
    
    public ExperienciaDetalleDTO obtenerDetalleExperiencia(Long id) {
        Optional<Experiencia> experienciaOptional = experienciaRepository.findById(id);
        return Mapper.convertExperienciaDetalleEntityToDto(desenvolvedorSeguro(experienciaOptional, id));
    }

    public Experiencia obtenerExperienciaDB(Long id) {
        Optional<Experiencia> experienciaOptional = experienciaRepository.findById(id);
        return desenvolvedorSeguro(experienciaOptional, id);
    }

    @Override
    public Experiencia desenvolvedorSeguro(Optional<Experiencia> experienciaOptional, Long entidadId) {
        if (experienciaOptional.isPresent()) {
            return experienciaOptional.get();
        } else {
            throw new ExperienciaNoExistenteException(entidadId);
        }
    }

    @Override
    public boolean entidadPresente(Experiencia entidad) {
        return false;
    }
}
