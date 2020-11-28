package myclasses;

import actor.ActorsAwards;
import entertainment.Season;
import fileio.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Assign {
    public static UsersList assignUsers(Input input) {
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

    public static ActorsList assignActors(Input input) {
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

    public static MoviesList assignMovies(Input input) {
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

    public static SerialsList assignSerials(Input input, int index) {
        List<Serial> mySerials = new ArrayList<>();
        for (SerialInputData serialInput : input.getSerials()) {
            String title = serialInput.getTitle();
            ArrayList<String> cast = serialInput.getCast();
            ArrayList<String> genres = serialInput.getGenres();
            int numseason = serialInput.getNumberSeason();
            ArrayList<Season> seasons = serialInput.getSeasons();
            int year = serialInput.getYear();

            Serial newSerial = new Serial(title, cast, genres, numseason, seasons, year, index);
            mySerials.add(newSerial);
            index++;
        }
        return new SerialsList(mySerials);
    }

    public static ShowsList assignShows(MoviesList myMoviesClass, SerialsList mySerialsClass) {
        List<Show> myShows = new ArrayList<>();
        myShows.addAll(myMoviesClass.getMoviesList());
        myShows.addAll(mySerialsClass.getSerialsList());
        return new ShowsList(myShows);

    }
}
