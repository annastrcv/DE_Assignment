package de_assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class Solution {
    ObservableList<XYChart.Data<Double, Double>> exact = FXCollections.observableArrayList();
    ObservableList<XYChart.Data<Double, Double>> approx = FXCollections.observableArrayList();
    ObservableList<XYChart.Data<Double, Double>> local = FXCollections.observableArrayList();
    ObservableList<XYChart.Data<Double, Double>> total = FXCollections.observableArrayList();
}
