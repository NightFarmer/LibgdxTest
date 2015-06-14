package com.mygdx.game.desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 
 * @author GhuaZo
 * QQ��137336521
 * 
 * Ⱥ�������кö���������ͼƬ���ܵ����⣬��ʵ���˾���û��ʲô��Ҫ��
 * ��Ҫ����ͼƬ���ܺ��˷���Դ���ر����ǿ����ֻ������ʱ�򣬾������ã�������������˾�д���򵥵�Сdemo��������˼·
 * �ؼ�������㷨�Ƚϼ򵥣�����ʱ�������ڴ��С��ȱ�㵱ȻҲ�о��Ǽ�������CPU���ܴ������漰��Ҫ���ǵ��ľ����ֻ���Ϸ
 * һ����Դ���ض����ڳ�������֮ǰ�����첽���ط�ʽ����������IO��������ʱ���ֻ��ڴ����úܸߣ���CPU����û�к�ʱ���㣬
 * �������ǽ����ǵĽ��ܴ������ʱ�����У��㷨�Ƚϼ򵥣����Ƕ���һ������㹻���ˡ�
 * ����һ�£�
 * 		��DEMO����LIBGDX��������Ⱥ ��145353767��ת������ɾ������ʾ
 */
 

public class CipherMaker {

	private static short FF = (short)-128  ;
	
	public static void main(String args[]) throws Exception{
		
		//��123.jpg�����ģ���ͼƬ���ܵ�456.jpg�����ģ�
//		cipher("D:\\123.png","D:\\ghuazo.png");
//		System.out.println("��123.jpg�����ģ���ͼƬ���ܵ�ghuazo.png�����ģ�");
		//��456.jpg�����ģ���ͼƬ���ܳ�789.jpg�����ģ�
		//cipher("D:\\456.jpg","D:\\789.jpg");
		//System.out.println("��456.jpg�����ģ���ͼƬ���ܳ�789.jpg�����ģ�");
		
		cipher(getFilePath("../../android/assets/images/texiao.pack.png"),getFilePath("../../android/assets/images/texiao.pack_.png"));
		cipher(getFilePath("../../android/assets/images/texiao.pack2.png"),getFilePath("../../android/assets/images/texiao.pack2_.png"));
		cipher(getFilePath("../../android/assets/images/texiao.pack3.png"),getFilePath("../../android/assets/images/texiao.pack3_.png"));
//		cipher(getFilePath("../../android/assets/images/juese.pack.png"),getFilePath("../../android/assets/images/juese.pack2.png"));
//		cipher(getFilePath("../../android/assets/images/npc.pack.png"),getFilePath("../../android/assets/images/npc.pack2.png"));
	}
	/**
	 * ����ļ��������ļ����룬��������ܵ��ļ������������Ǽ��ܵ��ļ�����������ǽ��ܺ���ļ�
	 * @param inputPath
	 * @param outputPath
	 * @throws Exception
	 */
	public static void cipher(String inputPath,String outputPath)throws Exception{
		//���׷��ģʽд�ļ�
		File outFile = new File(outputPath);
		if(outFile.exists()){
			outFile.delete();
		}
		//׷��ģʽд�ļ�
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
