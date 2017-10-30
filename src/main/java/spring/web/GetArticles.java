package spring.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import spring.spider.Article;
import spring.spider.UtilMethod;


public class GetArticles {

	/**
	 * @param args   126524   126680  http://www.sbkk88.com/wangluo/langyabang/126680.html
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		/*String firstUrl = "http://www.bxwx.org/b/5/5131/832882.html";
		Article article = UtilMethod.getArticle(firstUrl);
		System.out.println(article.getNextUrl() != null);
		System.out.println(article.getContent() != null);
		System.out.println(article.getNextUrl() != null && article.getContent() != null && !article.getId().equals("996627"));
		while(article.getNextUrl() != null && article.getContent() != null && !article.getId().equals("996627")){
			BigDecimal page=new BigDecimal(0);
			page=new BigDecimal(article.getId()).subtract(new BigDecimal(832881));
			String filePath="D://book/第"+page+"章.txt";
			saveFile(filePath,article.getContent().getBytes());
			article = UtilMethod.getArticle(article.getNextUrl());
		}*/
		String firstUrl = "http://www.sbkk88.com/wangluo/langyabang/126680.html";
		Article article = UtilMethod.getArticle(firstUrl);
		System.out.println(article.getNextUrl() != null);
		System.out.println(article.getContent() != null);
		System.out.println(article.getNextUrl() != null && article.getContent() != null && !article.getId().equals("126523"));
		while(article.getNextUrl() != null && article.getContent() != null && !article.getId().equals("126523")){
			BigDecimal page=new BigDecimal(0);
			page=new BigDecimal(126681).subtract(new BigDecimal(article.getId()));
			String filePath="D://langyabang/第"+page+"章.txt";
			saveFile(filePath,article.getContent().getBytes());
			article = UtilMethod.getArticle(article.getNextUrl());
		}
	}
	private static void saveFile(String filePath, byte[] content) throws IOException {
	    BufferedOutputStream bos = null;
	    try {
	      File file = new File(filePath);
	      //判断文件路径是否存在
	      if (!file.getParentFile().exists()) {
	        //文件路径不存在时，创建保存文件所需要的路径
	        file.getParentFile().mkdirs();
	      }
	      //创建文件（这是个空文件，用来写入上传过来的文件的内容）
	      file.createNewFile();
	      bos = new BufferedOutputStream(new FileOutputStream(file));
	      bos.write(content);
	    } catch (FileNotFoundException e) {
	      throw new FileNotFoundException("文件不存在。");
	    } finally {
	      if (null != bos) {
	        bos.close();
	      }
	    }
	  }    

}
