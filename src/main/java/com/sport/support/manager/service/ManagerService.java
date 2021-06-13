package com.sport.support.manager.service;

import com.sport.support.branch.service.BranchService;
import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import com.sport.support.manager.entity.Manager;
import com.sport.support.manager.messages.ManagerErrorMessages;
import com.sport.support.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final BranchService branchService;

    public List<Manager> retrieveAll() {
        return managerRepository.findAll();
    }

    public Manager retrieveById(Long id) {
        return managerRepository.findById(id).orElse(new Manager());
    }

    public Long add(Manager manager) {
        manager.setBranch(branchService.retrieveById(manager.getBranch().getId()));
        if (manager.isBranchExists()) {
            managerRepository.save(manager);
        }
        return manager.getId();
    }

    public void update(Manager manager) {
        managerRepository.findById(manager.getId()).ifPresentOrElse(managerDb -> {
            if (manager.isBranchExists()) {
                managerDb.update(manager);
                managerRepository.save(managerDb);
            }
        }, () -> {
            throw new RecordDoesNotExistException(ManagerErrorMessages.MANAGER_DOES_NOT_EXIST);
        });
    }

    public void delete(Long id) {
        managerRepository.findById(id).ifPresentOrElse(managerRepository::delete, () -> {
            throw new RecordDoesNotExistException(ManagerErrorMessages.MANAGER_DOES_NOT_EXIST);
        });
    }
}
