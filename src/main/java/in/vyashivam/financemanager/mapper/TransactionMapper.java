package in.vyashivam.financemanager.mapper;

import in.vyashivam.financemanager.model.Transaction;
import in.vyashivam.financemanager.model.TransactionDTO;

public class TransactionMapper {
    public static Transaction toEntity (TransactionDTO dto) {
        return Transaction.builder()
                .date(dto.getDate())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .build();
    }

    public static TransactionDTO toDto (Transaction transaction) {
        return TransactionDTO.builder()
                .date(transaction.getDate())
                .category(transaction.getCategory())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .build();
    }

}
