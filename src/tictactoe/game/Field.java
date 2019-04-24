package tictactoe.game;

public class Field {

    private char[] field;

    /**
     * Creates a new field, filled with nine spaces ' '.
     */
    public Field() {
        field = new char[9];
        for (int i = 0; i < field.length; i++) {
            field[i] = ' ';
        }
    }

    /**
     * Adds the marker to the requested position
     *
     * @param position Position to add the marker (1-9)
     * @param marker    The marker to be added, e.g. 'X'
     * @return true, if the position was still available. If the position was already used, it is not set and false is returned.
     */
    public boolean setMarker(int position, char marker) {
        if (isValidMove(position)) {
            field[position - 1] = marker;
            return true;
        }
        return false;
    }

    /**
     * Returns the current value of the field for a position (1-9)
     *
     * @param position The requested position.
     * @return The value of this position: ' ' if its empty, the marker otherwise, e.g. 'X'.
     */
    public char getValueAt(int position) {
        return field[position - 1];
    }

    /**
     * Checks if there are already three similar markers in a row, e.G. "X X X" at positions 1, 5, and 9.
     *
     * @return true if there are three similar markers in a row.
     */
    public boolean threeMarkersInARow() {
        return equalMakers(0, 1, 2) || equalMakers(3, 4, 5) || equalMakers(6, 7, 8) || equalMakers(0, 3, 6) || equalMakers(1, 4, 7) || equalMakers(2, 5, 8) || equalMakers(2, 4, 6);
    }

    private boolean equalMakers(int a, int b, int c) {
        return field[a] == field[b] && field[b] == field[c] && field[a] != ' ';
    }

    private boolean isValidMove(int position) {
        return field[position - 1] == ' ';
    }

    /**
     * Checks if there are empty positions left in the field.
     *
     * @return true, if there is an empty position left, false otherwise.
     */
    public boolean fieldFull() {
        for (int i = 0; i < field.length; i++) {
            if (field[i] == ' ') {
                return false;
            }
        }
        return true;
    }
}
