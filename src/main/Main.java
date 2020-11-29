package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import myclasses.ActorsList;
import myclasses.Assign;
import myclasses.Commands;
import myclasses.MoviesList;
import myclasses.Queries;
import myclasses.Recommendations;
import myclasses.SerialsList;
import myclasses.ShowsList;
import myclasses.UsersList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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

        // imi declar propriile clase cu cate o lista in ele
        UsersList myUsersClass = Assign.assignUsers(input);
        ActorsList myActorsClass = Assign.assignActors(input);
        MoviesList myMoviesClass = Assign.assignMovies(input);
        SerialsList mySerialsClass = Assign.assignSerials(input,
                                                myMoviesClass.getMoviesList().size());
        ShowsList myShowsClass = Assign.assignShows(myMoviesClass, mySerialsClass);

        for (ActionInputData action : input.getCommands()) {
            String message = "";
            if (action.getActionType().equals("command")) {
                if (action.getType().equals("favorite")) {
                    message = Commands.favourite(myUsersClass, action);
                }
                if (action.getType().equals("view")) {
                    message = Commands.view(myUsersClass, action);
                }
                if (action.getType().equals("rating")) {
                    message = Commands.rating(myUsersClass, myMoviesClass, mySerialsClass, action);
                }
            } else {
                if (action.getActionType().equals("recommendation")) {

                    if (action.getType().equals("standard")) {
                        message = Recommendations.standard(myUsersClass, myShowsClass, action);
                    }

                    if (action.getType().equals("best_unseen")) {
                      message = Recommendations.bestunseen(myUsersClass, myShowsClass, action);
                    }
                    if (action.getType().equals("search")) {
                       message = Recommendations.search(myUsersClass, myShowsClass, action);
                    }

                    if (action.getType().equals("favorite")) {
                        message = Recommendations.favorite(myUsersClass, myShowsClass, action);
                    }
                    if (action.getType().equals("popular")) {
                        message = Recommendations.popular(myUsersClass, myShowsClass, action);
                    }
                } else {
                    if (action.getActionType().equals("query")) {
                        if (action.getObjectType().equals("actors")) {
                            if (action.getCriteria().equals("filter_description")) {
                                message = Queries.filterdescription(myActorsClass, action);
                            }
                        }
                            if (action.getCriteria().equals("awards")) {
                                message = Queries.awards(myActorsClass, action);
                            }
                            if (action.getCriteria().equals("average")) {
                                message = Queries.average(myActorsClass, mySerialsClass,
                                                            myMoviesClass, action);
                        }
                    }
                    if (action.getObjectType().equals("movies")) {
                        if (action.getCriteria().equals("ratings")) {
                            message = Queries.moviesRating(myMoviesClass, action);
                        }
                        if (action.getCriteria().equals("favorite")) {
                            message = Queries.moviesFavorite(myUsersClass, myMoviesClass, action);
                        }
                        if (action.getCriteria().equals("longest")) {
                            message = Queries.moviesLongest(myMoviesClass, action);
                        }
                        if (action.getCriteria().equals("most_viewed")) {
                            message = Queries.moviesmostViewd(myUsersClass, myMoviesClass, action);
                        }
                    }
                    if (action.getObjectType().equals("shows")) {
                        if (action.getCriteria().equals("ratings")) {
                            message = Queries.serialsRating(mySerialsClass, action);
                        }
                        if (action.getCriteria().equals("favorite")) {
                            message = Queries.serialsFavourite(myUsersClass,
                                                            mySerialsClass, action);
                        }
                        if (action.getCriteria().equals("longest")) {
                            message = Queries.serialsLongest(mySerialsClass, action);
                        }
                        if (action.getCriteria().equals("most_viewed")) {
                            message = Queries.serialsmostViewd(myUsersClass,
                                                                mySerialsClass, action);
                        }
                    }
                    if (action.getObjectType().equals("users")) {
                        if (action.getCriteria().equals("num_ratings")) {
                            message = Queries.usersRating(myUsersClass, action);
                        }
                    }
                }
            }
            JSONObject newObj = fileWriter.writeFile(action.getActionId(),
                    "null", message);

            // noinspection unchecked
            arrayResult.add(newObj);
        }
        fileWriter.closeJSON(arrayResult);
    }
}


