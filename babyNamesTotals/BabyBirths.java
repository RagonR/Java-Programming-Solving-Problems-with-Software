/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public int getRank (int year, String name, String gender) {
        int placeInFile = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                placeInFile ++;
                if(rec.get(0).equals(name)){
                    return placeInFile;
                }
            }
        }   
        return -1;
    }
    
    public String getName (int year, int rank, String gender) {
        String name = new String();
        int placeInFile = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                placeInFile ++;
                if(placeInFile == rank){
                    return rec.get(0);
                }
            }
        }   
        return "NO NAME";
    }
    
    public int getTotalBirthsRankedHigher ( int year, String name, String gender) {
        int birthsOfSelectedName = 0;
        int births = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                birthsOfSelectedName = Integer.parseInt(rec.get(2));
            }
        }
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(birthsOfSelectedName < Integer.parseInt(rec.get(2)) && rec.get(1).equals(gender)){
                births += Integer.parseInt(rec.get(2));
            }
        }
        return births;
    }
    
    public int getNumberOfGenderInFile ( int year, String gender) {
        int NumberOfGender = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                NumberOfGender ++;
            }
        }
        return NumberOfGender;
    }
    
    public void testgetNumberOfGenderInFile () {
        String gender = "M";
        int year = 1905;
        int NumberOfGender = getNumberOfGenderInFile(year, gender);
        System.out.println("Number of selected gender names in file "+ NumberOfGender);
    }
    
    
    public void testgetTotalBirthsRankedHigher () {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int totalBirthsRankedHigher = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total Births Ranked Higher = "+ totalBirthsRankedHigher);
    }
       
    public double getAverageRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double averageRank = 0;
        int amountOfSelectedFiles = 0;
        for (File f : dr.selectedFiles()) {
            int fileyear = Integer.parseInt(f.getName().substring(f.getName().indexOf("yob")+3, f.getName().indexOf("yob")+7));
            int rank = getRank(fileyear, name, gender);
            if(rank != -1){
                amountOfSelectedFiles ++;
                averageRank += rank;
            }
        }
        return ((double)averageRank/amountOfSelectedFiles);
    }
    
    public void testgetAverageRank () {
        String name = "Robert";
        String gender = "M";
        double averageRankYear = getAverageRank(name, gender);
        System.out.println("Average Rank for name "+ name + " was "+ averageRankYear);
    }
    
    public int yearOfHighestRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int topranking = -1;
        int topRankYear = 0;
        for (File f : dr.selectedFiles()) {
            int fileyear = Integer.parseInt(f.getName().substring(f.getName().indexOf("yob")+3, f.getName().indexOf("yob")+7));
            int rank = getRank(fileyear, name, gender);
            if(topranking == -1){
                topranking = rank;
                topRankYear = fileyear;
            }
            else if (topranking > rank && rank != -1){
                topranking = rank;
                topRankYear = fileyear;
            }
        }
        return topRankYear;
    }
    
    public void testyearOfHighestRank () {
        String name = "Mich";
        String gender = "M";
        int topRankYear = yearOfHighestRank(name, gender);
        System.out.println("Highest Rank for name "+ name + " was in year " + topRankYear);
    }

    public String whatIsNameInYear (String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        return getName(newYear, rank, gender);
    }
    
    public void testwhatIsNameInYear () {
        int year = 1972;
        int newYear = 2014;
        String name = "Susan";
        String gender = "F";
        String newname = whatIsNameInYear(name, year, newYear, gender);
        System.out.println(name + " born in " + year + " would be " + newname + " if she/he was born in " + newYear);
    }
    
    public void testgetName () {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        String name = getName(year, rank, gender);
        System.out.println("Name " + name + " is ranked " + rank + " in " + year);
    }
    
    public void testNameRank () {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int rank = getRank(year, name, gender);
        System.out.println("Name " + name + " is ranked " + rank + " in " + year);
    }
    
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
}
