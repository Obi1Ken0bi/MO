import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SlauManager {
    private Slau slau;
    public void countFirst(){
        print();
        for (int i = 0; i < slau.getYList().size(); i++) {
            slau.swapAndDestroy(i,i);
            print();
        }
    }
    public void countAllBased(){



    }
    private void print(){
        System.out.println(slau);
    }

}
