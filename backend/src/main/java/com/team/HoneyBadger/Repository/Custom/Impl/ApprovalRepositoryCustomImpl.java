package com.team.HoneyBadger.Repository.Custom.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.HoneyBadger.Entity.*;
import com.team.HoneyBadger.Repository.Custom.ApprovalRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.team.HoneyBadger.Entity.QApprover.approver;
import static com.team.HoneyBadger.Entity.QViewer.viewer;

@RequiredArgsConstructor
public class ApprovalRepositoryCustomImpl implements ApprovalRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QApproval qApproval = QApproval.approval;

    public List<Approval> findByUsername(String username) {

        return jpaQueryFactory
                .selectFrom (qApproval)
                .where (qApproval.approvers.any ().user.username.eq (username)
                        .or (qApproval.viewers.any ().user.username.eq (username))
                        .or (qApproval.sender.username.eq (username)))
                .orderBy (qApproval.createDate.desc ())
                .fetch ();

    }

}
