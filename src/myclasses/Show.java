package myclasses;

import java.util.ArrayList;
import java.util.List;

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

    private final int indexDataBase;

    private int totalFav;

    public Show(final String title, final int year,
                     final ArrayList<String> cast,
                     final ArrayList<String> genres,
                     final int indexDataBase) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.indexDataBase = indexDataBase;
        this.totalFav = 0;
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

    /**
     * Intoarce indexul show-ului din baza de date
     */
    public int getIndexDataBase() {
        return indexDataBase;
    }

    /**
     * Intoarce cat de favorit este video-ul
     */
    public int getTotalFav() {
        return totalFav;
    }

    /**
     * Seteaza cat de favorit este video-ul
     */
    public void setTotalFav(final int totalfav) {
        this.totalFav = totalfav;
    }

    /**
     * Intoarce suma rating-urilor unui video
     */
    public abstract double sumRatings();

    /**
     * Verifica daca un show contine field-urile date in input
     */
    public boolean filtersVideos(final List<List<String>> filters) {
        List<String> years = filters.get(0);
        // verific daca filmul are anul caracteristic
        if (years.get(0) != null) {
            if (!years.get(0).equals(Integer.toString(getYear()))) {
                return false;
            }
        }
        // daca lista a 2-a de criterii e goala
        if (filters.get(1).get(0) == null) {
            return true;
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
        return years.get(0) == null && filters.get(1).get(0) == null;
    }

    /**
     * Functia calculeaza numarul de aparitii ale show-ului
     * in lista de useri
     */
    public int aparitionFavorite(final UsersList myUsers) {
        int aparitions = 0;
        for (User user : myUsers.getUsersList()) {
            for (String favoriteShow : user.getFavoriteMovies()) {
                if (favoriteShow.equals(getTitle())) {
                    aparitions++;
                }
            }
        }
        return aparitions;
    }

    /**
     * Intoarce suma vizionarilor unui video
     */
    public int sumViews(final UsersList myUsersClass) {
        int sumViews = 0;
        // caut filmul in istoricul fiecarui user
        for (User user : myUsersClass.getUsersList()) {
            if (user.getHistory().get(getTitle()) != null) {
                sumViews += user.getHistory().get(getTitle());
            }
        }
        return sumViews;
    }

    /**
     * Metoda intoarce daca show-ul are un gen dat ca si parametru
     */
    public boolean presentGenre(final String name) {
        for (String nameGenre : getGenres()) {
            if (nameGenre.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
