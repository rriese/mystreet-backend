package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.service.ClaimService;
import com.github.riese.rafael.mystreet.service.UserService;
import com.github.riese.rafael.mystreet.util.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/report")
public class ReportResource {
    @Resource
    private UserService userService;
    @Resource
    private ClaimService claimService;

    @GetMapping("/excel/users")
    public void exportUsersToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String uuid = UUID.randomUUID().toString();
        response.setHeader("Content-Disposition", "attachment; filename=users-" + uuid + ".xlsx");
        response.setContentType("application/vnd.ms-excel");
        List<User> users = userService.findAll().getBody();
        ExcelUtil<User> excelUtil = new ExcelUtil<>(users);
        excelUtil.generateExcelFile(response);
    }

    @GetMapping("/excel/claims")
    public void exportClaimsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String uuid = UUID.randomUUID().toString();
        response.setHeader("Content-Disposition", "attachment; filename=claims-" + uuid + ".xlsx");
        response.setContentType("application/vnd.ms-excel");
        List<Claim> claims = claimService.findAll().getBody();
        ExcelUtil<Claim> excelUtil = new ExcelUtil<>(claims);
        excelUtil.generateExcelFile(response);
    }
}
