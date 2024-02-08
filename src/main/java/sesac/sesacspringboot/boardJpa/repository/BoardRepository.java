package sesac.sesacspringboot.boardJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sesac.sesacspringboot.boardJpa.Entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
