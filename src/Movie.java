public class Movie {
    // title,cast,director,overview,runtime,userRating
    private String title;
    private String[] cast;
    private String overview;
    private int runtime;
    private double userRating;

    public Movie(String title, String[] cast, String overview, int runtime, double userRating) {
        this.title = title;
        this.cast = cast;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public String[] getCast() {
        return cast;
    }

    public String getOverview() {
        return getOverview();
    }

    public int getRuntime() {
        return runtime;
    }

    public double getUserRating() {
        return userRating;
    }
}