import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.StrictMath.abs;

class Utils {

    private static int GROUND = 0;
    private static int WALL = 1;

    public Past getClosedPastWithValueOne(List<Past> list1Pats, Pac pac) {
        int dist = 10000;
        int posBestPastOnList = 0;
        Past myPast = new Past(pac.getPosX(), pac.getPosY(), -1);

        for (int i = 0; i < list1Pats.size(); i++) {
            int calDist = this.distanceFromCell(list1Pats.get(i), myPast);
            if (calDist < dist) {
                dist = calDist;
                posBestPastOnList = i;
            }
        }
//        System.err.println("best closed one value= " + listWithPastOne.get(posBestPastOnList).toString());
        return list1Pats.get(posBestPastOnList);
    }

    public Past getClosedPastWithValueTen(List<Past> list10Pasts, Pac pac) {
        int dist = 10000;
        int posBestPastOnList = 0;
        Past myPast = new Past(pac.getPosX(), pac.getPosY(), -1);

        for (int i = 0; i < list10Pasts.size(); i++) {
            int calDist = this.distanceFromCell(list10Pasts.get(i), myPast);
            if (calDist < dist) {
                dist = calDist;
                posBestPastOnList = i;
            }
        }
//        System.err.println("best ten value= " + listWithPastTen.get(posBestPastOnList).toString());
        return list10Pasts.get(posBestPastOnList);
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

    public List<Past> getListPastTen(List<Past> allPastList, List<Pac> pacsList) {
        List<Past> listPastTen = new ArrayList<>();
        for (int i = 0; i < allPastList.size(); i++) {
            if (allPastList.get(i).getValue() == 10) {
                listPastTen.add(allPastList.get(i));
            }
        }
        return listPastTen;
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
        if (pac.getPosX() != board.getNbrCellX() - 1) {
            if (map[pac.getPosX() + 1][pac.getPosY()] == GROUND) {
                Cell cell = new Cell(pac.getPosX() + 1 , pac.getPosY(), "E");
                moveCell.add(cell);
            }
        }

        // South
        if (map[pac.getPosX()][pac.getPosY() + 1] == GROUND) {
            Cell cell = new Cell(pac.getPosX(), pac.getPosY() + 1, "S");
            moveCell.add(cell);
        }
        // West
        if (pac.getPosX() != 0) {
            if (map[pac.getPosX() - 1][pac.getPosY()] == GROUND) {
                Cell cell = new Cell(pac.getPosX() - 1, pac.getPosY(), "W");
                moveCell.add(cell);
            }
        }
        return moveCell;
    }

    public List<Past> removeLockedPast(List<Past> listPastIn, List<Cell> lockedCells) {
        List<Past> returnList = new ArrayList<>();
        returnList.addAll(listPastIn);
//        System.err.println("listPastIn: " + listPastIn.stream().count());
//        System.err.println("returnListSize: " + returnList.stream().count());

        if (lockedCells.size() > 0) {
            if (listPastIn.size() > 0) {
                for (int i = 0; i < lockedCells.size(); i++) {
                    for (int j = 0; j < returnList.size(); j++) {
                        if (lockedCells.get(i).getX() == returnList.get(j).getPastX() &&
                                lockedCells.get(i).getY() == returnList.get(j).getPastY()) {
                                listPastIn.remove(j);
                        }
                    }
                }
            }
            return listPastIn;

        } else {
            return listPastIn;
        }

    }

    public List<Cell> getLockedCell(List<Pac> pacsList) {
        List<Cell> lockedcells = new ArrayList<>();
        for (int i = 0; i < pacsList.size(); i++) {
            Cell cell = new Cell(pacsList.get(i).getPosX(), pacsList.get(i).getPosY(), null);
            lockedcells.add(cell);
        }
        return lockedcells;
    }
}
