package cs.util;

public class Const {
	
	public static final String SUCCESS = "true";
	public static final String FAILED = "false";
	public static final String SUCCESS_CODE = "100";
	public static final String SUCCESS_DESCRIPTION = "success";
	
	//---- Get Prop Value By Name
	public static final String PROP_CHECK_ERROR = "JOB.CHECK_ERROR";
	
	/* STATUS */
	public static final String STATUS_YES = "Y";
	public static final String STATUS_NO = "N";
	public static final String STATUS_WAIT = "W";
	public static final String STATUS_ERROR = "E";
	public static final String STATUS_PROCESS = "P";
	
	public static final int HTTP_STATUS_RESPONSE_SUCCESS = 200;
	
	public static final String PROCESS_IS_USE = "Y";
	
	public static final String DATASOURCE_NAME = "CSCENTERDB";
	public static final String DATASOURCE_DEVELOP = "DEVELOP";
	
	public static final String SCHEDULE_FOLDER_NAME = "Schedule";
	public static final String UTIL_SUB_FOLDER_NAME = "Common";
	public static final String CREATE_BY_OPERATOR = "SYSTEM";
	
	/**
	 * For String Util
	 */
	
	public static final String ENCODE_GETBYTE = "ISO8859-1";
	public static final String ENCODE = "TIS-620";
	public static final String ENCODE_UTF_8 = "UTF-8";
	
	/*thai month*/
	public static final String JAN_TH = "à¸¡.à¸?.";
	public static final String FEB_TH = "à¸?.à¸?.";
	public static final String MAR_TH = "à¸¡à¸µ.à¸?.";
	public static final String APL_TH = "à¹€à¸¡.à¸¢.";
	public static final String MAY_TH = "à¸?.à¸?.";
	public static final String JUN_TH = "à¸¡à¸´.à¸¢.";
	public static final String JUL_TH = "à¸?.à¸?.";
	public static final String AUG_TH = "à¸ª.à¸?.";
	public static final String SEP_TH = "à¸?.à¸¢.";
	public static final String OCT_TH = "à¸•.à¸?.";
	public static final String NOV_TH = "à¸?.à¸¢.";
	public static final String DEC_TH = "à¸?.à¸?";
	
	//Autofix Connect  POS
	public static final String POS_PREFIX = "117.1";
	
	public static final String SERVER_TEST_01 = "10.182.247.19";
	public static final String SERVER_TEST_02 = "10.182.247.20";
	public static final String SERVER_TEST_03 = "10.182.247.16";
	public static final String SERVER_TEST_04 = "10.182.247.18";
	
	/* Job */
	public static final String CS_CENTER_TASK_GROUP_NAME = "CSCenterGrp";
	public static final String TASK_NAME_CS_CENTER_CHECK_HTTP_URL = "CSCenterCheckHttpURL";
	public static final String TASK_NAME_CS_CENTER_CHECK_WEB_LOGIN = "CSCenterCheckWebLogin";	
	public static final String TASK_NAME_SUMMARY_APP_ERROR = "CSCenterSummaryAppError";
	public static final String TASK_NAME_CS_CENTER_CHECK_BATCH = "CSCenterCheckBatchDB";
	public static final String TASK_NAME_CS_CENTER_CHECK_TEXT = "CSCenterCheckScheduleText";
	public static final String TASK_NAME_CS_CENTER_PURGE_DATA = "CSCenterPurgeData";
	public static final String TASK_NAME_CS_CENTER_CHECK_STATEMENT = "CSCenterCheckStatement";
	
//	public static final String DB_SCHEMA = "TICKET.";
	public static final String DB_SCHEMA = "";
	
	/* System Type */
	public static final Integer TYPE_WS_WEBCONN = 1;
	public static final Integer TYPE_JOB = 2;
	public static final Integer TYPE_WEB_LOGIN = 3;
	public static final Integer TYPE_STATEMENT = 4;
	
	public static final Integer WS_CONNECT_TIMEOUT_DF = 10; //second
	public static final Integer WS_NEXT_RUN_TIME_DF = 3; //minute
	
