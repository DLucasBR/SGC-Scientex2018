package br.edu.univasf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Aluno;

@ManagedBean
@ViewScoped
public class ConsultarAlunosBean {

	private List<Aluno> alunos = new ArrayList<>();

	public ConsultarAlunosBean() {
		DAO<Aluno> daoAlunos = new DAO<Aluno>(Aluno.class);
		this.alunos = daoAlunos.listaTodos();
		System.out.println("Lista de alunos carregada " + alunos.get(1).getNome());
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> Alunos) {
		this.alunos = Alunos;
	}

}
