package myclasses;

import java.util.ArrayList;
import java.util.List;

public class MoviesList {

    private List<Movie> moviesList;

    public MoviesList(final List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     * Intoarce lista de filme
     */
    public List<Movie> getMoviesList() {
        return moviesList;
    }

    /**
     * Seteaza lista de filme
     */
    public void setMovieList(final List<Movie> inputmoviesList) {

        this.moviesList = inputmoviesList;
    }

    /**
     * Intoarce filmul corespunzator numelui primit
     */
    public Movie getMovie(final String name) {
        for (Movie movie : moviesList) {
            if (movie.getTitle().equals(name)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Sortare a listei de filme dupa rating si nume
     */
    public void ratingsort(final String criteria) {
        if (criteria.equals("asc")) {
            moviesList.sort((m1, m2) -> {
                if (m1.sumRatings() != m2.sumRatings()) {
                    return Double.compare(m1.sumRatings(), m2.sumRatings());
                }
                return m1.getTitle().compareTo(m2.getTitle());
            });

        }
        if (criteria.equals("desc")) {
            moviesList.sort((m1, m2) -> {
                if (m1.sumRatings() != m2.sumRatings()) {
                    return Double.compare(m2.sumRatings(), m1.sumRatings());
                }
                return m2.getTitle().compareTo(m1.getTitle());
            });

        }
    }

    /**
     * Compar dupa numarul de aparitii in lista de favorite
     * */
    public void favoritesort(final UsersList myUsers, final String criteria) {
        if (criteria.equals("asc")) {
            moviesList.sort((m1, m2) -> {
                if (m1.aparitionFavorite(myUsers) != m2.aparitionFavorite(myUsers)) {
                    return Double.compare(m1.aparitionFavorite(myUsers),
                            m2.aparitionFavorite(myUsers));
                }
                return m1.getTitle().compareTo(m2.getTitle());
            });
        }
        if (criteria.equals("desc")) {
            moviesList.sort((m1, m2) -> {
                if (m1.aparitionFavorite(myUsers) != m2.aparitionFavorite(myUsers)) {
                    return Double.compare(m2.aparitionFavorite(myUsers),
                            m1.aparitionFavorite(myUsers));
                }
                return m2.getTitle().compareTo(m1.getTitle());
            });
        }
    }

    /**
     * Sortare dupa durata lor si nume
     */
    public void durationSort(final String criteria) {
        if (criteria.equals("asc")) {
            moviesList.sort((m1, m2) -> {
                if (m1.getDuration() != m2.getDuration()) {
                    return Double.compare(m1.getDuration(), m2.getDuration());
                }
                return m1.getTitle().compareTo(m2.getTitle());
            });
        }
        if (criteria.equals("desc")) {
            moviesList.sort((m1, m2) -> {
                if (m1.getDuration() != m2.getDuration()) {
                    return Double.compare(m2.getDuration(), m1.getDuration());
                }
                return m2.getTitle().compareTo(m1.getTitle());
            });
        }
    }

    /**
     * Sortare a listei de filme dupa numarul de vizualizari si nume
     */
    public void viewsSort(final String criteria, final UsersList myUsersClass) {
        if (criteria.equals("asc")) {
            moviesList.sort((m1, m2) -> {
                if (m1.sumViews(myUsersClass) != m2.sumViews(myUsersClass)) {
                    return Double.compare(m1.sumViews(myUsersClass), m2.sumViews(myUsersClass));
                }
                return m1.getTitle().compareTo(m2.getTitle());
            });

        }
        if (criteria.equals("desc")) {
            moviesList.sort((m1, m2) -> {
                if (m1.sumViews(myUsersClass) != m2.sumViews(myUsersClass)) {
                    return Double.compare(m2.sumViews(myUsersClass), m1.sumViews(myUsersClass));
                }
                return m2.getTitle().compareTo(m1.getTitle());
            });

        }
    }

    /**
     * Scrie intr-un string primele N filme cu rating-ul
     * diferit de 0
     */
    public List<String> writeSortedMovies(final int n, final List<List<String>> filters) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while (cnt < moviesList.size() && i < n) {
            if (moviesList.get(cnt).sumRatings() != 0) {
                // daca are filtrele precizate
                if (moviesList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(moviesList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }

    /**
     * Scrie intr-un string primele n filme in functie de favorite
     */
    public List<String> writeFavAparition(final int n, final List<List<String>> filters,
                                          final UsersList myUsers) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while (cnt < moviesList.size() && i < n) {
            if (moviesList.get(cnt).aparitionFavorite(myUsers) != 0) {
                // daca are filtrele precizate
                if (moviesList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(moviesList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }

    /**
     * Scrie intr-un string primele N filme cu rating-ul
        diferit de 0
     */
    public List<String> writeLongestMovies(final int n, final List<List<String>> filters) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while (cnt < moviesList.size() && i < n) {
            if (moviesList.get(cnt).getDuration() != 0) {
                // daca are filtrele precizate
                if (moviesList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(moviesList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }

    /**
     * Scrie intr-un string primele n filme in functie de favorite
     */
    public List<String> writeMostView(final int n, final List<List<String>> filters,
                                      final UsersList myUsers) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while (cnt < moviesList.size() && i < n) {
            if (moviesList.get(cnt).sumViews(myUsers) != 0) {
                // daca are filtrele precizate
                if (moviesList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(moviesList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }
}
