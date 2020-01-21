import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrzystanekList {
    List<Przystanek> units = new ArrayList<>();

    public PrzystanekList() {}

    public PrzystanekList(Stream<Przystanek> adminUnitStream) {
        units = adminUnitStream.collect(Collectors.toList());
    }

    public void list(PrintStream out) {
        for (var a : units) {
            out.printf(a.toString());
        }
    }

    public void list(PrintStream out, int offset, int limit) {
        int i = 0;
        int printed = 0;
        for (var a : units) {
            if (i < offset) {
                i++;
                continue;
            }
            out.printf(a.toString());
            printed++;
            if (printed >= limit)
                break;
        }
    }

    public PrzystanekList sortInplace(Comparator<Przystanek> cmp) {
        units.sort(cmp);
        return this;
    }

    public PrzystanekList sort(Comparator<Przystanek> cmp) {
        var clone = new PrzystanekList();
        Collections.copy(clone.units, units);
        return clone.sortInplace(cmp);
    }

    public PrzystanekList filter(Predicate<Przystanek> pred) {
        return new PrzystanekList(units.stream().filter(pred));
    }

    public PrzystanekList filter(Predicate<Przystanek> pred, int limit) {
        return new PrzystanekList(units.stream().filter(pred).limit(limit));
    }

    public PrzystanekList filter(Predicate<Przystanek> pred, int offset, int limit) {
        return new PrzystanekList(units.stream().filter(pred).skip(offset).limit(limit));
    }
}
