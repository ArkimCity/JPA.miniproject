package view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import service.ChartsLogicService;

public class TimeSeriesView extends ApplicationFrame {
	private TimeSeries s1;

	public TimeSeriesView(final String title, ArrayList<String> aloctions, String location) {
		super(title);
		final XYDataset dataset = createDataset(aloctions, location);
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
		chartPanel.setMouseZoomable(true, false);
		setContentPane(chartPanel);
	}

	private TimeSeries createSeries(String location) {
		final TimeSeries series = new TimeSeries("Data");
		Day current1 = new Day();
		int value = 0; // 그래프 표시의 중간값
		ArrayList numbers = ChartsLogicService.getNumbers(location);
		for (int i = 0; i < 275; i++) {
			try {
				value = value + Integer.parseInt(String.valueOf(numbers.get(i)));
				series.add(current1, new Double(value));
				current1 = (Day) current1.next();
			} catch (SeriesException e) {
				System.err.println("Error adding to series");
			}
		}
		return series;
	}
	
	private XYDataset createDataset(ArrayList<String> aloctions, String location) {
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		s1 = createSeries(location);
		dataset.addSeries(s1);
		for (int i = 0; i < aloctions.size(); i++) {
			TimeSeries tempts = new TimeSeries("Data");
			tempts = createSeries(aloctions.get(i));
			dataset.addSeries(tempts);
		}
	
		// 필요한 것. - 위치 하나에 해당하는 인점 지역 리스트 받아오는 함수
		// 필요한 것. - 위치 하나에 그래프 하나 만들리스트 가져오는 함수.
		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {
		return ChartFactory.createTimeSeriesChart("Number of Patients in Seoul", "Time", "Number of Patients", dataset,
				false, false, false);
	}

	public static void getChartsGraph(ArrayList<String> aloctions, String location) {
		final String title = "Time Series Management";
		final TimeSeriesView demo = new TimeSeriesView(title, aloctions, location);
		demo.pack();
		RefineryUtilities.positionFrameRandomly(demo);
		demo.setVisible(true);
	}
}