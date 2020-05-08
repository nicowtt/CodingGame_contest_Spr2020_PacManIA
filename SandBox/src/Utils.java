import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;

class Utils {

    private static int GROUND = 0;
    private static int WALL = 1;

    public Past findOnePastWithValueTen(Board board) {
        Past bestPast = new Past();
        
        // find best past
        for (int i = 0; i < board.getPastsList().size(); i++) {
            if (board.getPastsList().get(i).getValue() == 10) {
                bestPast = board.getPastsList().get(i);
            }
        }
//        System.err.println("Find on big past: " + bestPast.toString());
        return bestPast;
    }

    public Past findBestPastWithValueTen(Board board, Pac pac) {
        Past bestPast = new Past();

        // find best past
        bestPast = getClosedPastWithValueTen(board, pac);
//        System.err.println("Find on big past: " + bestPast.toString());
        return bestPast;
    }

    public Past getClosedPastWithValueOne(Board board, Pac pac) {
        int dist = 10000;
        int posBestPastOnList = 0;
        Past myPast = new Past(pac.getPosX(), pac.getPosY(), -1);
        List<Past> listWithPastOne = this.getListPastOne(board.getPastsList());

        for (int i = 0; i < listWithPastOne.size(); i++) {
            int calDist = this.distanceFromCell(listWithPastOne.get(i), myPast);
            if (calDist < dist) {
                dist = calDist;
                posBestPastOnList = i;
            }
        }
//        System.err.println("best closed one value= " + listWithPastOne.get(posBestPastOnList).toString());

        return listWithPastOne.get(posBestPastOnList);
    }

    public Past getClosedPastWithValueTen(Board board, Pac pac) {
        int dist = 10000;
        int posBestPastOnList = 0;
        Past myPast = new Past(pac.getPosX(), pac.getPosY(), -1);
        List<Past> listWithPastTen = this.getListPastTen(board.getPastsList());

        for (int i = 0; i < listWithPastTen.size(); i++) {
            int calDist = this.distanceFromCell(listWithPastTen.get(i), myPast);
            if (calDist < dist) {
                dist = calDist;
                posBestPastOnList = i;
            }
        }
//        System.err.println("best ten value= " + listWithPastTen.get(posBestPastOnList).toString());
        return listWithPastTen.get(posBestPastOnList);
    }

    public int distanceFromCell(Past c, Past other){
        return abs(c.getPastX() - other.getPastX()) + abs(c.getPastY() - other.getPastY());
    }

    public List<Pac> getOnlyMyPac(List<Pac> pacList) {
        List<Pac> myPacList = new ArrayList<>();
        for (int i = 0; i < pacList.size(); i++) {
            if (pacList.get(i).isMine()) {
                myPacList.add(pacList.get(i));
            }
        }
        return myPacList;
    }

    public List<Past> getListPastTen(List<Past> allPastList) {
        List<Past> listPastTen = new ArrayList<>();
        for (int i = 0; i < allPastList.size(); i++) {
            if (allPastList.get(i).getValue() == 10) {
                listPastTen.add(allPastList.get(i));
            }
        }
        return  listPastTen;
    }

    public List<Past> getListPastOne(List<Past> allPastList) {
        List<Past> listPastOne = new ArrayList<>();
        for (int i = 0; i < allPastList.size(); i++) {
            if (allPastList.get(i).getValue() == 1) {
                listPastOne.add(allPastList.get(i));
            }
        }
        return  listPastOne;
    }

    public List<Cell> getListAllPossibleMove(Board board, Pac pac) {
        int[][] map = board.getMap();
        List<Cell> moveCell = new ArrayList<>();
        // North
        if (map[pac.getPosX()][pac.getPosY() - 1] == GROUND) {
            Cell cell = new Cell(pac.getPosX(), pac.getPosY() - 1, "N");
            moveCell.add(cell);
        }
        // East
        if (map[pac.getPosX() + 1][pac.getPosY()] == GROUND) {
            Cell cell = new Cell(pac.getPosX() + 1 , pac.getPosY(), "E");
            moveCell.add(cell);
        }
        // South
        if (map[pac.getPosX()][pac.getPosY() + 1] == GROUND) {
            Cell cell = new Cell(pac.getPosX(), pac.getPosY() + 1, "S");
            moveCell.add(cell);
        }
        // West
        if (map[pac.getPosX() - 1][pac.getPosY()] == GROUND) {
            Cell cell = new Cell(pac.getPosX() - 1, pac.getPosY(), "W");
            moveCell.add(cell);
        }
        return moveCell;
    }

}
