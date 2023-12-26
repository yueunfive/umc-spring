package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.FoodCategory;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Terms;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberAgree;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.mapping.MemberPrefer;
import umc.study.dto.member.CreateMemberRequest;
import umc.study.dto.member.CreateMemberMissionRequest;
import umc.study.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final TermsRepository termsRepository;
    private final MemberAgreeRepository memberAgreeRepository;
    private final MemberPreferRepository memberPreferRepository;

    // 회원 가입
    @Transactional
    public Long join(CreateMemberRequest request) {
        validateDuplicateMember(request); //중복 회원 검증
        Member member = request.toEntity();

        List<MemberAgree> memberAgreeList = request.getTermsList().stream()
                .map(termsId -> {
                    Terms terms = termsRepository.findById(termsId).orElseThrow(() -> new IllegalArgumentException("Invalid term ID: " + termsId));
                    return MemberAgree.builder()
                            .member(member)
                            .terms(terms)
                            .build();
                })
                .collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = request.getFoodCategoryList().stream()
                .map(foodCategoryId -> {
                    FoodCategory foodCategory = foodCategoryRepository.findById(foodCategoryId).orElseThrow(() -> new IllegalArgumentException("Invalid food category ID: " + foodCategoryId));
                    return MemberPrefer.builder()
                            .member(member)
                            .foodCategory(foodCategory)
                            .build();
                })
                .collect(Collectors.toList());


        memberRepository.save(member);
        memberAgreeRepository.saveAll(memberAgreeList);
        memberPreferRepository.saveAll(memberPreferList);

        return member.getId();
    }

    // 중복 회원 검증(email)
    private void validateDuplicateMember(CreateMemberRequest request) {
        Member findMemberByEmail = memberRepository.findByEmail(request.getEmail());
        if (findMemberByEmail != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //단일 조회
    public Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
}
