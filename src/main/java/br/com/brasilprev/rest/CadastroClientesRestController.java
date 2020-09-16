package br.com.brasilprev.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.brasilprev.entity.Cliente;
import br.com.brasilprev.exception.ClienteNotFoundException;
import br.com.brasilprev.service.ClienteService;

@RestController
@RequestMapping("/CadastroClientes")
@CrossOrigin(origins = { "http://localhost:8080" })
public class CadastroClientesRestController {

	private final ClienteService ClienteService;

	public CadastroClientesRestController(ClienteService ClienteService) {
		this.ClienteService = ClienteService;
	}

	// Cadastra Cliente
	@PostMapping("/Cadastro")
	public ResponseEntity<Object> cadastro(@RequestBody Cliente Cliente) {
		Cliente savedCliente = ClienteService.save(Cliente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCliente.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	// Altera Cliente
	@PutMapping("/{id}")
	public ResponseEntity<Object> alteracao(@RequestBody Cliente Cliente, @PathVariable long id) {

		Cliente ClienteOptional = ClienteService.findById(id);

		if (ClienteOptional == null)
			return ResponseEntity.notFound().build();

		Cliente.setId(id);

		ClienteService.save(Cliente);

		return ResponseEntity.noContent().build();
	}

	// Remove Cliente
	@DeleteMapping("/{id}")
	public void remocao(@PathVariable long id) {
		Cliente Cliente = ClienteService.findById(id);
		ClienteService.delete(Cliente);
	}

	// Consulta todas as Clientes
	@GetMapping("/ConsultaTodos")
	public Iterable<Cliente> consultaAll() {
		return ClienteService.findAll();
	}

	// Consulta Cliente por nome
	@GetMapping("/ConsultaNome/{nome}")
	public Iterable<Cliente> consultaNome(@PathVariable String nome) throws ClienteNotFoundException {

		Iterable<Cliente> lista = null;

		if (nome != null && !nome.equals("")) {
			lista = ClienteService.findByName(nome);
		}

		return lista;
	}

	// Consulta Cliente por cpf
	@GetMapping("/ConsultaCpf/{cpf}")
	public Iterable<Cliente> consultaCpf(@PathVariable String cpf)
			throws ClienteNotFoundException {

		Iterable<Cliente> lista = null;

		if (cpf != null && !cpf.equals("")) {
			lista = ClienteService.findByCpf(cpf);
		}

		return lista;
	}

	@GetMapping("/ConsultaId/{id}")
	public Cliente consultaId(@PathVariable long id) throws ClienteNotFoundException {

		Cliente Cliente = ClienteService.findById(id);

		return Cliente;
	}
	
	// Consulta Clientes
	@GetMapping("/Consulta/{valor}/{campo}")
	public Iterable<Cliente> consultaCpf(@PathVariable String valor, @PathVariable String campo)
			throws ClienteNotFoundException {

		Iterable<Cliente> lista = null;
		
		if ( campo.equals("ID") ) {
			Cliente Cliente = ClienteService.findById(new Long(valor));
			List<Cliente> persons = new ArrayList<Cliente>();    
			persons.add(Cliente);
			lista = persons; 
		} else if ( campo.equals("NOME") ) {
			lista = ClienteService.findByName(valor.toUpperCase());
		} else if ( campo.equals("CPF") ) {
			lista = ClienteService.findByCpf(valor);
		} else {
			lista = ClienteService.findAll();
		}
		
		return lista;
	}

}
