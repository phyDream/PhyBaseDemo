package com.demo.phy.phybasedemo.aidl.binder;

import android.os.IBinder;
import android.os.RemoteException;

import com.demo.phy.phybasedemo.data.bean.IBinderPool;


public class BinderPoolImpl extends IBinderPool.Stub{

    public static final int BINDER_CODE_BOOK = 1;
    public static final int BINDER_CODE_ACCOUNT = 2;

    BinderBookImpl binderBook=null;
    BinderAccountImpl binderAccount=null;

    @Override
    public IBinder queryBinder(int binderCode) throws RemoteException {
        IBinder binder=null;

        switch (binderCode) {
            case BINDER_CODE_BOOK:
                binder = new BinderBookImpl();
                binderBook = (BinderBookImpl) binder;
                break;
            case BINDER_CODE_ACCOUNT:
                binder = new BinderAccountImpl();
                binderAccount =(BinderAccountImpl) binder;
                break;
            default:
                break;
        }

        return binder;
    }

    public BinderBookImpl getBinderBook(){
        return binderBook;
    }

    public BinderAccountImpl getBinderAccount(){
        return binderAccount;
    }
}
