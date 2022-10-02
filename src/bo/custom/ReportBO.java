package bo.custom;

import bo.SuperBO;
import entity.CustomEntity;

import java.io.IOException;
import java.util.List;

public interface ReportBO extends SuperBO {
    List<CustomEntity> getAllDetails(String id) throws Exception;
}
