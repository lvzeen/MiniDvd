package program;

import java.io.Serializable;

public class DVDSet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public String[] name=new String[50];//DVD��������
	public int[] state=new int[50];//DVD���״̬��0�ѽ��/1�ɽ�
	public String[] date=new String[50];//DVD�������
    public int[] num=new int[50];//����������������а�
	
}
