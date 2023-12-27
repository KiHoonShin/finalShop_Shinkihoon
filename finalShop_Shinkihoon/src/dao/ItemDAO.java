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

import dao.FileDAO.FileName;
import dto.Board;
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
	public void print_category() {
		int idx = 1;
		for(Item i : itemList) {
			System.out.printf("[%d] %s %n",idx++ , i.getCategoryName());
		}
		System.out.println("[0] 뒤로가기");
		//int sel = Util.getValue("메뉴", 0, 5)-1;
		//System.out.println("[ "+itemList.get(sel).getCategoryName()+"의 아이템 목록 ]");
	}
	
	// ~의 아이템 목록 
	public void shoppingItem(int idx) {
		System.out.println("[ "+itemList.get(idx).getCategoryName()+"의 아이템 목록 ]");
		print_item(idx);
	}
	
	// 아이템 목록 출력
	void print_item(int idx) {
		int cnt = 1;
		for(Item ii : itemList) {
			if(ii.getCategoryName().equals(itemList.get(idx).getCategoryName())) {
				System.out.println("["+(cnt++)+"]"+ii.getItemName()+ "  " + ii.getPrice());
			}
		}
	}
	
	
	
}
