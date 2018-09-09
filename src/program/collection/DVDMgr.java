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
	 * DVD初始化
	 */
	public static void initial() {
		DVDSet dvd1 = new DVDSet();
		dvd1.name = "罗马假日";
		dvd1.state = 0;
		dvd1.date = "2010-7-1";
		dvd1.num = 1;
		list.add(dvd1);

		DVDSet dvd2 = new DVDSet();
		dvd2.name = "风声鹤唳";
		dvd2.state = 1;
		dvd2.date = "";
		dvd2.num = 0;
		list.add(dvd2);

		DVDSet dvd3 = new DVDSet();
		dvd3.name = "浪漫满屋";
		dvd3.state = 1;
		dvd3.date = "";
		dvd3.num = 0;
		list.add(dvd3);
	}

	/*
	 * 查看DVD信息
	 */
	public static void search() {
		System.out.println("--->查看DVD\n");
		System.out.println("序号" + "\t" + "状态" + "\t" + "名称" + "\t\t" + "借出日期");
		int i = 0;
		Iterator<DVDSet> ite = list.iterator();
		while (ite.hasNext()) {
			DVDSet dvd = ite.next();
			String sta = null;
			if (dvd.state == 0) {
				sta = "已借出";
			} else {
				sta = "可借";
			}
			System.out.print((i + 1) + "\t" + sta + "\t" + "《" + dvd.name + "》"
					+ "\t\t" + dvd.date + "\n");

			i++;
		}
	}

	/*
	 * 新增DVD
	 */
	public static void add() {
		System.out.println("--->新增DVD\n");
		System.out.println("请输入DVD名称：");
		String name = scan.nextLine().trim();
		DVDSet dvd = new DVDSet(name);
		list.add(dvd);
		System.out.println("新增" + "《" + dvd.name + "》" + "成功！");
	}

	/*
	 * 删除DVD
	 */
	public static void delete() {
		System.out.println("--->删除DVD\n");
		System.out.println("请输入DVD名称：");
		String name = scan.nextLine().trim();
		Iterator<DVDSet> ite = list.iterator();
		while (ite.hasNext()) {
			DVDSet dvd = ite.next();
			if (name.equals(dvd.name)) {// name在前才行
				if (dvd.state == 0) {
					System.out.println("《" + dvd.name + "》为借出状态，不能删除!");
					break;
				} else if (dvd.state == 1) {
					list.remove(dvd);
					System.out.println("《" + name + "》删除成功!");
					break;

				}
				break;
			} else if (dvd.name == null) {
				System.out.println("《" + name + "》不存在，无法删除!");
				break;
			}
		}
	}

	/*
	 * 借出DVD
	 */
	public static void borrow() {
		System.out.println("--->借出DVD\n");
		System.out.println("请输入DVD名称：");
		String name = scan.nextLine().trim();
		System.out.println("请输入借出日期(年-月-日)：");
		String date = scan.nextLine().trim();
		Iterator<DVDSet> ite = list.iterator();
		while (ite.hasNext()) {
			DVDSet dvd = ite.next();
			if (name.equals(dvd.name)) {
				if (dvd.state == 1) {
					dvd.state = 0;
					dvd.date = date;
					dvd.num += 1;
					System.out.println("借出《" + dvd.name + "》成功！");
					break;

				} else if (dvd.state == 0) {
					System.out.println("《" + dvd.name + "》已借出，请选择其他可借DVD!");
					break;
				}
			} else if (dvd.name == null) {
				System.out.println("《" + name + "》不存在，无法借出!");
				break;
			}
		}
	}

	/*
	 * 归还DVD
	 */
	public static void returnDvd() {
		System.out.println("--->归还DVD\n");
		System.out.println("请输入DVD名称：");
		String name = scan.nextLine().trim();
		System.out.println("请输入归还日期(年-月-日)：");
		String date = scan.nextLine().trim();
		Iterator<DVDSet> ite = list.iterator();
		while (ite.hasNext()) {
			DVDSet dvd = ite.next();
			if (name.equals(dvd.name)) {
				if (dvd.state == 1) {
					System.out.println("《" + dvd.name + "》未借出!");
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
					System.out.println("归还《" + dvd.name + "》成功！");
					System.out.println("借出日期为：" + sdf.format(d1));
					System.out.println("归还日期为：" + sdf.format(d2));
					System.out.println("应付租金(元)" + rent);
					break;
				}
			} else if (dvd.name == null) {
				System.out.println("《" + name + "》不存在!");
				break;
			}
		}
	}

	/*
	 * 借出排行榜
	 */
	public static void rank() {
		System.out.println("--->借出排行榜\n");
		System.out.println("借出次数" + "\t" + "名称");
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
			System.out.print(dvd1[i].num + "\t" + "《" + dvd1[i].name + "》"
					+ "\n");
		}
	}
	
	/*
	 * 序列化，将操作后的数据存储在硬盘中
	 */
	public static void store(ArrayList<program.collection1.DVDSet> list) throws Exception{
		File file=new File("\\javase\\miniDVD_collection.txt");
		//当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件。
		file.createNewFile();//如果第一次运行时没有序列化文件，则会创建一个序列化文件用于存储
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oow=new ObjectOutputStream(fos);
		oow.writeObject(list);
		oow.flush();
		oow.close();
	}
	
	/*
	 * 反序列化，初次运行之前，必须先序列化，存在已经序列化的文件，否则运行时会出现异常
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
