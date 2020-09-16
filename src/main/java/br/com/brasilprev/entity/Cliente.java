package br.com.brasilprev.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.brasilprev.web.jsonview.Views;

/**
 * @author Anderson Virginio Martins
 */
@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1022092624232399374L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Public.class)
	private Long id;

	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private String nome;

	@Column(unique = true, nullable = false)
	private String cpf;

	private String endereco;
	
	private LocalDateTime dataCadastro;
	private LocalDateTime dataAtualização;

	public Cliente() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAtualização() {
		return dataAtualização;
	}

	public void setDataAtualização(LocalDateTime dataAtualização) {
		this.dataAtualização = dataAtualização;
	}
	
	

}
