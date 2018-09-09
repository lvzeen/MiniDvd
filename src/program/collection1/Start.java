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
	 * ʵ�ֲ˵��л�
	 */
	private static void startMenu(ArrayList<DVDSet> list) throws Exception {
		System.out.println("��ӭʹ������DVD������");
		System.out.println("------------------------");
		System.out.println("0.������а�\n" +"1.����DVD\n" + "2.�鿴DVD\n" + "3.ɾ��DVD\n"
				+ "4.���DVD\n" + "5.�黹DVD\n" + "6.�˳�");
		System.out.println("------------------------");
		System.out.println("��ѡ��");
		int menu = 0;
		try {
			menu = scan.nextInt();
		} catch (Exception e) {
			System.out.println("���������֣�");
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
			System.out.println("ллʹ�ã�");
			System.exit(0);
		}
		store(list);
		System.out.println("************************");
		System.out.println("����0����");
		int back = scan.nextInt();
		returnMain(back,list);
	}

	private static void returnMain(int back,ArrayList<DVDSet> list) throws Exception{
		if (back == 0) {
			startMenu(list);
		}
	}
	
	/*
	 * ���л���������������ݴ洢��Ӳ����
	 */
	private static void store(ArrayList<DVDSet> list) throws Exception{
		FileOutputStream fos=new FileOutputStream("D:\\javase\\miniDVD_collection.txt");
		ObjectOutputStream oow=new ObjectOutputStream(fos);
		oow.writeObject(list);
		oow.flush();
		oow.close();
	}
	
	/*
	 * �����л�����������֮ǰ�����������л��������Ѿ����л����ļ�����������ʱ������쳣
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
