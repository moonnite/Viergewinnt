public class Board {
    private int xSize;
    private int ySize;
    private int[][] board;

    public Board(int x, int y){
        xSize = x;
        ySize = y;
        board = new int[x][y];
    }

    public int[][] getBoard(){
        return board;
    }

    public int getxSize(){
        return xSize;
    }

    public int getySize(){
        return ySize;
    }

    public int getContent(int x, int y){
        return board[x][y];
    }

    public boolean checkDraw(){
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                if(board[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public int[] insertChip(int x, byte player){
        //convert input to correct board array index
        x--;
        //check for next empty cell in column
        for(int i = ySize-1; i>=0;i--){
            if (getContent(x,i) == 0){
                if(player == 0) board[x][i] = 1;
                if(player == 1) board[x][i] = 2;
                return new int[] {x, i};
            }
        }
        return null;
    }
}