package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = ArrayUtils.add(schoolBooks, book);
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return Arrays.stream(schoolBooks)
                .filter(b ->name.equals(b.getName()))
                .toArray(SchoolBook[]::new);
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name) == null) {
            return false;
        }
        schoolBooks = ArrayUtils.removeElements(schoolBooks, findByName(name));
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
