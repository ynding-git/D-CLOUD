package com.ynding.cloud.common.model.entity.person;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @author dyn
 * @version 2020/1/20
 */
@Data
@Builder
@Document(collection = "t_user")
public class User {
    @Id
    private Long id;

    @Indexed(unique = true, background = true)//后台创建唯一索引
    private String no;

    private String username;
    private Integer age;
}
