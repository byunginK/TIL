package dao;

import java.util.List;

import dto.Batter;
import dto.Human;
import dto.Pitcher;
import file.FileProc;

public class BaseBallDao {


	private static BaseBallDao dao = new BaseBallDao();
	public List<Human> list;
	private int memberNumber;
	
	FileProc fp = new FileProc("baseball");
	
	private BaseBallDao() {
		
		list = fp.loadData();
		
		memberNumber = list.get(list.size() - 1).getNumber();
		
		if(memberNumber >= 2000) {
			memberNumber = memberNumber - 1000;			
		}
		memberNumber = memberNumber + 1;
	}
	
	public static BaseBallDao getInstance() {
		return dao;
	}
	
	public boolean insert(Human h) {
		if(h instanceof Pitcher) {
			h.setNumber(memberNumber);
		}
		else if (h instanceof Batter) {
			h.setNumber(memberNumber + 1000);
		}
		
		boolean b = list.add(h);
		memberNumber++;
		
		return b;
	}
	
	public boolean delete(String name) {
		Human human = null;
		for (int i = 0; i < list.size(); i++) {
			human = list.get(i);
			if(name.equals(human.getName())) {
				
			}
		}
		
		
		return true;
	}
	public void select(String name) {
		
	}
	public void updata(String name) {
		
	}
	public void allPrint() {
		
	}
	public void dataSave() {
		fp.saveData(list);
	}
}
