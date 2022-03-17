import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Integer> l1 = new ArrayList<>(List.of(2, 1, 1, 0, 1, 0, 0));
        List<Integer> l2 = new ArrayList<>(List.of(6, 2, 1, 1, 0, 0, 1));
        List<Integer> l3 = new ArrayList<>(List.of(1, -1, 1, 1, 0, 1, 0));


        Slau slau = Slau.createSlau(new ArrayList<>(List.of(l1, l2, l3)));
        SlauManager slauManager=new SlauManager(slau);
        slauManager.countFirst();
        Scanner scanner=new Scanner(System.in);
        while (true){
            int i= scanner.nextInt();
            int j=scanner.nextInt();
            slau.swap(i,j);
            System.out.println(slau);

        }

//

    }
}
