import java.util.*;

public class Matrix {
	double[] data;
	int rows;
	int cols;

	Matrix(Matrix m)
	{
		rows = m.rows;
		cols = m.cols;
		data = m.data;
	}

	public Matrix(double w, int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
		data = new double[rows*cols];
		for (var i = 0; i < data.length; i++)
			data[i] = w;
	}

	public Matrix(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
		data = new double[rows*cols];
	}

	public Matrix(double[][] d)
	{
		rows = d.length;
		cols = 0;
		for (var i = 0; i < rows; i++)
			if (cols < d[i].length)
				cols = d[0].length;
		data = new double[rows * cols];
		for (var r = 0; r < rows; r++)
		{
			for (var c = 0; c < d[r].length; c++)
				set(r, c, d[r][c]);
		}
	}

	public double[][] asArray()
	{
		double[][] array = new double[rows][cols];
		for (var i = 0; i < data.length; i++)
			array[i/rows][i%cols] = data[i];
		return array;
	}

	public double get(int r, int c)
	{
		return data[r*cols + c];
	}

	public void set(int r, int c, double value)
	{
		data[r*cols + c] = value;
	}

	public String toString()
	{
		StringBuilder buf = new StringBuilder();
		buf.append("[");
		for(var i = 0; i < rows; i++)
		{
			buf.append("[");
			for (var j = 0; j < cols; j++)
			{
				if (j > 0)
					buf.append(", ");
				buf.append(get(i, j));
			}
			if (i < rows-1)
				buf.append("],\n");
			else
			buf.append("]");
		}
		buf.append("]");
		return buf.toString();
	}

	public void reshape(int newR, int newC)
	{
		if (rows*cols != newR*newC)
			throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newR,newC));
		rows = newR;
		cols = newC;
	}

	public int[] shape()
	{
		return new int[]{rows, cols};
	}

	public Matrix add(Matrix m)
	{
		if (rows != m.rows || cols != m.cols)
			throw new RuntimeException(String.format("%d x %d matrix cannot be added to %d x %d",rows,cols,m.rows,m.cols));
		Matrix ext = new Matrix(m);
		for (var i = 0; i < data.length; i++)
			ext.data[i] += data[i];
		return ext;
	}

	public Matrix sub(Matrix m)
	{
		if (rows != m.rows || cols != m.cols)
			throw new RuntimeException(String.format("%d x %d matrix cannot be added to %d x %d",rows,cols,m.rows,m.cols));
		Matrix ext = new Matrix(m);
		for (var i = 0; i < data.length; i++)
			ext.data[i] -= data[i];
		return ext;
	}

	public Matrix mul(Matrix m)
	{
		if (rows != m.rows || cols != m.cols)
			throw new RuntimeException(String.format("%d x %d matrix cannot be multiplied with %d x %d",rows,cols,m.rows,m.cols));
		Matrix ext = new Matrix(m);
		for (var i = 0; i < data.length; i++)
			ext.data[i] *= data[i];
		return ext;
	}

	public Matrix div(Matrix m)
	{
		if (rows != m.rows || cols != m.cols)
			throw new RuntimeException(String.format("%d x %d matrix cannot be divided by %d x %d",rows,cols,m.rows,m.cols));
		Matrix ext = new Matrix(m);
		for (var i = 0; i < data.length; i++)
			ext.data[i] /= data[i];
		return ext;
	}

	public Matrix add(double w)
	{
		return add(new Matrix(w,rows,cols));
	}

	public Matrix sub(double w)
	{
		return sub(new Matrix(w,rows,cols));
	}

	public Matrix mul(double w)
	{
		return mul(new Matrix(w,rows,cols));
	}

	public Matrix div(double w)
	{
		return div(new Matrix(w,rows,cols));
	}

	public Matrix dot(Matrix m)
	{
		if (cols != m.rows)
			throw new RuntimeException(String.format("%d x %d matrix cannot be dot multiplied with %d x %d",rows,cols,m.rows,m.cols));
		Matrix ext = new Matrix(this.rows, m.cols);
		var j = 0;
		for (var c = 0; c < this.cols; c++)
		{
			var sum = 0.0;
			for (var r = 0; r < this.rows; r++)
			{
				for (var i = 0; i < m.cols; i++)
					sum += m.get(c, i) * get(r, c);
			}
			ext.set(j, c, sum);
			j++;
		}
		return ext;
	}

	public static void main(String[] args)
	{
		Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
		System.out.printf("Wielki test o macierzach\n");
		m.reshape(2, 8);
		System.out.printf("%s\n", m.toString());
		System.out.printf("%d, %d\n", m.shape()[0], m.shape()[1]);

		Matrix a = new Matrix(new double[][]{{1,2}, {2,1}}), b = new Matrix(new double[][]{{2,0}, {0,1}});
		System.out.printf("%s\n%s\n", a.toString(), b.toString());
		System.out.printf("%s\n", (a.dot(b)).toString());
	}
}
