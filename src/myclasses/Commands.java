package myclasses;

import fileio.ActionInputData;

public final class Commands {

    private Commands() {
    }

    /**
     * Adaugă un video în lista de favorite videos ale acelui user,
     * dacă a fost deja vizionat de user-ul respectiv.
     */
    public static String favourite(final UsersList myUsersClass, final ActionInputData action) {
        String message;
        // caut in userii mei numele celui dat
        User user = myUsersClass.getUser(action.getUsername());
        // apelez pentrul el comanda de favourite
        message = user.addFavourite(action.getTitle());
        return message;
    }

    /**
     * Vizualizează un video prin marcarea lui ca văzut
     */
    public static String view(final UsersList myUsersClass, final ActionInputData action) {
        String message;
        // caut in userii mei numele celui dat
        User user = myUsersClass.getUser(action.getUsername());
        // apelez pentrul el comanda de view
        message = user.addView(action.getTitle());
        return message;
    }

    /**
     * Oferă rating unui video care este deja văzut
     */
    public static String rating(final UsersList myUsersClass, final MoviesList myMoviesClass,
                                final SerialsList mySerialsClass, final ActionInputData action) {
        String message;
        // caut in userii mei numele celui dat
        User user = myUsersClass.getUser(action.getUsername());
        // apelez pentrul el comanda de rating
        message = user.addRating(action.getTitle(), action.getGrade(), action.getSeasonNumber(),
                myMoviesClass.getMoviesList(), mySerialsClass.getSerialsList());

         return message;
    }
}
