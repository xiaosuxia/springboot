package spring.spider;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class UtilMethod {

	/**
	 * 根据url获取Document对象
	 * @param url 小说章节url
	 * @return Document对象
	 */
	public static Document getDocument(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(500000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	/**
	 * 根据获取的Document对象找到章节标题
	 * @param doc
	 * @return 标题
	 */
	public static String getTitle(Document doc){
		return doc.getElementsByClass("zhbook_info").text();
		
	}
	
	/**
	 * 根据获取的Document对象找到小说内容
	 * @param doc
	 * @return 内容
	 */
	public static String getContent(Document doc){
		if(doc.getElementsByTag("p")!=null){
			return doc.getElementsByTag("p").text();
		}else{
			return null;
		}
		/*if(doc.getElementById("content") != null){
			return doc.getElementById("content").text();
		}else{
			return null;
		}*/
		
	}
	/**
	 * 根据获取的Document对象找到下一章的Url地址
	 * @param doc
	 * @return 下一章Url
	 */
	public static String getNextUrl(Document doc){
		Element ul=doc.getElementsByClass("nextPage").first();
		//Element ul = doc.select("ul").first();
		String regex = "<a href=\"(.*?)\" class=\"nextPage\">下一篇";
		Pattern pattern = Pattern.compile(regex);
		if(ul!=null){
		Matcher matcher = pattern.matcher(ul.toString());
		Document nextDoc = null;
		if (matcher.find()) {
			nextDoc = Jsoup.parse(matcher.group());
			Element href = nextDoc.select("a").first();
			return "http://www.sbkk88.com" + href.attr("href");
		}else{
			return null;
		}
		}else{
			return null;
		}
		
	}
	
	/**
	 * 根据url获取id
	 * @param url
	 * @return id 
	 */
	public static String getId(String url){
		String urlSpilts[] = url.split("/");
		return (urlSpilts[urlSpilts.length - 1]).split("\\.")[0];
	}
	
	/**
	 * 根据小说的Url获取一个Article对象
	 * @param url
	 * @return
	 */
	public static Article getArticle(String url){
		Article article = new Article();		
		article.setUrl(url);
		Document doc = getDocument(url);
		article.setId(getId(url));
		article.setTitle(getTitle(doc));
		article.setNextUrl(getNextUrl(doc));
		article.setContent(getContent(doc));
		return article;
	}
	
	
}