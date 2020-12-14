package api;

import java.awt.Color;
import java.util.ArrayList;

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

public class TimeSeriesAPI extends ApplicationFrame {
	
	public TimeSeriesAPI(final String title, ArrayList<String> aloctions, String location) {
		super(title);
		final XYDataset dataset = createDataset(aloctions, location);
		final JFreeChart chart = createChart(dataset);
		chart.setBackgroundPaint(Color.WHITE);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1200, 800));
		chartPanel.setMouseZoomable(true, false);
		setContentPane(chartPanel);
	}
	
	public static void getChartGraph(String location) {
		final String title = "Time Series Management";
		TimeSeriesAPI demo = new TimeSeriesAPI(title, ChartsLogicService.getLocRelations(location), location);
		demo.pack();
		RefineryUtilities.positionFrameRandomly(demo);
		demo.setVisible(true);
	}
	
	private JFreeChart createChart(final XYDataset dataset) {
		return ChartFactory.createTimeSeriesChart("Number of Patients in Seoul", "Time", "Number of Patients", dataset,
				false, false, false);
	}
	
	private XYDataset createDataset(ArrayList<String> aloctions, String location) {
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(createSeries(location));
		for (int i = 0; i < aloctions.size(); i++) {
			TimeSeries tempts = new TimeSeries("Data");
			tempts = createSeries(aloctions.get(i));
			dataset.addSeries(tempts);
		}
		return dataset;
	}

	private TimeSeries createSeries(String location) {
		final TimeSeries series = new TimeSeries("Location");
		Day current1 = new Day();
		int value = 0;
		ArrayList numbers = ChartsLogicService.getNumbers(location);
		for (int i = 0; i < numbers.size(); i++) {
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
}