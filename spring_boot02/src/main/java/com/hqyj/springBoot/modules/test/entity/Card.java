package com.hqyj.springBoot.modules.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @Description StudentCard
 * @Author HymanHu
 * @Date 2020/7/30 14:25
 */
@Entity   //代表实体baen
@Table(name = "h_card")  //映射到数据库中的表
public class Card {
    @Id     //对应表中  primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GeneratedValue主键生成策略
    private int cardId;
    @Column(name = "card_no", length = 33, unique = true) //@Column映射到表中的列
    private String cardNo;

    /**
     * OneToOne：一对一关系中，一方使用 JoinColumn 注解（有外键），另一方使用 mappedBy 属性（可选）
     * cascade：联级操作
     * fetch：加载数据策略
     * JsonIgnore：不序列化该字段，避免无限递归
     */
    // @OneToOne 一对一的关系，casacde 联级操作  fetch 加载数据策略
    @OneToOne(mappedBy = "studentCard", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnore   //不序列化该字段，避免无限递归
    private Student student;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
