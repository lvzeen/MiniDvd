package program.collection;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Start implements Serializable {
	private static final long serialVersionUID = 1L;
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		DVDMgr.initial();
		ArrayList<program.collection1.DVDSet> list = DVDMgr.list;
		
		File file = new File("\\javase\\miniDVD_collection.txt");
		if (file.exists()) {//如果序列化文件不存在，不进行反序列化操作
			list = DVDMgr.getInfo();
		}
		startMenu(list);
	}

	/*
	 * 实现菜单切换
	 */
	private static void startMenu(ArrayList<program.collection1.DVDSet> list)
			throws Exception {
		System.out.println("欢迎使用迷你DVD管理器");
		System.out.println("------------------------");
		System.out.println("0.借出排行榜\n" + "1.新增DVD\n" + "2.查看DVD\n"
				+ "3.删除DVD\n" + "4.借出DVD\n" + "5.归还DVD\n" + "6.退出");
		System.out.println("------------------------");
		System.out.println("请选择：");
		int menu = 0;
		try {
			menu = scan.nextInt();
		} catch (Exception e) {
			System.out.println("请输入数字：");
			menu = scan.nextInt();
		}
		switch (menu) {
		case 0:
			DVDMgr.rank();
			break;
		case 1:
			DVDMgr.add();
			break;
		case 2:
			DVDMgr.search();
			break;
		case 3:
			DVDMgr.delete();
			break;
		case 4:
			DVDMgr.borrow();
			break;
		case 5:
			DVDMgr.returnDvd();
			break;
		case 6:
			System.out.println("谢谢使用！");
			System.exit(0);
		}
		DVDMgr.store(list);
		System.out.println("************************");
		System.out.println("输入0返回");
		int back = scan.nextInt();
		returnMain(back, list);
	}

	private static void returnMain(int back,
			ArrayList<program.collection1.DVDSet> list) throws Exception {
		if (back == 0) {
			startMenu(list);
		}
	}

}
