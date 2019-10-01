import java.util.Scanner;
import java.util.Locale;

public class MainLocale
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in).useLocale(Locale.US);
		System.out.printf("String: ");
		String s = scan.next();
		System.out.printf("Integer: ");
		int i = scan.nextInt();
		System.out.printf("Double: ");
		double d = scan.nextDouble();
		System.out.printf(Locale.US, "Wczytano %s %d %f\n", s, i ,d);
	}
}
