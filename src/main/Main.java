package main;

import actor.ActorsAwards;
import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import entertainment.Season;
import fileio.*;
import myclasses.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation

        List<ActionInputData> actionList = input.getCommands();
        List<ActorInputData>  actorList = input.getActors();
        List<MovieInputData>  movieList = input.getMovies();
        List<SerialInputData> serialList = input.getSerials();
        List<UserInputData>   userList = input.getUsers();

        // imi declar propriile liste de clase
        List<User> myUsers = new ArrayList<>();
        List<Action> myActions = new ArrayList<>();
        List<Actor> myActors = new ArrayList<>();
        List<Movie> myMovies = new ArrayList<>();
        List<Serial> mySerials = new ArrayList<>();
        List<Show> myShows = new ArrayList<>();

        for (UserInputData userInput : userList) {
            String username = userInput.getUsername();
            String subscriptionType = userInput.getSubscriptionType();
            Map<String, Integer> history = userInput.getHistory();
            ArrayList<String> favoriteMovies = userInput.getFavoriteMovies();

            // aduc informatia din input in clasa mea
            User newUser = new User(username, subscriptionType, history, favoriteMovies);
            myUsers.add(newUser);
        }

        int index = 0;
        for (ActorInputData actorInput : actorList) {
            String actorname = actorInput.getName();
            String description = actorInput.getCareerDescription();
            Map<ActorsAwards, Integer> awards = actorInput.getAwards();
            ArrayList<String> filmography = actorInput.getFilmography();

            // aduc informatia din input in clasa mea
            Actor newActor = new Actor(actorname, description, filmography, awards, index);
            newActor.filterShow(filmography, movieList, serialList);
            myActors.add(newActor);
            index++;
        }

        int index1 = 0;
        for (MovieInputData movieInput : movieList) {
            String title = movieInput.getTitle();
            ArrayList<String> cast = movieInput.getCast();
            ArrayList<String> genre = movieInput.getGenres();
            int year = movieInput.getYear();
            int duration = movieInput.getDuration();

            Movie newMovie = new Movie(title, cast, genre, year, duration, index1);
            myMovies.add(newMovie);
            index1++;
        }
        for (SerialInputData serialInput : serialList) {
            String title = serialInput.getTitle();
            ArrayList<String> cast = serialInput.getCast();
            ArrayList<String> genres = serialInput.getGenres();
            int numseason = serialInput.getNumberSeason();
            ArrayList<Season> seasons = serialInput.getSeasons();
            int year = serialInput.getYear();

            Serial newSerial = new Serial(title, cast, genres, numseason, seasons, year, index1);
            mySerials.add(newSerial);
            index1++;
        }

        myShows.addAll(myMovies);
        myShows.addAll(mySerials);

        for (ActionInputData action : actionList) {
            if (action.getActionType().equals("command")) {
                if (action.getType().equals("favorite")) {
                    // caut in userii mei numele celui dat
                    for (User user : myUsers) {
                        // am gasit utilizatorul
                        if (user.getUsername().equals(action.getUsername())) {
                            // apelez pentrul el comanda de favourite
                            String message = user.addFavourite(action.getTitle());
                            JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                    "null", message);
                            arrayResult.add(newObj);
                            break;
                        }
                    }
                }
                if (action.getType().equals("view")) {
                    // caut in userii mei numele celui dat
                    for (User user : myUsers) {
                        // am gasit utilizatorul
                        if (user.getUsername().equals(action.getUsername())) {
                            // apelez pentrul el comanda de favourite
                            String message = user.addView(action.getTitle());
                            JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                    "null", message);
                            arrayResult.add(newObj);
                            break;
                        }
                    }
                }
                if (action.getType().equals("rating")) {
                    // caut in userii mei numele celui dat
                    for (User user : myUsers) {
                        // am gasit utilizatorul
                        if (user.getUsername().equals(action.getUsername())) {
                            // apelez pentrul el comanda de rating
                            String message = user.addRating(action.getTitle(), action.getGrade(), action.getSeasonNumber(), myMovies, mySerials);
                            JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                    "null", message);
                            arrayResult.add(newObj);
                            break;
                        }
                    }
                }
            } else {
                if (action.getActionType().equals("recommendation")) {
                    ActorsList myActorsClass = new ActorsList(myActors);
                    UsersList myUsersClass = new UsersList(myUsers);
                    MoviesList myMoviesClass = new MoviesList(myMovies);
                    SerialsList mySerialsClass = new SerialsList(mySerials);
                    ShowsList myShowsClass = new ShowsList(myShows);

                    if (action.getType().equals("standard")) {
                        String message = new String();
                        String username = action.getUsername();
                        User user = myUsersClass.getUser(username);
                        myShowsClass.indexdbSort();
                        String unseenVideo = user.unseen(myShowsClass.getShowsList());



                        if (unseenVideo == null) {
                            message = message + "StandardRecommendation cannot be applied!";
                        } else {
                            message = message + "StandardRecommendation result: ";
                            message = message + unseenVideo;
                        }
                        JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                "null", message);
                        arrayResult.add(newObj);


                    }

                    if (action.getType().equals("best_unseen")) {

                        myShowsClass.bestunseenSort();
                        String message = new String();
                        String username = action.getUsername();
                        User user = myUsersClass.getUser(username);
                        String unseenVideo = user.unseen(myShowsClass.getShowsList());
                        if (unseenVideo == null) {
                            message = message + "BestRatedUnseenRecommendation cannot be applied!";
                        } else {
                            message = message + "BestRatedUnseenRecommendation result: ";
                            message = message + unseenVideo;
                        }
                        JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                "null", message);
                        arrayResult.add(newObj);
                    }
                    if (action.getType().equals("search")) {

                        String message = new String();
                        // verific daca userul e premium
                        User premiumUser = myUsersClass.getUser(action.getUsername());
                        if (premiumUser.getSubscriptionType().equals("PREMIUM")) {
                            ShowsList classshowsFind = new ShowsList(myShows);
                            // caut in baza de date filmele cu acel gen
                            List<Show> showsFind = myShowsClass.searchGenre(action.getGenre());
                            classshowsFind.setShowsList(showsFind);


                            String username = action.getUsername();
                            User user = myUsersClass.getUser(username);
                            List<Show> searchVideo = user.searchList(classshowsFind.getShowsList());
                            classshowsFind.setShowsList(searchVideo);
                            classshowsFind.searchunseenSort();

                            if (classshowsFind.getShowsList().size() == 0) {
                                message = message + "SearchRecommendation cannot be applied!";
                            } else {
                                message = message + "SearchRecommendation result: ";
                                message = message + classshowsFind.getshowsName();
                            }
                        } else {
                            message = message + "SearchRecommendation cannot be applied!";
                        }
                        JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                "null", message);
                        arrayResult.add(newObj);
                    }

                    if (action.getType().equals("favorite")) {

                        String message = new String();
                        // verific daca userul e premium
                        User premiumUser = myUsersClass.getUser(action.getUsername());
                        if (premiumUser.getSubscriptionType().equals("PREMIUM")) {

                            // imi creez o copie a clasei de seriale
                            ShowsList classshowsFav = new ShowsList(myShows);

                            // adaug in de pe listele de fav ale userilor in campurile de la show
                            // totalfav
                            myUsersClass.addlistFav(myShows);

                            // ordonez serialele
                            classshowsFav.searchfavoriteSort();

                            // primul video nevazut
                            String nameFav = premiumUser.unseen(classshowsFav.getShowsList());

                            if (nameFav == null) {
                                message = message + "FavoriteRecommendation cannot be applied!";
                            } else {
                                message = message + "FavoriteRecommendation result: ";
                                message = message + nameFav;
                            }
                        } else {
                            message = message + "FavoriteRecommendation cannot be applied!";
                        }
                        JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                "null", message);
                        arrayResult.add(newObj);
                    }
                    if (action.getType().equals("popular")) {

                        String message = new String();
                        // verific daca userul e premium
                        User premiumUser = myUsersClass.getUser(action.getUsername());
                        if (premiumUser.getSubscriptionType().equals("PREMIUM")) {

                            List<myGenre> listGenres = new ArrayList<>();
                            GenresList myGenresClass = new GenresList(listGenres);
                            // calculez pentru fiecare gen in parte numarul de views
                            myGenresClass.calculatePopular(myShowsClass, myUsersClass);
                            // sortez lista de genuri
                            myGenresClass.searchPopularSort();
                            // sortez show-urile dupa ordinea din input
                            myShowsClass.indexdbSort();
                            String nameShow = myGenresClass.popularShow(myShowsClass, premiumUser);

                            if (nameShow == null) {
                                message = message + "PopularRecommendation cannot be applied!";
                            } else {
                                message = message + "PopularRecommendation result: ";
                                message = message + nameShow;
                            }
                        } else {
                            message = message + "PopularRecommendation cannot be applied!";
                        }
                        JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                "null", message);
                        arrayResult.add(newObj);
                    }

                } else {

                    if (action.getActionType().equals("query")) {
                        if (action.getObjectType().equals("actors")) {
                            ActorsList myActorsClass = new ActorsList(myActors);
                            UsersList myUsersClass = new UsersList(myUsers);
                            MoviesList myMoviesClass = new MoviesList(myMovies);
                            SerialsList mySerialsClass = new SerialsList(mySerials);
                            if (action.getCriteria().equals("filter_description")) {
                                List<String> queryfilterActors = new LinkedList<>();
                                // iterez prin lista de actori
                                for (Actor actor : myActors) {
                                    // a 3-a lista este cea care filtreaza dupa cuvinte
                                    String message = actor.filterDescription(action.getFilters());
                                    if (message != null) {
                                        queryfilterActors.add(actor.getName());
                                    }
                                }
                                // sortez lista de actori
                                String sortType = action.getSortType();
                                if (sortType.equals("asc")) {
                                    Collections.sort(queryfilterActors);
                                } else {
                                    if (sortType.equals("desc")) {
                                        Collections.sort(queryfilterActors, Collections.reverseOrder());
                                    }
                                }
                                // scrie lista queryfilterAct in arrayout
                                String message = new String("Query result: [");
                                int i;
                                for (i = 0; i < queryfilterActors.size() - 1; i++) {
                                    message = message + queryfilterActors.get(i) + ", ";
                                }
                                if (queryfilterActors.size() != 0) {
                                    message = message + queryfilterActors.get(queryfilterActors.size() - 1);
                                }
                                message = message + "]";
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }

                            if (action.getCriteria().equals("awards")) {
                                List<Actor> queryawardsActors = new LinkedList<>();
                                List<String> filters = action.getFilters().get(3);
                                for (Actor actor : myActors) {
                                    // a 4-a lista este cea care filtreaza dupa awards
                                    int totalAwords = actor.awards(filters);
                                    if (totalAwords != 0) {
                                        queryawardsActors.add(actor);
                                    }
                                }

                                // sortez lista de actori
                                String sortType = action.getSortType();
                                myActorsClass.awardsSort(action.getSortType(), queryawardsActors);
                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = myActorsClass.writeSortedAwards(queryawardsActors);
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);


                            }
                            if (action.getCriteria().equals("average")) {

                                // calculez rating-ul pentru fiecare actor
                                for (Actor actor : myActors) {
                                    actor.calculateRating(myMovies, mySerials);
                                }

                                if (action.getSortType().equals("asc")) {
                                    Collections.sort(myActors, new Comparator<Actor>() {
                                        @Override
                                        public int compare(Actor a1, Actor a2) {
                                            if (a1.getRating() != a2.getRating()) {
                                                return Double.compare(a1.getRating(), a2.getRating());
                                            }
                                            return a1.getName().compareTo(a2.getName());
                                        }
                                    });
                                }
                                // actorii trebuie ordonati descrescator
                                if (action.getSortType().equals("desc")) {
                                    Collections.sort(myActors, new Comparator<Actor>() {
                                        @Override
                                        public int compare(Actor a1, Actor a2) {
                                            if (a1.getRating() != a2.getRating()) {
                                                return Double.compare(a2.getRating(), a1.getRating());
                                            }
                                            return a2.getName().compareTo(a1.getName());
                                        }
                                    });
                                }

                                String message = new String("Query result: ");
                                // adaug actorii cu rating-ul diferit de 0
                                // cazul in care e mai mare N-ul decat numarul de rating-uri
                                // diferite de 0
                                List<String> listWrite = new ArrayList<>();
                                int i = 0, j = 0;
                                while (i < myActors.size() && j < action.getNumber()) {
                                    if (myActors.get(i).getRating() != 0) {
                                        listWrite.add(myActors.get(i).getName());
                                        j++;
                                    }
                                    i++;
                                }
                                message = message + listWrite;
                                // adaug si ultimul actor cu rating-ul diferit de 0
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                        }
                        if (action.getObjectType().equals("movies")) {
                            ActorsList myActorsClass = new ActorsList(myActors);
                            UsersList myUsersClass = new UsersList(myUsers);
                            MoviesList myMoviesClass = new MoviesList(myMovies);
                            SerialsList mySerialsClass = new SerialsList(mySerials);
                            if (action.getCriteria().equals("ratings")) {
                                myMoviesClass.ratingsort(action.getSortType());
                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = myMoviesClass.writeSortedMovies(action.getNumber(), action.getFilters());
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                            if (action.getCriteria().equals("favorite")) {
                                myMoviesClass.favoritesort(myUsersClass, action.getSortType());


                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = myMoviesClass.writeFavAparition(action.getNumber(), action.getFilters(), myUsersClass);
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                            if (action.getCriteria().equals("longest")) {
                                myMoviesClass.durationSort(action.getSortType());
                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = myMoviesClass.writeLongestMovies(action.getNumber(), action.getFilters());
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                            if (action.getCriteria().equals("most_viewed")) {
                                myMoviesClass.viewsSort(action.getSortType(), myUsersClass);
                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = myMoviesClass.writeMostView(action.getNumber(), action.getFilters(), myUsersClass);
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                        }
                        if (action.getObjectType().equals("shows")) {
                            ActorsList myActorsClass = new ActorsList(myActors);
                            UsersList myUsersClass = new UsersList(myUsers);
                            MoviesList myMoviesClass = new MoviesList(myMovies);
                            SerialsList mySerialsClass = new SerialsList(mySerials);
                            if (action.getCriteria().equals("ratings")) {
                                mySerialsClass.ratingsort(action.getSortType());
                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = mySerialsClass.writeSortedSerials(action.getNumber(), action.getFilters());
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                            if (action.getCriteria().equals("favorite")) {
                                mySerialsClass.favoritesort(myUsersClass, action.getSortType());
                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = mySerialsClass.writeFavAparition(action.getNumber(), action.getFilters(), myUsersClass);
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                            if (action.getCriteria().equals("longest")) {
                                mySerialsClass.durationSort(action.getSortType());
                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = mySerialsClass.writeLongestSerials(action.getNumber(), action.getFilters());
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                            if (action.getCriteria().equals("most_viewed")) {
                                mySerialsClass.viewsSort(action.getSortType(), myUsersClass);
                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = mySerialsClass.writeMostView(action.getNumber(), action.getFilters(), myUsersClass);
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                        }
                        if (action.getObjectType().equals("users")) {
                            ActorsList myActorsClass = new ActorsList(myActors);
                            UsersList myUsersClass = new UsersList(myUsers);
                            MoviesList myMoviesClass = new MoviesList(myMovies);
                            SerialsList mySerialsClass = new SerialsList(mySerials);
                            if (action.getCriteria().equals("num_ratings")) {
                                myUsersClass.ratingsSort(action.getSortType());
                                String message = new String();
                                message = message + "Query result: ";
                                List<String> message1 = myUsersClass.writeRating(action.getNumber());
                                message = message + message1;
                                JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                                        "null", message);
                                arrayResult.add(newObj);
                            }
                        }
                    }
                }
            }
        }
        fileWriter.closeJSON(arrayResult);
    }
}

