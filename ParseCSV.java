import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.Period;

public class ParseCSV {

    public static int peopleAboveAge(List<Person> list, int age) {
        int count = 0;
        for (Person person : list) {
            if (person.getAge() >= age) {
                count++;
            }
        }
        return count;
    }

    public static int peopleLivingIn(List<Person> list, String city, String state) {
        int count = 0;
        for (Person person : list) {
            if (person.getCity().equalsIgnoreCase(city)
                    && person.getState().equalsIgnoreCase(state)) {
                count++;
            }
        }
        return count;
    }

    public static Map<String, Integer> countLastNames(List<Person> list) {
        Map<String, Integer> map = new HashMap<>();
        for (Person person : list) {
            String lName = person.getLName();
            map.putIfAbsent(lName, 0);
            map.put(lName, map.get(lName) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        List<Person> persons = parsePersonsFromCSV("code.csv");
        System.out.println("Number of people above 50: " + peopleAboveAge(persons, 50));
        System.out.println("Number of people living in Lowell, MA: " + peopleLivingIn(persons, "Lowell", "MA"));
        System.out.println("Number of people living in NASHUA, NH: " + peopleLivingIn(persons, "NASHUA", "NH"));
        //last name with count
        System.out.println("");
        Map<String, Integer> map = countLastNames(persons);
        for (String lName : map.keySet()) {
            System.out.printf("Count of People with last name \"%s\" is %d\n",
                    lName, map.get(lName));
        }
        //sort by code
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getCode() - o2.getCode();
            }
        });
        System.out.println("");
        for (Person p : persons) {
            System.out.println(p);
        }
    }

    private static List<Person> parsePersonsFromCSV(String fileName) {
        List<Person> persons = new ArrayList<>();
        Path filepath = Paths.get(fileName);
        try ( BufferedReader br = Files.newBufferedReader(filepath, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            //while loop until the end
            while ((line = br.readLine()) != null) {
                //string split to parse a string array from the file
                String[] attr = line.split(",");
                Person person = generatePerson(attr);
                persons.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons;
    }

    private static Person generatePerson(String[] metadata) {
        String date = metadata[3];
        int code = Integer.parseInt(metadata[0]);
        String fname = metadata[1];
        String lname = metadata[2];
        LocalDate dob = LocalDate.parse(String.format("%s-%s-%s",
                date.substring(0, 4),
                date.substring(4, 6),
                date.substring(6)));
        String street = metadata[4];
        String city = metadata[5];
        String state = metadata[6];
        //creating and returning person of this metadata
        return new Person(code, fname, lname, dob, street, city, state);
    }
}

class Person {

    private int code;
    private String fname;
    private String lname;
    private LocalDate dob;
    private String street;
    private String city;
    private String state;

    public Person(int code, String fname, String lname, LocalDate dob,
            String street, String city, String state) {
        this.code = code;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public int getAge() {
        LocalDate curDate = LocalDate.now();
        return Period.between(dob, curDate).getYears();
    }

    public int getCode() {
        return code;
    }

    public LocalDate getDOB() {
        return dob;
    }

    public String getFName() {
        return fname;
    }

    public String getLName() {
        return lname;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDOB(LocalDate dob) {
        this.dob = dob;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLName(String lname) {
        this.lname = lname;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Person{" + "code=" + code + ", fname=" + fname
                + ", lname=" + lname + ", dob=" + dob + ", street="
                + street + ", city=" + city + ", state=" + state + '}';
    }

}
