package mapper;


import dto.CarDTO;
import dto.CarModelDTO;
import dto.DealerCenterDTO;
import entity.CarEntity;
import entity.CarModelEntity;
import entity.DealershipEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import dto.CarDTO;
import dto.CarModelDTO;
import dto.DealershipDTO;
import entity.CarEntity;
import entity.CarModelEntity;
import entity.DealershipEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mappings({
            @Mapping(source = "carModelId", target = "carModel.id"),
            @Mapping(source = "dealershipName", target = "dealership.name"),
            @Mapping(target = "dealership.cars", ignore = true)
    })
    CarDTO carEntityToCarDTO(CarEntity carEntity);

    @Mappings({
            @Mapping(source = "carModel.id", target = "carModelId"),
            @Mapping(source = "dealership.name", target = "dealershipName")
    })
    CarEntity carDTOToCarEntity(CarDTO carDTO);

    CarModelDTO carModelEntityToCarModelDTO(CarModelEntity carModelEntity);

    CarModelEntity carModelDTOToCarModelEntity(CarModelDTO carModelDTO);

    DealershipDTO dealershipEntityToDealershipDTO(DealershipEntity dealershipEntity);

    DealershipEntity dealershipDTOToDealershipEntity(DealershipDTO dealershipDTO);
}