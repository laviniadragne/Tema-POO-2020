package myclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Movie extends Show {
    /**
     * Duration in minutes of a season
     */
    private final int duration;

    // dictionar pentru a retine ce user a acordat fiecare rating
    private Map<User, Double> ratings;

    public Movie(final String title, final ArrayList<String> cast,
                          final ArrayList<String> genres, final int year,
                          final int duration, int indexDataBase) {
        super(title, year, cast, genres, indexDataBase);
        this.duration = duration;
        this.ratings = new HashMap<>();

    }

    public int getDuration() {
        return duration;
    }

    public Map<User, Double> getRatings() {
        return ratings;
    }

    public void setRatings(Map<User, Double> ratings) {
        this.ratings = ratings;
    }

    // metoda calculeaza rating-ul unui film
    public double sumRatings() {
        double sum = 0;
        for (double f : ratings.values()) {
            sum += f;
        }
        if(ratings.size() != 0) {
            return sum / ratings.size();
        }
        else
            return 0;
    }


    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }
}
