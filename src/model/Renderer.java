package model;

import processing.core.PApplet;

import java.util.Arrays;
import java.util.List;

//To control the simulation and communicate between the GUI library, data controller and unit controller.
public class Renderer extends PApplet {

    //A logic controller for simulating each unit.
    UnitController unitController;

    //A data parser to allow easier conversion between simulation data and graph library input data.
    DataParser dataParser;

    boolean end = false;

    //Array to store values displayed on screen edge
    String[] textData;


    public static void main(String[] args) {
        PApplet.main("model.Renderer");
    }

    @Override
    public void settings() {
        size(Cfg.SIMULATION_WIDTH[0] + Cfg.SIDEBAR_WIDTH, Cfg.SIMULATION_WIDTH[1]);
    }

    //Initialises field instances, also sets Processing window settings
    @Override
    public void setup() {

        unitController = new UnitController();
        dataParser = new DataParser(this);

        textFont(createFont("Arial", 15), 15);

        textData = new String[7];
        textData[0] = "N: " + Cfg.NO_OF_UNITS;

        textData[5] = "α: " + Cfg.ALPHA;
        textData[6] = "γ: " + Cfg.GAMMA;

        frameRate(Cfg.DEFAULT_FRAMERATE);
        noStroke();
        rectMode(CORNERS);
    }

    //Function to be called by the processing library once per frame update
    @Override
    public void draw() {

        if (end) noLoop();

        background(Cfg.BG_COL[0], Cfg.BG_COL[1], Cfg.BG_COL[2]);

        //Call a single update to the simulation controller
        unitController.updateUnits();

        int[] currVals = unitController.getStats();

        if (currVals[1] == 0) end = true;

        textData[1] = "t: " + frameCount;
        textData[2] = "S: " + currVals[0];
        textData[3] = "I: " + currVals[1];
        textData[4] = "R: " + currVals[2];

        //Update the parser with the new values and draw the graph to the screen
        dataParser.updateAndDraw(frameCount - 1, currVals);

        fill(Cfg.BG_COL[0], Cfg.BG_COL[1], Cfg.BG_COL[2]);

        //draw a textbox behind the text data onto the screen.
        rect(Cfg.SIMULATION_WIDTH[0] + Cfg.SIDEBAR_WIDTH - Cfg.RIGHT_TEXT_PADDING - Cfg.TEXT_BOX_EXPANSION, 0, Cfg.SIMULATION_WIDTH[0] + Cfg.SIDEBAR_WIDTH, Cfg.TOP_TEXT_PADDING + Cfg.TEXT_OFFSET * textData.length);

        //draw the text items onto the screen
        fill(Cfg.TEXT_COL[0], Cfg.TEXT_COL[1], Cfg.TEXT_COL[2], Cfg.TEXT_COL[3]);
        for (int i = 0, max = textData.length; i < max; i++)
            text(textData[i], Cfg.SIMULATION_WIDTH[0] + Cfg.SIDEBAR_WIDTH - Cfg.RIGHT_TEXT_PADDING, Cfg.TOP_TEXT_PADDING + Cfg.TEXT_OFFSET * i);

        //Retrieve a list of units from the controller and draw them onto the screen
        List<Unit> units = unitController.getUnits();
        for (Unit u : units) {

            int radius = (u.getState() == State.I) ? Cfg.INF_UNIT_RADIUS : Cfg.UNIT_RADIUS;

            switch (u.getState()) {
                case S:
                    fill(Cfg.S_COL[0], Cfg.S_COL[1], Cfg.S_COL[2], Cfg.S_COL[3]);
                    break;
                case I:
                    fill(Cfg.I_COL[0], Cfg.I_COL[1], Cfg.I_COL[2], Cfg.I_COL[3]);
                    break;
                case R:
                    fill(Cfg.R_COL[0], Cfg.R_COL[1], Cfg.R_COL[2], Cfg.R_COL[3]);
                    break;
            }
            double[] cord = u.getCords();
            circle((float) cord[0], (float) cord[1], radius);
        }

        //Save a screenshot of the window at the end of a simulation
        if (end) {
            String filename = "R " + Cfg.GAMMA + " I " + Cfg.ALPHA + ".png";
            saveFrame(filename);
        }
    }


}
