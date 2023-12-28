package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dao.FileDAO.FileName;
import dto.Cart;
import dto.Item;

public class CartDAO {
	
	ArrayList<Cart> cartList = new ArrayList<>();
	
	private CartDAO() {}
	
	private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	final String cartName = FileName.CART.getName();
	
	int maxNo;
	int cnt;
	
	// cart.txt 파일로부터 data 받은 후 cartList 생성
	public void roadToCartFile() {
		Cart cc = new Cart();
		int num = cc.getNum();
		FileDAO fileDAO = FileDAO.getInstance();
		String cart = fileDAO.roadFile(cartName);
		if(cart.equals("")) {
			System.out.println("카트 데이터 없음");
			return;
		}
		String[] temp = cart.split("\n");
		for(int i = 0; i < temp.length; i ++) {
			String[] info = temp[i].split("/");
			cartList.add(new Cart(Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3])));
			maxNo = Integer.parseInt(info[0]);
			cc.setNum(++num);
			cnt +=1;
		}
	}
	
	public void add_cartList(String id, int no, int count) {
		cartList.add(new Cart(cnt, id, no, count));
		cnt +=1;
		for(Cart c : cartList) {
			System.out.println(c);
		}
	}
	
	
	// 카트리스트 출력
	public void print_cartList(String log , ItemDAO itemDAO) {
		List<Integer> itemNumber = new ArrayList<>();
		System.out.println("=============================");
		int number = 1;
		for(Cart c : cartList) {
			 if(log.equals(c.getId())) {
				 for(Item ii : itemDAO.itemList) {
					 int itemCnt = 1;
					 if(ii.getItemNum() == c.getItemNum()) {
						 //if()
						 itemNumber.add(ii.getItemNum());
//						 System.out.println("["+(number++)+"]\t"+ ii.getItemName()+"(\t"+
//								 ii.getPrice()+"원)\t   "+c.getItemCnt()+"개 총" + ii.getPrice()*c.getItemCnt()+"원");
					 }
				 }
			 }
		}
		ArrayList<Integer> tempList = (ArrayList<Integer>) itemNumber.stream().distinct().collect(Collectors.toList());
		
//		for(Integer t : tempList) {
//			System.out.println(t);
//		}
		int total = 0; // 총 가격
		int totalCnt = 0; // 총 개수
		for(Integer t : tempList) {
			for(Item ii : itemDAO.itemList) {
				int items = 0;
			for (Cart c : cartList) {
				if (log.equals(c.getId())) {
					if (t == c.getItemNum()) {
						items += c.getItemCnt();
					}
				}
				 } // c
			if(ii.getItemNum() == t) {
				System.out.println("["+(number++)+"]\t"+ ii.getItemName()+"(\t"+
						ii.getPrice()+"원)\t   "+items+"개 총" + ii.getPrice()*items+"원");
				total += ii.getPrice()*items;
				totalCnt += items;
			}
			} // i
			
		} // t
		System.out.println("=============================");
		System.out.printf("총 %d개 (  %d  원) %n" , totalCnt , total);
		
		
 		//names.stream().distinct().forEach(System.out::println);
	}
	
	
	
}
