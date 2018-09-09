package program;

import java.io.*;
import java.util.Scanner;

public class Start {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		DVDSet dvd=new DVDSet();
		DVDMgr.initial(dvd);
		dvd=getInfo();
		startMenu(dvd);
	}

	/*
	 * 实现菜单切换
	 */
	private static void startMenu(DVDSet dvd) throws Exception {
		System.out.println("欢迎使用迷你DVD管理器");
		System.out.println("------------------------");
		System.out.println("0.借出排行榜\n" +"1.新增DVD\n" + "2.查看DVD\n" + "3.删除DVD\n"
				+ "4.借出DVD\n" + "5.归还DVD\n" + "6.退出");
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
			DVDMgr.rank(dvd);
			break;
		case 1:
			DVDMgr.add(dvd);
			break;
		case 2:
			DVDMgr.search(dvd);
			break;
		case 3:
			DVDMgr.delete(dvd);
			break;
		case 4:
			DVDMgr.borrow(dvd);
			break;
		case 5:
			DVDMgr.returnDvd(dvd);
			break;
		case 6:
			System.out.println("谢谢使用！");
			System.exit(0);
		}
		store(dvd);
		System.out.println("************************");
		System.out.println("输入0返回");
		int back = scan.nextInt();
		returnMain(back,dvd);
	}

	private static void returnMain(int back,DVDSet dvd) throws Exception{
		if (back == 0) {
			startMenu(dvd);
		}
	}
	
	/*
	 * 序列化，将操作后的数据存储在硬盘中
	 */
	private static void store(DVDSet dvd) throws Exception{
		FileOutputStream fos=new FileOutputStream("D:\\javase\\miniDVD.txt");
		ObjectOutputStream oow=new ObjectOutputStream(fos);
		oow.writeObject(dvd);
		oow.flush();
		oow.close();
	}
	
	/*
	 * 反序列化，初次运行之前，必须先序列化，存在已经序列化的文件，否则运行时会出现异常
	 */
	private static DVDSet getInfo()throws Exception{
		FileInputStream fis=new FileInputStream("D:\\javase\\miniDVD.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		DVDSet dvd =(DVDSet)ois.readObject();
		ois.close();
		return dvd;
	}
}
