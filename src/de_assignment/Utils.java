package de_assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static XYChart.Series<Double, Double> seriesOf(ObservableList<XYChart.Data<Double, Double>> data, String name) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        series.setData(data);
        series.setName(name);
        return series;
    }

    @SafeVarargs
    public static ObservableList<XYChart.Series<Double, Double>> observableListOf(XYChart.Series<Double, Double>... series) {
        ObservableList<XYChart.Series<Double, Double>> list = FXCollections.observableArrayList();
        list.addAll(Arrays.asList(series));
        return list;
    }

    public static List listOf(Object... objects) {
        return Arrays.asList(objects);
    }

}
