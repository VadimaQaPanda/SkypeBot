package skypeBot;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.Map;

public class Start {

	/**
	 * @method Start Bot
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		int thisDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int thisHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		Map<String, String> settingsMap = XlsData.getSettingsMap();
		int fromHour = Integer.parseInt(settingsMap.get("from_time"));
		int toHour = Integer.parseInt(settingsMap.get("to_time"));
		boolean sendRandom = Integer.valueOf(settingsMap.get("send_random")) == 1;
		boolean sendHoliday = Integer.valueOf(settingsMap.get("send_holiday")) == 1;

		System.out.println("hour = "+thisHour);
		JOptionPane.showMessageDialog(null, "Skype Bot was started");
		for(int j=0;j<31;){
			try{
				for(int i=0;i<24;){
					try{
						settingsMap = XlsData.getSettingsMap();
						fromHour = Integer.parseInt(settingsMap.get("from_time"));
						toHour = Integer.parseInt(settingsMap.get("to_time"));
						sendRandom = Integer.valueOf(settingsMap.get("send_random")) == 1;
						sendHoliday = Integer.valueOf(settingsMap.get("send_holiday")) == 1;
						System.out.println("i = "+i);
						if(thisHour>=fromHour && thisHour<=toHour){
							if(sendRandom){
								Send.randomMessageToMyFriends();}
							if(sendHoliday){
								Send.holidayMessage(new SimpleDateFormat("dd MM").format(new Date()));}
							Thread.sleep(10800000); // wait 3h
							Thread.sleep(10000);
							i+=3;
							thisHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
						}
						Thread.sleep(3600000); // wait 1h
						i+=1;
						thisHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
						System.out.println("hour = "+thisHour);
						if(thisDay!=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
							j=+1;
							break;
						}
					}catch (Exception e){
						System.out.println(e.getMessage());
					}
				}
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}








}
