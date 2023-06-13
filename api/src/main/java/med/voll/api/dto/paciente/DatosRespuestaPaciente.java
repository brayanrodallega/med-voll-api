package med.voll.api.dto.paciente;

import med.voll.api.model.Direccion;
import med.voll.api.model.Paciente;

public record DatosRespuestaPaciente(Long id, String nombre, String email, String telefono, String documentoIdentidad, Direccion direccion) {

    public DatosRespuestaPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumentoIdentidad(),
                paciente.getDireccion()
        );
    }
}
