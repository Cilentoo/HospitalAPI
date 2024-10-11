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

import br.com.serratec.entity.Consulta;
import br.com.serratec.repository.ConsultaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaRepository repository;

	@GetMapping
	public List<Consulta> list() {
		return repository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @Valid Consulta inserir(@Valid @RequestBody Consulta c) {
		return repository.save(c);
	}

	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Consulta> inserirVarios(@Valid @RequestBody List<Consulta> consultas) {
		return repository.saveAll(consultas);
	}

	@PutMapping("{codigo}")
	public ResponseEntity<Consulta> alterarConsulta(@PathVariable Long codigo, @Valid @RequestBody Consulta c) {
		if (repository.existsById(codigo)) {
			c.setCodigo(codigo); 
			return ResponseEntity.ok(repository.save(c));
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
