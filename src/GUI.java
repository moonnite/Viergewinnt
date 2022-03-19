import java.util.Scanner;

public class GUI implements GameIO
{
    Scanner sc;

    public GUI(){
         sc = new Scanner(System.in);
    }

    public String getInput(){
        return sc.next();
    }

    //board content: 0 = empty, 1 = red, 2 = yellow
    public void displayGameData(Board b,byte player) {

        //clear screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        //display player info
        if(player == 0) System.out.println("\u001B[91mRed's turn:");
        if(player == 1) System.out.println("\u001B[93mYellow's turn:");

        for (int i = 0; i < b.getySize(); i++) {
            for (int j = 0; j < b.getxSize(); j++) {
                int content = b.getContent(j, i);
                switch (content) {
                    case 0 -> {
                        System.out.print("\u001B[94m");
                        System.out.print("□");
                    }
                    case 1 -> {
                        System.out.print("\u001B[91m");
                        System.out.print("■");
                    }
                    case 2 -> {
                        System.out.print("\u001B[93m");
                        System.out.print("■");
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        for (int i = 0; i < b.getxSize(); i++) {
            System.out.print("\u001B[97m");
            System.out.print(i + 1 + " ");
        }
        System.out.println("\n");
    }

    public void wrongInput(){
        System.out.println("\u001B[36mIllegal move! Try again.");
    }

    public void displayWinner(byte winner){
        //display winner info
        if(winner == 0) System.out.println("\u001B[91mRed won!");
        if(winner == 1) System.out.println("\u001B[93mYellow won!");
    }

    public void displayDraw(){
        System.out.println("\u001B[91mD\u001B[93mr\u001B[91ma\u001B[93mw\u001B[91m!");
    }

}