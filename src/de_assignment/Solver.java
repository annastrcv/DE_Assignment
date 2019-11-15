package de_assignment;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class Solver {
    private String name;

    public Solver(String name) {
        this.name = name;
    }

    public abstract Solution solve(BiArgFunction f, double x0, double y0, double xmax, double h, ArgFunction exact);

    public String getName() {
        return name;
    }
}
