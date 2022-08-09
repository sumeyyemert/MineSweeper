import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    int rowNum, colNum;
    String[][] arr, arr2;
    boolean isFinish = false;

    Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    public MineSweeper() {
        System.out.println("Oyuna hoşgeldiniz ! ");
        System.out.print("Satır sayısı giriniz : ");
        this.rowNum = scan.nextInt();
        System.out.print("Sütun sayısı giriniz : ");
        this.colNum = scan.nextInt();
        arr = new String[rowNum][colNum];
        arr2 = new String[rowNum][colNum];
    }

    public void run() {
        int row, col;
        prepare();
        System.out.println();
        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = " - ";
            }
        }
        print(arr);

        int bns =0;
        while (!isFinish) {
            System.out.println();

            System.out.print("Satır giriniz : ");
            row = scan.nextInt() - 1;
            System.out.print("Sütun giriniz : ");
            col = scan.nextInt() - 1;
            System.out.println();


            if (arr2[row][col] != " * ") {
                check(row, col);
                if(bns == 0) {
                    if(rowNum > 3 && colNum >3) {
                        bonus(row, col);
                    }
                    bns++;
                }

                // ********* Oyunun bitip bitmediğinin kontolü ********
                int a = 0;
                for (int k = 0; k < arr2.length; k++) {
                    for (int j = 0; j < arr2.length; j++) {
                        if (arr2[k][j] == " * ") {
                            a++;
                        }
                        if (arr[k][j] != " - ") {
                            a++;
                        }
                    }
                } if(a== (rowNum * colNum)) {
                    System.out.println("Kazandınız !");
                    result();
                    break;
                }
                //                ***********************


                print(arr);

            } else {
                System.out.println("Kaybettiniz !");
                result();
                isFinish = true;
            }
        }
    }

    public void prepare() {
        int count = 0;
        while (count != (int) (rowNum * colNum) / 4) {
            int rowRand = rand.nextInt(rowNum);
            int colRand = rand.nextInt(colNum);
            if (arr2[rowRand][colRand] != " * ") {
                arr2[rowRand][colRand] = " * ";
                count++;
            }
        }
    }

    public void print(String[][] arr2) {
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                System.out.print(arr2[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void check(int row, int col) {
        int a = 0;

        if ((row > 0) && (col > 0) && arr2[row - 1][col - 1] == " * ") {
            a++;
        }
        if ((row > 0) && arr2[row - 1][col] == " * ") {
            a++;
        }
        if ((row > 0) && (col < colNum - 1) && arr2[row - 1][col + 1] == " * ") {
            a++;
        }
        if ((col < colNum - 1) && arr2[row][col + 1] == " * ") {
            a++;
        }
        if ((row < rowNum - 1) && (col < colNum - 1) && arr2[row + 1][col + 1] == " * ") {
            a++;
        }
        if ((row < rowNum - 1) && arr2[row + 1][col] == " * ") {
            a++;
        }
        if ((row < rowNum - 1) && (col > 0) && arr2[row + 1][col - 1] == " * ") {
            a++;
        }
        if ((col > 0) && arr2[row][col - 1] == " * ") {
            a++;
        }
        arr[row][col] = " " + String.valueOf(a) + " ";

    }

    public void result() {
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr2[i][j] == " * ") {
                    arr[i][j] = " * ";
                } else {
                    check(i, j);
                }
            }
        }
        print(arr);
    }
    public void bonus(int row, int col) {
        int r,c;
        boolean isContinue = true;
        while (isContinue) {
            r = rand.nextInt(row + 1);
            c = rand.nextInt(col + 1);
            if ((r >= row - 1) && (c >= col - 1) && arr2[r][c] != " * ") {
                check(r, c);
                isContinue = false;
            }
        }
    }

}
