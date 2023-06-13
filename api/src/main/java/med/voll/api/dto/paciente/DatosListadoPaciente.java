package med.voll.api.dto.paciente;

import med.voll.api.model.Paciente;

public record DatosListadoPaciente(String nombre, String email, String documentoIdentidad) {

    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}
