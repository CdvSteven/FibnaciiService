
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Fibonacci
 */
@WebServlet("/Fibonacci")
public class Fibonacci extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LRUCache cache = new LRUCache(100, 0.75f);
	
	public class LRUCache extends LinkedHashMap<Integer, String> {//cache
		
		private int capacity;

		public LRUCache(int capacity, float loadFactor) {
			super(capacity, loadFactor, true);
			this.capacity = capacity;
		}

		protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
			return size() > this.capacity;
		}
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fibonacci() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String number = request.getParameter("number");
        
        if (!number.trim().equals("")) {
        	int n;
        	try {
        		n = Integer.parseInt(number.trim());
        	} catch (Exception e) {
        		out.print("error, please type in a correct and fair number");
        		return;
        	}
        	if (n < 0) {
        		out.print("Error, please type in a positive number");
        	} else {
        		String result = cache.get(n);
        		if (result == null) {
        			System.out.println("no cache");
        			StringBuffer sb = new StringBuffer();
        			int oneRes;
        			for(int i = 0; i < n; i++) {
        				try {
        					oneRes = fibonacci(i, 1, 1);
        					if (oneRes < 0) {
        						throw new Exception();
        					}
        				} catch (Exception e) {
        					out.print("The number you type is too large");
        					return;
        				}
        				sb.append(oneRes);
        				sb.append(" ");
        			}
        			result = sb.toString();
                	cache.put(n, result);
        		}
        		out.print(result);
        	}
        }
	}
	
	private int fibonacci(int n, int acc1, int acc2) { //tail recursion, tuning.
		if (n < 2) {
			return n == 0 ? 0 : acc1;
		}
		return fibonacci(n - 1, acc2, acc1 + acc2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
