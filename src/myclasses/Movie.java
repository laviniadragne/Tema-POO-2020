package myclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Movie extends Show {
    /**
     * Duration in minutes of a season
     */
    private final int duration;

    // dictionar pentru a retine ce user a acordat fiecare rating
    private Map<User, Double> ratings;

    public Movie(final String title, final ArrayList<String> cast,
                          final ArrayList<String> genres, final int year,
                          final int duration, final int indexDataBase) {
        super(title, year, cast, genres, indexDataBase);
        this.duration = duration;
        this.ratings = new HashMap<>();

    }

    /**
     * Intoarce durata unui film
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Intoarce numarul de rating-uri ale unui film
     */
    public Map<User, Double> getRatings() {
        return ratings;
    }

    /**
     * Seteaza numarul de rating-uri ale unui film
     */
    public void setRatings(final Map<User, Double> ratings) {
        this.ratings = ratings;
    }

    /** Metoda calculeaza rating-ul unui film
     */
    public double sumRatings() {
        double sum = 0;
        for (double f : ratings.values()) {
            sum += f;
        }
        if (ratings.size() != 0) {
            return sum / ratings.size();
        } else {
            return 0;
        }
    }
}
