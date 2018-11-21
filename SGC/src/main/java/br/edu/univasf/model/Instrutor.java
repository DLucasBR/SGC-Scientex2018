package br.edu.univasf.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.univasf.model.enums.AreasDoConhecimento;

@Entity
@Table(name = "instrutores")
public class Instrutor implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	@Column(length = 60)
	private String nome;
	@NotNull
	@Enumerated(EnumType.STRING)
	private AreasDoConhecimento areaDoConhecimento;
	@NotNull
	@Column(length = 30)
	private String email;
	@NotBlank
	@Column(length = 60)
	private String profissao;
	private String telefone;
	
	
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

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public AreasDoConhecimento getAreaDoConhecimento() {
		return areaDoConhecimento;
	}

	public void setAreaDoConhecimento(AreasDoConhecimento areaDoConhecimento) {
		this.areaDoConhecimento = areaDoConhecimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
