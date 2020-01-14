package qwj.community.community.dto;

import lombok.Data;
import qwj.community.community.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project
 * @Title PaginationDto
 * @Description
 * @Author qwj
 * @Date 2020年01月05日 18:41
 */
@Data
public class PaginationDto {
    private List<QuestionDto> questions;
    //上一页
    private boolean showPrevious;
    //第一页
    private boolean showFirstPage;
    //下一页
    private boolean showNext;
    //最后一页
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, int page, int size) {

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size +1;
        }
        //判断越界
        if (page > totalPage) {
            page = totalPage;
        }
        //判断越界
        if (page < 1) {
            page = 1;
        }
        //特殊处理
        if (totalPage == 0) {
            totalPage = 1;
        }
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3 ; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //是否显示上一页
        if (page == 1) {
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否显示下一页
        if (page == totalPage) {
            showNext = false;
        }else {
            showNext = true;
        }
        //是否显示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        //是否显示最后一页
        if (page > (totalPage - 3)) {
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
