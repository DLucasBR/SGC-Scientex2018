package br.edu.univasf.model.enums;

public enum AreasDoConhecimento {

	Humanas("Ci�ncias Humanas"), Exatas("Ci�ncias Exatas"), Biologicas("Ci�ncias Biologicas"), 
	Computacao("Ci�ncias da computa��o"), Militares("Ci�ncias militares"), Engenharia("Engenharias"),
	Medica ("Medicina"), Outros("Outros");
	
	private String descricao;

	AreasDoConhecimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
