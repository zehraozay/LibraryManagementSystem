package com.ozayzehra.library.controller.mapper;

import org.springframework.beans.BeanUtils;

import com.ozayzehra.library.controller.request.RequestBook;
import com.ozayzehra.library.core.dto.BookDTO;

public class BookMapper {

    public static BookDTO convertBookApiRequestToInputDTO(RequestBook request) {

        if (request != null) {
            BookDTO result = new BookDTO();
            BeanUtils.copyProperties(request, result);
            return result;
        }
        return null;
    }
}
