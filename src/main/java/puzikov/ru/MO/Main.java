package puzikov.ru.MO;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<List<Long>> l1 = new ArrayList<>();
        try {
            FileReader fr = new FileReader("table.txt");
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                ArrayList<Long> collect = Arrays.stream(s.split(", "))
                        .map(Long::parseLong)
                        .collect(Collectors
                                .toCollection(ArrayList::new));
                l1.add(collect);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Slau slau = Slau.createSlau(l1);
//        List<Integer> l1 = new ArrayList<>(List.of(2, 1, 1, 0, 1, 0, 0));
//        List<Integer> l2 = new ArrayList<>(List.of(6, 2, 1, 1, 0, 0, 1));
//        List<Integer> l3 = new ArrayList<>(List.of(1, -1, 1, 1, 0, 1, 0));


        // Slau slau = Slau.createSlau(new ArrayList<>(List.of(l1, l2, l3)));
        SlauManager slauManager = new SlauManager(slau);
        slauManager.countFirst();
        System.out.println("Choose input type pls: \n 1)Manual\n 2)Auto(Beta)");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 2) {
            slauManager.countAllBased();
        } else {
            System.out.println(slau);
            while (true) {
                System.out.print("Введите координаты следующего x: ");
                int i = scanner.nextInt();
                int j = scanner.nextInt();
                slau.swap(i - 1, j - 1);
                System.out.println(slau);

            }
        }

//

    }
}
