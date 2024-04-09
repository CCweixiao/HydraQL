package com.hydraql.adapter.executor;

import com.hydraql.adapter.context.ConnectionContext;
import com.hydraql.common.callback.AdminCallback;
import com.hydraql.common.exception.HydraQLAdminOpException;
import org.apache.hadoop.hbase.client.Admin;

/**
 * @author leojie 2024/4/7 20:43
 */
public interface AdminExecutor extends ConnectionContext {

    default <T> T execute(AdminCallback<T, Admin> action) {
        try (Admin admin = this.getConnection().getAdmin()) {
            return action.doInAdmin(admin);
        } catch (Throwable e) {
            throw new HydraQLAdminOpException(e);
        }
    }
}
