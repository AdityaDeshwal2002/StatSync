package com.asn8.sqlitetestflightone;

public class CustomerModel {
    private Integer id;
    private String name;
    private Integer age;
    private Boolean customerActive;


    //Constructor
    CustomerModel(int id , String name , int age , boolean customerActive){
        this.id =id;
        this.age = age;
        this.name = name;
        this.customerActive = customerActive;
    }
    CustomerModel(){

    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", customerActive=" + customerActive +
                '}';
    }

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Boolean getCustomerActive() {
        return customerActive;
    }

    public void setCustomerActive(Boolean customerActive) {
        this.customerActive = customerActive;
    }
}
