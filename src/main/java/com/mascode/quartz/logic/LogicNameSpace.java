package com.mascode.quartz.logic;

import com.mascode.quartz.structure.impl.NameSpaceAdapter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class LogicNameSpace extends NameSpaceAdapter {
    @Override
    protected String getNameSpace() {
        return "logic-";
    }
}
