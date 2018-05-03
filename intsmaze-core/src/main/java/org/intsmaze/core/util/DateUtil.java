
package org.intsmaze.core.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.intsmaze.core.exception.FDFBRuntimeException;
public class DateUtil {

	public final static String default_date_joint = "-";
	public final static String default_datemillisformat = "yyyy-MM-dd HH:mm:ss.SSS";
	public final static String default_datetimeformat = "yyyy-MM-dd HH:mm:ss";
	public final static String default_datetimeformat2 = "yyyyMMdd HH:mm:ss";
	public final static String default_icddatetimeformat = "yyyy-MM-dd'T'HH:mm:ss";
	public final static String default_dateformat = "yyyy-MM-dd";
	public final static String default_file_datetimeformat = "yyyyMMddHHmmssSSS";
	public final static String defaule_date = "EEE MMM dd hh:mm:ss z yyyy";
	
	private static String default_timezone = "Asia/Shanghai";

	public static final String DATE_FORMAT_NUMBER8 = "yyyyMMdd";
	private static boolean LENIENT_DATE = false;	

	private static Integer offset = new Integer(0); // number of days to offset
	private static final int CONST_DAY_MILL_SECONDS = 86400000;

	private static Logger logger = Logger.getLogger(DateUtil.class);

	/**
	 * Returns the timestamp instance based on the current system date and
	 * default timezone.
	 * 
	 * @return the current timestamp
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(getDate().getTime());
	}
	
	public static Date defaultStringtoDate(String defultStr){
		try {
			return new SimpleDateFormat(defaule_date,Locale.ENGLISH).parse(defultStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * Returns the date instance based on the current system date and default
	 * timezone.
	 * <p/>
	 * 
	 * @return Current System date.
	 */
	public static Date getDate() {
		// [Etc/GMT+12, Etc/GMT+11, MIT, Pacific/Apia, Pacific/Midway,
		// Pacific/Niue, Pacific/Pago_Pago, Pacific/Samoa, US/Samoa,
		// America/Adak, America/Atka, Etc/GMT+10, HST, Pacific/Fakaofo,
		// Pacific/Honolulu, Pacific/Johnston, Pacific/Rarotonga,
		// Pacific/Tahiti, SystemV/HST10, US/Aleutian, US/Hawaii,
		// Pacific/Marquesas, AST, America/Anchorage, America/Juneau,
		// America/Nome, America/Yakutat, Etc/GMT+9, Pacific/Gambier,
		// SystemV/YST9, SystemV/YST9YDT, US/Alaska, America/Dawson,
		// America/Ensenada, America/Los_Angeles, America/Tijuana,
		// America/Vancouver, America/Whitehorse, Canada/Pacific, Canada/Yukon,
		// Etc/GMT+8, Mexico/BajaNorte, PST, PST8PDT, Pacific/Pitcairn,
		// SystemV/PST8, SystemV/PST8PDT, US/Pacific, US/Pacific-New,
		// America/Boise, America/Cambridge_Bay, America/Chihuahua,
		// America/Dawson_Creek, America/Denver, America/Edmonton,
		// America/Hermosillo, America/Inuvik, America/Mazatlan,
		// America/Phoenix, America/Shiprock, America/Yellowknife,
		// Canada/Mountain, Etc/GMT+7, MST, MST7MDT, Mexico/BajaSur, Navajo,
		// PNT, SystemV/MST7, SystemV/MST7MDT, US/Arizona, US/Mountain,
		// America/Belize, America/Cancun, America/Chicago, America/Costa_Rica,
		// America/El_Salvador, America/Guatemala, America/Indiana/Knox,
		// America/Indiana/Tell_City, America/Knox_IN, America/Managua,
		// America/Menominee, America/Merida, America/Mexico_City,
		// America/Monterrey, America/North_Dakota/Center,
		// America/North_Dakota/New_Salem, America/Rainy_River,
		// America/Rankin_Inlet, America/Regina, America/Swift_Current,
		// America/Tegucigalpa, America/Winnipeg, CST, CST6CDT, Canada/Central,
		// Canada/East-Saskatchewan, Canada/Saskatchewan, Chile/EasterIsland,
		// Etc/GMT+6, Mexico/General, Pacific/Easter, Pacific/Galapagos,
		// SystemV/CST6, SystemV/CST6CDT, US/Central, US/Indiana-Starke,
		// America/Atikokan, America/Bogota, America/Cayman,
		// America/Coral_Harbour, America/Detroit, America/Fort_Wayne,
		// America/Grand_Turk, America/Guayaquil, America/Havana,
		// America/Indiana/Indianapolis, America/Indiana/Marengo,
		// America/Indiana/Petersburg, America/Indiana/Vevay,
		// America/Indiana/Vincennes, America/Indiana/Winamac,
		// America/Indianapolis, America/Iqaluit, America/Jamaica,
		// America/Kentucky/Louisville, America/Kentucky/Monticello,
		// America/Lima, America/Louisville, America/Montreal, America/Nassau,
		// America/New_York, America/Nipigon, America/Panama,
		// America/Pangnirtung, America/Port-au-Prince, America/Resolute,
		// America/Thunder_Bay, America/Toronto, Canada/Eastern, Cuba, EST,
		// EST5EDT, Etc/GMT+5, IET, Jamaica, SystemV/EST5, SystemV/EST5EDT,
		// US/East-Indiana, US/Eastern, US/Michigan, America/Caracas,
		// America/Anguilla, America/Antigua, America/Argentina/San_Luis,
		// America/Aruba, America/Asuncion, America/Barbados,
		// America/Blanc-Sablon, America/Boa_Vista, America/Campo_Grande,
		// America/Cuiaba, America/Curacao, America/Dominica, America/Eirunepe,
		// America/Glace_Bay, America/Goose_Bay, America/Grenada,
		// America/Guadeloupe, America/Guyana, America/Halifax, America/La_Paz,
		// America/Manaus, America/Marigot, America/Martinique, America/Moncton,
		// America/Montserrat, America/Port_of_Spain, America/Porto_Acre,
		// America/Porto_Velho, America/Puerto_Rico, America/Rio_Branco,
		// America/Santiago, America/Santo_Domingo, America/St_Barthelemy,
		// America/St_Kitts, America/St_Lucia, America/St_Thomas,
		// America/St_Vincent, America/Thule, America/Tortola, America/Virgin,
		// Antarctica/Palmer, Atlantic/Bermuda, Atlantic/Stanley, Brazil/Acre,
		// Brazil/West, Canada/Atlantic, Chile/Continental, Etc/GMT+4, PRT,
		// SystemV/AST4, SystemV/AST4ADT, America/St_Johns, CNT,
		// Canada/Newfoundland, AGT, America/Araguaina,
		// America/Argentina/Buenos_Aires, America/Argentina/Catamarca,
		// America/Argentina/ComodRivadavia, America/Argentina/Cordoba,
		// America/Argentina/Jujuy, America/Argentina/La_Rioja,
		// America/Argentina/Mendoza, America/Argentina/Rio_Gallegos,
		// America/Argentina/Salta, America/Argentina/San_Juan,
		// America/Argentina/Tucuman, America/Argentina/Ushuaia, America/Bahia,
		// America/Belem, America/Buenos_Aires, America/Catamarca,
		// America/Cayenne, America/Cordoba, America/Fortaleza, America/Godthab,
		// America/Jujuy, America/Maceio, America/Mendoza, America/Miquelon,
		// America/Montevideo, America/Paramaribo, America/Recife,
		// America/Rosario, America/Santarem, America/Sao_Paulo,
		// Antarctica/Rothera, BET, Brazil/East, Etc/GMT+3, America/Noronha,
		// Atlantic/South_Georgia, Brazil/DeNoronha, Etc/GMT+2,
		// America/Scoresbysund, Atlantic/Azores, Atlantic/Cape_Verde,
		// Etc/GMT+1, Africa/Abidjan, Africa/Accra, Africa/Bamako,
		// Africa/Banjul, Africa/Bissau, Africa/Casablanca, Africa/Conakry,
		// Africa/Dakar, Africa/El_Aaiun, Africa/Freetown, Africa/Lome,
		// Africa/Monrovia, Africa/Nouakchott, Africa/Ouagadougou,
		// Africa/Sao_Tome, Africa/Timbuktu, America/Danmarkshavn,
		// Atlantic/Canary, Atlantic/Faeroe, Atlantic/Faroe, Atlantic/Madeira,
		// Atlantic/Reykjavik, Atlantic/St_Helena, Eire, Etc/GMT, Etc/GMT+0,
		// Etc/GMT-0, Etc/GMT0, Etc/Greenwich, Etc/UCT, Etc/UTC, Etc/Universal,
		// Etc/Zulu, Europe/Belfast, Europe/Dublin, Europe/Guernsey,
		// Europe/Isle_of_Man, Europe/Jersey, Europe/Lisbon, Europe/London, GB,
		// GB-Eire, GMT, GMT0, Greenwich, Iceland, Portugal, UCT, UTC,
		// Universal, WET, Zulu, Africa/Algiers, Africa/Bangui,
		// Africa/Brazzaville, Africa/Ceuta, Africa/Douala, Africa/Kinshasa,
		// Africa/Lagos, Africa/Libreville, Africa/Luanda, Africa/Malabo,
		// Africa/Ndjamena, Africa/Niamey, Africa/Porto-Novo, Africa/Tunis,
		// Africa/Windhoek, Arctic/Longyearbyen, Atlantic/Jan_Mayen, CET, ECT,
		// Etc/GMT-1, Europe/Amsterdam, Europe/Andorra, Europe/Belgrade,
		// Europe/Berlin, Europe/Bratislava, Europe/Brussels, Europe/Budapest,
		// Europe/Copenhagen, Europe/Gibraltar, Europe/Ljubljana,
		// Europe/Luxembourg, Europe/Madrid, Europe/Malta, Europe/Monaco,
		// Europe/Oslo, Europe/Paris, Europe/Podgorica, Europe/Prague,
		// Europe/Rome, Europe/San_Marino, Europe/Sarajevo, Europe/Skopje,
		// Europe/Stockholm, Europe/Tirane, Europe/Vaduz, Europe/Vatican,
		// Europe/Vienna, Europe/Warsaw, Europe/Zagreb, Europe/Zurich, MET,
		// Poland, ART, Africa/Blantyre, Africa/Bujumbura, Africa/Cairo,
		// Africa/Gaborone, Africa/Harare, Africa/Johannesburg, Africa/Kigali,
		// Africa/Lubumbashi, Africa/Lusaka, Africa/Maputo, Africa/Maseru,
		// Africa/Mbabane, Africa/Tripoli, Asia/Amman, Asia/Beirut,
		// Asia/Damascus, Asia/Gaza, Asia/Istanbul, Asia/Jerusalem,
		// Asia/Nicosia, Asia/Tel_Aviv, CAT, EET, Egypt, Etc/GMT-2,
		// Europe/Athens, Europe/Bucharest, Europe/Chisinau, Europe/Helsinki,
		// Europe/Istanbul, Europe/Kaliningrad, Europe/Kiev, Europe/Mariehamn,
		// Europe/Minsk, Europe/Nicosia, Europe/Riga, Europe/Simferopol,
		// Europe/Sofia, Europe/Tallinn, Europe/Tiraspol, Europe/Uzhgorod,
		// Europe/Vilnius, Europe/Zaporozhye, Israel, Libya, Turkey,
		// Africa/Addis_Ababa, Africa/Asmara, Africa/Asmera,
		// Africa/Dar_es_Salaam, Africa/Djibouti, Africa/Kampala,
		// Africa/Khartoum, Africa/Mogadishu, Africa/Nairobi, Antarctica/Syowa,
		// Asia/Aden, Asia/Baghdad, Asia/Bahrain, Asia/Kuwait, Asia/Qatar,
		// Asia/Riyadh, EAT, Etc/GMT-3, Europe/Moscow, Europe/Volgograd,
		// Indian/Antananarivo, Indian/Comoro, Indian/Mayotte, W-SU,
		// Asia/Riyadh87, Asia/Riyadh88, Asia/Riyadh89, Mideast/Riyadh87,
		// Mideast/Riyadh88, Mideast/Riyadh89, Asia/Tehran, Iran, Asia/Baku,
		// Asia/Dubai, Asia/Muscat, Asia/Tbilisi, Asia/Yerevan, Etc/GMT-4,
		// Europe/Samara, Indian/Mahe, Indian/Mauritius, Indian/Reunion, NET,
		// Asia/Kabul, Asia/Aqtau, Asia/Aqtobe, Asia/Ashgabat, Asia/Ashkhabad,
		// Asia/Dushanbe, Asia/Karachi, Asia/Oral, Asia/Samarkand,
		// Asia/Tashkent, Asia/Yekaterinburg, Etc/GMT-5, Indian/Kerguelen,
		// Indian/Maldives, PLT, Asia/Calcutta, Asia/Colombo, Asia/Kolkata, IST,
		// Asia/Kathmandu, Asia/Katmandu, Antarctica/Mawson, Antarctica/Vostok,
		// Asia/Almaty, Asia/Bishkek, Asia/Dacca, Asia/Dhaka, Asia/Novosibirsk,
		// Asia/Omsk, Asia/Qyzylorda, Asia/Thimbu, Asia/Thimphu, BST, Etc/GMT-6,
		// Indian/Chagos, Asia/Rangoon, Indian/Cocos, Antarctica/Davis,
		// Asia/Bangkok, Asia/Ho_Chi_Minh, Asia/Hovd, Asia/Jakarta,
		// Asia/Krasnoyarsk, Asia/Phnom_Penh, Asia/Pontianak, Asia/Saigon,
		// Asia/Vientiane, Etc/GMT-7, Indian/Christmas, VST, Antarctica/Casey,
		// Asia/Brunei, Asia/Choibalsan, Asia/Chongqing, Asia/Chungking,
		// Asia/Harbin, Asia/Hong_Kong, Asia/Irkutsk, Asia/Kashgar,
		// Asia/Kuala_Lumpur, Asia/Kuching, Asia/Macao, Asia/Macau,
		// Asia/Makassar, Asia/Manila, Asia/Shanghai, Asia/Singapore,
		// Asia/Taipei, Asia/Ujung_Pandang, Asia/Ulaanbaatar, Asia/Ulan_Bator,
		// Asia/Urumqi, Australia/Perth, Australia/West, CTT, Etc/GMT-8,
		// Hongkong, PRC, Singapore, Australia/Eucla, Asia/Dili, Asia/Jayapura,
		// Asia/Pyongyang, Asia/Seoul, Asia/Tokyo, Asia/Yakutsk, Etc/GMT-9, JST,
		// Japan, Pacific/Palau, ROK, ACT, Australia/Adelaide,
		// Australia/Broken_Hill, Australia/Darwin, Australia/North,
		// Australia/South, Australia/Yancowinna, AET,
		// Antarctica/DumontDUrville, Asia/Sakhalin, Asia/Vladivostok,
		// Australia/ACT, Australia/Brisbane, Australia/Canberra,
		// Australia/Currie, Australia/Hobart, Australia/Lindeman,
		// Australia/Melbourne, Australia/NSW, Australia/Queensland,
		// Australia/Sydney, Australia/Tasmania, Australia/Victoria, Etc/GMT-10,
		// Pacific/Guam, Pacific/Port_Moresby, Pacific/Saipan, Pacific/Truk,
		// Pacific/Yap, Australia/LHI, Australia/Lord_Howe, Asia/Magadan,
		// Etc/GMT-11, Pacific/Efate, Pacific/Guadalcanal, Pacific/Kosrae,
		// Pacific/Noumea, Pacific/Ponape, SST, Pacific/Norfolk,
		// Antarctica/McMurdo, Antarctica/South_Pole, Asia/Anadyr,
		// Asia/Kamchatka, Etc/GMT-12, Kwajalein, NST, NZ, Pacific/Auckland,
		// Pacific/Fiji, Pacific/Funafuti, Pacific/Kwajalein, Pacific/Majuro,
		// Pacific/Nauru, Pacific/Tarawa, Pacific/Wake, Pacific/Wallis, NZ-CHAT,
		// Pacific/Chatham, Etc/GMT-13, Pacific/Enderbury, Pacific/Tongatapu,
		// Etc/GMT-14, Pacific/Kiritimati]
		TimeZone tz = TimeZone.getTimeZone(default_timezone);
		Calendar calendar = Calendar.getInstance(tz);
		calendar.add(Calendar.DATE, offset.intValue());
		return calendar.getTime();
	}	
	/**
	 * Format the input date to a date time string in the following format: <br>
	 * <code>yyyy-MM-dd HH:mm:ss</code>
	 * 
	 * @param date
	 *            the date time value to be formatted into a date time string.
	 * @return the formatted date time string; an empty String if the input date
	 *         is <code>null</code> or if there is error during formatting.
	 */
	public static String formatDateTime(Date date) {
		String dateTimeStr = "";
		if(date == null)
		{
			return "";
		}

		try {
			DateFormat defaultDTF = new SimpleDateFormat(default_datetimeformat);
			dateTimeStr = defaultDTF.format(date);
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return dateTimeStr;
	}
	
	public static String formatDateTimeByFormat(Date date,String format) {
		String dateTimeStr = "";

		try {
			DateFormat defaultDTF = new SimpleDateFormat(format);
			dateTimeStr = defaultDTF.format(date);
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return dateTimeStr;
	}
	
	
	/**
	 * Format the input date to a date time string in the following format: <br>
	 * <code>yyyy-MM-ddTHH:mm:ss</code>
	 * 
	 * @param date
	 *            the date time value to be formatted into a date time string.
	 * @return the formatted date time string; an empty String if the input date
	 *         is <code>null</code> or if there is error during formatting.
	 */
	public static String formatICDDateTime(Date date) {
		String dateTimeStr = "";

		try {
			DateFormat defaultDTF = new SimpleDateFormat(default_icddatetimeformat);
			dateTimeStr = defaultDTF.format(date);
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return dateTimeStr;
	}
	
	public static Timestamp formatStringDateToTimestamp(String strDate){
		SimpleDateFormat sdf = new SimpleDateFormat();
		Timestamp ret = null;
		sdf.applyPattern("yyyyMMdd");
		try {
			Date d = sdf.parse(strDate);
			ret = new Timestamp(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}
		return ret;	
	}
	
	public static Timestamp formatStringDateToTimestamp(String strDate, String format){
		SimpleDateFormat sdf = new SimpleDateFormat();
		Timestamp ret = null;
		sdf.applyPattern(format);
		try {
			Date d = sdf.parse(strDate);
			ret = new Timestamp(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}
		return ret;	
	}
	
	public static String formatDate(String srtDate){
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String ret = "";
		try {
			Date date = format.parse(srtDate);
			format = new SimpleDateFormat("yyyyMMdd");
			ret = format.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}
		return ret;
	}

	/**
	 * Format the input date to a date time string in the following format: <br>
	 * <code>yyyy-MM-dd HH:mm:ss.SSS</code>
	 * 
	 * @param date
	 *            the date time value to be formatted into a date time string.
	 * @return the formatted date time string; an empty String if the input date
	 *         is <code>null</code> or if there is error during formatting.
	 */
	public static String formatDateMillis(Date date) {
		
		if(date == null)
		{
			return "";
		}
		String dateTimeStr = "";

		try {
			DateFormat defaultDMF = new SimpleDateFormat(
					default_datemillisformat);
			dateTimeStr = defaultDMF.format(date);
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return dateTimeStr;
	}

	

	/**
	 * Format the input date to a date time string in the following format: <br>
	 * <code>yyyy-MM-dd</code>
	 * 
	 * @param date
	 *            the date time value to be formatted into a date time string.
	 * @return the formatted date time string; an empty String if the input date
	 *         is <code>null</code> or if there is an error during formatting
	 */
	public static String formatDate(Date date) {
		if(date==null)
		{
			return "";
		}
		String dateStr = "";

		try {
			DateFormat defaultDF = new SimpleDateFormat(default_dateformat);
			dateStr = defaultDF.format(date);
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return dateStr;
	}

	/**
	 * Get the formatted string of the given date instance based on the date
	 * format provided.
	 * <p/>
	 * See {@link java.text.SimpleDateFormat SimpleDateFormat} for examples of
	 * the format string.
	 * <p/>
	 * 
	 * @param date
	 *            The date that needs to be formatted
	 * @param format
	 *            The format to be applied to the date
	 * @return The formatted Date String; an empty String if the given date or
	 *         format is <code>null</code>, or if there is error in formatting
	 */
	public static String format(Date date, String format) {
		if (date == null || format == null)
			return "";

		String dateStr = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateStr = sdf.format(date);
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return dateStr;
	}

	/**
	 * Parse the input date to a <code>Date</code> object. The expected input
	 * date is of format <code>yyyy-MM-dd</code>
	 * 
	 * @param dateStr
	 *            the date string.
	 * @return the date instance parsed from the date string; <code>null</code>
	 *         if the date string is <code>null</code> or if the date string is
	 *         invalid
	 */
	public static Date parse(String dateStr) {
		Date date = null;

		try {
			DateFormat defaultDF = new SimpleDateFormat(default_dateformat);
			date = defaultDF.parse(replaceDelimiters(dateStr));
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return date;
	}

	/**
	 * Returns a Date object instance from the input string. The input date
	 * string is parsed to return a date object based on the format provided.
	 * <p/>
	 * Eg., to parse the date string "01-01-2003 9:2 PM", use the format
	 * "yyyy-MM-dd h:m a". The resultant data object will have the value
	 * "Mar 11 2003 09:02", as displayed in 24 hr format.
	 * <p/>
	 * 
	 * @param dateStr
	 *            the date string
	 * @param format
	 *            the date format that the date string conforms to
	 * @return the date instance parsed from the date string; <code>null</code>
	 *         if the date string is <code>null</code> of if the date string is
	 *         invalid
	 */
	public static Date parse(String dateStr, String format) {
		Date date = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return date;
	}

	/**
	 * Parse the input date to a <code>java.sql.Date</code> object. The expected
	 * input date is of format <code>yyyy-MM-dd</code>
	 * 
	 * @param dateStr
	 *            the date string.
	 * @return the date instance created; <code>null</code> if the date string
	 *         is <code>null</code> or it does not conform to the format
	 *         <code>yyyy-MM-dd</code>
	 */
	public static java.sql.Date parseSQLDate(String dateStr) {

		java.sql.Date sqlDate = null;

		try {
			DateFormat defaultDF = new SimpleDateFormat(default_dateformat);
			Date date = defaultDF.parse(replaceDelimiters(dateStr));
			sqlDate = new java.sql.Date(date.getTime());
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return sqlDate;
	}

	/**
	 * Parse the input date to a <code>java.sql.Timestamp</code> object. The
	 * expected input date is of format <code>yyyy-MM-dd</code> or
	 * <code>yyyy-MM-dd HH:mm:ss</code>
	 * 
	 * @param dateStr
	 *            the date string.
	 * @return the Timestamp instance created; <code>null</code> if the date
	 *         string is <code>null</code> or it does not conform to the format
	 *         <code>yyyy-MM-dd</code> or <code>yyyy-MM-dd HH:mm:ss</code>
	 */
	public static java.sql.Timestamp parseTimestamp(String dateStr) {

		java.sql.Timestamp tsDate = null;

		DateFormat defaultDTF = null;
		if (dateStr != null && dateStr.length() == 10) {
			// append a default time element
			dateStr += " 00:00:00";
			defaultDTF = new SimpleDateFormat(default_datetimeformat);
		}else if (dateStr != null && dateStr.length() == 8) {
			// append a default time element
			dateStr += " 00:00:00";
			defaultDTF = new SimpleDateFormat(default_datetimeformat2);
		}
		else
		{
			defaultDTF = new SimpleDateFormat(default_datetimeformat);
		}

		try {
			Date date = defaultDTF.parse(replaceDelimiters(dateStr));
			tsDate = new java.sql.Timestamp(date.getTime());
		} catch (Exception e) {
			logger.error(e);
			
			
		}

		return tsDate;
	}

	
	public static java.sql.Timestamp parseTTimestamp(String dateStr) {

		java.sql.Timestamp tsDate = null;

		if (dateStr != null && dateStr.length() == 10) {
			// append a default time element
			dateStr += "T00:00:00";
		}

		try {
			DateFormat defaultDTF = new SimpleDateFormat(default_icddatetimeformat);
			Date date = defaultDTF.parse(replaceDelimiters(dateStr));
			tsDate = new java.sql.Timestamp(date.getTime());
		} catch (Exception e) {
			logger.error(e);
			
			
		}

		return tsDate;
	}
	/**
	 * format Timestamp Date
	 * @param dateStr
	 * @return
	 */
	public static Timestamp formatICDTimestamp(String dateStr){
		Timestamp tsDate = null;
		if(StringUtils.isBlank(dateStr)){
			return null;
		}	
		try {
			DateFormat df = new SimpleDateFormat(default_icddatetimeformat);
			Date date = df.parse(replaceDelimiters(dateStr));
			tsDate = new Timestamp(date.getTime());
		} catch (ParseException e) {
			logger.error(e);
			throw new FDFBRuntimeException(e.toString());
		}
		return tsDate;
	}
		
	/**
	 * Parse the input date to a <code>java.sql.Timestamp</code> object. The
	 * expected input date is of format <code>yyyy-MM-dd HH:mm:ss.SSS</code>
	 * 
	 * @param dateStr
	 *            the date string.
	 * @return the Timestamp instance created; <code>null</code> if the date
	 *         string is <code>null</code> or it does not conform to the format
	 *         <code>yyyy-MM-dd HH:mm:ss.SSS</code>
	 */
	public static java.sql.Timestamp parseDateMillis(String dateStr) {

		java.sql.Timestamp tsDate = null;

		try {
			int SSSlength = dateStr.substring(dateStr.indexOf(".")).length();
			if(SSSlength==2){
				dateStr =dateStr+"00";
			}else if(SSSlength==3){
				dateStr =dateStr+"0";
			}
			DateFormat defaultDMF = new SimpleDateFormat(
					default_datemillisformat);
			Date date = defaultDMF.parse(replaceDelimiters(dateStr));
			tsDate = new java.sql.Timestamp(date.getTime());
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return tsDate;
	}

	/**
	 * Parse the input date to a <code>java.sql.Timestamp</code> object. The
	 * expected input date is of format <code>yyyy-MM-dd HH:mm:ss.ffffff</code>
	 * where ffffff is microseconds.
	 * 
	 * @param dateStr
	 *            the date string.
	 * @return the Timestamp instance created; <code>null</code> if the date
	 *         string is <code>null</code> or it does not conform to the format
	 *         <code>yyyy-MM-dd HH:mm:ss.ffffff</code>
	 */
	public static java.sql.Timestamp parseDateMicro(String dateStr) {

		java.sql.Timestamp tsDate = null;
		dateStr = replaceDelimiters(dateStr);

		if (dateStr != null
				&& dateStr
						.matches("^\\d{2}\\-\\d{2}\\-\\d{4} \\d{2}:\\d{2}:\\d{2}\\.\\d{6}$")) {

			int dateStrLength = dateStr.length();

			// Get the last 3 digit.
			String microsecondsString = dateStr.substring(dateStrLength - 3);
			int microseconds = Integer.valueOf(microsecondsString).intValue();

			String millisDateStr = dateStr.substring(0, dateStrLength - 3);
			tsDate = parseDateMillis(millisDateStr);

			// Add microseconds to timestamp.
			int nanos = tsDate.getNanos();
			nanos += microseconds * 1000;
			tsDate.setNanos(nanos);
		}

		return tsDate;
	}

	/**
	 * Retrieves the value of the field in the Date. Some common fields that is
	 * likely to be used include :
	 * <p/>
	 * <li>Calendar.YEAR - retrieves the year value
	 * <li>Calendar.MONTH - retrieves the month value ( 1 - 12 )
	 * <li>Calendar.DAY_OF_MONTH - retrieve the day value ( 1 - 31 )
	 * <li>Calendar.HOUR - retrieves the hours value in 12 hour format ( 1 - 12
	 * )
	 * <li>Calendar.HOUR_OF_DAY - retrieves the hours value in 24 hour format (
	 * 0 - 23 )
	 * <li>Calendar.MINUTE - retrieves the minutes value ( 0 - 59 )
	 * <li>Calendar.AM_PM - retrieves the am/pm value ( 0 = am; 1 = pm )
	 * <p/>
	 * 
	 * @param date
	 *            The Date object to extract value from.
	 * @param field
	 *            A Calendar constant to retrieve the field value from the Date
	 *            object.
	 * @return The value of the field that is requested.
	 * @throws ArrayIndexOutOfBoundsException
	 *             - if specified field is out of range (<code>field</code> &lt;
	 *             0 || <code>field</code> &gt;= <code>Calendar.FIELD_COUNT
	 *             </code>).
	 * @see java.util.Calendar
	 */
	public static int get(Date date, int field) {

		TimeZone tz = TimeZone.getTimeZone(default_timezone);
		Calendar cal = Calendar.getInstance(tz);

		cal.setTime(date);

		int value = cal.get(field);

		// Add 1 if the field is Calendar.MONTH since Calendar returns
		// the month value starting from 0.
		if (Calendar.MONTH == field)
			value += 1;

		// If it is 12 am/pm, the value will be 0. Need to change it to 12 for
		// ease of display.
		if (Calendar.HOUR == field && value == 0)
			value = 12;

		return value;
	}

	/**
	 * Tests the input value to ensure that a valid Date instance can be created
	 * from it. Roll over dates are not allowed here and will return a false
	 * value. Eg. isValidDate(2002, 10, 32) will return false.
	 * <p/>
	 * 
	 * @param year
	 *            The year value.
	 * @param month
	 *            The month value. ( 1 - 12 )
	 * @param day
	 *            The day value. ( 1 - 31 )
	 * @return True if all values can be used to create a single valid Date
	 *         instance.
	 */
	public static boolean isValidDate(int year, int month, int day) {

		if (day <= 0 || month <= 0 || year <= 0)
			return false;
		if (month > 12 || day > 31)
			return false;

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);

		// Find the maximum field value possible for the day with the year and
		// month.
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return (day <= maxDay);
	}

	/**
	 * Tests the input string to ensure that a valid Date instance can be
	 * created from it according to the format provided.
	 * <p/>
	 * 
	 * @param dateStr
	 *            A date string.
	 * @param format
	 *            Date format to conform to.
	 * @return True if it conforms to the specified date format; false
	 *         otherwise.
	 */
	public static boolean isValidDate(String dateStr, String format) {

		if (dateStr == null) {
			return false;
		}

		Date date = parse(dateStr, format);
		return (date != null);
	}

	

	/**
	 * Tests if the inputs are valid time. When the ampm parameter is true, the
	 * input hour will be tested for 12-hour format ( 1 ? 12 ). When it is
	 * false, the input hour will be tested for 24-hour format ( 0 ? 23 ).
	 * <p/>
	 * 
	 * @param hour
	 *            The Hour value. ( 0 - 23 or 1 - 12 )
	 * @param minute
	 *            The Minute value. ( 0 - 59 )
	 * @param ampm
	 *            If true, the time is in 12 hour format. Otherwise, it is in 24
	 *            hour format.
	 * @return True if the time inputs can be used to create a valid time
	 *         instance.
	 */
	public static boolean isValidTime(int hour, int minute, boolean ampm) {

		if (minute < 0 || minute > 59)
			return false;

		if (ampm && (hour < 1 || hour > 12))
			return false;

		return !(hour < 0 || hour > 23);
	}

	/**
	 * Compute the age returning an array of integers, for year, month, and day
	 * respectively.
	 * <p/>
	 * 
	 * @param birthdate
	 *            The start date to start the age computation.
	 * @param asOf
	 *            The end date for the age computation
	 * @return The age in years, months, days in the 0, 1, 2 indices
	 *         respectively.
	 */
	public static int[] age(Date birthdate, Date asOf) {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.setTime(birthdate);
		to.setTime(asOf);

		int birthYYYY = from.get(Calendar.YEAR);
		int birthMM = from.get(Calendar.MONTH);
		int birthDD = from.get(Calendar.DAY_OF_MONTH);

		int asofYYYY = to.get(Calendar.YEAR);
		int asofMM = to.get(Calendar.MONTH);
		int asofDD = to.get(Calendar.DAY_OF_MONTH);

		int ageInYears = asofYYYY - birthYYYY;
		int ageInMonths = asofMM - birthMM;
		int ageInDays = asofDD - birthDD;

		if (ageInDays < 0) {
			// Guaranteed after this single treatment, ageInDays will be >= 0.
			// i.e. ageInDays = asofDD - birthDD + daysInBirthMM.
			ageInDays += from.getActualMaximum(Calendar.DAY_OF_MONTH);
			ageInMonths--;
		}

		if (ageInDays == to.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			ageInDays = 0;
			ageInMonths++;
		}

		if (ageInMonths < 0) {
			ageInMonths += 12;
			ageInYears--;
		}
		if (birthYYYY < 0 && asofYYYY > 0)
			ageInYears--;

		if (ageInYears < 0) {
			ageInYears = 0;
			ageInMonths = 0;
			ageInDays = 0;
		}

		int[] result = new int[3];
		result[0] = ageInYears;
		result[1] = ageInMonths;
		result[2] = ageInDays;
		return result;
	}

	/**
	 * Return the Date instance with the provided year, month ( 1 - 12 ), and
	 * day ( 1 - 31 ) values.
	 * <p/>
	 * The date value will roll over when given a value that is greater than the
	 * max possible. Eg. when getDate( 2002, 10, 32 ) is provided, the Date
	 * instance will be 1st Nov 2002.
	 * <p/>
	 * 
	 * @param year
	 *            Year
	 * @param month
	 *            Month ( 1 - 12 )
	 * @param day
	 *            Day( 1 - 31 )
	 * @return The Date instance created.
	 */
	public static Date getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();

		// Clear all fields first
		cal.clear();

		cal.set(year, month - 1, day);

		return cal.getTime();
	}

	/**
	 * Set the time component as the last seconds of the day.
	 * <p/>
	 * The Time Component of the date returned will be set to 23:59:59.
	 * <p/>
	 * 
	 * @param date
	 *            The Date to get the last seconds
	 * @return The date with the time component set to the last seconds of the
	 *         day.
	 */
	public static Date getEndOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Clear the time component
		cal.set(Calendar.HOUR_OF_DAY, cal
				.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal
				.getActualMaximum(Calendar.MILLISECOND));

		// NcsLogger.debug(getClass(), "cal.toString() = " + cal.toString() );

		return cal.getTime();
	}

	/**
	 * Set the time component as the start of the day.
	 * <p/>
	 * The Time Component of the date returned will be set to 00:00:00.
	 * <p/>
	 * 
	 * @param date
	 *            The Date to get the start of the day
	 * @return The date with the time component reset to 00:00:00.
	 */
	public static Date getStartOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Clear the time component
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		return getDate(year, month, day);
	}

	/**
	 * Date Arithmetic function. Adds the specified (signed) amount of time to
	 * the given time field, based on the calendar's rules.
	 * <p>
	 * For example, to subtract 5 days from a specific date, it can be achieved
	 * by calling:
	 * <p>
	 * DateUtil.add(date, Calendar.DATE, -5).
	 * <p>
	 * 
	 * @param date
	 *            The date to perform the arithmetic function on
	 * @param field
	 *            A Calendar constant to retrieve the field value from the Date
	 *            object. Same as for {@link #get get()}.
	 * @param amount
	 *            the amount of date or time to be added to the field
	 * @return The date as a result of the execution of the arithmetic function.
	 */
	public static Date add(Date date, int field, int amount) {
		TimeZone tz = TimeZone.getTimeZone(default_timezone);
		Calendar cal = Calendar.getInstance(tz);
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}

	/**
	 * Convert Date from Number(8) to Date format. (yyyyMMdd) --> Date
	 * 
	 * @param bdDate
	 * @return If covert failed(param is null or is not Date), return null.
	 */
	public static Date convertDateFromNumber8ToDate(BigDecimal bdDate) {
		if (bdDate == null) {
			return null;
		}
		String sDate = bdDate.toString();
		Date date = null;
		try {
			DateFormat number8DF = new SimpleDateFormat(DATE_FORMAT_NUMBER8);
			date = number8DF.parse(sDate);
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}
		return date;
	}

	/**
	 * Convert Date from Number(8) to String format. (yyyyMMdd) --> (yyyy-MM-dd)
	 * 
	 * @param bdDate
	 * @return If covert failed(param is null), return null.
	 */
	public static String convertDateFromNumber8ToString(BigDecimal bdDate) {
		if (bdDate == null) {
			return null;
		}

		String strDate = bdDate.toString();

		if (strDate.equals("0")) {
			return "0000-00-00";
		}

		String yyyy = strDate.substring(0, 4);
		String MM = strDate.substring(4, 6);
		String dd = strDate.substring(6, 8);
		return yyyy + "-" + MM + "-" + dd;
	}

	/**
	 * Convert Date from String to Number(8). Supports:
	 * 
	 * (yyyy-MM-dd) --> (yyyyMMdd)
	 * 
	 * 
	 * @param sDate
	 * @return If covert failed(param is null), return null.
	 */
	public static BigDecimal convertDateFromStringToNumber8(String sDate) {
		if (sDate == null || "".equals(sDate.trim())) {
			return null;
		}
		sDate = replaceDelimiters(sDate);

		String number8 = sDate.replaceAll("-", "");// ddMMyyyy

		String yyyy = "";
		String MM = "";
		String dd = "";

		if (number8.length() == 10) {

			yyyy = number8.substring(6, 10);
			MM = number8.substring(3, 5);
			dd = number8.substring(0, 2);

		} else if (number8.length() == 8) {

			yyyy = number8.substring(4, 8);
			MM = number8.substring(2, 4);
			dd = number8.substring(0, 2);
		}
		return new BigDecimal(yyyy + MM + dd);
	}

	/**
	 * Convert a Bigdecimal to a timeslot with format HH:MM AM or HH:MM PM <br>
	 * sample: input 930 will return 09:30 AM
	 * 
	 * @param timeslot
	 *            timeslot as BigDecimal
	 * @return <code>null</code> if conversion failed or input is
	 *         <code>null</code>; else return a timeslot String in HH:MM AM or
	 *         HH:MM PM
	 */
	public static String convertBigDecimalToTimeslot(BigDecimal timeslot) {
		String ind = "AM";
		if (timeslot == null) {
			return "";
		} else {
			StringBuffer oriValue = new StringBuffer(timeslot.toString());
			while (oriValue.length() < 4) {
				oriValue.insert(0, "0");
			}
			String strValue = oriValue.toString();
			String hh = strValue.substring(0, 2);
			String mm = strValue.substring(2, 4);
			int intHH = Integer.parseInt(hh);
			if (intHH > 12) {
				hh = Integer.toString(intHH - 12);
				if (hh.length() == 1) {
					hh = "0" + hh;
				}
				ind = "PM";
			} else if (intHH == 12) {
				ind = "PM";
			}
			return hh + ":" + mm + " " + ind;
		}
	}

	/**
	 * convert a timeslot with format HH:MM AM or HH:MM PM to a BigDecimal <br>
	 * sample: input 01:00 PM will return 1300
	 * 
	 * @param timeslot
	 *            timeslot as String
	 * @return return <code>null</code> if input length not equal 8 or the last
	 *         2 chars not equalsIgnoreCase "am" or "pm"; else return a
	 *         Bigdecimal
	 */
	public static BigDecimal convertTimeslotToBigDecimal(String timeslot) {
		if (timeslot == null || "".equalsIgnoreCase(timeslot)
				|| timeslot.length() != 8) {
			return null;
		} else {
			timeslot = timeslot.trim();
			String ind = timeslot.substring(timeslot.length() - 2, timeslot
					.length());
			if ("AM".equalsIgnoreCase(ind)
					|| ("PM".equalsIgnoreCase(ind) && "12"
							.equalsIgnoreCase(timeslot.substring(0, 2)))) {
				return new BigDecimal(timeslot.substring(0, 2)
						+ timeslot.substring(3, 5));
			} else if ("PM".equalsIgnoreCase(ind)) {
				return new BigDecimal(Integer.toString(Integer
						.parseInt(timeslot.substring(0, 2)) + 12)
						+ timeslot.substring(3, 5));
			} else {
				return null;
			}
		}
	}

	/**
	 * Compute the duration in number of days.
	 * <ul>
	 * <li>If the start date is later than the end date, a negative value will
	 * be returned.
	 * <li>The duration includes the start and end dates, and exclude the given
	 * <code>day of week</code>, and exclude the given
	 * <code>holiday dates</code>
	 * <li>Time is not considered in the duration computation.
	 * <li>If the start date and end date is the same day, the duration is 0.
	 * For e.g. if start date=01/01/2006, end date=02/01/2006, no Day of Week
	 * and holidays to be excluded, then the duration is 1 day
	 * <li>If any of the <code>holiday dates</code> falls in the given
	 * <code>day of week</code>, it is only counted as one excluded day.
	 * </ul>
	 * <p>
	 * <code>Day of Week</code> should only comprise any of the following:
	 * <ul>
	 * <li><code>Calendar.MONDAY</code>
	 * <li><code>Calendar.TUESDAY</code>
	 * <li><code>Calendar.WEDNESDAY</code>
	 * <li><code>Calendar.THURSDAY</code>
	 * <li><code>Calendar.FRIDAY</code>
	 * <li><code>Calendar.SATURDAY</code>
	 * <li><code>Calendar.SUNDAY</code>
	 * </ul>
	 * </p>
	 * 
	 * @param startDt
	 *            the start date
	 * @param endDt
	 *            the end date
	 * @param dayOfWeek
	 *            the array of Day of Week to be excluded from the duration
	 *            computation. Provide an array size of 0 if there are no day of
	 *            week to be excluded in the computation.
	 * @param holidayDates
	 *            the array of holiday dates. Provide an array of size 0 if
	 *            there are no holiday dates to be excluded in the computation.
	 * @return the duration in days. Duration will exclude the given day of week
	 *         and holiday dates if the excluded day fall within the start and
	 *         end date range.
	 * 
	 * @see java.util.Calendar
	 */
	public static int computeDurationInDays(Date startDt, Date endDt,
			int[] dayOfWeek, Date[] holidayDates) {
		int days = (int) ((endDt.getTime() - startDt.getTime()) / CONST_DAY_MILL_SECONDS);
		startDt = DateUtil.getStartOfDay(startDt);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDt);
		int weekendsDays = 0;
		int holidays = 0;

		// count the weekend days
		for (int i = 0; i < days + 1; i++) {
			for (int j = 0; j < dayOfWeek.length; j++) {
				if (cal.get(Calendar.DAY_OF_WEEK) == dayOfWeek[j]) {
					weekendsDays++;
				}
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		// count the holidays, if the holiday is weekend date, not count the
		// holiday.
		for (int i = 0; i < holidayDates.length; i++) {
			if (holidayDates[i].compareTo(startDt) >= 0
					&& holidayDates[i].compareTo(endDt) <= 0) {
				cal.setTime(holidayDates[i]);
				holidays++;
				for (int j = 0; j < dayOfWeek.length; j++) {
					if (cal.get(Calendar.DAY_OF_WEEK) == dayOfWeek[j]) {
						holidays--;
					}
				}
			}
		}
		return days - weekendsDays - holidays;
	}

	/**
	 * Compute the duration in minutes. If the start date is later than the end
	 * date, a negative value will be returned. Seconds are discounted from the
	 * computation.
	 * 
	 * @param startDt
	 *            the start date
	 * @param endDt
	 *            the end date
	 * @return the duration in minutes.
	 */
	public static int computeDurationInMinutes(Date startDt, Date endDt) {
		return (int) ((endDt.getTime() - startDt.getTime()) / 60000);
	}

	/**
	 * Compute the due date for duration measured in minutes. Seconds are
	 * discounted from the computation. Accuracy is up to the minutes.
	 * 
	 * @param startDate
	 *            the start date
	 * @param minsDuration
	 *            the duration in minutes.
	 * @return the due date with accuracy to the minutes.
	 */
	public static Date computeDueDate(Date startDate, int minsDuration) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.MINUTE, minsDuration);
		return cal.getTime();
	}

	/**
	 * Compute the due date for duration measured in days.
	 * <ul>
	 * <li>The computation will take into account the given
	 * <code>Day of Week</code>, and the given <code>holiday dates</code>.
	 * <li>Time is discounted from the due date computation.
	 * <li>For a duration of 1 day, if <code>Day of Week</code> and
	 * <code>Holiday dates</code> are not provided, the due date is the next day
	 * from the given start date.
	 * </ul>
	 * <p>
	 * <code>Day of Week</code> should only comprise any of the following:
	 * <ul>
	 * <li><code>Calendar.MONDAY</code>
	 * <li><code>Calendar.TUESDAY</code>
	 * <li><code>Calendar.WEDNESDAY</code>
	 * <li><code>Calendar.THURSDAY</code>
	 * <li><code>Calendar.FRIDAY</code>
	 * <li><code>Calendar.SATURDAY</code>
	 * <li><code>Calendar.SUNDAY</code>
	 * </ul>
	 * </p>
	 * 
	 * @param startDate
	 *            the start date
	 * @param daysDuration
	 *            the duration in days
	 * @param dayOfWeek
	 *            the array of Day of Week to be excluded from the duration
	 *            computation. Provide an array size of 0 if there are no day of
	 *            week to be excluded in the computation.
	 * @param holidayDates
	 *            the array of holiday dates. Provide an array of size 0 if
	 *            there are no holiday dates to be excluded in the computation.
	 * @return Date the due date where the minutes element is 12am (00:00)
	 * 
	 * @see java.util.Calendar
	 */
	public static Date computeDueDate(Date startDate, int daysDuration,
			int[] dayOfWeek, Date[] holidayDates) {
		// set the date to start date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.getStartOfDay(startDate));

		// get the non work day maps
		Map dayOfWeekMap = new HashMap();
		for (int i = 0; i < dayOfWeek.length; i++) {
			int oneDayOfWeek = dayOfWeek[i];
			dayOfWeekMap.put(new Integer(oneDayOfWeek), "");
		}

		// get the due date
		for (int i = 0; i < daysDuration + 1; i++) {
			// if the date is non work day
			if (dayOfWeekMap.containsKey(new Integer(cal
					.get(Calendar.DAY_OF_WEEK)))) {
				daysDuration++;
			}
			// if the day is holiday
			else {
				for (int j = 0; j < holidayDates.length; j++) {
					if (cal.getTime().equals(holidayDates[j])) {
						daysDuration++;
						break;
					}
				}
			}
			// if the day is due day, no need to add.
			if (i != daysDuration) {
				cal.add(Calendar.DATE, 1);
			}
		}
		return cal.getTime();
	}

	/**
	 * force replace incoming date string delimiters to '-'
	 * 
	 * @param dateStr
	 * 
	 * @return String
	 */
	private static String replaceDelimiters(String dateStr) {
		if (dateStr.length() >= 10) {
			String dd = dateStr.substring(8, dateStr.length());
			String MM = dateStr.substring(5, 7);
			String yyyy = dateStr.substring(0, 4);

			return yyyy.concat("-").concat(MM).concat("-").concat(dd);
		} else {
			return dateStr;
		}
	}

    /**
     * convert Timestamp to number8 format
     * @param ts
     * @return
     */
    public static String convertDateToNumber8(Timestamp ts) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_NUMBER8);
        return  df.format(ts);
    }
    
	/**
	 * 根据时间变量返回时间字符串
	 * 
	 * @return 返回时间字符串
	 * @param pattern
	 *            时间字符串样式
	 * @param date
	 *            时间变量
	 */
	public static String dateToString(Date date, String pattern) {

		if (date == null) {

			return "";
		}

		try {

			SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
			sfDate.setLenient(false);
			sfDate.setTimeZone(TimeZone.getDefault());
			return sfDate.format(date);
		} catch (Exception e) {

			return "";
		}
	}    
	
	/**
	 * 返回当前时间
	 * 
	 * @return 返回当前时间
	 */
	public static Date getCurrentDateTime() {
		java.util.Calendar calNow = java.util.Calendar.getInstance();
		calNow.setTimeZone(TimeZone.getTimeZone(default_timezone));
		calNow.setTimeZone(Calendar.getInstance().getTimeZone());
		java.util.Date dtNow = calNow.getTime();
		return dtNow;
	}	
	
	/**
	 * 根据时间变量返回时间字符串 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, default_dateformat);
	}	

	
	/**
	 * 
	 * @return
	 */
	public static String getDayTimeCurDate() {
		java.util.Date dd = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat(
				default_dateformat);
		//format.setTimeZone(TimeZone.getTimeZone(TIMEZONE_GMT));
		format.setTimeZone(Calendar.getInstance().getTimeZone());
		return format.format(dd);
		// java.util.Calendar dd = java.util.Calendar.getInstance();
		// Date dt = new Date(dd.getTime());
		// return dd.getTime().toString();
	}	
	
	
	/*
	 * 获得当前日期 2007-08-23
	 */
	public static String getCurDate() {
		java.util.Date dd = new java.util.Date();
		return dateToString(dd);
		// java.util.Calendar dd = java.util.Calendar.getInstance();
		// Date dt = new Date(dd.getTime());
		// return dd.getTime().toString();
	}
	
	/*
	 * 获得当前日 23
	 */
	public static String getCurDay() {
		String s = getCurDate();
		return s.substring(s.lastIndexOf("-") + 1);
	}

	/*
	 * 获得当前长日期格式 2007-08-23 16:22:24
	 */
	public static String getCurLongDate() {
		return getDayTimeCurDate();
	}

	/*
	 * 获得当前月 08
	 */
	public static String getCurMonth() {
		String s = getCurDate();
		int iB = s.indexOf("-");
		return s.substring(iB + 1, s.indexOf("-", iB + 1));
	}

	/*
	 * 获得当前月 08
	 */
	public static int getIntCurMonth() {
		return Integer.parseInt(getCurMonth());
	}	
	
	
	/*
	 * 获得当前年 2007
	 */
	public static String getCurYear() {
		String s = getCurDate();
		return s.substring(0, s.indexOf("-"));
	}

	/*
	 * 根据传入的日期，获得对应日 getDay("2007-03-24") --->24
	 */
	public static String getDay(String sdate) {
		try {
			Date dt = java.sql.Date.valueOf(sdate);
			return dt.toString().substring(8);
		} catch (Exception _ex) {
			return "0";
		}
	}

	/*
	 * 根据传入的字符，获得对应日期 getDayFromString
	 */
	public static Date getDayFromString(String sdate) {
		Date dt = java.sql.Date.valueOf(sdate);
		java.util.Date returnDate = new java.util.Date(dt.getTime());
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					default_dateformat);
			format.setTimeZone(TimeZone.getTimeZone(default_timezone));
			returnDate = format.parse(sdate);
		} catch (Exception _ex) {
			return returnDate;
		}
		return returnDate;
	}

	/*
	 * 根据传入的字符，获得对应日期/时间
	 */
	public static Date getDayTimeFromString(String sDateTime) {
		SimpleDateFormat format = new SimpleDateFormat(
				default_dateformat);
		format.setTimeZone(TimeZone.getTimeZone(default_timezone));
		java.util.Date date1 = null;

		try {
			date1 = format.parse(sDateTime);
		} catch (Exception _ex) {
			return date1;
		}
		return date1;
	}

	/*
	 * 获得传入日期的月头日 getBeginDateOfMonth("2007-03-24") ----> 2007-03-01
	 */
	public static String getBeginDateOfMonth(String strDate) {
		strDate = strDate.substring(0, 7) + "-" + "01";
		return strDate;
	}	
	
	/*
	 * 获得传入日期的月末日 getEndDateOfMonth("2007-03-24") ----> 2007-03-31
	 */
	public static String getEndDateOfMonth(String strDate) {
		int iD = 30;
		int iM = StringUtil.str2int(strDate.substring(5, 7));
		if (iM == 1 || iM == 3 || iM == 5 || iM == 7 || iM == 8 || iM == 10
				|| iM == 12)
			iD = 31;
		else if (iM == 4 || iM == 6 || iM == 9 || iM == 11)
			iD = 30;
		else if (iM == 2)
			if (isRenYear(strDate))
				iD = 29;
			else
				iD = 28;
		strDate = strDate.substring(0, 7) + "-" + iD;
		return strDate;
	}
	
	/*
	 * 判断年是否为润年 isRenYear("2001-02-05");---false isRenYear("2000-02-05");---true
	 */
	public static boolean isRenYear(String sDate) {
		try {
			String s = java.sql.Date.valueOf(sDate).toString();
			int iYear = StringUtil.str2int(s.substring(0, 4));
			return (iYear % 4 == 0 && iYear % 100 != 0) || iYear % 400 == 0;
		} catch (Exception _ex) {
			return false;
		}
	}
	
	//得到前一个月第1天
	public static String getPreviousFirstDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, -1);
	    str = sdf.format(calendar.getTime());
		return str.substring(0, 7)+"-01";
	}	

	//得到前一个月第15天
	public static String getPreviousMiddleDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, -1);
	    str = sdf.format(calendar.getTime());
		return str.substring(0, 7)+"-15";
	}
	
	//得到前一个月最后1天
	public static String getPreviousLastDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		//calendar.add(Calendar.MONTH, -1);
		calendar.add(Calendar.DATE,-1);//减去一天，变为当月最后一天  
	    str = sdf.format(calendar.getTime());
		return str;
	}		
	
	//得到后一天
	public static String getNextDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, 1);
	    str = sdf.format(calendar.getTime());
		return str.substring(0, 7)+"-01";
	}		
	
	//得到下一个月第1天
	public static String getNextFirstDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 1);
	    str = sdf.format(calendar.getTime());
		return str;
	}	

	//得到下一个月第15天
	public static String getNextMiddleDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 1);
	    str = sdf.format(calendar.getTime());
		return str.substring(0, 7)+"-15";
	}
	
	//得到下一个月最后1天
	public static String getNextLastDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.DATE,-1);//减去一天，变为当月最后一天  
	    str = sdf.format(calendar.getTime());
		return str;
	}	

	//得到当月第1天
	public static String getThisFirstDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 0);
	    str = sdf.format(calendar.getTime());
		return str.substring(0, 7)+"-01";
	}	
	
	//得到当月第15天
	public static String getThisMiddleDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 0);
	    str = sdf.format(calendar.getTime());
		return str.substring(0, 7)+"-15";	    
	}	
	
	//得到当月最后1天
	public static String getThisLastDate(Date today) 
	{
		String str = "";   
	    SimpleDateFormat sdf = new SimpleDateFormat(default_dateformat);	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE,-1);//减去一天，变为当月最后一天  
	    str = sdf.format(calendar.getTime());
		return str;
	}		
	
	public static Date getBeginOfYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		String yesterday = String.valueOf(calendar.get(Calendar.YEAR)) + "-"
				+ String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-"
				+ String.valueOf(calendar.get(Calendar.DATE));

		return stringToDate(yesterday);
	}

	public static Date getEndOfYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		String yesterday = String.valueOf(calendar.get(Calendar.YEAR)) + "-"
				+ String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-"
				+ String.valueOf(calendar.get(Calendar.DATE));

		return stringToDate(yesterday);
	}

	public static Date getBeginOfTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		String yesterday = String.valueOf(calendar.get(Calendar.YEAR)) + "-"
				+ String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-"
				+ String.valueOf(calendar.get(Calendar.DATE));

		return stringToDate(yesterday);
	}

	public static Date getEndOfTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		String yesterday = String.valueOf(calendar.get(Calendar.YEAR)) + "-"
				+ String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-"
				+ String.valueOf(calendar.get(Calendar.DATE));

		return stringToDate(yesterday);
	}
	
	/**
	 * 字符串转换为日期java.util.Date
	 * 
	 * @param dateText
	 *            字符串
	 */
	public static Date stringToDate(String dateString) {

		return stringToDate(dateString, default_dateformat, LENIENT_DATE);
	}	
	
	/**
	 * 字符串转换为日期java.util.Date
	 * 
	 * @param dateText
	 *            字符串
	 * @param format
	 *            日期格式
	 * @param lenient
	 *            日期越界标志
	 * @return
	 */
	public static Date stringToDate(String dateText, String format,
			boolean lenient) {

		if (dateText == null) {

			return null;
		}

		DateFormat df = null;

		try {

			if (format == null) {
				df = new SimpleDateFormat();
			} else {
				df = new SimpleDateFormat(format);
			}

			// setLenient avoids allowing dates like 9/32/2001
			// which would otherwise parse to 10/2/2001
			df.setLenient(false);
			df.setTimeZone(TimeZone.getTimeZone(default_timezone));
			return df.parse(dateText);
		} catch (ParseException e) {

			return null;
		}
	}	
	
	/**
	 * Format the input date to a date time string in the following format: <br>
	 * <code>yyyy-MM-dd HH:mm:ss</code>
	 * 
	 * @param date
	 *            the date time value to be formatted into a date time string.
	 * @return the formatted date time string; an empty String if the input date
	 *         is <code>null</code> or if there is error during formatting.
	 */
	public static String formatDateTimeForFile(Date date) {
		String dateTimeStr = "";

		try {
			DateFormat defaultDTF = new SimpleDateFormat(default_file_datetimeformat);
			dateTimeStr = defaultDTF.format(date);
		} catch (Exception e) {
			logger.error(e);
			//added by xulei 20110330
			throw new FDFBRuntimeException(e.toString());
		}

		return dateTimeStr;
	}
	
	public static Timestamp delayHour(Timestamp time, String hour)
	{
		if("".equals(StringUtil.isNullString(hour)))
		{
			return time;
		}
		int hourInt = 0;
		try{
			hourInt = Integer.parseInt(hour);
		}catch (FDFBRuntimeException e) {
			// TODO: handle exception
			e.printStackTrace();
			return time;
		}
		Calendar ca=Calendar.getInstance();
		ca.setTime(time);
		ca.add(Calendar.HOUR_OF_DAY, hourInt);
		Date date = ca.getTime();
		String dateStr = DateUtil.formatDateMillis(date);
		return DateUtil.parseDateMillis(dateStr);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timestamp time = DateUtil.getTimestamp();
		time = delayHour(time, "48");
		System.out.println(DateUtil.formatDateTime(time));
	}

}
