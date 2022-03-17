import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Number {
    private int numerator;
    private int denominator;


    public Number(int numerator) {
        this.numerator = numerator;
        this.denominator=1;
    }
    public double getValue(){
        return (double) numerator/denominator;
    }
    public Number multiply(Number z){
        int num= this.numerator* z.getNumerator();
        int denum=this.denominator*z.getDenominator();
        return new Number(num,denum);
    }
    public Number multiply(Integer z){
        int num= this.numerator*z;
        int denum=this.denominator;
        return new Number(num,denum);
    }
    public Number plus(Integer z){
       int num= this.numerator+z;
       int denum=this.denominator;
        return new Number(num,denum);
    }
    public Number plus(Number z){
       int denum= this.denominator* z.getDenominator();
       int num= this.numerator+ z.numerator*z.getDenominator();
        return new Number(num,denum);
    }

    public void flip(){
        int temp=this.numerator;
        this.numerator=this.denominator;
        this.denominator=temp;
    }
    public Number divide(Number z){
       int num= this.numerator*z.getDenominator();
       int denum= this.denominator*z.getNumerator();
        return new Number(num,denum);
    }

    public Number divide(Integer z){
      int denum=  this.denominator*z;
      int num=this.numerator;
        return new Number(num,denum);
    }
    public static Number buildNumber(Number number){
        return new Number(number.getNumerator(),number.getDenominator());
    }
    public Number copyOf(){
        return new Number(this.numerator,this.getDenominator());
    }

    public void setAllFieldsLike(Number number){
        this.numerator= number.getNumerator();
        this.denominator= number.getDenominator();
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}

