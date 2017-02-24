package cs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class StringUtil {
	private static DecimalFormat integerFormat = new DecimalFormat("###,###,##0");
	private static DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
	public static SimpleDateFormat ymdHHmmSSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
	private static SimpleDateFormat dmyHhmmNotSlashFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US);
	private static SimpleDateFormat ymdSlashFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
	private static SimpleDateFormat ymdHhmmSlashFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
	public static SimpleDateFormat dmyFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
	private static SimpleDateFormat dmyHhmmFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
	private static SimpleDateFormat yyyymmddHhmmssFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
	public static SimpleDateFormat dmyHhmmssFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
	private static SimpleDateFormat HHmmFormat = new SimpleDateFormat("HH:mm", Locale.US);
	public static SimpleDateFormat dateZoneFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
	public static SimpleDateFormat HHmmssFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
	
	private static SimpleDateFormat hhMMssFormat = new SimpleDateFormat("HHmmss", Locale.US);
	private static SimpleDateFormat hhmmAmPmFormat = new SimpleDateFormat("hh:mm a", Locale.US);
	static SimpleDateFormat EN = new SimpleDateFormat("d MMM yyyy, EEE K:mm a",Locale.US);
	static SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.US);
	static SimpleDateFormat monthTH = new SimpleDateFormat("MMMM",new Locale("th","th"));
	static SimpleDateFormat ddMMMTH = new SimpleDateFormat("ddMMM",new Locale("th","th"));
	static SimpleDateFormat yearTH = new SimpleDateFormat("yyyy",new Locale("th","th"));
	static SimpleDateFormat dmyTH = new SimpleDateFormat("dd/MMMM/yyyy",new Locale("th","th"));
	static SimpleDateFormat dmyThSpace = new SimpleDateFormat("dd MMMM yyyy",new Locale("th","th"));
	static SimpleDateFormat dmyHHmmThSpace = new SimpleDateFormat("dd MMMM yyyy HH:mm",new Locale("th","th"));
	static SimpleDateFormat dmyTh = new SimpleDateFormat("dd MMM yyyy",new Locale("th","th"));
	static SimpleDateFormat yearEN = new SimpleDateFormat("yyyy",Locale.US);
	static SimpleDateFormat dmYearTh = new SimpleDateFormat("dd/MM/yyyy",new Locale("th","th"));
	static SimpleDateFormat dmYearThHHmmss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",new Locale("th","th"));
	static SimpleDateFormat dmyHHmmSS = new SimpleDateFormat("ddMMyyyy HHmmss", Locale.US);
	static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd", Locale.US);
	public static SimpleDateFormat ddMMyyyy = new SimpleDateFormat("ddMMyyyy", Locale.US);
	static SimpleDateFormat hhMMss = new SimpleDateFormat("HHmmss", Locale.US);
	
	private static SimpleDateFormat dmyHHmmssSlashFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
	
	private static DateFormatSymbols meridiemSymbols = new DateFormatSymbols();
	
	public static String[] cutSuggestion(String desc){
		int i = 0;
		String[] str = desc.split("\\|");
		int len = str.length;

		if(len<3){
			len = 3;
		}		
		String[] result = new String[len];
		for(String val: str) {
			result[i] = val;
			i++;
		}
		
		return result;
	}
	
	public static boolean isEmpty(String rcvData) {
		if (rcvData == null) {
		    return true;				    
		} else if (rcvData.trim().equalsIgnoreCase("null")) {
		    return true;		    
		} else if (rcvData.trim().length() == 0) {
		    return true;		    
		} else {
		    return false;		
		}
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	
	public static String checkNull(String value) {
		if (value == null || value.equalsIgnoreCase("null")) {
			value = "";
		}
		return value;
	}
	
	public static String padLeft(String str, int maxLength, String strPad) throws Exception{
		String result = str;
		try{		
			if(result!=null){
				for(int i=0; i<maxLength; i++){
					if(result.length()<maxLength){
						result = strPad + result;
					} else {
						return result;
					}
				}
			}
		} catch(Exception e){
			throw e;
		}
		return result; 
	}
	
	public static String padRight(String str, int maxLength, String strPad) throws Exception{
		String result = str;
		try{
			if(result!=null){
				for(int i=0; i<maxLength; i++){
					if(result.length()<maxLength){
						result = result + strPad;
					} else {
						return result;
					}
				}
			}
		} catch(Exception e){
			throw e;
		}
		return result; 
	}

	@SuppressWarnings("unchecked")
	public static String replacePattern(String str, ArrayList param) throws Exception{
		String result = str;
		int n = 0;
		try{
			if (str.indexOf("${")>-1) {
				Pattern pattern = Pattern.compile("\\$\\{([^\\}]*)\\}");
				Matcher matcher = pattern.matcher(str);
				while (matcher.find()) {
					String strKey = (matcher.group(1));
					if(param.get(n) instanceof Date){
						Format formatter = new SimpleDateFormat(strKey, Locale.US);
						result = result.replaceFirst("\\$\\{"+strKey+"\\}", formatter.format(new Date()));
					} else {
						result = result.replaceFirst("\\$\\{"+strKey+"\\}", param.get(n).toString());
					}
					n++;
				}
			} 
		} catch(Exception e){		
			throw e;
		}
		return result;
	}
	
	public static String replaceSqlParam(String str, String[] param){
		str = System.getProperty("line.separator") + str;
		
		StringBuffer tmp = new StringBuffer(str);
		int pos = 0;
		for(int i=0; i<param.length; i++){
			pos = tmp.toString().indexOf("?", pos);
			pos = pos + 1;
			tmp.insert(pos, " /*" + param[i] + "*/");
		}
		return tmp.toString();
	}
	
	public static String replaceParam(String str, String strReplce, String[] param){
		StringBuffer tmp = new StringBuffer(str);
		int pos = 0;
		for(int i=0; i<param.length; i++){
			pos = tmp.toString().indexOf(strReplce, pos);
			pos = pos + 1;
			tmp.insert(pos, param[i]);
		}
		return tmp.toString();
	}
	
	public static String encodeThai(String str) throws UnsupportedEncodingException{
		return (isNotEmpty(str)?(new String(str.getBytes(Const.ENCODE_GETBYTE),Const.ENCODE)):null);
	}
	
	public static String encodeThaiNotNull(String str) throws UnsupportedEncodingException{
		return (isNotEmpty(str)?(new String(str.getBytes(Const.ENCODE_GETBYTE),Const.ENCODE)):"");
	}
	
	public static String encode(String str, String strGetByte, String strEncode) throws UnsupportedEncodingException{
		return new String(str.getBytes(strGetByte),strEncode);
	}
	
	public static String encodeToDb(String str) throws UnsupportedEncodingException {
//		return (isNotEmpty(str)?Unicode2ASCII(new String(str.getBytes("ISO-8859-1"),"UTF-8")):null);
		return (isNotEmpty(str)?Unicode2ASCII(new String(str.getBytes(Const.ENCODE_GETBYTE),Const.ENCODE_UTF_8)):null);
	}
	
	public static String encodeDbToPos(String str) throws UnsupportedEncodingException{
		return new String(StringUtil.ASCII2Unicode(str).getBytes("UTF-8"),"ISO8859-1");
	}
	
	public static String Unicode2ASCII(String unicode) {
		unicode = ifEmpty(unicode,"");
		StringBuffer ascii = new StringBuffer(unicode);
		int code;
		for (int i = 0; i < unicode.length(); i++) {
			code = (int) unicode.charAt(i);
			if ((0xE01 <= code) && (code <= 0xE5B))
				ascii.setCharAt(i, (char) (code - 0xD60));
		}
		return ascii.toString();
	}
	
	public static String ASCII2Unicode(String ascii) {
		StringBuffer unicode = new StringBuffer(ascii);
		int code;
		for (int i = 0; i < ascii.length(); i++) {
			code = (int) ascii.charAt(i);
			if ((0xA1 <= code) && (code <= 0xFB))
				unicode.setCharAt(i, (char) (code + 0xD60));
		}
		return unicode.toString();
	}
	
	public static String getRunIPServer() {
		String ipServer = "";
		try{
			InetAddress address = InetAddress.getLocalHost(); 
			ipServer = address.getHostAddress(); 
						
		}catch(Exception ex){
			ipServer = "";
		}
		return ipServer;
	}
	
	public static String convertThaiMonth(String rcvDate) throws Exception {
		String rtnDate = "";
				
		try {
			String day = rcvDate.substring(0,2);
			String month = rcvDate.substring(3,5);
//			String year = rcvDate.substring(6,10);
			
			int iMonth = Integer.parseInt(month);
//			int iYear = Integer.parseInt(year)+543;
//			year = String.valueOf(iYear).substring(2,4);
			
			switch (iMonth){  
			  case 1:
				  rtnDate = day+Const.JAN_TH;
				  break;
			  case 2:
				  rtnDate = day+Const.FEB_TH;
				  break;
			  case 3:
				  rtnDate = day+Const.MAR_TH;
				  break;
			  case 4:
				  rtnDate = day+Const.APL_TH;
				  break;
			  case 5:
				  rtnDate = day+Const.MAY_TH;
				  break;
			  case 6:
				  rtnDate = day+Const.JUN_TH;
				  break;
			  case 7:
				  rtnDate = day+Const.JUL_TH;
				  break;
			  case 8:
				  rtnDate = day+Const.AUG_TH;
				  break;
			  case 9:
				  rtnDate = day+Const.SEP_TH;
				  break;
			  case 10:
				  rtnDate = day+Const.OCT_TH;
				  break;
			  case 11:
				  rtnDate = day+Const.NOV_TH;
				  break;
			  case 12:
				  rtnDate = day+Const.DEC_TH;
				  break;
			  }
			
		} catch (Exception e) {
			throw e;
		}
		
		return rtnDate;
	}	
	
	
	
	public static String ifNull(String rcvText, String replaceNull){
		String rtnText = "";
		
		if(rcvText == null){
			rtnText = replaceNull ;
		}else{
			rtnText = rcvText ;
		}
		
		return rtnText ;
	}
	
	public static String ifEmpty(String rcvText, String replaceNull){
		String rtnText = "";
		
		if(isEmpty(rcvText) || rcvText.equals("null")){
			rtnText = replaceNull ;
		}else{
			rtnText = rcvText ;
		}
		
		return rtnText ;
	}
	
	public static String toCurrency(Object d) throws Exception {
		if(d==null){
			return null;
		}
		return decimalFormat.format(Double.parseDouble(d.toString()));
	}
	
	public static String toCurrencyNonDecimal(Object d) throws Exception {
		if(d==null){
			return null;
		}
		return integerFormat.format(Double.parseDouble(d.toString()));
	}

	public static String replaceNull(String str, String strReplace) throws Exception {
		String result = str;
		if(result==null || result.equals("null")){
			result = strReplace;
		}
		return result;
	}

	public static String hideTelno(String telNo) throws Exception {
		String result = telNo;
		if(result!=null && !result.equals("")){			
			String s1 = "";
			String s2 = "";
			String s3 = "";
			
			if(result.length()==10){
				s1 = result.substring(0,3);
				s2 = result.substring(3,6);
				s3 = "XXXX";
			} else if(result.length()==9){
				s1 = result.substring(0,2);
				s2 = result.substring(2,5);
				s3 = "XXXX";
			}
			
			result = s1 + "-" + s2 + "-" + s3;
		}
			
		return result;
	}
	
	public static String hideNIdno(String nIdNo) throws Exception {
		String result = nIdNo;
		if(result!=null && !result.equals("")){			
			String s1 = "";
			String s2 = "";
			
			if(result.length()==13){
				s1 = "XXXXXXXXX";
				s2 = result.substring(9);
				
			} 
			
			result = s1 + s2; 
		}
			
		return result;
	}
	
	public static String ymdToDmyHhmm(String d) throws Exception {
		return dmyHhmmFormat.format(ymdHHmmSSFormat.parse(d));
	}
	
	public static String ymdToDmyHhmmss(String d) throws Exception {
		return dmyHhmmssFormat.format(ymdHHmmSSFormat.parse(d));
	}
	
	public static String ymdToDmy(String d) throws Exception {
		if(d == null){
			return "";
		}else{	
			return dmyFormat.format(ymdHHmmSSFormat.parse(d));
		}
	}
	
	public static String ymdToHhmm(String d) throws Exception {
		return HHmmFormat.format(ymdHHmmSSFormat.parse(d));
	}
	
	public static String dmyToDmyHhmm(String d) throws Exception {
		return dmyHhmmFormat.format(dmyHhmmFormat.parse(d));
	}
	
	public static String dmyHHmmToHhmm(String d) throws Exception {
		return HHmmFormat.format(dmyHhmmFormat.parse(d));
	}
	
	public static String dmyToDmy(String d) throws Exception {
		return dmyFormat.format(dmyHhmmFormat.parse(d));
	}
	
	public static String dmyToYmd(String d) throws Exception {
		return ymdSlashFormat.format(dmyFormat.parse(d));
	}
	
	public static String ymdSlashToDmy(String d) throws Exception {
		return dmyFormat.format(ymdSlashFormat.parse(d));
	}
	
	public static String dmyHHmmToHhmmAmPm(String d) throws Exception {
		
		meridiemSymbols.setAmPmStrings(new String[] {"am", "pm"});
		hhmmAmPmFormat.setDateFormatSymbols(meridiemSymbols);
		
		return hhmmAmPmFormat.format(dmyHhmmFormat.parse(d));
	}
	
	
	/************dmyHhmmFormat to dmyHhmmTH*********************/
	public static String dmyHhmmToDay(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return new SimpleDateFormat("dd",new Locale("th","th")).format(ymdSlashFormat.parse(str));	
		}		
	}
	
	public static String dmyHhmmToMonthTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return monthTH.format(ymdSlashFormat.parse(str));	
		}		
	}
	
	public static String dmyHhmmToYear(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{				
			if(Integer.parseInt(str.substring(0,4))<2500){
				return yearTH.format(ymdSlashFormat.parse(str));
			}else{
				return yearEN.format(ymdSlashFormat.parse(str));
			}	
		}
	}
	
	public static String dmyHhmmToDmyTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dmyTH.format(dmyHhmmFormat.parse(str));	
		}
	}/************dmyHhmmFormat to dmyHhmmTH*********************/
	
	
	public static String yyyymmddHhmmssToDay(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return new SimpleDateFormat("dd",new Locale("th","th")).format(yyyymmddHhmmssFormat.parse(str));	
		}		
	}
	
	public static String yyyymmddHhmmssToMonthTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return monthTH.format(yyyymmddHhmmssFormat.parse(str));	
		}		
	}
	
	public static String yyyymmddHhmmssToYearTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			if(Integer.parseInt(str.substring(0,4))<2500){
				return yearTH.format(yyyymmddHhmmssFormat.parse(str));
			}else{
				return str;
			}
		}
	}
	
	public static String yyyymmddHhmmssToDmyTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dmyTH.format(yyyymmddHhmmssFormat.parse(str));	
		}
	}
	
	
	/************dmyFormat to dmyHhmmTH*********************/
	public static String dmyToDay(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return new SimpleDateFormat("dd",new Locale("th","th")).format(ymdSlashFormat.parse(str));	
		}
	}
	
	public static String dmyToMonthTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return monthTH.format(ymdSlashFormat.parse(str));
		}
	}
	
	public static String dmyToYearTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			if(Integer.parseInt(str.substring(0,4))<2500){
				return yearTH.format(ymdSlashFormat.parse(str));
			}else{
				return str;
			}	
		}		
	}
	
	public static String ymdToDmyTHSpace(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dmyThSpace.format(ymdSlashFormat.parse(str));	
		}
	}
	
	public static String ymdToDmyTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dmyTh.format(ymdSlashFormat.parse(str));	
		}
	}
	
	public static String YmdTodmy(String d) throws Exception {
		if(d == null || d.equals("")){
			return "";
		}else{	
			return dmyFormat.format(ymdSlashFormat.parse(d));
		}
	}
	
	public static String joinStringArray(String[] str) throws Exception{
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
		   result.append( str[i] );
		}
		return result.toString();
	}

	public static String setIPPosAddress(String rcvIPAddress, String rcvStoreId,String rcvStationId) throws Exception {
		String strIPPosAddress = "";
		
		if(rcvIPAddress.equals(Const.SERVER_TEST_01) ||
				rcvIPAddress.equals("10.184.4.21") ||
				rcvIPAddress.equals(Const.SERVER_TEST_02) ||	
				rcvIPAddress.equals(Const.SERVER_TEST_03) ||
				rcvIPAddress.equals(Const.SERVER_TEST_04)) {

		}
		
		return strIPPosAddress;
	}
	
	
	public static String setIPSCAddress(String rcvIPAddress, String rcvStoreId) throws Exception {
		String strIPSCAddress = "";
		
		if(rcvIPAddress.equals(Const.SERVER_TEST_01) ||
				rcvIPAddress.equals("10.184.4.21") ||
				rcvIPAddress.equals(Const.SERVER_TEST_02) ||
				rcvIPAddress.equals(Const.SERVER_TEST_03) ||
				rcvIPAddress.equals(Const.SERVER_TEST_04)) {
			
			if(rcvStoreId.equals("19196")) {
				strIPSCAddress = "117.199.194.119";	// Store Id == 19196
			} else if(rcvStoreId.equals("19197")) {
				strIPSCAddress = "117.199.194.179"; //Store Id : 19197
			} else if(rcvStoreId.equals("19118")) {
				strIPSCAddress = "117.191.108.189"; //Store Id : 19118
			
			} else if(rcvStoreId.equals("19884")) {
				strIPSCAddress = "117.199.191.199"; 
			} else if(rcvStoreId.equals("19801")) {
				strIPSCAddress = "117.199.191.119"; //Store Id : 19118
			} else if(rcvStoreId.equals("19891")) {
				strIPSCAddress = "117.199.191.99"; //Store Id : 19118
			} else if(rcvStoreId.equals("19991")) {
				strIPSCAddress = "117.199.191.129"; //Store Id : 19118
			} else if(rcvStoreId.equals("19886")) {
				strIPSCAddress = "117.199.191.219"; //Store Id : 19118
			} else {		
				strIPSCAddress = "117.199.194.179";
			}

		} else {
			strIPSCAddress = Const.POS_PREFIX
			+ rcvStoreId.substring(1, 3)
			+ ".1"
			+ rcvStoreId.substring(3, 5)
			+ ".119";
		}
		
		return strIPSCAddress;
	}
	
	
	
	public static String fill(
			String inStr,
			String addStr,
			int strSize,
			boolean prefix)
			throws Exception {
		
			String tmpStr ;
			
			if (inStr == null) {
				inStr = "";
			}
			if ((addStr == null) || (addStr.length() == 0))
				throw new NullPointerException("Add String is null");
			else if (inStr.length() > strSize) {
				return inStr.substring(0, strSize);
			}
			if (strSize < 0)
				throw new Exception("Size of string must more than 0.");
			if (strSize == 0)
				return "";
			int addStrSize = strSize - inStr.length();
			int round = (addStrSize / addStr.length());
			tmpStr = "";
			for (int i = 0; i < round; i++) {
				tmpStr += addStr;
			}
			return (prefix ? tmpStr + inStr : inStr + tmpStr);
		}
	
	public static String[] split(String str, String expr) throws Exception {
		String[] result = null;
		ArrayList a = new ArrayList();
		int start = 0;
		int end = 0;
		
		for(int i=0; i<10; i++){
			if(str.indexOf(expr, start+1)<0){
				start = start + 1;
				end = str.length();
				if(str.substring(start,end).length()==0){
					a.add(" ");
				} else {
					a.add(str.substring(start,end));
				}
				break;
			} else {
				if(i>0){
					start = start + 1;
				}
				end = str.indexOf(expr, start);
			}
			
			a.add(str.substring(start,end));
			start = end;
		}		

		return null;
	}
	
	public static String chkAndConvertDmyToYmd(String date) throws Exception{	
		String result = date;
		//if(date.indexOf("/")!=0){
		if(date!=null && !date.equals("")){
			if(date.indexOf("/")==2){
				if(date.indexOf(":")>=0){
					String[] v = date.split(" ");
					result = StringUtil.dmyToYmd(v[0]) + " " + v[1];
				} else {
					result = StringUtil.dmyToYmd(date);
				}
								
			}
		}

		return result;
	}
	
	public static String chkAndConvertYmdToDmy(String date) throws Exception{	
		String result = date;
		//if(date.indexOf("/")!=0){
		if(date!=null && !date.equals("")){
			if(date.indexOf("/")==4){
				if(date.indexOf(":")>=0){
					String[] v = date.split(" ");
					result = StringUtil.ymdSlashToDmy(v[0]) + " " + v[1];
				} else {
					result = StringUtil.ymdSlashToDmy(date);
				}
								
			}
		}

		return result;
	}
	
	public static String convertYearTHToEN(String d) throws Exception{	
		String result = d;
		if(d!=null && d.indexOf("/")==4){
			if(Integer.parseInt(d.substring(0,4))>2500){
				result = (Integer.parseInt(d.substring(0,4)) - 543) + d.substring(4,10);
			}
		} else {
			if(Integer.parseInt(d.substring(6,10))>2500){
				result = d.substring(0,6) + (Integer.parseInt(d.substring(6,10)) - 543);
			}
		}
		return result;
	}
	
	public static int randomNumber(int min, int max)throws Exception{
		Random ran = new Random();
		int range = max - min + 1;
		int result = ran.nextInt(range) + min;
		ran = null;
		return  result;
	}

