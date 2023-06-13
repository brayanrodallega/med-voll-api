package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.paciente.DatosActualizarPaciente;
import med.voll.api.dto.paciente.DatosListadoPaciente;
import med.voll.api.dto.paciente.DatosRegistroPaciente;
import med.voll.api.dto.paciente.DatosRespuestaPaciente;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                                    UriComponentsBuilder uriComponentsBuilder) {
        Paciente paciente = pacienteRepository.save(new Paciente(datosRegistroPaciente));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente);
        return ResponseEntity.created(
                uriComponentsBuilder.path("/pacientes/{id}")
                        .buildAndExpand(datosRespuestaPaciente.id())
                        .toUri()
        ).body(datosRespuestaPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPaciente>> listar(@PageableDefault(size = 2) Pageable pageable) {
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(pageable).map(DatosListadoPaciente::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> retornarDatosPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosRegistroPaciente) {
        Paciente paciente = pacienteRepository.getReferenceById(datosRegistroPaciente.id());
        paciente.actualizarInformacion(datosRegistroPaciente);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inactivar();
        return ResponseEntity.noContent().build();
    }
}
