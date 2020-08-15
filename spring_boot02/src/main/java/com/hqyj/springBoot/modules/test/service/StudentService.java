package com.hqyj.springBoot.modules.test.service;

import com.hqyj.springBoot.modules.common.vo.Result;
import com.hqyj.springBoot.modules.common.vo.SearchVo;
import com.hqyj.springBoot.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    Result<Student> insertStudent(Student student);

    Student getStudentByStudentId(int studentId);

    //分页查询
    Page<Student> getStudentBySearchVo(SearchVo searchVo);

    //通过名字来查询
    List<Student> getStudentByStudentName(String studentName);

    List<Student> getStudentByParams(String studentName, int cardId);
}
