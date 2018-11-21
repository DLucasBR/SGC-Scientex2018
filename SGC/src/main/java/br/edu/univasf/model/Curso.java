package br.edu.univasf.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.univasf.model.enums.AreasDoConhecimento;
import br.edu.univasf.model.enums.Salas;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Column(nullable = false, length = 50)
	private String titulo;
	@NotBlank
	@Column(nullable = false, length = 500)
	private String descricao;
	@NotBlank
	@Column(nullable = false, length = 500)
	private String justificativa;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AreasDoConhecimento areaDoConhecimento;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "instutor_id", nullable = false)
	private Instrutor instrutor;
	@NotNull
	@Column(nullable = false, name = "data_curso")
	private LocalDate data;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Salas sala;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "curso_alunos", joinColumns = @JoinColumn(name = "curso_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private List<Aluno> alunos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public AreasDoConhecimento getAreaDoConhecimento() {
		return areaDoConhecimento;
	}

	public void setAreaDoConhecimento(AreasDoConhecimento areaDoConhecimento) {
		this.areaDoConhecimento = areaDoConhecimento;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Salas getSala() {
		return sala;
	}

	public void setSala(Salas sala) {
		this.sala = sala;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}
