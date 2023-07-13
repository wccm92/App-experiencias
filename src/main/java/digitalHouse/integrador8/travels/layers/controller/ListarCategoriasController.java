package digitalHouse.integrador8.travels.layers.controller;

import digitalHouse.integrador8.travels.dto.CategoriaDTO;
import digitalHouse.integrador8.travels.layers.service.categoria.ListarCategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/categoria")
public class ListarCategoriasController {
	
    private final ListarCategoriaServiceImpl listarCategoria;

    @Autowired
    public ListarCategoriasController(ListarCategoriaServiceImpl listarCategoria) {
        this.listarCategoria = listarCategoria;
    }

    @GetMapping(value = "/listar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        return ResponseEntity.ok(listarCategoria.listarCategorias());
    }
}
