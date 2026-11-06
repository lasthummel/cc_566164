package designpatterns;

public class RabattStrategie implements PreisStrategie {

    @Override
    public double berechnePreis(double betrag) {
        return betrag * .9;
    }
}
