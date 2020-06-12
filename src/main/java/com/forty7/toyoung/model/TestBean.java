package com.forty7.toyoung.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TestBean {

    @ApiModelProperty(value = "内容")
    private String text;

    @ApiModelProperty(value = "扩展字段")
    private String ext;

}
