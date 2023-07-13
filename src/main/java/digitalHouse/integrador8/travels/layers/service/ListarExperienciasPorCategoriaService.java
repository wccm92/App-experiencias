package digitalHouse.integrador8.travels.layers.service;

import digitalHouse.integrador8.travels.dto.ExperienciaDTO;

import java.util.List;

public interface ListarExperienciasPorCategoriaService {
	List<ExperienciaDTO> listarExperienciasPorCategoria(Long id);
}
