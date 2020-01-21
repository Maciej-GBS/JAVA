import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class PrzystanekQuery {
    PrzystanekList src;
    Predicate<Przystanek> p = x->true;
    Comparator<Przystanek> cmp;
    int limit = Integer.MAX_VALUE;
    int offset = 0;

    /**
     * Ustawia listę jako przetwarzane źródło
     * @param src
     * @return this
     */
    PrzystanekQuery selectFrom(PrzystanekList src){
        this.src = src;
        return this;
    }

    /**
     *
     * @param pred - ustawia predykat p
     * @return this
     */
    PrzystanekQuery where(Predicate<Przystanek> pred){
        p = pred;
        src.units.stream().filter(pred).skip(offset).limit(limit);
        return this;
    }

    /**
     * Wykonuje operację p = p and pred
     * @param pred
     * @return this
     */
    PrzystanekQuery and(Predicate<Przystanek> pred){
        p = p.and(pred);
        return this;
    }

    /**
     * Wykonuje operację p = p or pred
     * @param pred
     * @return this
     */
    PrzystanekQuery or(Predicate<Przystanek> pred){
        p = p.or(pred);
        return this;
    }

    /**
     * Ustawia komparator cmp
     * @param cmp
     * @return this
     */
    PrzystanekQuery sort(Comparator<Przystanek> cmp){
        this.cmp = cmp;
        src.sort(cmp);
        return this;
    }

    /**
     * Ustawia limit
     * @param limit
     * @return this
     */
    PrzystanekQuery limit(int limit){
        this.limit = limit;
        return this;
    }

    /**
     * Ustawia offset
     * @param offset
     * @return this
     */
    PrzystanekQuery offset(int offset){
        this.offset = offset;
        return this;
    }

    /**
     * Wykonuje zapytanie i zwraca wynikową listę
     * @return przefiltrowana i opcjonalnie posortowana lista (uwzględniamy także offset/limit)
     */
    PrzystanekList execute(){
        return where(p).sort(cmp).src;
    }
}
