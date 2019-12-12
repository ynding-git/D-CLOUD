package com.ynding.cloud.common.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ynding
 * @version 2019/06/14
 *
 */
@Data
@Entity
@ApiModel(value = "Book", description = "书籍")
@Table(name = "t_book")
public class Book implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(name = "id", notes = "ID", dataType = "long")
	private Long id;

	@ApiModelProperty(name = "reader", notes = "读者", dataType = "string")
	@Column(name = "reader")
	private String reader;

	@ApiModelProperty(name = "isbn", notes = "isbn", dataType = "string")
    @Column(name = "isbn")
	private String isbn;

	@ApiModelProperty(name = "title", notes = "标题", dataType = "string")
    @Column(name = "title")
	private String title;

	@ApiModelProperty(name = "author", notes = "作者", dataType = "string")
    @Column(name = "author")
	private String author;

	@ApiModelProperty(name = "description", notes = "描述", dataType = "string")
    @Column(name = "description")
	private String description;

}
