import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Document {

    public static void CreateFiles() throws IOException {
        try {
            File myObj = new File("datos.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void WriteDocument(String account, String value){
        try {
            FileWriter myWriter = new FileWriter("datos.txt", true);
            myWriter.append(account + "," + value);
            myWriter.append("\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
