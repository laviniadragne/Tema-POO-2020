package myclasses;

import actor.ActorsAwards;
import entertainment.Season;
import fileio.UserInputData;
import fileio.Input;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Assign {

    private Assign() {
    }

    /**
     * Construieste lista de useri pe baza inputului data
     */
    public static UsersList assignUsers(final Input input) {
        List<User> myUsers = new ArrayList<>();
        for (UserInputData userInput : input.getUsers()) {
            String username = userInput.getUsername();
            String subscriptionType = userInput.getSubscriptionType();
            Map<String, Integer> history = userInput.getHistory();
            ArrayList<String> favoriteMovies = userInput.getFavoriteMovies();

            // aduc informatia din input in clasa mea
            User newUser = new User(username, subscriptionType, history, favoriteMovies);
            myUsers.add(newUser);
        }
        return new UsersList(myUsers);
    }

    /**
     * Construieste lista de actori pe baza inputului dat
     */
    public static ActorsList assignActors(final Input input) {
        List<Actor> myActors = new ArrayList<>();
        for (ActorInputData actorInput : input.getActors()) {
            String actorname = actorInput.getName();
            String description = actorInput.getCareerDescription();
            Map<ActorsAwards, Integer> awards = actorInput.getAwards();
            ArrayList<String> filmography = actorInput.getFilmography();

            // aduc informatia din input in clasa mea
            Actor newActor = new Actor(actorname, description, filmography, awards);
            newActor.filterShow(filmography, input.getMovies(), input.getSerials());
            myActors.add(newActor);
        }
        return new ActorsList(myActors);
    }

    /**
     * Construieste lista de movies pe baza inputului dat
     */
    public static MoviesList assignMovies(final Input input) {
        List<Movie> myMovies = new ArrayList<>();
        int index1 = 0;
        for (MovieInputData movieInput : input.getMovies()) {
            String title = movieInput.getTitle();
            ArrayList<String> cast = movieInput.getCast();
            ArrayList<String> genre = movieInput.getGenres();
            int year = movieInput.getYear();
            int duration = movieInput.getDuration();

            Movie newMovie = new Movie(title, cast, genre, year, duration, index1);
            myMovies.add(newMovie);
            index1++;
        }
        return new MoviesList(myMovies);
    }

    /**
     * Construieste lista de serials pe baza inputului dat
     */
    public static SerialsList assignSerials(final Input input, final int index) {
        List<Serial> mySerials = new ArrayList<>();
        int indexData = index;
        for (SerialInputData serialInput : input.getSerials()) {
            String title = serialInput.getTitle();
            ArrayList<String> cast = serialInput.getCast();
            ArrayList<String> genres = serialInput.getGenres();
            int numseason = serialInput.getNumberSeason();
            ArrayList<Season> seasons = serialInput.getSeasons();
            int year = serialInput.getYear();

            Serial newSerial = new Serial(title, cast, genres, numseason, seasons, year, indexData);
            mySerials.add(newSerial);
            indexData++;
        }
        return new SerialsList(mySerials);
    }

    /**
     * Construieste lista de videos pe baza inputului dat
     */
    public static ShowsList assignShows(final MoviesList myMoviesClass,
                                        final SerialsList mySerialsClass) {
        List<Show> myShows = new ArrayList<>();
        myShows.addAll(myMoviesClass.getMoviesList());
        myShows.addAll(mySerialsClass.getSerialsList());
        return new ShowsList(myShows);

    }
}
