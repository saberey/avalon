package test;

import java.util.List;

/**
 *@description:TODO
 *@author saber
 *@date 2017年10月25日 下午2:41:47
 *@version
 */
public class WorkShop {

	private PeopleService peopleService = new PeopleService();
	
	public void work(){
		BaseEntity base = peopleService.getPeople();
		PupilEntity pupilE = (PupilEntity) base;
		List<PupilEntity> list = pupilE.getListObjet();
		for(int i=0;i<list.size();i++){
			list.get(i).setFlag(1);
		}
		
		for(int j=0;j<list.size();j++){
			System.out.println(list.get(j));
		}
	}
	
	public static void main(String[] args) {
		//WorkShop ws = new WorkShop();
		//ws.work();
		String[] strArray = {"1","2","3","4","5","6"};
		String[] destArray = new String[3];
		String[] destArray2 = new String[3];
		System.arraycopy(strArray, 0, destArray, 0, 3);
		System.arraycopy(strArray, 3, destArray2, 0, 3);
		System.out.println(strArray[0]);
		System.out.println(strArray[5]);
		System.out.println(destArray[2]);
		//System.out.println(destArray[3]);
		System.out.println(destArray2[2]);
	}
}
