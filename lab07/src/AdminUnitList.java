import java.io.*;
import java.util.*;

public class AdminUnitList {
	List<AdminUnit> units = new ArrayList<>();

	public void read(String filename) throws FileNotFoundException, IOException {
		CSVReader reader = new CSVReader(filename);
		//id,parent,name,admin_level,population,area,density,x1,y1,x2,y2,x3,y3,x4,y4
		while (reader.next())
		{
			AdminUnit au = new AdminUnit();
			au.name = reader.get("name");
			au.adminLevel = reader.getInt("admin_level");
			au.population = reader.getDouble("population");
			au.area = reader.getDouble("area");
			au.density = reader.getDouble("density");
			au.bbox = new BoundingBox();
			au.bbox.xmin = reader.getDouble("x1");
			au.bbox.xmax = reader.getDouble("x3");
			au.bbox.ymin = reader.getDouble("y1");
			au.bbox.ymax = reader.getDouble("y3");
			units.add(au);
		}
	}

	public void list(PrintStream out) {
		for (var a : units) {
			out.printf(a.toString());
		}
	}

	public void list(PrintStream out, int offset, int limit) {
		int i = 0;
		int printed = 0;
		for (var a : units) {
			if (i < offset) {
				i++;
				continue;
			}
			out.printf(a.toString());
			printed++;
			if (printed >= limit)
				break;
		}
	}

	public AdminUnitList selectByName(String pattern, boolean regex) {
		AdminUnitList output = new AdminUnitList();
		for (var a : units) {
			if (a.name.contains(pattern) || (regex && a.name.matches(pattern)))
				output.units.add(a);
		}
		return output;
	}
}
