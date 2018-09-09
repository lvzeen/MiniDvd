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
		if (file.exists()) {//������л��ļ������ڣ������з����л�����
			list = DVDMgr.getInfo();
		}
		startMenu(list);
	}

	/*
	 * ʵ�ֲ˵��л�
	 */
	private static void startMenu(ArrayList<program.collection1.DVDSet> list)
			throws Exception {
		System.out.println("��ӭʹ������DVD������");
		System.out.println("------------------------");
		System.out.println("0.������а�\n" + "1.����DVD\n" + "2.�鿴DVD\n"
				+ "3.ɾ��DVD\n" + "4.���DVD\n" + "5.�黹DVD\n" + "6.�˳�");
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
		DVDMgr.store(list);
		System.out.println("************************");
		System.out.println("����0����");
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
