package com.security.study.spring.aop.JDK_CGLIB.CGLIB.otherDemo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.LazyLoader;

/**
 * LazyLoader实现延迟加载
 *
 *
 * 博客的内容一般比较多，需要用到内容的时候，我们再去加载，下面来模拟博客内容延迟加载的效果。
 */
public class LazyLoaderTest2 {
    //博客信息
    public static class BlogModel {
        private String title;
        //博客内容信息比较多，需要的时候再去获取
        private BlogContentModel blogContentModel;
        public BlogModel() {
            this.title = "spring aop详解!";
            this.blogContentModel = this.getBlogContentModel();
        }
        private BlogContentModel getBlogContentModel() {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(BlogContentModel.class);
            enhancer.setCallback(new LazyLoader() {
                @Override
                public Object loadObject() throws Exception {
                    //此处模拟从数据库中获取博客内容
                    System.out.println("开始从数据库中获取博客内容.....");
                    BlogContentModel result = new BlogContentModel();
                    result.setContent("欢迎大家和我一起学些spring，我们一起成为spring高手！");
                    return result;
                }
            });
            return (BlogContentModel) enhancer.create();
        }
    }
    //表示博客内容信息
    public static class BlogContentModel {
        //博客内容
        private String content;
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
    }
    public static void main(String[] args) {
        //创建博客对象
        BlogModel blogModel = new BlogModel();
        System.out.println(blogModel.title);
        System.out.println("博客内容");
        System.out.println(blogModel.blogContentModel.getContent()); //调用blogContentModel.getContent()方法的时候，才会通过LazyLoader#loadObject方法从db中获取到博客内容信息
    }
    
    /**
     * 运行输出:
     * spring aop详解!
     * 博客内容
     * 开始从数据库中获取博客内容.....
     * 欢迎大家和我一起学些spring，我们一起成为spring高手！
     */
    
    
}
