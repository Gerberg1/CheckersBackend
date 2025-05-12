package com.example.checkersbackend;
import java.util.HashMap;
import java.util.List;

public class Moves {

    public Moves(){
        legalMovesBlack.put("a1", List.of("b2"));
        legalMovesBlack.put("c1", List.of("b2", "d2"));
        legalMovesBlack.put("e1", List.of("d2", "f2"));
        legalMovesBlack.put("g1", List.of("f2", "h2"));

        legalMovesBlack.put("b2", List.of("a3", "c3"));
        legalMovesBlack.put("d2", List.of("c3", "e3"));
        legalMovesBlack.put("f2", List.of("e3", "g3"));
        legalMovesBlack.put("h2", List.of("g3"));

        legalMovesBlack.put("a3", List.of("b4"));
        legalMovesBlack.put("c3", List.of("b4", "d4"));
        legalMovesBlack.put("e3", List.of("d4", "f4"));
        legalMovesBlack.put("g3", List.of("f4", "h4"));

        legalMovesBlack.put("b4", List.of("a5", "c5"));
        legalMovesBlack.put("d4", List.of("c5", "e5"));
        legalMovesBlack.put("f4", List.of("e5", "g5"));
        legalMovesBlack.put("h4", List.of("g5"));

        legalMovesBlack.put("a5", List.of("b6"));
        legalMovesBlack.put("c5", List.of("b6", "d6"));
        legalMovesBlack.put("e5", List.of("d6", "f6"));
        legalMovesBlack.put("g5", List.of("f6", "h6"));

        legalMovesBlack.put("b6", List.of("a7", "c7"));
        legalMovesBlack.put("d6", List.of("c7", "e7"));
        legalMovesBlack.put("f6", List.of("e7", "g7"));
        legalMovesBlack.put("h6", List.of("g7"));

        legalMovesBlack.put("a7", List.of("b8"));
        legalMovesBlack.put("c7", List.of("b8", "d8"));
        legalMovesBlack.put("e7", List.of("d8", "f8"));
        legalMovesBlack.put("g7", List.of("f8", "h8"));


        //moves white

        legalMovesWhite.put("b8", List.of("a7", "c7"));
        legalMovesWhite.put("d8", List.of("c7", "e7"));
        legalMovesWhite.put("f8", List.of("e7", "g7"));
        legalMovesWhite.put("h8", List.of("g7"));

        legalMovesWhite.put("a7", List.of("b6"));
        legalMovesWhite.put("c7", List.of("b6", "d6"));
        legalMovesWhite.put("e7", List.of("d6", "f6"));
        legalMovesWhite.put("g7", List.of("f6", "h6"));

        legalMovesWhite.put("b6", List.of("a5", "c5"));
        legalMovesWhite.put("d6", List.of("c5", "e5"));
        legalMovesWhite.put("f6", List.of("e5", "g5"));
        legalMovesWhite.put("h6", List.of("g5"));

        legalMovesWhite.put("a5", List.of("b4"));
        legalMovesWhite.put("c5", List.of("b4", "d4"));
        legalMovesWhite.put("e5", List.of("d4", "f4"));
        legalMovesWhite.put("g5", List.of("f4", "h4"));

        legalMovesWhite.put("b4", List.of("a3", "c3"));
        legalMovesWhite.put("d4", List.of("c3", "e3"));
        legalMovesWhite.put("f4", List.of("e3", "g3"));
        legalMovesWhite.put("h4", List.of("g3"));

        legalMovesWhite.put("a3", List.of("b2"));
        legalMovesWhite.put("c3", List.of("b2", "d2"));
        legalMovesWhite.put("e3", List.of("d2", "f2"));
        legalMovesWhite.put("g3", List.of("f2", "h2"));

        legalMovesWhite.put("b2", List.of("a1", "c1"));
        legalMovesWhite.put("d2", List.of("c1", "e1"));
        legalMovesWhite.put("f2", List.of("e1", "g1"));
        legalMovesWhite.put("h2", List.of("g1"));



        //jumps black

        legalJumpsBlack.put("a1", List.of("c3"));
        legalJumpsBlack.put("c1", List.of("a3", "e3"));
        legalJumpsBlack.put("e1", List.of("c3", "g3"));
        legalJumpsBlack.put("g1", List.of("e3"));

        legalJumpsBlack.put("b2", List.of("d4"));
        legalJumpsBlack.put("d2", List.of("b4", "f4"));
        legalJumpsBlack.put("f2", List.of("d4", "h4"));
        legalJumpsBlack.put("h2", List.of("f4"));

        legalJumpsBlack.put("a3", List.of("c5"));
        legalJumpsBlack.put("c3", List.of("a5", "e5"));
        legalJumpsBlack.put("e3", List.of("c5", "g5"));
        legalJumpsBlack.put("g3", List.of("e5"));

        legalJumpsBlack.put("b4", List.of("d6"));
        legalJumpsBlack.put("d4", List.of("b6", "f6"));
        legalJumpsBlack.put("f4", List.of("d6", "h6"));
        legalJumpsBlack.put("h4", List.of("f6"));

        legalJumpsBlack.put("a5", List.of("c7"));
        legalJumpsBlack.put("c5", List.of("a7", "e7"));
        legalJumpsBlack.put("e5", List.of("c7", "g7"));
        legalJumpsBlack.put("g5", List.of("e7"));

        legalJumpsBlack.put("b6", List.of("d8"));
        legalJumpsBlack.put("d6", List.of("b8", "f8"));
        legalJumpsBlack.put("f6", List.of("d8", "h8"));
        legalJumpsBlack.put("h6", List.of("f8"));

        legalJumpsBlack.put("a7", List.of());
        legalJumpsBlack.put("c7", List.of());
        legalJumpsBlack.put("e7", List.of());
        legalJumpsBlack.put("g7", List.of());

        legalJumpsBlack.put("b8", List.of());
        legalJumpsBlack.put("d8", List.of());
        legalJumpsBlack.put("f8", List.of());
        legalJumpsBlack.put("h8", List.of());


        //jumps white

        legalJumpsWhite.put("b8", List.of("d6"));
        legalJumpsWhite.put("d8", List.of("b6", "f6"));
        legalJumpsWhite.put("f8", List.of("d6", "h6"));
        legalJumpsWhite.put("h8", List.of("f6"));

        legalJumpsWhite.put("a7", List.of("c5"));
        legalJumpsWhite.put("c7", List.of("a5", "e5"));
        legalJumpsWhite.put("e7", List.of("c5", "g5"));
        legalJumpsWhite.put("g7", List.of("e5"));

        legalJumpsWhite.put("b6", List.of("d4"));
        legalJumpsWhite.put("d6", List.of("b4", "f4"));
        legalJumpsWhite.put("f6", List.of("d4", "h4"));
        legalJumpsWhite.put("h6", List.of("f4"));

        legalJumpsWhite.put("a5", List.of("c3"));
        legalJumpsWhite.put("c5", List.of("a3", "e3"));
        legalJumpsWhite.put("e5", List.of("c3", "g3"));
        legalJumpsWhite.put("g5", List.of("e3"));

        legalJumpsWhite.put("b4", List.of("d2"));
        legalJumpsWhite.put("d4", List.of("b2", "f2"));
        legalJumpsWhite.put("f4", List.of("d2", "h2"));
        legalJumpsWhite.put("h4", List.of("f2"));

        legalJumpsWhite.put("a3", List.of("c1"));
        legalJumpsWhite.put("c3", List.of("a1", "e1"));
        legalJumpsWhite.put("e3", List.of("c1", "g1"));
        legalJumpsWhite.put("g3", List.of("e1"));

        legalJumpsWhite.put("b2", List.of());
        legalJumpsWhite.put("d2", List.of());
        legalJumpsWhite.put("f2", List.of());
        legalJumpsWhite.put("h2", List.of());

        legalJumpsWhite.put("a1", List.of());
        legalJumpsWhite.put("c1", List.of());
        legalJumpsWhite.put("e1", List.of());
        legalJumpsWhite.put("g1", List.of());


        //jumps kings
        legalJumpsKing.put("a1", List.of("c3"));
        legalJumpsKing.put("c1", List.of("a3", "e3"));
        legalJumpsKing.put("e1", List.of("c3", "g3"));
        legalJumpsKing.put("g1", List.of("e3"));

        legalJumpsKing.put("b2", List.of("d4"));
        legalJumpsKing.put("d2", List.of("b4", "f4"));
        legalJumpsKing.put("f2", List.of("d4", "h4"));
        legalJumpsKing.put("h2", List.of("f4"));

        legalJumpsKing.put("a3", List.of("c5", "c1"));
        legalJumpsKing.put("c3", List.of("a5", "e5", "a1", "e1"));
        legalJumpsKing.put("e3", List.of("c5", "g5", "c1", "g1"));
        legalJumpsKing.put("g3", List.of("e5", "e1"));

        legalJumpsKing.put("b4", List.of("d6", "d2"));
        legalJumpsKing.put("d4", List.of("b6", "f6", "b2", "f2"));
        legalJumpsKing.put("f4", List.of("d6", "h6", "d2", "h2"));
        legalJumpsKing.put("h4", List.of("f6", "f2"));

        legalJumpsKing.put("a5", List.of("c7", "c3"));
        legalJumpsKing.put("c5", List.of("a7", "e7", "a3", "e3"));
        legalJumpsKing.put("e5", List.of("c7", "g7", "c3", "g3"));
        legalJumpsKing.put("g5", List.of("e7", "e3"));

        legalJumpsKing.put("b6", List.of("d8", "d4"));
        legalJumpsKing.put("d6", List.of("b8", "f8", "b4", "f4"));
        legalJumpsKing.put("f6", List.of("d8", "h8", "d4", "h4"));
        legalJumpsKing.put("h6", List.of("f8", "f4"));

        legalJumpsKing.put("a7", List.of("c5"));
        legalJumpsKing.put("c7", List.of("a5", "e5"));
        legalJumpsKing.put("e7", List.of("c5", "g5"));
        legalJumpsKing.put("g7", List.of("e5"));

        legalJumpsKing.put("b8", List.of("d6"));
        legalJumpsKing.put("d8", List.of("b6", "f6"));
        legalJumpsKing.put("f8", List.of("d6", "h6"));
        legalJumpsKing.put("h8", List.of("f6"));


        //kings
        legalMovesKing.put("a1", List.of("b2"));
        legalMovesKing.put("c1", List.of("b2", "d2"));
        legalMovesKing.put("e1", List.of("d2", "f2"));
        legalMovesKing.put("g1", List.of("f2", "h2"));

        legalMovesKing.put("b2", List.of("a1", "a3", "c1", "c3"));
        legalMovesKing.put("d2", List.of("c1", "c3", "e1", "e3"));
        legalMovesKing.put("f2", List.of("e1", "e3", "g1", "g3"));
        legalMovesKing.put("h2", List.of("g1", "g3"));

        legalMovesKing.put("a3", List.of("b2", "b4"));
        legalMovesKing.put("c3", List.of("b2", "b4", "d2", "d4"));
        legalMovesKing.put("e3", List.of("d2", "d4", "f2", "f4"));
        legalMovesKing.put("g3", List.of("f2", "f4", "h2", "h4"));

        legalMovesKing.put("b4", List.of("a3", "a5", "c3", "c5"));
        legalMovesKing.put("d4", List.of("c3", "c5", "e3", "e5"));
        legalMovesKing.put("f4", List.of("e3", "e5", "g3", "g5"));
        legalMovesKing.put("h4", List.of("g3", "g5"));

        legalMovesKing.put("a5", List.of("b4", "b6"));
        legalMovesKing.put("c5", List.of("b4", "b6", "d4", "d6"));
        legalMovesKing.put("e5", List.of("d4", "d6", "f4", "f6"));
        legalMovesKing.put("g5", List.of("f4", "f6", "h4", "h6"));

        legalMovesKing.put("b6", List.of("a5", "a7", "c5", "c7"));
        legalMovesKing.put("d6", List.of("c5", "c7", "e5", "e7"));
        legalMovesKing.put("f6", List.of("e5", "e7", "g5", "g7"));
        legalMovesKing.put("h6", List.of("g5", "g7"));

        legalMovesKing.put("a7", List.of("b6", "b8"));
        legalMovesKing.put("c7", List.of("b6", "b8", "d6", "d8"));
        legalMovesKing.put("e7", List.of("d6", "d8", "f6", "f8"));
        legalMovesKing.put("g7", List.of("f6", "f8", "h6", "h8"));

        legalMovesKing.put("b8", List.of("a7", "c7"));
        legalMovesKing.put("d8", List.of("c7", "e7"));
        legalMovesKing.put("f8", List.of("e7", "g7"));
        legalMovesKing.put("h8", List.of("g7"));
    }
    HashMap<String, List<String>> legalMovesBlack = new HashMap<>();
    HashMap<String, List<String>> legalJumpsBlack = new HashMap<>();
    HashMap<String, List<String>> legalMovesWhite = new HashMap<>();
    HashMap<String, List<String>> legalJumpsWhite = new HashMap<>();

    HashMap<String, List<String>> legalMovesKing = new HashMap<>();
    HashMap<String, List<String>> legalJumpsKing = new HashMap<>();



}
