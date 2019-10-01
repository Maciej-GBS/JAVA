import java.util.Scanner;
import java.util.Locale;

public class MainLocale
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in).useLocale(Locale.US);
		String s = scan.next();
		int i = scan.nextInt();
		double d = scan.nextDouble();
		System.out.printf(Locale.US, "Wczytano %s %d %f\n", s, i ,d);
	}
}
