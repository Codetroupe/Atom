package com.trevonn.molecule.delegates;

/**
 * Created by Jeffrey on 2018/3/13
 * @function 给外部继承类
 */

public abstract class AtomDelegate extends PermissionCheckerDelegate{

    @SuppressWarnings("unchecked")
    public <T extends AtomDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }


}
