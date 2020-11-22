package myclasses;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class User {
    /**
     * User's username
     */
    private final String username;
    /**
     * Subscription Type
     */
    private final String subscriptionType;
    /**
     * The history of the movies seen
     */
    private final Map<String, Integer> history;
    /**
     * Movies added to favorites
     */
    private final ArrayList<String> favoriteMovies;
    /**
     * Numarul de rating-uri date de utilizator
     */
    private Integer numberRatings;

    public User(final String username, final String subscriptionType,
                         final Map<String, Integer> history,
                         final ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favoriteMovies = favoriteMovies;
        this.history = history;
        this.numberRatings = 0;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public Integer getNumberRatings() { return numberRatings; }


    /**
     * metoda intoarce un mesaj corespunzator pentru fiecare caz de add
     * favourite in parte
     */
    public String addFavourite(final String movie) {

        String answer = new String("error -> " + movie + " is not seen");
        // verific daca filmul e vizionat
        Integer indexMovie = history.get(movie);
        // daca l-am vizionat
        if (indexMovie != null) {
            // il caut prin lista de favorite
            for (String favMovie : favoriteMovies) {
                // l-am gasit deja in lista de favorite
                if (favMovie.equals(movie)) {
                    answer = "error -> " + movie + " is already in favourite list";
                    return answer;
                }
            }
            // nu l-am gasit in lista, il adaug
            favoriteMovies.add(movie);
            answer = "success -> " + movie + " was added as favourite";
        }
        return answer;
    }

    public String addView(final String movie) {

        String answer = new String("success -> " + movie + " was viewed with total views of ");
        int numberViews = 1;
        // verific daca filmul e vizionat
        Integer indexMovie = history.get(movie);

        // daca nu a mai fost vizionat
        if (indexMovie == null) {
            // il adaug la history
            history.putIfAbsent(movie, 1);
        }
        else {
            // ii cresc numarul de vizionari
            indexMovie++;
            history.replace(movie, indexMovie);
            numberViews = indexMovie;
        }
        answer = answer + numberViews;
        return answer;
    }

    public String addRating(final String video, final double rating, int seznumber, List<Movie> myMovies, List<Serial> mySerials) {
        String answer = new String();
        // verific daca filmul e vizionat
        Integer indexVideo = history.get(video);

        if (indexVideo != null) {
            // caut daca nu a mai dat o data rating aceluiasi video
            // in map-ul ratings al video-ului corespunzator adaug user si rating-ul
            for (Movie movie : myMovies) {
                if (movie.getTitle().equals(video)) {
                    // daca nu a mai dat rating deja acest user
                    if (!movie.getRatings().containsKey(this)) {
                        movie.getRatings().put(this, rating);
                        this.numberRatings++;
                        answer = "success -> " + video + " was rated with " + rating +
                                " by " + getUsername();
                        return answer;
                    }
                }
            }
            // daca e serial
            if(seznumber != 0) {
                for (Serial serial : mySerials) {
                    if (serial.getTitle().equals(video)) {
                        // daca nu i-a fost acordat deja un rating de catre acest user
                        if(!((serial.getSeasons()).get(seznumber - 1)).getRatingsUser().containsKey(this)) {
                            (((serial.getSeasons()).get(seznumber - 1)).getRatingsUser()).put(this, rating);
                            this.numberRatings++;
                            answer = "success -> " + video + " was rated with " + rating +
                                    " by " + getUsername();
                            return answer;
                        }
                    }
                }
            }
            answer = answer + "error -> " + video + " has been already rated";
            return answer;
        }
        answer  = answer + "error -> " + video + " is not seen";
        return answer;
    }

    // cauta in history ul user-ului filmele din lista
    // si il intoarce pe primul nevazut, cel mai popular
    public String unseen(List<Show> shows) {

        for (Show show : shows) {
            if (history.get(show.getTitle()) == null) {
                return show.getTitle();
            }
        }

        return null;
    }

    // cauta in history ul user-ului filmele din lista
    // si le intoarce pe cele nevazute dupa gen
    public List<Show> searchList(List<Show> shows) {

        List<Show> searchList = new ArrayList<>();
        for (Show show : shows) {
            if (history.get(show.getTitle()) == null) {
                searchList.add(show);
            }
        }

        return searchList;
    }

    public void addvideoFavorite(List<Show> shows) {
        // pentru toate filmele favorite ale unui user
        // le caut in lista de show-uri si le maresc
        // numarul total de fav aparitii
        for(String favorite : getFavoriteMovies()) {
            for(Show show : shows) {
                if(show.getTitle().equals(favorite)) {
                    int total = show.getTotalFav();
                    total++;
                    show.setTotalFav(total);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }
}
