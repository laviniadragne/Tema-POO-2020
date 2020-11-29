package myclasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShowsList {

    private List<Show> showsList;

    public ShowsList(final List<Show> inputshowsList) {
        showsList = inputshowsList;
    }

    /**
     * Intoarce lista de video-uri
     */
    public List<Show> getShowsList() {
        return showsList;
    }

    /**
     * Seteaza lista de video-uri
     */
    public void setShowsList(final List<Show> inputshowsList) {
        showsList = inputshowsList;
    }

    /**
     * Creeaza o lista cu numele shor-urilor
     */
    public List<String> getshowsName() {
        List<String> nameList = new ArrayList<>();
        for (Show show : getShowsList()) {
            nameList.add(show.getTitle());
        }
        return nameList;
    }

    /**
     * Sorteaza dupa numarul de rating-uri o lista de show-uri
     * si dupa aparitia lor in baza de date
     */
    public void bestunseenSort() {
        showsList.sort((s1, s2) -> {
            if (s1.sumRatings() != s2.sumRatings()) {
                return Double.compare(s2.sumRatings(), s1.sumRatings());
            }
            return Double.compare(s1.getIndexDataBase(), s2.getIndexDataBase());
        });

    }

    /**
     * Sorteaza dupa numarul de rating-uri o lista de show-uri
     * si dupa numele lor
     */
    public void searchunseenSort() {
        showsList.sort((s1, s2) -> {
            if (s1.sumRatings() != s2.sumRatings()) {
                return Double.compare(s1.sumRatings(), s2.sumRatings());
            }
            return s1.getTitle().compareTo(s2.getTitle());
        });

    }

    /**
     * Creeaza o lista de show-uri dintr-un anumit gen
     */
    public List<Show> searchGenre(final String criteria) {
        List<Show> specificShows = new ArrayList<>();
        for (Show show : getShowsList()) {
            for (String genre : show.getGenres()) {
                if (genre.equals(criteria)) {
                        specificShows.add(show);
                }
            }
        }
        return specificShows;
    }

    /**
     * Sorteaza dupa numarul de rating-uri o lista de show-uri
     * si dupa numele lor
     */
    public void searchfavoriteSort() {
        showsList.sort((s1, s2) -> {
            if (s1.getTotalFav() != s2.getTotalFav()) {
                return Double.compare(s2.getTotalFav(), s1.getTotalFav());
            }
            return Double.compare(s1.getIndexDataBase(), s2.getIndexDataBase());
        });

    }

    /**
     * Sorteaza dupa indexul din baza de date
     */
    public void indexdbSort() {
        showsList.sort(Comparator.comparingDouble(Show::getIndexDataBase));
    }

}
