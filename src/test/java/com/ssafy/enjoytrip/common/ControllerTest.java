package com.ssafy.enjoytrip.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.attraction.controller.AttractionController;
import com.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import com.ssafy.enjoytrip.board.controller.BoardController;
import com.ssafy.enjoytrip.board.model.mapper.BoardMapper;
import com.ssafy.enjoytrip.board.model.service.BoardService;
import com.ssafy.enjoytrip.comment.controller.CommentController;
import com.ssafy.enjoytrip.comment.model.mapper.CommentMapper;
import com.ssafy.enjoytrip.comment.model.service.CommentService;
import com.ssafy.enjoytrip.global.service.AmazonS3Service;
import com.ssafy.enjoytrip.member.controller.MemberController;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import com.ssafy.enjoytrip.payment.controller.PaymentController;
import com.ssafy.enjoytrip.payment.model.mapper.PaymentMapper;
import com.ssafy.enjoytrip.payment.model.service.PaymentService;
import com.ssafy.enjoytrip.plan.controller.PlanController;
import com.ssafy.enjoytrip.plan.model.mapper.PlanMapper;
import com.ssafy.enjoytrip.plan.model.service.PlanService;
import com.ssafy.enjoytrip.reservation.controller.ReservationController;
import com.ssafy.enjoytrip.reservation.model.mapper.ReservationMapper;
import com.ssafy.enjoytrip.reservation.model.service.ReservationService;
import com.ssafy.enjoytrip.review.controller.ReviewController;
import com.ssafy.enjoytrip.review.model.mapper.ReviewMapper;
import com.ssafy.enjoytrip.review.model.service.ReviewService;
import com.ssafy.enjoytrip.room.controller.RoomController;
import com.ssafy.enjoytrip.room.model.mapper.RoomMapper;
import com.ssafy.enjoytrip.room.model.service.RoomService;
import com.ssafy.enjoytrip.roompicture.model.mapper.RoomPictureMapper;
import com.ssafy.enjoytrip.roompicture.model.service.RoomPictureService;
import com.ssafy.enjoytrip.security.service.UserDetailServiceImpl;
import com.ssafy.enjoytrip.transaction.controller.TransactionController;
import com.ssafy.enjoytrip.transaction.model.mapper.TransactionMapper;
import com.ssafy.enjoytrip.transaction.model.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureRestDocs
@WebMvcTest({
    AttractionController.class,
    BoardController.class,
    MemberController.class,
    RoomController.class,
    PaymentController.class,
    ReservationController.class,
    TransactionController.class,
    CommentController.class,
    PlanController.class,
    ReviewController.class,
})
@ActiveProfiles("test")
public abstract class ControllerTest {

    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;
    @MockBean protected BoardService boardService;
    @MockBean protected BoardMapper boardMapper;
    @MockBean protected AttractionService attractionService;
    @MockBean protected AttractionMapper attractionMapper;
    @MockBean protected MemberService memberService;
    @MockBean protected MemberMapper memberMapper;
    @MockBean protected RoomService roomService;
    @MockBean protected RoomMapper roomMapper;
    @MockBean protected RoomPictureService roomPictureService;
    @MockBean protected RoomPictureMapper roomPictureMapper;
    @MockBean protected AmazonS3Service amazonS3Service;
    @MockBean protected UserDetailServiceImpl userDetailService;
    @MockBean protected ReservationService reservationService;
    @MockBean protected ReservationMapper reservationMapper;
    @MockBean protected TransactionService transactionService;
    @MockBean protected TransactionMapper transactionMapper;
    @MockBean protected PaymentService paymentService;
    @MockBean protected PaymentMapper paymentMapper;
    @MockBean protected CommentService commentService;
    @MockBean protected CommentMapper commentMapper;
    @MockBean protected ReviewService reviewService;
    @MockBean protected ReviewMapper reviewMapper;
    @MockBean protected PlanService planService;
    @MockBean protected PlanMapper planMapper;
}
