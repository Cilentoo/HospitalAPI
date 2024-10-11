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

import br.com.serratec.entity.Medico;
import br.com.serratec.repository.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@GetMapping
	public List<Medico> list() {
		return repository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @Valid Medico inserir(@Valid @RequestBody Medico m) {
		return repository.save(m);
	}

	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Medico> inserirVarios(@Valid @RequestBody List<Medico> medicos) {
		return repository.saveAll(medicos);
	}

	@PutMapping("{codigo}")
	public ResponseEntity<Medico> alterarMedico(@PathVariable Long codigo, @Valid @RequestBody Medico m) {
		if (repository.existsById(codigo)) {
			m.setCodigo(codigo); 
			return ResponseEntity.ok(repository.save(m));
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
