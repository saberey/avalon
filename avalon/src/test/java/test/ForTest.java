package test;
/**
 *@description:TODO
 *@author saber
 *@date 2017年12月15日 下午4:37:01
 *@version
 */
public class ForTest {

	public static void main(String[] args) {
		String[] needVerifyCardCollections = {"154415705","6228481359519701972","6228480888214451476","6217996242000656759","80020309000027861","6228411730231137510","6222600920004001225","100389465635","500000190018010058252","6217991430012477448","6222080412002196438","6217002120010180394","45050160478000000147","6217991730006038241","6228231265408016963","6217000150016601251","6212660101201699867","0609047709024811558","6228481738486617776","6228481266352204065","6228480619525193972","6236680150003819996","6217000150018509304","6217994920013659777","5450120100000570001","140000138012016006794","6228481739190820177","580101040013124","6217003810009783062","6228400017002969369","6236680150003132507","6228482121594778518","6217991430010793358","66666654123785251116","6666660123456789012","66666678945612374","6666660000000","66666666666","66666666666666666666","66666632154325614879","66666632154325614879","66666654123785251116","666666000000000000","666666666612345","66666612345678900","66666622222222222222","66666632154325614879","66666654856954587458","66666647895132","6666666666666666666666","66666612345678900","6666667777789898900","66666612345098743210","666666000000000","666666666612345","666666123456789123","666666000000000","666666256498745623","66666612345678900","6666661234509874321","66666601234567890","6666663213213232132131322","66666612345678901","6666660123456789012","666666666666","666666666666677777776"};
		for(String curCardNo : needVerifyCardCollections){
			if(curCardNo.startsWith("666666")){
				System.out.println(curCardNo+":"+1);
			}else{
				System.out.println(curCardNo);
			}
			//dlrcb
		}
	}
}
