import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

public class CsvToHtmlTable {
    public static void main(String[] args) {
        // print info and show user how to call the program if needed
        System.out.println("This program is tested only for UTF-8 files.");
        if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("-help") || args.length != 2) {
            System.out.println("java CsvToHtmlTable <input file> <output file>");
            System.out.println("Example: java CsvToHtmlTable nice.csv nice.html");
            System.exit(0);
        }
        
        String csvFile = args[0];
        String outputFile = args[1];
        
        // read lines of csv to a string array list
        List<String> lines = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //embrace <td> and <tr> for lines and columns
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, "<tr><td>" + lines.get(i) + "</td></tr>");
            lines.set(i, lines.get(i).replaceAll(",", "</td><td>"));
        }
        
        // embrace <table> and </table>
        lines.set(0, "<table border>" + lines.get(0));
        lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "</table>"); 
        
        // output result
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}