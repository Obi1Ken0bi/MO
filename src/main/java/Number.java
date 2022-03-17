import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Number {
    private int numerator;
    private int denominator;


    public Number(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public double getValue() {
        return (double) numerator / denominator;
    }

    public Number multiply(Number z) {
        int num = this.numerator * z.getNumerator();
        int denum = this.denominator * z.getDenominator();
        Number number = new Number(num, denum);
        number.optimize();
        return number;
    }

    public Number multiply(Integer z) {
        int num = this.numerator * z;
        int denum = this.denominator;
        Number number = new Number(num, denum);
        number.optimize();
        return number;
    }

    public Number plus(Integer z) {
        int num = this.numerator + z;
        int denum = this.denominator;
        Number number = new Number(num, denum);
        number.optimize();
        return number;
    }

    public Number plus(Number z) {
        int denum = this.denominator * z.getDenominator();
        int num = this.numerator * z.getDenominator() + z.numerator * this.getDenominator();
        Number number = new Number(num, denum);
        number.optimize();
        return number;

    }

    private void optimize() {
        long limit = Math.min(numerator, denominator);
        if (numerator == 0) {
            denominator = 1;
        }
        if (Math.abs(numerator) == Math.abs(denominator)) {
            numerator /= Math.abs(numerator);
            denominator /= Math.abs(denominator);
        }
        for (long i = 2; i <= limit; i++) {
            if (numerator % i == 0 && denominator % i == 0) {
                numerator /= i;
                denominator /= i;
            }
        }
    }

    public void flip() {
        int temp = this.numerator;
        this.numerator = this.denominator;
        this.denominator = temp;
    }

    public Number divide(Number z) {
        int num = this.numerator * z.getDenominator();
        int denum = this.denominator * z.getNumerator();
        Number number = new Number(num, denum);
        number.optimize();
        return number;
    }

    public void setAllFieldsLike(Number number) {
        this.numerator = number.getNumerator();
        this.denominator = number.getDenominator();
    }

    @Override
    public String toString() {
        if (getValue() < 0)
            return String.valueOf(getValue());
        return " " + getValue();
    }
}

