package crawler;

import java.io.*;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        Vector<Vector<String>> result = new Vector<>();
        for (int i = 1; i < 6; i++) {
            result.add(crawler.startCrawler("https://sh.esf.fang.com/house/i3" + i + "/", "?rfss=2-ccc6a3c399ce32b1bb-a7"));
        }

        File writeFile = new File("house.csv");
        BufferedWriter writeText = null;
        try {
            writeText = new BufferedWriter(new FileWriter(writeFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Vector<String> vector : result) {

            try {
                for (String str : vector) {
                    writeText.newLine();
                    writeText.write(str);
                }
            } catch (FileNotFoundException e) {
                System.out.println("没有找到指定文件");
            } catch (IOException e) {
                System.out.println("文件读写出错");
            }
        }
    }
}
