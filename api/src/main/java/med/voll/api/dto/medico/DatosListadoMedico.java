package med.voll.api.dto.medico;

import med.voll.api.model.Medico;

public record DatosListadoMedico(String nombre, String especialidad, String documento, String email) {

    public DatosListadoMedico(Medico medico) {
        this(medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
