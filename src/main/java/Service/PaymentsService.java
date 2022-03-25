package Service;

import Dto.PaymentsDTO;
import Dto.PaymentsFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PaymentsService {

    PaymentsDTO save(PaymentsFormDTO body);

    PaymentsDTO search(Long id);

    Page<PaymentsDTO> findAll(Pageable page);

    PaymentsDTO update(Long id, PaymentsFormDTO body);

    void delete(Long id);

}
