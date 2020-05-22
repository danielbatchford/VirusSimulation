package model;

import java.util.Arrays;

//A class representing a single unit.
class Unit {

    private State state;
    private double[] cords;

    Unit() {
        this.state = State.S;

        //Initialise the position in a random x,y location  in the grid.
        this.cords = new double[]{Cfg.SIMULATION_WIDTH[0] * Utils.rand.nextDouble(), Cfg.SIMULATION_WIDTH[1] * Utils.rand.nextDouble()};
    }

    State getState() {
        return state;
    }

    void setState(State state) {
        this.state = state;
    }

    double[] getCords() {
        return cords;
    }

    void setCords(double[] cords) {
        this.cords = cords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Arrays.equals(cords, unit.cords);
    }


}
