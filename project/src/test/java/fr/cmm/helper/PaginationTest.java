package fr.cmm.helper;

import org.junit.Assert;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;


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


    @Test
    public void getPagesInf4Inf10() {
        Pagination pagination = new Pagination();
        pagination.setCount(9);
        pagination.setPageIndex(3);
        Assert.assertEquals(asList(1, 2, 3, 4, 5, 6, 7, 8), pagination.getPages());

    }

    @Test
    public void getPagesSup4Inf10() {
        Pagination pagination = new Pagination();
        pagination.setCount(9);
        pagination.setPageIndex(5);
        Assert.assertEquals( asList(1, 2, 3, 4, 5, 6, 7, 8, 9), pagination.getPages());

    }

    @Test
    public void getPagesSup4Sup10ProcheBout() {
        Pagination pagination = new Pagination();
        pagination.setCount(15);
        pagination.setPageIndex(12);
        Assert.assertEquals(asList(8, 9, 10, 11, 12, 13, 14, 15), pagination.getPages());

    }

    @Test
    public void getPagesSup4Sup10LoinBout() {
        Pagination pagination = new Pagination();
        pagination.setCount(25);
        pagination.setPageIndex(12);
        Assert.assertEquals(asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17), pagination.getPages());

    }
}