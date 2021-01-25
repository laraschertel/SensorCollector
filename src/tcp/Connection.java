package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Connection {
    /**
     * @return output stream  of created connection
     * @throws IOException if there is any I/O problems
     */
    OutputStream getOutputStream() throws IOException;

    /**
     *
     * @return input stream of created connection
     * @throws IOException if there is any I/O problems
     */
    InputStream getInputStream() throws IOException;
}
