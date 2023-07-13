package digitalHouse.integrador8.travels.layers.service;

import java.util.Optional;

public interface TravelService<T> {

    T desenvolvedorSeguro(Optional<T> entidad, Long entidadId);
    
    public boolean entidadPresente(T entidad);

}
