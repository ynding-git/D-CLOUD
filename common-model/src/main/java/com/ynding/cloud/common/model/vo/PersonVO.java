package com.ynding.cloud.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ynding
 * @version 2020/08/17
 */
@Data
@ApiModel(value = "PersonVO", description = "人员")
public class PersonVO {
    @ApiModelProperty(name = "id", notes = "ID", dataType = "long")
    private Long id;

    @ApiModelProperty(name = "name", notes = "姓名", dataType = "string")
    private String name;

    @ApiModelProperty(name = "sex", notes = "性别", dataType = "string")
    private String sex;
}
