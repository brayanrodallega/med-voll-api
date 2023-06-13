package med.voll.api.dto.medico;

import med.voll.api.dto.direccion.DatosDireccion;
import med.voll.api.model.Medico;

public record DatosRespuestaMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        DatosDireccion direccion
) {
    public DatosRespuestaMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion())
        );
    }
}
