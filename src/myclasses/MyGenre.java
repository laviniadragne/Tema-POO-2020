package myclasses;

public class MyGenre {

    private final String nameGenre;
    private int numberViews;

    public MyGenre(final String nameGenre) {
        this.nameGenre = nameGenre;
        this.numberViews = 0;
    }

    /**
     * Intoarce numele unui gen
     */
    public String getNameGenre() {
        return nameGenre;
    }

    /**
     * Intoarce numarul de vizualizari ale unui gen
     */
    public int getNumberViews() {
        return numberViews;
    }

    /**
     * Seteaza numarul de vizualizari ale unui gen
     */
    public void setNumberViews(final int numberViews) {
        this.numberViews = numberViews;
    }

}
