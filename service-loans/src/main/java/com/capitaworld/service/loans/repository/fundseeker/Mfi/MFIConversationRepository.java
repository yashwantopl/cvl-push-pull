package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MFIConversationRepository extends JpaRepository<MFIConversation, Long> {

    //@Query("from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.type= :type and fn.isActive = true")
    @Query("from MFIConversation where fromId=:fromId and applicationId=:applicationId")
    List<MFIConversation> findAllByFromId(@Param("fromId") Integer fromId,@Param("applicationId") Long applicationId);

    @Query("from MFIConversation where toId=:toId and applicationId=:applicationId")
    List<MFIConversation> findAllByToId(@Param("toId") Integer toId,@Param("applicationId") Long applicationId);

    @Query("from MFIConversation where applicationId=:applicationId and (fromId=:fromId and toId=:toId) OR (fromId=:toId AND toId=:fromId)")
    List<MFIConversation> findAllMessageByFromIdAndToId(@Param("fromId") Integer fromId, @Param("toId") Integer toId, @Param("applicationId") Long applicationId);

    @Query("from MFIConversation where toId=:toId and applicationId=:applicationId")
    List<MFIConversation> findAllReceiveMessageByFromIdAndToId(@Param("toId") Integer toId,@Param("applicationId") Long applicationId);

}
