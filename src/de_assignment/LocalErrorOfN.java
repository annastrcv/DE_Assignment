package de_assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class LocalErrorOfN {
    public static ObservableList<XYChart.Data<Double,Double>> calcMax(Double xmax, Double x0, Double y0, int n, BiArgFunction f, ArgFunction exact, Solver sl ) {
        ObservableList<XYChart.Data<Double, Double>> local_error_of_n = FXCollections.observableArrayList();
        for (int i = 1; i <= n; i++) {
            Double h1 = (xmax - x0) / i;
            Solution solu = sl.solve(f, x0, y0, xmax, h1, exact);
            Double max = 0.0;
            for (int j = 0; j < solu.local.size(); j++) {
                if (Math.abs(solu.local.get(j).getYValue()) > max) {
                    max = Math.abs(solu.local.get(j).getYValue());
                }
            }
            local_error_of_n.add(new XYChart.Data<>((double) i, max));
        }
        return local_error_of_n;
    }
}

