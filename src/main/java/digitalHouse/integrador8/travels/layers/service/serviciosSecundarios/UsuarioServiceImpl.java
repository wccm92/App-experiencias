package digitalHouse.integrador8.travels.layers.service.serviciosSecundarios;

import digitalHouse.integrador8.travels.entity.Usuario;
import digitalHouse.integrador8.travels.exception.UsuarioNoExistenteException;
import digitalHouse.integrador8.travels.layers.repository.UsuarioRepository;
import digitalHouse.integrador8.travels.layers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            throw new UsuarioNoExistenteException(email);
        } else {
            return usuario.get();
        }
    }
}



