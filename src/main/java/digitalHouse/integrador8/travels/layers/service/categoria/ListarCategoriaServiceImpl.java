package digitalHouse.integrador8.travels.layers.service.categoria;

import digitalHouse.integrador8.travels.dto.CategoriaDTO;
import digitalHouse.integrador8.travels.layers.repository.CategoriaRepository;
import digitalHouse.integrador8.travels.layers.service.ListarCategoriasService;
import digitalHouse.integrador8.travels.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCategoriaServiceImpl implements ListarCategoriasService {
	
	private final CategoriaRepository categoriaRepository;
	
	@Autowired
	public ListarCategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public List<CategoriaDTO> listarCategorias() { 
		return categoriaRepository.findAll().stream().map(Mapper::convertCategoriaToDto).toList();
	}
	
}
