package tictactoe.game;

import tictactoe.players.ComputerPlayer1;
import tictactoe.players.ComputerPlayer2;
import tictactoe.players.ComputerPlayer3;
import tictactoe.players.HumanPlayer;
import tictactoe.printers.ConsolePrinter;

public class Game {
    private Field field;
    private ConsolePrinter printer;
    //private ComputerPlayer2 player1;
    private HumanPlayer player1;
    private ComputerPlayer3 player2;

    /**
     * Creates a new game with two players.
     */
    public Game() {
        field = new Field();
        printer = new ConsolePrinter();
        player1 = new HumanPlayer('X');
        //player1 = new ComputerPlayer2('X');
        player2 = new ComputerPlayer3('O');
    }

    /**
     * Runs the game
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    /**
     * Plays the game.
     */
    public void play() {
        printer.print("Neues Spiel:");
        printer.print(field);
        sleep();
        while (!field.fieldFull()) {
            player1.nextMove(field);
            printer.print("Spieler 1: ");
            printer.print(field);
            sleep();
            if (field.fieldFull()) {
                printer.print("Unentschieden!");
                break;
            }
            if (field.threeMarkersInARow()) {
                printer.print("Spieler 1 hat gewonnen!");
                break;
            }
            player2.nextMove(field);
            printer.print("Spieler 2: ");
            printer.print(field);
            sleep();
            if (field.fieldFull()) {
                printer.print("Unentschieden!");
                break;
            }
            if (field.threeMarkersInARow()) {
                printer.print("Spieler 2 hat gewonnen!");
                break;
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //OK
        }
    }
}
