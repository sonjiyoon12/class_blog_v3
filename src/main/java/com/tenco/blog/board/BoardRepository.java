package com.tenco.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor // 생성자 자동 생성 + 멤버변수 -> DI 처리됨
@Repository // IoC 대상 + 싱글톤 패턴 관리 = 스프링 컨테이터
public class BoardRepository {

    // DI 의존 주입
    private final EntityManager em;

    /**
     * 전체 게시글 조회
     */
    public List<Board> findByAll() {
        // 네이티브쿼리 , JPQL 둘 중 하나 사용
        // 조회 - JPQL 쿼리 선택
        String jpql = "SELECT b FROM Board b ORDER BY b.id DESC ";
        TypedQuery query = em.createQuery(jpql, Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    /**
     * 게시글 단건 조회 (pk 기준)
     * @param id : Board 엔티티의 id 값
     * @return : Board 엔티티
     */
    public Board findById(Long id) {
        // 조회 - PK 조회는 무조건 엔티티 매니저에 메서드 활용이 이득이다.
        Board board = em.find(Board.class, id);

        return board;
    }
}
