package cs.app;

import org.apache.log4j.Logger;

import cs.util.Const;
import cs.util.LogUtil;

public class Demo {	
	
	
	public static void main(String[] args) {
		
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
	}
	
}
