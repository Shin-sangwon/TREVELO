package com.ssafy.enjoytrip.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.attraction.controller.AttractionController;
import com.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import com.ssafy.enjoytrip.board.controller.BoardController;
import com.ssafy.enjoytrip.board.model.mapper.BoardMapper;
import com.ssafy.enjoytrip.board.model.service.BoardService;
import com.ssafy.enjoytrip.global.service.AmazonS3Service;
import com.ssafy.enjoytrip.member.controller.MemberController;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import com.ssafy.enjoytrip.room.controller.RoomController;
import com.ssafy.enjoytrip.room.model.mapper.RoomMapper;
import com.ssafy.enjoytrip.room.model.service.RoomService;
import com.ssafy.enjoytrip.roompicture.model.mapper.RoomPictureMapper;
import com.ssafy.enjoytrip.roompicture.model.service.RoomPictureService;
import com.ssafy.enjoytrip.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest({
    AttractionController.class,
    BoardController.class,
    MemberController.class,
    RoomController.class
})
@ActiveProfiles("test")
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected BoardService boardService;
    @MockBean
    protected BoardMapper boardMapper;
    @MockBean
    protected AttractionService attractionService;
    @MockBean
    protected AttractionMapper attractionMapper;
    @MockBean
    protected MemberService memberService;
    @MockBean
    protected MemberMapper memberMapper;
    @MockBean
    protected RoomService roomService;
    @MockBean
    protected RoomMapper roomMapper;
    @MockBean
    protected RoomPictureService roomPictureService;
    @MockBean
    protected RoomPictureMapper roomPictureMapper;
    @MockBean
    protected AmazonS3Service amazonS3Service;
    @MockBean
    protected UserDetailServiceImpl userDetailService;
}
