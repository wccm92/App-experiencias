package digitalHouse.integrador8.travels.layers.service.categoria;

import digitalHouse.integrador8.travels.dto.CategoriaDTO;
import digitalHouse.integrador8.travels.entity.Categoria;
import digitalHouse.integrador8.travels.entity.ImagenCategoria;
import digitalHouse.integrador8.travels.exception.CategoriaNoValidaException;
import digitalHouse.integrador8.travels.exception.EntidadExistenteException;
import digitalHouse.integrador8.travels.layers.repository.CategoriaRepository;
import digitalHouse.integrador8.travels.layers.repository.ImagenCategoriaRepository;
import digitalHouse.integrador8.travels.layers.service.TravelService;
import digitalHouse.integrador8.travels.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrearCategoriaService implements TravelService<Categoria> {

    CategoriaRepository categoriaRepository;
    ImagenCategoriaRepository imagenCategoriaRepository;
    
    @Autowired
    public CrearCategoriaService(CategoriaRepository categoriaRepository, ImagenCategoriaRepository imagenCategoriaRepository) {
    	this.categoriaRepository = categoriaRepository;
        this.imagenCategoriaRepository = imagenCategoriaRepository;
    }

    public Categoria guardarCategoria(CategoriaDTO categoriaDTO) {
        if (entidadPresente(Mapper.convertCategoriaDtoToEntity(categoriaDTO).getTipoExperiencia())) {
        	 throw new EntidadExistenteException(categoriaDTO.getTipoExperiencia());
        } else {
            Categoria categoria = categoriaRepository.save(Mapper.convertCategoriaDtoToEntity(categoriaDTO));
            ImagenCategoria imagenCategoria = new ImagenCategoria(categoriaDTO.getUrlImagen(), categoria);
            imagenCategoriaRepository.save(imagenCategoria);
            categoria.setImagenCategoria(imagenCategoria);
            return categoriaRepository.save(categoria);
        }
    }

    public Categoria obtenerCategoria(String nombreCategoria) {
        if (!entidadPresente(nombreCategoria)) {
            throw new CategoriaNoValidaException(nombreCategoria);
        } else {
            return categoriaRepository.findByTipoExperiencia(nombreCategoria).get();
        }
    }

    @Override
    public Categoria desenvolvedorSeguro(Optional<Categoria> entidad, Long entidadId) {
        return null;
    }

    @Override
    public boolean entidadPresente(Categoria entidad) {
        return false;
    }

    public boolean entidadPresente(String nombreCategoria) {
        return categoriaRepository.findByTipoExperiencia(nombreCategoria).isPresent();
    }
}
