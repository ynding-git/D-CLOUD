package com.ynding.cloud.book.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ynding.cloud.book.service.BookService;
import com.ynding.cloud.common.model.bo.GQuery;
import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.book.entity.Book;
import com.ynding.cloud.common.model.bo.ResponsePageBean;
import com.ynding.cloud.common.model.vo.BookVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ynding
 *
 */
@Slf4j
@RestController
@RequestMapping("/book")
@Api(value="Book",tags={"Book-Controller"})
@CrossOrigin(origins = "*")
public class BookController {

	private final BookService bookService;
	private final FastFileStorageClient fastFileStorageClient;
	@Value("${imageUrl.prefix}")
	private String imageUrl;

	public BookController(BookService bookService, FastFileStorageClient fastFileStorageClient) {
		this.bookService = Objects.requireNonNull(bookService);
		this.fastFileStorageClient = fastFileStorageClient;
	}

	@PostMapping("/insert")
	@ApiOperation(value = "添加书本", produces = "application/json")
    public ResponseBean insert(@RequestBody BookVO bookVO){

		bookService.save(bookVO);
		return ResponseBean.ok(1);
	}

	@GetMapping("/list")
	@ApiOperation(value = "查询列表", produces = "application/json")
	public ResponseBean findList(@RequestParam Map<String, Object> params){

	    GQuery query = new GQuery(params);
	    List<BookVO> books = bookService.findList(query);

		return ResponseBean.ok(books);
	}

	@GetMapping("/page")
	@ApiOperation(value = "分页查询", produces = "application/json")
	public ResponsePageBean findPage(@RequestParam Map<String, Object> params){
		GQuery query = new GQuery(params);
		return bookService.pageList(query);
	}

	@PostMapping("/picture")
	@ApiOperation(value = "图片上传", produces = "application/json")
	public ResponseBean picture(@RequestParam("file") MultipartFile file) {
		try {
			StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
			String filepath = imageUrl + storePath.getFullPath();
			return ResponseBean.ok(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseBean.fail("上传失败",400);
	}
}
