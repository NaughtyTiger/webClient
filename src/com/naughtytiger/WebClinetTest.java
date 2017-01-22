package com.naughtytiger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * get list of special keyword search result
 * @description
 * @author lzhang
 * @date Jan 17, 2017
 */
public class WebClinetTest {
  public static void main(String args[]){
    WebClient webClient=new WebClient(BrowserVersion.CHROME);
    webClient.getOptions().setJavaScriptEnabled(true);
    webClient.getOptions().setCssEnabled(false);
    webClient.getOptions().setRedirectEnabled(true);
    webClient.getOptions().setThrowExceptionOnScriptError(false);
    webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    webClient.getOptions().setTimeout(10000);
    try {
      WebRequest webRequest=new WebRequest(new URL("https://s.taobao.com/search?initiative_id=tbindexz_20170117&ie=utf8&spm=a21bo.50862.201856-taobao-item.2&sourceId=tb.index&search_type=item&ssid=s5-e&commend=all&imgfile=&q=%E8%B5%9E%E8%BF%AA%E5%8D%A1%E4%B9%8B%E5%A3%B0%E5%A6%AE%E8%8E%8E&suggest=history_1&_input_charset=utf-8&wq=%E8%B5%9E%E8%BF%AA%E5%8D%A1&suggest_query=%E8%B5%9E%E8%BF%AA%E5%8D%A1&source=suggest"));
      webRequest.setHttpMethod(HttpMethod.GET);
      HtmlPage page=webClient.getPage(webRequest);
      webClient.waitForBackgroundJavaScript(10000);
      String result=page.asXml();
      webClient.close();
      
      Document doc=Jsoup.parse(result);
      Elements itemlist=doc.select("div[class=item J_MouserOnverReq]");
      int i=0;
      for (Element item:itemlist){
        i++;
        if (i==2){
          System.out.println("!! item index: "+i);
          System.out.println(item.html());
        }
      }
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FailingHttpStatusCodeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
