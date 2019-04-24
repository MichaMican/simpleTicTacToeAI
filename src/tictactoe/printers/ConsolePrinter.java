package tictactoe.printers;

import tictactoe.game.Field;

public class ConsolePrinter {

    /**
     * Prints the given field to the console output.
     *
     * @param field field to be printed.
     */
    public void print(Field field) {
        System.out.print("\n");
        System.out.print("\n  " + field.getValueAt(1) + " | " + field.getValueAt(2) + " | " + field.getValueAt(3));
        System.out.print("\n -----------");
        System.out.print("\n  " + field.getValueAt(4) + " | " + field.getValueAt(5) + " | " + field.getValueAt(6));
        System.out.print("\n -----------");
        System.out.print("\n  " + field.getValueAt(7) + " | " + field.getValueAt(8) + " | " + field.getValueAt(9));
    }

    /**
     * Prints the given text to the console output.
     *
     * @param text text to be printed.
     */
    public void print(String text) {
        System.out.print("\n\n" + text);
    }
}
