import java.io.PrintStream;

public class Przystanek {
    public int Lp;
    public String Nazwa, Droga, Kilom;
    public double Lat, Lon;

    public String toString() {
        return String.format("%d. %s, %s, %s (%f, %f)", Lp, Nazwa, Droga, Kilom, Lat, Lon);
    }
}