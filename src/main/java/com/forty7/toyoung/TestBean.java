package com.forty7.toyoung;

import io.swagger.annotations.ApiModelProperty;

public class TestBean {

    @ApiModelProperty(value = "内容")
    private String text;

    @ApiModelProperty(value = "扩展字段")
    private String ext;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

}
