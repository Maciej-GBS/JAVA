public class Matrix {
	double[] data;
	int rows;
	int cols;

	public Matrix(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
		data = new double[rows*cols];
	}
}