import java.util.Scanner;

public class Fibo
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.printf("Zakres (1-45): ");
		int n = scan.nextInt();
		if (n < 1 || n > 45)
			System.exit(1);
		int[] tab = new int[n];
		tab[0] = 0;
		tab[1] = 1;
		for (var i = 0; i < n; i++)
		{
			if (i > 1)
				tab[i] = tab[i-1] + tab[i-2];
			System.out.printf("%d\n", tab[i]);
		}
	}
}
