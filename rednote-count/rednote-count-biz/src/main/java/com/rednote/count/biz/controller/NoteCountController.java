package com.rednote.count.biz.controller;

import com.rednote.common.response.Result;
import com.rednote.count.api.dto.FindNoteCountsByIdRspDTO;
import com.rednote.count.api.dto.FindNoteCountsByIdsReqDTO;
import com.rednote.count.biz.service.NoteCountService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Jason Feng
 * @since 2026-11-22 11:55
 */
@RestController
@RequestMapping("/count")
@Slf4j
public class NoteCountController {

    @Resource
    private NoteCountService noteCountService;

    @PostMapping(value = "/notes/data")
    public Result<List<FindNoteCountsByIdRspDTO>> findNotesCountData(@RequestBody @Valid FindNoteCountsByIdsReqDTO findNoteCountsByIdsReqDTO) {
        return noteCountService.findNotesCountData(findNoteCountsByIdsReqDTO);
    }
}
