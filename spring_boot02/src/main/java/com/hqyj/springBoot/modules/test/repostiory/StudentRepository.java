package com.hqyj.springBoot.modules.test.repostiory;

import com.hqyj.springBoot.modules.test.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    /*
    * JPA属性查询
    * */
    //JPA提供好的规则进行查询findByStudentName，通过名字进行查询
    List<Student> findByStudentName(String studentName);

    //JPA提供好的规则进行查询findByStudentName，通过名字来模糊查询
    List<Student> findByStudentNameLike(String studentName);

    //JPA提供好的规则进行查询findByStudentName，通过名字来模糊查询只取前面两条数据
    List<Student> findTop2ByStudentNameLike(String studentName);

    /*
    * Jpa自定义查询
    *
    * */
   /* //hql使用"类名"代替"表名"，使用"类名.属性名" 代替"列名"，没有*查询
    //1.?index的方式，index顺序和方法参数顺序一致，从1开始
    @Query(value = "select s from Student s where s.studentName=?1 and s.studentCard.cardId=?2")*/
    /*@Query(value = "select s from Student s where s.studentName = :studentName " +
            "and s.studentCard.cardId = :cardId")*/
    @Query(nativeQuery = true,value = "select * from h_student s where s.student_name = :studentName" +
            "and card_id= :cardId")
    List<Student> getStudentByParams(@Param("studentName") String studentName, @Param("carId") int carId);
}
