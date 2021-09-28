package com.xuan.mix.domain.cpv.service.action;

import com.xuan.mix.common.exception.BizException;
import com.xuan.mix.domain.common.ExceptionCode;
import com.xuan.mix.domain.cpv.model.Category;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2021/9/28
 */
@Component
public class CategoryCheckAction {

    public void check(Category category) {

        if (null == category) {
            throw new BizException(ExceptionCode.CATEGORY, "category is null");
        }

        if (null == category.getName() || category.getName().trim().length() == 0) {
            throw new BizException(ExceptionCode.CATEGORY, "category.getName is empty");
        }
    }

    public void checkDelete(Category category) {
        if (null == category) {
            throw new BizException(ExceptionCode.CATEGORY, "category is null");
        }

        if (null == category.getId()) {
            throw new BizException(ExceptionCode.CATEGORY, "category.getId is null");
        }
    }

}
