package com.supinbank.services;

import com.supinbank.entities.Account;
import com.supinbank.entities.Operation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/29/12
 * Time: 2:33 PM
 * Service to handle basic operation on accounts.
 */
@Stateless
public class OperationService
{
    @Inject
    private GenericCrudService genericCrudService;

    public void createOperation(Account account, BigDecimal amount, String wording)
    {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setDate(new Date());
        operation.setAmount(amount);
        operation.setWording(wording);

        BigDecimal currentAmount = account.getAmount();
        BigDecimal newAmount = currentAmount.add(amount);
        account.setAmount(newAmount);
        account.getOperations().add(operation);

        genericCrudService.create(operation);
        genericCrudService.update(account);
    }

    public GenericCrudService getGenericCrudService()
    {
        return genericCrudService;
    }

    public void setGenericCrudService(GenericCrudService genericCrudService)
    {
        this.genericCrudService = genericCrudService;
    }
}
