package myclasses;

public class myGenre {

    private final String nameGenre;
    private int numberViews;

    public myGenre(String nameGenre) {
        this.nameGenre = nameGenre;
        this.numberViews = 0;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public int getNumberViews() {
        return numberViews;
    }

    public void setNumberViews(int numberViews) {
        this.numberViews = numberViews;
    }

}
