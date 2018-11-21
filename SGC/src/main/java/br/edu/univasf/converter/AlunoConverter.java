package br.edu.univasf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Aluno;

@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			DAO<Aluno> dao = new DAO<Aluno>(Aluno.class);
			Aluno aluno = dao.buscaPorId(new Long(value));
			System.out.println("Chama aqui no ass string");
			System.out.println(aluno.getNome() + aluno.getId());
			return aluno;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Aluno aluno = (Aluno)value;
			System.out.println("Chama aqui no ass string" + aluno.getId());
			return aluno.getId().toString();
		}
		return "";
	}
}
