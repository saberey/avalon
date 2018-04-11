package test;

import java.util.ArrayList;
import java.util.List;

/**
 *@description:TODO
 *@author saber
 *@date 2017年10月25日 下午2:36:45
 *@version
 */
public class PeopleService {

	public BaseEntity getPeople(){
		int size = 2;
		PeopleField pe = new PeopleField();
		pe.setAge(2);
		List<PeopleField> lp = new ArrayList();
		lp.add(pe);
		pe = new PeopleField();
		pe.setAge(4);
		lp.add(pe);
		
		PeopleEntity pet = new PeopleEntity();
		pet.setListObjet(lp);
		return pet;
	}
}
