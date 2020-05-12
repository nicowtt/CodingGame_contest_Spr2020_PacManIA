import java.util.*;

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
            Cell cell = new Cell(pac.getPosX(), pac.getPosY() - 1);
            moveCell.add(cell);
        }
        // East
        if (pac.getPosX() != board.getNbrCellX() - 1) {
            if (map[pac.getPosX() + 1][pac.getPosY()] == GROUND) {
                Cell cell = new Cell(pac.getPosX() + 1 , pac.getPosY());
                moveCell.add(cell);
            }
        }

        // South
        if (map[pac.getPosX()][pac.getPosY() + 1] == GROUND) {
            Cell cell = new Cell(pac.getPosX(), pac.getPosY() + 1);
            moveCell.add(cell);
        }
        // West
        if (pac.getPosX() != 0) {
            if (map[pac.getPosX() - 1][pac.getPosY()] == GROUND) {
                Cell cell = new Cell(pac.getPosX() - 1, pac.getPosY());
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
            Cell cell = new Cell(pacsList.get(i).getPosX(), pacsList.get(i).getPosY());
            lockedcells.add(cell);
        }
        return lockedcells;
    }

    public void addPacOnMyListPac(Board board, Pac pacIn) {
        boolean pacIsPresent = false;
        Pac pacToUpdate;

        if (board.getMyPac().isEmpty()) {
//            System.err.println("first pass");
            pacIn.setUpdated(true);
            board.getMyPac().add(pacIn);
        } else {
            for (int i = 0; i < board.getMyPac().size(); i++) {
                if (board.getMyPac().get(i).getPacId() == pacIn.getPacId()) {
                    pacIsPresent = true;
                }
            }
            if (!pacIsPresent) { // add pac
//                System.err.println("ajout pac" + pacIn.getPacId());
                pacIn.setUpdated(true);
                board.getMyPac().add(pacIn);
            } else { // update pac
                for (int i = 0; i < board.getMyPac().size(); i++) {
                    if (board.getMyPac().get(i).getPacId() == pacIn.getPacId()) {
                        pacToUpdate = board.getMyPac().get(i);
                        this.updatePac(pacToUpdate, pacIn);
                    }
                }
            }
        }
    }

    public void addPacOnOpponentListPac(Board board, Pac pacIn) {
        boolean pacIsPresent = false;
        Pac pacToUpdate;

        if (board.getOpponentPac().isEmpty()) {
//            System.err.println("first pass");
            pacIn.setUpdated(true);
            board.getOpponentPac().add(pacIn);
        } else {
            for (int i = 0; i < board.getOpponentPac().size(); i++) {
                if (board.getOpponentPac().get(i).getPacId() == pacIn.getPacId()) {
                    pacIsPresent = true;
                }
            }
            if (!pacIsPresent) { // add pac
//                System.err.println("ajout pac" + pacIn.getPacId());
                pacIn.setUpdated(true);
                board.getOpponentPac().add(pacIn);
            } else { // update pac
                for (int i = 0; i < board.getOpponentPac().size(); i++) {
                    if (board.getOpponentPac().get(i).getPacId() == pacIn.getPacId()) {
                        pacToUpdate = board.getOpponentPac().get(i);
                        this.updatePac(pacToUpdate, pacIn);
                    }
                }
            }
        }
    }

    public Pac updatePac(Pac pacToUpdate, Pac newPac) {
        Cell previousPos = new Cell();
        pacToUpdate.setAbilityCooldown(newPac.getAbilityCooldown());
        pacToUpdate.setSpeedTurnsLeft(newPac.getSpeedTurnsLeft());
        pacToUpdate.setLockedCell(newPac.getLockedCell());
        pacToUpdate.setTypeId(newPac.getTypeId());
        // set previous position
        if (pacToUpdate.isTypeNotChange()) {
            previousPos.setX(pacToUpdate.getPosX());
            previousPos.setY(pacToUpdate.getPosY());
            pacToUpdate.setPreviousPos(previousPos);
            pacToUpdate.setTypeNotChange(false);
        }

        // new pos
        pacToUpdate.setPosX(newPac.getPosX());
        pacToUpdate.setPosY(newPac.getPosY());

        pacToUpdate.setUpdated(true);

        return pacToUpdate;
    }

    public void allMyPacUpdatedFalse(Board board) {
        for (Pac pac: board.getMyPac()) { pac.setUpdated(false); }
    }

    public void allOpponentPacUpdatedFalse(Board board) {
        for (Pac pac: board.getOpponentPac()) { pac.setUpdated(false); }
    }

    public void removeMyPacIfNotUpdated(Board board) {
        board.getMyPac().removeIf(pac -> !pac.isUpdated());
    }

    public void removeOpponentPacIfNotUpdated(Board board) {
        board.getOpponentPac().removeIf(pac -> !pac.isUpdated());
    }

    public boolean checkIfPacIsBlock(Pac pacIn) {
        Cell previouscell = new Cell(pacIn.getPreviousPos().getX(), pacIn.getPreviousPos().getY());
        Cell actualCell = new Cell(pacIn.getPosX(), pacIn.getPosY());

        if((previouscell.getX() == actualCell.getX()) &&
            previouscell.getY() == actualCell.getY()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfAbilityCooldownIsOk(Pac pacIn) {
        if (pacIn.getAbilityCooldown() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
