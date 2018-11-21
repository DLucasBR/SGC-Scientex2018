package br.edu.univasf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Curso;

@ManagedBean
@ViewScoped
public class ConsultarCursosBean {

	private List<Curso> cursos = new ArrayList<>();

	public ConsultarCursosBean() {
		DAO<Curso> daoCursos = new DAO<Curso>(Curso.class);
		this.cursos = daoCursos.listaTodos();
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
