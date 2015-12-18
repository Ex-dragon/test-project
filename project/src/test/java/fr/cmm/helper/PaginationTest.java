package fr.cmm.helper;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by pomme on 18/12/2015.
 */
public class PaginationTest {

    @Test
    public void getPageCountMultiple() {
        Pagination pagination = new Pagination();
        pagination.setPageSize(20);
        pagination.setCount(40);
        assertEquals(2, pagination.getPageCount());
    }

    @Test
    public void getPageCountNonMultiple() {
        Pagination pagination = new Pagination();
        pagination.setPageSize(20);
        pagination.setCount(50);
        assertEquals(3, pagination.getPageCount());
    }

    @Test
    public void getPageCountZero() {
        Pagination pagination = new Pagination();
        pagination.setPageSize(40);
        pagination.setCount(0);
        assertEquals(0, pagination.getPageCount());
    }

}