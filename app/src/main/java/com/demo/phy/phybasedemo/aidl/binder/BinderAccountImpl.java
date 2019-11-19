package com.demo.phy.phybasedemo.aidl.binder;

import android.os.RemoteException;

import com.demo.phy.phybasedemo.data.bean.IAccount;
import com.demo.phy.phybasedemo.data.bean.User;

/**
 * Created by phy on 2019/11/19
 */
public class BinderAccountImpl extends IAccount.Stub {
    @Override
    public void setUser(User user) throws RemoteException {

    }

    @Override
    public User getUser() throws RemoteException {
        return null;
    }

    @Override
    public void setPasswrod(String password) throws RemoteException {

    }

    @Override
    public String getPasswrod() throws RemoteException {
        return null;
    }

    @Override
    public void setId(String id) throws RemoteException {

    }

    @Override
    public String getId() throws RemoteException {
        return null;
    }
}
