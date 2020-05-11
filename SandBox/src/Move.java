import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Move {
    Utils utils = new Utils();
    List<Cell> lockedCells;


    /**
     * move to big value past first
     * then litle value pas
     * then random with avoid last position (exception when there is a wall)
     * Speed if possible
     * @param board
     * @return
     */
    public String moveIA1(Board board) {
        String move = "";
        lockedCells = new ArrayList<>();
        boolean pacIsBlock = false;
        boolean availabilityCooldown = false;

        List<Past> listPastTen = utils.getListPastTen(board.getPastsList(), board.getMyPac());
        List<Past> listPastOne = utils.getListPastOne(board.getPastsList());

        for (int i = 0; i < board.getMyPac().size(); i++) {
            // ckeck
            if (board.getMyPac().get(i).getPreviousPos() != null) {
                pacIsBlock = utils.checkIfPacIsBlock(board.getMyPac().get(i));
            }
            availabilityCooldown = utils.checkIfAbilityCooldownIsOk(board.getMyPac().get(i));

            // remove other locked cell from other pac
            List<Past> listPastTenWhitoutLocked = utils.removeLockedPast(listPastTen,lockedCells);
            List<Past> listPastOneWithoutLocked = utils.removeLockedPast(listPastOne,lockedCells);

            // if there is 10 value past on the map
            if (listPastTen.size() > 0 ) {
                Past bestPast = utils.getClosedPastWithValueTen(listPastTenWhitoutLocked, board.getMyPac().get(i));
                board.getMyPac().get(i).setLockedCell(new Cell(bestPast.getPastX(), bestPast.getPastY()));
                lockedCells.add(new Cell(bestPast.getPastX(), bestPast.getPastY()));

                if (pacIsBlock) { move += this.randomMoveAvoidLastPosition(board, board.getMyPac().get(i)); }
                else if (availabilityCooldown){ move += this.speedOrSwitch(board, board.getMyPac().get(i));}
                else {move += " MOVE " + board.getMyPac().get(i).getPacId() + " " + bestPast.getPastX() + " "
                        + bestPast.getPastY() + "|"; }

                // check
//                System.err.println("best 10 pac" + board.getMyPac().get(i).getPacId() + " =" + bestPast);

            } else if (listPastOne.size() > 0){
                // if there is 1 value past on the map
                if (listPastOne .size() > 0) {
                    Past bestPast = utils.getClosedPastWithValueOne(listPastOneWithoutLocked, board.getMyPac().get(i));
                    board.getMyPac().get(i).setLockedCell(new Cell(bestPast.getPastX(), bestPast.getPastY()));
                    lockedCells.add(new Cell(bestPast.getPastX(), bestPast.getPastY()));

                    if (pacIsBlock) { move += this.randomMoveAvoidLastPosition(board, board.getMyPac().get(i)); }
                    else if (availabilityCooldown){ move += this.speedOrSwitch(board, board.getMyPac().get(i)); }
                    else {move += " MOVE " + board.getMyPac().get(i).getPacId() + " " + bestPast.getPastX() + " "
                            + bestPast.getPastY() + "|";}

                    // check
//                    System.err.println("best 1 pac" + board.getMyPac().get(i).getPacId() + " =" + bestPast);
                }
            } else {
                if (availabilityCooldown) { move += this.speedOrSwitch(board, board.getMyPac().get(i)); }
                else {
                    move += this.randomMoveAvoidLastPosition(board, board.getMyPac().get(i));
                }


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
//        System.err.println("nbr for random:" + listAllPossibleMove.stream().count());
//        listAllPossibleMove.stream().forEach(cell -> System.err.println(cell.toString()));
//        System.err.println("random pos:" + randomCellInt);

        if (listAllPossibleMove.size() > 0) {
            move += " MOVE " + myPac.getPacId() + " "
                    + listAllPossibleMove.get(randomCellInt).getX() + " "
                    + listAllPossibleMove.get(randomCellInt).getY() + "|";
        }
        return move;
    }

    public String speedOrSwitch(Board board, Pac myPac) {
        boolean oppClosed = false;
        int oppId = -1;
        String oppType = "";

        String speed = " SPEED " + myPac.getPacId() + "|";
        String toRock = " SWITCH " + myPac.getPacId() + " ROCK " + "|";
        String toScissor = " SWITCH " + myPac.getPacId() + " SCISSORS " + "|";
        String toPaper = " SWITCH " + myPac.getPacId() + " PAPER " + "|";

        // check if there is opponent pac on line
        // find Cell around my pac
        List<Cell> listAroundMyPac = utils.getListAllPossibleMove(board, myPac);
        // check position of ennemy
        for (int i = 0; i < board.getOpponentPac().size(); i++) {
            for (int j = 0; j < listAroundMyPac.size(); j++) {
                if ((listAroundMyPac.get(j).getX() == board.getOpponentPac().get(i).getPosX()) &&
                        (listAroundMyPac.get(j).getY() == board.getOpponentPac().get(i).getPosY())) {
                    oppClosed = true;
                    oppId = board.getOpponentPac().get(i).getPacId();
                    oppType = board.getOpponentPac().get(i).getTypeId();
                    System.err.println("opp pac" + oppId + " type " + oppType + " is on my possible move !!");
                }
            }
        }
        // change type
        if (oppClosed) {
            System.err.println("oppType:" + oppType);
            System.err.println("MyPacType:" + myPac.getTypeId());
            if ((oppType.equals("ROCK")) && (myPac.getTypeId().equals("SCISSORS"))) {
                System.err.println("myPac try to change paper... ");
                return toPaper;
            }
            else if ((oppType.equals("SCISSORS")) && (myPac.getTypeId().equals("PAPER"))) {
                System.err.println("myPac try to change to rock... ");

                return toRock;
            }
            else if ((oppType.equals("PAPER")) && (myPac.getTypeId().equals("ROCK"))) {
                System.err.println("myPac try to change rock... ");
                return toScissor;
            }
        }
        return speed;
    }


}
