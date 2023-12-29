package _mall;

import java.nio.file.Path;
import java.nio.file.Paths;

import controller.MallController;

public class _testMain {

	public static void main(String[] args) {
//
//		
//		MallController con = MallController.getInstance();
//		con.init();
//		
//		//con.mapCont.get("MallMain");
//	
//		con.setLoginId("test1");
//		MenuCommand commd = con.mapCont.get("MemberCart");
//		commd.init();
//		commd.update();
		
		
		String CUR_PATH = System.getProperty("user.dir");
	
		System.out.println(CUR_PATH);
		
		Path path = Paths.get("").toAbsolutePath();
		System.out.println(path.toString());

	}

}
