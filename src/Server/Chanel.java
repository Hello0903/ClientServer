package Server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jdk.nashorn.internal.parser.JSONParser;

@WebServlet(urlPatterns = {"/api-chanel"})
public class Chanel extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		String[] data = {"Facebook","Youtube","BaoMoi","Test"};
		PrintWriter printWriter = resp.getWriter();
		Gson gson = new Gson();
		printWriter.println(gson.toJson(data));
	
	}
}
