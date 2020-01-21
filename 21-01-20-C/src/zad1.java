import java.io.IOException;
import java.util.*;
import java.util.Comparator;

public class zad1 {
    static PrzystanekList przystanki = new PrzystanekList();

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("przystanki.csv", ";");
        while (reader.next()) {
            Przystanek nowy = new Przystanek();
            nowy.Lp = reader.getInt("Lp");
            nowy.Lon = reader.getDouble("Lon");
            nowy.Lat = reader.getDouble("Lat");
            nowy.Kilom = reader.get("Kilometraż");
            nowy.Droga = reader.get("Droga");
            nowy.Nazwa = reader.get("Nazwa");
            przystanki.units.add(nowy);
        }
        taskA();
        taskB();
    }

    public static void taskA() {
        PrzystanekQuery query = new PrzystanekQuery()
                .selectFrom(przystanki)
                .where(x->x.Droga.startsWith("P"));
        printList(query.execute().units);
    }

    public static void taskB() {
        PrzystanekQuery query = new PrzystanekQuery()
                .selectFrom(przystanki)
                .where(x->x.Lat >= 50.54 && x.Lat <= 50.62)
                .where(x->x.Lon >= 21.63 && x.Lon <= 21.73)
                .sort(Comparator.comparing(a -> a.Nazwa));
        printList(query.execute().units);
    }

    public static void printList(List<Przystanek> list) {
        for (Przystanek p: list) {
            System.out.printf("%s\n", p.toString());
        }
    }
}