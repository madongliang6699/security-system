package com.security.study.myBatis.A_Mybatis使用详解1;

import com.security.study.myBatis.A_Mybatis使用详解1.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

@Slf4j
public class Test0729 {
    public static void main(String[] args) throws IOException {
    
        /**
         * 构建SqlSessionFactory对象：
         *
         * SqlSessionFactory是一个接口，是一个重量级的对象，SqlSessionFactoryBuilder通过读取全局配置文件来创建
         * 一个SqlSessionFactory，创建这个对象是比较耗时的，主要耗时在对mybatis全局配置文件的解析上面，
         * 全局配置文件中包含很多内容，SqlSessionFactoryBuilder通过解析这些内容，创建了一个复杂的SqlSessionFactory对象，
         * 这个对象的生命周期一般和应用的生命周期是一样的，随着应用的启动而创建，随着应用的停止而结束，所以一般是一个全局对象，
         * 一般情况下一个db对应一个SqlSessionFactory对象。
         */
        
        //指定mybatis全局配置文件
        String resource = "mybatis-config.xml";
        //读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        
        System.out.println("-------"+sqlSessionFactory);
        
//===============================================================================================================
    
        /**
         * 下面构建SqlSession对象
         *
         * SqlSession相当于jdbc中的Connection对象，相当于数据库的一个连接，可以用SqlSession来对db进行操作：
         * 如执行sql、提交事务、关闭连接等等，
         * 需要通过SqlSessionFactory来创建SqlSession对象，SqlSessionFactory中常用的有2个方法来创建SqlSession对象，
         * 如下：
         * //创建一个SqlSession，默认不会自动提交事务。
         * SqlSession openSession();
         * //创建一个SqlSession,autoCommit：指定是否自动提交事务
         * SqlSession openSession(boolean autoCommit);
         *
         *
         *
         * SqlSession接口中很多方法，直接用来操作db，方法清单如下，大家眼熟一下：
         *
         * <T> T selectOne(String statement);
         * <T> T selectOne(String statement, Object parameter);
         * <E> List<E> selectList(String statement);
         * <E> List<E> selectList(String statement, Object parameter);
         * <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds);
         * <K, V> Map<K, V> selectMap(String statement, String mapKey);
         * <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey);
         * <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds);
         * <T> Cursor<T> selectCursor(String statement);
         * <T> Cursor<T> selectCursor(String statement, Object parameter);
         * <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds);
         * void select(String statement, Object parameter, ResultHandler handler);
         * void select(String statement, ResultHandler handler);
         * void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler);
         * int insert(String statement);
         * int insert(String statement, Object parameter);
         * int update(String statement);
         * int update(String statement, Object parameter);
         * int delete(String statement);
         * int delete(String statement, Object parameter);
         * void commit();
         * void commit(boolean force);
         * void rollback();
         * void rollback(boolean force);
         * List<BatchResult> flushStatements();
         * void close();
         * void clearCache();
         * Configuration getConfiguration();
         * <T> T getMapper(Class<T> type);
         * Connection getConnection();
         *
         * 上面以select开头的可以对db进行查询操作，insert相关的可以对db进行插入操作，update相关的可以对db进行更新操作。
         */
    
    
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
    
        log.info("-----{}", sqlSession);
        
 //=======================================================================================================
    
        /**
         * 使用SqlSesion执行sql操作:
         *
         * SqlSession常见的用法:
         * SqlSession相当于一个连接，可以使用这个对象对db执行增删改查操作，操作完毕之后需要关闭，使用步骤：
         *
         * 1.获取SqlSession对象：通过该sqlSessionFactory.openSession方法获取SqlSession对象
         * 2.对db进行操作：使用SqlSession对象进行db操作
         * 3.关闭SqlSession对象：sqlSession.close();
         *
         *
         * 常见的使用方式如下：
         *
         * //获取SqlSession
         * SqlSession sqlSession = this.sqlSessionFactory.openSession();
         * try {
         *     //执行业务操作，如：增删改查
         * } finally {
         *     //关闭SqlSession
         *     sqlSession.close();
         * }
         *
         * 上面我们将SqlSession的关闭放在finally块中，确保close()一定会执行。更简单的方式是使用java中的try()的方式，如下：
         *
         * try (SqlSession sqlSession = this.sqlSessionFactory.openSession();) {
         *     //执行业务操作，如：增删改查
         * }
         */
    
        try (SqlSession sqlSession2 = sqlSessionFactory.openSession(false);) {
            //创建UserModel对象
            UserModel_0729 userModel0729 = UserModel_0729.builder().id(RandomUtils.nextLong()).name("javacode2018").age(30).salary(50000D).sex(1).build();
            //执行插入操作
            int result = sqlSession.insert("com.security.study.myBatis.demo1.mapper.UserMapper.insertUser", userModel0729);
            log.info("插入影响行数：{}", result);
            //提交事务
            sqlSession2.commit();//这里使用的sqlSession2默认是不会自动提交事务，要想自动提交事务，上面的方法改成true。这里就不用手动commit了。
        }
    
        /**
         * 上面是insert操作，修改查询操作是是同样的道理，这里就演示了。
         */
    
        /**
         * Mapper接口的使用
         * 为什么需要Mapper接口
         * 上面我们讲解了对一个表的增删改查操作，都是通过调用SqlSession中的方法来完成的，大家再来看一下SqlSession接口中刚才用到的几个方法的定义：
         *
         * int insert(String statement, Object parameter);
         * int update(String statement, Object parameter);
         * int delete(String statement, Object parameter);
         * <E> List<E> selectList(String statement);
         * 这些方法的特点我们来看一下：
         *
         * 调用这些方法，需要明确知道statement的值，statement的值为namespace.具体操作的id，这些需要打开Mapper xml中去查看了才知道，写起来不方便
         * parameter参数都是Object类型的，我们根本不知道这个操作具体类型是什么，需要查看Mapper xml才知道，随便传递个值，可能类型不匹配，但是只有在运行的时候才知道有问题
         * selectList方法返回的是一个泛型类型的，通过这个方法我们根本不知道返回的结果的具体类型，也需要去查看Mapper xml才知道
         * 以上这几点使用都不是太方便，有什么方法能解决上面这些问题么？
         *
         * 有，这就是mybatis中的Mapper接口，我们可以定义一个interface，然后和Mapper xml关联起来，
         * Mapper xml中的操作和Mapper接口中的方法会进行绑定，当我们调用Mapper接口的方法的时候，会间接调用到Mapper xml中的操作，
         * 接口的完整类名需要和Mapper xml中的namespace一致。
         *
         *
         *
         *
         * Mapper接口的用法（三步）:
         * 步骤1：定义UserMapper接口,这个不多说了，工作每天都在写。
         * 步骤2：通过SqlSession获取Mapper接口对象:
         * SqlSession中有个getMapper方法，可以传入接口的类型，获取具体的Mapper接口对象，如下：
         *   <T > T getMapper(Class < T > type);
         *
         *   UserMapper mapper = sqlSession.getMapper(UserMapper.class);
         *
         * 步骤3：调用Mapper接口的方法对db进行操作:
         */
        try (SqlSession sqlSession3 = sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession3.getMapper(UserMapper.class);
            //创建UserModel对象
            UserModel_0729 userModel = UserModel_0729.builder().id(System.currentTimeMillis()).name("路人甲Java").age(30).salary(50000D).sex(1).build();
            //执行插入操作
            int insert = mapper.insertUser(userModel);
            log.info("影响行数：{}", insert);
        }
    
        /**
         * Mapper接口使用时注意的几点:
         * Mapper接口的完整类名必须和对应的Mapper xml中的namespace的值一致
         * Mapper接口中方法的名称需要和Mapper xml中具体操作的id值一致
         * Mapper接口中方法的参数、返回值可以 【不】 和Mapper xml中的一致
         */
    
    
        /**
         * Mapper接口的原理
         * 这个使用java中的动态代理实现的，mybatis启动的时候会加载全局配置文件mybatis-config.xml，
         * 然后解析这个文件中的mapper标签指定的UserMapper.xml，会根据UserMapper.xml的namespace的值创建这个接口的一个动态代理，
         * 具体可以去看一下mybatis的源码，主要使用java中的Proxy实现的，使用java.lang.reflect.Proxy类中的newProxyInstance方法，
         * 我们可以创建任意一个接口的一个代理对象：
         * public static Object newProxyInstance(ClassLoader loader,
         *                                           Class<?>[] interfaces,
         *                                           InvocationHandler h)
         *
         *
         * 我们使用Proxy来模仿Mapper接口的实现：
         */
    
        try (SqlSession sqlSession4 = sqlSessionFactory.openSession(true);) {
            UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(
                    Test0729.class.getClassLoader(),
                    new Class[]{UserMapper.class},
                    new UserMapperProxy(sqlSession4, UserMapper.class));
            
            log.info("{}", userMapper.getUserList());
            log.info("{}", userMapper.getUserList());
        }
    
        /**
         * 上面代码中：UserMapper是没有实现类的，可以通过Proxy.newProxyInstance给UserMapper接口创建一个代理对象，
         * 当调用UserMapper接口的方法的时候，会调用到UserMapperProxy对象的invoke方法。
         *
         * 打印：
         * 28:15.995 [main] DEBUG com.mdl.myBatis.demo1.Test0729 - invoke start
         * 28:15.999 [main] DEBUG c.m.m.d.m.UserMapper.getUserList - ==>  Preparing: SELECT * FROM t_user
         * 28:16.000 [main] DEBUG c.m.m.d.m.UserMapper.getUserList - ==> Parameters:
         * 28:16.086 [main] DEBUG c.m.m.d.m.UserMapper.getUserList - <==      Total: 8
         * 28:16.090 [main] DEBUG com.mdl.myBatis.demo1.Test0729 - invoke end
         * 28:16.091 [main] INFO  com.mdl.myBatis.demo1.Test0729 - [UserModel_0729(id=2, name=javacode2018, age=30, salary=50000.0, sex=1), UserModel_0729(id=3, name=javacode2018, age=30, salary=50000.0, sex=1), UserModel_0729(id=1690619059456, name=路人甲Java, age=30, salary=50000.0, sex=1), UserModel_0729(id=1690619169681, name=路人甲Java, age=30, salary=50000.0, sex=1), UserModel_0729(id=1690619295749, name=路人甲Java, age=30, salary=50000.0, sex=1), UserModel_0729(id=2641431413531887616, name=javacode2018, age=30, salary=50000.0, sex=1), UserModel_0729(id=7133115263137942528, name=javacode2018, age=30, salary=50000.0, sex=1), UserModel_0729(id=7798554412310389760, name=javacode2018, age=30, salary=50000.0, sex=1)]
         * 28:16.091 [main] DEBUG com.mdl.myBatis.demo1.Test0729 - invoke start
         * 28:16.091 [main] DEBUG com.mdl.myBatis.demo1.Test0729 - invoke end
         * 28:16.091 [main] INFO  com.mdl.myBatis.demo1.Test0729 - [UserModel_0729(id=2, name=javacode2018, age=30, salary=50000.0, sex=1), UserModel_0729(id=3, name=javacode2018, age=30, salary=50000.0, sex=1), UserModel_0729(id=1690619059456, name=路人甲Java, age=30, salary=50000.0, sex=1), UserModel_0729(id=1690619169681, name=路人甲Java, age=30, salary=50000.0, sex=1), UserModel_0729(id=1690619295749, name=路人甲Java, age=30, salary=50000.0, sex=1), UserModel_0729(id=2641431413531887616, name=javacode2018, age=30, salary=50000.0, sex=1), UserModel_0729(id=7133115263137942528, name=javacode2018, age=30, salary=50000.0, sex=1), UserModel_0729(id=7798554412310389760, name=javacode2018, age=30, salary=50000.0, sex=1)]
         *
         * 注意上面输出的invoke start和invoke end，可以看到我们调用userMapper.getUserList时候，被UserMapperProxy#invoke方法处理了。
         */
    
        
        /**
         * Mybatis中创建Mapper接口代理对象使用的是下面这个类，大家可以去研究一下：
         * org.apache.ibatis.binding.MapperProxyFactory
         */
    }
    
    
    public static class UserMapperProxy implements InvocationHandler {
        private SqlSession sqlSession;
        private Class<?> mapperClass;
        public UserMapperProxy(SqlSession sqlSession, Class<?> mapperClass) {
            this.sqlSession = sqlSession;
            this.mapperClass = mapperClass;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.debug("invoke start");
            String statement = mapperClass.getName() + "." + method.getName();
            List<Object> result = sqlSession.selectList(statement);
            log.debug("invoke end");
            return result;
        }
    }
    
}
