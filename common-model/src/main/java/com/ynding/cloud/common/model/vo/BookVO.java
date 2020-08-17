package com.ynding.cloud.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ynding
 * @version 2020/08/17
 */
@Data
@ApiModel(value = "BookVO", description = "书籍")
public class BookVO {

    @ApiModelProperty(name = "id", notes = "ID", dataType = "long")
    private Long id;

    @ApiModelProperty(name = "reader", notes = "读者", dataType = "string")
    private String reader;

    @ApiModelProperty(name = "isbn", notes = "isbn", dataType = "string")
    private String isbn;

    @ApiModelProperty(name = "title", notes = "标题", dataType = "string")
    private String title;

    @ApiModelProperty(name = "author", notes = "作者", dataType = "string")
    private String author;

    @ApiModelProperty(name = "description", notes = "描述", dataType = "string")
    private String description;
}
