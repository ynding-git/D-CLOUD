package com.ynding.cloud.person.controller;

import com.ynding.cloud.common.model.bo.GQuery;
import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.entity.person.Person;
import com.ynding.cloud.common.model.entity.person.User;
import com.ynding.cloud.person.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ynding
 */
@RestController
@RequestMapping("/person")
@Slf4j
@Api(value = "PersonController", tags = "person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加人员", produces = "application/json")
    public ResponseBean save(@RequestBody Person person) {
        personService.save(person);
        return ResponseBean.ok(1);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询人员", produces = "application/json")
    public ResponseBean getList(@RequestParam Map<String, Object> params) {
        GQuery query = new GQuery(params);
        return ResponseBean.ok(personService.findList(query));
    }
}
