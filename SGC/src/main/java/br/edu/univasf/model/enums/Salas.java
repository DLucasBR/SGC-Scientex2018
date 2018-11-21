package br.edu.univasf.model.enums;

public enum Salas {

	LabProgBas("Laboratorio de programacao b�sica"), LabProgAva("Laboratorio de programacao avan�ada"),
	LabEngSoft("Laboratorio de engenharia de software"),Salas("Laboratorio de hardware"), SalaDois("Sala 2"), 
	SalaCinco("Sala 5"), Auditorio("Audit�rio");

	private String descricao;

	Salas(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
