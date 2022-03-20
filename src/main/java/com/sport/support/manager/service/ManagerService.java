package com.sport.support.manager.service;

import com.sport.support.appuser.service.AppUserDetailsManager;
import com.sport.support.branch.service.BranchService;
import com.sport.support.infrastructure.exception.RecordIsNotFoundException;
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
    private final AppUserDetailsManager userDetailsManager;

    public List<Manager> retrieveAll() {
        return managerRepository.findAll();
    }

    public Manager retrieveById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new RecordIsNotFoundException(ManagerErrorMessages.ERROR_MANAGER_IS_NOT_FOUND));
    }

    @Transactional
    public void add(Manager manager) {
        manager.setBranch(branchService.retrieveById(manager.getBranch().getId()));
        userDetailsManager.updatePermissions(manager.getAppUser().getId(), RoleEnum.MANAGER);
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
