package net.gong.com;

import java.lang.reflect.Array;
import java.util.Arrays;

public class demo4 {

/*预定义字符类 
. 任何字符（与行结束符可能匹配也可能不匹配） 
\d 数字：[0-9] 
\D 非数字： [^0-9] 
\s 空白字符：[ \t\n\x0B\f\r] 
\S 非空白字符：[^\s] 
\w 单词字符：[a-zA-Z_0-9] 
\W 非单词字符：[^\w] */
		
		
		/*Greedy 数量词 
		X? X，一次或一次也没有 
		X* X，零次或多次 
		X+ X，一次或多次 
		X{n} X，恰好 n 次 
		X{n,} X，至少 n 次 
		X{n,m} X，至少 n 次，但是不超过 m 次 */
		
		/*字符类 
		[abc] a、b 或 c（简单类） 
		[^abc] 任何字符，除了 a、b 或 c（否定） 
		[a-zA-Z] a 到 z 或 A 到 Z，两头的字母包括在内（范围） 
		[a-d[m-p]] a 到 d 或 m 到 p：[a-dm-p]（并集） 
		[a-z&&[def]] d、e 或 f（交集） 
		[a-z&&[^bc]] a 到 z，除了 b 和 c：[ad-z]（减去） 
		[a-z&&[^m-p]] a 到 z，而非 m 到 p：[a-lq-z]（减去） */

		
		/*
		 * 正则表达式对字符串的操作，主要有以下几种应用
		 * 	匹配：matches()
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 	切割：split()
		 * 
		 * 
		 * 
		 * 
		 * 	替换：
		 * 
		 * 
		 * 
		 * 
		 * 	查找：
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
	//没加上数量词之前预定义字符只能匹配一个字符而已
	public static void main(String[] args) {
		//匹配手机号码（1开头，第二位：3 4 5 7 8 长度11位）
		System.out.println("18914125581".matches("1[34578]\\d{9}"));
		testSplit();
	}
	
	public static void testSplit(){
		String str = "大家加加加明天天玩的的的的的的开心";
		//如果正则内容需要被复用，那么需要对正则的内容进行分组，为了提高正则的复用性。组号不能指定，组号从1开始，用：()分子，\\1表示第一组
		String[] sss = str.split("(.)\\1+");
		System.out.println(Arrays.toString(sss));
	}
}
