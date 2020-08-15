package com.hqyj.springBoot.modules.test.controller;

import com.hqyj.springBoot.modules.common.vo.Result;
import com.hqyj.springBoot.modules.common.vo.SearchVo;
import com.hqyj.springBoot.modules.test.entity.Student;
import com.hqyj.springBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*
    * 127.0.0.1/api/insertStudent
    *{"studentName":"zhangshan","studentCard":{"cardId":"1"}}
    * */
    //新增
     @PostMapping(value = "/insertStudent",consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }
    /*
    * 127.0.0.1/api/Stu/1
    * */
    //通过studentId来查询
    @GetMapping("/Stu/{studentId}")
    public Student getStudentByStudentId(@PathVariable int studentId){
         return studentService.getStudentByStudentId(studentId);
    }

    /*
    * 127.0.0.1/api/students----post
    * {"currentPage":"1","pageSize":"5","keyWord":"hu","orderBy":"studentName","sort":"desc"}
    * */
    //多條件分頁查詢
    @PostMapping(value = "/students",consumes = "application/json")
    public Page<Student> getStudentBySearchVo(@RequestBody SearchVo searchVo){
        return studentService.getStudentBySearchVo(searchVo);
    }

    /*
    * 127.0.0.1/api/studentByName?studentName=yanghouneng ----post
    * */
    //通过名字来查询
    @GetMapping ("/studentByName")
    public List<Student> getStudentByStudentName(@RequestParam String studentName){
        return studentService.getStudentByStudentName(studentName);
    }

    /*
    *  127.0.0.1/api/Params?studentName=yanghouneng
    * required = false  不是必须的
    * defaultValue = "1" 默认值为1
    * */
    //Jpa自定义查询
    @GetMapping ("/Params")
    public List<Student> getStudentByParams(@RequestParam String studentName,
                                            @RequestParam(required = false,defaultValue = "1") Integer cardId){
        return studentService.getStudentByParams(studentName,cardId);
    }
}
