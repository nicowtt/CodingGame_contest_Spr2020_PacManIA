import javax.rmi.CORBA.Util;
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Grab the pellets as fast as you can!
 **/
class Player {
    private Board board;
    private Move move;
    private Utils utils;

    public static void main(String args[]) {
        new Player().run();
    }

    public void run() {
        board = new Board();
        move = new Move();
        utils = new Utils();

        boolean speed = true;

        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // size of the grid
        int height = in.nextInt(); // top left corner is (x=0, y=0)

        if (in.hasNextLine()) {
            in.nextLine();
        }
        // create biDirectional table for map
        List<String> mapLines = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            String line = in.nextLine();
            mapLines.add(line);
//            System.err.println(line);
        }
        // create new board
        board = new Board(width,height, new int[width][height], 0, null, new ArrayList<>(), new ArrayList<>());
        board.createBiDirectionalTable(mapLines);

        // game loop
        while (true) {
            int myScore = in.nextInt();
            int opponentScore = in.nextInt();
            int visiblePacCount = in.nextInt(); // all your pacs and enemy pacs in sight
            for (int i = 0; i < visiblePacCount; i++) {
                Pac pac = new Pac();
                int pacId = in.nextInt(); // pac number (unique within a team)
                pac.setPacId(pacId);
                boolean mine = in.nextInt() != 0; // true if this pac is yours
                pac.setMine(mine);
                int x = in.nextInt(); // position in the grid
                pac.setPosX(x);
                int y = in.nextInt(); // position in the grid
                pac.setPosY(y);
                String typeId = in.next(); // unused in wood leagues
                int speedTurnsLeft = in.nextInt(); // unused in wood leagues
                pac.setSpeedTurnsLeft(speedTurnsLeft);
                int abilityCooldown = in.nextInt(); // unused in wood leagues
                pac.setAbilityCooldown(abilityCooldown);
                if (pac.isMine()) { utils.addPacOnMyListPac(board, pac);}
            }
            utils.removeMyPacIfNotUpdated(board);
            int visiblePelletCount = in.nextInt(); // all pellets in sight
            board.setNbrOfPast(visiblePelletCount);
            List<Past> pastsList = new ArrayList<>();
            for (int i = 0; i < visiblePelletCount; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int value = in.nextInt(); // amount of points this pellet is worth
                Past past = new Past(x,y,value);
                pastsList.add(past);
            }
            board.setPastsList(pastsList);
            System.err.println("myPac on board: " + board.getMyPac().toString());


            // Write an action using System.out.println()
            String nextMove = move.moveIA1(board);

            // *********** speed if possible ! ***********
            if (board.getMyPac().get(0).getAbilityCooldown() == 0) {speed = true;}
            if (speed) {
                nextMove = move.moveWithSpeed(board.getMyPac());
                speed = false;
            }

            System.out.println(nextMove); // MOVE <pacId> <x> <y>
            // reset my pac
            utils.allMyPacUpdatedFalse(board);
        }
    }
}