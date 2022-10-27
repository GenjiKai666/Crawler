package crawler;

import crawler.Crawler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CrawlerTest {
    Crawler crawler = new Crawler();
    @Test
    void startCrawler() {
        for(int i = 1;i<6;i++){
            assertEquals(crawler.startCrawler("https://sh.esf.fang.com/house/i3"+i+"/","?rfss=1-889742072bc5353032-a0"),1);
        }
    }
}