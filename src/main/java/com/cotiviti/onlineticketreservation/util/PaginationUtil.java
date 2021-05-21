package com.cotiviti.onlineticketreservation.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PaginationUtil {
    public static PageRequest getPageRequest(Integer page, Integer perPage, String sortBy, String order) {
        PageRequest pageRequest;
        if (page != null && perPage != null && !sortBy.isBlank() && order.equals("desc")) {
            pageRequest = PageRequest.of(page, perPage, Sort.by(sortBy).descending());
        } else if (page != null && perPage != null && !sortBy.isBlank() && order.equals("asc")) {
            pageRequest = PageRequest.of(page, perPage, Sort.by(sortBy).ascending());
        } else if (page != null && perPage != null) {
            pageRequest = PageRequest.of(page, perPage, Sort.by("id").ascending());
        } else {
            pageRequest = PageRequest.of(0, 5, Sort.by("id").ascending());
        }
        return pageRequest;
    }
}
