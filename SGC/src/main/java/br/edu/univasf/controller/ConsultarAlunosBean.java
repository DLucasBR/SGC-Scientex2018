package br.edu.univasf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Aluno;

@ManagedBean
@ViewScoped
public class ConsultarAlunosBean implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private List<Aluno> alunos = new ArrayList<>();
	private Aluno alunoSelecionado;

	public ConsultarAlunosBean() {
		DAO<Aluno> daoAlunos = new DAO<Aluno>(Aluno.class);
		this.alunos = daoAlunos.listaTodos();
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> Alunos) {
		this.alunos = Alunos;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		System.out.println("fui chamado!" + alunoSelecionado.getNome());
		this.alunoSelecionado = alunoSelecionado;
	}

}
