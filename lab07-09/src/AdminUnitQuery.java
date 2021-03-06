import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class AdminUnitQuery {
	AdminUnitList src;
	Predicate<AdminUnit> p = a->true;
	Comparator<AdminUnit> cmp;
	int limit = Integer.MAX_VALUE;
	int offset = 0;

	/**
	* Ustawia listę jako przetwarzane źródło
	* @param src
	* @return this
	*/
	AdminUnitQuery selectFrom(AdminUnitList src){
		this.src = src;
		return this;
	}

	/**
	*
	* @param pred - ustawia predykat p
	* @return this
	*/
	AdminUnitQuery where(Predicate<AdminUnit> pred){
		p = pred;
		src.filter(pred, offset, this.limit);
		return this;
	}

	/**
	* Wykonuje operację p = p and pred
	* @param pred
	* @return this
	*/
	AdminUnitQuery and(Predicate<AdminUnit> pred){
		p = p.and(pred);
		return this;
	}

	/**
	* Wykonuje operację p = p or pred
	* @param pred
	* @return this
	*/
	AdminUnitQuery or(Predicate<AdminUnit> pred){
		p = p.or(pred);
		return this;
	}

	/**
	* Ustawia komparator cmp
	* @param cmp
	* @return this
	*/
	AdminUnitQuery sort(Comparator<AdminUnit> cmp){
		this.cmp = cmp;
		src.units.sort(cmp);
		return this;
	}

	/**
	* Ustawia limit
	* @param limit
	* @return this
	*/
	AdminUnitQuery limit(int limit){
		this.limit = limit;
		return this;
	}

	/**
	* Ustawia offset
	* @param offset
	* @return this
	*/
	AdminUnitQuery offset(int offset){
		this.offset = offset;
		return this;
	}

	/**
	* Wykonuje zapytanie i zwraca wynikową listę
	* @return przefiltrowana i opcjonalnie posortowana lista (uwzględniamy także offset/limit)
	*/
	AdminUnitList execute(){
		return where(p).sort(cmp).src;
	}
}
