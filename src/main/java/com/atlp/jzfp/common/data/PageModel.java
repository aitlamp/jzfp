package com.atlp.jzfp.common.data;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 分页模版
 *
 * @author ctc
 * @date 2018年8月24日 21:26:59
 */
@Data
public class PageModel {
    private int pageSize; // 每页记录数
    private int currentPage; // 当前页数
    private int totalPages; // 总页数
    private int totalRows; // 总记录数
    private List<Map> rows; // 数据
    private List<Map> columns; // 表头数据
    private Map pmap; // 其他参数
}
