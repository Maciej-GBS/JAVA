import java.io.*;

public class Testy {
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		AdminUnitList ls = new AdminUnitList();
		ls.read("admin-units.csv");
		ls.list(System.out, 0, 5);
	}
}
