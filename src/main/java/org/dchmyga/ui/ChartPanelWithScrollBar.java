package org.dchmyga.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import org.dchmyga.data.DataHolder;
import org.dchmyga.data.format.Field;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

class ChartPanelWithScrollBar extends JPanel {

	private ChartPanel chartPanel;
	private DataHolder dataHolder;
	private List<GraphFields> selectedFields = null;
	private int start = 0;
	private int frame = 100;
	private int end = start + frame;
	private JScrollBar scrollBar;
	private int step = 10;
	private Map<Field, XYSeries> seriesMap = new HashMap<>();

	public ChartPanelWithScrollBar() {
		super(new GridBagLayout());
	}

	public void setDataHolder(DataHolder dataHolder) {
		this.dataHolder = dataHolder;
		reinitPanels();
	}

	public void reinitPanels() {
		if (chartPanel != null) {
			this.remove(chartPanel);
		}
		if (scrollBar != null) {
			this.remove(scrollBar);
		}
		initPanels();
		this.repaint();
		this.revalidate();
	}

	private void initPanels() {
		setStart(0);
		setEnd(frame);
		Map<GraphFields, List<Number>> data = null;
		if (dataHolder != null) {
			data = dataHolder.getDataForFields(getSelectedFields(), getStart(), getEnd());
		}
		JFreeChart xylineChart = ChartFactory.createXYLineChart("Renix", "Category", "Score", createDataset(data),
				PlotOrientation.VERTICAL, true, true, false);

		chartPanel = new ChartPanel(xylineChart);
		chartPanel.setDomainZoomable(false);
		chartPanel.setRangeZoomable(false);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		final XYPlot plot = xylineChart.getXYPlot();
		plot.setDomainPannable(true);
		plot.getDomainAxis().setRange(getStart(), getEnd());
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setDomainGridlinePaint(Color.BLACK);

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setBaseShapesVisible(false);
		renderer.setBaseShapesFilled(false);
		renderer.setBaseStroke(new BasicStroke(2));
		plot.setRenderer(renderer);
		this.add(chartPanel, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		scrollBar = initScrollBar();
		this.add(scrollBar, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.01, GridBagConstraints.SOUTH,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
	}

	private JScrollBar initScrollBar() {
		int maxValue = 0;
		if (dataHolder != null) {
			maxValue = dataHolder.getValuesCount();
		}
		int extent = getApproximatedExtent(maxValue);
		JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, getStart(), extent, 0,
				dataHolder.getValuesCount());
		if (dataHolder != null) {
			scrollBar.setUnitIncrement(step);
		} else {
			scrollBar.setEnabled(false);
		}
		scrollBar.addAdjustmentListener(new ScrollBarListener());
		return scrollBar;
	}

	private int getApproximatedExtent(int maxValue) {
		int extent = getVisibleRange();
		return extent;
	}

	private int getEnd() {
		return end;
	}

	private int getStart() {
		return start;
	}

	private XYDataset createDataset(Map<GraphFields, List<Number>> data) {
		final XYSeriesCollection dataset = new XYSeriesCollection();
		if (data != null) {
			for (Map.Entry<GraphFields, List<Number>> entry : data.entrySet()) {
				XYSeries series = new XYSeries(entry.getKey().getTitle());
				for (int i = 0; i < entry.getValue().size(); i++) {
					series.add(i, entry.getValue().get(i));
				}
				seriesMap.put(entry.getKey(), series);
				dataset.addSeries(series);
			}
		}
		return dataset;
	}

	private void changeData(int prevStart, int prevEnd, int curStart, int curEnd) {
		System.out.println("changing data");
		if (curStart == prevStart) {
			return;
		}
		Map<GraphFields, List<Number>> data = dataHolder.getDataForFields(getSelectedFields(), curStart, curEnd);
		for (Map.Entry<GraphFields, List<Number>> entry : data.entrySet()) {
			Field f = entry.getKey();
			XYSeries series = seriesMap.get(f);
			series.clear();
			List<Number> dataForField = entry.getValue();
			for (int i = 0, j = curStart; i < dataForField.size(); i++, j++) {
				series.add(j, dataForField.get(i), false);
			}
		}
		chartPanel.getChart().getXYPlot().getDomainAxis().setRange(curStart, curEnd);
	}

	private List<GraphFields> getSelectedFields() {
		if (selectedFields == null) {
			selectedFields = GraphFields.valuesAsList();
		}
		return selectedFields;
	}

	private int getVisibleRange() {
		return getEnd() - getStart();
	}

	private void setStart(int start) {
		this.start = start;
	}

	private void setEnd(int end) {
		this.end = end;
	}

	private class ScrollBarListener implements AdjustmentListener {

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			int prevStart = getStart();
			int prevEnd = getEnd();
			int curStart = e.getAdjustable().getValue();
			int curEnd = e.getAdjustable().getValue() + 100;
			setStart(curStart);
			setEnd(curEnd);
			changeData(prevStart, prevEnd, curStart, curEnd);
		}

	}
}