import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Fibonacci extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public Fibonacci() {super();}
	public void destroy() {super.destroy();}
	public void init() throws ServletException {}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String number = request.getParameter("number");
        if(!number.trim().equals(""))
        {
        	int n = Integer.parseInt(number.trim());
        	if(n < 0)
        		out.print("error");
        	else 
        	{
        		for(int i = 0; i < n; i++)
            		out.print(fibonacci(i)+" ");
			}        	
        }
	}

	public static int fibonacci(int n)
	{ 
        if(n == 0) 
            return 0; 
        else if(n == 1)
        	return 1;
        else
            return fibonacci(n-1) + fibonacci(n-2); 
    } 
}
