package myclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Show {
    /**
     * Show's title
     */
    private final String title;
    /**
     * The year the show was released
     */
    private final int year;
    /**
     * Show casting
     */
    private final ArrayList<String> cast;
    /**
     * Show genres
     */
    private final ArrayList<String> genres;

    private int indexDataBase;


    public Show(final String title, final int year,
                     final ArrayList<String> cast, final ArrayList<String> genres, int indexDataBase) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.indexDataBase = indexDataBase;
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    public int getIndexDataBase() {
        return indexDataBase;
    }

    public void setIndexDataBase(int indexDataBase) {
        this.indexDataBase = indexDataBase;
    }

    public abstract double sumRatings();

        // verifica daca un movie contine field-urile
    public boolean filtersVideos(List<List<String>> filters){
        List<String> year = filters.get(0);
        // verific daca filmul are anul caracteristic
        if(year.get(0) != null) {
            if (!year.get(0).equals(Integer.toString(getYear()))) {
                return false;
            }
        }
        // verific daca este din genul caracteristic
        for (String filter : filters.get(1)) {
            for (String genre : getGenres()) {
                if (genre.equals(filter)) {
                    return true;
                }
            }
        }
        // daca nu am filtre
        if (year.get(0) == null && filters.get(1).get(0) == null) {
            return true;
        }
        return false;
    }

    // functia calculeaza numarul de aparitii ale show-ului
    // in lista de useri
    public int aparitionFavorite(UsersList myUsers) {
        int aparitions = 0;
        for (User user : myUsers.getUsersList()) {
            for (String favoriteShow : user.getFavoriteMovies()) {
                if(favoriteShow.equals(getTitle())) {
                    aparitions++;
                }
            }
        }
        return aparitions;
    }

    public int sumViews(UsersList myUsersClass) {
        int sumViews = 0;
        // caut filmul in istoricul fiecarui user
        for(User user : myUsersClass.getUsersList()) {
            if(user.getHistory().get(getTitle()) != null) {
                sumViews += user.getHistory().get(getTitle());
            }
        }
        return sumViews;
    }

}
