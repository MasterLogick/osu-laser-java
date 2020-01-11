package osu.desktop;

public class Main {
    public static boolean DEBUG = false;
    public static Osu osu;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--debug")) DEBUG = true;
        osu = new Osu();
        osu.initialise();
        osu.run();
    }
}
