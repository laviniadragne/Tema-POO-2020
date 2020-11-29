package myclasses;

import entertainment.Season;

import java.util.ArrayList;

public class Serial extends Show {
    /**
     * Number of seasons
     */
    private final int numberOfSeasons;
    /**
     * Season list
     */
    private final ArrayList<Season> seasons;

    public Serial(final String title, final ArrayList<String> cast,
                           final ArrayList<String> genres,
                           final int numberOfSeasons, final ArrayList<Season> seasons,
                           final int year, final int indexDataBase) {
        super(title, year, cast, genres, indexDataBase);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    /**
     * Intoarce numarul de sezoane
     */
    public int getNumberSeason() {
        return numberOfSeasons;
    }

    /**
     * Intoarce lista de sezoane
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * Calculeaza rating-ul unui serial
     */
    public double sumRatings() {
        double sum = 0;
        for (Season season : seasons) {
           sum += season.sumRatings();
        }
        return sum / numberOfSeasons;
    }

    /**
     * Intoarce durata totala a unui serial
     */
   public int sumDuration() {
        int sum = 0;
        for (Season season : seasons) {
            sum += season.getDuration();
        }
        return sum;
   }
}
