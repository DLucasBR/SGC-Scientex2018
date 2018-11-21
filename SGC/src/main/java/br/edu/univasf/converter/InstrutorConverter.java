package br.edu.univasf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Instrutor;

@FacesConverter(forClass = Instrutor.class)
public class InstrutorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			DAO<Instrutor> dao = new DAO<Instrutor>(Instrutor.class);
			Instrutor instrutor = dao.buscaPorId(new Long(value));
			return instrutor;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Instrutor instrutor = (Instrutor)value;
			return instrutor.getId().toString();
		}
		return "";
	}
}
