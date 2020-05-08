import java.util.Arrays;
import java.util.List;

class Board {

    private static int GROUND = 0;
    private static int WALL = 1;

    private int nbrCellX;
    private int nbrCellY;
    private int map[][];
    private int nbrOfPast;
    private List<Past> pastsList;

    // constructor
    public Board() {
    }

    public Board(int nbrCellX, int nbrCellY, int[][] map, int nbrOfPast, List<Past> pastsList) {
        this.nbrCellX = nbrCellX;
        this.nbrCellY = nbrCellY;
        this.map = map;
        this.nbrOfPast = nbrOfPast;
        this.pastsList = pastsList;
    }

    // getters and setters
    public static int getGROUND() {
        return GROUND;
    }

    public static void setGROUND(int GROUND) {
        Board.GROUND = GROUND;
    }

    public static int getWALL() {
        return WALL;
    }

    public static void setWALL(int WALL) {
        Board.WALL = WALL;
    }

    public int getNbrCellX() {
        return nbrCellX;
    }

    public void setNbrCellX(int nbrCellX) {
        this.nbrCellX = nbrCellX;
    }

    public int getNbrCellY() {
        return nbrCellY;
    }

    public void setNbrCellY(int nbrCellY) {
        this.nbrCellY = nbrCellY;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getNbrOfPast() {
        return nbrOfPast;
    }

    public void setNbrOfPast(int nbrOfPast) {
        this.nbrOfPast = nbrOfPast;
    }

    public List<Past> getPastsList() {
        return pastsList;
    }

    public void setPastsList(List<Past> pastsList) {
        this.pastsList = pastsList;
    }

    // to string
    @Override
    public String toString() {
        return "Board{" +
                "nbrCellX=" + nbrCellX +
                ", nbrCellY=" + nbrCellY +
                ", map=" + Arrays.toString(map) +
                ", nbrOfPast=" + nbrOfPast +
                '}';
    }

    public void createBiDirectionalTable(List<String> stringMap) {
        for (int y = 0; y < stringMap.size(); y++) {
            for (int x = 0; x < stringMap.get(y).length(); x++) { // 33
                map[x][y] = stringMap.get(y).charAt(x) == '#' ? WALL : GROUND;
//                if (map[x][y] == WALL) {
//                    System.err.println("WALL = " + "x:" + x + "y:" + y);
                }
            }
        }
    }

