package com.springcloud.learn.example.orderserviceprovider.dto;

import com.springcloud.learn.example.exception.ValidationException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDto {

    private String userId;
    @NotNull(message = "名称不能为空")
    private String name;
    @NotNull(message="手机号码不能为空")
    private String telNo;

    @NotEmpty(message="商品列表不能为空")
    private List<ItemDto> items;//商品列表

    public void validData(BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError oe:bindingResult.getAllErrors()){
                stringBuilder.append(oe.getDefaultMessage() + "\n");
            }
            throw new ValidationException(stringBuilder.toString());
        }
    }
}
