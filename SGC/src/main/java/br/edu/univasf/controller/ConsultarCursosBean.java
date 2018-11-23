package br.edu.univasf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Curso;
import br.edu.univasf.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ConsultarCursosBean implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private List<Curso> cursos = new ArrayList<>();
	private DAO<Curso> daoCursos;
	private Curso cursoSelecionado;
	
	public ConsultarCursosBean() {
		daoCursos = new DAO<Curso>(Curso.class);
		this.cursos = daoCursos.listaTodos();
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	public Curso getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public void excluirCurso() {
		daoCursos.remove(cursoSelecionado);
		cursos.remove(cursoSelecionado);
		FacesUtil.addInfoMessage("Curso removido com sucesso!");
	}

}
