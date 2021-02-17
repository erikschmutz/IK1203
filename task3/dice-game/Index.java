import java.util.*;

class Index {

    public static void main(String[] args) {
        System.out.println("Hello, welcome to dice game!");
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        //
        int diceOne = 1;
        int diceTwo = 2;
        int diceThree = 3;

        int wins = 0;
        int loses = 0;

        int[] myAwsomeIntList = { 1, 2, 3, 4, 5, 6, 7, 8, 10 };

        for (int index = 0; index < myAwsomeIntList.length; index++) {
            myAwsomeIntList[index] = -myAwsomeIntList[index];
        }


    }
}