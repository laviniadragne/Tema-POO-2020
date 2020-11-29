package myclasses;

import java.util.List;

public class GenresList {

    private final List<MyGenre> genresList;

    public GenresList(final List<MyGenre> mygenresList) {
        this.genresList = mygenresList;
        MyGenre newGenre = new MyGenre("Action");
        genresList.add(newGenre);
        newGenre = new MyGenre("Adventure");
        genresList.add(newGenre);
        newGenre = new MyGenre("Drama");
        genresList.add(newGenre);
        newGenre = new MyGenre("Comedy");
        genresList.add(newGenre);
        newGenre = new MyGenre("Crime");
        genresList.add(newGenre);
        newGenre = new MyGenre("Romance");
        genresList.add(newGenre);
        newGenre = new MyGenre("War");
        genresList.add(newGenre);
        newGenre = new MyGenre("History");
        genresList.add(newGenre);
        newGenre = new MyGenre("Thriller");
        genresList.add(newGenre);
        newGenre = new MyGenre("Mystery");
        genresList.add(newGenre);
        newGenre = new MyGenre("Family");
        genresList.add(newGenre);
        newGenre = new MyGenre("Horror");
        genresList.add(newGenre);
        newGenre = new MyGenre("Fantasy");
        genresList.add(newGenre);
        newGenre = new MyGenre("Science Fiction");
        genresList.add(newGenre);
        newGenre = new MyGenre("Action & Adventure");
        genresList.add(newGenre);
        newGenre = new MyGenre("Sci-Fi & Fantasy");
        genresList.add(newGenre);
        newGenre = new MyGenre("Animation");
        genresList.add(newGenre);
        newGenre = new MyGenre("Kids");
        genresList.add(newGenre);
        newGenre = new MyGenre("Western");
        genresList.add(newGenre);
        newGenre = new MyGenre("TV Movie");
        genresList.add(newGenre);
    }

    /**
     * Intoarce lista cu toate genurile
     */
    public List<MyGenre> getGenresList() {
        return genresList;
    }

    /**
     * Metoda cauta fiecare gen in baza de date
     * si calculeaza numarul total de view-uri pentru acel gen
     */
    public void calculatePopular(final ShowsList myShows, final UsersList myUsers) {
        // parcurg toate genurile
        for (MyGenre genre : genresList) {
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

    /**
     * Sorteaza dupa numarul de view-uri o lista de genuri
     */
    public void searchPopularSort() {
        genresList.sort((g1, g2) -> Double.compare(g2.getNumberViews(), g1.getNumberViews()));
    }

    /**
     * Returneaza primul show nevazut din cel mai popular gen
        ale unui user dat ca si parametru
     */
    public String popularShow(final ShowsList myShows, final User user) {
        // pentru fiecare gen caut toate show-urile cu acel gen
        for (MyGenre genre : getGenresList()) {
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
