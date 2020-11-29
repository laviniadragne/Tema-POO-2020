package entertainment;

import myclasses.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information about a season of a tv show
 * <p>
 * DO NOT MODIFY
 */
public final class Season {
    /**
     * Number of current season
     */
    private final int currentSeason;
    /**
     * Duration in minutes of a season
     */
    private int duration;
    /**
     * List of ratings for each season
     */
    private List<Double> ratings;

    /**
     * Rating-urile date de fiecare user
     */
    private final Map<User, Double> ratingsUser;


    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.ratings = new ArrayList<>();
        this.ratingsUser = new HashMap<>();

    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }

    public Map<User, Double> getRatingsUser() {
        return ratingsUser;
    }

    /**
     * Calculeaza rating-ul unui sezon
     */
    public double sumRatings() {
        double sum = 0;
        for (double f : ratingsUser.values()) {
            sum += f;
        }
        if (ratingsUser.size() != 0) {
            return sum / ratingsUser.size();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Episode{"
                + "currentSeason="
                + currentSeason
                + ", duration="
                + duration
                + '}';
    }
}

