package br.edu.univasf.model.enums;

public enum EstadosFederacao {

	AC("Acre"), AL("Alagoas"), AP("Amap�"), AM("Amazonas"), BA("Bahia"), CE("Cear�"), DF("Distrito Federal"),
	GO("Goi�s"), MA("Maranh�o"), MT("Mato Grosso"), MS("Mato Grosso do Sul"), MG("Minas Gerais"), PA("Par�"),
	PB("Para�ba"), PR("Paran�"), PE("Pernambuco"), PI("Piau�"), RJ("Rio de Janeiro"), RN("Rio Grande do Norte"),
	RS("Rio Grande do Sul"), RO("Rond�nia"), RR("Roraima"), SC("Santa Catarina"), SP("S�o Paulo"), SE("Sergipe"),
	TO("Tocantins");

	private String descricao;

	EstadosFederacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
