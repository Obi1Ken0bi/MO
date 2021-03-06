package puzikov.ru.MO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Slau {
    private List<List<Number>> matrix;
    private List<String> xList;
    private List<String> yList;


    public Slau(List<List<Number>> matrix) {
        this.matrix = matrix;
    }

    public static Slau createSlau(List<List<Long>> matrix) {
        List<List<Number>> numberMatrix = matrix.stream()
                .map(x -> x.stream()
                        .map(Number::new)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .collect(Collectors.toCollection(ArrayList::new));
        Slau slau = new Slau(numberMatrix);
        slau.populateXList(matrix.get(0).size() - 1);
        slau.populateYList(matrix.size());

        return slau;
    }

    @Override
    public String toString() {
        StringBuilder sr = new StringBuilder();
        sr.append("    ");
        for (String s : xList) {
            sr.append(s).append("    ");

        }
        sr.append(System.lineSeparator());
        sr.append("______".repeat(xList.size()));
        sr.append(System.lineSeparator());
        for (int i = 0; i < matrix.size(); i++) {
            sr.append(yList.get(i)).append("|");
            for (int j = 0; j < matrix.get(i).size(); j++) {
                sr.append(matrix.get(i).get(j).toString()).append("   ");
                if (j == 0) {
                    sr.append("|");
                }
            }
            sr.append(System.lineSeparator());
        }
        return sr.toString();
    }

    public void swap(int row, int column) {
        int newColumn = column + 1;
        swapXY(row, column);
        Number aKS = matrix.get(row).get(newColumn);
        if (aKS.getValue() == 0) {
            return;
        }

        for (int i = 0; i < matrix.size(); i++) {
            if (i != row) {
                for (int j = 0; j < matrix.get(row).size(); j++) {
                    if (j != newColumn) {
                        Number n = matrix.get(i).get(j);
                        Number opposite1 = matrix.get(i).get(newColumn);
                        Number opposite2 = matrix.get(row).get(j);
                        Number newN = n.multiply(aKS);
                        Number opMultiply = opposite1.multiply(opposite2);
                        Number opMinusMultiply = opMultiply.multiply(-1);
                        newN = newN.plus(opMinusMultiply);
                        newN = newN.divide(aKS);
                        n.setAllFieldsLike(newN);
                    }
                }
            }
        }
        for (int i = 0; i < matrix.get(row).size(); i++) {
            if (i != newColumn) {
                Number number = matrix.get(row).get(i);
                number.setAllFieldsLike(number.divide(aKS));
            }
        }
        for (int i = 0; i < matrix.size(); i++) {
            if (i != row) {
                Number n = matrix.get(i).get(newColumn);
                n.setAllFieldsLike(n.divide(aKS));
                n.setAllFieldsLike(n.multiply(-1));
            }
        }
        aKS.flip();
        //cringe();

    }

    private void cringe() {
        for (int i = 0; i < yList.size() - 1; i++) {
            if (yList.get(i).equals(" 0")) {
                int z = 0;
                for (int j = 0; j < matrix.get(i).size(); j++) {
                    if (matrix.get(i).get(j).getValue() < 0) {
                        z++;
                    }
                }
                if (z == matrix.get(i).size()) {
                    for (int j = 0; j < matrix.get(i).size(); j++) {
                        matrix.get(i).set(j, matrix.get(i).get(j).multiply(-1));
                    }
                }
            }
        }
    }

    public void swapAndDestroy(int row, int column) {
        swap(row, column);
        for (List<Number> numbers : matrix) {
            numbers.remove(column + 1);
        }
        cringe();
        xList.remove(column + 1);
    }

    private void swapXY(int yCount, int xCount) {
        String temp = yList.get(yCount);
        yList.set(yCount, xList.get(xCount + 1));
        xList.set(xCount + 1, temp);
    }

    private void populateXList(int xCount) {
        xList = new ArrayList<>();
        xList.add("1");
        for (int i = 1; i < xCount + 1; i++) {
            xList.add("x" + i);
        }
    }

    private void populateYList(int yCount) {
        yList = new ArrayList<>();
        for (int i = 0; i < yCount - 1; i++) {
            yList.add(" 0");
        }
        yList.add(" L");
    }
}
