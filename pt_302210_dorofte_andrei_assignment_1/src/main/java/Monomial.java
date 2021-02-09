public class Monomial{
    private double coeficient;
    private double power;

    public Monomial(double coeficient, double power)
    {
        this.coeficient = coeficient;
        this.power = power;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public double getPower() {
        return power;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    public void setPower(double power) {
        this.power = power;
    }
}