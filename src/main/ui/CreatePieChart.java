package ui;

import model.ExpenseList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.util.List;


public class CreatePieChart extends JFrame {

    public CreatePieChart(String frameTitle, String chartTitle, ExpenseList userExpenses) {
        PieDataset dataset = createDataset(userExpenses);
        JFreeChart pieChart = createChart(dataset, chartTitle);
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        setContentPane(chartPanel);
    }

    private PieDataset createDataset(ExpenseList userExpenses) {
        List<String> categories = userExpenses.allCategories();
        List<Integer> percentage = userExpenses.categoriesPercentage();
        DefaultPieDataset result = new DefaultPieDataset();
        int start = 0;
        for (String c : categories) {
            result.setValue(c, percentage.get(start));
            start++;
        }
        return result;
    }

    private JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart pieChart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return pieChart;
    }
}
