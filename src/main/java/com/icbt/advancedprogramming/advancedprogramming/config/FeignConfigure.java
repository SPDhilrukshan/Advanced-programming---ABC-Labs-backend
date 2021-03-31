//package com.icbt.advancedprogramming.advancedprogramming.config;
//
//import feign.Client;
//import feign.Feign;
//import feign.Logger;
//import feign.Request;
//import feign.hystrix.HystrixFeign;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.conn.ssl.TrustStrategy;
//import org.apache.http.ssl.SSLContexts;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocketFactory;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//
//@Configuration
//@EnableFeignClients
//@EnableCircuitBreaker
//@Slf4j
//public class FeignConfigure {
//     public static int connectTimeOutMillis = 50000;
//     public static int readTimeOutMillis = 50000;
//
//     @Bean
//     public Request.Options options() {
//          return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
//     }
//
//     @Bean
//     public Client feignClient()
//     {
//          Client trustSSLSockets = new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
//          log.info("feignClient called");
//          return trustSSLSockets;
//     }
//
//    @Bean
//    @Scope("prototype")
//    @ConditionalOnMissingBean
//    @ConditionalOnProperty(name = "feign.hystrix.enabled")
//    public Feign.Builder feignHystrixBuilder() {
//        return HystrixFeign.builder();
//    }
//
//     @Bean
//     Logger.Level feignLoggerLevel() {
//          return Logger.Level.FULL;
//     }
//
//     private SSLSocketFactory getSSLSocketFactory() {
//          try {
//               TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
//                    @Override
//                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                         return true;
//                    }
//               };
//
//               SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//               return sslContext.getSocketFactory();
//          } catch (Exception exception) {
//          }
//          return null;
//     }
//
//
//}