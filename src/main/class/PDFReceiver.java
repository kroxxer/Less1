import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

@WebServlet("/quake")
public class PDFReceiver extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        receiveProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        receiveProcess(request, response);
    }

    protected void receiveProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        byte[] arrByte = null;
        OutputStream outstream = response.getOutputStream();

        if (request.getParameter("link").equals("pdf"))
        {

            File pdf = new File("C:\\Users\\Vapio\\IdeaProjects\\Kurs1\\src\\main\\webapp\\quake.pdf");

            if (!pdf.exists()) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            response.setHeader("Content-Type", getServletContext().getMimeType(pdf.getName()));
            response.setHeader("Content-Length", String.valueOf(pdf.length()));
            response.setHeader("Content-Disposition", "inline; filename=quake.pdf");
            response.setContentType("application/pdf");
            response.setContentLength((int)pdf.length());

            arrByte = Files.readAllBytes(pdf.toPath());

        } else if (request.getParameter("link").equals("jpg")) {

            File jpg = new File("C:\\Users\\Vapio\\IdeaProjects\\Kurs1\\src\\main\\webapp\\cat.jpg");

            if (!jpg.exists()) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            response.setHeader("Content-Type", getServletContext().getMimeType(jpg.getName()));
            response.setHeader("Content-Length", String.valueOf(jpg.length()));
            response.setHeader("Content-Disposition", "inline; filename=quake.jpg");
            response.setContentType("image/jpeg");
            response.setContentLength((int)jpg.length());

            arrByte = Files.readAllBytes(jpg.toPath());

        }

        outstream.write(arrByte);

        outstream.flush();
        outstream.close();

    }

}
