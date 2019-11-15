package de_assignment;

import javafx.scene.chart.XYChart;

public class EulerMethod extends Solver {

    public EulerMethod() {
        // Call the super class with the give argument
        super("Euler's Method");
    }

    @Override
    public Solution solve(BiArgFunction f, double x0, double y0, double xmax, double h, ArgFunction exact) {
        Solution sol = new Solution();

        // Insert IVP point
        sol.approx.add(new XYChart.Data<>(x0, y0));
        sol.exact.add(new XYChart.Data<>(x0, y0));
        sol.total.add(new XYChart.Data<>(x0, 0.0));
        sol.local.add(new XYChart.Data<>(x0, 0.0));

        double prevY = y0;
        double prevE, curT;
        for (double x = x0 + h; x <= xmax; x += h) {
            sol.approx.add(new XYChart.Data<>(x , prevY = prevY + h * f.func(x - h, prevY)));
            sol.exact.add(new XYChart.Data<>(x, prevE = exact.func(x)));
            sol.total.add(new XYChart.Data<>(x,curT = prevY - prevE ));
            sol.local.add(new XYChart.Data<>(x,curT - sol.total.get(sol.total.size() - 2).getYValue()));
        }

        return sol;
    }
}
