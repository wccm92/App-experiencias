package digitalHouse.integrador8.travels.layers.controller;

import digitalHouse.integrador8.travels.layers.repository.CategoriaRepository;
import digitalHouse.integrador8.travels.layers.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categoria/eliminar")
public class EliminarCategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ExperienciaRepository experienciaRepository;

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long categoriaId) {
        categoriaRepository.deleteById(categoriaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
