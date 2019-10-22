import static junit.framework.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class MatrixTest
{
	@org.junit.Test
	public void Matrix() {
		Matrix m = new Matrix(new double[][] {{3,4},{7,1}});
		Matrix o = new Matrix(2, 2);
		assertEquals(m.rows, o.rows);
		assertEquals(m.cols, o.cols);
	}

	@org.junit.Test
	public void asArray() {
		double d[][] = {{1,3}, {5,6,1,4}, {2,2}};
		Matrix m = new Matrix(d);
		double actual[][] = {{1,3,0,0}, {5,6,1,4}, {2,2,0,0}};
		double mAsArr[][] = m.asArray();
		assertArrayEquals(mAsArr, actual);
	}

	@org.junit.Test
	public void set() {
		Matrix m = new Matrix(3,2);
		double d[][] = {{2,0},{3,1},{4,0}};
		for (var i = 0; i < d.length; i++)
		{
			for (var j = 0; j < d[i].length; j++)
			{
				m.set(i,j,d[i][j]);
				assertEquals(m.get(i,j), d[i][j]);
			}
		}
	}

	@org.junit.Test
	public void testToString() {
		Matrix m = new Matrix(new double[][] {{3,4},{7,1}});
		String str = m.toString();
		assertEquals(str.chars().filter(c -> c == '[').count(), 3);
		assertEquals(str.chars().filter(c -> c == ']').count(), 3);
		assertEquals(str.chars().filter(c -> c == ',').count(), 3);
	}

	@org.junit.Test
	public void reshape() {
		Matrix m = new Matrix(5,5);
		try {
			m.reshape(7, 5);
			fail();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@org.junit.Test
	public void add() {
	}

	@org.junit.Test
	public void sub() {
	}

	@org.junit.Test
	public void mul() {
	}

	@org.junit.Test
	public void div() {
	}

	@org.junit.Test
	public void testAdd() {
	}

	@org.junit.Test
	public void testSub() {
	}

	@org.junit.Test
	public void testMul() {
	}

	@org.junit.Test
	public void testDiv() {
	}

	@org.junit.Test
	public void dot() {
		Matrix A = new Matrix(new double[][] {{1,3},{1,2},{0,1}}), B = new Matrix(new double[][] {{1,0},{0,1}});
		Matrix result = A.dot(B);
		double actual[] = {1,3, 2,5, 2,6};
		assertArrayEquals(result.data, actual, 1e-7);
	}
}
