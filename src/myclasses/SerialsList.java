package myclasses;

import java.util.ArrayList;
import java.util.List;

public class SerialsList {

    private List<Serial> serialsList;

    /**
     * Construieste lista cu seriale pe baza inputului
     */
    public SerialsList(final List<Serial> inputserialsList) {
       this.serialsList = inputserialsList;
    }

    /**
     * Intoarce lista de seriale
     */
    public List<Serial> getSerialsList() {
        return serialsList;
    }

    /**
     * Seteaza lista de seriale
     */
    public void setSerialsList(final List<Serial> inputserialsList) {
        this.serialsList = inputserialsList;
    }

    /**
     * Sorteaza serialele dupa rating si nume
     */
    public void ratingsort(final String criteria) {
        if (criteria.equals("asc")) {
            serialsList.sort((m1, m2) -> {
                if (m1.sumRatings() != m2.sumRatings()) {
                    return Double.compare(m1.sumRatings(), m2.sumRatings());
                }
                return m1.getTitle().compareTo(m2.getTitle());
            });

        }
        if (criteria.equals("desc")) {
            serialsList.sort((m1, m2) -> {
                if (m1.sumRatings() != m2.sumRatings()) {
                    return Double.compare(m2.sumRatings(), m1.sumRatings());
                }
                return m2.getTitle().compareTo(m1.getTitle());
            });
        }
    }

    /**
     * Sortez dupa numarul de aparitii in lista de favorite si nume
     */
    public void favoritesort(final UsersList myUsers, final String criteria) {
        if (criteria.equals("asc")) {
            serialsList.sort((m1, m2) -> {
                if (m1.aparitionFavorite(myUsers) != m2.aparitionFavorite(myUsers)) {
                    return Double.compare(m1.aparitionFavorite(myUsers),
                                        m2.aparitionFavorite(myUsers));
                }
                return m1.getTitle().compareTo(m2.getTitle());
            });
        }
        if (criteria.equals("desc")) {
            serialsList.sort((m1, m2) -> {
                if (m1.aparitionFavorite(myUsers) != m2.aparitionFavorite(myUsers)) {
                    return Double.compare(m2.aparitionFavorite(myUsers),
                                        m1.aparitionFavorite(myUsers));
                }
                return m2.getTitle().compareTo(m1.getTitle());
            });
        }
    }

    /**
     * Sorteaza dupa durata si nume
     */
    public void durationSort(final String criteria) {
        if (criteria.equals("asc")) {
            serialsList.sort((s1, s2) -> {
                if (s1.sumDuration() != s2.sumDuration()) {
                    return Double.compare(s1.sumDuration(), s2.sumDuration());
                }
                return s1.getTitle().compareTo(s2.getTitle());
            });
        }
        if (criteria.equals("desc")) {
            serialsList.sort((s1, s2) -> {
                if (s1.sumDuration() != s2.sumDuration()) {
                    return Double.compare(s2.sumDuration(), s1.sumDuration());
                }
                return s2.getTitle().compareTo(s1.getTitle());
            });
        }
    }

    /**
     * Sorteaza dupa nr de vizualizari si nume
     */
    public void viewsSort(final String criteria, final UsersList myUsersClass) {
        if (criteria.equals("asc")) {
            serialsList.sort((s1, s2) -> {
                if (s1.sumViews(myUsersClass) != s2.sumViews(myUsersClass)) {
                    return Double.compare(s1.sumViews(myUsersClass),
                                        s2.sumViews(myUsersClass));
                }
                return s1.getTitle().compareTo(s2.getTitle());
            });

        }
        if (criteria.equals("desc")) {
            serialsList.sort((s1, s2) -> {
                if (s1.sumViews(myUsersClass) != s2.sumViews(myUsersClass)) {
                    return Double.compare(s2.sumViews(myUsersClass),
                                            s1.sumViews(myUsersClass));
                }
                return s2.getTitle().compareTo(s1.getTitle());
            });

        }
    }

    /**
     * Scrie intr-un string primele n filme in functie de favorite
     */
    public List<String> writeFavAparition(final int n, final List<List<String>> filters,
                                          final UsersList myUsers) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while (cnt < serialsList.size() && i < n) {
            if (serialsList.get(cnt).aparitionFavorite(myUsers) != 0) {
                // daca are filtrele precizate
                if (serialsList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(serialsList.get(cnt).getTitle());
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
    public List<String> writeSortedSerials(final int n, final List<List<String>> filters) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while (cnt < serialsList.size() && i < n) {
            if (serialsList.get(cnt).sumRatings() != 0) {
                // daca are filtrele precizate
                if (serialsList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(serialsList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }

    /**
     * Scrie intr-un string primele N seriale sortate dupa durata
     */
    public List<String> writeLongestSerials(final int n, final List<List<String>> filters) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while (cnt < serialsList.size() && i < n) {
            if (serialsList.get(cnt).sumDuration() != 0) {
                // daca are filtrele precizate
                if (serialsList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(serialsList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }

    /**
     * Scrie intr-un string primele N seriale in functie de favorite
     */
    public List<String> writeMostView(final int n, final List<List<String>> filters,
                                      final UsersList myUsers) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while (cnt < serialsList.size() && i < n) {
            if (serialsList.get(cnt).sumViews(myUsers) != 0) {
                // daca are filtrele precizate
                if (serialsList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(serialsList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }
}
