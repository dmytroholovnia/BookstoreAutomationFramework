package datasetup;

import datagenerator.RandomGenerator;
import lombok.Getter;
import service.BookApiService;

@Getter
public class BookDataSetup {

    private final BookApiService bookApiService;

    public BookDataSetup() {
        this.bookApiService = new BookApiService();
    }

    public Integer getRandomBookId() {
        var randomBook = new RandomGenerator().getRandomElement(bookApiService.getBooks());
        return randomBook.getId();
    }

}
