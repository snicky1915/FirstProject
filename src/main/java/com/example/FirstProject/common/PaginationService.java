package com.example.FirstProject.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {

    public Pageable resolvePageable(Integer page, Integer size, String sortBy, String direction) {
        int resolvedPage = size != null ? (page != null ? page : 0) : 0;
        int resolvedSize = size != null ? size : 100;
        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return PageRequest.of(resolvedPage, resolvedSize, sort);
    }
}
