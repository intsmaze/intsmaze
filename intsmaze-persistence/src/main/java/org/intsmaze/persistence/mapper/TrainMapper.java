package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.Train;

public interface TrainMapper {
    
    public List selectAllByPage(Train Train);
    
    public int getAllTrainsCount(Train Train);
}