package testvagrant.com.qa;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class ResponseValidation {

// Test 1 : validate that a team have only 4 foreign players
	
	public static JsonPath js;
	@Test
	public static void testCase1() {
		js = new JsonPath(Payload.testPayload());

		int size = js.getInt("player.size()");

		Map<String, String> map = new HashMap<String, String>();
		
		String countryName = "";
		String playerNmae = "";
	
		
		int indianPlayerCount = 0;
		int foriegnPlayerCount = 0;

		for (int i = 0; i < size; i++) {
			countryName = js.get("player[" + i + "].country");
			playerNmae = js.get("player[" + i + "].name");
		
			map.put(playerNmae, countryName);
			
	
			
		}

		System.out.println("RBC Team member player name ---:" + map);

		for (Entry<String, String> map1 : map.entrySet()) {
			if (map1.getValue().contains("India")) {
				System.out.println("Player name  ---:" + map1.getKey() + " --:Country Name --: " + map1.getValue());
				indianPlayerCount++;
			} else {
				System.out.println("Player name ---:" + map1.getKey() + "--:Country Name --:" + map1.getValue());
				foriegnPlayerCount++;
			}

		}
		System.out.println("Indian Player team member count ---:" + indianPlayerCount);
		System.out.println("Foriegn Player team member count --- :" + foriegnPlayerCount);

		
	}
	
// Test 2 : Validate that a team have at least 1 wicket keep
	@Test
	public static void testCase2()
	{
		int size = js.getInt("player.size()");
		String role="";
		int wicketKeepCount=0;
		
		for (int i = 0; i < size; i++) {
			
			role = js.get("player[" + i + "].role");
		
			
			if(role.contains("Wicket-keeper"))
			{
				wicketKeepCount++;
				break;
			}
		}
		System.out.println("At least 1 Wicket Keeper in a Team ---:"+wicketKeepCount);
	}

}
