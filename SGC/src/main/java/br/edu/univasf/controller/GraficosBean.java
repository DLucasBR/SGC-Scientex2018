package br.edu.univasf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.edu.univasf.DAO.DAO;
import br.edu.univasf.model.Curso;
import br.edu.univasf.model.enums.AreasDoConhecimento;
import br.edu.univasf.model.enums.Salas;

@ManagedBean
@ViewScoped
public class GraficosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private BarChartModel barModel;
	private LineChartModel lineModel2;
	private DAO<Curso> daoCursos;
	private int numeroMaxDeCursos;

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	public void setLineModel2(LineChartModel zoomModel) {
		this.lineModel2 = zoomModel;
	}

	@PostConstruct
	public void init() {
		daoCursos = new DAO<>(Curso.class);
		createBarModels();
		createLineModels();
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);

		ChartSeries areasDoConhecimentoSerie = new ChartSeries();

		numeroMaxDeCursos = 0;
		int aux;

		for (AreasDoConhecimento area : AreasDoConhecimento.values()) {
			aux = daoCursos.contaAreasDoConhecimentoNosCursos(area);
			if (numeroMaxDeCursos < aux)
				numeroMaxDeCursos = aux;
			areasDoConhecimentoSerie.set(area.toString(), aux);
		}

		model.addSeries(areasDoConhecimentoSerie);

		return model;
	}

	private void createBarModels() {
		createBarModel();
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle("Cursos por area do conhecimento");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Areas do conhecimento");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("cursos");
		yAxis.setMin(0);
		yAxis.setMax(numeroMaxDeCursos + 1);
	}

	private LineChartModel initCategoryModel() {
		LineChartModel model = new LineChartModel();

		ChartSeries salasSerie = new ChartSeries();

		numeroMaxDeCursos = 0;
		int aux;

		for (Salas sala : Salas.values()) {
			aux = daoCursos.contaSalasNosCursos(sala);
			if (numeroMaxDeCursos < aux)
				numeroMaxDeCursos = aux;
			salasSerie.set(sala.toString(), aux);
		}

		salasSerie.setLabel("Salas");
		model.addSeries(salasSerie);

		return model;
	}

	private void createLineModels() {
		lineModel2 = initCategoryModel();
		lineModel2.setAnimate(true);
		lineModel2.setSeriesColors("58BA27");
		lineModel2.setTitle("Cursos por salas");
		lineModel2.setLegendPosition("e");
		lineModel2.setShowPointLabels(true);
		lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Salas"));
		Axis yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis.setLabel("Cursos");
		yAxis.setMin(0);
		yAxis.setMax(numeroMaxDeCursos + 1);
	}

}
