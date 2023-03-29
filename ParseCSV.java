import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ParseCSV{
    public static void main(String[] args) {

        List<Person> persons= parsePersonsFromCSV("/Users/deepkhajanchi/Documents/Projects/Practice/code.csv");
       for(Person p: persons){
           System.out.println(p);
       }   
    }

    private static List<Person> parsePersonsFromCSV(String fileName){
        List<Person> persons= new ArrayList<>();
        Path filepath= Paths.get(fileName);

        try(BufferedReader br= Files.newBufferedReader(filepath, StandardCharsets.US_ASCII)){
            String line= br.readLine();

            //while loop until the end
            while(line != null){
            
                //string split to parse a string array from the file
                String[] attr=  line.split(",");
                Person person= generatePerson(attr);

                persons.add(person);

                line= br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return persons;
    }
    private static Person generatePerson(String[] metadata){
        int code= Integer.parseInt(metadata[0]);
        String fname= metadata[1];
        String lname= metadata[2];
        long dob= Long.parseLong(metadata[3]);
        String street= metadata[4];
        String city= metadata[5];
        String state= metadata[6];

        //creating and returning person of this metadata
        return new Person(code, fname, lname, dob, street, city, state);
    }
}

class Person{
    private int code;
    private String fname;
    private String lname;
    private long dob;
    private String street;
    private String city;
    private String state;

    public Person(int code, String fname, String lname, Long dob, String street, String city, String state){
        this.code= code;
        this.fname= fname;
        this.lname= lname;
        this.dob= dob;
        this.street= street;
        this.city= city;
        this.state= state;
    }
    public int getCode(){
        return code;
    }
    public long getDOB(){
        return dob;
    }
    public String getFName(){
        return fname;
    }
    public String getLName(){
        return lname;
    }
    public String getStreet(){
        return street;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }

    public void setCode(int code){
        this.code= code;
    }
    public void setDOB(long dob){
        this.dob= dob;
    }
    public void setFname(String fname){
        this.fname= fname;
    }
    public void setLName(String lname){
        this.lname= lname;
    }
    public void setStreet(String street){
        this.street= street;
    }
    public void setCity(String city){
        this.city= city;
    }
    public void setState(String state){
        this.state=state;
    }

}
 /*
         *try (
            //parse csv
        Scanner sc = new Scanner(new File("/Users/deepkhajanchi/Documents/Projects/Practice/code.csv"))) {
            //Scanner sc= new Scanner(new File("/code.csv")); //relative path
            sc.useDelimiter(",");
            while(sc.hasNext()){
                System.out.println(sc.next());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
         * 
         */
