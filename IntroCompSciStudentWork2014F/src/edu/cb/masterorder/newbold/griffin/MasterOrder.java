package edu.cb.masterorder.newbold.griffin;
import edu.jenks.dist.cb.masterorder.*;
import java.util.*;

public class MasterOrder extends AbstractMasterOrder{

	private List<CookieOrder> orders;
	public MasterOrder(){
		orders = new ArrayList<CookieOrder>();
		orders = getOrders();
	}
	public MasterOrder(ArrayList<CookieOrder> list){
		orders = list;
	}
	public int getTotalBoxes(){
		if(orders.size() == 0){
		   return 0;
		}
		int boxes = 0;
		for(int i = 0; i < orders.size(); i++){
			boxes += orders.get(i).getNumBoxes();
		}
		return boxes;
	}
	public Map<String, Integer> getTotalBoxesByVariety(){
		Map<String, Integer> boxHash = new HashMap<>();
		for(int i = 0; i < orders.size(); i++){
			if(boxHash.containsKey(orders.get(i).getVariety()) == false){
				boxHash.put(orders.get(i).getVariety(), totalBoxes(orders.get(i).getVariety()));
			}
		}
		return boxHash;
	}

	private int totalBoxes(String cookieVar){
		int numOfBoxes = 0;
		for(int i = 0; i < orders.size(); i++){
			if(orders.get(i).getVariety().equals(cookieVar)){
				numOfBoxes += orders.get(i).getNumBoxes();
			}
		}
		return numOfBoxes;
	}

	private void printList(){
		for(int i =0; i< orders.size(); i++){
			System.out.println(orders.get(i).getVariety());
		}
	}
	public int removeVariety(String cookieVar){
		int boxesRemoved = 0;
		for(int i = 0; i < orders.size(); i++){
			if(orders.get(i).getVariety().equals(cookieVar)){
				boxesRemoved += orders.get(i).getNumBoxes();
				orders.remove(i);
				i--;
			}
		}
		printList();
		return boxesRemoved;
	}
	public static void main(String[] args){
		ArrayList<CookieOrder> list = new ArrayList<>();
		CookieOrder cookie1 = new CookieOrder("Chocolate", 5);
		CookieOrder cookie2 = new CookieOrder("Brownie", 100);
		CookieOrder cookie3 = new CookieOrder("Chocolate", 7);
		CookieOrder cookie4 = new CookieOrder("SnickerDoodle", 9);
		CookieOrder cookie5 = new CookieOrder("Peanut", 2);
		CookieOrder cookie6 = new CookieOrder("Chocolate Chip", 5);
		CookieOrder cookie7 = new CookieOrder("Brownie", 100);
		CookieOrder cookie8 = new CookieOrder("Chocolate", 7);
		CookieOrder cookie9 = new CookieOrder("SnickerDoodle", 9);
		CookieOrder cookie10 = new CookieOrder("Peanut", 2);
		list.add(cookie1);
		list.add(cookie2);
		list.add(cookie3);
		list.add(cookie4);
		list.add(cookie5);
		list.add(cookie6);
		list.add(cookie7);
		list.add(cookie8);
		list.add(cookie9);
		list.add(cookie10);
		AbstractMasterOrder test = new MasterOrder(list);
		System.out.println(test.getTotalBoxes());
		System.out.println(test.getTotalBoxesByVariety());
		System.out.println(test.removeVariety("Chocolate"));
	}
}
