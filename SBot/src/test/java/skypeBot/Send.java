package skypeBot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Send {


	/**
	 * @method send holiday Message
	 * @param date
	 * @throws Exception
	 */

	public static void holidayMessage(String date) throws Exception {
		try{
			Map<String, String> dayMap = XlsData.getDayMap(date);
			List<String> friend_ids = XlsData.getFriends(dayMap.get("friends"));
			for (String friend_id : friend_ids) { 
				SkypeMessager.sendMessage(friend_id, dayMap.get("text"));
			}
		}catch (Exception e){
			System.out.println("holidayMessage - "+e.getMessage());
		}
	}


	/**
	 * @method send random Message To Friends
	 * @param date
	 * @throws Exception
	 */

	public static void randomMessageToMyFriends() throws Exception {
		try{
			List<String> friends = XlsData.getFriends("my");
			for (String friend : friends) { 
				String ssStr = new SimpleDateFormat("ss").format(new Date());
				int ss = Integer.parseInt(ssStr);
				if((ss&1L)==0){
					String randomHiMessage = XlsData.getRandomHiMessage();
					String randomGif = XlsData.getRandomGif();
					SkypeMessager.sendMessage(friend, randomHiMessage+"\n"+randomGif);
				}else{
					System.out.println("random message is false");
				}
				Thread.sleep(1234);
			}
		}catch (Exception e){
			System.out.println("randomMessageToFriends - "+e.getMessage());
		}
	}






}
