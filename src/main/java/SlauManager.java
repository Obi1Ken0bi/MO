import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SlauManager {
    private Slau slau;

    public void countFirst() {
        print();
        for (int i = 0; i < slau.getYList().size(); i++) {
            slau.swapAndDestroy(i, i);
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
