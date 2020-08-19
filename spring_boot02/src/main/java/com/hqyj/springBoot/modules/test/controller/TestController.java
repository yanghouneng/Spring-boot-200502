package com.hqyj.springBoot.modules.test.controller;

import com.hqyj.springBoot.modules.test.entity.City;
import com.hqyj.springBoot.modules.test.entity.Country;
import com.hqyj.springBoot.modules.test.service.CityService;
import com.hqyj.springBoot.modules.test.service.CountryServcie;
import com.hqyj.springBoot.modules.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TestController
 * @Author HymanHu
 * @Date 2020/8/10 10:39
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CountryServcie countryServcie;

    /*
    * 127.0.0.1/test/indexPage
    * */
    @GetMapping("/indexSimple")
    public String indexPageTest(){
        return "indexSimple";
    }

    /*
    * 127.0.0.1/test/down
    * */
    //下载文件
    @GetMapping("/down")
    public ResponseEntity<Resource> fileDownLoad(String fileName){
        Resource resource = null;
        try {
            resource = new UrlResource(
                    Paths.get("D:\\upload\\" + fileName).toUri());//获取文件下找的path路径
            if (resource.exists()&&resource.isReadable()){
                //HttpHeaders.CONTENT_TYPE包装了content-type，  HttpHeaders.CONTENT_DISPOSITION对下载的一个描述
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/octet-stream")
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                String.format("attachment; filename=\"%s\"", resource.getFilename()))
                        .body(resource);//body()//传输给页面的文件内容    filename="%s"下载内容的名称
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
     */
    @RequestMapping("/download1")
    public void downloadFile1(HttpServletRequest request,
                              HttpServletResponse response, @RequestParam String fileName) {
        String filePath = "D:/upload" + File.separator + fileName;
        File downloadFile = new File(filePath);

        if (downloadFile.exists()) {
            response.setContentType("application/octet-stream");
            response.setContentLength((int)downloadFile.length());
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    String.format("attachment; filename=\"%s\"", fileName));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(downloadFile);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                LOGGER.debug(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                } catch (Exception e2) {
                    LOGGER.debug(e2.getMessage());
                    e2.printStackTrace();
                }
            }
        }
    }

    /*
    * 127.0.0.1/test/files
    * */
    //上传多个文件
    @PostMapping(value = "/files",consumes = "multipart/form-data")
    public String filesUpload(@RequestParam MultipartFile[] files, RedirectAttributes redirectAttributes) {
        boolean empty = true;//定义是否为真
        try {
            for (MultipartFile file : files) { //遍历
                if (file.isEmpty()) {   //如果上传的文件为空
                    continue;
                }

                String destFilePath = "D:\\upload\\" + file.getOriginalFilename();
                File destFile = new File(destFilePath);
                file.transferTo(destFile); //转存到目标文件中
                empty = false;
            }

            if (empty) {
                redirectAttributes.addFlashAttribute(
                        "messages", "上传失败");
            } else {
                redirectAttributes.addFlashAttribute(
                        "messages", "上传成功");
            }

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute(
                    "messages", "上传失败");
        }

        return "redirect:/test/index";
    }

    /*
    * 127.0.0.1/test/file
    * */
    //单个文件上传
    @PostMapping(value = "/file",consumes = "multipart/form-data")
    public String fileUpload(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
        //如果上传的文件为null
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","上传失败");
            return "redirect:/test/index";
        }
        try {
            //定义文件所在的位置
            String destPath = "D:\\upload\\" + file.getOriginalFilename();
            System.err.println(destPath);
            File file1 = new File(destPath);
            file.transferTo(file1);  //转存到目标文件中

            // 使用工具类Files来上传文件
//			byte[] bytes = file.getBytes();
//			Path path = Paths.get(destFileName);
//			Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message","上传成功");

        }catch (IOException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","上传失败");
        }
        return "redirect:/test/index";
    }



    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Value("${server.port}")
    private int port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;

    @Autowired
    private ApplicationTest applicationTest;

    /*
    * 127.0.0.1/test/index
    * */
   @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap){
       int countryId = 522;
       List<City> cities = cityService.getCitiesByCountryId(countryId);
       cities = cities.stream().limit(10).collect(Collectors.toList());
       Country country = countryServcie.getCountryByCountryId(countryId);

       modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
       modelMap.addAttribute("checked", true);
       modelMap.addAttribute("currentNumber", 99);
       modelMap.addAttribute("changeType", "checkbox");
       modelMap.addAttribute("baiduUrl", "/test/log");
       modelMap.addAttribute("city", cities.get(0));
       modelMap.addAttribute("shopLogo",
               "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
       modelMap.addAttribute("shopLogo",
               "/upload/111.png");
       modelMap.addAttribute("country", country);
       modelMap.addAttribute("cities", cities);
       modelMap.addAttribute("updateCityUri", "/api/updateCity");
       modelMap.addAttribute("template", "test/index");
       //返回外部碎片的组装器
        return "index";
    }

    /*
    * 127.0.0.1/test/index2
    * */
    @GetMapping("index2")
    public String testIndex2Page(ModelMap modelMap){
      modelMap.addAttribute("template","test/index2");
       return "index";
    }

    /**
     * 127.0.0.1:8085/test/logTest ---- get
     */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test11111";
    }

    /**
     * 127.0.0.1:8085/test/config ---- get
     */
    @GetMapping("/config")
    @ResponseBody
    public String configTest() {
        StringBuffer sb = new StringBuffer();
        sb.append(port).append("----")
            .append(name).append("----")
            .append(age).append("----")
            .append(desc).append("----")
            .append(random).append("----").append("<br>");
        sb.append(applicationTest.getPort()).append("----")
            .append(applicationTest.getName()).append("----")
            .append(applicationTest.getAge()).append("----")
            .append(applicationTest.getDesc()).append("----")
            .append(applicationTest.getRandom()).append("----").append("<br>");

        return sb.toString();

    }

    /**
     * 127.0.0.1/test/testDesc?paramKey=fuck ---- get
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request, @RequestParam(value = "paramKey") String paramValue) {
        String paramValue2 = request.getParameter("paramKey");
        return "This is test module desc."+paramValue+"=="+paramValue2;
    }

}
