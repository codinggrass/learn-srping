package learn.filter.executer;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.IOException;

import static org.junit.Assert.*;

public class NetworkServiceTest {
    @Test
    public void start_a_network_service() throws IOException {
        NetworkService networkService = new NetworkService(9099, 3);
        networkService.run();
    }

}