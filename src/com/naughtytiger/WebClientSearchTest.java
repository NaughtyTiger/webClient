package com.naughtytiger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WebClientSearchTest {
  public static void main(String args[]) throws Exception {
    WebClient webClient = new WebClient(BrowserVersion.CHROME);
    webClient.getOptions().setJavaScriptEnabled(true);
    webClient.getOptions().setCssEnabled(false);
    webClient.getOptions().setThrowExceptionOnScriptError(false);
    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    HtmlPage page=(HtmlPage) webClient.getPage("https://s.taobao.com/search?q=%E5%A4%A9%E7%8C%AB%E9%AD%94%E7%9B%92&imgfile=&commend=all&ssid=s5-e&search_type=item&sourceId=tb.index&spm=a21bo.50862.201856-taobao-item.2&ie=utf8&initiative_id=tbindexz_20170117&hintq=1");
    
    HtmlInput input=(HtmlInput)page.getHtmlElementById("q");
    input.setValueAttribute("赞迪卡伙伴");

    HtmlButton btn=(HtmlButton)page.getElementById("J_SearchForm").getLastElementChild();
    System.out.println(btn.asXml());
    
    HtmlPage page2=btn.click();
    webClient.close();
  }
}
