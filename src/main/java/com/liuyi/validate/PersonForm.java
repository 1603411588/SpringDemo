package com.liuyi.validate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

    @NotNull(message = "姓名不能为空")
    @Size(min = 2, max = 30,message = "字符数需要在2到30个之间")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 18,message = "年龄不能小于18岁")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonForm{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
