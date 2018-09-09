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
	 * ʵ�ֲ˵��л�
	 */
	private static void startMenu(DVDSet dvd) throws Exception {
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
			System.out.println("ллʹ�ã�");
			System.exit(0);
		}
		store(dvd);
		System.out.println("************************");
		System.out.println("����0����");
		int back = scan.nextInt();
		returnMain(back,dvd);
	}

	private static void returnMain(int back,DVDSet dvd) throws Exception{
		if (back == 0) {
			startMenu(dvd);
		}
	}
	
	/*
	 * ���л���������������ݴ洢��Ӳ����
	 */
	private static void store(DVDSet dvd) throws Exception{
		FileOutputStream fos=new FileOutputStream("D:\\javase\\miniDVD.txt");
		ObjectOutputStream oow=new ObjectOutputStream(fos);
		oow.writeObject(dvd);
		oow.flush();
		oow.close();
	}
	
	/*
	 * �����л�����������֮ǰ�����������л��������Ѿ����л����ļ�����������ʱ������쳣
	 */
	private static DVDSet getInfo()throws Exception{
		FileInputStream fis=new FileInputStream("D:\\javase\\miniDVD.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		DVDSet dvd =(DVDSet)ois.readObject();
		ois.close();
		return dvd;
	}
}
