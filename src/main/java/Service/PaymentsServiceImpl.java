package Service;

import Dto.PaymentsDTO;
import Dto.PaymentsFormDTO;
import Entity.Payments;
import Repository.PaymentsRepository;
import org.modelmapper.ModelMapper;
import Exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    private PaymentsRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PaymentsDTO save(PaymentsFormDTO body) {
        Payments payments = this.repository.save(mapper.map(body, Payments.class));
        return mapper.map(payments, PaymentsDTO.class);
    }

    @Override
    public PaymentsDTO search(Long id) {
        Payments payments = this.repository.findById(id)
                .orElseThrow(() -> new BusinessException(404, "NOT_FOUND", "Payment not found"));
        return mapper.map(payments, PaymentsDTO.class);
    }

    @Override
    public Page<PaymentsDTO> findAll(Pageable page) {
        Page<Payments> payments = this.repository.findAll(page);
        List<PaymentsDTO> listPayments = payments.getContent()
                .stream()
                .map(payment -> mapper.map(payments, PaymentsDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<PaymentsDTO>(listPayments, page, payments.getTotalElements());
    }

    @Override
    public PaymentsDTO update(Long id, PaymentsFormDTO body) {
        Payments payments = this.repository.findById(id)
                .orElseThrow(() -> new BusinessException(404, "NOT_FOUND", "Payment not found"));
        payments.setType(body.getType());
        payments.setDiscount(body.getDiscount());
        payments.setStatus(body.isStatus());
        Payments updatedPayment = this.repository.save(payments);
        return mapper.map(updatedPayment, PaymentsDTO.class);
    }

    @Override
    public void delete(Long id) {
            Payments payments = this.repository.findById(id)
                    .orElseThrow(() -> new BusinessException(404, "NOT_FOUND", "Payment not found"));
            this.repository.delete(payments);
    }


}
