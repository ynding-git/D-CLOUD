package com.ynding.cloud.common.model.entity.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Data
@Entity
@ApiModel(value = "Person", description = "人员")
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "ID", dataType = "long")
    private Long id;

    @ApiModelProperty(name = "name", notes = "姓名", dataType = "string")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(name = "sex", notes = "性别", dataType = "string")
    @Column(name = "sex")
    private String sex;
}
