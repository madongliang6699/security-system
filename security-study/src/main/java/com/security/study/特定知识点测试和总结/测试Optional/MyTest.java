package com.security.study.特定知识点测试和总结.测试Optional;

public class MyTest {
    public static void main(String[] args) {
    
    
        /**
         * 好吧，大家应该都看得懂什么意思了。相比较of(T value)的区别就是，当value值为null时，of(T value)会报NullPointerException异常；
         * ofNullable(T value)不会throw Exception，ofNullable(T value)直接返回一个EMPTY对象。
         *
         * 那是不是意味着，我们在项目中只用ofNullable函数而不用of函数呢?
         *
         * 不是的，一个东西存在那么自然有存在的价值。当我们在运行过程中，不想隐藏NullPointerException。而是要立即报告，这种情况下就用Of函数。
         * 但是不得不承认，这样的场景真的很少。博主也仅在写junit测试用例中用到过此函数。
         */
    
    
        //MyDto230531 dto = null;
        ////dto.setName("nnn");
        ////dto = null;
        //
        //
        //Optional.ofNullable(dto).ifPresent(en -> {
        //    System.out.println(dto);
        //});
        //
        //Optional.ofNullable(dto).orElseThrow;
        //
        //Optional.of(dto).
        
    }
}
