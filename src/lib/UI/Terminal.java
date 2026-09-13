package lib.UI;

public class Terminal {

    public static final String CLEAR = "\033[H\033[2J";
    public static final String RESET = "\033[0m";
    public static final String BOLD = "\033[1m";
    public static final String CYAN = "\033[36m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String MAGENTA = "\033[35m";
    public static final String BLUE = "\033[34m";

    public static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    public static String center(String text, int width) {
        String plain = text.replaceAll("\033\\[[0-9;]*m", "");
        int padding = Math.max(0, width - plain.length());
        int left = padding / 2;
        int right = padding - left;
        return " ".repeat(left) + text + " ".repeat(right);
    }
}
