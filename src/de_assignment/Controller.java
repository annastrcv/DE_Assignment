package de_assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static de_assignment.Utils.*;

public class Controller implements Initializable {

    @FXML
    private TextField x0;
    @FXML
    private TextField y0;
    @FXML
    private TextField xmax;
    @FXML
    private TextField n;

    @FXML
    private Button draw;

    @FXML
    private LineChart<Double, Double> solution;
    @FXML
    private LineChart<Double, Double> local_error;
    @FXML
    private LineChart<Double, Double> local_error_n;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        solution.getXAxis().setLabel("X");
        solution.getYAxis().setLabel("Y");
        solution.setCreateSymbols(false);

        local_error.getXAxis().setLabel("X");
        local_error.getYAxis().setLabel("Local Error");
        local_error.setCreateSymbols(false);

        local_error_n.getXAxis().setLabel("N");
        local_error_n.getYAxis().setLabel("Max Local Error");
        local_error_n.setCreateSymbols(false);
    }


    public void listener(javafx.event.ActionEvent actionEvent) {
        //noinspection unchecked
        List<Solver> solvers = listOf(new EulerMethod(), new ImprovedEulerMethod(), new RungeKuttaMethod());


        Double x0_ = Double.parseDouble(x0.getText());
        Double y0_ = Double.parseDouble(y0.getText());
        Double xmax_ = Double.parseDouble(xmax.getText());
        int n_ = Integer.parseInt(n.getText());

        Double h = (xmax_ - x0_) / n_;
        BiArgFunction f = (Double x, Double y) -> (2 - y * y) / (2 * x * x * y);
        BiArgFunction c_func = (Double x, Double y) -> (x * Math.log(y * y - 2) - 1) / x;
        Double c = c_func.func(x0_, y0_);
        ArgFunction exact = (Double x) -> Math.sqrt(Math.pow(Math.E, c + 1 / x) + 2);


        solution.getData().clear();
        local_error.getData().clear();
        local_error_n.getData().clear();

        // Add exact solution separately
        solution.getData().add(seriesOf(solvers.get(0).solve(f, x0_, y0_, xmax_, h, exact).exact, "Exact"));

        for (Solver solver : solvers) {
            Solution sol = solver.solve(f, x0_, y0_, xmax_, h, exact);

            solution.getData().add(seriesOf(sol.approx, solver.getName()));
            local_error.getData().add(seriesOf(sol.local, solver.getName()));
            local_error_n.getData().add(seriesOf(LocalErrorOfN.calcMax(xmax_,x0_,y0_,n_,f,exact, solver), solver.getName()));
        }
    }
}
