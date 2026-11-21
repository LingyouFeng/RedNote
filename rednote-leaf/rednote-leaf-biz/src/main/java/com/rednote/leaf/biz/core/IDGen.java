package com.rednote.leaf.biz.core;


import com.rednote.leaf.biz.core.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
