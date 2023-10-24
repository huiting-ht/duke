package amebot.common;

/**
 * Title class is used to print the title and welcome message of the program.
 * It contains the logo and the greeting message.
 */
public class Title {
    private static final String BORDER = "================================================================================" + System.lineSeparator();

    private static final String MESSAGE = "                               _|" + System.lineSeparator()
            + "           _|    _|    _|_|    _|  _|      _|_|      _|_|_|    _|_|" + System.lineSeparator()
            + "           _|    _|  _|    _|  _|_|      _|    _|  _|_|      _|    _|" + System.lineSeparator()
            + "           _|    _|  _|    _|  _|  _|    _|    _|      _|_|  _|    _|" + System.lineSeparator()
            + "             _|_|_|    _|_|    _|    _|    _|_|    _|_|_|      _|_|" + System.lineSeparator()
            + "                 _|" + System.lineSeparator()
            + "             _|_|" + System.lineSeparator();

    private static final String LOGO = "    _     _      _     _      _     _      _     _      _     _      _     _" + System.lineSeparator()
            + "   (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)" + System.lineSeparator()
            + "    / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\" + System.lineSeparator()
            + "  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__" + System.lineSeparator()
            + " (_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)" + System.lineSeparator()
            + "    || A ||      || M ||      || E ||      || B ||      || O ||      || T ||" + System.lineSeparator()
            + "  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._" + System.lineSeparator()
            + " (.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)" + System.lineSeparator()
            + "  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'" + System.lineSeparator();

    private static final String GREETING = "Yokoso, your personal assistant AMEBOT~!" + System.lineSeparator()
            + "Please enter the command(s):";

    /**
     * Prints the title of the program.
     */
    public static void printHeader() {
        System.out.print(BORDER
                + MESSAGE
                + LOGO
                + BORDER);
    }

    /**
     * Prints the greeting message.
     */
    public static void printGreeting() {
        System.out.println(GREETING);
    }
}
