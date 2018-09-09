package program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DVDMgr {
	static Scanner scan = new Scanner(System.in);

	/*
	 * DVD��ʼ��
	 */
	public static void initial(DVDSet dvd) {
		dvd.name[0] = "�������";
		dvd.state[0] = 0;
		dvd.date[0] = "2010-7-1";
		dvd.num[0] = 1;

		dvd.name[1] = "�������";
		dvd.state[1] = 1;
		dvd.date[1] = "";
		dvd.num[1] = 0;

		dvd.name[2] = "��������";
		dvd.state[2] = 1;
		dvd.date[2] = "";
		dvd.num[2] = 0;
	}

	/*
	 * �鿴DVD��Ϣ
	 */
	public static void search(DVDSet dvd) {
		System.out.println("--->�鿴DVD\n");
		System.out.println("���" + "\t" + "״̬" + "\t" + "����" + "\t\t" + "�������");
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] == null) {// �ж��������ַ����Ƿ�Ϊ��һ��Ҫ��==�����жϣ�isEmpty()������equals()����������
				break;
			} else {
				String sta = null;
				if (dvd.state[i] == 0) {
					sta = "�ѽ��";
				} else {
					sta = "�ɽ�";
				}
				System.out.print((i + 1) + "\t" + sta + "\t" + "��"
						+ dvd.name[i] + "��" + "\t\t" + dvd.date[i] + "\n");
			}
		}
	}

	/*
	 * ����DVD
	 */
	public static void add(DVDSet dvd) {
		System.out.println("--->����DVD\n");
		System.out.println("������DVD���ƣ�");
		String name = scan.nextLine().trim();
		for (int i = 0; i < dvd.name.length; i++) {
			if (dvd.name[i] == null) {
				dvd.name[i] = name;
				dvd.state[i] = 1;
				dvd.date[i] = "";
				System.out.println("����" + "��" + dvd.name[i] + "��" + "�ɹ���");
				break;
			}
		}
	}

	/*
	 * ɾ��DVD
	 */
	public static void delete(DVDSet dvd) {
		System.out.println("--->ɾ��DVD\n");
		System.out.println("������DVD���ƣ�");
		String name = scan.nextLine().trim();
		for (int i = 0; i < dvd.name.length; i++) {
			if (name.equals(dvd.name[i])) {// name��ǰ����
				if (dvd.state[i] == 0) {
					System.out.println("��" + dvd.name[i] + "��Ϊ���״̬������ɾ��!");
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
							System.out.println("��" + name + "��ɾ���ɹ�!");
							break;
						}
					}
					break;
				}
			} else if (dvd.name[i] == null) {
				System.out.println("��" + name + "�������ڣ��޷�ɾ��!");
				break;
			}
		}
	}

	/*
	 * ���DVD
	 */
	public static void borrow(DVDSet dvd) {
		System.out.println("--->���DVD\n");
		System.out.println("������DVD���ƣ�");
		String name = scan.nextLine().trim();
		System.out.println("������������(��-��-��)��");
		String date = scan.nextLine().trim();
		for (int i = 0; i < dvd.name.length; i++) {
			if (name.equals(dvd.name[i])) {
				if (dvd.state[i] == 1) {
					dvd.state[i] = 0;
					dvd.date[i] = date;
					dvd.num[i] += 1;
					System.out.println("�����" + dvd.name[i] + "���ɹ���");
					break;

				} else if (dvd.state[i] == 0) {
					System.out.println("��" + dvd.name[i] + "���ѽ��!");
					break;
				}
			} else if (dvd.name[i] == null) {
				System.out.println("��" + name + "�������ڣ��޷����!");
				break;
			}
		}
	}

	/*
	 * �黹DVD
	 */
	public static void returnDvd(DVDSet dvd) {
		System.out.println("--->�黹DVD\n");
		System.out.println("������DVD���ƣ�");
		String name = scan.nextLine().trim();
		System.out.println("������黹����(��-��-��)��");
		String date = scan.nextLine().trim();
		for (int i = 0; i < dvd.name.length; i++) {
			if (name.equals(dvd.name[i])) {
				if (dvd.state[i] == 1) {
					System.out.println("��" + dvd.name[i] + "��δ���!");
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
					System.out.println("�黹��" + dvd.name[i] + "���ɹ���");
					System.out.println("�������Ϊ��" + sdf.format(d1));
					System.out.println("�黹����Ϊ��" + sdf.format(d2));
					System.out.println("Ӧ�����(Ԫ)" + rent);
					break;
				}
			} else if (dvd.name[i] == null) {
				System.out.println("��" + name + "��������!");
				break;
			}
		}
	}

	/*
	 * ������а�
	 */
	public static void rank(DVDSet dvd) {
		System.out.println("--->������а�\n");

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
		System.out.println("�������" + "\t" + "����");
		for (int i = (dvd.name.length - 1); i >= 0; i--) {
		if (dvd.name[i] != null) {
				System.out.print(dvd.num[i] + "\t" + "��" + dvd.name[i] + "��"
						+ "\n");
			}
		}
	}
	
	/*
	 * ������а񣬷���2
	 */
	public static void rank1(DVDSet dvd) {
		System.out.println("--->������а�\n");
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
		System.out.println("�������" + "\t" + "����");
		for (int i = (k.length - 1); i >= 0; i--) {
			if (k[i] != null) {
				System.out.print(h[i] + "\t" + "��" + k[i] + "��" + "\n");
			}
		}
	}
	
	/*
	 * ������а�,����ʾ��,����1Ϊ�ڴ˻����ϵĸĽ���
	 * ����ô˷�������������2.�鿴����0.���а񡪡�2.�鿴��ʱ�򣬵�һ�β鿴û���⣬�ڶ��β鿴ʱ���ݻ�ȱʧ
	 */
	public static void rank2(DVDSet dvd) {
		System.out.println("--->������а�\n");
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
		System.out.println("�������" + "\t" + "����");
		for (int i = (dvd.name.length - 1); i >= 0; i--) {
		if (dvd.name[i] != null) {
				System.out.print(dvd.num[i] + "\t" + "��" + dvd.name[i] + "��"
						+ "\n");
			}
		}
	}
}
