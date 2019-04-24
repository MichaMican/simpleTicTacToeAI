package tictactoe.players;

import tictactoe.game.Field;

import java.util.Scanner;

public class HumanPlayer {

    private char marker;

    /**
     * Creates a new player with the following strategy: Player uses next free field (1-9)
     *
     * @param marker The marker of the player, e.g. 'X' or 'O'
     */
    public HumanPlayer(char marker) {
        this.marker = marker;
    }

    /**
     * Player sets the marker to a position in the field. He uses the next free field (1-9)
     *
     * @param field Field to set the marker to.
     */
    public void nextMove(Field field) {
        System.out.println();
        Scanner sc = new Scanner(System.in); // object for scanner
        System.out.println("Enter where you want to put your point No: ");
        int input = -1;
        while(input == -1){
            input = sc.nextInt();
            if(input < 1 || input > 9) {
                System.out.println("That input was invalid - Please enter valid number (0 - 9)");
                input = -1;
            }else{
                if(input <= 3){
                    input += 6;
                }else if(input >= 7){
                    input -= 6;
                }
            }
        }

        field.setMarker(input, marker);

    }
}
