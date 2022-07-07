package book.service;

import book.pojo.Book;
import book.pojo.Page;
import book.utils.LoggerUtils;

import java.util.List;

/**
 * @author: Ding
 * @date: 2022/7/1
 * @description:
 * @modify:
 */

public class PageService {

    private BookService bookService;

    public Page getPage(String pageNum, String pageSize, List<?> list) {
        if (pageSize == null) {
            pageSize = "15";
            LoggerUtils.logInfo("web.xml 未指定 pageSize，已赋默认值 15");
        }
        // 获取当前页数
        if (pageNum == null) {
            pageNum = "1";
        }
        // 封装 page 对象
        Page page = new Page();
        page.setPageNum(Integer.parseInt(pageNum));
        page.setPageSize(Integer.parseInt(pageSize));

        Integer allCount;
        if (list == null) {
            allCount = bookService.selectAllCount();
        } else {
            allCount = list.size();
        }
        page.setAllCount(allCount);
        page.setMaxPageNum(allCount / page.getPageSize() + 1);
        return page;
    }
}
