package myclasses;

import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.List;

public class Recommendations {

    public static String standard(UsersList myUsersClass ,ShowsList myShowsClass, ActionInputData action) {
        String message = "";
        String username = action.getUsername();
        User user = myUsersClass.getUser(username);
        myShowsClass.indexdbSort();
        String unseenVideo = user.unseen(myShowsClass.getShowsList());

        if (unseenVideo == null) {
            message = message + "StandardRecommendation cannot be applied!";
        } else {
            message = message + "StandardRecommendation result: ";
            message = message + unseenVideo;
        }
        return message;
    }

    public static String best_unseen(UsersList myUsersClass, ShowsList myShowsClass, ActionInputData action) {
        String message = "";
        myShowsClass.bestunseenSort();
        User user = myUsersClass.getUser(action.getUsername());
        String unseenVideo = user.unseen(myShowsClass.getShowsList());
        if (unseenVideo == null) {
            message = message + "BestRatedUnseenRecommendation cannot be applied!";
        } else {
            message = message + "BestRatedUnseenRecommendation result: ";
            message = message + unseenVideo;
        }
        return message;
    }
    
    public static String search(UsersList myUsersClass, ShowsList myShowsClass, ActionInputData action) {
        String message = "";
        // verific daca userul e premium
        User premiumUser = myUsersClass.getUser(action.getUsername());
        if (premiumUser.getSubscriptionType().equals("PREMIUM")) {
            ShowsList classshowsFind = new ShowsList(myShowsClass.getShowsList());
            // caut in baza de date filmele cu acel gen
            List<Show> showsFind = myShowsClass.searchGenre(action.getGenre());
            classshowsFind.setShowsList(showsFind);

            User user = myUsersClass.getUser(action.getUsername());
            List<Show> searchVideo = user.searchList(classshowsFind.getShowsList());
            classshowsFind.setShowsList(searchVideo);
            classshowsFind.searchunseenSort();

            if (classshowsFind.getShowsList().size() == 0) {
                message = message + "SearchRecommendation cannot be applied!";
            } else {
                message = message + "SearchRecommendation result: ";
                message = message + classshowsFind.getshowsName();
            }
        } else {
            message = message + "SearchRecommendation cannot be applied!";
        }
        return message;
    }

    public static String favorite(UsersList myUsersClass, ShowsList myShowsClass, ActionInputData action) {
        String message = "";
        // verific daca userul e premium
        User premiumUser = myUsersClass.getUser(action.getUsername());
        if (premiumUser.getSubscriptionType().equals("PREMIUM")) {

            // imi creez o copie a clasei de seriale
            ShowsList classshowsFav = new ShowsList(myShowsClass.getShowsList());

            // adaug pe listele de fav ale userilor in campurile de la show
            // totalfav
            myUsersClass.addlistFav(myShowsClass.getShowsList());

            // ordonez serialele
            classshowsFav.searchfavoriteSort();

            // primul video nevazut
            String nameFav = premiumUser.unseen(classshowsFav.getShowsList());

            if (nameFav == null) {
                message = message + "FavoriteRecommendation cannot be applied!";
            } else {
                message = message + "FavoriteRecommendation result: ";
                message = message + nameFav;
            }
        } else {
            message = message + "FavoriteRecommendation cannot be applied!";
        }
        return message;
    }

    public static String popular(UsersList myUsersClass, ShowsList myShowsClass, ActionInputData action) {
        String message = "";
        // verific daca userul e premium
        User premiumUser = myUsersClass.getUser(action.getUsername());
        if (premiumUser.getSubscriptionType().equals("PREMIUM")) {

            List<myGenre> listGenres = new ArrayList<>();
            GenresList myGenresClass = new GenresList(listGenres);
            // calculez pentru fiecare gen in parte numarul de views
            myGenresClass.calculatePopular(myShowsClass, myUsersClass);
            // sortez lista de genuri
            myGenresClass.searchPopularSort();
            // sortez show-urile dupa ordinea din input
            myShowsClass.indexdbSort();
            String nameShow = myGenresClass.popularShow(myShowsClass, premiumUser);

            if (nameShow == null) {
                message = message + "PopularRecommendation cannot be applied!";
            } else {
                message = message + "PopularRecommendation result: ";
                message = message + nameShow;
            }
        } else {
            message = message + "PopularRecommendation cannot be applied!";
        }
        return message;

    }

}
