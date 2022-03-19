public class GameManager {

    private Board board;
    private GUI gui;

    //0 = redPlayer, 1 = yellowPlayer
    private byte currentPlayer;

    public GameManager(){
        board = new Board(7,6);
        gui = new GUI();
        startGame();
    }

    private void startGame(){
        nextMove();
    }

    private void resetGame(){

    }

    private void nextMove(){
        gui.displayGameData(board,currentPlayer);

        //get user input and check if valid
        String sInput = gui.getInput();
        while(!validateInput(sInput)){
            gui.wrongInput();
            sInput = gui.getInput();
        }

        int iInput = Integer.parseInt(sInput);
        int[] pos = board.insertChip(iInput,currentPlayer);

        //should never be called, as the input is already valid and insertChip should not return null :)
        if(pos == null) resetGame();

        if(checkVictory(pos[0],pos[1])){
            gui.displayGameData(board,currentPlayer);
            gui.displayWinner(currentPlayer);
            return;
        }
        if(board.checkDraw()){
            gui.displayGameData(board,currentPlayer);
            gui.displayDraw();
            return;
        }
        switchPlayer();
        nextMove();
    }

    //On call: switch currentPlayer between 0 and 1
    private void switchPlayer(){
        if(currentPlayer == 0) currentPlayer ++;
        else currentPlayer --;
    }

    private boolean checkVictory(int x, int y){
        int checkFor = board.getContent(x,y);
        //store x and y size of board and convert to board array index
        int xLen = board.getxSize()-1;
        int yLen = board.getySize()-1;

        //check down
        int count = 1;
        for(int i = y+1; i <= yLen; i++){
            if(board.getContent(x,i) == checkFor){
                count ++;
            }
            else{
                break;
            }
            if(count == 4) return true;
        }

        //check right & left
        count = 1;
        //right
        for(int i = x+1; i <= xLen; i++){
            if(board.getContent(i,y) == checkFor){
                count ++;
            }
            else{
                break;
            }
            if(count == 4) return true;
        }
        //left
        for(int i = x-1; i >= 0; i--){
            if(board.getContent(i,y) == checkFor){
                count ++;
            }
            else{
                break;
            }
            if(count == 4) return true;
        }

        //check diagonally left
        count = 1;
        //left up
        for(int i = 1; x-i > 0 && y-i > 0; i++){
            if(board.getContent(x-i,y-i) == checkFor){
                count ++;
            }
            else{
                break;
            }
            if(count == 4) return true;
        }
        //right down
        for(int i = 1; x+i <= xLen && y+i <= yLen; i++){
            if(board.getContent(x+i,y+i) == checkFor){
                count ++;
            }
            else{
                break;
            }
            if(count == 4) return true;
        }

        //check diagonally right
        count = 1;
        //right up
        for(int i = 1; x+i <= xLen && y-i >= 0; i++){
            if(board.getContent(x+i,y-i) == checkFor){
                count ++;
            }
            else{
                break;
            }
            if(count == 4) return true;
        }
        //left down
        for(int i = 1; x-i >= 0 && y+i <= yLen; i++){
            if(board.getContent(x-i,y+i) == checkFor){
                count ++;
            }
            else{
                break;
            }
            if(count == 4) return true;
        }
        return false;
    }

    public boolean validateInput(String s){

        int input;

        //if input not a number
        try {
             input = Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return false;
        }

        //if input out of board bounds
        if(!(input >= 1 && input <= board.getxSize())){
            return false;
        }

        //if selected column already filled
        if(board.getContent(input-1,0)!=0){
            return false;
        }

        //if input passed all checks -> valid input
        return true;
    }
}
