package wang.relish.dispatching.other;
/**
 * @version 1.0
 * @Copyright: Copyright (c) 2008 IT Home Inc. All rights reserved.
 * @see java.lang.Class
 * @Class: DiskDispatchArithmetic 2008/05/28
 * @Summary: 先到先服务算法FCFS、最短寻道时间优先算法SSTF和扫描算法Scan的演示
 * @author kobe
 * @since JDK 1.5
 */

import java.io.*;

public class DiskDispatchArithmetic{
	int[] track = {23,62,31,50,70,85,92,12,99};//定义要访问的磁道

	int first = 45;//定义当前所在的磁道

	int count = 0 ;//用于统计移动磁道的总数

	int trackCount = 0;//当前磁道与要访问的磁头间的磁道数

	int len = track.length;//数组长度

	int c = 0;//访问次序

	BufferedReader streamin = new BufferedReader(
			new InputStreamReader(System.in));
	//声明并创建缓冲字符输入流BufferedReader对象

	public DiskDispatchArithmetic(){
		//显示该程序所要执行的主要信息
		System.out.println ();
		System.out.println ("***********************************");
		System.out.println ("请选择磁盘调度算法:");
		System.out.println ("1.先到先服务算法FCFS");
		System.out.println ("2.最短寻道时间优先算法SSTF");
		System.out.println ("3.扫描算法SSTF");
		System.out.println ("4.退出(exit)");
		System.out.println ("***********************************");
		System.out.print ("请输入你的选择(1-4):");

		int select;	//定义要选择的数字
		try{
			select = Integer.parseInt(streamin.readLine());//从命令行读取输入的字符并返回
			switch (select) {
				case 1: System.out.println ();
					System.out.println ("1.先到先服务算法FCFS");
					fcfs();//选择1，执行FCFS算法
					break;
				case 2: System.out.println ();
					System.out.println ("2.最短寻道时间优先算法SSTF");
					sstf();//选择2，执行SSTF算法
					break;
				case 3: System.out.println ();
					System.out.println ("3.扫描算法SSTF");
					scan();//选择3，执行Scan算法
					break;
				case 4: System.out.println ();
					System.out.println ("安全退出!");break;//退出
				default :System.out.println ();
					System.out.println ("错误提示:请输入规定中的数字(即1-4其中一个)!");
					new DiskDispatchArithmetic();//输入选择不是1，2，3 提示重新输入正确数字

			}

		}catch(IOException e){
			e.printStackTrace();
		}

	}



	/**先到先服务算法FCFS
	 * count 用于统计移动磁道的总数
	 * trackCount 当前磁道与要访问的磁头间的磁道数
	 */
	public void fcfs(){
		this.getInformation();
		for (int j = 0; j<len; j++){
			trackCount = Math.abs(first-track[j]);
			System.out.println ("  "+(j+1)+"		  "+track[j]+"	   		"+trackCount);
			count+= trackCount;
			first = track[j];
		}
		System.out.println ("磁头移动的磁道总数是:"+count);
		float average = (float)count/(track.length);
		String s = String.valueOf(average);
		System.out.println ("磁头移动的平均磁道数是:"+s.substring(0,4));
		toContinue();
	}

	/**最短寻道时间优先算法SSTF
	 * count 用于统计移动磁道的总数
	 * trackCount 当前磁道与要访问的磁头间的磁道数
	 */

	public void sstf(){
		toContinue();
	}

	/**扫描算法Scan
	 * count 用于统计移动磁道的总数
	 * trackCount 当前磁道与要访问的磁头间的磁道数
	 */
	public void scan(){

		System.out.println ("磁头当前位于第"+first+"磁道,假定对如下磁道进行由外向内访问:");
		int test = 0;
		int p = 0;//当前磁道要访问的下一个磁道的下标

		for (int i = 0; i<len; i++){
			System.out.print (track[i]+"  ");
		}
		System.out.println ();
		System.out.println ("请求次序:"+"	访问磁道:"+"	磁头移动的磁道数:");
		for (int i = 0; i<len-1; i++){

			//将数组按从小到大排序
			for (int n = i+1; n<len; n++){
				if(track[i]>track[n]){
					test = track[i];
					track[i]=track[n];
					track[n] = test;
				}
			}
		}

		//求p值，即是求出当前磁道即将要访问的是哪个磁道
		for (int j = 0; j<len; j++){
			if (first>track[j]){
				p = j;
			}
		}
		//p=0即是磁道单纯从内向外访问
		if(p == 0){
			for (int i = 0; i<len; i++){
				trackCount = Math.abs(first-track[i]);
				count+= trackCount;
				System.out.println ("  "+(++c)+"		  "+track[i]+"	   		"+trackCount);
				first = track[i];
			}

		}
		//p=len - 1即是磁道单纯从外向内访问
		else if(p == len-1){
			for (int j = len-1; j>=0; j--){
				trackCount = Math.abs(first-track[j]);
				count+= trackCount;
				System.out.println ("  "+(++c)+"		  "+track[j]+"	   		"+trackCount);
				first = track[j];
			}

		}
		//p=j即是当前磁道即将从track[j]向内访问然后再从内向外访问
		else{
			for (int e1 = p; e1>=0; e1--){
				trackCount = Math.abs(first-track[e1]);
				count+= trackCount;
				System.out.println ("  "+(++c)+"		  "+track[e1]+"	   		"+trackCount);
				first = track[e1];
			}
			for (int e2 = p+1; e2<len; e2++){
				trackCount = Math.abs(first-track[e2]);
				count+= trackCount;
				System.out.println ("  "+(++c)+"		  "+track[e2]+"	   		"+trackCount);
				first = track[e2];
			}
		}
		System.out.println ("磁头移动的磁道总数是:"+count);
		float average = (float)(count/len);
		String s = String.valueOf(average);
		System.out.println ("磁头移动的平均磁道数是:"+s.substring(0,4));
		toContinue();

	}
	//表头信息
	public void getInformation(){
		System.out.println ("磁头当前位于第"+first+"磁道,对如下磁道进行访问:");
		for (int i = 0; i<track.length; i++){
			System.out.print (track[i]+"  ");
		}
		System.out.println ();
		System.out.println ("请求次序:"+"	访问磁道:"+"	磁头移动的磁道数:");
	}

	/**提示操作是否继续*/

	public void toContinue(){
		System.out.println ();
		System.out.print ("是否继续操作(Y/N):");

		try {
			String str = streamin.readLine();
			if (str.equals("Y")||str.equals("y")){
				new DiskDispatchArithmetic();
			}
			else{
				System.out.println ();
				System.out.println ("安全退出!");
				System.exit(0);
			}
		}
		catch (Exception ex) {
		}

	}

	/**主函数*/

	public static void main(String args[]){
		new DiskDispatchArithmetic();
	}
}