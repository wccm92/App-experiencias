package digitalHouse.integrador8.travels.layers.controller;

import digitalHouse.integrador8.travels.layers.service.EliminarExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/experiencia")
public class EliminarExperienciaController {
    @Autowired
    EliminarExperienciaService experienciaService;

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteExperiencia(@PathVariable Long id){
        experienciaService.eliminar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
