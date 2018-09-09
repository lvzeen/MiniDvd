package program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DVDMgr {
	static Scanner scan = new Scanner(System.in);

	/*
	 * DVD初始化
	 */
	public static void initial(DVDSet dvd) {
		dvd.name[0] = "罗马假日";
		dvd.state[0] = 0;
		dvd.date[0] = "2010-7-1";
		dvd.num[0] = 1;

		dvd.name[1] = "风声鹤唳";
		dvd.state[1] = 1;
		dvd.date[1] = "";
		dvd.num[1] = 0;

		dvd.name[2] = "浪漫满屋";
		dvd.state[2] = 1;
		dvd.date[2] = "";
		dvd.num[2] = 0;
	}

	/*
	 * 查看DVD信息
	 */
	public static void search(DVDSet dvd) {
		System.out.println("--->查看DVD\n");
		System.out.println("序号" + "\t" + "状态" + "\t" + "名称" + "\t\t" + "借出日期");
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] == null) {// 判断数组中字符串是否为空一定要用==进行判断，isEmpty()方法和equals()方法都不行
				break;
			} else {
				String sta = null;
				if (dvd.state[i] == 0) {
					sta = "已借出";
				} else {
					sta = "可借";
				}
				System.out.print((i + 1) + "\t" + sta + "\t" + "《"
						+ dvd.name[i] + "》" + "\t\t" + dvd.date[i] + "\n");
			}
		}
	}

	/*
	 * 新增DVD
	 */
	public static void add(DVDSet dvd) {
		System.out.println("--->新增DVD\n");
		System.out.println("请输入DVD名称：");
		String name = scan.nextLine().trim();
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] == null) {
				dvd.name[i] = name;
				dvd.state[i] = 1;
				dvd.date[i] = "";
				System.out.println("新增" + "《" + dvd.name[i] + "》" + "成功！");
				break;
			}
		}
	}

	/*
	 * 删除DVD
	 */
	public static void delete(DVDSet dvd) {
		System.out.println("--->删除DVD\n");
		System.out.println("请输入DVD名称：");
		String name = scan.nextLine().trim();
		for (int i = 0; i < dvd.name.length; i++) {
			if (name.equals(dvd.name[i])) {// name在前才行
				if (dvd.state[i] == 0) {
					System.out.println("《" + dvd.name[i] + "》为借出状态，不能删除!");
					break;
				} else if (dvd.state[i] == 1) {
					for (int x = i; x < dvd.name.length; x++) {
						dvd.name[x] = dvd.name[x + 1];
						dvd.state[x] = dvd.state[x + 1];
						dvd.date[x] = dvd.date[x + 1];
						dvd.num[x] = dvd.num[x + 1];

						if (dvd.name[x + 1] == null) {
							dvd.name[x] = null;
							dvd.state[x] = 0;
							dvd.date[x] = null;
							dvd.num[x] = 0;
							System.out.println("《" + name + "》删除成功!");
							break;
						}
					}
					break;
				}
			} else if (dvd.name[i] == null) {
				System.out.println("《" + name + "》不存在，无法删除!");
				break;
			}
		}
	}

	/*
	 * 借出DVD
	 */
	public static void borrow(DVDSet dvd) {
		System.out.println("--->借出DVD\n");
		System.out.println("请输入DVD名称：");
		String name = scan.nextLine().trim();
		System.out.println("请输入借出日期(年-月-日)：");
		String date = scan.nextLine().trim();
		for (int i = 0; i < dvd.name.length; i++) {
			if (name.equals(dvd.name[i])) {
				if (dvd.state[i] == 1) {
					dvd.state[i] = 0;
					dvd.date[i] = date;
					dvd.num[i] += 1;
					System.out.println("借出《" + dvd.name[i] + "》成功！");
					break;

				} else if (dvd.state[i] == 0) {
					System.out.println("《" + dvd.name[i] + "》已借出!");
					break;
				}
			} else if (dvd.name[i] == null) {
				System.out.println("《" + name + "》不存在，无法借出!");
				break;
			}
		}
	}

	/*
	 * 归还DVD
	 */
	public static void returnDvd(DVDSet dvd) {
		System.out.println("--->归还DVD\n");
		System.out.println("请输入DVD名称：");
		String name = scan.nextLine().trim();
		System.out.println("请输入归还日期(年-月-日)：");
		String date = scan.nextLine().trim();
		for (int i = 0; i < dvd.name.length; i++) {
			if (name.equals(dvd.name[i])) {
				if (dvd.state[i] == 1) {
					System.out.println("《" + dvd.name[i] + "》未借出!");
					break;
				} else if (dvd.state[i] == 0) {
					dvd.state[i] = 1;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date d1 = null;
					try {
						d1 = sdf.parse(dvd.date[i]);
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
					dvd.date[i] = "";
					System.out.println("归还《" + dvd.name[i] + "》成功！");
					System.out.println("借出日期为：" + sdf.format(d1));
					System.out.println("归还日期为：" + sdf.format(d2));
					System.out.println("应付租金(元)" + rent);
					break;
				}
			} else if (dvd.name[i] == null) {
				System.out.println("《" + name + "》不存在!");
				break;
			}
		}
	}

	/*
	 * 借出排行榜
	 */
	public static void rank(DVDSet dvd) {
		System.out.println("--->借出排行榜\n");

		int len = 0;
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] == null) {
				len = i-1;
				break;
			}
		}
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - i; j++) {
				if (dvd.num[j] > dvd.num[j + 1]) {
					int temp = dvd.num[j];
					dvd.num[j] = dvd.num[j + 1];
					dvd.num[j + 1] = temp;

					int temp1 = dvd.state[j];
					dvd.state[j] = dvd.state[j + 1];
					dvd.state[j + 1] = temp1;

					String x = dvd.name[j];
					dvd.name[j] = dvd.name[j + 1];
					dvd.name[j + 1] = x;

					String x1 = dvd.date[j];
					dvd.date[j] = dvd.date[j + 1];
					dvd.date[j + 1] = x1;
				}
			}
		}
		System.out.println("借出次数" + "\t" + "名称");
		for (int i = (dvd.name.length - 1); i >= 0; i--) {
		if (dvd.name[i] != null) {
				System.out.print(dvd.num[i] + "\t" + "《" + dvd.name[i] + "》"
						+ "\n");
			}
		}
	}
	
	/*
	 * 借出排行榜，方法2
	 */
	public static void rank1(DVDSet dvd) {
		System.out.println("--->借出排行榜\n");
		int[] h = new int[50];
		String[] k = new String[50];
		for (int i = 0; i < 50; i++) {
			h[i] = dvd.num[i];
			k[i] = dvd.name[i];
		}
		for (int i = 0; i < k.length - 1 && k[i] != null; i++) {
			if (k[i] != null) {
				for (int j = 0; j < k.length - (i + 1); j++) {
					if (h[j] > h[j + 1]) {
						int temp = h[j];
						h[j] = h[j + 1];
						h[j + 1] = temp;

						String x = k[j];
						k[j] = k[j + 1];
						k[j + 1] = x;
					}
				}
			}
		}
		System.out.println("借出次数" + "\t" + "名称");
		for (int i = (k.length - 1); i >= 0; i--) {
			if (k[i] != null) {
				System.out.print(h[i] + "\t" + "《" + k[i] + "》" + "\n");
			}
		}
	}
	
	/*
	 * 借出排行榜,错误示范,方法1为在此基础上的改进。
	 * 如果用此方法进行排序，则2.查看——0.排行榜——2.查看的时候，第一次查看没问题，第二次查看时数据会缺失
	 */
	public static void rank2(DVDSet dvd) {
		System.out.println("--->借出排行榜\n");
		for (int i = 0; i < dvd.name.length-1; i++) {
			for (int j = 0; j < dvd.name.length-1 - i; j++) {
				if(dvd.name[i]!=null){
				if (dvd.num[j] > dvd.num[j + 1]) {
					int temp = dvd.num[j];
					dvd.num[j] = dvd.num[j + 1];
					dvd.num[j + 1] = temp;

					int temp1 = dvd.state[j];
					dvd.state[j] = dvd.state[j + 1];
					dvd.state[j + 1] = temp1;

					String x = dvd.name[j];
					dvd.name[j] = dvd.name[j + 1];
					dvd.name[j + 1] = x;

					String x1 = dvd.date[j];
					dvd.date[j] = dvd.date[j + 1];
					dvd.date[j + 1] = x1;
				}
				}
			}
		}
		System.out.println("借出次数" + "\t" + "名称");
		for (int i = (dvd.name.length - 1); i >= 0; i--) {
		if (dvd.name[i] != null) {
				System.out.print(dvd.num[i] + "\t" + "《" + dvd.name[i] + "》"
						+ "\n");
			}
		}
	}
}
