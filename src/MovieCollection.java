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
            File myFile = new File("src\\movies.csv");
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


    private void searchTitles(String searchTerm) {
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
        for (int i = 0; i < matches.size(); i++) {
            System.out.println((i + 1) + ". " + matches.get(i).getTitle());
        }
        System.out.println("Which movie would you like to learn about (num): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice <= matches.size() - 1) {
            System.out.println("Title: " + matches.get(choice - 1).getTitle());
            System.out.println("Runtime: " + matches.get(choice - 1).getRuntime() + " minutes");
            System.out.println("Directed by: " + matches.get(choice - 1).getDirector());
            System.out.println("Cast: " + matches.get(choice - 1).getCast());
            System.out.println("Overview " + matches.get(choice - 1).getOverview());
            System.out.println("User Rating: " + matches.get(choice - 1).getUserRating());
        } else {
            System.out.println("INVALID");
        }
    }

    private void searchCast(String searchTerm) {
        ArrayList<String> cast = new ArrayList<>();
        for (Movie movie : movies) {
            String[] parsedData = movie.getCast().split("\\|");
            for (int i = 0; i < parsedData.length; i++) {
                if (parsedData[i].toLowerCase().contains(searchTerm.toLowerCase())) {
                    cast.add(parsedData[i]);
                }
            }
        }
        if (cast.isEmpty()) {
            System.out.println("Not found");
        }
        selectionSortWordList(cast);
        cast = removeDuplicates(cast);
        for (int i = 0; i < cast.size(); i++) {
            System.out.println((i + 1) + ". " + cast.get(i));
        }
        System.out.println("Which person would you like to learn about (num): ");
        int person = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Movie> hasActor = new ArrayList<>();
        for (Movie movie : movies) {
            String[] parsedData = movie.getCast().split("\\|");
            for (int i = 0; i < parsedData.length; i++) {
                if (parsedData[i].toLowerCase().contains(cast.get(person - 1).toLowerCase())) {
                    hasActor.add(movie);
                }
            }
        }
        sortMovieList(hasActor);
        System.out.println("How many movies would you like to display: ");
        int movDisplay = scanner.nextInt();
        if (movDisplay >= hasActor.size()) {
            movDisplay = hasActor.size();
        }
        for (int i = 0; i < movDisplay; i++) {
            System.out.println((i + 1) + ". " + hasActor.get(i).getTitle());
        }
        System.out.println("What movie would you like to learn about: ");
        int choice = scanner.nextInt();
        if (choice <= hasActor.size() - 1) {
            System.out.println("Title: " + hasActor.get(choice - 1).getTitle());
            System.out.println("Runtime: " + hasActor.get(choice - 1).getRuntime() + " minutes");
            System.out.println("Directed by: " + hasActor.get(choice - 1).getDirector());
            System.out.println("Cast: " + hasActor.get(choice - 1).getCast());
            System.out.println("Overview " + hasActor.get(choice - 1).getOverview());
            System.out.println("User Rating: " + hasActor.get(choice - 1).getUserRating());
        } else {
            System.out.println("INVALID");
        }
    }


    private ArrayList<String> removeDuplicates(ArrayList<String> list) {
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
                System.out.println("What movie do you want to search: ");
                searchTitles(scanner.nextLine().toLowerCase());
            } else if (menuOption.equals("c")) {
                System.out.println("Who do you want to search up");
                searchCast(scanner.nextLine().toLowerCase());
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }
}