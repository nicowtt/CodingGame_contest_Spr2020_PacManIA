import java.util.List;
import java.util.Random;

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
                Random rand = new Random();

                // random move where is possible
                System.err.println("pac " + myPacList.get(i).getPacId() + " move aléatoirement");
                // list of future possible move
                List<Cell> listAllPossibleMove = utils.getListAllPossibleMove(board, myPacList.get(i));
                // limit for random
                int max = listAllPossibleMove.size() - 1;
                int min = 0;
                int randomCellInt = rand.nextInt(max - min + 1) + min;
                if (listAllPossibleMove.size() > 0) {
                    move += " MOVE " + myPacList.get(i).getPacId() + " "
                            + listAllPossibleMove.get(randomCellInt).getX() + " "
                            + listAllPossibleMove.get(randomCellInt).getY() + "|";
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
}
