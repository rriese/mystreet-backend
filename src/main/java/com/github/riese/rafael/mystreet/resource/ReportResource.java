package com.github.riese.rafael.mystreet.resource;

import com.github.riese.rafael.mystreet.cache.DocumentCache;
import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.model.User;
import com.github.riese.rafael.mystreet.service.ClaimService;
import com.github.riese.rafael.mystreet.service.UserService;
import com.github.riese.rafael.mystreet.util.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
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

    @GetMapping("/excel/users/generate")
    public String generateUsersToExcel() throws IOException {
        List<User> users = userService.findAll().getBody();
        ExcelUtil<User> excelUtil = new ExcelUtil<>(users);
        return excelUtil.generateExcelFile();
    }

    @GetMapping("/excel/claims/generate")
    public String generateClaimsToExcel() throws IOException {
        List<Claim> claims = claimService.findAll().getBody();
        ExcelUtil<Claim> excelUtil = new ExcelUtil<>(claims);
        return excelUtil.generateExcelFile();
    }

    @GetMapping("/export/{filename}")
    public void exportToExcel(@PathVariable String filename, HttpServletResponse response) throws IOException {
        var file = DocumentCache.getInstance().getFromCache(filename);

        if (file != null) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + UUID.randomUUID() + ".xlsx");

            ServletOutputStream outputStream = response.getOutputStream();
            file.write(outputStream);
            file.close();
            outputStream.close();
        } else {
            throw new RuntimeException("Documento não encontrado para download!");
        }
    }
}
