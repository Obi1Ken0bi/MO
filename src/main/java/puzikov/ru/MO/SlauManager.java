package puzikov.ru.MO;

import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class SlauManager {
    private Slau slau;

    public void countFirst() {
        print();
        for (int k = 0; k < slau.getYList().size() - 1; k++) {


            System.out.print("Введите координаты следующего x: ");
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            slau.swapAndDestroy(i - 1, j - 1);
            print();
        }
    }

    public void countAllBased() {
        for (int i = 0; i < slau.getYList().size(); i++) {
            for (int j = 0; j < slau.getXList().size() - 1; j++) {

                slau.swap(i, j);
                for (int k = 0; k < slau.getYList().size(); k++) {
                    for (int l = 0; l < slau.getXList().size() - 1; l++) {
                        if (i != k && j != l || (i != l && k != j)) {
                            slau.swap(k, l);
                            print();
                            slau.swap(k, l);
                        }
                    }
                }
                slau.swap(i, j);


            }
            print();
        }


    }

    private void print() {
        System.out.println(slau);
    }

}
