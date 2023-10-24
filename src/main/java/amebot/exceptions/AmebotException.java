package amebot.exceptions;

/**
 * AmebotException is the base exception class for all Amebot exceptions.
 *
 * @since 0.1
 */
public class AmebotException extends Exception {
    public AmebotException(String err) {
        super(err);
    }
}
