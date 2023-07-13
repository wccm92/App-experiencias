package digitalHouse.integrador8.travels.layers.service.categoria;

import digitalHouse.integrador8.travels.entity.Categoria;
import digitalHouse.integrador8.travels.layers.repository.CategoriaRepository;
import digitalHouse.integrador8.travels.layers.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService implements TravelService<Categoria> {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public Categoria desenvolvedorSeguro(Optional<Categoria> entidad, Long entidadId) {
        if(entidad.isPresent()) {
            return entidad.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean entidadPresente(Categoria entidad) {
        return categoriaRepository.findByTipoExperiencia(entidad.getTipoExperiencia()).isPresent();
    }
    
}
