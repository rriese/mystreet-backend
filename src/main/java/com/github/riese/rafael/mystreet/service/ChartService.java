package com.github.riese.rafael.mystreet.service;

import com.github.riese.rafael.mystreet.model.Chart;
import com.github.riese.rafael.mystreet.model.Claim;
import com.github.riese.rafael.mystreet.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChartService {
    @Resource
    private ClaimService claimService;
    @Resource
    UserService userService;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<Chart> getClaimsPerState() {
        Chart chart = new Chart();
        chart.setTitle("Reclamações por estado");

        List<Claim> claims = claimService.findAll().getBody();
        for (Claim claim : claims) {
            var data = chart.getData();

            if (data.get(claim.getState()) == null) {
                data.put(claim.getState(), 1D);
            } else {
                data.put(claim.getState(), data.get(claim.getState()) + 1);
            }
        }
        return ResponseEntity.ok().body(chart);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<Chart> getClaimsPerCity() {
        Chart chart = new Chart();
        chart.setTitle("Reclamações por cidade");

        List<Claim> claims = claimService.findAll().getBody();
        for (Claim claim : claims) {
            var data = chart.getData();

            if (data.get(claim.getCity()) == null) {
                data.put(claim.getCity(), 1D);
            } else {
                data.put(claim.getCity(), data.get(claim.getCity()) + 1);
            }
        }
        return ResponseEntity.ok().body(chart);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<Chart> getUsersPerProfile() {
        Chart chart = new Chart();
        chart.setTitle("Usuários por perfil");

        List<User> users = userService.findAll().getBody();
        for (User user : users) {
            var data = chart.getData();
            String profileName = this.getConvertedProfileName(user.getProfile().getName());

            if (data.get(profileName) == null) {
                data.put(profileName, 1D);
            } else {
                data.put(profileName, data.get(profileName) + 1);
            }
        }
        return ResponseEntity.ok().body(chart);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<Chart> getClaimsPerStatus() {
        Chart chart = new Chart();
        chart.setTitle("Reclamações por status");

        List<Claim> claims = claimService.findAll().getBody();
        for (Claim claim : claims) {
            var data = chart.getData();
            String statusName = claim.getStatus().getName();

            if (data.get(statusName) == null) {
                data.put(statusName, 1D);
            } else {
                data.put(statusName, data.get(statusName) + 1);
            }
        }
        return ResponseEntity.ok().body(chart);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ResponseEntity<Chart> getTopTenUsersClaim() {
        Chart chart = new Chart();
        chart.setTitle("Top 10 usuários que mais abriram reclamações");

        List<Claim> claims = claimService.findAll().getBody();
        for (Claim claim : claims) {
            var data = chart.getData();
            String userName = claim.getUser().getName();

            if (data.get(userName) == null) {
                data.put(userName, 1D);
            } else {
                data.put(userName, data.get(userName) + 1);
            }
        }
        chart.setData(chart.getData().entrySet().
                stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                    collect(java.util.stream.Collectors.toMap(Map.Entry::getKey,
                            Map.Entry::getValue, (oldValue, newValue) ->
                                    oldValue, java.util.LinkedHashMap::new)));

        Map<String, Double> newMap = new HashMap<>();
        for (Map.Entry<String, Double> entry : chart.getData().entrySet()) {
            if (newMap.size() <= 10) {
                newMap.put(entry.getKey(), entry.getValue());
            } else {
                break;
            }
        }
        chart.setData(newMap);

        return ResponseEntity.ok().body(chart);
    }

    private String getConvertedProfileName(String profileName) {
        String result = profileName;
        if (profileName.equals("ROLE_ADMIN")) {
            result = "Administrador";
        } else if (profileName.equals("ROLE_VISITOR")) {
            result = "Visitante";
        } else if (profileName.equals("ROLE_CITY_HALL")) {
            result = "Prefeitura";
        }
        return result;
    }

}
