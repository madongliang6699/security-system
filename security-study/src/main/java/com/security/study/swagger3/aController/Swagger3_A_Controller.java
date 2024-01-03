package com.security.study.swagger3.aController;

import com.security.study.swagger3.aController.pojo.Swagger3User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Swagger3_A_Controller业务模块")
@RestController
@RequestMapping(value = {"/swagger3a", "/swagger3aa"})
public class Swagger3_A_Controller {


    @ApiOperation("根据id查询")
    @GetMapping(value = {"/selectById", "/selectOne"})
    public void aa(@PathVariable("sid") Long sid) {
        System.out.println("sssssssrgfs");
    }

    @ApiOperation("根据id查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "sid", value = "用户id", required = true, paramType = "path")})
    @GetMapping("/select/{sid}")
    public void select(@PathVariable("sid") Long sid) {
        System.out.println("sssssssrgfs");
    }

    /**
     * ApiImplicitParam的 paramType 属性表示参数放在哪里，主要有以下几个属性：
     * header : 请求参数的获取：@RequestHeader 参数需要放到header里
     * query : 请求参数的获取：@RequestParam 参数需要放到参数里，包括地址的?后的参数里和post表单提交
     * path : 请求参数的获取：@PathVariable 参数需要放到url里，是url的一部分
     * body : 请求参数的获取：@RequestBody
     * form : 不常用
     * ————————————————
     * 原文链接：https://blog.csdn.net/u010250240/article/details/101945915
     */
    @ApiOperation(value = "分页查询", notes = "支持keyword参数模糊查询（作用于name和addr字段）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "订单名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "订单编码", paramType = "query", dataType = "Long", defaultValue = "1234"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/selectByPage")
    public String selectTwo(Long id, String name, Long code) {
        System.out.println("sssssssrgfs");
        return "haha";
    }


    /**
     * tags属性：标记相同tags标识的的接口会另外单独显示在一起。
     * 比如实现一个”课程排班录入“的复杂功能，需要用到老师模块，学生模块，教室模块，等多个模块的接口，这些模块的接口都分布在各自的模块下，
     * 在自动生成的swagger页面中就是在不同的地方，因此前端人员对接这个功能的时候就要去好几个地方找接口，也不一定知道到底需要用到哪些接口，
     * 如果把该功能的接口都打上同样的标签，这些不同模块的接口，就会另外单独拎出来展示在”课程排班录入“这个分类下，前端人员就不用到处找接口了，也知道这个功能需要用到哪些接口了。
     */
    @ApiOperation(value = "查询所有", notes = "支持keyword参数模糊查询（作用于name和addr字段）", tags = "课程排班录入")
    @PostMapping("/selectAll")
    public String selectAll(Swagger3User swagger3User) {
        System.out.println("sssssssrgfs");
        return "haha";
    }


    /**
     * 参数上加 @RequestBody 后，swagger页面才能展示实体类信息。
     */
    @ApiOperation(value = "查询所有", notes = "支持keyword参数模糊查询（作用于name和addr字段）", tags = "课程排班录入")
    @PostMapping("/selectAll2")
    public String selectAll2(@RequestBody Swagger3User swagger3User) {
        System.out.println("sssssssrgfs");
        return "haha";
    }

}
