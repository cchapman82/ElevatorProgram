package main;

import main.java.gui.ElevatorDisplay;
import main.java.projectCode.Building;
import main.java.projectCode.ElevatorController;
import main.java.projectCode.MultipleElevators;
import main.java.projectCode.People;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int multElev = 0;
    private static int numElev;
    private static int numFloor;
    private static People person = new People("Null", 1, 1);
    private static ArrayList<People> persons = new ArrayList<People>();
    private static ArrayList<Integer> active = new ArrayList<Integer>();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static ArrayList<Integer> runTest(ArrayList<People> people) {
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for(People p : people) {
            int num = person.addPerson(p);
            if(!ints.contains(num))
                ints.add(num);
            if(!ElevatorController.getInstance().getMultipleElevators(num).isAlive()) {
                ElevatorController.getInstance().getMultipleElevators(num).start();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException z) {
                z.printStackTrace();
            }
        }
        return ints;
    }

    public static int getNumFloor() {
        return numFloor;
    }

    public static int getNumElev() {
        return numElev;
    }

    public static People parsePerson(JSONObject object) {
        JSONObject o = (JSONObject) object.get("person");
        return new People( (String) o.get("name"), Integer.parseInt((String) o.get("origin")), Integer.parseInt((String) o.get("destination")));
    }

    public static void main(String[] args) {
        //parser to read json files
        JSONParser parser = new JSONParser();

        //read json file and get fields for numFloor and numElev
        try {
            System.out.println("Reading json file to get specs to build floors and elevators in building.");
            FileReader in = new FileReader("src/main/resources/Building.json");
            JSONObject o = (JSONObject) parser.parse(in);
            numFloor = Integer.parseInt( (String) o.get("numFloor"));
            numElev = Integer.parseInt( (String) o.get("numElev"));
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Number of floors in building is " + numFloor);
        System.out.println("Number of elevators in building is " + numElev);

        //initialize display
        ElevatorDisplay.getInstance().initialize(numFloor);

        for (int i = 1; i <= numElev; i++) {
            ElevatorDisplay.getInstance().addElevator(i, 1);
        }

        //make floors and elevators
        Building.getInstance().makeFloors(numFloor);
        ElevatorController.getInstance().makeElevators(numElev);

        //wait for building to be made
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //ask user what test to run
        Scanner scanner = new Scanner(System.in);
        boolean cont = true;
        while(cont) {
            System.out.println("Please enter the test you want to run\n '1' for Test 1 \n '2' for Test 2 \n '3' for Test 3" +
                    "\n '4' for Test 4 \n '5' to get Riders from new json file \n\nor\n '0' to exit program");
            int num = scanner.nextInt();
            switch(num) {
                case 1 :
                    try {
                        MultipleElevators.setTime(45);
                        FileReader in = new FileReader("src/main/resources/Test1.json");
                        Object o = parser.parse(in);
                        JSONArray peps = (JSONArray) o;
                        for (Object j : peps) {
                            persons.add(parsePerson( (JSONObject) j));
                        }
                        active = runTest(persons);
                        for (Integer i : active) {
                            ElevatorController.getInstance().getMultipleElevators(i).join();
                            ElevatorController.getInstance().replaceThread(i);
                        }
                        active.removeAll(active);
                        persons.removeAll(persons);
                        in.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ParseException p) {
                        p.printStackTrace();
                    } catch (IOException i) {
                        i.printStackTrace();
                    } catch (InterruptedException s) {
                        s.printStackTrace();
                    }
                    break;
                case 2 :
                    try {
                        FileReader in = new FileReader("src/main/resources/Test2.json");
                        MultipleElevators.setTime(70);
                        Object o = parser.parse(in);
                        JSONArray peps = (JSONArray) o;
                        for (Object j : peps) {
                            persons.add(parsePerson( (JSONObject) j));
                        }
                        active = runTest(persons);
                        for (Integer i : active) {
                            ElevatorController.getInstance().getMultipleElevators(i).join();
                            ElevatorController.getInstance().replaceThread(i);
                        }
                        active.removeAll(active);
                        persons.removeAll(persons);
                        in.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ParseException p) {
                        p.printStackTrace();
                    } catch (IOException i) {
                        i.printStackTrace();
                    } catch (InterruptedException s) {
                        s.printStackTrace();
                    }
                    break;
                case 3 :
                    MultipleElevators.setTime(60);
                    multElev = 1;
                    try {
                        FileReader in = new FileReader("src/main/resources/Test3.json");
                        Object o = parser.parse(in);
                        JSONArray peps = (JSONArray) o;
                        for (Object j : peps) {
                            persons.add(parsePerson( (JSONObject) j));
                        }
                        active = runTest(persons);
                        for (Integer i : active) {
                            ElevatorController.getInstance().getMultipleElevators(i).join();
                            ElevatorController.getInstance().replaceThread(i);
                        }
                        active.removeAll(active);
                        persons.removeAll(persons);
                        in.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ParseException p) {
                        p.printStackTrace();
                    } catch (IOException i) {
                        i.printStackTrace();
                    } catch (InterruptedException s) {
                        s.printStackTrace();
                    }
                    break;
                case 4 :
                    MultipleElevators.setTime(110);
                    multElev = 1;
                    try {
                        FileReader in = new FileReader("src/main/resources/Test4.json");
                        Object o = parser.parse(in);
                        JSONArray peps = (JSONArray) o;
                        for (Object j : peps) {
                            persons.add(parsePerson( (JSONObject) j));
                        }
                        active = runTest(persons);
                        for (Integer i : active) {
                            ElevatorController.getInstance().getMultipleElevators(i).join();
                            ElevatorController.getInstance().replaceThread(i);
                        }
                        active.removeAll(active);
                        persons.removeAll(persons);
                        in.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ParseException p) {
                        p.printStackTrace();
                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                    catch (InterruptedException s) {
                        s.printStackTrace();
                    }
                    break;
                case 5 :
                    try {
                        System.out.println("Please enter path for json file");
                        String fileName = scanner.nextLine();
                        FileReader in = new FileReader(fileName);
                        Object o = parser.parse(in);
                        JSONArray peps = (JSONArray) o;
                        for (Object j : peps) {
                            persons.add(parsePerson( (JSONObject) j));
                        }
                        active = runTest(persons);
                        for (Integer i : active) {
                            ElevatorController.getInstance().getMultipleElevators(i).join();
                            ElevatorController.getInstance().replaceThread(i);
                        }
                        active.removeAll(active);
                        persons.removeAll(persons);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ParseException p) {
                        p.printStackTrace();
                    } catch (IOException i) {
                        i.printStackTrace();
                    } catch (InterruptedException s) {
                        s.printStackTrace();
                    }
                    break;
                case 0 :
                    cont = false;
                    break;
                default :
                    System.out.println("Incorrect Response");
                    break;
            }
        }
        System.out.println("Thank You");
        System.exit(0);
    }
}

