package myclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowsList {

    private List<Show> ShowsList;

    public ShowsList(List<Show> showsList) {
        ShowsList = showsList;
    }

    public List<Show> getShowsList() {
        return ShowsList;
    }

    public void setShowsList(List<Show> showsList) {
        ShowsList = showsList;
    }

    // creeaza o lista cu numele shor-urilor
    public List<String> getshowsName() {
        List<String> nameList = new ArrayList<>();
        for(Show show : getShowsList()) {
            nameList.add(show.getTitle());
        }
        return nameList;
    }

    // sorteaza dupa numarul de rating-uri o lista de show-uri
    // si dupa aparitia lor in baza de date
    public void bestunseenSort() {
        Collections.sort(ShowsList, new Comparator<Show>() {
            @Override
            public int compare(Show s1, Show s2) {
                if (s1.sumRatings() != s2.sumRatings()) {
                    return Double.compare (s2.sumRatings(), s1.sumRatings());
                }
                return Double.compare(s1.getIndexDataBase(), s2.getIndexDataBase());
            }
        });

    }

    // sorteaza dupa numarul de rating-uri o lista de show-uri
    // si dupa numele lor
    public void searchunseenSort() {
        Collections.sort(ShowsList, new Comparator<Show>() {
            @Override
            public int compare(Show s1, Show s2) {
                if (s1.sumRatings() != s2.sumRatings()) {
                    return Double.compare (s1.sumRatings(), s2.sumRatings());
                }
                return s1.getTitle().compareTo(s2.getTitle());
            }
        });

    }

    // creeaza o lista de show-uri dintr-un anumit gen
    public List<Show> searchGenre(String criteria) {
        List<Show> specificShows = new ArrayList<>();
        for (Show show : getShowsList()) {
            for(String genre : show.getGenres()) {
                if (genre.equals(criteria)) {
                        specificShows.add(show);
                }
            }
        }
        return specificShows;
    }

    // sorteaza dupa numarul de rating-uri o lista de show-uri
    // si dupa numele lor
    public void searchfavoriteSort() {
        Collections.sort(ShowsList, new Comparator<Show>() {
            @Override
            public int compare(Show s1, Show s2) {
                if (s1.getTotalFav() != s2.getTotalFav()) {
                    return Double.compare (s2.getTotalFav(), s1.getTotalFav());
                }
                return Double.compare(s1.getIndexDataBase(), s2.getIndexDataBase());
            }
        });

    }
}
