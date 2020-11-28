package myclasses;

import fileio.ActionInputData;

public class Commands {

    public static String favourite(UsersList myUsersClass, ActionInputData action) {
        String message;
        // caut in userii mei numele celui dat
        User user = myUsersClass.getUser(action.getUsername());
        // apelez pentrul el comanda de favourite
        message = user.addFavourite(action.getTitle());
        return message;
    }

    public static String view(UsersList myUsersClass, ActionInputData action) {
        String message;
        // caut in userii mei numele celui dat
        User user = myUsersClass.getUser(action.getUsername());
        // apelez pentrul el comanda de view
        message = user.addView(action.getTitle());
        return message;
    }

    public static String rating(UsersList myUsersClass, MoviesList myMoviesClass, SerialsList mySerialsClass,
                                ActionInputData action) {
        String message;
        // caut in userii mei numele celui dat
        User user = myUsersClass.getUser(action.getUsername());
        // apelez pentrul el comanda de rating
        message = user.addRating(action.getTitle(), action.getGrade(), action.getSeasonNumber(),
                myMoviesClass.getMoviesList(), mySerialsClass.getSerialsList());

         return message;
    }
}
