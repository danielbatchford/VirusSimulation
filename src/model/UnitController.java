package model;

import java.util.ArrayList;
import java.util.List;

//To Control the behaviour of each unit on the grid.
class UnitController {

    List<Unit> units;

    //Initialise all units in a random grid location
    UnitController() {
        units = new ArrayList();

        for (int i = 0; i < Cfg.NO_OF_UNITS; i++) {
            units.add(new Unit());
        }
        units.get(Utils.rand.nextInt(units.size())).setState(State.I); //randomly infect one unit
    }

    //Simulate a single step in the simulation
    void updateUnits() {

        for (Unit u : units) {

            //If a person is removed, skip over them.
            if (u.getState() == State.R) continue;

            //Generate new co-ordinates of a unit.
            double[] newPos = Utils.add(u.getCords(), Utils.getWalkVector());

            //Check edge conditions, to ignore movement if it exceeds the grid boundaries.
            if (newPos[0] < 0 || newPos[1] < 0 || newPos[0] >= Cfg.SIMULATION_WIDTH[0] || newPos[1] >= Cfg.SIMULATION_WIDTH[1]) {
                continue;
            }
            u.setCords(newPos);
        }
        infectInRadius();
    }

    //A function to simulate infection within a radius of an infected unit, and removal from an infected status.
    private void infectInRadius() {

        List<Unit> infected = new ArrayList<>();

        //Remove a unit's infection with probability GAMMA.
        for (Unit u : units) {
            if (u.getState() == State.I && Utils.rand.nextFloat() < Cfg.GAMMA) {
                u.setState(State.R);
            }
        }

        //Infect in a radius of an infected unit.
        for (Unit u : units) {

            //Infect in a radius with a random probability ALPHA
            if (u.getState() == State.I && Utils.rand.nextFloat() < Cfg.ALPHA) {
                for (Unit v : units) {
                    if (Utils.get2DDistance(u.getCords(), v.getCords()) < Cfg.INFECTION_RADIUS && v.getState() == State.S) {
                        infected.add(v);
                    }
                }
            }
        }
        //Set the state of all newly infected units.
        for (Unit u : infected) u.setState(State.I);
    }

    List<Unit> getUnits() {
        return units;
    }

    //Return a 3 length list of S,I,R counts in the units list.
    int[] getStats() {

        int succeptible = 0;
        int infected = 0;
        int removed = 0;

        for (Unit u : units) {
            switch (u.getState()) {
                case S:
                    succeptible++;
                    break;
                case I:
                    infected++;
                    break;
                case R:
                    removed++;
                    break;
            }
        }
        return new int[]{succeptible, infected, removed};
    }
}
