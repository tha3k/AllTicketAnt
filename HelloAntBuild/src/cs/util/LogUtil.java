package cs.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class LogUtil {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
	private static final SimpleDateFormat hhFormat = new SimpleDateFormat("HH", Locale.US);
	private static final SimpleDateFormat mmFormat = new SimpleDateFormat("mm", Locale.US);
	private String logPath = null;
	private String prefix = null;
	private String logname = null;
	private String subFolder = null;
	private Appender newAppender = null;
	private Logger logger = null;	
	private String FILE_SEP = System.getProperty("file.separator");
	private String FILE_SIZE = "2MB";
	private Level logLevel = Level.INFO;
	private String lvl = null;
	
	public LogUtil(String subFolder, String prefix, String logName){
		setLogLevel(subFolder, logName);
		this.FILE_SIZE = PropConfig.getProperty("LOGAPP.FILESIZE");
		String logPath = PropConfig.getProperty("LOGAPP.PATH");
		if(logName!=null && logName.lastIndexOf(".")>=0){
			logName = logName.substring(logName.lastIndexOf(".")+1);
		}		
		if(prefix==null){
			prefix = "";
		}		
		this.logPath = logPath;
		this.prefix = prefix;
		this.logname = StringUtil.ifEmpty(logName,"");
		this.subFolder = subFolder;
		//PropertyConfigurator.configure(logConfig);
	}
	
	public Logger getLogger(){	
		try {
			
			if(StringUtil.isNotEmpty(this.prefix) && this.prefix.indexOf(FILE_SEP)<0){
				this.prefix = this.prefix + FILE_SEP;
			}
			
//			if(this.subFolder.indexOf("\\")<0 && !this.subFolder.equals("")){
//				this.subFolder = this.subFolder + "\\";
//			}
			
			logger = Logger.getLogger(this.prefix + this.logname); 		
		
			Layout pattern = new PatternLayout("%d %5p (%F:%L) - %m%n");
			//String logFile = this.logPath +FILE_SEP+ dateFormat.format(new Date()) + FILE_SEP+ this.subFolder + this.prefix + this.logname + ".log";
			
			this.logPath  = this.logPath.trim();
			if (!this.logPath.endsWith(FILE_SEP)) {
				logPath = logPath + FILE_SEP;
			}
						
//			checkDir(this.logPath + this.subFolder + dateFormat.format(new Date()) + FILE_SEP + timeFormat.format(new Date()) );
//			checkDir(this.logPath + this.subFolder + dateFormat.format(new Date()) + FILE_SEP + timeFormat.format(new Date()) + FILE_SEP + this.subFolder );
						
			String logFileFullPath = this.logPath + dateFormat.format(new Date()) + FILE_SEP + hhFormat.format(new Date()) + FILE_SEP + mmFormat.format(new Date()) + FILE_SEP + this.subFolder + FILE_SEP + getFileName();
			
			if(!lvl.equalsIgnoreCase("OFF")){
				newAppender = new RollingFileAppender(pattern, logFileFullPath, true);
				newAppender.setName(this.subFolder);
				((RollingFileAppender) newAppender).setMaxFileSize(this.FILE_SIZE);
				//((RollingFileAppender) newAppender).setEncoding("UTF-8");
				
				logger.setAdditivity(false);
				//logger.setLevel(Level.DEBUG);
				logger.setLevel(logLevel);
				logger.addAppender(newAppender); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return logger;
	}
	
	public void closeLogger(Logger logger){
		if(logger!=null) {
			logger.removeAllAppenders();
			logger=null;
			logLevel = null;
		}
		if(newAppender!=null){
			newAppender.close();
			newAppender = null;
		}
		
		MDC.clear();
		
	}
	
	private void checkDir(String dirName) {
		File file = new File( dirName);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	private String getFileName() {
		return this.prefix + this.logname + ".log";
	}
	
	private void setLogLevel(String subFolder, String logName){
			
		if(logName!=null && !logName.equals("")){
			lvl = PropConfig.getProperty("LOG_"+logName.toUpperCase()+".LEVEL");
		} else {
			lvl = PropConfig.getProperty("LOGAPP.LEVEL");
		}
		
		if(subFolder==null || subFolder.equals("")) {
			lvl = PropConfig.getProperty("LOGAPP.LEVEL");	
		}
		
		if(lvl==null){
			lvl = "INFO";
			logLevel = Level.INFO;
		} else if(lvl.equalsIgnoreCase("info")) {
			logLevel = Level.INFO;
		} else if(lvl.equalsIgnoreCase("debug")) {
			logLevel = Level.DEBUG;
		} else if(lvl.equalsIgnoreCase("error")) {
			logLevel = Level.ERROR;
		} else if(lvl.equalsIgnoreCase("fatal")) {
			logLevel = Level.FATAL;
		} else if(lvl.equalsIgnoreCase("off")) {
			logLevel = Level.OFF;
		} else if(lvl.equalsIgnoreCase("all")) {
			logLevel = Level.ALL;
		} else {
			logLevel = Level.INFO;
		}
	}
}
