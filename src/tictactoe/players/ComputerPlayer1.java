package tictactoe.players;

import tictactoe.game.Field;

public class ComputerPlayer1 {

    private char marker;

    /**
     * Creates a new player with the following strategy: Player uses next free field (1-9)
     *
     * @param marker The marker of the player, e.g. 'X' or 'O'
     */
    public ComputerPlayer1(char marker) {
        this.marker = marker;
    }

    /**
     * Player sets the marker to a position in the field. He uses the next free field (1-9)
     *
     * @param field Field to set the marker to.
     */
    public void nextMove(Field field) {
        for (int i = 1; i <= 9; i++) {
            if (field.setMarker(i, marker)) {
                break;
            }
        }
    }
}
