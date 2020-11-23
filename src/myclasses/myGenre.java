package myclasses;

public class myGenre {

    private String nameGenre;
    private int numberViews;

    public myGenre(String nameGenre) {
        this.nameGenre = nameGenre;
        this.numberViews = 0;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public int getNumberViews() {
        return numberViews;
    }

    public void setNumberViews(int numberViews) {
        this.numberViews = numberViews;
    }

}
