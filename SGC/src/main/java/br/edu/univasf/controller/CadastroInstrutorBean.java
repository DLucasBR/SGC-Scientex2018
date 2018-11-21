package br.edu.univasf.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Instrutor;
import br.edu.univasf.model.enums.AreasDoConhecimento;

@ManagedBean
@ViewScoped
public class CadastroInstrutorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Instrutor instrutor = new Instrutor();

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public AreasDoConhecimento[] getAreasDoConhecimento() {
		return AreasDoConhecimento.values();
	}
	
	public void cadastrar() {
		DAO<Instrutor> dao = new DAO<Instrutor>(Instrutor.class);
		
		if(this.instrutor.getId() == null) {
			dao.adiciona(this.instrutor);
		}else {
			dao.atualiza(instrutor);
		}
	}

}
