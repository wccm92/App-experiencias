package digitalHouse.integrador8.travels.layers.controller;

import digitalHouse.integrador8.travels.dto.ExperienciaDetalleDTO;
import digitalHouse.integrador8.travels.layers.service.experiencia.DetalleExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/experiencia")
public class DetalleExperienciaController {

    @Autowired
    DetalleExperienciaService detalleExperienciaService;

    @GetMapping(value = "/detalle/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExperienciaDetalleDTO> obtenerDetalleExperiencia(@PathVariable Long id) {
    	return new ResponseEntity<>(detalleExperienciaService.obtenerDetalleExperiencia(id), HttpStatus.OK);
    }
}
