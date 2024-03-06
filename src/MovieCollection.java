import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class MovieCollection {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Movie> movies = new ArrayList<>();
    public MovieCollection() {
        readData();
        menu();
    }

    private void readData() {

        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
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

    public static void sortMovieList(ArrayList<Movie> movieList) {
        int n = movieList.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (movieList.get(j).getTitle().compareTo(movieList.get(min_idx).getTitle()) < 0) {
                    min_idx = j;
                }
            }
            Movie temp = movieList.get(i);
            movieList.set(i, movieList.get(min_idx));
            movieList.set(min_idx, temp);
        }
    }


    private ArrayList<Movie> searchTitles(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        ArrayList<Movie> matches = new ArrayList<>();
        boolean hasMatches = false;
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(searchTerm)) {
                matches.add(movie);
                hasMatches = true;
            }
        }
        if (!hasMatches) {
            System.out.println("No movies found");
        }
        sortMovieList(matches);
        return matches;
    }

    private void searchCast(ArrayList<Movie> validMovies, String searchTerm) {

    }



    private static ArrayList<String> removeDuplicates(ArrayList<String> list)
    {
        ArrayList<String> newList = new ArrayList<String>();
        for (String castMember : list) {
            if (!newList.contains(castMember)) {
                newList.add(castMember);
            }
        }
        return newList;
    }

    public static void selectionSortWordList(ArrayList<String> words) {
        int n = words.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (words.get(j).compareTo(words.get(min_idx)) < 0) {
                    min_idx = j;
                }
            }
            String temp = words.get(i);
            words.set(i, words.get(min_idx));
            words.set(min_idx, temp);
        }
    }




    private void menu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }
}