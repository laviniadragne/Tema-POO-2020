package myclasses;

import fileio.ActionInputData;

import java.util.LinkedList;
import java.util.List;

public final class Queries {

    private Queries() {
    }

    /**
     * Returneaza o lista cu toți actorii cu premiile menționate în query
     */
    public static String awards(final ActorsList myActorsClass,
                                final ActionInputData action) {
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

    /**
     * Returneaza toți actorii în descrierea cărora apar toate keywords-urile
     */
    public static String filterdescription(final ActorsList myActorsClass,
                                           final ActionInputData action) {
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

    /**
     * Primii N actori (N dat în query) sortați după media ratingurilor
     * filmelor și a serialelor
     */
    public static String average(final ActorsList myActorsClass,
                                 final SerialsList mySerialsClass,
                                 final MoviesList myMoviesClass,
                                 final ActionInputData action) {
        // calculez rating-ul pentru fiecare actor
        for (Actor actor : myActorsClass.getActorsList()) {
            actor.calculateRating(myMoviesClass.getMoviesList(), mySerialsClass.getSerialsList());
        }

        // sortez lista de actori dupa rating
        myActorsClass.ratingSort(action.getSortType(), myActorsClass.getActorsList());

        String message = "Query result: ";
        List<String> message1 = myActorsClass.writeSortedNName(myActorsClass.getActorsList(),
                                                                action);
        message = message + message1;
        return message;
    }

    /**
     * Primele N filme sortate după rating
     */
    public static String moviesRating(final MoviesList myMoviesClass,
                                      final ActionInputData action) {
        myMoviesClass.ratingsort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = myMoviesClass.writeSortedMovies(action.getNumber(),
                                                            action.getFilters());
        message = message + message1;
        return message;
    }

    /**
     * Primele N filme sortate după numărul de apariții în listele
     * de video-uri favorite ale utilizatorilor
     */
    public static String moviesFavorite(final UsersList myUsersClass,
                                        final MoviesList myMoviesClass,
                                        final ActionInputData action) {
        myMoviesClass.favoritesort(myUsersClass, action.getSortType());
        String message = "Query result: ";
        List<String> message1 = myMoviesClass.writeFavAparition(action.getNumber(),
                action.getFilters(), myUsersClass);
        message = message + message1;
        return message;
    }

    /**
     * Primele N filme sortate după durata lor
     */
    public static String moviesLongest(final MoviesList myMoviesClass,
                                       final ActionInputData action) {
        myMoviesClass.durationSort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = myMoviesClass.writeLongestMovies(action.getNumber(),
                                                                action.getFilters());
        message = message + message1;
        return message;
    }

    /**
     * Primele N filme sortate după numărul de vizualizări
     */
    public static String moviesmostViewd(final UsersList myUsersClass,
                                         final MoviesList myMoviesClass,
                                         final ActionInputData action) {
        myMoviesClass.viewsSort(action.getSortType(), myUsersClass);
        String message = "Query result: ";
        List<String> message1 = myMoviesClass.writeMostView(action.getNumber(),
                                                action.getFilters(), myUsersClass);
        message = message + message1;
        return message;
    }
    /**
     * Primele N seriale sortate după rating
     */
    public static String serialsRating(final SerialsList mySerialsClass,
                                       final ActionInputData action) {
        mySerialsClass.ratingsort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = mySerialsClass.writeSortedSerials(action.getNumber(),
                                                            action.getFilters());
        message = message + message1;
        return message;
    }

    /**
     * Primele N seriale sortate după numărul de apariții în listele
     * de video-uri favorite ale utilizatorilor
     */
    public static String serialsFavourite(final UsersList myUsersClass,
                                          final SerialsList mySerialsClass,
                                          final ActionInputData action) {
        mySerialsClass.favoritesort(myUsersClass, action.getSortType());
        String message = "Query result: ";
        List<String> message1 = mySerialsClass.writeFavAparition(action.getNumber(),
                                        action.getFilters(), myUsersClass);
        message = message + message1;
        return message;
    }

    /**
     * Primele N seriale sortate după durata lor
     */
    public static String serialsLongest(final SerialsList mySerialsClass,
                                        final ActionInputData action) {
        mySerialsClass.durationSort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = mySerialsClass.writeLongestSerials(action.getNumber(),
                                                        action.getFilters());
        message = message + message1;
        return message;
    }

    /**
     * Primele N seriale sortate după numărul de vizualizări
     */
    public static String serialsmostViewd(final UsersList myUsersClass,
                                          final SerialsList mySerialsClass,
                                          final ActionInputData action) {
        mySerialsClass.viewsSort(action.getSortType(), myUsersClass);
        String message = "Query result: ";
        List<String> message1 = mySerialsClass.writeMostView(action.getNumber(),
                                                action.getFilters(), myUsersClass);
        message = message + message1;
        return message;
    }

    /**
     * Primii N utilizatori sortați după numărul de ratings pe care le-au dat
     */
    public static String usersRating(final UsersList myUsersClass, final ActionInputData action) {
        myUsersClass.ratingsSort(action.getSortType());
        String message = "Query result: ";
        List<String> message1 = myUsersClass.writeRating(action.getNumber());
        message = message + message1;
        return message;
    }

}
