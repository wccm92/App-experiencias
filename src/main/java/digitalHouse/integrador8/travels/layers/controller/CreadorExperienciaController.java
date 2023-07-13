package digitalHouse.integrador8.travels.layers.controller;

import digitalHouse.integrador8.travels.dto.SolicitudExperienciaDTO;
import digitalHouse.integrador8.travels.entity.Experiencia;
import digitalHouse.integrador8.travels.layers.service.experiencia.CrearExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/experiencia/guardar")
public class CreadorExperienciaController {

    @Autowired
    CrearExperienciaService guardarExperienciaService;

    @PostMapping("/v2")
    public ResponseEntity<Experiencia> guardarExperienciaV2(@RequestBody SolicitudExperienciaDTO experienciaDTO2) {
        return new ResponseEntity<>(guardarExperienciaService.guardarExperiencia(experienciaDTO2), HttpStatus.CREATED);
    }
}
