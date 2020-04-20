package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import org.apache.commons.lang3.ArrayUtils;
import java.util.stream.IntStream;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = ArrayUtils.add(authors, author);
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        int index = IntStream.range(0, authors.length)
                .filter(a -> name.equals(authors[a].getName()) && lastname.equals(authors[a].getLastName()))
                .findFirst()
                .orElse(-1);
        if (index >= 0) {
            return authors[index];
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        authors = ArrayUtils.removeElement(authors, author);
        return true;

    }

    @Override
    public int count() {
        return  authors.length;
    }
}
