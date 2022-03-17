import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Slau {
    private List<List<Number>> matrix;
    public Slau(int xNumber,int restrictionNumber){
        matrix=new ArrayList<>(0);
        for (int i = 0;i< restrictionNumber; i++) {
            matrix.add(new ArrayList<>(xNumber+1));
        }
    }



    public Slau(List<List<Number>> matrix){
        this.matrix=matrix;
    }

    public static Slau createSlau(List<List<Integer>> matrix) {
        List<List<Number>> numberMatrix= matrix.stream()
                .map(x->x.stream()
                        .map(Number::new)
                        .toList())
                .toList();
        return new Slau(numberMatrix);
    }

    @Override
    public String toString() {
        StringBuilder sr=new StringBuilder();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                sr.append(matrix.get(i).get(j).toString()+" ");
            }
            sr.append(System.lineSeparator());
        }
        return sr.toString();
    }
    public void swap(int row, int column){
        int newColumn=column+1;
        Number aKS= matrix.get(row).get(newColumn);
        aKS.flip();
        for (int i = 0; i < matrix.size(); i++) {
            if(i!=row){
            for (int j = 0; j < matrix.get(row).size(); j++) {
                if (j != newColumn) {
                    Number n = matrix.get(i).get(j);
                    Number opposite1 = matrix.get(i).get(newColumn);
                    Number opposite2 = matrix.get(row).get(j);
                    Number newN=n.multiply(aKS);
                    newN=newN.plus(opposite1.multiply(opposite2).multiply(-1));
                    newN=newN.divide(aKS);
                    n.setAllFieldsLike(newN);
                }
            }
        }
        }
        for (int i = 0; i < matrix.get(row).size(); i++) {
            if(i!=newColumn) {
                Number number = matrix.get(row).get(i);
                number.setAllFieldsLike(number.divide(aKS));
            }
        }
        for (int i = 0; i < matrix.size(); i++) {
            if(i!=row) {
                Number n = matrix.get(i).get(newColumn);
                n.setAllFieldsLike( n.divide(aKS));
                n.setAllFieldsLike(n.multiply(-1));
            }
        }

    }
    public void swapAndDestroy(int row, int column){

    }
}
