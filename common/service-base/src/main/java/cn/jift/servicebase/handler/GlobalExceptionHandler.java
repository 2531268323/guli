package cn.jift.servicebase.handler;

import cn.jift.commonutils.R;
import cn.jift.servicebase.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //全局异常
    @ExceptionHandler(Exception.class) //@ExceptionHandler：指定出现了什么异常执行该方法
    @ResponseBody
    public R error(Exception e) {
        return R.error().message("执行了全局异常处理");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        return R.error().message("执行了特定异常处理");
    }

    //自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
