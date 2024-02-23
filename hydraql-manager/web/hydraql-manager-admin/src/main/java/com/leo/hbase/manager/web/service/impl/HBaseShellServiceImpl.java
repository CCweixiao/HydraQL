package com.leo.hbase.manager.web.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.github.CCweixiao.hbase.sdk.common.util.StringUtil;
import com.github.CCweixiao.hbase.sdk.shell.HBaseShellCommands;
import com.github.CCweixiao.hbase.sdk.shell.HBaseShellSession;
import com.github.CCweixiao.hbase.sdk.shell.Result;
import com.leo.hbase.manager.system.dto.HBaseShellCommand;
import com.leo.hbase.manager.system.dto.HBaseShellCommandModel;
import com.leo.hbase.manager.web.hds.HBaseClusterDSConfig;
import com.leo.hbase.manager.web.service.IHBaseShellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author leojie 2023/7/10 22:12
 */
@Service
public class HBaseShellServiceImpl implements IHBaseShellService {
    @Autowired
    private HBaseClusterDSConfig hBaseClusterDSConfig;

    @Override
    public Result execute(HBaseShellCommand command) {
        String clusterId = command.getClusterId();
        if (StringUtil.isBlank(clusterId)) {
            return Result.failed("接收命令的集群ID不能为空～");
        }
        String commandContent = command.getCommand();
        if (StringUtil.isBlank(commandContent)) {
            return Result.failed("待执行命令不能为空～");
        }

        HBaseShellSession hBaseShellSession = hBaseClusterDSConfig.getHBaseShellSession(clusterId);
        if (!hBaseShellSession.isConnected()) {
            return Result.failed(String.format("与集群[%s]的连接已断开～", clusterId));
        }
        return hBaseShellSession.execute(commandContent);
    }

    @Override
    public  Map<String, Map<String, List<String>>> getAllCommands() {
        try {
            Set<String> allCommands = HBaseShellCommands.getAllCommands();
            return HBaseShellCommandModel.generateHBaseShellCommand(new ArrayList<>(allCommands));
        } catch (IOException e) {
            e.printStackTrace();
            return HBaseShellCommandModel.generateDefaultHBaseShellCommand();
        }
    }
}
