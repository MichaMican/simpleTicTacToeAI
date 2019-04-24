package tictactoe.players;

import tictactoe.game.Field;

import java.util.Random;

public class ComputerPlayer2 {

    private char marker;

    /**
     * Creates a new player with the following strategy: Player uses random free field (1-9)
     *
     * @param marker The marker of the player, e.g. 'X' or 'O'
     */
    public ComputerPlayer2(char marker) {
        this.marker = marker;
    }

    /**
     * Player sets the marker to a position in the field. He uses a random free field (1-9)
     *
     * @param field Field to set the marker to.
     */
    public void nextMove(Field field) {
        Random rand = new Random();
        while (true) {
            int i = rand.nextInt(9) + 1;
            if (field.setMarker(i, marker)) {
                break;
            }
        }
    }
}
