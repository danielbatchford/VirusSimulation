package model;

//A class containing variables used throughout the package.
public class Cfg {

    static final int DEFAULT_FRAMERATE = 120;
    static final int[] SIMULATION_WIDTH = new int[]{800, 800};

    static final int SIDEBAR_WIDTH = 800;

    static final int LEFT_GRAPH_PADDING = 50;
    static final int RIGHT_GRAPH_PADDING = 50;

    static final int RIGHT_TEXT_PADDING = 100;
    static final int TOP_TEXT_PADDING = 50;
    static final int TEXT_BOX_EXPANSION = 10;
    static final int TEXT_OFFSET = 20;

    static final int[] BG_COL = new int[]{255, 255, 255};//{210, 204, 186};
    static final int[] S_COL = new int[]{0, 0, 0, 255};
    static final int[] I_COL = new int[]{204, 41, 54, 255};
    static final int[] R_COL = new int[]{129, 108, 97, 100};
    static final int[] TEXT_COL = new int[]{0, 0, 0, 255};

    static final int NO_OF_UNITS = 10000;
    static final int UNIT_RADIUS = 4;
    static final int INF_UNIT_RADIUS = 10;

    static final double SD_OF_WALK_DIST = 5;
    static final double GAMMA = 0.1;
    static final double ALPHA = 0.1;
    static final double INFECTION_RADIUS = 15;

    static final String XLABEL = "Steps";
    static final String YLABEL = "Number of Units";
}
