package tictactoe.players;

import tictactoe.game.Field;

import java.util.Arrays;


public class ComputerPlayer3 {

    private char marker;

    /**
     * Creates a new player with the following strategy: idek
     *
     * @param marker The marker of the player, e.g. 'X' or 'O'
     */
    public ComputerPlayer3(char marker) {
        this.marker = marker;
    }

    private float calculateScore(Field field, int x, int y){

        //THE LOWER THE SCORE THE BETTER
        float score = 100f; //The start score is 100 --> You can subtract from score for good moves/situations
        //creates a board around the play field, so calculating is easier later
        byte[][] convertedArray = new byte[3][3];

        //Init board with 0 = Nothing 1 = Player 2 = Enemy -1 = outOfBounds
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if(field.getValueAt(row * 3 + column + 1) == ' '){
                    convertedArray[column][row] = 0;
                }else if(field.getValueAt(row * 3 + column + 1) == marker){
                    convertedArray[column][row] = 1;
                }
                else{
                    convertedArray[column][row] = 2;
                }
            }
        }
        convertedArray[x][y] = 1; //Simulates current move

        //GameDecider
        //Rows
        for (int row = 0; row < convertedArray.length; row++) {
            byte numberOfEnemy = 0;
            byte numberOfMe = 0;
            for (int column = 0; column < convertedArray[0].length; column++) {
                if(convertedArray[column][row] == 2){
                    numberOfEnemy++;
                }else if(convertedArray[column][row] == 1){
                    numberOfMe++;
                }
            }
            if(numberOfMe == 3){
                score -= 1000;
            }else if(numberOfEnemy >= 2 && numberOfMe == 0){
                score += 10.0;
            }else if(numberOfEnemy > 0 && numberOfMe == 0){
                score += 1.5;
            }else if(numberOfMe >= 2 && numberOfEnemy == 0){
                score -= 12.0; //This number is 12 and not 10 to prevent enemy player from winning (Move P: 1 KI: 5 P: 9 --> now answer with 4 or 6 and not in the corners!)
            }else if(numberOfMe > 0 && numberOfEnemy == 0){
                score -= 1.5;
            }else{ //if numberOfEnemy & numberOfMe is same or the row is full without a "winner"
                score += 0;
            }
        }

        //columns
        for (int column = 0; column < convertedArray[0].length; column++) {
            byte numberOfEnemy = 0;
            byte numberOfMe = 0;
            for (int row = 0; row < convertedArray.length; row++) {
                if(convertedArray[column][row] == 2){
                    numberOfEnemy++;
                }else if(convertedArray[column][row] == 1){
                    numberOfMe++;
                }
            }
            if(numberOfMe == 3){
                score -= 1000;
            }else if(numberOfEnemy >= 2 && numberOfMe == 0){
                score += 10.0;
            }else if(numberOfEnemy > 0 && numberOfMe == 0){
                score += 1.5;
            }else if(numberOfMe >= 2 && numberOfEnemy == 0){
                score -= 10.0;
            }else if(numberOfMe > 0 && numberOfEnemy == 0) {
                score -= 1.5;
            }else{ //if numberOfEnemy & numberOfMe is same or the row is full without a "winner"
                score += 0;
            }
        }

        //cross
        for (int j = 0; j < 2; j++) {
            byte numberOfEnemy = 0;
            byte numberOfMe = 0;
            for(int i = 0; i < convertedArray.length; i++){
                //the - 4 * j is used to mirror the axis on the y axis
                if(convertedArray[Math.abs(i - (2 * j))][i] == 2){
                    numberOfEnemy++;
                }else if(convertedArray[Math.abs(i - (2 * j))][i] == 1){
                    numberOfMe++;
                }
            }
            if(numberOfMe == 3){
                score -= 1000;
            }else if(numberOfEnemy >= 2 && numberOfMe == 0){
                score += 10.0;
            }else if(numberOfEnemy > 0 && numberOfMe == 0){
                score += 1.5;
            }else if(numberOfMe >= 2 && numberOfEnemy == 0){
                score -= 10.0;
            }else if(numberOfMe > 0 && numberOfEnemy == 0){
                score -= 1.5;
            }else{ //if numberOfEnemy & numberOfMe is same or the row is full without a "winner"
                score += 0;
            }
        }

        //TODO: Evtl. Boni


        /* DEBUG OUTPUT */
        /*for (int i = 0; i < convertedArray.length; i++) {
            for (int j = 0; j < convertedArray.length; j++) {
                System.out.print(convertedArray[j][i]);
            }
            System.out.println();
        }*/
        /* DEBUG OUTPUT END */


        if(score < 0){
            score = 0;
        }
        System.out.println("My Score is " + score);
        return score;
    }

    /**
     * Player sets the marker to a position in the field. He uses a random free field (1-9)
     *
     * @param field Field to set the marker to.
     */
    public void nextMove(Field field) {
        float[][] moveValues = new float[3][3];

        //inits array with occupied fields
        for (int y = 0; y < moveValues.length; y++) {
            for (int x = 0; x < moveValues[0].length; x++) {
                if(field.getValueAt(y * 3 + x + 1) != ' '){
                    moveValues[x][y] = -1;
                }else{
                    moveValues[x][y] = calculateScore(field, x, y);
                }
            }
        }

        /* DEBUG OUTPUT*/
        /*for (int i = 0; i < moveValues.length; i++) {
            for (int j = 0; j < moveValues.length; j++) {
                System.out.print(moveValues[j][i]);
            }
            System.out.println();
        }*/
        /* DEBUG OUTPUT END*/


        //THE LOWER THE SCORE THE BETTER
        float lowestScore = 2000;
        int bestPosition = -1;
        for (int y = 0; y < moveValues.length; y++) {
            for (int x = 0; x < moveValues[0].length; x++) {
                if(moveValues[x][y] < lowestScore && moveValues[x][y] >= 0){
                    lowestScore = moveValues[x][y];
                    bestPosition = y * 3 + x + 1;
                }
            }
        }

        System.out.println("I will set my marker at position: " + bestPosition + " it had the lowest position " + lowestScore);

        if(bestPosition == -1){
            System.out.println("Well that's embarrassing now... This text should never be displayed... Unless the board is full...\n" +
                    "But i assume, that the Framework around the game made sure to catch that case... So if you're reading\n" +
                    "this: Sorry! I messed up somewhere - please contact tictactoe@daota.de maybe somebody there can help you!");
        }else{
            field.setMarker(bestPosition, marker);
        }

    }
}
