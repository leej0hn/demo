package io.communet.demo.web.controller;

import io.communet.demo.common.exception.ServiceException;
import io.communet.demo.common.model.TestModel;
import io.communet.demo.common.vo.Response;
import io.communet.demo.kafka.MsgProducer;
import io.communet.demo.service.TestService;
import io.communet.demo.web.configuration.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/01/11
 * <p>Version: 1.0
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private TestService testService;

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Value("${web.ips:}")
    private String ips;

    @Autowired
    private WebConfig config;

    @RequestMapping(value = "/api/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response index() {
        return Response.ok("test");
    }

    @RequestMapping(value = "/api/test/kafka", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response kafka() {
        msgProducer.send();
        return Response.ok("test kafka");
    }

    /**
     * 前端数据提交 Content-Type:application/json;
     * body raw : {"name":"1","age":18}
     * @param model
     * @return
     */
    @RequestMapping(value="/api/testjson", method=RequestMethod.POST, consumes = "application/json")
    public Response test(@RequestBody TestModel model){
        log.info(model.toString());
        return Response.ok("testjson");
    }

    @GetMapping("/page/test")
    public ModelAndView testPage() {
        String test = "Test Page , hello world ";
        return new ModelAndView("testPage","test",test);
    }

    @GetMapping("/api/exception")
    public Response testExcption(){
        throw new ServiceException("testException msg");
    }

    @PostMapping("/api/file/upload")
    public Response<String> fileUpload(@RequestParam("uploadFile") MultipartFile uploadFile) throws Exception{
        if (uploadFile.isEmpty()) {
            throw new ServiceException("uploadFile is null");
        }
        String fileName = uploadFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String imageName = UUID.randomUUID().toString() + suffixName;
        Files.copy(uploadFile.getInputStream(), Paths.get(config.getFileUploadPath(), imageName));
        return Response.ok(imageName);
    }

    @GetMapping("/api/file/download/{id:.+}")
    @ResponseBody
    public ResponseEntity<?> fileDownload(@PathVariable String id) {
        return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(config.getFileUploadPath(), id).toString()));
    }

    @GetMapping("/api/write/redis")
    public Response<Boolean> testWriteRedis(){
        stringRedisTemplate.opsForValue().set("test", "test123");
        return Response.ok(true);
    }

    @GetMapping("/api/read/redis")
    public Response<String> testReadRedis(){
        String value = stringRedisTemplate.opsForValue().get("test");
        System.out.println("test : " + value);
        Set<String> obj = (Set)redisTemplate.opsForValue().get("ccrm:compid_userid_appid:10_136_109");
        if( obj != null ){
            System.out.println(obj.size());
        }
        System.out.println("stringRedisTemplate hashcode : " + stringRedisTemplate.hashCode() );
        System.out.println("redisTemplate hashcode : " + redisTemplate.hashCode() );
        return Response.ok(value);
    }
}
