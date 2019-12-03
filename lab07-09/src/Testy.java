import java.io.*;

public class Testy {
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		AdminUnitList ls = new AdminUnitList();
		ls.read("admin-units.csv");
		System.out.println("Default:");
		ls.list(System.out, 0, 5);
		ls.sortInplaceByName();
		System.out.println("Sorted by name:");
		ls.list(System.out, 0, 5);
		System.out.println("Filtered:");
		ls.filter(a->a.name.startsWith("K")).list(System.out, 0, 5);
		System.out.println("Query:");
		AdminUnitQuery query = new AdminUnitQuery()
				.selectFrom(ls)
				//.where(a->a.area>1000)
				.where(a->a.name.startsWith("Sz"))
				.sort((a,b)->Double.compare(a.area,b.area))
				.limit(100);
		query.execute().list(System.out);
	}
}
