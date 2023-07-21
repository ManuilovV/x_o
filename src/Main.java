import java.util.Scanner;
import java.util.Random;
public class Main {


    public static void main(String[] args) {

        System.out.println("\u001B[32mЯкий ваш хід?\u001B[0m");
        boolean t = definitionFigure(), q, w = true;
        String [] p = {"1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String valueX0 = "X";
        System.out.println("\u001B[32mВиберіть рівень складності (1-3):\u001B[0m");
        int r = difficultyLevel();
        for (int i = 0; i < 9; i++) {
            q = true;
            if(w){
                outfield(p);
            }
            if(t){
                System.out.println("\n\u001B[32mВаш хід: \u001B[0m");
                int vP = choicePlayer();
                if(vP > 0 && vP < 10){
                    q = (checkStroke(p[vP-1]));
                    if(q){
                        p[vP-1] = valueX0;
                    }
                    else{
                        System.out.println("\n\u001B[31mЦе поле зайняте!\u001B[0m");
                        i = i-1;
                    }
                }
                else {
                    System.out.println("\n\u001B[31mТакого поля не існує! (тільки 1-9)\u001B[0m");
                    i--;
                    q = false;
                }
                checkWin(p, t);
                w = true;
                if(q){
                    t = false;
                }
                else {
                    t = true;
                }
            }
            else {
                int value0;
                if(r == 1){
                    value0 = random();
                }
                else if(r == 2){
                    value0 = botSecond1(p);
                }
                else {
                    value0 = botThird1(p);
                }

                q = (checkStroke(p[value0]));
                if(q){
                    System.out.println("\n\u001B[38;5;208mХід іншого гравця:\u001B[0m");
                    p[value0]="0";
                    w = true;
                }
                else{
                    w = false;
                    i = i-1;
                }
                checkWin(p, t);
                if(q){
                    t = true;
                }
                else {
                    t = false;
                }
            }
        }

        outfield(p);
        System.out.println("\n\u001B[33mНічия!\nКінець гри!\u001B[0m");
    }

    public static int random (){
        Random random = new Random();
        int value = random.nextInt(9);
        return value;
    }
    public static int difficultyLevel() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 1; i--){
            int value = scanner.nextInt();
            if(value > 0 && value < 4){
                return value;
            }
            else {
                System.out.println("\n\u001B[31mТакого рівня складності не існує! Тільки 1-3!\u001B[0m");
                System.out.println("\u001B[31mВиберіть знову:\u001B[0m");
            }
        }
        return 0;
    }
    public static boolean definitionFigure() {
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        if(value == 1){
            return true;
        }
        else {
            return false;
        }
    }
    public static int choicePlayer(){
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        return value;
    }
    public static boolean checkStroke(String value){
        if (value.equals("X") || value.equals("0")){
            return false;
        }
        else {
            return true;
        }

    }
    public static void checkWin (String p[], boolean t){
        for(int i = 0; i < 7; i+=3){
            if(p[i] == p[i+1]&& p[i+1] == p[i+2]){
                checkFinal(p, t);
            }
        }
        for(int i = 0; i < 3; i++){
            if(p[i] == p[i+3]&& p[i+3] == p[i+6]){
                checkFinal(p, t);
            }
        }
        if(p[0] == p[4] && p[4] == p[8])
        {
            checkFinal(p, t);
        }
        if(p[2] == p[4] && p[4] == p[6])
        {
            checkFinal(p, t);
        }
    }
    public static void checkFinal (String p[], boolean t){
        if(t){
            outfield(p);
            System.out.println("\n\u001B[32mВи перемогли!\nКінець гри!\u001B[0m");
            System.exit(0);

        }
        else{
            outfield(p);
            System.out.println("\n\u001B[38;5;208mВи програли!\nКінець гри!\u001B[0m");
            System.exit(0);
        }
    }
    public static void outfield(String p[]){
       System.out.println("\n\u001B[34mПоле:\n" + p[0] + "|" + p[1] + "|" + p[2] + "\n"
                                      + p[3] + "|" + p[4] + "|" + p[5] + "\n"
                                      + p[6] + "|" + p[7] + "|" + p[8] + "\u001B[0m");
    }
    public static int botSecond1 (String p[]){
        int v = 10;
        v = botSecond2(p, 0, 1, 2, "3", "1", "2", v);
        v = botSecond2(p, 3, 4, 5, "6", "4", "5", v);
        v = botSecond2(p, 6, 7, 8, "9", "7", "8", v);
        v = botSecond2(p, 0, 3, 6, "7", "1", "4", v);
        v = botSecond2(p, 1, 4, 7, "8", "2", "5", v);
        v = botSecond2(p, 2, 5, 8, "9", "3", "6", v);
        v = botSecond2(p, 0, 4, 8, "9", "1", "5", v);
        v = botSecond2(p, 2, 4, 6, "7", "3", "5", v);
        if(v != 10){
            return v;
        }
        else {
            int value = random();
            return value;
        }

    }
    public static int botSecond2 (String p[], int a, int b, int c, String a1, String a2, String a3, int v){

        if(p[a].equals(p[b]) && p[b].equals("X") && p[c].equals(a1)){
            return c;
        }
        if(p[c].equals(p[b]) && p[b].equals("X") && p[a].equals(a2)){
            return a;
        }
        if(p[a].equals(p[c]) && p[c].equals("X") && p[b].equals(a3)){
            return b;
        }
        return v;
    }
    public static int botThird1 (String p[]){
        int v = 10;
        v = botThird2(p, 0, 1, 2, "3", "1", "2", v);
        v = botThird2(p, 3, 4, 5, "6", "4", "5", v);
        v = botThird2(p, 6, 7, 8, "9", "7", "8", v);
        v = botThird2(p, 0, 3, 6, "7", "1", "4", v);
        v = botThird2(p, 1, 4, 7, "8", "2", "5", v);
        v = botThird2(p, 2, 5, 8, "9", "3", "6", v);
        v = botThird2(p, 0, 4, 8, "9", "1", "5", v);
        v = botThird2(p, 2, 4, 6, "7", "3", "5", v);
        if(v != 10){
            return v;
        }
        else {
            int value = botSecond1(p);
            return value;
        }
    }
    public static int botThird2 (String p[], int a, int b, int c, String a1, String a2, String a3, int v){
        if(p[a].equals(p[b]) && p[b].equals("0") && p[c].equals(a1)){
            return c;
        }
        if(p[c].equals(p[b]) && p[b].equals("0") && p[a].equals(a2)){
            return a;
        }
        if(p[a].equals(p[c]) && p[c].equals("0") && p[b].equals(a3)){
            return b;
        }
        return v;
    }
}

