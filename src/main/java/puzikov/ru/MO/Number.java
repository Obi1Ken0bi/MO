package puzikov.ru.MO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Number {
    private Long numerator;
    private Long denominator;


    public Number(Long numerator) {
        this.numerator = numerator;
        this.denominator = 1L;
    }


    public double getValue() {
        return (double) numerator / denominator;
    }

    public Number multiply(Number z) {
        long num = this.numerator * z.getNumerator();
        long denum = this.denominator * z.getDenominator();
        Number number = new Number(num, denum);
        number.optimize();
        return number;
    }

    public Number multiply(Integer z) {
        long num = this.numerator * z;
        Long denum = this.denominator;
        Number number = new Number(num, denum);
        number.optimize();
        return number;
    }

    public Number plus(Integer z) {
        long num = this.numerator + z;
        Long denum = this.denominator;
        Number number = new Number(num, denum);
        number.optimize();
        return number;
    }

    public Number plus(Number z) {
        long denum = this.denominator * z.getDenominator();
        long num = this.numerator * z.getDenominator() + z.numerator * this.getDenominator();
        Number number = new Number(num, denum);
        number.optimize();
        return number;

    }

    private void optimize() {
        long limit = Math.min(this.numerator, this.denominator);
        if (this.numerator == 0) {
            this.denominator = 1L;
        }
        if (Math.abs(this.numerator) == Math.abs(this.denominator)) {
            this.numerator /= Math.abs(this.numerator);
            this.denominator /= Math.abs(this.denominator);
        }
        for (long i = 2; i <= limit; i++) {
            if (this.numerator % i == 0 && this.denominator % i == 0) {
                this.numerator /= i;
                this.denominator /= i;
            }
        }
    }

    public void flip() {
        Long temp = this.numerator;
        this.numerator = this.denominator;
        this.denominator = temp;
    }

    public Number divide(Number z) {
        long num = this.numerator * z.getDenominator();
        long denum = this.denominator * z.getNumerator();
        Number number = new Number(num, denum);
        number.optimize();
        return number;
    }

    public void setAllFieldsLike(Number number) {
        number.optimize();
        this.numerator = number.getNumerator();
        this.denominator = number.getDenominator();
    }

    @Override
    public String toString() {
        if (getValue() < 0)
            return String.valueOf(getValue());
        return " " + getValue();
//        optimize();
//        return String.valueOf(this.numerator)+"/" +String.valueOf( this.denominator);
    }
}

