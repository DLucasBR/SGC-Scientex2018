package br.edu.univasf.model.enums;

public enum Salas {

	LabProgBas("Laboratorio de programacao básica"), LabProgAva("Laboratorio de programacao avançada"),
	LabEngSoft("Laboratorio de engenharia de software"),Salas("Laboratorio de hardware"), SalaDois("Sala 2"), 
	SalaCinco("Sala 5"), Auditorio("Auditório");

	private String descricao;

	Salas(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
