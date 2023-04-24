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
        System.out.println("""
                1. View contacts
                2. Add a new contact
                3. Search a contact by name
                4. Delete an existing contact
                5. Exit
                Enter a number option: 
                """);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();

    }

    public static void userChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                try {
                    List<String> allFiles = Files.readAllLines(Paths.get("data", "contacts.txt"));
                    System.out.println(allFiles);
                } catch(IOException e) {
                    e.printStackTrace();
                }
                userChoice(mainMenu());
                break;

            case 2:
                ContactFunctionality.addPerson();
                break;
            case 3:
                ContactFunctionality.searchPerson();
                break;
            case 4:
                ContactFunctionality.deletePerson();
                break;
            case 5:
                System.out.println("Have a great day, goodbye!");

        }
    }

    public void printContacts() throws IOException {
        try {
            List<String> allFiles = Files.readAllLines(dataFile);
            System.out.println(allFiles);
        } catch(IOException e) {
            e.printStackTrace();
        }

        userChoice(mainMenu());
    }

    public static void addPerson() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter last name: ");
        String lastName = sc.nextLine();
        System.out.println("Enter phone number with dashes");
        String phoneNumber = sc.nextLine();
        try {
            Files.write(
                    Paths.get("data", "contacts.txt"),
            Arrays.asList(firstName + " " + lastName + " " + phoneNumber),
            StandardOpenOption.APPEND
            );

        } catch(IOException e) {
            e.printStackTrace();
        }

        userChoice(mainMenu());
    }

    public static void searchPerson() throws IOException {
        System.out.println("Enter the person would you like to search: ");
        Scanner sc = new Scanner(System.in);
        String search = sc.nextLine();
        List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));
        List<String> newList = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(search)) {
                newList.add(line);
            }
        }
        for (String line : newList) {
            System.out.println(line);
        }

        userChoice(mainMenu());

    }

    public static void deletePerson() throws IOException {
        System.out.println("Enter the person would you like to delete: ");
        Scanner sc = new Scanner(System.in);
        String delete = sc.nextLine();
        List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));
        List<String> newList = new ArrayList<>();

        for (String line : lines) {
            if (!line.contains(delete)) {
                newList.add(line);
            }
        }

        try {
            Files.write(Paths.get("data", "contacts.txt"), newList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        userChoice(mainMenu());
    }
}
