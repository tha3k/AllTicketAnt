package aws.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cs.util.Const;
import cs.util.LogUtil;

/**
 * Servlet implementation class AntServlet
 */
@WebServlet("/AntServlet")
public class AntServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AntServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		
		Logger logger = null;
		LogUtil logUtil = null;
		
		try{
					
			if(logger==null){						
				logUtil = new LogUtil(Const.SCHEDULE_FOLDER_NAME, "TestAnt", "TestAnt");
				logger = logUtil.getLogger();			
			}
			
			System.out.println("executing Demo ... ");
			
			logger.info("executing Demo ... ");
		
		}catch (Exception e) {
			
			// TODO: handle exception
			e.printStackTrace();
			
			logger.info("Error : "+e.toString(), e);
			
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

}
