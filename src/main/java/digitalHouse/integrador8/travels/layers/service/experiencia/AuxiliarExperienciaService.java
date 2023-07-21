package digitalHouse.integrador8.travels.layers.service.experiencia;

import digitalHouse.integrador8.travels.dto.SolicitudExperienciaDTO;
import digitalHouse.integrador8.travels.entity.Experiencia;
import digitalHouse.integrador8.travels.entity.Paquete;
import digitalHouse.integrador8.travels.layers.repository.ExperienciaRepository;
import digitalHouse.integrador8.travels.layers.service.categoria.CrearCategoriaService;
import digitalHouse.integrador8.travels.layers.service.serviciosSecundarios.CreadorDestinoServiceImpl;
import digitalHouse.integrador8.travels.layers.service.serviciosSecundarios.CreadorPaqueteServiceImpl;
import digitalHouse.integrador8.travels.layers.service.serviciosSecundarios.ImagenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuxiliarExperienciaService {

    @Autowired
    CreadorDestinoServiceImpl guardarDestinoService;
    @Autowired
    CrearCategoriaService guardarCategoriaService;
    @Autowired
    ExperienciaRepository experienciaRepository;
    @Autowired
    CreadorPaqueteServiceImpl guardarPaqueteService;
    @Autowired
    ImagenServiceImpl imagenService;

    public Experiencia guardarExperiencia(SolicitudExperienciaDTO experienciaDTO) {
        try {
            Experiencia experiencia = Experiencia.builder()
                    .nombreExperiencia(experienciaDTO.getNombreExperiencia())
                    .descripcion(experienciaDTO.getDescripcion())
                    .categoria(guardarCategoriaService.obtenerCategoria(experienciaDTO.getCategoria()))
                    .destino(guardarDestinoService.guardarDestino(experienciaDTO.getDestino()))
                    .cantidadCalificaciones(0)
                    .calificacion(0.0)
                    .paqueteMap(new HashMap<>())
                    .cupoMaximo(experienciaDTO.getCupoMaximo())
                    .duracionDias(experienciaDTO.getDuracionDias())
                    .build();

            Paquete paquetePremium = guardarPaqueteService.guardarPaquete("premium", experienciaDTO.getPrecioPremium(), 1L);
            Paquete paqueteBasic = guardarPaqueteService.guardarPaquete("basic", experienciaDTO.getPrecioBasico(), 2L);
            experiencia.getPaqueteMap().put("premium", paquetePremium);
            experiencia.getPaqueteMap().put("basic", paqueteBasic);
            experienciaRepository.save(experiencia);
            experiencia.setImagenes(imagenService.guardarImagenes(experienciaDTO.getUrlImagenes(), experiencia));

            return experienciaRepository.save(experiencia);
        } catch (RuntimeException ex) {
                throw new DataIntegrityViolationException("Uno de los campos está vacio");
        }
    }
}
