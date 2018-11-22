package br.edu.univasf.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Aluno;
import br.edu.univasf.model.Curso;
import br.edu.univasf.model.Instrutor;
import br.edu.univasf.model.enums.AreasDoConhecimento;
import br.edu.univasf.model.enums.Salas;

@ManagedBean
@ViewScoped
public class CadastroCursoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Curso curso = new Curso();
	private List<Aluno> alunosCadastradosNoSistema;
	private List<Instrutor> instrutoresCadastradosNoSistema;
	private Aluno alunoSelecionado;
	
	DAO<Aluno> daoAluno; 
	DAO<Instrutor> daoInstrutor;
	DAO<Curso> daoCurso;

	public CadastroCursoBean() {
		daoAluno = new DAO<Aluno>(Aluno.class);
		alunosCadastradosNoSistema = daoAluno.listaTodos();
		daoInstrutor = new DAO<Instrutor>(Instrutor.class);
		instrutoresCadastradosNoSistema = daoInstrutor.listaTodos();
		daoCurso = new DAO<Curso>(Curso.class);
	}

	public Curso getCurso() {
		return curso;
	}

	public List<Instrutor> getInstrutoresCadastradosNoSistema() {
		return instrutoresCadastradosNoSistema;
	}

	public void setInstrutoresCadastradosNoSistema(List<Instrutor> instrutoresCadastradosNoSistema) {
		this.instrutoresCadastradosNoSistema = instrutoresCadastradosNoSistema;
	}

	public List<Aluno> getAlunosCadastradosNoSistema() {
		return alunosCadastradosNoSistema;
	}

	public void setAlunosCadastradosNoSistema(List<Aluno> alunos) {
		this.alunosCadastradosNoSistema = alunos;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public Salas[] getSalas() {
		return Salas.values();
	}

	public AreasDoConhecimento[] getAreasDoConhecimento() {
		return AreasDoConhecimento.values();
	}

	public void cadastrar() {
		if (this.curso.getId() == null) {
			daoCurso.adiciona(this.curso);
		} else {
			daoCurso.atualiza(curso);
		}
	}
	
	public void excluir() {
		daoCurso.remove(curso);
	}

	public void adicionarAluno() {
		if (!this.curso.getAlunos().contains(alunoSelecionado) && alunoSelecionado.getId() != null)
			this.curso.getAlunos().add(alunoSelecionado);
	}
	
	public void removeAluno() {
		this.curso.getAlunos().remove(alunoSelecionado);
	}

}
