package Service;

import dto.DealershipDTO;
import entity.DealershipEntity;
import mapper.CarMapper;
import repository.DealershipRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DealershipService {

    private final DealershipRepository dealershipRepository;
    private final CarMapper carMapper;

    public DealershipService(DealershipRepository repository, CarMapper mapper) {
        this.dealershipRepository = repository;
        this.carMapper = mapper;
    }

    public List<DealershipDTO> fetchAllDealerships() {
        List<DealershipEntity> entities = dealershipRepository.findAll();
        return entities.stream()
                .map(carMapper::dealershipEntityToDealershipDTO)
                .collect(Collectors.toList());
    }
}
