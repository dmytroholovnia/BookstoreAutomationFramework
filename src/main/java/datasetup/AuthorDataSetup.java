package datasetup;

import datagenerator.RandomGenerator;
import service.AuthorApiService;

public class AuthorDataSetup {

    private final AuthorApiService authorApiService;

    public AuthorDataSetup() {
        this.authorApiService = new AuthorApiService();
    }

    public Integer getRandomAuthor() {
        var author = new RandomGenerator().getRandomElement(authorApiService.getAuthors());
        return author.getId();
    }
}
