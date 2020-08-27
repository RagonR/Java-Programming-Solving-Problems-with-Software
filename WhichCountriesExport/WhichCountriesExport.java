/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for(CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);            
            }
        }
    }
    
    public String coyntrytIngo(CSVParser parser, String country) {
        for(CSVRecord record : parser) {
            String export = record.get("Country");
            if (export.contains(country)){
                String info = record.get("Country") + ": ";
                info += record.get("Exports") + ": ";
                info += record.get("Value (dollars)");
                return info;            
            }
        }
        return ((String)("NOT FOUND"));
    }
    
    public String listExportersTwoProducts (CSVParser parser, String exportItem1 , String exportItem2) {
        String info = new String();
        for(CSVRecord record : parser) {
            String export = record.get("Exports");          
            if (export.contains(exportItem1) && export.contains(exportItem2)){
                info += record.get("Country") + "\n";        
            }
        }
        if(info != null && !info.isEmpty())
            return info;
        else
            return ((String)("NOT FOUND"));
    }
    
    public int numberOfExporters (CSVParser parser, String exportItem) {
        int numberOfCountries = 0;
            for(CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)){
                numberOfCountries ++;           
            }
        }
        return numberOfCountries;
    }
    
    public String bigExporters (CSVParser parser, String setvalue) {
        String info = new String();
            for(CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > setvalue.length()){
                info += record.get("Country") + " ";
                info += record.get("Value (dollars)") + "\n";       
            }
        }
        if(info != null && !info.isEmpty())
            return info;
        else
            return ((String)("NOT FOUND"));
    }
    
    
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(coyntrytIngo(parser, "Nauru"));
        //System.out.println(listExportersTwoProducts(parser, "cotton", "flowers"));
        //System.out.println(numberOfExporters(parser, "cocoa"));
        System.out.println(bigExporters(parser, "$999,999,999,999"));
    }
}
