/*******************************************************************************
 * Skype api samples https://github.com/taksan/skype-api-samples
 * Java File Libraries http://www.java2s.com/Code/Jar/s/Downloadskypejavaapi15jar.htm
 * In first time http://screencast.com/t/FFUucA8FL
 * skype Id http://screencast.com/t/8GHn62RC
 ******************************************************************************/

/*******************************************************************************
 * TEST QA CLIENT
 * MAIL = germesfort@gmail.com
 * PASS = Q1w2e3r4t
 * LOGIN = PM.QA.Team
 ******************************************************************************/

package skypeBot;

import com.skype.Skype;

public class SkypeMessager{
	

	/**
	 * @method SendMessage
	 * @param skype_id, message
	 * @throws Exception
	 * @example <pre>SkypeQA.SendMessage("krat_vadim", "hi");</pre>
	 */

	public static void sendMessage(String skypeId, String message) throws Exception {
		try{
			Skype.chat(skypeId).send(message);
			System.out.println("Skype message was sent to "+skypeId);
		}catch (Exception e){
			System.out.println("Skype message wasn't sent");
			System.out.println(e.getMessage());
		}
	}

}
