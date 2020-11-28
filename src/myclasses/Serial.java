package myclasses;

import entertainment.Season;

import java.util.ArrayList;

public final class Serial extends Show {
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
                           final int year, int indexDataBase) {
        super(title, year, cast, genres, indexDataBase);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    // calculeaza rating-ul unui serial
    public double sumRatings() {
        double sum = 0;
        for(Season season : seasons) {
           sum += season.sumRatings();
        }
        return sum/numberOfSeasons;
    }

   public int sumDuration() {
        int sum = 0;
        for(Season season : seasons) {
            sum += season.getDuration();
        }
        return sum;
   }

    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
}
