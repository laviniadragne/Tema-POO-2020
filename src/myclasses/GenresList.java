package myclasses;

import entertainment.Genre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GenresList {

    private List<myGenre> GenresList;

    public GenresList(List<myGenre> GenresList) {
        this.GenresList = GenresList;
        myGenre newGenre = new myGenre("Action");
        GenresList.add(newGenre);
        newGenre = new myGenre("Adventure");
        GenresList.add(newGenre);
        newGenre = new myGenre("Drama");
        GenresList.add(newGenre);
        newGenre = new myGenre("Comedy");
        GenresList.add(newGenre);
        newGenre = new myGenre("Crime");
        GenresList.add(newGenre);
        newGenre = new myGenre("Romance");
        GenresList.add(newGenre);
        newGenre = new myGenre("War");
        GenresList.add(newGenre);
        newGenre = new myGenre("History");
        GenresList.add(newGenre);
        newGenre = new myGenre("Thriller");
        GenresList.add(newGenre);
        newGenre = new myGenre("Mystery");
        GenresList.add(newGenre);
        newGenre = new myGenre("Family");
        GenresList.add(newGenre);
        newGenre = new myGenre("Horror");
        GenresList.add(newGenre);
        newGenre = new myGenre("Fantasy");
        GenresList.add(newGenre);
        newGenre = new myGenre("Science Fiction");
        GenresList.add(newGenre);
        newGenre = new myGenre("Action & Adventure");
        GenresList.add(newGenre);
        newGenre = new myGenre("Sci-Fi & Fantasy");
        GenresList.add(newGenre);
        newGenre = new myGenre("Animation");
        GenresList.add(newGenre);
        newGenre = new myGenre("Kids");
        GenresList.add(newGenre);
        newGenre = new myGenre("Western");
        GenresList.add(newGenre);
        newGenre = new myGenre("TV Movie");
        GenresList.add(newGenre);
    }

    public List<myGenre> getGenresList() {
        return GenresList;
    }

    public void setGenresList(List<myGenre> genresList) {
        GenresList = genresList;
    }

    // metoda cauta fiecare gen in baza de date de view-urilor ale show-urilor
    // si calculeaza numarul total de view-uri pentru acel gen
    public void calculatePopular(ShowsList myShows, UsersList myUsers) {
        // parcurg toate genurile
        for (myGenre genre : GenresList) {
            // parcurg toate genurile fiecarui show
            for (Show show : myShows.getShowsList()) {
                // caut in lista de genuri ale show-ului
                for (String genreShow : show.getGenres()) {
                    if (genreShow.equals(genre.getNameGenre())) {
                        // cate view-uri are acel show
                        int views = show.sumViews(myUsers);
                        int actualGenreView = genre.getNumberViews();
                        actualGenreView += views;
                        genre.setNumberViews(actualGenreView);
                        // la un show nu poate aparea acelasi gen de 2 ori
                        break;
                    }
                }
            }
        }
    }

    // sorteaza dupa numarul de view-uri o lista de genuri
    public void searchPopularSort() {
        Collections.sort(GenresList, new Comparator<myGenre>() {
            @Override
            public int compare(myGenre g1, myGenre g2) {
                return Double.compare(g2.getNumberViews(), g1.getNumberViews());
            }
        });
    }

    // returneaza primul show nevazut din cel mai popular gen
    // ale unui user dat ca si parametru
    public String popularShow(ShowsList myShows, User user) {
        // pentru fiecare gen caut toate show-urile cu acel gen
        for (myGenre genre : getGenresList()) {
            for (Show show : myShows.getShowsList()) {
                // show-ul e din acel gen
                if (show.presentGenre(genre.getNameGenre())) {
                    // daca user-ul nu a vazut acel show
                    if (!user.getHistory().containsKey(show.getTitle())) {
                        return show.getTitle();
                    }
                }
            }
        }
        return null;
    }
}
