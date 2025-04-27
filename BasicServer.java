import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class BasicServer {
    public static void main(String[] args) throws IOException {
        // Create an HTTP server on port 6969
        HttpServer server = HttpServer.create(new InetSocketAddress(6969), 0);

        // Define a context and its handler
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "Hello, World!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        // Start the server
        server.setExecutor(null); // Use the default executor
        System.out.println("Server is running on http://localhost:6969");
        server.start();
    }
}