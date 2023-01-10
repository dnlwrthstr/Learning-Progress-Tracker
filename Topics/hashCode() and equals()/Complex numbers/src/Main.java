import java.util.Objects;

class ComplexNumber {

    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ComplexNumber)) {
            return false;
        }

        ComplexNumber complexNumber = (ComplexNumber) other;

        return re == complexNumber.re &&
                im == complexNumber.im;
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
}