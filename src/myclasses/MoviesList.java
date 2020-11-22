package myclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MoviesList {

    private List<Movie> MoviesList;

    public MoviesList(List<Movie> moviesList) {

        MoviesList = moviesList;
    }

    public List<Movie> getMoviesList() {
        return MoviesList;
    }

    public void setMovieList(List<Movie> moviesList) {

        MoviesList = moviesList;
    }

    public Movie getMovie(String name) {
        for (Movie movie : MoviesList) {
            if (movie.getTitle().equals(name)) {
                return movie;
            }
        }
        return null;
    }
    public void ratingsort(String criteria) {
        if (criteria.equals("asc")) {
            Collections.sort(MoviesList, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if (m1.sumRatings() != m2.sumRatings()) {
                        return Double.compare (m1.sumRatings(), m2.sumRatings());
                    }
                    return m1.getTitle().compareTo(m2.getTitle());
                }
            });

        }
        if (criteria.equals("desc")) {
            Collections.sort(MoviesList, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if (m1.sumRatings() != m2.sumRatings()) {
                        return Double.compare (m2.sumRatings(), m1.sumRatings());
                    }
                    return m2.getTitle().compareTo(m1.getTitle());
                }
            });

        }
    }
    // compar dupa numarul de aparitii in lista de favorite
    public void favoritesort(UsersList myUsers, String criteria) {
        if (criteria.equals("asc")) {
            Collections.sort(MoviesList, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if (m1.aparitionFavorite(myUsers) != m2.aparitionFavorite(myUsers)) {
                        return Double.compare(m1.aparitionFavorite(myUsers), m2.aparitionFavorite(myUsers));
                    }
                    return m1.getTitle().compareTo(m2.getTitle());
                }
            });
        }
        if (criteria.equals("desc")) {
            Collections.sort(MoviesList, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if (m1.aparitionFavorite(myUsers) != m2.aparitionFavorite(myUsers)) {
                        return Double.compare(m2.aparitionFavorite(myUsers), m1.aparitionFavorite(myUsers));
                    }
                    return m2.getTitle().compareTo(m1.getTitle());
                }
            });
        }
    }
    // compar dupa durata lor
    public void durationSort(String criteria) {
        if (criteria.equals("asc")) {
            Collections.sort(MoviesList, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if (m1.getDuration() != m2.getDuration()) {
                        return Double.compare(m1.getDuration(), m2.getDuration());
                    }
                    return m1.getTitle().compareTo(m2.getTitle());
                }
            });
        }
        if (criteria.equals("desc")) {
            Collections.sort(MoviesList, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if (m1.getDuration() != m2.getDuration()) {
                        return Double.compare(m2.getDuration(), m1.getDuration());
                    }
                    return m2.getTitle().compareTo(m1.getTitle());
                }
            });
        }
    }
    public void viewsSort(String criteria, UsersList myUsersClass) {
        if (criteria.equals("asc")) {
            Collections.sort(MoviesList, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if (m1.sumViews(myUsersClass) != m2.sumViews(myUsersClass)) {
                        return Double.compare (m1.sumViews(myUsersClass), m2.sumViews(myUsersClass));
                    }
                    return m1.getTitle().compareTo(m2.getTitle());
                }
            });

        }
        if (criteria.equals("desc")) {
            Collections.sort(MoviesList, new Comparator<Movie>() {
                @Override
                public int compare(Movie m1, Movie m2) {
                    if (m1.sumViews(myUsersClass) != m2.sumViews(myUsersClass)) {
                        return Double.compare (m2.sumViews(myUsersClass), m1.sumViews(myUsersClass));
                    }
                    return m2.getTitle().compareTo(m1.getTitle());
                }
            });

        }
    }

    // scrie intr-un string pe primele N filme cu rating-ul
    // diferit de 0
    public List<String> writeSortedMovies (int n, List<List<String>> filters) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while(cnt < MoviesList.size() && i < n) {
            if (MoviesList.get(cnt).sumRatings() != 0) {
                // daca are filtrele precizate
                if (MoviesList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(MoviesList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }

    // scrie intr-un string pe primele n filme in functie de favorite
    public List<String> writeFavAparition (int n, List<List<String>> filters, UsersList myUsers) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while(cnt < MoviesList.size() && i < n) {
            if (MoviesList.get(cnt).aparitionFavorite(myUsers) != 0) {
                // daca are filtrele precizate
                if (MoviesList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(MoviesList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }

    // scrie intr-un string pe primele N filme cu rating-ul
    // diferit de 0
    public List<String> writeLongestMovies (int n, List<List<String>> filters) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while(cnt < MoviesList.size() && i < n) {
            if (MoviesList.get(cnt).getDuration() != 0) {
                // daca are filtrele precizate
                if (MoviesList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(MoviesList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }
    // scrie intr-un string pe primele n filme in functie de favorite
    public List<String> writeMostView (int n, List<List<String>> filters, UsersList myUsers) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while(cnt < MoviesList.size() && i < n) {
            if (MoviesList.get(cnt).sumViews(myUsers) != 0) {
                // daca are filtrele precizate
                if (MoviesList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(MoviesList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }
}
