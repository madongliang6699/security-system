package com.security.study.spring.注解.java注解预备知识.demo1;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class MyTest {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        
        //region 解析类的注解
        for (Annotation annotation : UseAnnotationTest.class.getAnnotations()) {
            System.out.println(annotation);
        }
        
        /**
         * 输出：
         * @com.mdl.java注解预备知识.demo1.MyAnn1(value=用在了类上)
         * @com.mdl.java注解预备知识.demo1.MyAnn1_1(value=23)
         */
        //endregion
        
        
        //region 解析类上的类型变量：解析类名后面的尖括号的部分
        TypeVariable<Class<UseAnnotationTest>>[] typeParameters = UseAnnotationTest.class.getTypeParameters();
        for (TypeVariable<Class<UseAnnotationTest>> typeParameter : typeParameters) {
            System.out.println(typeParameter.getName() + "变量类型注解信息：");
            Annotation[] annotations = typeParameter.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
        }
        
        /**
         * 输出：
         * V1变量类型注解信息：
         * @com.mdl.java注解预备知识.demo1.MyAnn1(value=用在了类变量类型V1上)
         * @com.mdl.java注解预备知识.demo1.MyAnn1_1(value=1)
         * V2变量类型注解信息：
         * @com.mdl.java注解预备知识.demo1.MyAnn1(value=用在了类变量类型V2上)
         * @com.mdl.java注解预备知识.demo1.MyAnn1_1(value=2)
         */
        //endregion
        
        
        //region 解析字段name上的注解
        Field nameField = UseAnnotationTest.class.getDeclaredField("name");
        for (Annotation annotation : nameField.getAnnotations()) {
            System.out.println(annotation);
        }
        
        /**
         * 输出：
         * @com.mdl.java注解预备知识.demo1.MyAnn1(value=用在了字段上)
         * @com.mdl.java注解预备知识.demo1.MyAnn1_1(value=3)
         */
        //endregion
        
        //region 解析泛型字段map上的注解
        Field field = UseAnnotationTest.class.getDeclaredField("map");
        Type genericType = field.getGenericType();
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        AnnotatedType annotatedType = field.getAnnotatedType();
        AnnotatedType[] annotatedActualTypeArguments = ((AnnotatedParameterizedType) annotatedType).getAnnotatedActualTypeArguments();
        int i = 0;
        for (AnnotatedType actualTypeArgument : annotatedActualTypeArguments) {
            Type actualTypeArgument1 = actualTypeArguments[i++];
            System.out.println(actualTypeArgument1.getTypeName() + "类型上的注解如下：");
            for (Annotation annotation : actualTypeArgument.getAnnotations()) {
                System.out.println(annotation);
            }
        }
        
        /**
         * 这个比较复杂，先不学习
         */
        //endregion
    
    
        //region 解析构造函数上的注解
        Constructor<?> constructor = UseAnnotationTest.class.getConstructors()[0];
        for (Annotation annotation : constructor.getAnnotations()) {
            System.out.println(annotation);
        }
        /**
         * 打印：
         * @com.mdl.java注解预备知识.demo1.MyAnn1(value=用在了构造方法上)
         * @com.mdl.java注解预备知识.demo1.MyAnn1_1(value=6)
         */
        //endregion
    
    
        //region 解析m1方法上的注解
        Method method = UseAnnotationTest.class.getMethod("m1", String.class);
        for (Annotation annotation : method.getAnnotations()) {
            System.out.println(annotation);
        }
        /**
         * @com.mdl.java注解预备知识.demo1.MyAnn1(value=用在了方法上)
         * @com.mdl.java注解预备知识.demo1.MyAnn1_1(value=7)
         */
        //endregion
    
        //region 解析m1方法参数注解
        Method method1 = UseAnnotationTest.class.getMethod("m1", String.class);
        for (Parameter parameter : method1.getParameters()) {
            System.out.println(String.format("参数%s上的注解如下:", parameter.getName()));
            for (Annotation annotation : parameter.getAnnotations()) {
                System.out.println(annotation);
            }
        }
        /**
         * 参数arg0上的注解如下:
         * @com.mdl.java注解预备知识.demo1.MyAnn1(value=用在了参数上)
         * @com.mdl.java注解预备知识.demo1.MyAnn1_1(value=8)
         */
        /**
         * 上面参数名称为arg0，如果想让参数名称和源码中真实名称一致，操作如下：
         * 如果你编译这个class的时候没有添加参数–parameters，运行的时候你会得到这个结果：
         * Parameter: arg0
         * 编译的时候添加了–parameters参数的话，运行结果会不一样：
         * Parameter: args
         * 对于有经验的Maven使用者，–parameters参数可以添加到maven-compiler-plugin的配置部分：
         * <plugin>
         *     <groupId>org.apache.maven.plugins</groupId>
         *     <artifactId>maven-compiler-plugin</artifactId>
         *     <version>3.1</version>
         *     <configuration>
         *         <compilerArgument>-parameters</compilerArgument>
         *         <source>1.8</source>
         *         <target>1.8</target>
         *     </configuration>
         * </plugin>
         */
        //endregion
        
        
        
        
    }
}
