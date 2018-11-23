package br.edu.univasf.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Aluno;
import br.edu.univasf.model.enums.EstadosFederacao;
import br.edu.univasf.util.ClienteWsCorreios;
import br.edu.univasf.util.FacesUtil;
import br.edu.univasf.validator.ValidadorCPF;

@ManagedBean
@ViewScoped
public class CadastroAlunoBean implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Aluno aluno = new Aluno();
	private String cepInformado;
	private DAO<Aluno> daoAluno;

	public CadastroAlunoBean() {
		daoAluno = new DAO<Aluno>(Aluno.class);
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public String getCepInformado() {
		return cepInformado;
	}

	public void setCepInformado(String cepInformado) {
		this.cepInformado = cepInformado;
	}

	public void cadastrar() {
		if(this.aluno.getId() == null) {
			daoAluno.adiciona(this.aluno);
			FacesUtil.addInfoMessage("Aluno cadastrado com sucesso!");
			this.aluno = new Aluno();
		}else {
			daoAluno.atualiza(aluno);
		}
	}
	
	public EstadosFederacao[] getUFs() {
		return EstadosFederacao.values();
	}
	
	public void pesquisaCep() {

		this.aluno.setCep(cepInformado);
		System.out.println("Pesquisando CEP");
		try {
			Map<String, String> endereco = ClienteWsCorreios.getMapPorCep(cepInformado);
			this.aluno.setLogradouro(endereco.get("end"));
			this.aluno.setBairro(endereco.get("bairro"));
			this.aluno.setMunicipio(endereco.get("cidade"));
			this.aluno.setComplementoEndereco(endereco.get("complemento"));
			this.aluno.setUF(EstadosFederacao.valueOf(endereco.get("uf")));
		} catch (RuntimeException e) {
			FacesUtil.addErrorMessage("Seu CEP nao foi localizado pelos correios, preencha o endereco manualmente!");
		}
	}

	public void validaCPF(FacesContext fc, UIComponent component, Object value) {
		String CPF = value.toString();
		if (!ValidadorCPF.isCPF(CPF)) {
			FacesUtil.addErrorMessage("CPF em branco ou inválido");
		} else
			this.aluno.setCPF(ValidadorCPF.imprimeCPF(CPF));
	}

}
