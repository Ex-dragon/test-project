package fr.cmm.helper;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    // 1 based page index
    private int pageIndex;

    private int pageSize;

    private long count;

    public static final int PAGINATION_SIZE = 10;

    public int getPreviousPageIndex() {
        return isFirstPage() ? pageIndex : pageIndex - 1;
    }

    public int getNextPageIndex() {
        return isLastPage() ? pageIndex : pageIndex + 1;
    }

    public boolean isFirstPage() {
        return pageIndex == 1;
    }

    public boolean isLastPage() {
        return pageIndex * pageSize >= count;
    }

    public int getPageCount() {
        if ((count *1.0 / pageSize) == (int) (count / pageSize) ) {
            return (int) count / pageSize;
        } else {
            return (int) count / pageSize + 1;
        }

    }

    public List<Integer> getPages() {
        List<Integer> listesPages = new ArrayList<>();
        if (pageIndex <=4 && this.getPageCount() <=10) {

            for (int i = 1 ; i<this.getPageCount() ; i++){
                listesPages.add(i);
            }

        } else { if (pageIndex > 4 && this.getPageCount() - pageIndex < 5) {

            for (int i = pageIndex - 4; i < this.getPageCount()+1; i++) {
                listesPages.add(i);
            }

        } else { if (pageIndex > 4 && this.getPageCount() - pageIndex > 5){
            for (int i = pageIndex - 4 ; i<pageIndex + 6 ; i++){
                listesPages.add(i);
            }

        }
        }

        }

        return listesPages;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
