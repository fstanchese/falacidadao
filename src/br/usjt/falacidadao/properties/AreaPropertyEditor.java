package br.usjt.falacidadao.properties;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.usjt.falacidadao.dao.AreaDAO;
import br.usjt.falacidadao.model.Area;


@Component
public class AreaPropertyEditor extends PropertyEditorSupport {

	@Autowired 
	private AreaDAO daoArea;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Long id;
		Area area;
		try {
			id = Long.parseLong(text);
			area = daoArea.buscaPorId(id);
		} catch (Exception e) {
			area = null;
		}
		setValue(area);
	}
}
