package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Paciente;
import br.com.serratec.repository.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@GetMapping
	public List<Paciente> list() {
		return repository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @Valid Paciente inserir(@Valid @RequestBody Paciente p) {
		return repository.save(p);
	}

	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Paciente> inserirVarios(@Valid @RequestBody List<Paciente> pacientes) {
		return repository.saveAll(pacientes);
	}

	@PutMapping("{codigo}")
	public ResponseEntity<Paciente> alterarPaciente(@PathVariable Long codigo, @Valid @RequestBody Paciente p) {
		if (repository.existsById(codigo)) {
			p.setCodigo(codigo); 
			return ResponseEntity.ok(repository.save(p));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		if (repository.existsById(codigo)) {
			repository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
