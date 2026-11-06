package designpatterns;

public class NormalpreisStrategie implements PreisStrategie {

    @Override
    public double berechnePreis(double betrag) {
        return betrag;
    }

}
