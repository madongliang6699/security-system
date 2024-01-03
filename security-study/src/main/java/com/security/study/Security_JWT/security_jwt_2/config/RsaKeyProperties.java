package com.security.study.Security_JWT.security_jwt_2.config;


import com.security.study.Security_JWT.security_jwt_2.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@ConfigurationProperties("rsa.key") //从配置文件中获取文件路径
public class RsaKeyProperties {

    /**
     *  存储RSA公钥的文件路径 (使用于从文件中获取公钥的方式)
     */
    private String pubKeyPath;
    /**
     * 存储RSA私钥的文件路径 (使用于从文件中获取公钥的方式)
     */
    private String priKeyPath;

    /**
     * RSA公钥 (直接可以使用)
     */
    private static final String TOKEN_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCU0HErFIuj54Y1X4vDcGLvxptPgv07gXFhCrwM270frVWcPIGpBBRujUKggVR+eLN1nwvWqPvnAkF1x6iwSAihjyqHiVTRFnKGYaDub7Et05ZmsRUToIXKdYQHplbVTOw3e8ujpdD0zSvRaGS7OXBs8iWPWY/h032JoFRld0ZO6QIDAQAB";
    /**
     * RSA私钥 (直接可以使用)
     */
    private static final String TOKEN_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJTQcSsUi6PnhjVfi8NwYu/Gm0+C/TuBcWEKvAzbvR+tVZw8gakEFG6NQqCBVH54s3WfC9ao++cCQXXHqLBICKGPKoeJVNEWcoZhoO5vsS3TlmaxFROghcp1hAemVtVM7Dd7y6Ol0PTNK9FoZLs5cGzyJY9Zj+HTfYmgVGV3Rk7pAgMBAAECgYBjA3FYvZnPm1tjpcfjaKKbcOSjeb8t2YlrjO3kDEyAiB7fDi8jGrDRgbGkA4kJgRu+le0VjWfVo12UlRy69aKvf3XpciOxSfGuCMfKdM4MuJIrMMmKK2v+iXVfL1Ca1yyPPEK2Pcdxy1nOBa7qMTmUJHKRGrvno383hR0Xcnvk1QJBANN2slHWgQF3TYJNTayoe2qAEAr+v7F+niA1IpzIr6HakOAR1DeN7FZYWf72P5Q9eFrV4RdH5LDoD8lJPQzrvAsCQQC0J+4h7EcjCVL7vidgthWneNFwSbpMd9RxgzBSUHv7ugmEJI1BzzD09WFgI2mmdO4oCsPWqXR/5Yf/p6AdEsVbAkEAr0lj9Uye8U0olctoiKe8bgKrycFzuzje8Im7IEWGuN7JWsPMqyRc9RIVv6/18fambn1+MWMp4a7rbwnjrnM2EwJAA4eUvs1mR2VzXsNG+joXCoTvdYe8QqtGWkL7u2EgTLpEXXZp3hQ1HVeBZOTMuRopYFd1pssDIU5Z78RU+rzXaQJBAIq/Z5bmi39fpKcMe9+bgba7MUrLwBiqcvhPSY+hL8bOS4LoEyEFborOylOnafUR5R/VQQLpM2Q3sflPZVn5uF4=";


    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void createKey() throws Exception {

        //通过读取文件中密钥生成公钥私钥对象
        //this.publicKey = RsaUtils.getPublicKeyByFile(pubKeyPath);
        //this.privateKey = RsaUtils.getPrivateKeyByFile(priKeyPath);

        //通过已有密钥字符串生成公钥私钥对象
        this.publicKey = RsaUtils.getPublicKeyByStr(TOKEN_PUBLIC_KEY);
        this.privateKey = RsaUtils.getPrivateKeyByStr(TOKEN_PRIVATE_KEY);
    }

}
