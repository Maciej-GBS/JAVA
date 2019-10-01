import java.util.Scanner;

public class Pasha
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.printf("Zakres (2-inf): ");
		int n = scan.nextInt(), count = 0;
		if (n < 2)
			System.exit(1);
		if (n % 2 != 0)
			n -= 1;
		n /= 2;
		for (var i = 1; i < (n+1) / 2; i++)
		{
			if (i != (n-i))
			{
				System.out.printf("{%d, %d, %d, %d}\n", i, i, n-i, n-i);
				count++;
			}
		}
		System.out.printf("Ilosc: %d\n", count);
	}
}
