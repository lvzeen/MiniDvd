package program.collection1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Start implements Serializable{
	private static final long serialVersionUID = 1L;
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		DVDMgr.initial();
		ArrayList<DVDSet> list =DVDMgr.list;
		DVDMgr.list=getInfo();
		startMenu(list);
	}

	/*
	 * 实现菜单切换
	 */
	private static void startMenu(ArrayList<DVDSet> list) throws Exception {
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
		store(list);
		System.out.println("************************");
		System.out.println("输入0返回");
		int back = scan.nextInt();
		returnMain(back,list);
	}

	private static void returnMain(int back,ArrayList<DVDSet> list) throws Exception{
		if (back == 0) {
			startMenu(list);
		}
	}
	
	/*
	 * 序列化，将操作后的数据存储在硬盘中
	 */
	private static void store(ArrayList<DVDSet> list) throws Exception{
		FileOutputStream fos=new FileOutputStream("D:\\javase\\miniDVD_collection.txt");
		ObjectOutputStream oow=new ObjectOutputStream(fos);
		oow.writeObject(list);
		oow.flush();
		oow.close();
	}
	
	/*
	 * 反序列化，初次运行之前，必须先序列化，存在已经序列化的文件，否则运行时会出现异常
	 */
	@SuppressWarnings("unchecked")
	private static ArrayList<DVDSet> getInfo()throws Exception{
		FileInputStream fis=new FileInputStream("D:\\javase\\miniDVD_collection.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		ArrayList<DVDSet> list =(ArrayList<DVDSet>)ois.readObject();
		ArrayList<DVDSet> list1 =new ArrayList<DVDSet>();
		for(DVDSet x:list){
			list1.add(x);
		}
//		if(obj instanceof ArrayList){
//			list=(ArrayList<DVDSet>) obj;
//		}
		System.out.println(list);
		ois.close();
		return list1;
	}
}
