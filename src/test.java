import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class test {
    public static void main(String[] args) throws IOException {

        String directory = "data";
        String contactInfo = "contacts.txt";


        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, contactInfo);


        ContactFunctionality.userChoice(ContactFunctionality.mainMenu());




    }
}

