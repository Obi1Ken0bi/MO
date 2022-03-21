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
        long positiveNumerator = Math.abs(this.numerator);
        long positiveDenominator = Math.abs(this.denominator);
        long limit = Math.min(positiveNumerator, positiveDenominator);
        if (this.numerator == 0) {
            this.denominator = 1L;
        }
        if (Math.abs(this.numerator) == Math.abs(this.denominator)) {
            this.numerator /= Math.abs(this.numerator);
            this.denominator /= Math.abs(this.denominator);
        }
        for (long i = 2; i <= limit; i++) {
            if (positiveNumerator % i == 0 && positiveDenominator % i == 0) {
                //this.numerator /= i;
                //this.denominator /= i;

                positiveNumerator /= i;
                positiveDenominator /= i;

                //positiveNumerator=Math.abs(this.numerator);
                //positiveDenominator=Math.abs(this.denominator);
                limit = Math.min(this.numerator, this.denominator);
            }
        }
        var sign = Math.signum(this.numerator) * Math.signum(this.denominator);
        this.numerator = positiveNumerator * (long) sign;
        this.denominator = positiveDenominator;
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

    private String getUnicodeSupFromNumber(Long number) {
        StringBuilder result = new StringBuilder();
        String temp = String.valueOf(Math.abs(number));
        for (int i = temp.length() - 1; i >= 0; i--) {
            char aboba = temp.charAt(i);
            switch (aboba) {
                case '1' -> result.append("\u00b9");
                case '2' -> result.append("\u00b2");
                case '3' -> result.append("\u00b3");
                case '4' -> result.append("\u2074");
                case '5' -> result.append("\u2075");
                case '6' -> result.append("\u2076");
                case '7' -> result.append("\u2077");
                case '8' -> result.append("\u2078");
                case '9' -> result.append("\u2079");
            }
        }
        return result.toString();
    }

    private String getUnicodeSubFromNumber(Long number) {
        StringBuilder result = new StringBuilder();
        String temp = String.valueOf(Math.abs(number));
        String base = "208";
        for (int i = temp.length() - 1; i >= 0; i--) {
            String aboba = base + temp.charAt(i);
            result.append((char) Integer.parseInt(aboba, 16));
        }
        return result.toString();
    }

    @Override
    public String toString() {
        String sign = " ";
        float numSign = Math.signum(this.denominator);

        float denumSign = Math.signum(this.numerator);
        float multSign = numSign * denumSign;
        boolean isNegative = multSign < 0;
        if (isNegative) {
            sign = "-";
        }

        if (numerator == 0)
            return " 0";
        if (denominator == 0)
            return "";
        if (denominator == 1) {
            return " " + numerator;
        }
        if (denominator == -1) {
            return String.valueOf((numerator > 0 ? " " + numerator : -numerator));
        }
        String num = getUnicodeSupFromNumber(numerator);
        String denum = getUnicodeSubFromNumber(denominator);
        return sign +
                num +
                "/" +
                denum;

    }
}

