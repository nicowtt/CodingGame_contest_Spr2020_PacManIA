import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

class Move {
    Utils utils = new Utils();
    List<Cell> lockedCells;


    /**
     * move to big value past first
     * then litle value pas
     * then random
     * Speed if possible
     * @param board
     * @param pacsList
     * @return
     */
    public String moveIA1(Board board) {
        String move = "";
        lockedCells = new ArrayList<>();

        List<Past> listPastTen = utils.getListPastTen(board.getPastsList(), board.getMyPac());
        List<Past> listPastOne = utils.getListPastOne(board.getPastsList());

        for (int i = 0; i < board.getMyPac().size(); i++) {

            // remove other locked cell from other pac
            List<Past> listPastTenWhitoutLocked = utils.removeLockedPast(listPastTen,lockedCells);
//            System.err.println("listPastTenWithoutLocked= " + listPastTenWhitoutLocked.toString() );
            // remove other locked cell from other pacs
            List<Past> listPastOneWithoutLocked = utils.removeLockedPast(listPastOne,lockedCells);

            // if there is 10 value past
            if (listPastTen.size() > 0 ) {
                Past bestPast = utils.getClosedPastWithValueTen(listPastTenWhitoutLocked, board.getMyPac().get(i));
                board.getMyPac().get(i).setLockedCell(new Cell(bestPast.getPastX(), bestPast.getPastY(), null));
                lockedCells.add(new Cell(bestPast.getPastX(), bestPast.getPastY(), null));
//                System.err.println("locked cell: " + pacsList.get(i).getLockedCell().toString());
                move += " MOVE " + board.getMyPac().get(i).getPacId() + " " + bestPast.getPastX() + " " + bestPast.getPastY() + "|";

                // check
                System.err.println("best 10 pac" + board.getMyPac().get(i).getPacId() + " =" + bestPast);

            } else if (listPastOne.size() > 0){
                //if there is 1 value past
                if (listPastOne .size() > 0) {
                    Past bestPast = utils.getClosedPastWithValueOne(listPastOneWithoutLocked, board.getMyPac().get(i));
                    board.getMyPac().get(i).setLockedCell(new Cell(bestPast.getPastX(), bestPast.getPastY(), null));
                    lockedCells.add(new Cell(bestPast.getPastX(), bestPast.getPastY(), null));

                    move += " MOVE " + board.getMyPac().get(i).getPacId() + " " + bestPast.getPastX() + " " + bestPast.getPastY() + "|";

                    // check
                    System.err.println("best 1 pac" + board.getMyPac().get(i).getPacId() + " =" + bestPast);
                }
            } else {
                move += this.randomMoveAvoidLastPosition(board, board.getMyPac().get(i));
            }
        }
        return move;
    }

    public String moveWithSpeed(List<Pac> pacsList) {
        String move = "";
        for (int i = 0; i < pacsList.size() ; i++) {
            move += " SPEED " + pacsList.get(i).getPacId() + "|";
        }
        return move;
    }

    public String randomMove(Board board, Pac myPac) {
        Random rand = new Random();
        String move = "";

        // random move where is possible
        System.err.println("pac " + myPac.getPacId() + " move aléatoirement");
        // list of future possible move
        List<Cell> listAllPossibleMove = utils.getListAllPossibleMove(board, myPac);
        // limit for random
        int max = listAllPossibleMove.size() - 1;
        int min = 0;
        int randomCellInt = rand.nextInt(max - min + 1) + min;
        if (listAllPossibleMove.size() > 0) {
            move += " MOVE " + myPac.getPacId() + " "
                    + listAllPossibleMove.get(randomCellInt).getX() + " "
                    + listAllPossibleMove.get(randomCellInt).getY() + "|";
        }
        return move;
    }

    public String randomMoveAvoidLastPosition(Board board, Pac myPac) {
        Random rand = new Random();
        String move = "";

        // random move where is possible
        System.err.println("pac " + myPac.getPacId() + " move aléatoirement");
        // list of future possible move
        List<Cell> listAllPossibleMove = utils.getListAllPossibleMove(board, myPac);
        // remove old position on this list
        if (listAllPossibleMove.size() > 1) {
            listAllPossibleMove.removeIf(cell -> (cell.getX() == myPac.getPreviousPos().getX()) && (cell.getY() == myPac.getPreviousPos().getY()));
        }
        // limit for random
        int max = listAllPossibleMove.size() - 1;
        int min = 0;
        int randomCellInt = rand.nextInt(max - min + 1) + min;
        System.err.println("nbr for random:" + listAllPossibleMove.stream().count());
        listAllPossibleMove.stream().forEach(cell -> System.err.println(cell.toString()));
        System.err.println("random pos:" + randomCellInt);

        if (listAllPossibleMove.size() > 0) {
            move += " MOVE " + myPac.getPacId() + " "
                    + listAllPossibleMove.get(randomCellInt).getX() + " "
                    + listAllPossibleMove.get(randomCellInt).getY() + "|";
        }
        return move;
    }

}
