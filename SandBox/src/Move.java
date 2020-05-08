import java.util.List;

class Move {
    Utils utils = new Utils();

    /**
     * move to big value past first
     * then litle value pas
     * then random
     * @param board
     * @param pacsList
     * @return
     */
    public String moveIA1(Board board, List<Pac> pacsList) {
        String move = "";
        List<Pac> myPacList = utils.getOnlyMyPac(pacsList);

        for (int i = 0; i < myPacList.size(); i++) {
            Past bestPast = new Past();
            List<Past> listPastTen = utils.getListPastTen(board.getPastsList());
            List<Past> listPastOne = utils.getListPastOne(board.getPastsList());
            // if there is 10 value past
            if (listPastTen.size() > 0 ) {
                bestPast = utils.getClosedPastWithValueTen(board, myPacList.get(i));
                // check
                System.err.println("best 10 past for pac" + myPacList.get(i).getPacId() + " =" + bestPast);

                move += " MOVE " + myPacList.get(i).getPacId() + " " + bestPast.getPastX() + " " + bestPast.getPastY() + "|";
            } else if (listPastOne.size() > 0){
                //if there is 1 value past
                if (listPastOne .size() > 0) {
                    bestPast = utils.getClosedPastWithValueOne(board, myPacList.get(i));
                    // check
                    System.err.println("best 1 past for pac" + myPacList.get(i).getPacId() + " =" + bestPast);
                    move += " MOVE " + myPacList.get(i).getPacId() + " " + bestPast.getPastX() + " " + bestPast.getPastY() + "|";
                }
            } else {
                // random move where is possible
                System.err.println("pac " + myPacList.get(i).getPacId() + " move aléatoirement");
                return this.moveIfNoPast(board, myPacList.get(i));
            }
        }
        return move;
    }

    /**
     * Random move if no past !!!
     * @param board
     * @param pac
     * @return
     */
    public String moveIfNoPast(Board board, Pac pac) {
        // list of future possible move
        List<Cell> listAllPossibleMove = utils.getListAllPossibleMove(board, pac);
        if (listAllPossibleMove.size() > 0) {
            return "MOVE " + pac.getPacId() + " " + listAllPossibleMove.get(0).getX() + " " + listAllPossibleMove.get(0).getY() + "|";
        } else {
            return "";
        }
    }
}
