package br.com.brasilprev.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.brasilprev.Application;
import br.com.brasilprev.entity.Cliente;

/**
 * @author Anderson Virginio Martins
 * 
 * Claase responsável por testar a CadastroClientesRestController 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles(profiles = "test")
public class CadastroClientesRestControllerTest {
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testConsultaNome() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Cliente>> response = restTemplate.exchange("http://localhost:8080/CadastroClientes/ConsultaNome/cliente 1",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Cliente>>() {
				});

		Iterable<Cliente> listaclientees = response.getBody();
		
		System.out.println("####################### testConsultaNome #######################");

		for (Cliente cliente : listaclientees) {
			System.out.println("Nome : "+cliente.getNome());
		}

		assertNotNull(response.getBody());
	}
	
	@Test
	public void testConsultaCpf() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Cliente>> response = restTemplate.exchange("http://localhost:8080/CadastroClientes/ConsultaCpf/77777777777",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Cliente>>() {
				});

		Iterable<Cliente> listaclientees = response.getBody();
		
		System.out.println("\n####################### testConsultaCpf #######################");

		for (Cliente cliente : listaclientees) {
			System.out.println("Nome : "+cliente.getNome());
		}

		assertNotNull(response.getBody());
	}

	@Test
	public void testCadastro() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		Cliente cliente = new Cliente();
		cliente.setCpf("12345678901");
		cliente.setNome("cliente 5 teste");
		cliente.setEndereco("M");
		
		HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);

		final String baseUrl = "http://localhost:8080/CadastroClientes/Cadastro";

		try {
			URI uri = new URI(baseUrl);
			restTemplate.postForEntity(uri, request, Cliente.class);
			assertNotNull(request.getBody());
			System.out.println("####################### testCadastro #######################");
			System.out.println("cliente Criado com sucesso!");
		} catch (HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Erro não existe request ou header"));
		} catch (Exception ex) {
			assertEquals(500, ex.getMessage());
		}
	}
	
	@Test
	public void testAlteracao() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
				
		Cliente cliente = buscacliente("2");
		cliente.setCpf("07225612700");
		cliente.setNome("cliente 1-2");
		cliente.setEndereco("Endereço 2");
		
		HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);

		final String baseUrl = "http://localhost:8080/CadastroClientes/2";

		try {
			URI uri = new URI(baseUrl);
			restTemplate.exchange(uri, HttpMethod.PUT, request,Cliente.class);
			assertNotNull(request.getBody());
			System.out.println("####################### testAlteracao #######################");
			System.out.println("Atualização cliente com sucesso!");
		} catch (HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Erro não existe request ou header"));
		} catch (Exception ex) {
			assertEquals(500, ex.getMessage());
		}
	}
	
	@Test
	public void testRemocao() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
				
		Cliente cliente = buscacliente("2");
				
		HttpEntity<Cliente> request = new HttpEntity<>(cliente, headers);

		final String baseUrl = "http://localhost:8080/CadastroClientes/2";

		try {
			URI uri = new URI(baseUrl);
			restTemplate.exchange(uri, HttpMethod.DELETE, request,Cliente.class);
			assertNotNull(request.getBody());
			System.out.println("####################### testRemocao #######################");
			System.out.println("cliente deletado com sucesso!\n");
		} catch (HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Erro não existe request ou header"));
		} catch (Exception ex) {
			assertEquals(500, ex.getMessage());
		}
	}

	@Test
	public void testConsultaTodos() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Cliente>> response = restTemplate.exchange("http://localhost:8080/CadastroClientes/ConsultaTodos",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Cliente>>() {
				});

		Iterable<Cliente> listaclientees = response.getBody();
		
		System.out.println("####################### testConsultaTodos #######################");

		for (Cliente cliente : listaclientees) {
			System.out.println("Nome : "+cliente.getNome());
		}

		assertNotNull(response.getBody());
	}
	
	@Test
	public void testConsultaId() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Cliente> response = restTemplate.exchange("http://localhost:8080/CadastroClientes/ConsultaId/1",
				HttpMethod.GET, null, new ParameterizedTypeReference<Cliente>() {
				});

		Cliente cliente = response.getBody();
		
		System.out.println("####################### testConsultaId #######################");

		System.out.println("Nome : "+cliente.getNome());

		assertNotNull(response.getBody());
	}
	
	@Test
	public void testConsulta() {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Cliente>> response = restTemplate.exchange("http://localhost:8080/CadastroClientes/Consulta/1/ID",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Cliente>>() {
				});

		Iterable<Cliente> listaclientees = response.getBody();
		
		System.out.println("####################### testConsulta #######################");

		for (Cliente cliente : listaclientees) {
			System.out.println("Consulta por ID   : "+cliente.getNome());
		}
		
		ResponseEntity<Iterable<Cliente>> response2 = restTemplate.exchange("http://localhost:8080/CadastroClientes/Consulta/cliente/NOME",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Cliente>>() {
				});

		listaclientees = response2.getBody();

		for (Cliente cliente : listaclientees) {
			System.out.println("Consulta por Nome : "+cliente.getNome());
		}
		
		ResponseEntity<Iterable<Cliente>> response3 = restTemplate.exchange("http://localhost:8080/CadastroClientes/Consulta/77777777777/CPF",
				HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Cliente>>() {
				});

		listaclientees = response3.getBody();

		for (Cliente cliente : listaclientees) {
			System.out.println("Consulta por CPF  : "+cliente.getNome());
		}

		assertNotNull(response.getBody());
	}
	
	private Cliente buscacliente(String id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Cliente> response = restTemplate.exchange("http://localhost:8080/CadastroClientes/ConsultaId/"+id,
				HttpMethod.GET, null, new ParameterizedTypeReference<Cliente>() {
				});

		Cliente cliente = response.getBody();

		return cliente;
	}

}
