package com.money.management.statistics.controller;

import com.money.management.statistics.domain.Account;
import com.money.management.statistics.domain.timeseries.DataPoint;
import com.money.management.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public List<DataPoint> getCurrentAccountStatistics(Principal principal) {
        return statisticsService.findByAccountName(principal.getName());
    }

    @PreAuthorize("#oauth2.hasScope('server') or #accountName.equals('demo')")
    @RequestMapping(value = "/{accountName}", method = RequestMethod.GET)
    public List<DataPoint> getStatisticsByAccountName(@PathVariable String accountName) {
        return statisticsService.findByAccountName(accountName);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(value = "/{accountName}", method = RequestMethod.PUT)
    public void saveAccountStatistics(@PathVariable String accountName, @Valid @RequestBody Account account) {
        statisticsService.save(accountName, account);
    }
}