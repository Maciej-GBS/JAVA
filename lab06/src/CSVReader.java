import java.io.*;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt;
    String[] current;
 
    /**
     *  
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */
    public CSVReader(String filename, String delimiter, boolean hasHeader) throws FileNotFoundException
    {
	this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename, String delimiter) throws FileNotFoundException
    {
        this(filename, delimiter, false);
    }

    public CSVReader(String filename) throws FileNotFoundException
    {
        this(filename, ",", false);
    }

    public CSVReader(Reader freader, String delimiter, boolean hasHeader) throws FileNotFoundException
    {
        this.reader = new BufferedReader(freader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader)
		parseHeader();
	else
		columnLabelsToInt = new HashMap<>();
    }

    void parseHeader() throws IOException
    {
	String line = reader.readLine();
	if (line == null)
		return;
	String[] header = line.split(delimiter);
        columnLabelsToInt = new HashMap<>(header.length);
	for (int i = 0; i < header.length; i++)
	{
		columnLabels.add(header[i]);
		columnLabelsToInt.put(header[i], i);
	}
    }

    public boolean next() throws IOException
    {
	String line = reader.readLine();
	if (line == null || line.length() < 1)
		return false;
	System.out.printf("DEBUG : %s\n", line);
	current = line.split(delimiter);
	return true;
    }

    public String get(int index)
    {
	    return current[index];
    }

    public String get(String index)
    {
	    return current[columnLabelsToInt.get(index)];
    }

    public List<String> getColumnLabels()
    {
	    return columnLabels;
    }

    public int getRecordLength()
    {
	    return current.length;
    }

    public boolean isMissing(int index)
    {
	    String val = get(index);
	    return val.length() > 0;
    }

    public boolean isMissing(String index)
    {
	    return isMissing(columnLabelsToInt.get(index));
    }

    public int getInt(int index)
    {
	    return 0;
    }

    public static void main(String[] args) throws IOException
    {
	    CSVReader reader = null;
	    try {
		    reader = new CSVReader("titanic-part.csv", ",", true);
	    }
	    catch (FileNotFoundException e) { e.printStackTrace(); }
	    if (reader == null)
		    return;
	    while (reader.next())
	    {
		    String id = reader.get("PassengerId");
		    String name = reader.get("Name");
		    String fare = reader.get("Fare");
		    System.out.printf("%s \t| %s \t| %s\n", id, name, fare);
	    }
    }
}

