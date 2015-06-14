package com.mygdx.game.desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 
 * @author GhuaZo
 * QQ：137336521
 * 
 * 群里面总有好多人在讨论图片加密的问题，其实个人觉得没有什么必要，
 * 主要觉得图片解密很浪费资源，特别我们开发手机程序的时候，尽量不用；但是有人提出了就写个简单的小demo给大家提个思路
 * 关键是这个算法比较简单，运行时候消耗内存很小，缺点当然也有就是计算量（CPU）很大，这样涉及主要考虑到的就是手机游戏
 * 一般资源加载都是在程序运行之前采用异步加载方式，加载属于IO操作，这时候手机内存利用很高，而CPU几乎没有耗时运算，
 * 所以我们将我们的解密代码加载时候运行，算法比较简单，但是对于一般的人足够用了。
 * 声明一下：
 * 		本DEMO来自LIBGDX开发交流群 ：145353767，转载切勿删除本提示
 */
 

public class CipherMaker {

	private static short FF = (short)-128  ;
	
	public static void main(String args[]) throws Exception{
		
		//将123.jpg（明文）的图片加密到456.jpg（密文）
//		cipher("D:\\123.png","D:\\ghuazo.png");
//		System.out.println("将123.jpg（明文）的图片加密到ghuazo.png（密文）");
		//将456.jpg（密文）的图片解密成789.jpg（明文）
		//cipher("D:\\456.jpg","D:\\789.jpg");
		//System.out.println("将456.jpg（密文）的图片解密成789.jpg（明文）");
		
		cipher(getFilePath("../../android/assets/images/texiao.pack.png"),getFilePath("../../android/assets/images/texiao.pack_.png"));
		cipher(getFilePath("../../android/assets/images/texiao.pack2.png"),getFilePath("../../android/assets/images/texiao.pack2_.png"));
		cipher(getFilePath("../../android/assets/images/texiao.pack3.png"),getFilePath("../../android/assets/images/texiao.pack3_.png"));
//		cipher(getFilePath("../../android/assets/images/juese.pack.png"),getFilePath("../../android/assets/images/juese.pack2.png"));
//		cipher(getFilePath("../../android/assets/images/npc.pack.png"),getFilePath("../../android/assets/images/npc.pack2.png"));
	}
	/**
	 * 如果文件是明文文件输入，则输出加密的文件，如果输入的是加密的文件，则输出的是解密后的文件
	 * @param inputPath
	 * @param outputPath
	 * @throws Exception
	 */
	public static void cipher(String inputPath,String outputPath)throws Exception{
		//配合追加模式写文件
		File outFile = new File(outputPath);
		if(outFile.exists()){
			outFile.delete();
		}
		//追加模式写文件
		InputStream input = new FileInputStream(inputPath) ; 
		OutputStream output = new FileOutputStream(outputPath,true) ; 
		byte[] cacheBytes = new byte[1024];
		int cacheSize = 0;
		while ((cacheSize = input.read(cacheBytes)) > 0) {
			
			for(int i=0 ; i<cacheSize ; i++){
				cacheBytes[i] = (byte) (cacheBytes[i] ^ FF);
			}
			output.write(cacheBytes, 0, cacheSize);
		}
		input.close() ; 
		output.close() ; 
	
	}
	
	
	public static String getFilePath(String path){
		String path2 = Class.class.getClass().getResource("/").getPath();
		return path2+path;
	}
}
