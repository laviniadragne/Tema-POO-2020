package myclasses;

import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActorsList {

    private List<Actor> ActorsList;

    public ActorsList(List<Actor> actorsList) {
        ActorsList = actorsList;
    }

    public List<Actor> getActorsList() {
        return ActorsList;
    }

    public void setActorList(List<Actor> actorsList) {
        ActorsList = actorsList;
    }

    // sorteaza dupa numarul de premii, dupa ordinea din input
    public void awardsSort(String criteria, List<Actor> actors) {
        if (criteria.equals("asc")) {
            actors.sort((a1, a2) -> {
                if (a1.getNumberAwards() != a2.getNumberAwards()) {
                    return Double.compare(a1.getNumberAwards(), a2.getNumberAwards());
                }
                return a1.getName().compareTo(a2.getName());
            });

        }
        if (criteria.equals("desc")) {
            actors.sort((a1, a2) -> {
                if (a1.getNumberAwards() != a2.getNumberAwards()) {
                    return Double.compare(a2.getNumberAwards(), a1.getNumberAwards());
                }
                return a2.getName().compareTo(a1.getName());
            });

        }
    }
    // sorteaza dupa ordinea alfabetica a numelor
    public void filterdescriptionSort(String criteria, List<Actor> actors) {
        if (criteria.equals("asc")) {
            actors.sort(Comparator.comparing(Actor::getName));

        }
        if (criteria.equals("desc")) {
            actors.sort((a1, a2) -> a2.getName().compareTo(a1.getName()));

        }
    }

    // sorteaza dupa rating si dupa nume
    public void ratingSort(String criteria, List<Actor> actors) {
        if (criteria.equals("asc")) {
            actors.sort((a1, a2) -> {
                if (a1.getRating() != a2.getRating()) {
                    return Double.compare(a1.getRating(), a2.getRating());
                }
                return a1.getName().compareTo(a2.getName());
            });
        }
        // actorii trebuie ordonati descrescator
        if (criteria.equals("desc")) {
            actors.sort((a1, a2) -> {
                if (a1.getRating() != a2.getRating()) {
                    return Double.compare(a2.getRating(), a1.getRating());
                }
                return a2.getName().compareTo(a1.getName());
            });
        }
    }

    // scrie intr-un string numele tuturor actorilor
    // cu premiile cerute
    public List<String> writeSortedNames (List<Actor> actor) {
        List<String> listWrite = new ArrayList<>();
        int cnt = 0;
        while(cnt < actor.size()) {
            listWrite.add(actor.get(cnt).getName());
            cnt++;
        }
        return listWrite;
    }

    // scrie intr-un string primii N actori, sortati dupa rating
    public List<String> writeSortedNName (List <Actor> actor, ActionInputData action) {
        // adaug actorii cu rating-ul diferit de 0
        // cazul in care e mai mare N-ul decat numarul de rating-uri
        // diferite de 0
        List<String> listWrite = new ArrayList<>();
        int i = 0, j = 0;
        while (i < actor.size() && j < action.getNumber()) {
            if (actor.get(i).getRating() != 0) {
                listWrite.add(actor.get(i).getName());
                j++;
            }
            i++;
        }
        return listWrite;
    }
}
