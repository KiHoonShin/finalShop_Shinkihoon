package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import controller.MallController;
import dao.FileDAO.FileName;
import dto.Board;
import dto.Cart;
import dto.Item;
import util.Util;

public class ItemDAO {
	ArrayList<Item> itemList = new ArrayList<>();
	String fileName = FileName.ITEM.getName();
	int maxNo;
	int cnt;
	
	private ItemDAO() {}
	
	private static ItemDAO instance = new ItemDAO();
	
	public static ItemDAO getInstance() {
		return instance;
	}
	
	// item.txt 파일로부터 data 받은 후 itemList 생성
	public void roadToItemFile() {
		Item ii = new Item();
		int num = ii.getNum();
		FileDAO fileDAO = FileDAO.getInstance();
		String item = fileDAO.roadFile(fileName);
		if(item.equals("")) {
			System.out.println("아이템 데이터 없음");
			return;
		}
		String[] temp = item.split("\n");
		for(int i = 0; i < temp.length; i ++) {
			String[] info = temp[i].split("/");
			itemList.add(new Item(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3])));
			maxNo = Integer.parseInt(info[0]);
			ii.setNum(++num);
			cnt +=1;
		}
	}
	
	// 카테고리 출력
	public boolean print_category(CartDAO cartDAO , String log) {
		int index = 1;
		ArrayList<String> name = new ArrayList<>();
		name.add(itemList.get(0).getCategoryName());
		for(int i = 1; i < itemList.size(); i++) {
			int cnt = 0;
			for(int k = 0; k < name.size();k++) {
				if(itemList.get(i).getCategoryName().equals(name.get(k))) {
					cnt +=1;
				}
			}
			if(cnt == 0) {
				name.add(itemList.get(i).getCategoryName());
			}
		}
		
		for(String n : name) {
			System.out.printf("[%d] %s %n", index++, n);
		}
		System.out.println("[0] 뒤로가기");
		int sel = Util.getValue("메뉴", 0, cnt);
		if(sel == 0) {
			return false;
		}
		String nn = name.get(sel-1);
		//Item ii = itemList.get(idx);
		shoppingItem(nn , cartDAO , log);
//		System.out.println("[ "+nn+"의 아이템 목록 ]");
//		//print_item(idx);
//		int cnt = 1;
//		for(Item ii : itemList) {
//			if(ii.getCategoryName().equals(nn)) {
//				System.out.println("["+(cnt++)+"]"+ii.getItemName()+ "  " + ii.getPrice());
//			}
//		}
		return true;
		//System.out.println("[ "+itemList.get(sel).getCategoryName()+"의 아이템 목록 ]");
	}
	
	// ~의 아이템 목록 
	public void shoppingItem(String nn , CartDAO cartDAO , String id) {
		System.out.println("[ "+nn+"의 아이템 목록 ]");
		int cnt = 1;
		for(Item ii : itemList) {
			if(ii.getCategoryName().equals(nn)) {
				System.out.println("["+(cnt++)+"]"+ii.getItemName()+ "  " + ii.getPrice());
			}
		}
		while(true) {
		String input = Util.getValue("구매 아이템 이름");
		int validCount = 0;
		for(Item ii : itemList) {
			if(ii.getCategoryName().equals(nn)) {  
				if(ii.getItemName().contains(input)) {
					validCount += 1;
				} 
			}
		}
		if(validCount == 0) {
			System.out.println("아이템 이름 오류 - 다시 입력 해주세요");
			continue;
		} else {
			int count = Util.getValue("아이템 구매 수량", 1, 100);
			System.out.println("[ "+input +" "+ count +"개 구매 완료 ]");
			
			int price = 0;
			int no = 0;
			for(Item i : itemList) {
				if(i.getItemName().equals(input)) {
					price = i.getPrice();
					no = i.getItemNum();
					break;
				}
			}
			// 아이템 개수 추가
			this.cnt +=1;
			System.out.println("nn : " + nn + "  input : " + input + " price : " + price);
			itemList.add(new Item(cnt, nn, input, price));
			
			// -> cartdao에서 cart추가  메서드 만들기 !!!
			// cartDAO에 num++, 아이디, 아이템번호, 수량 추가하기. 
//			cartDAO.cartList.add(new Cart(++cartDAO.maxNo, id, maxNo, count));
//			cartDAO.cnt += 1;
			
//			for(Item i : itemList) {
//				System.out.println(i.getItemNum()+" "+i.getCategoryName() +" " + i.getItemName());
//			}
			cartDAO.add_cartList(id, no, count);
			break;
		}
		} //while
		
		
	}
	
//	// 아이템 목록 출력
	public void print_item() {
		System.out.println("======= 카테고리별 아이템 목록 =======");
		int itemCnt = 1;
		for(Item ii : itemList) {
			// ii.setItemNum(itemCnt++); -> 판매된 아이템 에서 추가
			maxNo = ii.getItemNum();
		}
		ArrayList<Item> tempItemList = (ArrayList<Item>) itemList.clone();
		Collections.sort(tempItemList);
		for(Item tt : tempItemList) {
			System.out.println(tt);
		}
	}
	
	// admin 아이템 추가
	public void add_item() {
		String item = Util.getValue("아이템");
		if(!isValidName(item)) {
			System.out.println("이미 있는 카테고리/아이템 이름 입니다");
			return;
		}
		String cateName = Util.getValue("카테고리");
		if(!isValidName(cateName)) {
			System.out.println("이미 있는 카테고리/아이템 이름 입니다");
			return;
		}
		int newPrice = Util.getValue("가격", 100, 1000000);
		itemList.add(new Item(++maxNo, cateName, item, newPrice));
		cnt +=1;
		System.out.println("아이템 추가 완료");
	}
	
	// 이미 있는 카테고리/아이템 체크
	private boolean isValidName(String name) {
		for(Item ii : itemList) {
			if(name.equals(ii.getCategoryName())) {
				return false;
			}
			if(name.equals(ii.getItemName())) {
				return false;
			}
		}
		return true;
	}
	
	// admin - 아이템 삭제
	public void del_item() {
		System.out.println("[ 아이템 삭제시 구매 내역이 사라집니다 ]");
		int sel = Util.getValue("삭제할 아이템 번호", 1, maxNo)-1;
		for(int i = 0; i < itemList.size(); i+=1) {
			if(i == sel) {
				itemList.remove(sel);
			}
		}
		System.out.println("[ 아이템 삭제 완료 ]");
	}
	
}
