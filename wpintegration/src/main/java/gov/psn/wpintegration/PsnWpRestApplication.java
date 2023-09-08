
package gov.psn.wpintegration;

import io.helidon.microprofile.server.Server;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.io.IOException;

@ApplicationPath("/psn/1.0/")
@ApplicationScoped
public class PsnWpRestApplication  extends Application{
    public PsnWpRestApplication() {
    }

    public static void main(final String[] args) throws IOException {

        Server server = startServer();
        System.out.println("http://localhost:" + server.port());
    }

    public static Server startServer() {
        return Server.create().start();
    }
}
