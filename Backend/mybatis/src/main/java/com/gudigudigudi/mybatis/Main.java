package com.gudigudigudi.mybatis;

import com.gudigudigudi.mybatis.inter.IUserOperation;
import com.gudigudigudi.mybatis.model.Article;
import com.gudigudigudi.mybatis.plugin.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class Main {

    @RequestMapping("/pagelist")
    public ModelAndView pageList(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int pageSize = 3;
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int currentResult = (currentPage - 1) * pageSize;

        System.out.println(request.getRequestURI());
        System.out.println(request.getQueryString());

        PageInfo pageInfo = new PageInfo();
        pageInfo.setShowCount(pageSize);
        pageInfo.setCurrentResult(currentResult);
        List<Article> articles = IUserOperation.selectArticleListPage(pageInfo, 1);

        System.out.println(pageInfo);

        int totalCount = pageInfo.getTotalResult();

        int lastPage = 0;
        if (totalCount % pageSize == 0) {
            lastPage = totalCount % pageSize;
        } else {
            lastPage = 1 + totalCount / pageSize;
        }

        if (currentPage >= lastPage) {
            currentPage = lastPage;
        }

        String pageStr = "";

        pageStr = String.format("<a href=\"%s\">last page</a>      <a href=\"%s\"><next page</a>",
                request.getRequestURI() + "?page=" + (currentPage - 1), request.getRequestURI() + "?page=" + (currentPage + 1));

        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("articles", articles);
        modelAndView.addObject("pageStr", pageStr);

        return modelAndView;
    }
}
