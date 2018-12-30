package io.pingzi.telecheck.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.pingzi.telecheck.domain.Analysis;
import io.pingzi.telecheck.domain.JxSendSmsTest;
import io.pingzi.telecheck.domain.UserInfo;
import io.pingzi.telecheck.repository.UserInfoRepository;
import io.pingzi.telecheck.service.UserInfoService;
import io.pingzi.telecheck.web.rest.errors.BadRequestAlertException;
import io.pingzi.telecheck.web.rest.util.HeaderUtil;
import io.pingzi.telecheck.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.poi.hssf.usermodel.*;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.*;

/**
 * REST controller for managing UserInfo.
 */
@RestController
@RequestMapping("/api")
public class UserInfoResource {

    private final Logger log = LoggerFactory.getLogger(UserInfoResource.class);

    private static final String ENTITY_NAME = "userInfo";
    @Autowired
    private UserInfoRepository userinforepository;
    private final UserInfoService userInfoService;

    public UserInfoResource(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * POST  /user-infos : Create a new userInfo.
     *
     * @param userInfo the userInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userInfo, or with status 400 (Bad Request) if the userInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-infos")
    @Timed
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo) throws URISyntaxException {
        log.debug("REST request to save UserInfo : {}", userInfo);
        if (userInfo.getId() != null) {
            throw new BadRequestAlertException("A new userInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserInfo result = userInfoService.save(userInfo);
        return ResponseEntity.created(new URI("/api/user-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-infos : Updates an existing userInfo.
     *
     * @param userInfo the userInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userInfo,
     * or with status 400 (Bad Request) if the userInfo is not valid,
     * or with status 500 (Internal Server Error) if the userInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-infos")
    @Timed
    public ResponseEntity<UserInfo> updateUserInfo(@RequestBody UserInfo userInfo) throws URISyntaxException {
        log.debug("REST request to update UserInfo : {}", userInfo);
        if (userInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserInfo result = userInfoService.save(userInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-infos : get all the userInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userInfos in body
     */
    @GetMapping("/user-infos")
    @Timed
    public ResponseEntity<List<UserInfo>> getAllUserInfos(Pageable pageable) {
        log.debug("REST request to get a page of UserInfos");
        Page<UserInfo> page = userInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-infos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /user-infos/:id : get the "id" userInfo.
     *
     * @param id the id of the userInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userInfo, or with status 404 (Not Found)
     */
    @GetMapping("/user-infos/{id}")
    @Timed
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable Long id) {
        log.debug("REST request to get UserInfo : {}", id);
        Optional<UserInfo> userInfo = userInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userInfo);
    }

    /**
     * DELETE  /user-infos/:id : delete the "id" userInfo.
     *
     * @param id the id of the userInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserInfo(@PathVariable Long id) {
        log.debug("REST request to delete UserInfo : {}", id);
        userInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/user-infos/getPhone")
    @Timed
    public List getPhoe(@RequestParam(value = "phone") String phone) throws DocumentException{
        System.out.println(phone);
        List<UserInfo> lists = userinforepository.findByPhone(phone);
        System.out.println(lists.size());
        if (!lists.isEmpty()) {
            return lists;
        } else {
            JxSendSmsTest jxSendSmsTest = new JxSendSmsTest();
            Analysis analysis = new Analysis();
            Map map = analysis.parse(jxSendSmsTest.sendSms(phone));
            if (!map.isEmpty()) {
                System.out.println(map.get("UserId").toString());
                List<UserInfo> list = new ArrayList<>();
                UserInfo userInfo = new UserInfo();
                userInfo.setUserid(Integer.parseInt(map.get("UserId").toString()));
                userInfo.setUsername(map.get("Username").toString());
                userInfo.setPhone(map.get("Phone").toString());
                userInfo.setIsregister(map.get("IsRegister").toString());
                userInfo.setStatus(map.get("Status").toString());
                userInfo.setFirstname(map.get("Firstname").toString());
                userInfo.setLastname(map.get("Lastname").toString());
                userInfo.setLogintime(map.get("LoginTime").toString());
                userInfo.setIsimage(map.get("IsImage").toString());
                userInfo.setRemark(map.get("Remark").toString());
                String base64= map.get("Portrait").toString();
                Base64.getDecoder().decode(base64);
                userInfo.setPortrait( Base64.getDecoder().decode(base64));
                userInfo.setPortraitContentType("image/jpeg");
                userinforepository.save(userInfo);
                list.add(userInfo);
                return list;
            } else {
                return Collections.emptyList();
            }
        }
    }
    @GetMapping(value = "/user-infos/UserExcelDownloads")
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
       HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        List<UserInfo> classmateList = userinforepository.findAll();
        String fileName = "Employee"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "用户ID", "用户号码", "是否注册", "在线状态","上线时间","用户名","姓","名","查看头像","备注","头像"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (UserInfo teacher : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(teacher.getUserid());
            row1.createCell(1).setCellValue(teacher.getPhone());
            row1.createCell(2).setCellValue(teacher.getIsregister());
            row1.createCell(3).setCellValue(teacher.getStatus());
            row1.createCell(4).setCellValue(teacher.getLogintime());
            row1.createCell(5).setCellValue(teacher.getUsername());
            row1.createCell(6).setCellValue(teacher.getFirstname());
            row1.createCell(7).setCellValue(teacher.getLastname());
            row1.createCell(8).setCellValue(teacher.getIsimage());
            row1.createCell(9).setCellValue(teacher.getRemark());
            row1.createCell(10).setCellValue(teacher.getPortrait().toString());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
