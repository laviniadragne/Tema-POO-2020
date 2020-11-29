package myclasses;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    private List<User> usersList;

    public UsersList(final List<User> inputusersList) {
        usersList = inputusersList;
    }

    /**
     * Intoarce lista de useri
     */
    public List<User> getUsersList() {
        return usersList;
    }

    /**
     * Seteaza lista de useri
     */
    public void setUsersList(final List<User> inputusersList) {
        usersList = inputusersList;
    }

    /**
     * Cauta in lista un user cu un nume dat si il
     * returneaza
     */
    public User getUser(final String name) {
        for (User user : usersList) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    /**
     *  Calculeaza numarul total de aparitii favorite
     *  ale tuturor filmelor de la toti userii
     */
    public void addlistFav(final List<Show> shows) {
        for (User user : getUsersList()) {
            user.addvideoFavorite(shows);
        }
    }

    /**
     * Sorteaza userii pe baza rating-ului
     */
    public void ratingsSort(final String criteria) {
        if (criteria.equals("asc")) {
            usersList.sort((u1, u2) -> {
                if (!u1.getNumberRatings().equals(u2.getNumberRatings())) {
                    return Double.compare(u1.getNumberRatings(), u2.getNumberRatings());
                }
                return u1.getUsername().compareTo(u2.getUsername());
            });

        }
        if (criteria.equals("desc")) {
            usersList.sort((u1, u2) -> {
                if (!u1.getNumberRatings().equals(u2.getNumberRatings())) {
                    return Double.compare(u2.getNumberRatings(), u1.getNumberRatings());
                }
                return u2.getUsername().compareTo(u1.getUsername());
            });

        }
    }

    /**
     * Scrie intr-un string primele n filme in functie de favorite
     */
    public List<String> writeRating(final int n) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while (cnt < usersList.size() && i < n) {
            if (usersList.get(cnt).getNumberRatings() != 0) {
                    listWrite.add(usersList.get(cnt).getUsername());
                    i++;
            }
            cnt++;
        }
        return listWrite;
    }
}
