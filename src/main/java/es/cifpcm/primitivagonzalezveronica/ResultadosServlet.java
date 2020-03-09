package es.cifpcm.primitivagonzalezveronica;

import java.io.IOException;
import java.util.Random;
import java.util.TreeSet;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResultadosServlet
 */
@WebServlet(description = "Un servlet que compara la combinación ganadora con la dada por el cliente", urlPatterns = {
		"/resultados" })
public class ResultadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TreeSet<Integer> COMBINACION_GANADORA = new TreeSet<Integer>();
	TreeSet<Integer> COMBINACION = new TreeSet<Integer>();
	String css = "<link rel=\'stylesheet\' type=\'text/css\' href=\'css/main.css\'>";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResultadosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String[] szNumeros = request.getParameterValues("numero");
		int acierto = 0;

		// Asignar valores TreeSet
		for (int i = 0; i < szNumeros.length; i++) {
			COMBINACION.add(Integer.parseInt(szNumeros[i]));
		}

		// Para calcular el número de aciertos
		for (int j = 0; j < COMBINACION.size(); j++) {
			if (COMBINACION_GANADORA.contains(COMBINACION.ceiling(j + 1))) {
				acierto++;
				System.out.println(acierto);
			}
		}

		// Pantalla a mostrar
		// en caso de texto vacío o incorrecto

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		try {
			/* TODO output your page here. You may use following sample code. */
			sb.append("<!DOCTYPE html>");
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>Juego de la primitiva</title>");
			sb.append(css);
			sb.append("</head>");
			sb.append("<body>");
			if (acierto < 6) {
				sb.append("<h1>Lo sentimos, siga jugando...</h1>");

			} else {
				sb.append("<h1>¡FELICIDADES, es usted el GANADOR!</h1>");

			}
			sb.append("<div>");
			sb.append("<h2>¡Has acertado " + acierto + " de 6 numeros!" + "</h2>");
			sb.append("<h2>La Combinacion Ganadora es " + COMBINACION_GANADORA + "</h2>");
			sb.append("<h2>Tu Combinacion  es " + COMBINACION + "</h2>");
			sb.append("</div>");
			sb.append("</body>");
			sb.append("</html>");
			out.println(sb.toString());
		} finally {
			out.close();
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		Random rnd = new Random();
		while (COMBINACION_GANADORA.size() < 6) {
			int num = rnd.nextInt(49) + 1;
			if (!COMBINACION_GANADORA.contains(num)) {
				COMBINACION_GANADORA.add(num);
			}
		}
	}

}
