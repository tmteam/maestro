/**
 * Created by Su on 24/03/17.
 */
public interface ILog {
    void writeMessage(String message);
    void writeWarning(String message);
    void writeError(String message);
}
