package myclasses;

import net.sf.json.util.JSONUtils;

import java.util.ArrayList;
import java.util.Collections;
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

    public Actor getActor(String name) {
        for (Actor actor : ActorsList) {
            if(actor.getName().equals(name)) {
                return actor;
            }
        }
        return null;
    }
    // sorteaza dupa numarul de premii, dupa ordinea din input
    public void awardsSort(String criteria, List<Actor> actors) {
        if (criteria.equals("asc")) {
            Collections.sort(actors, new Comparator<Actor>() {
                @Override
                public int compare(Actor a1, Actor a2) {
                    if (a1.getNumberAwards() != a2.getNumberAwards()) {
                        return Double.compare (a1.getNumberAwards(), a2.getNumberAwards());
                    }
                    return a1.getName().compareTo(a2.getName());
                }
            });

        }
        if (criteria.equals("desc")) {
            Collections.sort(actors, new Comparator<Actor>() {
                @Override
                public int compare(Actor a1, Actor a2) {
                    if (a1.getNumberAwards() != a2.getNumberAwards()) {
                        return Double.compare (a2.getNumberAwards(), a1.getNumberAwards());
                    }
                    return a2.getName().compareTo(a1.getName());
                }
            });

        }
    }
    // scrie intr-un string numele tuturor actorilor
    // cu premiile cerute
    public List<String> writeSortedAwards (List<Actor> actor) {
        List<String> listWrite = new ArrayList<>();
        int cnt = 0;
        while(cnt < actor.size()) {
            listWrite.add(actor.get(cnt).getName());
            cnt++;
        }
        return listWrite;
    }
}