//	@SuppressWarnings("deprecation")
//	public static void main(String[] args) throws Exception{
//		try{
//			//convertYearTHToEN("2557/09/29"));
//			//convertYearTHToEN("29/09/2557"));
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}
	
	public static String setSpaceToUnderScore(String text){
		return text.replace(' ', '_');
	}
	
	public static String setUnderScoreToSpace(String text){
		return text.replace('_', ' ');
	}
	
	public static String dmyHhmmssToMonthTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return monthTH.format(dmyHhmmssFormat.parse(str));
		}
	}
	
	public static String dmyHhmmssToYearTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return yearTH.format(dmyHhmmssFormat.parse(str));
		}
	}
	
	public static String dmyHhmmssToDayFormat(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dayFormat.format(dmyHhmmssFormat.parse(str));
		}
	}
	
	public static String dmyHhmmssToDmyYearTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dmYearTh.format(dmyHhmmssFormat.parse(str));
		}
	}
	
	public static String dmyHhmmssToDmyYearTHHHmmss(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dmYearThHHmmss.format(dmyHhmmssFormat.parse(str));
		}
	}
	
	public static String dmyHhmmssToHhmm(String d) throws Exception {		
		if(d == null || d.equals("")){
			return "";
		}else{	
			return HHmmFormat.format(dmyHhmmssFormat.parse(d));
		}		
	}
	
	public static String dmyHHmmssToDmyHHmmssSlash(String d) throws Exception {		
		if(d == null || d.equals("")){
			return "";
		}else{	
			return dmyHHmmssSlashFormat.format(dmyHHmmSS.parse(d));
		}		
	}
	
	
	public static String dmyHHmmssToDmyTHSpace(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dmyThSpace.format(dmyHhmmssFormat.parse(str));	
		}
	}
	
	public static String dmyHHmmssToDmyFormatUS(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dmyFormat.format(dmyHhmmssFormat.parse(str));	
		}
	}
	
	public static String replaceSpace(String str){
		if(str!=null && !str.equals("")){
			str = str.replace(" ", "");
		}
		return str;
	}
	
	public static String dmyNotSlashToYmd(String d) throws Exception {
		return yyyyMMdd.format(ddMMyyyy.parse(d));
	}
	
	
	public static String yyyyMMddToDDmmYYYY(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{										
			
			if(str.indexOf("/")>2){
								
				if(Integer.parseInt(str.substring(0,4))<2500){
					
					return dmyFormat.format(ymdSlashFormat.parse(str));
				}else{
					String dateInfo = str.substring(4,str.length());					
					str = String.valueOf(Integer.parseInt(str.substring(0,4)) - 543);					
					str += dateInfo;					
					
					return dmyFormat.format(ymdSlashFormat.parse(str));
				}														
			}
		}
		
		return str;
	}

	public static String dmyHHmmssToDmyHHmmThSpace(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return dmyHHmmThSpace.format(dmyHhmmssFormat.parse(str));	
		}
	}
	
	public static String dmyHHmmssToHHmmss(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return hhMMssFormat.format(dmyHhmmssFormat.parse(str));	
		}
	}
	
	public static String dmyToDDMMMTH(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			return ddMMMTH.format(dmyFormat.parse(str));	
		}
	}	
	
	public static String dateFormatToDmyHHmmss(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			
			return dmyHhmmssFormat.format(dateZoneFormat.parse(str));	
		}
	}
	
	public static String dmyHHmmssToYmd(String str) throws Exception{
		if(str == null || str.equals("")){
			return "";
		}else{	
			
			return ymdHHmmSSFormat.format(dmyHhmmssFormat.parse(str));	
		}
	}
	
	public static String convertStrParamByDate(String rcvParamName,Logger rcvLogger) throws Exception{
		
		String newParamName = null;		
		Date now = new Date();
		
		try{					
			
			newParamName = rcvParamName;
					
			rcvLogger.debug("convertFileNameParamDate Begin");	
			//System.out.println("convertFileNameParamDate Begin");
			
			rcvLogger.debug("Old Param : "+rcvParamName);			
			//System.out.println("FileName : "+newFileName);
			//System.out.println("FileName : "+newFileName.indexOf("$"));	
			//System.out.println("FileName : "+newFileName.lastIndexOf("$"));							
			
			newParamName = newParamName.replace("$YYYY", "$yyyy");
			newParamName = newParamName.replace("$DD", "$dd");
			newParamName = newParamName.replace("$mm", "$MM");
			newParamName = newParamName.replace("#HH", "#hh");
			newParamName = newParamName.replace("#MM", "#mm");
			newParamName = newParamName.replace("#SS", "#ss");
			
			newParamName = newParamName.replace("$dd", new SimpleDateFormat("dd",Locale.US).format(now));
			newParamName = newParamName.replace("$MM", new SimpleDateFormat("MM",Locale.US).format(now));
			newParamName = newParamName.replace("$yyyy", new SimpleDateFormat("yyyy",Locale.US).format(now));
			
			newParamName = newParamName.replace("#hh", new SimpleDateFormat("HH",Locale.US).format(now));
			newParamName = newParamName.replace("#mm", new SimpleDateFormat("mm",Locale.US).format(now));
			newParamName = newParamName.replace("#ss", new SimpleDateFormat("ss",Locale.US).format(now));
			
			rcvLogger.debug("New Param "+newParamName);	
			
		} catch (Exception ex){		
			//rcvLogger.error("ConvertURLParamDate Error : "+ex, ex);
			ex.printStackTrace();
			throw ex;
		}
		
		return newParamName;
	}
	
	public static void delete(File file)
	    	throws IOException{
	 
	    	if(file.isDirectory()){
	 
	    		//directory is empty, then delete it
	    		if(file.list().length==0){
	    			
	    		   file.delete();
	    		   //"Directory is deleted : "+ file.getAbsolutePath());
	    			
	    		}else{
	    			
	    		   //list all the directory contents
	        	   String files[] = file.list();
	     
	        	   for (String temp : files) {
	        		   //"temp : "+temp);
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	        	      //"sub dir : "+fileDelete);
	        	      //recursive delete
	        	      delete(fileDelete);
	        	   }
	        		
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	     //"Directory is deleted : " + file.getAbsolutePath());
	        	   }
	    		}
	    		
	    	}else{
	    		//if file, then delete it
	    		file.delete();
	    		//"File is deleted : " + file.getAbsolutePath());
	    	}
	    }
	
	
	
	public static boolean delFileByCondition(File rcvDir, final String rcvStr){		
		
		try{
					
			final File[] temp = rcvDir.listFiles( new FilenameFilter() {
			    @Override
			    public boolean accept( final File dir,
			                           final String name ) {
			    	return name.matches( rcvStr+".*" );
			    }
			} );
			
			
			for ( final File subFile : temp ) {
								
				//---- CLEAN SUB DIRS & SUB FILES CHILD
				if (!isSymlink(subFile)) {
					  cleanDirectory(subFile);
				}
				
				//---- CLEAN PARENT
			    if ( !subFile.delete() ) {
			        System.err.println( "Can't remove " + subFile.getAbsolutePath() );
			    }
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	public static boolean isSymlink(final File file) throws IOException {
		    
		if (file == null) {
		    throw new NullPointerException("File must not be null");
		}
		     
        File fileInCanonicalDir = null;
        
        if (file.getParent() == null) {
            fileInCanonicalDir = file;
        } else {
            final File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalDir = new File(canonicalDir, file.getName());
        }

        if (fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile())) {
            return false;
        } else {
            return true;
        }
	}
	
	
	public static void cleanDirectory(final File directory) throws IOException {
	        if (!directory.exists()) {
	            final String message = directory + " does not exist";
	            throw new IllegalArgumentException(message);
	        }
	
	        if (!directory.isDirectory()) {
	            final String message = directory + " is not a directory";
	            throw new IllegalArgumentException(message);
	        }
	
	        final File[] files = directory.listFiles();
	        if (files == null) {  // null if security restricted
	            throw new IOException("Failed to list contents of " + directory);
	        }
	
	        IOException exception = null;
	        for (final File file : files) {
	            try {
	                forceDelete(file);
	            } catch (final IOException ioe) {
	                exception = ioe;
	            }
	        }
	
	        if (null != exception) {
	            throw exception;
	        }
	}
	
	public static void forceDelete(final File file) throws IOException {
		        if (file.isDirectory()) {
		            deleteDirectory(file);
		        } else {
		            final boolean filePresent = file.exists();
		            if (!file.delete()) {
		                if (!filePresent){
		                    throw new FileNotFoundException("File does not exist: " + file);
		                }
		                final String message =
		                    "Unable to delete file: " + file;
		                throw new IOException(message);
		            }
		        }
	}
	
	public static void deleteDirectory(final File directory) throws IOException {
	        if (!directory.exists()) {
	            return;
	        }
	
	        if (!isSymlink(directory)) {
	            cleanDirectory(directory);
	        }
	
	        if (!directory.delete()) {
	            final String message =
	                "Unable to delete directory " + directory + ".";
	            throw new IOException(message);
	        }
	 }
	
	
	public static String hoursAgo(String datetime) throws ParseException {
	    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(datetime); // Parse into Date object
	    Date now = new Date(); // Get time now
	    long differenceInMillis = now.getTime() - date.getTime();
	    long differenceInHours = (differenceInMillis) / 1000L / 60L / 60L; // Divide by millis/sec, secs/min, mins/hr
	    long differenceInMin =  (differenceInMillis) / 1000L / 60L;
	    long differenceInSec =  (differenceInMillis) / 1000L;
	    
	    //differenceInMillis);
	    
	    String hour = String.format("%02d", differenceInHours);
	    String min = String.format("%02d", differenceInMin - (60L * differenceInHours));
	    String sec = String.format("%02d", differenceInSec - (((60L * differenceInHours) + (differenceInMin - (60L * differenceInHours))) * 60L));
	    
//	    //"differenceInHours : "+differenceInHours);
//	    //"differenceInMin : "+differenceInMin);
//	    //"differenceInSec : "+differenceInSec);
	    
	    //System.out.println(hour+":"+min+":"+sec);
	    
	    return hour+":"+min+":"+sec;
	}
	
	public static Calendar convertTimeFormString(String timeDaily) throws ParseException {

		Calendar cDate = Calendar.getInstance();
		Date date = new Date();
		cDate.setTime(date);								
		String[] data = timeDaily.split(":");
		String hour = null;
		String min = null;
		String sec = null;
		
		System.out.println("cDate : "+cDate.getTime());
		
		if(data.length == 3){
			
			for(String time : data){
							
				if(StringUtil.isEmpty(hour)){									
					
					hour = time;
					
					if(Integer.parseInt(hour) > 24){
						System.out.println("Hour of day format incorrect : "+Integer.parseInt(hour));
					}
			
				}else if(StringUtil.isEmpty(min)){
															
					min = time;
					
					if(Integer.parseInt(min) > 60){
						System.out.println("Minute of day format incorrect : "+Integer.parseInt(min));
					}
					
				}else if(StringUtil.isEmpty(sec)){										
										
					sec = time;
					
					if(Integer.parseInt(sec) > 60){
						System.out.println("Second of day format incorrect : "+Integer.parseInt(sec));
					}
					
				}
				
			}
			
			cDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
			cDate.set(Calendar.MINUTE, Integer.parseInt(min));
			cDate.set(Calendar.SECOND, Integer.parseInt(sec));
			
			System.out.println("cDate : "+cDate.getTime());
		
		}else{
			
			System.out.println("Not One Time Daily");
			
		}
	    
	    return cDate;
	}
	
	public static void main(String[] args) throws Exception{
		String ddMMyyyy = "2558/03/16 #hh";
		String yyyyMMdd = "2015/04/11";
		 SimpleDateFormat dmyTh = new SimpleDateFormat("dd MMMM yyyy",new Locale("th","th"));
		Date now = new Date(); 
		 
		 System.out.println(ddMMyyyy.replace("#hh", new SimpleDateFormat("mm",Locale.US).format(now)));
		 
		//"DMY : "+StringUtil.yyyyMMddToDDmmYYYY(ddMMyyyy));
		
		//StringUtil.dmyHhmmssToDmyYearTH("30/12/1899 00:00:00"));			
		
		//StringUtil.hoursAgo("2016-01-29 07:15:00"));
	
	}
		
		
}
