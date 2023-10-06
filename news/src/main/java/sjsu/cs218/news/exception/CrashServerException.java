//SJSU CS 218 SPRING 2023 TEAM4
package sjsu.cs218.news.exception;

public class CrashServerException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CrashServerException (String message) {
        super(message);
    }
}
