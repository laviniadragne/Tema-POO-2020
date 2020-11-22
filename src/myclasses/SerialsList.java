package myclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SerialsList {

    private List<Serial> SerialsList;

    public SerialsList(List<Serial> serialsList) {
        SerialsList = serialsList;
    }

    public List<Serial> getSerialsList() {
        return SerialsList;
    }

    public void setSerialsList(List<Serial> serialsList) {
        SerialsList = serialsList;
    }

    public Serial getSerial(String name) {
        for (Serial serial : SerialsList) {
            if (serial.getTitle().equals(name)) {
                return serial;
            }
        }
        return null;
    }


    public void ratingsort(String criteria) {
        if (criteria.equals("asc")) {
            Collections.sort(SerialsList, new Comparator<Serial>() {
                @Override
                public int compare(Serial m1, Serial m2) {
                    if (m1.sumRatings() != m2.sumRatings()) {
                        return Double.compare (m1.sumRatings(), m2.sumRatings());
                    }
                    return m1.getTitle().compareTo(m2.getTitle());
                }
            });

        }
        if (criteria.equals("desc")) {
            Collections.sort(SerialsList, new Comparator<Serial>() {
                @Override
                public int compare(Serial m1, Serial m2) {
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
            Collections.sort(SerialsList, new Comparator<Serial>() {
                @Override
                public int compare(Serial m1, Serial m2) {
                    if (m1.aparitionFavorite(myUsers) != m2.aparitionFavorite(myUsers)) {
                        return Double.compare(m1.aparitionFavorite(myUsers), m2.aparitionFavorite(myUsers));
                    }
                    return m1.getTitle().compareTo(m2.getTitle());
                }
            });
        }
        if (criteria.equals("desc")) {
            Collections.sort(SerialsList, new Comparator<Serial>() {
                @Override
                public int compare(Serial m1, Serial m2) {
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
            Collections.sort(SerialsList, new Comparator<Serial>() {
                @Override
                public int compare(Serial s1, Serial s2) {
                    if (s1.sumDuration() != s2.sumDuration()) {
                        return Double.compare(s1.sumDuration(), s2.sumDuration());
                    }
                    return s1.getTitle().compareTo(s2.getTitle());
                }
            });
        }
        if (criteria.equals("desc")) {
            Collections.sort(SerialsList, new Comparator<Serial>() {
                @Override
                public int compare(Serial s1, Serial s2) {
                    if (s1.sumDuration() != s2.sumDuration()) {
                        return Double.compare(s2.sumDuration(), s1.sumDuration());
                    }
                    return s2.getTitle().compareTo(s1.getTitle());
                }
            });
        }
    }
    // sorteaza dupa nr de vizualizari
    public void viewsSort(String criteria, UsersList myUsersClass) {
        if (criteria.equals("asc")) {
            Collections.sort(SerialsList, new Comparator<Serial>() {
                @Override
                public int compare(Serial s1, Serial s2) {
                    if (s1.sumViews(myUsersClass) != s2.sumViews(myUsersClass)) {
                        return Double.compare (s1.sumViews(myUsersClass), s2.sumViews(myUsersClass));
                    }
                    return s1.getTitle().compareTo(s2.getTitle());
                }
            });

        }
        if (criteria.equals("desc")) {
            Collections.sort(SerialsList, new Comparator<Serial>() {
                @Override
                public int compare(Serial s1, Serial s2) {
                    if (s1.sumViews(myUsersClass) != s2.sumViews(myUsersClass)) {
                        return Double.compare (s2.sumViews(myUsersClass), s1.sumViews(myUsersClass));
                    }
                    return s2.getTitle().compareTo(s1.getTitle());
                }
            });

        }
    }
    // scrie intr-un string pe primele n filme in functie de favorite
    public List<String> writeFavAparition (int n, List<List<String>> filters, UsersList myUsers ) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while(cnt < SerialsList.size() && i < n) {
            if (SerialsList.get(cnt).aparitionFavorite(myUsers) != 0) {
                // daca are filtrele precizate
                if (SerialsList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(SerialsList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }
    // scrie intr-un string pe primele N filme cu rating-ul
    // diferit de 0
    public List<String> writeSortedSerials (int n, List<List<String>> filters) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while(cnt < SerialsList.size() && i < n) {
            if (SerialsList.get(cnt).sumRatings() != 0) {
                // daca are filtrele precizate
                if (SerialsList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(SerialsList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }
    // scrie intr-un string pe primele N seriale sortate dupa durata
    public List<String> writeLongestSerials (int n, List<List<String>> filters) {
        List<String> listWrite = new ArrayList<>();
        int i = 0;
        int cnt = 0;
        while(cnt < SerialsList.size() && i < n) {
            if (SerialsList.get(cnt).sumDuration() != 0) {
                // daca are filtrele precizate
                if (SerialsList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(SerialsList.get(cnt).getTitle());
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
        while(cnt < SerialsList.size() && i < n) {
            if (SerialsList.get(cnt).sumViews(myUsers) != 0) {
                // daca are filtrele precizate
                if (SerialsList.get(cnt).filtersVideos(filters)) {
                    listWrite.add(SerialsList.get(cnt).getTitle());
                    i++;
                }
            }
            cnt++;
        }
        return listWrite;
    }
}
