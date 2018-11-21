package br.edu.univasf.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Aluno;
import br.edu.univasf.model.Curso;
import br.edu.univasf.model.enums.AreasDoConhecimento;
import br.edu.univasf.model.enums.Salas;

@ManagedBean
@ViewScoped
public class CadastroCursoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Curso curso = new Curso();
	private List<Aluno> alunosCadastradosNoSistema;
	private Aluno alunoSelecionado;

	public CadastroCursoBean() {
		DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
		alunosCadastradosNoSistema = dao.listaTodos();
	}
	
	public Curso getCurso() {
		return curso;
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
		DAO<Curso> dao = new DAO<Curso>(Curso.class);
		
		if(this.curso.getId() == null) {
			dao.adiciona(this.curso);
		}else {
			dao.atualiza(curso);
		}
	}
	
	public void adicionarAluno() {
		this.curso.getAlunos().add(alunoSelecionado);
	}

}
