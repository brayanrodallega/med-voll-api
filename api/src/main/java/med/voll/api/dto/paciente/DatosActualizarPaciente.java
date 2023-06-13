package med.voll.api.dto.paciente;

import jakarta.validation.Valid;
import med.voll.api.dto.direccion.DatosDireccion;

public record DatosActualizarPaciente(
        Long id,
        String nombre,
        String telefono,
        @Valid
        DatosDireccion direccion
) {
}