	/*Define Value*/
	public static final String DEFAULT_CS_VENDOR_ID = "000";
	public static final String DEFAULT_CS_SERVICE_CODE = "00";
	public static final String CATE000 = "CATE000";
	public static final String SMS_ENDPOINT = "SMS_ENDPOINT";
	
	public static final Integer DEFAULT_SYSTEM_ID = 0;
	public static final Integer DEFAULT_TYPE_ID = 0;
	public static final Integer DEFAULT_APP_ID = 0;
	public static final String DEFAULT_CATEGORY = "CATE000";
	public static final String DEFINE_VALUE_USER_DB = "USER_DB";
	public static final String DEFINE_VALUE_PASS_DB = "PASS_DB";
	public static final String DEFINE_VALUE_USER_DB_CSHEALTH = "USER_DB_CSHEALTH";
	public static final String DEFINE_VALUE_PASS_DB_CSHEALTH = "PASS_DB_CSHEALTH";
	public static final String SERVICE_CSHEALTH = "CSHEALTHCENTER";	
	
	public static final String DEFINE_VALUE_USER_LOGIN = "USER_LOGIN";
	public static final String DEFINE_VALUE_PASS_LOGIN = "PASS_LOGIN";
	public static final String DEFINE_VALUE_SFTP_PORT = "SFTP_PORT";
	public static final String DEFINE_VALUE_SMS_ENDPOINT = "SMS_GATEWAY_ENDPOINT";	
	public static final String JOB_DB_ADD_TIME_DF = "JOB_DB_ADD_TIME";
	public static final String DEFAULT_TIMEOUT = "DEFAULT_TIMEOUT_CONN";
	public static final String WS_ADD_TIME_DF = "WS_ADD_TIME";
	public static final String CAT_STRING_TEMPLATE = "CATE002";
	public static final String ERROR_TEMPLATE = "ERROR_TMPL";	
	
	public static final String SQLITE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String HTTP = "http";
	public static final String HTTPS = "https";
	
	//File Event
	public static final String FILE_NOT_EXIST 	= "*FileNotExist";
	public static final String DIR_NOT_EXIST 	= "*DirectoryNotExist";
	public static final String FILE_IS_EMPTY 	= "*FileIsEmpty";
	public static final String FILE_NOT_UPDTAE 	= "*FileNotUpdate";
	public static final String FILE_NOT_FOUND 	= "*FileNotFound";
	
	//JOB TYPE 
	public static final String JOB_DAILY = "JOB DAILY"; //---- job working one time on daily
	public static final String JOB_THREAD = "JOB THREAD"; //---- job working by thread sleep config
	
	public static final String KEY_FOUND = "KEY FOUND";
	public static final String KEY_NOT_FOUND = "*KEY NOT FOUND";
	
	public static final String SMS_SENDER_NAME = "CS HEALTH";
	
	//---- Name Define Proxy Info
	public static final int DEFAULT_PORT_PROXY = 8080;
	public static final String PROXY_NAME = "PROXY_NAME";
	public static final String PROXY_USER = "PROXY_USER";
	public static final String PROXY_PASS = "PROXY_PASS";
	public static final String DOMAIN_NAME = "DOMAIN_NAME";
	
	//---- Mail Grp 
	public static final String MAIL_PURGE_LOG_DATA = "MAIL_PURGE_LOG_DATA";
	public static final String MAIL_PURGE_DB_DATA = "MAIL_PURGE_DB_DATA";
	
	//---- SMS Grp
	public static final String SMS_ID_PURGE_PROCESS = "SMS_ID_PURGE_PROCESS";
	public static final String SMS_MSG_PURGE_PROCESS = "SMS_MSG_PURGE_PROCESS";
	
	//---- Name Client Define Value
	public static final String KEEP_DATE_PURGE_LOG = "KEEP_DATE_PURGE_LOG";
	public static final String PATH_LOG_FILE = "PATH_LOG_FILE";
	
	
	
	
}
