import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Grab the pellets as fast as you can!
 **/
class Player {
    private Board board;
    private Pac pac;
    private Past past;
    private Move move;

    public static void main(String args[]) {
        new Player().run();
    }

    public void run() {
        board = new Board();
        move = new Move();

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
        board = new Board(width,height, new int[width][height], 0, null);
        board.createBiDirectionalTable(mapLines);

        // game loop
        while (true) {
            int myScore = in.nextInt();
            int opponentScore = in.nextInt();
            int visiblePacCount = in.nextInt(); // all your pacs and enemy pacs in sight
            List<Pac> pacsList = new ArrayList<>();
            for (int i = 0; i < visiblePacCount; i++) {
                int pacId = in.nextInt(); // pac number (unique within a team)
                boolean mine = in.nextInt() != 0; // true if this pac is yours
                int x = in.nextInt(); // position in the grid
                int y = in.nextInt(); // position in the grid
                String typeId = in.next(); // unused in wood leagues
                int speedTurnsLeft = in.nextInt(); // unused in wood leagues
                int abilityCooldown = in.nextInt(); // unused in wood leagues
                pac = new Pac(pacId,mine,x,y, speedTurnsLeft, abilityCooldown);
                if (pac.isMine()) { pacsList.add(pac); }
            }
            int visiblePelletCount = in.nextInt(); // all pellets in sight
            board.setNbrOfPast(visiblePelletCount);
            List<Past> pastsList = new ArrayList<>();
            for (int i = 0; i < visiblePelletCount; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int value = in.nextInt(); // amount of points this pellet is worth
                past = new Past(x,y,value);
                pastsList.add(past);
            }
            board.setPastsList(pastsList);

            // Write an action using System.out.println()
            String nextMove = move.moveIA1(board, pacsList);
            
            // *********** speed if possible ! ***********
            if (pacsList.get(0).getAbilityCooldown() == 0) {speed = true;}
            if (speed) {
                nextMove = move.moveWithSpeed(pacsList);
                speed = false;
            }

            System.out.println(nextMove); // MOVE <pacId> <x> <y>
        }
    }
}