package program;

import java.io.Serializable;

public class DVDSet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public String[] name=new String[50];//DVD名称数组
	public int[] state=new int[50];//DVD借出状态：0已借出/1可借
	public String[] date=new String[50];//DVD借出日期
    public int[] num=new int[50];//借出次数，用于排行榜
	
}
