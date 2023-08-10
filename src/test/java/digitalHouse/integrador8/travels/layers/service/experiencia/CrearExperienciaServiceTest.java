package digitalHouse.integrador8.travels.layers.service.experiencia;

import digitalHouse.integrador8.travels.dto.SolicitudExperienciaDTO;
import digitalHouse.integrador8.travels.entity.Categoria;
import digitalHouse.integrador8.travels.entity.Destino;
import digitalHouse.integrador8.travels.entity.EtiquetaCategoria;
import digitalHouse.integrador8.travels.entity.Experiencia;
import digitalHouse.integrador8.travels.exception.EntidadExistenteException;
import digitalHouse.integrador8.travels.layers.repository.ExperienciaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class CrearExperienciaServiceTest {

    @Mock
    ExperienciaRepository mockedExperienciaRepository;
    @Mock
    AuxiliarExperienciaService mockedAuxiliarExperienciaService;
    @InjectMocks
    CrearExperienciaService crearExperienciaService;

    // Preparando DTO solicitud experiencia
    SolicitudExperienciaDTO solicitudExperienciaDTO = SolicitudExperienciaDTO.builder()
            .nombreExperiencia("experiencia1")
            .categoria("moda")
            .cupoMaximo(5)
            .descripcion("prueba")
            .destino("Cartagena")
            .duracionDias(4)
            .precioBasico(300.0)
            .precioPremium(450.0)
            .urlImagenes(Arrays.asList("a", "b"))
            .build();

    // Preparando entidad experiencia

    EtiquetaCategoria etiquetaCategoria = EtiquetaCategoria.builder()
            .id(1L)
            .urlImagen("url1")
            .categoria(new Categoria())
            .build();

    Categoria categoria = Categoria.builder()
            .id(1L)
            .descripcion("hola")
            .etiquetaCategoria(etiquetaCategoria)
            .tipoExperiencia("moda")
            .build();

    Destino destino = Destino.builder()
            .nombreDestino("Cancun")
            .experiencias(new ArrayList<>())
            .build();

    Experiencia experienciaGuardada = Experiencia.builder()
            .id(1L)
            .nombreExperiencia("experiencia1")
            .calificacion(5.0)
            .cantidadCalificaciones(0)
            .categoria(categoria)
            .cupoMaximo(20)
            .descripcion("prueba experiencia")
            .destino(destino)
            .duracionDias(4)
            .build();

    @BeforeEach
    void setUp() {
        etiquetaCategoria.setCategoria(categoria);
    }

    @Test
    void guardarExperienciaNueva() {
        Mockito.when(mockedExperienciaRepository.findByNombreExperiencia(solicitudExperienciaDTO.getNombreExperiencia()))
                .thenReturn(Optional.empty());
        Mockito.when(mockedAuxiliarExperienciaService.guardarExperiencia(solicitudExperienciaDTO))
                .thenReturn(experienciaGuardada);

        Experiencia result = crearExperienciaService.guardarExperiencia(solicitudExperienciaDTO);
        Assert.assertSame(result, experienciaGuardada);
    }

    @Test
    void guardarExperienciaRepetida() {
        Mockito.when(mockedExperienciaRepository.findByNombreExperiencia(solicitudExperienciaDTO.getNombreExperiencia()))
                .thenReturn(Optional.ofNullable(experienciaGuardada));

        Assert.assertThrows(EntidadExistenteException.class, () -> crearExperienciaService.guardarExperiencia(solicitudExperienciaDTO));
    }
}