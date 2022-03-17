import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> l1= List.of(2,1,1,0,1,0,0);
        List<Integer> l2= List.of(6,2,1,1,0,0,1);
        List<Integer> l3=List.of(1,-1,1,1,0,1,0);


        Slau slau = Slau.createSlau(List.of(l1,l2,l3));
        System.out.println(slau);
        slau.swap(0,0);
        System.out.println(slau);
    }
}
