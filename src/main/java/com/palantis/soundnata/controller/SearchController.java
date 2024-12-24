package com.palantis.soundnata.controller;

import com.palantis.soundnata.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/playlist/search")
    @ResponseBody
    public Map<String, List<?>> playlistSearch(@RequestParam("query") String query) {
        return searchService.search(query);
    }


}
