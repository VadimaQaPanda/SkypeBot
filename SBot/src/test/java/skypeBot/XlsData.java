package skypeBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

import Exception.FilloException;
import Fillo.Connection;
import Fillo.Fillo;
import Fillo.Recordset;

public class XlsData {

	static String xlsPath = "config.xls";
	static Random rand = new Random();
	static Fillo fillo=new Fillo();
	static Connection connection;

	/**
	 * @method getList
	 * @return List fields from config
	 */
	public static List<String> getList(String table, String column) throws FilloException{
		List<String> list = new ArrayList<String>();
		connection = fillo.getConnection(xlsPath);
		String strQuery="SELECT "+column+" FROM "+table;
		Recordset recordset=connection.executeQuery(strQuery);
		while (recordset.next()){
			String field = recordset.getField(column);
			if(!field.contains("null")){
				list.add(field);
			}
		}
		connection.close();
		return list;
	}	


	/**
	 * @method getFriends
	 * @return List with All Friends
	 * @throws FilloException
	 * @friends: my, qa, developer
	 */

	public static List<String> getFriends(String friends) throws FilloException{
		List<String> friendsIds = new ArrayList<String>();
		friendsIds = getList("friends", friends);
		return friendsIds;
	}

	/**
	 * @method getMessage
	 * @return  messages
	 * @throws FilloException
	 * @message: hi
	 */

	public static List<String> getMessage(String message) throws FilloException{
		List<String> messages = new ArrayList<String>();
		messages = getList("message", message);
		return messages;
	}

	/**
	 * @method getRandomHiMessage
	 * @return
	 * @throws FilloException
	 */
	public static String getRandomHiMessage() throws FilloException{
		List<String> messages = getMessage("hi");
		Integer randomNum = rand.nextInt(((messages.size()-1) - 0) + 1) + 0; 
		String message = messages.get(randomNum);
		return message;
	}

	/**
	 * @method getRandomGif
	 * @return url on gif
	 * @throws FilloException
	 * @throws IOException
	 */
	public static String getRandomGif() throws FilloException, IOException{
		Integer randomNum = rand.nextInt((60 - 1) + 1) + 1; 
		String gifsUrl = "http://forgifs.com/gallery/v/Funny/?g2_page="+randomNum;
		String gifsHtml = Jsoup.connect(gifsUrl).get().outerHtml();
		String regex = "/gallery/v/Funny/(.*).gif.html";
		Pattern pattern = Pattern.compile(regex); 
		Matcher matcher = pattern.matcher(gifsHtml);
		List<String> gifUrls = new ArrayList<String>();
		while(matcher.find()){
			String gifUrl = matcher.group();
			gifUrl = "http://forgifs.com"+gifUrl;
			gifUrls.add(gifUrl);
		}
		randomNum = rand.nextInt((11 - 0) + 1) + 0; 
		String randomGifHTMLUrl = gifUrls.get(randomNum);

		gifsHtml = Jsoup.connect(randomGifHTMLUrl).get().outerHtml();
		regex = "img src=\"/gallery/(.*).gif";
		pattern = Pattern.compile(regex); 
		matcher = pattern.matcher(gifsHtml);
		String randomGifUrl = null;
		if(matcher.find()){
			randomGifUrl = matcher.group();
			randomGifUrl = randomGifUrl.substring(9, randomGifUrl.length());
			randomGifUrl = "http://forgifs.com"+randomGifUrl;
		}

		return randomGifUrl;
	}


	/**
	 * @method getDayMap
	 * @param day
	 * @return map
	 * @throws FilloException
	 * @throws IOException
	 */
	public static Map<String, String> getDayMap(String day) throws FilloException, IOException{
		Map<String,String> dayMap = new HashMap<String,String>();
		connection = fillo.getConnection(xlsPath);
		String strQuery="SELECT * FROM days where date = '"+day+"'";
		Recordset recordset=connection.executeQuery(strQuery);
		while (recordset.next()){
			dayMap.put("text", recordset.getField("text"));
			dayMap.put("name", recordset.getField("name"));
			dayMap.put("friends", recordset.getField("friends"));
		}
		connection.close();
		return dayMap;
	}

	/**
	 * @method getSettingsMap
	 * @return map 
	 * @throws FilloException
	 * @throws IOException
	 */
	public static Map<String, String> getSettingsMap() throws FilloException, IOException{
		Map<String,String> settingsMap = new HashMap<String,String>();
		connection = fillo.getConnection(xlsPath);
		String strQuery="SELECT * FROM settings";
		Recordset recordset=connection.executeQuery(strQuery);
		while (recordset.next()){
			settingsMap.put("from_time", recordset.getField("from_time"));
			settingsMap.put("to_time", recordset.getField("to_time"));
			settingsMap.put("send_random", recordset.getField("send_random"));
			settingsMap.put("send_holiday", recordset.getField("send_holiday"));
		}
		connection.close();
		return settingsMap;
	}

}
