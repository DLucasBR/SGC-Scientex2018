package br.edu.univasf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Instrutor;

@ManagedBean
@ViewScoped
public class ConsultarInstrutoresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Instrutor> instrutores = new ArrayList<>();

	public ConsultarInstrutoresBean() {
		DAO<Instrutor> daoInstrutores = new DAO<Instrutor>(Instrutor.class);
		this.instrutores = daoInstrutores.listaTodos();
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	public void setInstrutores(List<Instrutor> Instrutores) {
		this.instrutores = Instrutores;
	}

}