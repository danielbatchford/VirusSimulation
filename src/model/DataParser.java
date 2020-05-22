package model;

import org.gicentre.utils.stat.XYChart;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

class DataParser {

    private final XYChart chart;
    private final List xData;
    List<float[]> yData;
    PApplet inst;

    DataParser(PApplet inst) {
        this.inst = inst;

        //Initialise and manipulate settings of chart object.
        chart = new XYChart(this.inst);
        chart.showXAxis(true);
        chart.showYAxis(true);
        chart.setXAxisLabel(Cfg.XLABEL);
        chart.setYAxisLabel(Cfg.YLABEL);
        chart.setMinX(0);
        chart.setMinY(0);
        chart.setMaxY(Cfg.NO_OF_UNITS);
        chart.setLineWidth(5);
        chart.setPointSize(0);

        xData = new ArrayList<>();
        yData = new ArrayList<>();
    }

    void updateAndDraw(int x, int[] y) {

        //Add a new x,y point into the dataset
        xData.add((float) x);
        yData.add(new float[]{y[0], y[1], y[2]});


        //Transforming the data from 2 lists into 4 primitive arrays
        int length = xData.size();

        float[] xd = new float[length];

        float[] sd = new float[length];
        float[] id = new float[length];
        float[] rd = new float[length];

        for (int i = 0; i < length; i++) {

            xd[i] = (float) xData.get(i);
            float[] dataSet = yData.get(i);

            sd[i] = dataSet[0];
            id[i] = dataSet[1];
            rd[i] = dataSet[2];
        }

        //Draw the dataset's to the screen
        draw(Cfg.R_COL, xd, rd);
        draw(Cfg.S_COL, xd, sd);
        draw(Cfg.I_COL, xd, id);
    }

    //Draw a dataset to the screen, with provided color and x y arrays.
    private void draw(int[] colorVal, float[] xd, float[] yd) {
        chart.setData(xd, yd);
        chart.setLineColour(inst.color(colorVal[0], colorVal[1], colorVal[2]));
        chart.draw(Cfg.SIMULATION_WIDTH[0] + Cfg.LEFT_GRAPH_PADDING, 0, Cfg.SIDEBAR_WIDTH - Cfg.LEFT_GRAPH_PADDING - Cfg.RIGHT_GRAPH_PADDING, Cfg.SIMULATION_WIDTH[1]);
    }
}
