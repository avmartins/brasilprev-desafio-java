package br.com.brasilprev.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brasilprev.entity.Cliente;
import br.com.brasilprev.repository.ClienteRepository;
import br.com.brasilprev.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository ClienteRepository;

	@Autowired
	public ClienteServiceImpl(ClienteRepository ClienteRepository) {
		this.ClienteRepository = ClienteRepository;
	}

	@Override
	public Iterable<Cliente> findAll() {
		return ClienteRepository.findAll();
	}

	@Override
	public Cliente save(Cliente cliente) {
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if (cliente.getId() == null) {
			cliente.setDataCadastro(localDateTime);
		} else {
			cliente.setDataAtualização(localDateTime);
		}
		
		return ClienteRepository.save(cliente);
	}

	@Override
	public void update(Cliente cliente) {
		ClienteRepository.save(cliente);
	}

	@Override
	public void updateForm(Cliente cliente) {
		Cliente c = ClienteRepository.findById(cliente.getId()).orElse(null);
		
		c.setNome(cliente.getNome());
		c.setCpf(cliente.getCpf());
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if (cliente.getId() == null) {
			c.setDataCadastro(localDateTime);
		} else {
			c.setDataAtualização(localDateTime);
		}
		
		
		c.setEndereco(cliente.getEndereco());
		
		ClienteRepository.save(c);
	}

	@Override
	public void delete(Cliente cliente) {
		Cliente e = ClienteRepository.findById(cliente.getId()).orElse(null);

		ClienteRepository.delete(e);
	}

	@Override
	public Cliente findById(Long id) {
		return ClienteRepository.findById(id).orElse(null);
	}
	
	@Override
	public Iterable<Cliente> findByName(String nome) {
		return ClienteRepository.findByName(nome);
	}
	
	@Override
	public Iterable<Cliente> findByCpf(String cpf) {
		return ClienteRepository.findByCpf(cpf);
	}

}
