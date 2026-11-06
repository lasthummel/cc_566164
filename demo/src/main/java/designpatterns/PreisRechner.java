package designpatterns;

/**
 * PreisRechner
 */
public class PreisRechner {

    /*
    public double berechnePreisMitRabatt(double betrag) {
        return betrag * 0.9;
    }

    public double berechnePreisOhneRabatt(double betrag) {
        return betrag;
    }
    */

    private PreisStrategie strategie = new NormalpreisStrategie();

    public void setStrategie(PreisStrategie strategie) {
        this.strategie = strategie;
    }

    public double berechnePreis(double betrag) {
        return strategie.berechnePreis(betrag);
    }
}
