package br.edu.univasf.model.enums;

public enum AreasDoConhecimento {

	Humanas("Ciências Humanas"), Exatas("Ciências Exatas"), Biologicas("Ciências Biologicas"), 
	Computacao("Ciências da computação"), Militares("Ciências militares"), Engenharia("Engenharias"),
	Medica ("Medicina"), Outros("Outros");
	
	private String descricao;

	AreasDoConhecimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
