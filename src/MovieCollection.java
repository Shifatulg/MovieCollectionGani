import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.IOError;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieCollection {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Movie> movies = new ArrayList<>();
    public MovieCollection() {
        readData();
        menu();
    }

    private void readData() throws FileNotFoundException {
        try {
            File myFile = new File("src\\movies.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String[] cast = splitData[1].split("\\|");
                String director = splitData[2];
                String overview = splitData[3];
                int runtime = Integer.parseInt(splitData[4]);
                double userRating = Double.parseDouble(splitData[5]);
                Movie movie = new Movie(title, cast, director, overview, runtime, userRating);
                movies.add(movie);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void menu() {

    }
}
