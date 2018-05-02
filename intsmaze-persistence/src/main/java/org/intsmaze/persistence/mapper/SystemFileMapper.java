package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.SystemFile;

public interface SystemFileMapper {
    public List selectAllByPage(SystemFile systemFile);
    
    public int getAllTrainsCount(SystemFile systemFile);
}