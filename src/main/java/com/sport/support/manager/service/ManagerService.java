package com.sport.support.manager.service;

import com.sport.support.appuser.service.AppUserService;
import com.sport.support.branch.service.BranchService;
import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import com.sport.support.manager.entity.Manager;
import com.sport.support.manager.messages.ManagerErrorMessages;
import com.sport.support.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final BranchService branchService;
    private final AppUserService appUserService;

    public List<Manager> retrieveAll() {
        return managerRepository.findAll();
    }

    public Manager retrieveById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExistException(ManagerErrorMessages.MANAGER_DOES_NOT_EXIST));
    }

    @Transactional
    public void add(Manager manager) {
        manager.setBranch(branchService.retrieveById(manager.getBranch().getId()));
        appUserService.updatePermissions(manager.getAppUser().getId(), RoleEnum.MANAGER);
        managerRepository.save(manager);
    }

    public void update(Manager manager) {
        Manager managerDb = retrieveById(manager.getId());
        manager.setBranch(branchService.retrieveById(manager.getBranch().getId()));
        managerRepository.save(managerDb);
    }

    public void delete(Long id) {
        managerRepository.delete(retrieveById(id));
    }
}
