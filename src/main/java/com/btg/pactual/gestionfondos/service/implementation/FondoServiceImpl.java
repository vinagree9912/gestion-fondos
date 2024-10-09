package com.btg.pactual.gestionfondos.service.implementation;

import com.btg.pactual.gestionfondos.dto.FondoDTO;
import com.btg.pactual.gestionfondos.exception.BusinessException;
import jakarta.validation.Validator;
import com.btg.pactual.gestionfondos.model.Fondo;
import com.btg.pactual.gestionfondos.repository.ClienteRepository;
import com.btg.pactual.gestionfondos.repository.FondoRepository;
import com.btg.pactual.gestionfondos.repository.SuscripcionRepository;
import com.btg.pactual.gestionfondos.service.FondoService;
import com.btg.pactual.gestionfondos.service.SuscripcionService;

import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.List;
import java.util.Set;

import static com.btg.pactual.gestionfondos.utils.Utils.generateId;

@Slf4j
@Service
@AllArgsConstructor
public class FondoServiceImpl implements FondoService {

    private final SuscripcionRepository suscripcionRepository;
    private final SuscripcionService suscripcionService;
    private final ClienteRepository clienteRepository;
    private final FondoRepository fondoRepository;
    private final Validator validator;
    @Override
    public void crearFondos(List<FondoDTO> fondos) {
        for (FondoDTO fondoRequest : fondos) {
            try {
                // Validar el DTO del fondo
                validarFondoDTO(fondoRequest);

                // Convertir el DTO a un objeto de tipo Fondo
                Fondo nuevoFondo = convertirADto(fondoRequest);

                // Guardar el nuevo fondo en la base de datos
                fondoRepository.save(nuevoFondo);

                log.info("Fondo creado: {}", nuevoFondo);
            } catch (BusinessException e) {
                log.error("Error al crear el fondo: {}", e.getMessage());
                // Manejo de excepción según la lógica de negocio
            } catch (Exception e) {
                log.error("Error inesperado al crear el fondo: {}", e.getMessage());
                throw new BusinessException("Error al crear el fondo: " + e.getMessage());
            }
        }
    }
    // Método para validar el DTO del fondo
    private void validarFondoDTO(FondoDTO fondoRequest) {
        Set<ConstraintViolation<FondoDTO>> violations = validator.validate(fondoRequest);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<FondoDTO> violation : violations) {
                sb.append(violation.getMessage()).append(" ");
            }
            throw new BusinessException(sb.toString().trim());
        }
    }

    // Método para convertir un DTO a un objeto Fondo
    private Fondo convertirADto(FondoDTO fondoRequest) {
        return new Fondo(
                generateId(), // Método para generar un ID único
                fondoRequest.getNombre(),
                fondoRequest.getMontoMinimo(),
                fondoRequest.getCategoria(),
                BigDecimal.ZERO // O cualquier valor predeterminado que desees para montoTotal
        );
    }
}
