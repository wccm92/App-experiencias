package digitalHouse.integrador8.travels.layers.service.experiencia;

import digitalHouse.integrador8.travels.dto.ExperienciaDTO;
import digitalHouse.integrador8.travels.exception.NoExperienciasCategoria;
import digitalHouse.integrador8.travels.layers.repository.ExperienciaRepository;
import digitalHouse.integrador8.travels.layers.service.ListarExperienciasPorCategoriaService;
import digitalHouse.integrador8.travels.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarExperienciasPorCategoriaServiceImpl implements ListarExperienciasPorCategoriaService {
	
	private final ExperienciaRepository experienciaRepository;
	
	@Autowired
	public ListarExperienciasPorCategoriaServiceImpl(ExperienciaRepository experienciaRepository) {
		this.experienciaRepository = experienciaRepository;
	}

	@Override
	public List<ExperienciaDTO> listarExperienciasPorCategoria(Long id) {
		List<ExperienciaDTO> experienciaCategoria = experienciaRepository.findByCategoriaId(id).stream().map(Mapper::convertExperienciaEntityToDto).toList();
		if(experienciaCategoria.isEmpty()) {
			throw new NoExperienciasCategoria();
		}
		return experienciaCategoria;
	}
	
}
