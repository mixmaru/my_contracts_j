package com.mixmaru.my_contracts_j.domain.application;


import com.mixmaru.my_contracts_j.domain.entity.CorporationUserEntity;
import com.mixmaru.my_contracts_j.domain.entity.IndividualUserEntity;
import com.mixmaru.my_contracts_j.domain.repository.CorporationUserRepository;
import com.mixmaru.my_contracts_j.domain.repository.IndividualUserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Service
public class UserApplication {

    public interface IUserResponse {
        IndividualUserEntity getIndividualUserEntity();
        CorporationUserEntity getCorporationUserEntity();
    }

    private static class UserResponse implements IUserResponse {

        private final IndividualUserEntity individualUserEntity;
        private final CorporationUserEntity corporationUserEntity;

        public UserResponse() {
            this.individualUserEntity = null;
            this.corporationUserEntity = null;
        }

        public UserResponse(
                IndividualUserEntity individualUserEntity,
                CorporationUserEntity corporationUserEntity) {
            this.individualUserEntity = individualUserEntity;
            this.corporationUserEntity = corporationUserEntity;
        }

        public IndividualUserEntity getIndividualUserEntity() {
            return individualUserEntity;
        }

        public CorporationUserEntity getCorporationUserEntity() {
            return corporationUserEntity;
        }
    }

    private final IndividualUserRepository individualUserRepository;
    private final CorporationUserRepository corporationUserRepository;

    public UserApplication(
            IndividualUserRepository individualUserRepository,
            CorporationUserRepository corporationUserRepository) {
        this.individualUserRepository = individualUserRepository;
        this.corporationUserRepository = corporationUserRepository;
    }

    /**
     * 個人userを新規登録する
     * @param name user名
     * @param createdAt 作成日
     * @return 新規登録された個人userのEntity
     */
    @Transactional
    public IndividualUserEntity registerNewIndividualUser(String name, ZonedDateTime createdAt) {
        var newUser = IndividualUserEntity.createNew(name, createdAt);
        return individualUserRepository.save(newUser);
    }

    /**
     * 個人userを取得する
     * @param id 個人userのid
     * @return 取得された個人userのentity
     */
    public IUserResponse getUser(long id) {

        // 個人user取得
        var individualUser = individualUserRepository.getById(id);
        if(individualUser.isPresent()) {
            return new UserResponse(individualUser.orElseThrow(), null);
        }

        // 企業user取得
        var corporationUser = corporationUserRepository.getById(id);
        if(corporationUser.isPresent()) {
            return new UserResponse(null, corporationUser.orElseThrow());
        }

        // なにもなければ空データを返す
        return new UserResponse();

    }

    public CorporationUserEntity registerNewCorporationUser(String contactPersonName, String presidentName, String corporationName, ZonedDateTime createdAt) {
        var newUser = CorporationUserEntity.createNew(
                corporationName,
                presidentName,
                contactPersonName,
                createdAt
        );
        return corporationUserRepository.save(newUser);
    }
}
