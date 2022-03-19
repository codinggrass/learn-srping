package learn.servlet;


import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class MyServletTest {
    @Test
    public void testServlet() throws IOException {

        MyServlet myServlet = new MyServlet();
        myServlet.init(new ServletConfig() {
            public String getServletName() {
                return "my servlet";
            }

            public ServletContext getServletContext() {
                return null;
            }

            public String getInitParameter(String s) {
                return null;
            }

            public Enumeration<String> getInitParameterNames() {
                return null;
            }
        });
        HttpServletRequest mockReq = mock(HttpServletRequest.class);
        HttpServletResponse mockRsp = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(mockRsp.getWriter()).thenReturn(writer);

        myServlet.service(mockReq, mockRsp);

//        writer.flush(); // it may not have been flushed yet...
        assertTrue(stringWriter.toString().contains("Hello from"));
    }

    @Test
    public void test_writer_flush() {
        StringWriter stringWriter = new StringWriter();
        stringWriter.append("char1");
        log.info(stringWriter.toString());
        log.info(stringWriter.toString());
        stringWriter.append("char2");
        log.info(stringWriter.toString());
        stringWriter.write("abc");
        log.info(stringWriter.toString());
        Assert.assertNotNull(stringWriter.toString());
    }

}