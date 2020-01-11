package osu.desktop;

public class Main {
    public static boolean DEBUG = false;
    public static Game game;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--debug")) DEBUG = true;
        game = new Game();
        game.initialise();
        game.run();
    }
}
