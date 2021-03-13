package bol.com.interview.kahala.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@Scope("singleton")
public class Board {

    private static Board instance = null;

    private BigPit southKahala;
    private BigPit northKahala;
    private List<Pit> northPits;
    private List<Pit> southPits;
    private boolean winner;

    public static Board getInstance(){
        if(instance == null){
            instance = new Board();
        }
        return instance;
    }
}
