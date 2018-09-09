package program.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import program.collection1.DVDSet;

public class DVDMgr {
	static Scanner scan = new Scanner(System.in);
	static ArrayList<DVDSet> list = new ArrayList<DVDSet>();
	/*
	 * DVD��ʼ��
	 */
	public static void initial() {
		DVDSet dvd1 = new DVDSet();
		dvd1.name = "�������";
		dvd1.state = 0;
		dvd1.date = "2010-7-1";
		dvd1.num = 1;
		list.add(dvd1);

		DVDSet dvd2 = new DVDSet();
		dvd2.name = "�������";
		dvd2.state = 1;
		dvd2.date = "";
		dvd2.num = 0;
		list.add(dvd2);

		DVDSet dvd3 = new DVDSet();
		dvd3.name = "��������";
		dvd3.state = 1;
		dvd3.date = "";
		dvd3.num = 0;
		list.add(dvd3);
	}

	/*
	 * �鿴DVD��Ϣ
	 */
	public static void search() {
		System.out.println("--->�鿴DVD\n");
		System.out.println("���" + "\t" + "״̬" + "\t" + "����" + "\t\t" + "�������");
		int i = 0;
		Iterator<DVDSet> ite = list.iterator();
		while (ite.hasNext()) {
			DVDSet dvd = ite.next();
			String sta = null;
			if (dvd.state == 0) {
				sta = "�ѽ��";
			} else {
				sta = "�ɽ�";
			}
			System.out.print((i + 1) + "\t" + sta + "\t" + "��" + dvd.name + "��"
					+ "\t\t" + dvd.date + "\n");

			i++;
		}
	}

	/*
	 * ����DVD
	 */
	public static void add() {
		System.out.println("--->����DVD\n");
		System.out.println("������DVD���ƣ�");
		String name = scan.nextLine().trim();
		DVDSet dvd = new DVDSet(name);
		list.add(dvd);
		System.out.println("����" + "��" + dvd.name + "��" + "�ɹ���");
	}

	/*
	 * ɾ��DVD
	 */
	public static void delete() {
		System.out.println("--->ɾ��DVD\n");
		System.out.println("������DVD���ƣ�");
		String name = scan.nextLine().trim();
		Iterator<DVDSet> ite = list.iterator();
		while (ite.hasNext()) {
			DVDSet dvd = ite.next();
			if (name.equals(dvd.name)) {// name��ǰ����
				if (dvd.state == 0) {
					System.out.println("��" + dvd.name + "��Ϊ���״̬������ɾ��!");
					break;
				} else if (dvd.state == 1) {
					list.remove(dvd);
					System.out.println("��" + name + "��ɾ���ɹ�!");
					break;

				}
				break;
			} else if (dvd.name == null) {
				System.out.println("��" + name + "�������ڣ��޷�ɾ��!");
				break;
			}
		}
	}

	/*
	 * ���DVD
	 */
	public static void borrow() {
		System.out.println("--->���DVD\n");
		System.out.println("������DVD���ƣ�");
		String name = scan.nextLine().trim();
		System.out.println("������������(��-��-��)��");
		String date = scan.nextLine().trim();
		Iterator<DVDSet> ite = list.iterator();
		while (ite.hasNext()) {
			DVDSet dvd = ite.next();
			if (name.equals(dvd.name)) {
				if (dvd.state == 1) {
					dvd.state = 0;
					dvd.date = date;
					dvd.num += 1;
					System.out.println("�����" + dvd.name + "���ɹ���");
					break;

				} else if (dvd.state == 0) {
					System.out.println("��" + dvd.name + "���ѽ������ѡ�������ɽ�DVD!");
					break;
				}
			} else if (dvd.name == null) {
				System.out.println("��" + name + "�������ڣ��޷����!");
				break;
			}
		}
	}

	/*
	 * �黹DVD
	 */
	public static void returnDvd() {
		System.out.println("--->�黹DVD\n");
		System.out.println("������DVD���ƣ�");
		String name = scan.nextLine().trim();
		System.out.println("������黹����(��-��-��)��");
		String date = scan.nextLine().trim();
		Iterator<DVDSet> ite = list.iterator();
		while (ite.hasNext()) {
			DVDSet dvd = ite.next();
			if (name.equals(dvd.name)) {
				if (dvd.state == 1) {
					System.out.println("��" + dvd.name + "��δ���!");
					break;
				} else if (dvd.state == 0) {
					dvd.state = 1;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date d1 = null;
					try {
						d1 = sdf.parse(dvd.date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Date d2 = null;
					try {
						d2 = sdf.parse(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					int day = (int) ((d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000));
					int rent = day * 2;
					dvd.date = "";
					System.out.println("�黹��" + dvd.name + "���ɹ���");
					System.out.println("�������Ϊ��" + sdf.format(d1));
					System.out.println("�黹����Ϊ��" + sdf.format(d2));
					System.out.println("Ӧ�����(Ԫ)" + rent);
					break;
				}
			} else if (dvd.name == null) {
				System.out.println("��" + name + "��������!");
				break;
			}
		}
	}

	/*
	 * ������а�
	 */
	public static void rank() {
		System.out.println("--->������а�\n");
		System.out.println("�������" + "\t" + "����");
		ArrayList<DVDSet> list1 = list;
		Object[] obj = list1.toArray();
		DVDSet[] dvd1 = new DVDSet[obj.length];
		
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] instanceof DVDSet) {
				dvd1[i] = (DVDSet) obj[i];
			}
		}
		
		for (int i = 1; i <= dvd1.length - 1; i++) {
			for (int j = 0; j < dvd1.length - i; j++) {
				if (dvd1[j].num < dvd1[j + 1].num) {
					DVDSet temp = dvd1[j];
					dvd1[j] = dvd1[j + 1];
					dvd1[j + 1] = temp;
				}
			}
		}
		
		for (int i = 0; i < dvd1.length; i++) {
			System.out.print(dvd1[i].num + "\t" + "��" + dvd1[i].name + "��"
					+ "\n");
		}
	}
	
	/*
	 * ���л���������������ݴ洢��Ӳ����
	 */
	public static void store(ArrayList<program.collection1.DVDSet> list) throws Exception{
		File file=new File("\\javase\\miniDVD_collection.txt");
		//���ҽ��������ھ��д˳���·����ָ�����Ƶ��ļ�ʱ�����ɷֵش���һ���µĿ��ļ���
		file.createNewFile();//�����һ������ʱû�����л��ļ�����ᴴ��һ�����л��ļ����ڴ洢
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oow=new ObjectOutputStream(fos);
		oow.writeObject(list);
		oow.flush();
		oow.close();
	}
	
	/*
	 * �����л�����������֮ǰ�����������л��������Ѿ����л����ļ�����������ʱ������쳣
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<DVDSet> getInfo()throws Exception{
		FileInputStream fis=new FileInputStream("\\javase\\miniDVD_collection.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		list =(ArrayList<DVDSet>) ois.readObject();
		ois.close();
		return list;
	}

}
