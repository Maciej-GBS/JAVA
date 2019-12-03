public class AdminUnit {
	String name;
	int adminLevel;
	double population;
	double area;
	double density;
	AdminUnit parent;
	BoundingBox bbox = new BoundingBox();

	public String toString() {
		return String.format("%s level:%d pop:%f area:%f den:%f\n", name, adminLevel, population, area, density);
	}
}
