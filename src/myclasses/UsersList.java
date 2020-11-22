package myclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UsersList {
    private List<User> UsersList;

    public UsersList(List<User> usersList) {
        UsersList = usersList;
    }

    public List<User> getUsersList() {
        return UsersList;
    }

    public void setUsersList(List<User> usersList) {
        UsersList = usersList;
    }

    public User getUser(String name) {
        for (User user : UsersList) {
            if(user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    // calculeaza numarul total de aparitii favorite
    // ale tuturor filmelor de la toti userii
    public void addlistFav(List<Show> shows) {
        for (User user : getUsersList()) {
            user.addvideoFavorite(shows);
        }
    }

    public void ratingsSort(String criteria) {
        if (criteria.equals("asc")) {
            Collections.sort(UsersList, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    if (u1.getNumberRatings() != u2.getNumberRatings()) {
                        return Double.compare (u1.getNumberRatings(), u2.getNumberRatings());
                    }
                    return u1.getUsername().compareTo(u2.getUsername());
                }
            });

        }
        if (criteria.equals("desc")) {
            Collections.sort(UsersList, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    if (u1.getNumberRatings() != u2.getNumberRatings()) {
                        return Double.compare (u2.getNumberRatings(), u1.getNumberRatings());
                    }
                    return u2.getUsername().compareTo(u1.getUsername());
                }
            });

        }
    }
    // scrie intr-un string pe primele n filme in functie de favorite
    public List<String> writeRating (int n) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while(cnt < UsersList.size() && i < n) {
            if (UsersList.get(cnt).getNumberRatings() != 0) {
                    listWrite.add(UsersList.get(cnt).getUsername());
                    i++;
            }
            cnt++;
        }
        return listWrite;
    }
}
