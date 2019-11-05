public class Demo {
	public static void main(String[] args)
	{
		System.out.println("Testy");
		Variable x = new Variable("x", 2.0);
		Sum w = new Sum();
		w.add(2, new Power(x,3));
		w.add(new Power(x,2));
		w.add(-2, x);
		w.add(new Constant(7));
		System.out.println("f(x) = " + w.toString());
		System.out.println("f(2) = " + w.evaluate());
	}
}
