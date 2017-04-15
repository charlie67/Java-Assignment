package uk.ac.aber.dcs.blockmotion.gui;

/**
 * This is the template Animator to display the animation. You will
 * need to update this file
 *
 * @author Chris Loftus  Charlie Robinson
 * @version 1.0 (29th March 2017)
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.aber.dcs.blockmotion.model.Footage;
import uk.ac.aber.dcs.blockmotion.model.IFootage;
import uk.ac.aber.dcs.blockmotion.model.IFrame;
import uk.ac.aber.dcs.blockmotion.transformer.FlipVertical;
import uk.ac.aber.dcs.blockmotion.transformer.SlideLeft;
import uk.ac.aber.dcs.blockmotion.transformer.Transform;

import java.io.IOException;
import java.util.Scanner;

public class Animator extends Application {

    private Button[][] gridArray;
    private GridPane grid;
    private Thread animation;
    private boolean doRun;
    private IFootage footage = new Footage();
    private Stage stage;
    private Scene scene;
    private String fileName;
    private boolean transformationsDone = false;
    private Transform transformer;

    //for edit menu slide numbers
    private int slideRightNumber = 1;
    private int slideLeftNumber = 1;
    private int slideDownNumber = 1;
    private int slideUpNumber = 1;

    private Scanner in = new Scanner(System.in);


    public static void main(String[] args) {
        // This is how a javafx class is run.
        // This will cause the start method to run.
        // You don't need to change main.
        launch(args);
    }

    // This is the javafx main starting method. The JVM will run this
    // inside an object of this class (Animator). You do not need to
    // create that object yourself.
    @Override
    public void start(Stage primaryStage) {

        // The Stage is where we place GUI stuff, such as a GridPane (see later).
        // I'll look more at this after Easter, but please read the
        // following comments
        stage = primaryStage;

        // In this version of the app we will drive
        // the app using a command line menu.
        // YOU ARE REQUIRED TO IMPLEMENT THIS METHOD
        System.out.println("Welcome to Blockmation");
        System.out.println("Please choose an option:");
        runMenu();

        // This is how we can handle a window close event. I will talk
        // about the strange syntax later (lambdas), but essentially it's a Java 8
        // was of providing a callback function: when someone clicks the
        // close window icon then this bit of code is run, passing in the event
        // object that represents the click event.
        primaryStage.setOnCloseRequest((event) -> {
            // Prevent window from closing. We only want quitting via
            // the command line menu.
            event.consume();

            System.out.println("Please quit the application via the menu");
        });
    }

    private void runMenu() {
        // The GUI runs in its own thread of control. Here
        // we start by defining the function we want the thread
        // to call using that Java 8 lambda syntax. The Thread
        // constructor takes a Runnable
        Runnable commandLineTask = () -> {

            String choice;

            do {

                printMenu();
                choice = in.nextLine().toLowerCase();

                switch (choice) {
                    case "l":

                        System.out.println("Please enter file name");
                        fileName = in.nextLine();


                        boolean go;
                        do {
                            go = true;
                            try {
                                footage.load(fileName);
                                go = false;
                                System.out.println("loaded footage for " + fileName);


                                //TODO footage needs to be flipped vertically because atm it's upside down
                                createGrid(footage.getNumRows());

                            } catch (IOException e) {
                                System.err.println("Could not open file: " + fileName +
                                        " provide a different name for the footage file or press q to quit");
                                String answer = in.nextLine();
                                if (answer.equals("q")) {
                                    break;
                                } else {
                                    fileName = answer;
                                }
                            }
                        } while (go);

                        break;

                    case "s":
                        saveFootage(fileName);
                        break;

                    case "sa":
                        System.out.println("Enter the new filename");
                        String newFilename = in.nextLine();
                        saveFootage(newFilename);

                        break;

                    case "a":
                        System.out.println("Running animation");
                        runAnimation();
                        break;

                    case "t":
                        System.out.println("Stopping animation");
                        terminateAnimation();
                        break;

                    case "e":
                        editMenu();
                        break;

                    case "q":
                        if (transformationsDone){
                            System.out.println("Do you want to save first (y/n)?");
                            String input = in.nextLine();
                            if (input.equals("y")){
                                saveFootage(fileName);
                            }
                            Platform.exit();
                            break;
                        }
                        Platform.exit();
                        break;

                    default:
                        System.out.println("Unknown command, please try again.");
                        break;
                }

            } while (!choice.equals("q"));


            // At some point you will call createGrid.
            // Here's an example
            //createGrid();

        };
        Thread commandLineThread = new Thread(commandLineTask);
        // This is how we start the thread.
        // This causes the run method to execute.
        commandLineThread.start();

        // You can stop javafx with the command
        // Platform.exit();
    }

    private void saveFootage(String fn) {
        boolean go;
        System.out.println("Saving as " + fn);


        do {
            go = true;
            try {
                footage.save(fn);
                go = false;
                System.out.println("Saved footage for " + fn);

            } catch (IOException e) {
                System.err.println("Could not open file: " + fn +
                        " provide a different name for the footage file or press q to quit");
                String answer = in.nextLine();
                if (answer.equals("q")) {
                    break;
                } else {
                    fn = answer;
                }
            }
        } while (go);
    }

    private void editMenu() {
        System.out.println("** edit menu **");
        String input;


        do {
            printEditMenu();
            input = in.nextLine().toLowerCase();

            switch (input) {
                case "fh":
                    transformationsDone = true;
                    break;

                case "fv":
                    transformationsDone = true;
                    break;

                case "sl":
                    transformer = new SlideLeft();
                    footage.transform(transformer);
                    transformationsDone = true;
                    break;

                case "sr":
                    transformationsDone = true;
                    break;

                case "su":
                    transformationsDone = true;
                    break;

                case "sd":
                    transformationsDone = true;
                    break;

                case "nr":
                    System.out.println("Enter the new number:");
                    slideRightNumber = in.nextInt();
                    in.nextLine();
                    break;

                case "nl":
                    System.out.println("Enter the new number:");
                    slideLeftNumber = in.nextInt();
                    in.nextLine();
                    break;

                case "nd":
                    System.out.println("Enter the new number:");
                    slideDownNumber = in.nextInt();
                    in.nextLine();
                    break;

                case "nu":
                    System.out.println("Enter the new number:");
                    slideUpNumber = in.nextInt();
                    in.nextLine();
                    break;

                case "r":
                    break;

                case "q":
                    break;

                default:
                    System.out.println("Unknown command, please try again.");
                    break;
            }

        } while (!input.equals("q"));

    }


    // An example method that you might like to call from your
    // menu. Whenever you need to do something in the GUI thread
    // from another thread you have to call Platform.runLater
    // This is a javafx method that queues your code ready for the GUI
    // thread to run when it is ready. We use that strange lambda Java 8 syntax
    // again although this time there are no parameters hence ()
    private void terminateAnimation() {
        doRun = false;
    }

    // Here is another example. This one is useful because it creates
    // the GUI grid. You will need to call this from the menu, e.g. when starting
    // an animation.


    private void createGrid() {
        Platform.runLater(() -> {

            // Update UI here
            //createGrid(numRows);   // E.g. let's create a 20 x 20 grid
            createGrid(20);
        });
    }


    // I'll give you this method since I haven't done
    // much on javafx. This also creates a scene and adds it to the stage;
    // all very theatrical.
    private void createGrid(int numRows) {

        /*had to write this platform.runLater thing because otherwise I was getting a
        java.lang.IllegalStateException: Not on FX application thread; currentThread = Thread-4
        */
        Platform.runLater(() -> {
            // Creates a grid so that we can display the animation. We will see
            // other layout panes in the lectures.
            grid = new GridPane();

            // We need to create a 2D array of javafx Buttons that we will
            // add to the grid. The 2D array makes it much easier to change
            // the colour of the buttons in the grid; easy lookup using
            // 2D indicies. Note that we make this read-only for this display
            // onlt grid. If you go for flair marks, then I'd imagine that you
            // could create something similar that allows you to edits frames
            // in the footage.
            gridArray = new Button[numRows][numRows];
            Button displayButton = null;
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numRows; col++) {  // The display is square
                    displayButton = new Button();
                    gridArray[row][col] = displayButton;
                    displayButton.setDisable(true);
                    grid.add(displayButton, col, row);
                }
            }

            // Create a scene to hold the grid of buttons
            // The stage will "shrink wrap" around the grid of buttons,
            // so we don't need to set its height and width.
            scene = new Scene(grid);
            stage.setScene(scene);
            scene.getStylesheets().add(Animator.class.getResource("styling.css").toExternalForm());

            // Make it resizable so that the window shrinks to fit the scene grid
            stage.setResizable(true);
            stage.sizeToScene();
            // Raise the curtain on the stage!
            stage.show();
            // Stop the user from resizing the window
            stage.setResizable(false);
        });
    }

    // I'll also give you this one too. This does the animation and sets colours for
    // the grid buttons. You will have to call this from the menu. You should not
    // need to change this code, unless you want to add more colours
    private void runAnimation() {
        if (footage == null) {
            System.out.println("Load the footage first");
        } else {
            Runnable animationToRun = () -> {

                Background yellowBg = new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY));
                Background blackBg = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
                Background blueBg = new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY));
                Background whiteBg = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

                doRun = true;
                int numFrames = footage.getNumFrames();
                int currentFrameIndex = 0;
                Background bck = null;
                while (doRun) {
                    if (currentFrameIndex >= numFrames - 1) currentFrameIndex = 0;
                    IFrame currentFrame = footage.getFrame(currentFrameIndex);
                    // Iterate through the current frame displaying appropriate colour
                    for (int row = 0; row < footage.getNumRows(); row++) {
                        for (int col = 0; col < footage.getNumRows(); col++) {
                            switch (currentFrame.getChar(row, col)) {
                                case 'l':
                                    bck = yellowBg;
                                    break;
                                case 'r':
                                    bck = blackBg;
                                    break;
                                case 'b':
                                    bck = blueBg;
                                    break;
                                default:
                                    bck = whiteBg;
                            }
                            final int theRow = row;
                            final int theCol = col;
                            final Background backgroundColour = bck;
                            // This is another lambda callback. When the Platform
                            // GUI thread is ready it will run this code.
                            Platform.runLater(() -> {

                                // Update UI here
                                // We have to make this request on a queue that the GUI thread
                                // will run when ready.
                                gridArray[theRow][theCol].setBackground(backgroundColour);
                            });

                        }
                    }
                    try {
                        // This is how we delay for 200th of a second
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                    currentFrameIndex++;
                }
            };
            animation = new Thread(animationToRun);
            animation.start();
        }
    }

    private void printEditMenu() {
        System.out.println("fh - Flip horizontal");
        System.out.println("fv - Flip vertical");
        System.out.println("sl - Slide left");
        System.out.println("sr - Slide right");
        System.out.println("su - Slide up");
        System.out.println("sd - Slide down");
        System.out.println("nl - Slide left number, Currently= " + slideLeftNumber);
        System.out.println("nr - Slide right number, Currently= " + slideRightNumber);
        System.out.println("nd - Slide down number, Currently= " + slideDownNumber);
        System.out.println("nu - Slide up number, Currently= " + slideUpNumber);
        System.out.println("r - Repeat last operation");
        System.out.println("q - quit editing");
        System.out.println("Enter option:");
    }

    private void printMenu() {
        System.out.println("l - load footage file");
        System.out.println("s - save footage file");
        System.out.println("sa - save as footage file");
        System.out.println("a - run footage aimation");
        System.out.println("t - stop footage animation");
        System.out.println("e - edit current footage");
        System.out.println("q - Quit");
        System.out.println("Enter option:");
    }


    /**
     * Used to print out the i's and j's are the correct way round
     */
    private void layoutTest(){
        System.out.println("i = 0, j = 0");
        System.out.println(footage.getFrame(0).getChar(0,0));

        System.out.println("i = 0, j = 1");
        System.out.println(footage.getFrame(0).getChar(0,1));

        System.out.println("i = 0, j = 2");
        System.out.println(footage.getFrame(0).getChar(0,2));
    }
}

