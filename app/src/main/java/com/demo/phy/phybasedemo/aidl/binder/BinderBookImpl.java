package com.demo.phy.phybasedemo.aidl.binder;

import android.os.RemoteException;

import com.demo.phy.phybasedemo.data.bean.IBook;

/**
 * Created by phy on 2019/11/19
 */
public class BinderBookImpl extends IBook.Stub {

    String bookname = "无名";

    @Override
    public void setName(String s) throws RemoteException {
        bookname = s;
    }

    @Override
    public String getName() throws RemoteException {
        return bookname;
    }

}
