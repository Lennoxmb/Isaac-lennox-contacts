
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactFunctionality {
    String directory = "data";
    String contactInfo = "contacts.txt";


    Path dataDirectory = Paths.get(directory);
    Path dataFile = Paths.get(directory, contactInfo);




    public static int mainMenu() {
        System.out.println((char)27 + "[35m" + "Hello! What would you like to do?\n" +
        (char)27 + "[36m" + """
                1. View contacts
                2. Add a new contact
                3. Search a contact by name
                4. Delete an existing contact
                5. Exit
                Enter a number option: 
                """);
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice > 5) {
            System.out.println("Not a valid number, try again.");
            mainMenu();
        }
        return choice;

    }



    public static void userChoice(int choice) throws IOException {
        switch (choice) {
            case 1 -> ContactFunctionality.printContacts();
            case 2 -> ContactFunctionality.addPerson();
            case 3 -> ContactFunctionality.searchPerson();
            case 4 -> ContactFunctionality.deletePerson();
            case 5 -> System.out.println("Have a great day, goodbye!");
        }
    }

    public static void printContacts() throws IOException {
        try {
            List<String> allFiles = Files.readAllLines(Paths.get("data", "contacts.txt"));
            System.out.println("Name             |   Phone number  |");
            System.out.println("------------------------------------");
            for(String line : allFiles){
                String name = line.substring(0, line.indexOf(" ", line.indexOf(" ") +1));
                String number = line.substring(line.indexOf(" ", line.indexOf(" ") +2));
                System.out.printf("%-17s|  %-15s|%n", name, number);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
        userChoice(mainMenu());
    }

    public static void addPerson() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter last name: ");
        String lastName = sc.nextLine();
        System.out.println("Enter phone number");
        String phoneNumber = sc.nextLine();
        String formattedPhone = "";
        if (phoneNumber.length() == 10) {
            formattedPhone = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6, 10);
        }
        if (phoneNumber.length() == 7) {
            formattedPhone = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7);
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));
            for(String line : lines){
                if(line.contains(firstName) && line.contains(lastName)) {
                    System.out.println((char)27 + "[31m" + "Error: There is already a contact matching " + firstName + " " + lastName);
                    System.out.println("Would you like to override? [y/N]");
                    String override = sc.nextLine();
                    if(override.equalsIgnoreCase("y")){
                        Files.write(
                                Paths.get("data", "contacts.txt"),
                                Arrays.asList(firstName + " " + lastName + " " + formattedPhone),
                                StandardOpenOption.APPEND);
                        System.out.println("Contact has been added!");
                        userChoice(mainMenu());

                    } else {
                        System.out.println("\n");
                        userChoice(mainMenu());
                    }
                }
            }
            Files.write(
                    Paths.get("data", "contacts.txt"),
            Arrays.asList(firstName + " " + lastName + " " + formattedPhone),
            StandardOpenOption.APPEND
            );
            System.out.println("Contact has been added!");
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
        userChoice(mainMenu());
    }

    public static void searchPerson() throws IOException {
        System.out.println("Enter the person would you like to search: ");
        Scanner sc = new Scanner(System.in);
        String search = sc.nextLine();
        List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));
        List<String> newList = new ArrayList<>();

        for (String line : lines) {
            if (line.toLowerCase().contains(search.toLowerCase())) {
                newList.add(line);
            }
        }
        for (String line : newList) {
            System.out.println("\n");
            System.out.println("Name             |   Phone number  |");
            System.out.println("------------------------------------");
            for(String el : newList){
                String name = el.substring(0, line.indexOf(" ", line.indexOf(" ") +1));
                String number = el.substring(line.indexOf(" ", line.indexOf(" ") +2));
                System.out.printf("%-17s|  %-15s|%n", name, number);
            }
            System.out.println("\n");
        }
        System.out.println("\n");
        userChoice(mainMenu());

    }

    public static void deletePerson() throws IOException {
        System.out.println("Enter the person would you like to delete: ");
        Scanner sc = new Scanner(System.in);
        String delete = sc.nextLine();
        List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));
        List<String> newList = new ArrayList<>();

        for (String line : lines) {
            if (!line.toLowerCase().contains(delete.toLowerCase())) {
                newList.add(line);
            } else {
                System.out.println(line.substring(0, line.indexOf(" ", line.indexOf(" ") +1)) + " has been deleted");
            }
        }

        try {
            Files.write(Paths.get("data", "contacts.txt"), newList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
        userChoice(mainMenu());
    }
}
