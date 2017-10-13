package com.example.dell.zersey.POJO;

/**
 * Created by DELL on 13/10/2017.
 */

public class CommentPojo {
    String name,user_comment;

   public CommentPojo(){}

    public CommentPojo(String user_comment,String name){
           this.name=name;
          this.user_comment=user_comment;
    }


    public String getUser_name() {
        return name;
    }

    public void setUser_name(String user_name) {
        name = user_name;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }
}
