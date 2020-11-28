package myclasses;

import fileio.ActionInputData;

import java.util.LinkedList;
import java.util.List;

public class Queries {

    public static String awards(ActorsList myActorsClass, ActionInputData action) {
        List<Actor> queryawardsActors = new LinkedList<>();
        List<String> filters = action.getFilters().get(3);
        for (Actor actor : myActorsClass.getActorsList()) {
            // a 4-a lista este cea care filtreaza dupa awards
            int totalAwords = actor.awards(filters);
            if (totalAwords != 0) {
                queryawardsActors.add(actor);
            }
        }

        // sortez lista de actori
        myActorsClass.awardsSort(action.getSortType(), queryawardsActors);
        String message = "Query result: ";
        List<String> message1 = myActorsClass.writeSortedNames(queryawardsActors);
        message = message + message1;
        return message;
    }

    public static String filterdescription(ActorsList myActorsClass, ActionInputData action) {
        String message;
        List<Actor> queryfilterActors = new LinkedList<>();
        // iterez prin lista de actori
        for (Actor actor : myActorsClass.getActorsList()) {
            // a 3-a lista este cea care filtreaza dupa cuvinte
            message = actor.filterDescription(action.getFilters());
            if (message != null) {
                queryfilterActors.add(actor);
            }
        }
        // sortez lista de actori dupa nume
        myActorsClass.filterdescriptionSort(action.getSortType(), queryfilterActors);

        // scrie lista queryfilterAct in arrayout
        message = "Query result: ";
        List<String> message1 = myActorsClass.writeSortedNames(queryfilterActors);
        message = message + message1;
        return message;
    }

    public static String average(ActorsList myActorsClass, SerialsList mySerialsClass, MoviesList myMoviesClass,
                                 ActionInputData action) {
        // calculez rating-ul pentru fiecare actor
        for (Actor actor : myActorsClass.getActorsList()) {
            actor.calculateRating(myMoviesClass.getMoviesList(), mySerialsClass.getSerialsList());
        }

        // sortez lista de actori dupa rating
        myActorsClass.ratingSort(action.getSortType(), myActorsClass.getActorsList());

        String message = "Query result: ";
        List<String> message1 = myActorsClass.writeSortedNName(myActorsClass.getActorsList(), action);
        message = message + message1;
        return message;
    }

    public static String moviesRating(MoviesList myMoviesClass, ActionInputData action) {
        myMoviesClass.ratingsort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = myMoviesClass.writeSortedMovies(action.getNumber(), action.getFilters());
        message = message + message1;
        return message;
    }

    public static String moviesFavorite(UsersList myUsersClass,MoviesList myMoviesClass, ActionInputData action) {
        myMoviesClass.favoritesort(myUsersClass, action.getSortType());
        String message = "Query result: ";
        List<String> message1 = myMoviesClass.writeFavAparition(action.getNumber(), action.getFilters(), myUsersClass);
        message = message + message1;
        return message;
    }

    public static String moviesLongest(MoviesList myMoviesClass, ActionInputData action) {
        myMoviesClass.durationSort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = myMoviesClass.writeLongestMovies(action.getNumber(), action.getFilters());
        message = message + message1;
        return message;
    }

    public static String moviesmostViewd(UsersList myUsersClass,MoviesList myMoviesClass, ActionInputData action) {
        myMoviesClass.viewsSort(action.getSortType(), myUsersClass);
        String message = "Query result: ";
        List<String> message1 = myMoviesClass.writeMostView(action.getNumber(), action.getFilters(), myUsersClass);
        message = message + message1;
        return message;
    }

    public static String serialsRating(SerialsList mySerialsClass, ActionInputData action) {
        mySerialsClass.ratingsort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = mySerialsClass.writeSortedSerials(action.getNumber(), action.getFilters());
        message = message + message1;
        return message;
    }

    public static String serialsFavourite(UsersList myUsersClass,SerialsList mySerialsClass, ActionInputData action) {
        mySerialsClass.favoritesort(myUsersClass, action.getSortType());
        String message = "Query result: ";
        List<String> message1 = mySerialsClass.writeFavAparition(action.getNumber(), action.getFilters(), myUsersClass);
        message = message + message1;
        return message;
    }

    public static String serialsLongest(SerialsList mySerialsClass, ActionInputData action) {
        mySerialsClass.durationSort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = mySerialsClass.writeLongestSerials(action.getNumber(), action.getFilters());
        message = message + message1;
        return message;
    }

    public static String serialsmostViewd(UsersList myUsersClass,SerialsList mySerialsClass, ActionInputData action) {
        mySerialsClass.viewsSort(action.getSortType(), myUsersClass);
        String message = "Query result: ";
        List<String> message1 = mySerialsClass.writeMostView(action.getNumber(), action.getFilters(), myUsersClass);
        message = message + message1;
        return message;
    }

    public static String usersRating(UsersList myUsersClass, ActionInputData action) {
        myUsersClass.ratingsSort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = myUsersClass.writeRating(action.getNumber());
        message = message + message1;
        return message;
    }

}
