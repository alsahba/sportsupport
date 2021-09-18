package com.sport.support.member.controller;

import com.sport.support.member.controller.dto.AddMemberDTO;
import com.sport.support.member.controller.dto.AddMembershipDTO;
import com.sport.support.member.controller.dto.MemberDetailDTO;
import com.sport.support.member.controller.dto.UpdateMemberDTO;
import com.sport.support.member.entity.Member;
import com.sport.support.member.entity.Membership;
import com.sport.support.member.entity.enumeration.MemberStatus;
import com.sport.support.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping()
    public ResponseEntity<List<MemberDetailDTO>> getAll() {
        List<MemberDetailDTO> detailDTOList = memberService.retrieveAll().stream()
                .map(MemberDetailDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(detailDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberDetailDTO> get(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(new MemberDetailDTO(memberService.retrieveById(id)));
    }

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody @Valid AddMemberDTO addMemberDTO) {
        Long id = memberService.add(new Member(addMemberDTO));
        return new ResponseEntity<>("Member with ID = " + id + " added!", HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody @Valid UpdateMemberDTO updateMemberDTO) {
        memberService.update(new Member(updateMemberDTO));
        return new ResponseEntity<>("Member with ID = " + updateMemberDTO.getId() + " updated!",
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable @Min(1) Long id) {
        memberService.delete(id);
        return new ResponseEntity<>("Member with ID = " + id + " deleted!", HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{id}/ban")
    public ResponseEntity<String> ban(@PathVariable @Min(1) Long id) {
        memberService.updateMemberStatus(id, MemberStatus.BANNED);
        return new ResponseEntity<>("Member with ID = " + id + " banned!", HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{id}/pacify")
    public ResponseEntity<String> pacify(@PathVariable @Min(1) Long id) {
        memberService.updateMemberStatus(id, MemberStatus.PASSIVE);
        return new ResponseEntity<>("Member with ID = " + id + " pacified!", HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/{id}/membership")
    public ResponseEntity<String> membership(@PathVariable @Min(1) Long id,
                                             @RequestBody @Valid AddMembershipDTO addMembershipDTO) {
        Long membershipId = memberService.addMembership(new Membership(addMembershipDTO, id));
        return new ResponseEntity<>("Membership with ID = " + membershipId + " added!", HttpStatus.CREATED);
    }
}
