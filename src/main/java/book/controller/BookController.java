package book.controller;

import book.pojo.Book;
import book.service.BookService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * @author: Ding
 * @date: 2022/7/2
 * @description:
 * @modify:
 */

public class BookController {

    private BookService bookService;

    public String deleteOne(String deleteBookId) {
        bookService.deleteOne(Integer.parseInt(deleteBookId));
        return "thymeleaf:manager/book_manager";
    }

    public String editOrAddBook(String bookId, String bookName, String price, String author, String saleCount, String bookCount, HttpSession session) {
        Book newBook = new Book(Integer.parseInt(bookCount), bookName, new BigDecimal(price), null, author, Integer.parseInt(saleCount), 0);
        if (bookId == null) {
            addBook(newBook);
        } else {
            newBook.setId(Integer.parseInt(bookId));
            editBook(newBook);
        }
        return "thymeleaf:manager/book_manager";
    }

    private void addBook(Book newBook) {
        bookService.insertOne(newBook);

    }

    private void editBook(Book newBook) {
        Book oldBook = bookService.selectById(newBook.getId());

        // 去重，避免更新不必要的数据
        if (oldBook.getBookCount().equals(newBook.getBookCount())) {
            newBook.setBookCount(null);
        }
        if (oldBook.getBookName().equals(newBook.getBookName())) {
            newBook.setBookName(null);
        }
        if (oldBook.getPrice().equals(newBook.getPrice())) {
            newBook.setPrice(null);
        }
        if (oldBook.getBookImg().equals(newBook.getBookImg())) {
            newBook.setBookImg(null);
        } else if (newBook.getBookImg() == null){
            newBook.setBookImg(oldBook.getBookImg());
        }
        if (oldBook.getAuthor().equals(newBook.getAuthor())) {
            newBook.setAuthor(null);
        }
        if (oldBook.getSaleCount().equals(newBook.getSaleCount())) {
            newBook.setSaleCount(null);
        }
        if (oldBook.getBookStatus().equals(newBook.getBookStatus())) {
            newBook.setBookStatus(null);
        }

        bookService.updateById(newBook);
    }

}
