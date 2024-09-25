package exceptions;

public class InvalidPathException extends RuntimeException{

    public InvalidPathException () {
        super("Path not set to class");
    }

}
