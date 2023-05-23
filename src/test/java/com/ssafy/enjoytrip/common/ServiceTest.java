package com.ssafy.enjoytrip.common;

import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.entity.Grade;
import com.ssafy.enjoytrip.member.model.entity.Role;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import com.ssafy.enjoytrip.payment.model.service.PaymentService;
import com.ssafy.enjoytrip.reservation.model.service.ReservationDateService;
import com.ssafy.enjoytrip.reservation.model.service.ReservationService;
import com.ssafy.enjoytrip.room.model.service.RoomService;
import com.ssafy.enjoytrip.roompicture.model.service.RoomPictureService;
import com.ssafy.enjoytrip.transaction.model.service.TransactionService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

//TODO : @Sql로 Auto_increment값은 초기화가 되는데... 너무 오래걸림
@ActiveProfiles("test")
@Sql(scripts = "classpath:sql/tripdb_test.sql", config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
public class ServiceTest {

    @Autowired protected MemberService memberService;
    @Autowired protected RoomService roomService;
    @Autowired protected RoomPictureService roomPictureService;
    @Autowired protected ReservationService reservationService;
    @Autowired protected ReservationDateService reservationDateService;
    @Autowired protected TransactionService transactionService;
    @Autowired protected PaymentService paymentService;

    @BeforeEach
    void setUp() throws Exception {

        MemberJoinDto member1 = MemberJoinDto.builder()
                                             .loginId("test1")
                                             .loginPassword("test1")
                                             .name("테스트1")
                                             .birthday(LocalDateTime.now())
                                             .email("test1@test.com")
                                             .mileage(10000000L)
                                             .role(Role.ADMIN)
                                             .grade(Grade.GENERAL)
                                             .build();

        MemberJoinDto member2 = MemberJoinDto.builder()
                                             .loginId("test2")
                                             .loginPassword("test2")
                                             .name("테스트2")
                                             .birthday(LocalDateTime.now())
                                             .email("test2@test.com")
                                             .mileage(10000000L)
                                             .role(Role.SELLER)
                                             .grade(Grade.VIP)
                                             .build();

        MemberJoinDto member3 = MemberJoinDto.builder()
                                             .loginId("test3")
                                             .loginPassword("test3")
                                             .name("테스트3")
                                             .birthday(LocalDateTime.now())
                                             .email("test3@test.com")
                                             .mileage(0L)
                                             .role(Role.MEMBER)
                                             .grade(Grade.VVIP)
                                             .build();

        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);
    }
}
