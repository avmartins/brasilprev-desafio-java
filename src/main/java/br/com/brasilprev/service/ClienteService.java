package  br.com.brasilprev.service;

import  br.com.brasilprev.entity.Cliente;

public interface ClienteService {

	Iterable<Cliente> findAll();
	
	Iterable<Cliente> findByName(String nome);
	
	Iterable<Cliente> findByCpf(String cpf);
	
	Cliente findById(Long id);

	Cliente save(Cliente cliente);

	void update(Cliente cliente);

	void updateForm(Cliente cliente);
	
	void delete(Cliente cliente);

}
