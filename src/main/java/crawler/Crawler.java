package crawler;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Random;
import java.util.Vector;

public class Crawler {
    /**
     * 通过URL爬取网页内容
     * @param url 目标网页
     * @return document
     */
    private Document getURL(String url){
        try
        {
            return Jsoup.connect(url).get();
        }
        catch (IOException e)
        {
            //爬取失败
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 启动爬虫
     * @param url
     */
    public Vector<String> startCrawler(String url,String key){
        Vector<String> houses_string = new Vector<>();
        //调用Jsoup读取网页
        Document document = getURL(url+key);
        //调取每一个房屋元素
        String title,attribute,scale,floor,height,direction,date,contact,name,traffic,cost,averagePrice;
        Elements houses = document.select("dl[dataflag=\"bg\"]");
        for(Element element:houses){
            title = element.select("span[class=\"tit_shop\"]").text();

            //获取以下信息
            //3室2厅 | 139.5㎡ | 低层 （共20层） | 南向 | 2006年建 | 徐旭明 len 12
            //1室1厅 | 49㎡ | 低层 （共6层） | 南北向 | 孙春兰 len 10
            //独栋 | 卧室：5个 | 花园：500平米 | 480.74㎡ | 南向 | 徐剑峰 len=11
            String temp = element.select("p[class=\"tel_shop\"]").text();
            String[] tmp = temp.split(" ");
            if(tmp.length == 10){
                attribute = tmp[0];
                scale = tmp[2];
                floor = tmp[4];
                height = tmp[5].substring(tmp[5].indexOf("（")+1,tmp[5].indexOf("）"));
                direction = tmp[7];
                date = null;
                contact = tmp[9];
            }
            else if(tmp.length ==11){
                attribute = tmp[0];
                scale = tmp[6];
                floor = null;
                height = null;
                direction = tmp[8];
                date = null;
                contact = tmp[10];
            }
            else{
                attribute = tmp[0];
                scale = tmp[2];
                floor = tmp[4];
                height = tmp[5].substring(tmp[5].indexOf("（")+1,tmp[5].indexOf("）"));
                direction = tmp[7];
                date = tmp[9];
                contact = tmp[11];
            }

            //获取交通、小区名字、价格信息
            name = element.select("p[class=\"add_shop\"]").text().split(" ")[0];
            String[] prices = element.select("dd[class=\"price_right\"]").text().split(" ");
            cost = prices[0];
            averagePrice = prices[1];
            traffic = element.select("span[class=\"bg_none icon_dt\"]").text();

            //将信息存入数组
            houses_string.add(title+","+
                    attribute+","+
                    scale+","+
                    floor+","+
                    height+","+
                    direction+","+
                    date+","+
                    contact+","+
                    name+","+
                    traffic+","+
                    cost+","+
                    averagePrice);
        }
        return houses_string;
        //hhhhhhhhhh
        //hhhhh

    }
}
