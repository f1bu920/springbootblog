package com.springboot.practice.Service;

import com.springboot.practice.Bean.BlogLink;

import java.util.List;
import java.util.Map;

public interface LinkService {
    Map<Byte, List<BlogLink>> getLinksForLinkPage();
}
